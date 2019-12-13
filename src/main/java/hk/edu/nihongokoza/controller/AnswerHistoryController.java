package hk.edu.nihongokoza.controller;

import com.fasterxml.jackson.databind.JsonNode;
import hk.edu.nihongokoza.App;
import hk.edu.nihongokoza.constant.Difficulties;
import hk.edu.nihongokoza.datahold.DataHolder;
import hk.edu.nihongokoza.service.DisplayTextService;
import hk.edu.nihongokoza.service.GenerateCSVService;
import hk.edu.nihongokoza.service.GetJSONTextService;
import hk.edu.nihongokoza.service.JSONService;
import hk.edu.nihongokoza.viewmodel.AnswerHistoryViewModel;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static hk.edu.nihongokoza.constant.JSONKeysConstant.AnswerHistoryKeys.*;
import static hk.edu.nihongokoza.constant.JSONKeysConstant.CORRECT_KEY;
import static hk.edu.nihongokoza.constant.JSONKeysConstant.SYMBOL_KEY;
import static hk.edu.nihongokoza.constant.JSONKeysConstant.SceneName.ANSWER_HISTORY_SCENE_NAME;
import static hk.edu.nihongokoza.constant.JSONKeysConstant.SceneName.COMMON_SCENE_NAME;

public class AnswerHistoryController {
    private JsonNode jsonNode = JSONService.getInstance().getJSONNode(ANSWER_HISTORY_SCENE_NAME);

    private DisplayTextService displayTextService = DisplayTextService.getInstance();

    private GetJSONTextService getJSONTextService = GetJSONTextService.getInstance();

    private GenerateCSVService generateCSVService = GenerateCSVService.getInstance();

    @FXML
    private TableView<AnswerHistoryViewModel> answerHistoryTableView;
    @FXML
    private TableColumn<AnswerHistoryViewModel, String> difficultyColumn;
    @FXML
    private TableColumn<AnswerHistoryViewModel, String> questionColumn;
    @FXML
    private TableColumn<AnswerHistoryViewModel, String> questionTypeColumn;
    @FXML
    private TableColumn<AnswerHistoryViewModel, String> correctAnswerColumn;
    @FXML
    private TableColumn<AnswerHistoryViewModel, String> yourAnswerColumn;

    @FXML
    private Label easyCorrectRateLabel;

    @FXML
    private Label mediumCorrectRateLabel;

    @FXML
    private Label hardCorrectRateLabel;

    @FXML
    private Label expertCorrectRateLabel;

    @FXML
    private Label correctHintDisplayLabel;

    @FXML
    private Label correctRateDisplayLabel;

    @FXML
    private Button exportToCSVButton;

    @FXML
    private Button returnButton;

    public AnswerHistoryController() throws IOException {
    }

    private void initializeDisplayText() {
        displayTextService.initializeSingleLabeledDisplayText(ANSWER_HISTORY_SCENE_NAME, correctRateDisplayLabel, CORRECT_RATE_KEY);
        displayTextService.initializeSingleLabeledDisplayText(ANSWER_HISTORY_SCENE_NAME, correctHintDisplayLabel, CORRECT_HINT_KEY);
        displayTextService.initializeSingleLabeledDisplayText(ANSWER_HISTORY_SCENE_NAME, exportToCSVButton, EXPORT_TO_CSV_KEY);
        displayTextService.initializeSingleLabeledDisplayText(ANSWER_HISTORY_SCENE_NAME, returnButton, RETURN_KEY);

        displayTextService.initializeSingleTableColumnDisplay(ANSWER_HISTORY_SCENE_NAME, difficultyColumn, DIFFICULTY_COLUMN_KEY);
        displayTextService.initializeSingleTableColumnDisplay(ANSWER_HISTORY_SCENE_NAME, questionColumn,QUESTION_COLUMN_KEY);
        displayTextService.initializeSingleTableColumnDisplay(ANSWER_HISTORY_SCENE_NAME, questionTypeColumn,QUESTION_TYPE_COLUMN_KEY);
        displayTextService.initializeSingleTableColumnDisplay(ANSWER_HISTORY_SCENE_NAME, correctAnswerColumn,CORRECT_ANSWER_COLUMN_KEY);
        displayTextService.initializeSingleTableColumnDisplay(ANSWER_HISTORY_SCENE_NAME, yourAnswerColumn,YOUR_ANSWER_COLUMN_KEY);

        var correctSymbolText = jsonNode.findValue("correctSymbolRep").textValue();

        var correctSymbol = getJSONTextService.getJSONTextValue(COMMON_SCENE_NAME, CORRECT_KEY, SYMBOL_KEY);
        correctHintDisplayLabel.setText(correctHintDisplayLabel.getText().replace("'" + correctSymbolText + "'", correctSymbol));
    }

