package com.quizapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String questionTitle;

    private String option1;

    private String option2;

    private String option3;

    private String writeAnswer;

    private String difficultyLevel;

    private String category;
}
