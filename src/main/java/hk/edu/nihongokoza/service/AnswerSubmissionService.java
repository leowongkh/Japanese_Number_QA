package hk.edu.nihongokoza.service;

import hk.edu.nihongokoza.common.model.QuestionAnswerTypePair;
import hk.edu.nihongokoza.constant.QuestionType;
import hk.edu.nihongokoza.viewmodel.AnswerHistoryViewModel;
import hk.edu.nihongokoza.viewmodel.NumberWordViewModel;

import java.io.IOException;

import static hk.edu.nihongokoza.constant.JSONKeysConstant.*;
import static hk.edu.nihongokoza.constant.JSONKeysConstant.SceneName.COMMON_SCENE_NAME;
import static hk.edu.nihongokoza.constant.JSONKeysConstant.SceneName.IN_GAME_SCENE_NAME;
import static hk.edu.nihongokoza.datahold.DataHolder.ANSWERS;

public class AnswerSubmissionService {
    private static AnswerSubmissionService instance;

    private DifficultyService difficultyService= DifficultyService.getInstance();

    private JSONService jsonService = JSONService.getInstance();

    private GetJSONTextService getJSONTextService = GetJSONTextService.getInstance();

    private AnswerSubmissionService() {
    }

    private boolean checkAnswer(String answer, NumberWordViewModel question, QuestionType answerType) {
        switch (answerType){
            case KANJI:
                return question.getKanji().equals(answer);
            case HIRAGANA:
                return question.getHiragana().equals(answer);
            case NUMBER:
                return String.valueOf(question.getNumber().intValue()).equals(answer);
            default:
                throw new IllegalStateException("Unexpected value: " + answerType);
        }
    }

    public boolean submitAnswer(String answer, NumberWordViewModel question, QuestionAnswerTypePair questionAnswerTypePair){
        var answerHistoryViewModel = new AnswerHistoryViewModel();
        answerHistoryViewModel.setDifficulty(difficultyService.getDifficulty().name());
        try {

            var jsonTypeNode = jsonService.getJSONNode(IN_GAME_SCENE_NAME).findValue("types");
            switch (questionAnswerTypePair.getQuestionType()){
                case KANJI:

                    answerHistoryViewModel.setQuestion(question.getKanji());
                    break;
                case HIRAGANA:
                    answerHistoryViewModel.setQuestion(question.getHiragana());
                    break;
                case NUMBER:
                    answerHistoryViewModel.setQuestion(String.valueOf(question.getNumber().intValue()));
                    break;
            }

            var qTypeString = "";
            switch (questionAnswerTypePair.getAnswerType()){
                case KANJI:
                    qTypeString = jsonTypeNode.findValue("kanji").textValue();
                    if (qTypeString == null || qTypeString.isBlank()){
                        throw new NullPointerException();
                    }
                    answerHistoryViewModel.setQuestionType(qTypeString);
                    answerHistoryViewModel.setCorrectAnswer(question.getKanji());
                    break;
                case HIRAGANA:
                    qTypeString = jsonTypeNode.findValue("hiragana").textValue();
                    if (qTypeString == null || qTypeString.isBlank()){
                        throw new NullPointerException();
                    }
                    answerHistoryViewModel.setQuestionType(qTypeString);
                    answerHistoryViewModel.setCorrectAnswer(question.getHiragana());
                    break;
                case NUMBER:
                    qTypeString = jsonTypeNode.findValue("number").textValue();
                    if (qTypeString == null || qTypeString.isBlank()){
                        throw new NullPointerException();
                    }
                    answerHistoryViewModel.setQuestionType(qTypeString);
                    answerHistoryViewModel.setCorrectAnswer(String.valueOf(question.getNumber().intValue()));
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean correct = checkAnswer(answer, question, questionAnswerTypePair.getAnswerType());
        if (correct){
            answerHistoryViewModel.setYourAnswer(getJSONTextService.getSymbol(COMMON_SCENE_NAME, CORRECT_KEY, SYMBOL_KEY));
        }else{
            answerHistoryViewModel.setYourAnswer(answer);
        }
        ANSWERS.add(answerHistoryViewModel);
        return correct;
    }

    /**
     * Gets the instance for this service, for singleton dependency injection.
     *
     * @return the instance that this service provides
     */
    public static AnswerSubmissionService getInstance() {
        if (instance == null){
            instance = new AnswerSubmissionService();
        }
        return instance;
    }
}
