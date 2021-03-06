package hk.edu.nihongokoza.controller;

import hk.edu.nihongokoza.App;
import hk.edu.nihongokoza.common.model.QuestionAnswerTypePair;
import hk.edu.nihongokoza.converter.NumberWordModelConverter;
import hk.edu.nihongokoza.service.*;
import hk.edu.nihongokoza.viewmodel.NumberWordViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static hk.edu.nihongokoza.constant.JSONKeysConstant.*;
import static hk.edu.nihongokoza.constant.JSONKeysConstant.InGameKeys.*;
import static hk.edu.nihongokoza.constant.JSONKeysConstant.SceneName.COMMON_SCENE_NAME;
import static hk.edu.nihongokoza.constant.JSONKeysConstant.SceneName.IN_GAME_SCENE_NAME;
import static hk.edu.nihongokoza.constant.SceneNameConstant.MAIN_MENU;

public class InGameController {

    private DisplayTextService displayTextService = DisplayTextService.getInstance();

    private DifficultyService difficultyService = DifficultyService.getInstance();

    private NumberGenerationService numberGenerationService = NumberGenerationService.getInstance();

    private QuestionTypeService questionTypeService = QuestionTypeService.getInstance();

    private AnswerSubmissionService answerSubmissionService = AnswerSubmissionService.getInstance();

    private GetJSONTextService getJSONTextService = GetJSONTextService.getInstance();

    private NumberWordModelConverter numberWordModelConverter = NumberWordModelConverter.getInstance();

    private QuestionAnswerTypePair currentQuestionAnswerTypePair;

    private NumberWordViewModel numberWordViewModel = new NumberWordViewModel();

    @FXML
    private Label forTheFollowingDisplayLabel;
    @FXML
    private Label questionsTypeDisplayLabel;
    @FXML
    private Label enterDisplayLabel;
    @FXML
    private Label answerTypeDisplayLabel;
    @FXML
    private Label questionDisplayLabel;
    @FXML
    private TextField inputTextField;
    @FXML
    private Label resultLabel;
    @FXML
    private Button submitButton;
    @FXML
    private Button nextQuestionButton;
    @FXML
    private Button returnButton;

    private void initDisplayForSingleQuestion() {
        if (currentQuestionAnswerTypePair != null && numberWordViewModel != null) {
            switch (currentQuestionAnswerTypePair.getQuestionType()) {
                case KANJI:
                    displayTextService.initializeSingleLabeledDisplayText(COMMON_SCENE_NAME, questionsTypeDisplayLabel, TYPES_KEY, KANJI_KEY);
                    break;
                case HIRAGANA:
                    displayTextService.initializeSingleLabeledDisplayText(COMMON_SCENE_NAME, questionsTypeDisplayLabel, TYPES_KEY, HIRAGANA_KEY);
                    break;
                case NUMBER:
                    displayTextService.initializeSingleLabeledDisplayText(COMMON_SCENE_NAME, questionsTypeDisplayLabel, TYPES_KEY, NUMBER_KEY);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + currentQuestionAnswerTypePair.getQuestionType());
            }

            switch (currentQuestionAnswerTypePair.getAnswerType()) {
                case KANJI:
                    displayTextService.initializeSingleLabeledDisplayText(COMMON_SCENE_NAME, answerTypeDisplayLabel, TYPES_KEY, KANJI_KEY);
                    break;
                case HIRAGANA:
                    displayTextService.initializeSingleLabeledDisplayText(COMMON_SCENE_NAME, answerTypeDisplayLabel, TYPES_KEY, HIRAGANA_KEY);
                    break;
                case NUMBER:
                    displayTextService.initializeSingleLabeledDisplayText(COMMON_SCENE_NAME, answerTypeDisplayLabel, TYPES_KEY, NUMBER_KEY);
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + currentQuestionAnswerTypePair.getAnswerType());
            }

