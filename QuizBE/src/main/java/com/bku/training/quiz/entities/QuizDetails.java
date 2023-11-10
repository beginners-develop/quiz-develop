package com.bku.training.quiz.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Nam Tran
 * @project QuizBE
 **/
@Entity
@Table(name = "quiz_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDetails extends BaseEntity{

    @ManyToOne
    private Quiz quiz;

    @ManyToOne
    private Question question;

}
