package com.bku.training.quiz.config;

import com.bku.training.quiz.config.security.CustomAccessDeniedHandler;
import com.bku.training.quiz.config.security.CustomAuthFailureHandler;
import com.bku.training.quiz.config.security.CustomAuthSuccessHandler;
import com.bku.training.quiz.config.security.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Using for reCaptcha Google V2
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Autowired
    private CustomAuthFailureHandler customAuthFailureHandler;

    @Autowired
    private CustomAuthSuccessHandler customAuthSuccessHandler;

    @Autowired
    private CustomOAuth2UserService oAuth2UserService;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests().antMatchers("/", "/login", "/register").permitAll()
//                .antMatchers("/member/**").hasRole("MEMBER")
//                .antMatchers("/setting/**").hasAnyRole("MEMBER", "MANAGER", "ADMIN")
//                .antMatchers("/manager/").hasRole("MANAGER")
//                .antMatchers("/admin/").hasRole("ADMIN")
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .usernameParameter("username")
//                    .passwordParameter("password")
//                    .failureHandler(customAuthFailureHandler)
//                    .successHandler(customAuthSuccessHandler)
//                .and()
//                    .exceptionHandling()
//                    .accessDeniedHandler(customAccessDeniedHandler)
//                .and()
//                    .rememberMe().tokenValiditySeconds(60*60)
//                .and()
//                    .oauth2Login()
//                    .loginPage("/login")
//                    .permitAll()
//                    .userInfoEndpoint().userService(oAuth2UserService)
//                .and()
//                    .successHandler(customAuthSuccessHandler)
//                .and()
//                    .logout()
//                    .logoutUrl("/logout")
//                    .logoutSuccessUrl("/logoutSuccessful");
        http.cors()
                .and().csrf().disable().authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/public/**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
