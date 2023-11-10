package com.bku.training.quiz.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Entity
@Table(name = "token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token extends BaseEntity {

    //hh:mm:ss.000 -> 24 hours
    private static final long HOUR = 24*60*60*1000;

    private String token;

    private Date expireDate = new Date();

    @OneToOne (targetEntity = Member.class)
    @JoinColumn (name = "id", nullable = false)
    private Member member;

    public Token (Member member) {
        this.member = member;
        this.token = UUID.randomUUID().toString();
        expireDate = new Date(expireDate.getTime() + HOUR);
    }

}
