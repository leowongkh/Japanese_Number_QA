package hk.edu.nihongokoza.service;

import hk.edu.nihongokoza.common.model.QuestionAnswerTypePair;
import hk.edu.nihongokoza.constant.QuestionType;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

public class QuestionTypeService {
    private static QuestionTypeService instance;

    private QuestionType selectQuestionType(){
        var random = new Random(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        var selectedIndex = random.nextInt(QuestionType.values().length);
        return QuestionType.values()[selectedIndex];
    }

    private QuestionType selectAnswerType(QuestionType selectedQuestion){
        var random = new Random(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        var selectedIndex = random.nextInt(QuestionType.values().length);
        var selectedAnswer = QuestionType.values()[selectedIndex];
        do {
            selectedIndex = random.nextInt(QuestionType.values().length);
            selectedAnswer = QuestionType.values()[selectedIndex];
        } while (selectedAnswer == selectedQuestion);
        return selectedAnswer;
    }

    public QuestionAnswerTypePair generateQuestionAnswerTypePair(){
        var questionType = selectQuestionType();
        var answerType = selectAnswerType(questionType);

        return new QuestionAnswerTypePair(questionType,answerType);
    }

    private QuestionTypeService() {
    }

    /**
     * Gets the instance for this service, for singleton dependency injection.
     *
     * @return the instance that this service provides
     */
    public static QuestionTypeService getInstance() {
        if (instance == null){
            instance = new QuestionTypeService();
        }
        return instance;
    }
}
