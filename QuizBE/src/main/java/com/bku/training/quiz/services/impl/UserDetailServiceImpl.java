package com.bku.training.quiz.services.impl;

import com.bku.training.quiz.entities.Member;
import com.bku.training.quiz.entities.Role;
import com.bku.training.quiz.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * load user by username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " is not found"));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<Role> roles = member.getRoles();
        for (Role e : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(e.getRoleName()));
        }
        return User.withUsername(member.getUsername())
                .password(member.getPassword())
                .disabled(!member.isActivated()) // disable account
                .accountLocked(member.isAccountNonLocked()) // lock account
                .authorities(grantedAuthorities).build();
    }
}
