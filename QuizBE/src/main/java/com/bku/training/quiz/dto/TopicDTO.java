package com.bku.training.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

/**
 * 
 * @author TuMT
 *
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TopicDTO {

	private Integer id;
	private String topicName;
	private Set<QuestionDTO> questions;

}
