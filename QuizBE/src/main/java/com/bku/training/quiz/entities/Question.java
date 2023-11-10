package com.bku.training.quiz.entities;

import com.bku.training.quiz.enumeric.QuestionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Entity
@Table(name = "question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = "quiz")
public class Question extends BaseEntity{

    private String question;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Enumerated (EnumType.STRING)
    @Column (name = "question_type")
    private QuestionType questionType;

//    @ManyToOne
//    private Quiz quiz;
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<QuizDetails> quizDetails;

    @OneToMany (mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answers;

    @ManyToMany
    private Set<Topic> topics;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Question question = (Question) o;
        return Objects.equals(getId(), question.getId());
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
