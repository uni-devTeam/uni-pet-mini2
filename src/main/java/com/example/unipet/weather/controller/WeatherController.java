package com.example.unipet.weather.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    @Value("${WAPI_KEY}")
    private String WAPI_KEY;

    @GetMapping("/weather")
    public String weatherPage(Model model) {
        model.addAttribute("WAPI_KEY", WAPI_KEY);
        return "weather";
    }
}
