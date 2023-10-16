package com.example.unipet.list.controller;

import com.example.unipet.list.domain.AnimalListDTO;
import com.example.unipet.list.service.AnimalListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalListController {
    @Autowired
    private AnimalListService animalService;

    @GetMapping("/list")
    public List<AnimalListDTO> getRecentAnimalList() {
        // API에서 데이터를 가져와서 동물 리스트로 변환
        List<AnimalListDTO> animalList = animalService.getRecentAnimalList();

        return animalList;
    }
}