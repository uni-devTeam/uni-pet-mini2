package com.example.unipet.boards.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("board")
public class ShareBoardController {

    @RequestMapping("sbview")
    public String shareBoardView() {
        return "shareBoardView";
    }

}
