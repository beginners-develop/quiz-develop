package com.bku.training.quiz.services.impl;

import com.bku.training.quiz.entities.Member;
import com.bku.training.quiz.entities.Role;
import com.bku.training.quiz.exception.AlreadyExistException;
import com.bku.training.quiz.exception.IdNotFoundException;
import com.bku.training.quiz.exception.NotFoundException;
import com.bku.training.quiz.repositories.MemberRepository;
import com.bku.training.quiz.repositories.RoleRepository;
import com.bku.training.quiz.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Add new member into DB
     */
    @Override
    public Member addNewMember(Member member) {
        if (memberRepository.existsByUsername(member.getUsername())) {
            throw new AlreadyExistException("Username " + member.getUsername() + " is existed!");
        }
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new AlreadyExistException("Email " + member.getEmail() + " is existed!");
        }
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        List<Role> roles = new ArrayList<>();
        for (Role e: member.getRoles()) {
            roles.add(roleRepository.findByRoleName(e.getRoleName()));
        }
        member.setRoles(roles);
        return memberRepository.save(member);
    }

    /**
     * get member by username
     */
    @Override
    public Member getMemberByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("The username " + username + " is not found"));
    }

    /**
     * get member by id
     */
    @Override
    public Member getMemberById(Integer id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Member is not exist"));
    }

    /**
     * update member
     */
    @Override
    public Member updateMember(Member member) {
        return memberRepository.save(member);
    }

    /**
     * update information of member
     */
    @Override
    public Member updateInfoMember(Member member) {
        Member updateMember = memberRepository.findById(
                member.getId()).orElseThrow(() -> new IdNotFoundException("Member is not found"));
        updateMember.setLastName(member.getLastName());
        updateMember.setFirstName(member.getFirstName());
        updateMember.setDateOfBirth(member.getDateOfBirth());
        updateMember.setEmail(member.getEmail());
        updateMember.setPhone(member.getPhone());
        if (member.getAvatar() != null) {
            updateMember.setAvatar(member.getAvatar());
        }
        return memberRepository.save(updateMember);
    }

    /**
     * Get member by email
     */
    @Override
    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("The email " + email + " is not found"));
    }

    @Override
    public void deleteMember(Member member) {
        memberRepository.delete(member);
    }


    /**
     * Increase number of failed login of user's account by 1
     */
    @Override
    public void increaseFailedLogin(Member member) {
        int newFailedLogin = member.getFailedLogin() + 1;
        memberRepository.updateNumberOfFailedLogin(newFailedLogin, member.getUsername());
    }

    /**
     * Reset number of failed login of user's account when user is logged successful
     */
    @Override
    public void resetFailedLogin(Member member) {
        memberRepository.updateNumberOfFailedLogin(0, member.getUsername());
    }

    /**
     * Lock member's account when user is failed to log-in equals or greater than {MAX_FAILED_LOGIN} times
     */
    @Override
    public void lockMember(Member member) {
        member.setAccountNonLocked(true);
        member.setLockTime(new Date());
        memberRepository.save(member);
    }

    /**
     * Member's account will be unlocked when the lock time is out of time
     */
    @Override
    public boolean unlockWhenOutOfLockTime(Member member) {
        // Time will be millis time
        long lockTime = member.getLockTime().getTime();
        long currentTime = System.currentTimeMillis();
        // unlock account if it's out of time
        if (lockTime + LOCK_TIME < currentTime) {
            member.setAccountNonLocked(false);
            member.setFailedLogin(0);
            member.setLockTime(null);
            memberRepository.save(member);
            return true;
        }
        return false;
    }
}
