package com.bku.training.quiz.mapper;

import com.bku.training.quiz.dto.QuizDTO;
import com.bku.training.quiz.entities.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Mapper(componentModel = "spring")
public interface QuizMapper {

    @Mapping(source = "id", target = "quizId")
    QuizDTO entityToDto(Quiz quiz);



    @Mapping(source = "quizId", target = "id")
    Quiz dtoToEntities(QuizDTO quizDTO);

    List<QuizDTO> entitiesToQuizzDtoes(List<Quiz> quizzes);
}
