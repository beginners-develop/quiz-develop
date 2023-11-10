package com.bku.training.quiz.services;

import com.bku.training.quiz.entities.Member;

/**
 * @author Nam Tran
 * @project Quiz
 **/
public interface MemberService {
    // Number of member can make failed login
    int MAX_FAILED_LOGIN = 3;
    // Lock member's account in 2 minutes
    long LOCK_TIME = 2 * 60 * 1000;

    Member addNewMember(Member member);
    Member getMemberByUsername(String username);
    Member getMemberById(Integer id);
    Member updateMember(Member member);
    Member updateInfoMember(Member member);
    Member getMemberByEmail(String email);
    void deleteMember(Member member);

    /**
     * Function for handling failed login
     */
    // Increase number of failed login
    void increaseFailedLogin(Member member);
    // Reset number of failed login when user is log-in successful
    void resetFailedLogin(Member member);
    // Lock member if Number of failed login is equals or greater than {MAX_FAILED_LOGIN}
    void lockMember(Member member);
    // Unlock account when it's out of {LOCK_TIME}
    boolean unlockWhenOutOfLockTime(Member member);
}
