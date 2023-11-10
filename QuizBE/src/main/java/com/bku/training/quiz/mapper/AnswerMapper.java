package com.bku.training.quiz.mapper;

import com.bku.training.quiz.dto.AnswerDTO;
import com.bku.training.quiz.entities.Answer;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Mapper (componentModel = "spring")
public interface AnswerMapper {

    AnswerDTO entityToDto(Answer answer);

    List<AnswerDTO> entitiesToDtoes(List<Answer> answers);

    @InheritInverseConfiguration
    Answer dtoToEntity(AnswerDTO answerDTO);

}
