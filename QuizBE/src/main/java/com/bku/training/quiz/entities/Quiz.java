package com.bku.training.quiz.entities;

import com.bku.training.quiz.enumeric.QuizStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Entity
@Table(name = "quiz")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = "member")
public class Quiz extends BaseEntity {

    @Column(name = "quiz_name")
    private String quizName;

    @Column(name = "quiz_status")
    @Enumerated (EnumType.STRING)
    private QuizStatus quizStatus;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    private String description;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.REMOVE)
    private List<Score> scores;

//    @OneToMany(mappedBy = "quiz", cascade = CascadeType.REMOVE)
//    private List<Question> questions;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.REMOVE)
    private List<QuizDetails> quizDetails;

}
