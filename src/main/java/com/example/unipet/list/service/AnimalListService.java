package com.example.unipet.list.service;

import com.example.unipet.list.converter.AnimalConverter;
import com.example.unipet.list.domain.AnimalListDTO;
import com.example.unipet.list.entity.Animal;
import com.example.unipet.list.util.JsonParsingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AnimalListService {

    @Autowired
    private AnimalApiService animalApiService;

    @Autowired
    private AnimalDbService animalDatabaseService;

    @Autowired
    private JsonParsingUtil jsonParsingUtil;

    @Autowired
    private AnimalConverter animalConverter;

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
    public void scheduledAnimalUpdate() {
        LocalDate today = LocalDate.now();
        String startDateString = today.minusDays(30).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String endDateString = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        animalDatabaseService.deleteExpiredAnimals(endDateString);

        String jsonResponse = animalApiService.callAnimalApi(LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("yyyyMMdd")), today);
        List<AnimalListDTO> newAnimals = jsonParsingUtil.parseJsonResponse(jsonResponse, today);

        List<Animal> animalEntities = newAnimals.stream()
                .map(animalConverter::convertToEntity)
                .collect(Collectors.toList());

        animalDatabaseService.insertNewAnimals(animalEntities);
    }

    //이부분 추가한것
    public AnimalListDTO getAnimalById(int id) {
        Animal animal = animalDatabaseService.findById(id); // 수정된 부분
        if (animal != null) {
            return animalConverter.convertToDTO(animal); // 수정된 부분
        }
        return null;
    }


    public List<AnimalListDTO> getRecentAnimalList(String startDateString) {
        LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate today = LocalDate.now();

        // 기존 DB에서 데이터를 가져옴
        List<Animal> existingAnimals = animalDatabaseService.getAnimalsAfterDate(startDate);
        List<AnimalListDTO> existingAnimalsDto = existingAnimals.stream()
                .map(animalConverter::convertToDTO)
                .collect(Collectors.toList());

        // API에서 새로운 데이터를 가져옴
        String jsonResponse = animalApiService.callAnimalApi(startDate, today);
        List<AnimalListDTO> newAnimals = jsonParsingUtil.parseJsonResponse(jsonResponse, today);

        // 기존 동물들의 ID 추출
        Set<Long> existingIds = existingAnimals.stream()
                .map(Animal::getDesertionNo)
                .collect(Collectors.toSet());

        // 중복되지 않는 새로운 동물들만 필터링
        List<AnimalListDTO> nonDuplicateNewAnimals = newAnimals.stream()
                .filter(animal -> !existingIds.contains(animal.getDesertionNo()))
                .collect(Collectors.toList());

        // 필요한 경우, 새로운 동물들을 DB에 추가
        List<Animal> newAnimalEntities = nonDuplicateNewAnimals.stream()
                .map(animalConverter::convertToEntity)
                .collect(Collectors.toList());
        animalDatabaseService.insertNewAnimals(newAnimalEntities); // 수정된 부분

        // 기존 데이터와 새로운 데이터를 합침
        existingAnimalsDto.addAll(nonDuplicateNewAnimals);
        return existingAnimalsDto;
    }

}