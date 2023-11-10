package com.bku.training.quiz.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Data
public class QuizDTO {

    private Integer quizId;
    private String quizName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String quizStatus;
    private Integer userId;
    private List<Integer> questionIds;
    private List<QuestionDTO> listQuestion;
    private OwnerDTO ownerDTO;

}
