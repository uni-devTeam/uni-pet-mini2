package com.example.unipet.boards.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class BoardDTO {
    private int boardNo;
    private String userId;
    private int boardId;
    private String title;
    private String content;
    private String postingDate;
    private int views;
    private int likeCounting;
    private String imgPath;
}
