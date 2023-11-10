package com.bku.training.quiz.services.impl;

import com.bku.training.quiz.dto.QuizDTO;
import com.bku.training.quiz.dto.SubmitQuiz;
import com.bku.training.quiz.entities.*;
import com.bku.training.quiz.enumeric.QuizStatus;
import com.bku.training.quiz.exception.IdNotFoundException;
import com.bku.training.quiz.exception.NotFoundException;
import com.bku.training.quiz.mapper.AnswerMapper;
import com.bku.training.quiz.mapper.MemberMapper;
import com.bku.training.quiz.mapper.QuestionMapper;
import com.bku.training.quiz.mapper.QuizMapper;
import com.bku.training.quiz.repositories.*;
import com.bku.training.quiz.services.QuizService;
import com.bku.training.quiz.dto.QuestionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizMapper quizMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuizDetailsRepository quizDetailsRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private MemberMapper memberMapper;

    private static  final Logger LOGGER = LoggerFactory.getLogger(Quiz.class);


    /**
     * create quiz
     */
    @Override
    public QuizDTO addNewQuiz(QuizDTO requestQuizDTO) {
        Quiz quiz = quizMapper.dtoToEntities(requestQuizDTO);
        Member member = memberRepository.findById(requestQuizDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User is not found"));
        quiz.setMember(member);
        quiz.setQuizStatus(QuizStatus.DRAFT);
        QuizDTO quizDTO = quizMapper.entityToDto(quizRepository.save(quiz));
        quizDTO.setUserId(member.getId());
        return quizDTO;
    }

    /**
     * continue creating quiz
     */
    @Override
    public QuizDTO updateNewQuiz(QuizDTO requestQuizDTO) {
        Quiz quiz = quizRepository.findById(requestQuizDTO.getQuizId())
                .orElseThrow(() -> new NotFoundException("Quiz is not found"));
        List<QuizDetails> listQuizDetails = new ArrayList<>();
        for (Integer id : requestQuizDTO.getQuestionIds()) {
            Question question = questionRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Question is not found"));
            // Store quiz and question into quiz details
            QuizDetails quizDetails = new QuizDetails();
            quizDetails.setQuestion(question);
            quizDetails.setQuiz(quiz);
            quizDetailsRepository.save(quizDetails);
            listQuizDetails.add(quizDetails);
        }
        quiz.setQuizDetails(listQuizDetails);
        return mappingQuizToQuizDTO(quizRepository.save(quiz));
    }

    /**
     * update Quiz's status DRAFT to PUBLISHED
     */
    @Override
    public Quiz updateStatusQuiz(Integer id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Quiz is not found"));
        quiz.setQuizStatus(QuizStatus.PUBLISHED);
        return quizRepository.save(quiz);
    }

    /**
     * get All quiz by status
     */
    @Override
    public List<QuizDTO> getAllByStatus(String status, Integer page, Integer size) {
        List<Quiz> quizzes;
        if (page == null || size == null) {
            quizzes = quizRepository.findAllByQuizStatus(Enum.valueOf(QuizStatus.class, status));
        } else {
            PageRequest pageRequest = PageRequest.of(page, size);
            quizzes = quizRepository.findAllByQuizStatus(Enum.valueOf(QuizStatus.class, status), pageRequest);
        }
        List<QuizDTO> quizDTOS = new ArrayList<>();
        for (Quiz quiz: quizzes) {
            QuizDTO quizDTO = mappingQuizToQuizDTO(quiz);
            quizDTOS.add(quizDTO);
        }
        return quizDTOS;
    }

    /**
     * get All quiz by Quiz name, using for search global
     */
    @Override
    public List<QuizDTO> getAllQuizByQuizName(String quizName) {
        List<Quiz> quizzes = quizRepository.findAllByQuizNameContaining(quizName);
        List<QuizDTO> quizzesDTO = new ArrayList<>();
        for (Quiz quiz: quizzes) {
            QuizDTO quizDTO = mappingQuizToQuizDTO(quiz);
            quizzesDTO.add(quizDTO);
        }
        return quizzesDTO;
    }

    /**
     * Get Quiz by quizId
     */
    @Override
    public QuizDTO getQuizById(Integer id) {
        return mappingQuizToQuizDTO(quizRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Quiz is not exist")));
    }

    @Override
    public void deleteQuizById(Integer id) {
        quizRepository.deleteById(id);
    }

    /**
     * Calculate point for Quiz
     */
    @Override
    public Double calculatePointOfQuiz(SubmitQuiz submitQuiz) {
        Score score = new Score();
        Member member = memberRepository.findById(submitQuiz.getUserId())
                .orElseThrow(() -> new NotFoundException("User is not found"));
        score.setMember(member);

        Quiz quiz = quizRepository.findById(submitQuiz.getQuizId())
                .orElseThrow(() -> new NotFoundException("Quiz is not found"));
        score.setQuiz(quiz);

        int correct = 0;
        int inCorrect = 0;
        // Calculate correct or inCorrect answer
        for (Map.Entry<Integer, Integer> mapQuestionAnswer : submitQuiz.getAnswerOfUser().entrySet()) {
            Answer answer = answerRepository.findByIdAndQuestion(
                    mapQuestionAnswer.getValue(), questionRepository.getById(mapQuestionAnswer.getKey()));
            if (answer == null || !answer.isCorrectAnswer()) {
                inCorrect++;
            } else {
                correct++;
            }
        }
        score.setCorrectAnswer(correct);
        score.setInCorrectAnswer(inCorrect);
        // Calculate Point (Maximum is 100 point)
        Double point = ((double) 100 / submitQuiz.getAnswerOfUser().size()) * score.getCorrectAnswer();
        score.setScore(point);
        scoreRepository.save(score);

        return point;
    }

    /**
     * Mapping Quiz To QuizDTO
     */
    private QuizDTO mappingQuizToQuizDTO(Quiz quiz) {
        QuizDTO quizDTO = quizMapper.entityToDto(quiz);
        quizDTO.setUserId(quiz.getMember().getId());
        quizDTO.setOwnerDTO(memberMapper.entityToOwnerDTO(quiz.getMember()));
        List<Question> questions = quiz.getQuizDetails().stream()
                .map(QuizDetails::getQuestion)
                .collect(Collectors.toList());
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        List<Integer> questionIds = new ArrayList<>();
        for (Question question: questions) {
            QuestionDTO questionDTO = questionMapper.entitiesToDTO(question);
            questionDTO.setAnswerDTOS(answerMapper.entitiesToDtoes(question.getAnswers()));
            Integer id = question.getId();
            questionDTOS.add(questionDTO);
            questionIds.add(id);
        }
        quizDTO.setListQuestion(questionDTOS);
        quizDTO.setQuestionIds(questionIds);
        return quizDTO;
    }

    /**
     *  Checking whether Quiz's status PUBLISHED is COMPLETED?
     */
    @Scheduled(initialDelay = 5000, fixedDelay = 5000)
    public void checkingQuizStatus() {
        LOGGER.info("Begin checking Quiz is outdated....");
        List<Quiz> quizzes = quizRepository.findAllByQuizStatus(QuizStatus.PUBLISHED);
        quizzes.forEach(e -> {
            if (e.getEndTime().isBefore(LocalDateTime.now())) {
                e.setQuizStatus(QuizStatus.COMPLETED);
            }
        });
    }
}
