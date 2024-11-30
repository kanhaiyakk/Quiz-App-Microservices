package com.kk.Question_Service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "questionTitle must not be  null")
    @Size(min = 3, message = "questionTitle must be atleast 3 characters")
    private String questionTitle;


    @NotBlank(message = "option1 must not be  null")
    private String option1;
    @NotBlank(message = "option2 must not be  null")
    private String option2;
    @NotBlank(message = "option3 must not be  null")
    private String option3;
    @NotBlank(message = "option4 must not be  null")
    private String option4;
    @NotBlank(message = "rightAnswer must not be  null")
    private String rightAnswer;
    @NotBlank(message = "difficultyLevel must not be  null")
    private String difficultyLevel;
    @NotBlank(message = "category must not be  null")
    private String category;
}

