package com.example.unipet.boards.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "board_no")
    private int boardNo;

//    @Column(name = "user_id")
    private String userId;

//    @Column(name = "board_id")
    private int boardId;

    private String title;
    private String content;

//    @Column(name = "posting_date", nullable = false)
    private LocalDateTime postingDate;

    @PrePersist
    public void prePersist() {
        this.postingDate = LocalDateTime.now();
    }

    private int views;

//    @Column(name = "like_counting")
    private int likeCounting;

//    @Column(name = "img_path")
    private String imgPath;

    //Board 엔터티가 삭제될 때 연결된 Comment 엔터티도 함께 삭제
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> comments;
}
