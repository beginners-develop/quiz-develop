package com.bku.training.quiz.mapper;

import java.util.List;

import com.bku.training.quiz.dto.TopicDTO;
import org.mapstruct.Mapper;

import com.bku.training.quiz.entities.Topic;

@Mapper(componentModel = "spring")
public interface TopicMapper {
	Topic dtoToEntities(TopicDTO topicDTO);
	TopicDTO entitiesToDto(Topic topic);
	List<TopicDTO> EntitiesToDto(List<Topic> topics);
}
