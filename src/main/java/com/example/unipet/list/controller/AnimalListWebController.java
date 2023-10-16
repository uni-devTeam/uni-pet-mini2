package com.example.unipet.list.controller;

import com.example.unipet.list.domain.AnimalListDTO;
import com.example.unipet.list.service.AnimalListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AnimalListWebController {

    @Autowired
    private AnimalListService animalService;

    @GetMapping("/animalList")
    public String getAnimalList(Model model) {
        List<AnimalListDTO> animalList = animalService.getRecentAnimalList();
        model.addAttribute("animalList", animalList);
        return "animalList";
    }
}