    private void initializeTable() {
        difficultyColumn.setCellValueFactory(param -> param.getValue().difficultyProperty());
        questionColumn.setCellValueFactory(param -> param.getValue().questionProperty());
        questionTypeColumn.setCellValueFactory(param -> param.getValue().questionTypeProperty());
        correctAnswerColumn.setCellValueFactory(param -> param.getValue().correctAnswerProperty());
        yourAnswerColumn.setCellValueFactory(param -> param.getValue().yourAnswerProperty());

        var answerHistoryViewModelObservableList = FXCollections.observableArrayList(DataHolder.ANSWERS);

        answerHistoryTableView.setItems(answerHistoryViewModelObservableList);
    }

    private void initializeCorrectRates() {
        var easyList = answerHistoryTableView.getItems().filtered(answerHistoryViewModel -> answerHistoryViewModel.getDifficulty().equals(Difficulties.EASY.name()));
        var mediumList = answerHistoryTableView.getItems().filtered(answerHistoryViewModel -> answerHistoryViewModel.getDifficulty().equals(Difficulties.MEDIUM.name()));
        var hardList = answerHistoryTableView.getItems().filtered(answerHistoryViewModel -> answerHistoryViewModel.getDifficulty().equals(Difficulties.HARD.name()));
        var expertList = answerHistoryTableView.getItems().filtered(answerHistoryViewModel -> answerHistoryViewModel.getDifficulty().equals(Difficulties.EXPERT.name()));

        calculateCorrectRatesAndSetText(easyList, easyCorrectRateLabel);
        calculateCorrectRatesAndSetText(mediumList, mediumCorrectRateLabel);
        calculateCorrectRatesAndSetText(hardList, hardCorrectRateLabel);
        calculateCorrectRatesAndSetText(expertList, expertCorrectRateLabel);
    }

    private void calculateCorrectRatesAndSetText(FilteredList<AnswerHistoryViewModel> filteredList, Label label) {
        var percentageFormat = new DecimalFormat("#0.00%");
        percentageFormat.setRoundingMode(RoundingMode.HALF_UP);

        var correctString = getJSONTextService.getJSONTextValue(COMMON_SCENE_NAME, CORRECT_KEY, SYMBOL_KEY);
        if (filteredList.isEmpty()) {
            label.setText(" -.--% ");
        } else {
            var correctNumber = filteredList.filtered(answerHistoryViewModel -> answerHistoryViewModel.getYourAnswer().equals(correctString)).size();
            var correctRate = new BigDecimal(correctNumber).setScale(4, RoundingMode.UNNECESSARY).divide(new BigDecimal(filteredList.size()), RoundingMode.HALF_UP);
            var correctRateString = percentageFormat.format(correctRate);
            label.setText(correctRateString);
        }
    }

    @FXML
    private void initialize() {
        initializeDisplayText();
        initializeTable();
        initializeCorrectRates();
    }


    @FXML
    private void exportToCSV() {
        generateCSVService.createAndSaveCSVFileForAnswerHistory(answerHistoryTableView);
    }

    @FXML
    private void returnToMainMenu() throws IOException {
        App.switchScene("mainMenu");
    }
}
