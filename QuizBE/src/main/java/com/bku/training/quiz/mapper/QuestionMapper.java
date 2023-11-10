package com.bku.training.quiz.mapper;

import java.util.Set;

import com.bku.training.quiz.entities.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bku.training.quiz.dto.QuestionDTO;
import com.bku.training.quiz.entities.Question;
@Mapper(componentModel = "spring")
public interface QuestionMapper {
	QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

	@Mapping(source = "topics", target = "topicName")
	QuestionDTO entitiesToDTO (Question question);
	default String topicToString (Topic topic) {
		return topic == null ? null : topic.getTopicName();
	}

	@Mapping(source = "topicName", target = "topics")
	Question DTOToEntities (QuestionDTO questionDTO);
	default Topic stringToTopic (String topicName) {
		return topicName == null ? null : new Topic(topicName);
	}

	Set<QuestionDTO> EntitiesToDto(Set<Question> questions);
}
