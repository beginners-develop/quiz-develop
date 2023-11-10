package com.bku.training.quiz.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class QuestionDTO {

	private Integer id;
	private LocalDateTime createTime;
	private String question;
	private List<String> topicName;
	private List<AnswerDTO> answerDTOS;

}