            var questionText = "";
            switch (currentQuestionAnswerTypePair.getQuestionType()) {
                case KANJI:
                    questionText = numberWordViewModel.getKanji();
                    break;
                case HIRAGANA:
                    questionText = numberWordViewModel.getHiragana();
                    break;
                case NUMBER:
                    questionText = numberWordViewModel.getNumber().toString();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + currentQuestionAnswerTypePair.getQuestionType());
            }

            questionDisplayLabel.setText(questionText);

            inputTextField.setText("");

        } else if (currentQuestionAnswerTypePair == null) {
            throw new NullPointerException("ERROR: currentQuestionAnswerTypePair is null!");
        } else {
            throw new NullPointerException("ERROR: numberWordViewModel is null!");
        }
    }

    private void initializeDisplay() {

        displayTextService.initializeSingleLabeledDisplayText(IN_GAME_SCENE_NAME, forTheFollowingDisplayLabel, PRE_QUESTION_TYPE_DISPLAY_KEY);
        displayTextService.initializeSingleLabeledDisplayText(IN_GAME_SCENE_NAME, enterDisplayLabel, PRE_ANSWER_TYPE_DISPLAY_KEY);
        displayTextService.initializeSingleLabeledDisplayText(IN_GAME_SCENE_NAME, submitButton, SUBMIT_KEY);
        displayTextService.initializeSingleLabeledDisplayText(IN_GAME_SCENE_NAME, nextQuestionButton, NEXT_QUESTION_KEY);
        displayTextService.initializeSingleLabeledDisplayText(IN_GAME_SCENE_NAME, returnButton, RETURN_KEY);

        displayTextService.initializeSingleTextInputControlPromptText(IN_GAME_SCENE_NAME, inputTextField, INPUT_FIELD_PROMPT_TEXT_KEY);

    }

    private void generateQuestion() {
        currentQuestionAnswerTypePair = questionTypeService.generateQuestionAnswerTypePair();
        var difficulty = difficultyService.getDifficulty();
        var numberWordModel = numberGenerationService.generateQuestion(difficulty.getBound());
        numberWordModelConverter.convertFromModelToViewModel(numberWordModel, numberWordViewModel);
    }

    @FXML
    private void initialize() {
        initializeDisplay();
        nextQuestion();
    }


    @FXML
    private void returnToMainMenu() throws IOException {
        App.switchScene(MAIN_MENU);
    }

    @FXML
    private void submitAnswer() {
        var answer = inputTextField.getText().trim();
        var result = answerSubmissionService.submitAnswer(answer, numberWordViewModel, currentQuestionAnswerTypePair);
        String title, contentText;
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.initOwner(inputTextField.getScene().getWindow());
        if (result) {
            // display tick
            resultLabel.setText(getJSONTextService.getJSONTextValue(COMMON_SCENE_NAME, CORRECT_KEY, SYMBOL_KEY));
            alert.setAlertType(Alert.AlertType.INFORMATION);
            title = getJSONTextService.getJSONTextValue(COMMON_SCENE_NAME, CORRECT_KEY, POP_UP_KEY, TITLE_KEY);
            contentText = getJSONTextService.getJSONTextValue(COMMON_SCENE_NAME, CORRECT_KEY, POP_UP_KEY, CONTENT_TEXT_KEY);
        } else {
            // display cross
            resultLabel.setText(getJSONTextService.getJSONTextValue(COMMON_SCENE_NAME, INCORRECT_KEY, SYMBOL_KEY));
            alert.setAlertType(Alert.AlertType.ERROR);
            title = getJSONTextService.getJSONTextValue(COMMON_SCENE_NAME, INCORRECT_KEY, POP_UP_KEY, TITLE_KEY);
            StringBuilder contentTextBuilder = new StringBuilder(getJSONTextService.getJSONTextValue(COMMON_SCENE_NAME, INCORRECT_KEY, POP_UP_KEY, CONTENT_TEXT_KEY));
            contentTextBuilder.append(getJSONTextService.getJSONTextValue(COMMON_SCENE_NAME, PUNCTUATION_KEY, QUOTATION_KEY, OPEN_KEY));
            switch (currentQuestionAnswerTypePair.getAnswerType()) {
                case KANJI:
                    contentTextBuilder.append(numberWordViewModel.getKanji());
                    break;
                case HIRAGANA:
                    contentTextBuilder.append(numberWordViewModel.getHiragana());
                    break;
                case NUMBER:
                    contentTextBuilder.append(numberWordViewModel.getNumber().toString());
                    break;
            }
            contentTextBuilder.append(getJSONTextService.getJSONTextValue(COMMON_SCENE_NAME, PUNCTUATION_KEY, QUOTATION_KEY, CLOSE_KEY));
            contentTextBuilder.append(getJSONTextService.getJSONTextValue(COMMON_SCENE_NAME, INCORRECT_KEY, POP_UP_KEY, CONTENT_AFTER_TEXT_KEY));
            contentText = contentTextBuilder.toString();
        }
        alert.setTitle(title);
        alert.setHeaderText("");
        alert.setContentText(contentText);
        submitButton.setDisable(true);
        nextQuestionButton.setDisable(false);
        alert.showAndWait();
    }

    @FXML
    private void nextQuestion() {
        generateQuestion();
        initDisplayForSingleQuestion();
        submitButton.setDisable(false);
        nextQuestionButton.setDisable(true);
    }
}
