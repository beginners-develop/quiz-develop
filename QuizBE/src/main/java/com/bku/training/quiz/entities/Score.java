package com.bku.training.quiz.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Entity
@Getter
@Setter
@Table(name = "score")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"quiz", "member"})
public class Score extends BaseEntity{

    private Double score;

    private Integer correctAnswer;

    private Integer inCorrectAnswer;

    @ManyToOne
    private Quiz quiz;

    @ManyToOne
    private Member member;

}
