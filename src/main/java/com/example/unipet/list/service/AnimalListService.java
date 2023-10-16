package com.example.unipet.list.service;

import com.example.unipet.list.dao.AnimalMapper;
import com.example.unipet.list.domain.AnimalListDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalListService {
    @Autowired
    private AnimalMapper animalMapper;

    public List<AnimalListDTO> getRecentAnimalList() {
        // API에서 데이터를 가져오는 로직을 구현
        String jsonResponse = allowBasic();
        List<AnimalListDTO> allRecentAnimals = parseJsonResponse(jsonResponse);

        // 최근 10개의 동물 정보로 제한
        if (allRecentAnimals.size() > 12) {
            allRecentAnimals = allRecentAnimals.subList(0, 12);
        }

        // 데이터베이스에서 최근에 업데이트된 데이터 가져오기
        List<AnimalListDTO> recentAnimalsFromDB = animalMapper.selectAllAnimals();

        // 중복 데이터 확인 및 최신 데이터만 선택
        List<AnimalListDTO> recentAnimals = new ArrayList<>();

        for (AnimalListDTO animal : allRecentAnimals) {
            boolean isDuplicate = false;
            for (AnimalListDTO existingAnimal : recentAnimalsFromDB) {
                if (animal.getNoticeNo().equals(existingAnimal.getNoticeNo())) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                recentAnimals.add(animal);
            }
        }

        // 데이터베이스에 데이터를 추가 (업데이트를 원하면 주석 해제)
        for (AnimalListDTO animal : recentAnimals) {
            //animalMapper.insertAnimal(animal);
            // 이미 데이터베이스에 존재하는 경우 업데이트하도록 하려면 아래 주석 해제
             animalMapper.updateAnimal(animal);
        }

        return recentAnimals;
    }






    public String allowBasic() {
        StringBuilder result = new StringBuilder();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic");
            urlBuilder.append("?" + URLEncoder.encode("bgnde", "UTF-8") + "=" + URLEncoder.encode("20230101", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("endde", "UTF-8") + "=" + URLEncoder.encode("20231001", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("12", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("serviceKey", "UTF-8") + "=wzo4W7QZeILljzxez1UjpoqqfoohiTZ6CHQGCVyxZIJRoV6ZXDLdpqbGrGftBwS6%2FO7AgBIUOL9VXLHzIEapEA%3D%3D");

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public List<AnimalListDTO> parseJsonResponse(String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            // JSON 데이터 "item" 배열 가져옴.
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode itemNode = rootNode.path("response").path("body").path("items").path("item");

            List<AnimalListDTO> animalList = new ArrayList<>();

            for (JsonNode item : itemNode) {
                AnimalListDTO dto = objectMapper.treeToValue(item, AnimalListDTO.class);
                // "보호중"인 동물만 데이터베이스에 저장
                if ("보호중".equals(dto.getProcessState())) {
                    animalList.add(dto);
                }
            }

            return animalList;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
