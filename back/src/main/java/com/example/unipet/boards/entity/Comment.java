package com.example.unipet.boards.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne
    //@ManyToOne 어노테이션을 쓰면 Commnet Entity가 Board Entity를 여러 개 가질 수 있음
    @JoinColumn(name = "board_no")
    //@JoinColumn 어노테이션은 board_no 컬럼을 외래키로 사용한다는 의미
    @JsonIgnore
    private Board board; //Board Entity 참조

    private String content;

    @Column(name = "comment_date")
    private LocalDateTime commentDate;

    @PrePersist
    public void prePersist() {
        this.commentDate = LocalDateTime.now();
    }

}

