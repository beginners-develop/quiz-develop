package com.bku.training.quiz.config;

import com.bku.training.quiz.entities.Role;
import com.bku.training.quiz.entities.Topic;
import com.bku.training.quiz.repositories.RoleRepository;
import com.bku.training.quiz.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Component
public class DataSeedingLogin implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<String> topics = Arrays.asList("Java", "Python", "SQL", "PHP", "CSS", "HTML", "Javascript");

        if (roleRepository.findByRoleName("ROLE_MEMBER") == null) {
            roleRepository.save(new Role("ROLE_MEMBER"));
            for (String e : topics) {
                Topic topic = new Topic();
                topic.setTopicName(e);
                topicRepository.save(topic);
            }
        }
        if (roleRepository.findByRoleName("ROLE_MANAGER") == null) {
            roleRepository.save(new Role("ROLE_MANAGER"));
        }
        if (roleRepository.findByRoleName("ROLE_ADMIN") == null) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }
    }
}
