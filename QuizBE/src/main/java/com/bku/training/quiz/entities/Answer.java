package com.bku.training.quiz.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Entity
@Table(name = "answer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = "question")
public class Answer extends BaseEntity{

    private String answer;

    @Column(name = "correct_answer")
    private boolean correctAnswer;

    @ManyToOne
    private Question question;

}
