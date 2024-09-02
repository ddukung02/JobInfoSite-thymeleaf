package com.example.jobInfoSite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
//    크기가 2에서 30 사이여야 합니다
//    @Size(min=2, max=30)
    @Size(min=2, max=30, message = "제목은 2자이상 30자 이하입니다.")
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
