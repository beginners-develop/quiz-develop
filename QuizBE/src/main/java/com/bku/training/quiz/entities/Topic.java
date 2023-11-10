package com.bku.training.quiz.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Entity
@Table(name = "topic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("questions")
public class Topic extends BaseEntity{

    @Column(name = "topic_name")
    private String topicName;

    @ManyToMany (mappedBy = "topics")
    private Set<Question> questions;

    public Topic(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Topic topic = (Topic) o;
        return Objects.equals(getId(), topic.getId());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
