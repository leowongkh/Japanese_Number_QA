package hk.edu.nihongokoza.controller;

import com.fasterxml.jackson.databind.JsonNode;
import hk.edu.nihongokoza.App;
import hk.edu.nihongokoza.service.DisplayTextService;
import hk.edu.nihongokoza.service.JSONService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

import static hk.edu.nihongokoza.constant.JSONKeysConstant.SceneName.MAIN_MENU_SCENE_NAME;
import static hk.edu.nihongokoza.constant.SceneNameConstant.ANSWER_HISTORY;
import static hk.edu.nihongokoza.constant.SceneNameConstant.DIFFICULTY_SELECT;

public class MainMenuController {

    private JsonNode jsonNode = JSONService.getInstance().getJSONNode(MAIN_MENU_SCENE_NAME);

    private DisplayTextService displayTextService = DisplayTextService.getInstance();

    @FXML
    private Label titleLabel;

    @FXML
    private Button startButton;

    @FXML
    private Button answerHistoryButton;

    @FXML
    private Button exitButton;

    public MainMenuController() throws IOException {
    }

    private void initializeDisplay() {
        displayTextService.initializeSingleLabeledDisplayText(jsonNode, "title", titleLabel);
        displayTextService.initializeSingleLabeledDisplayText(jsonNode, "startGame", startButton);
        displayTextService.initializeSingleLabeledDisplayText(jsonNode, "answerHistory", answerHistoryButton);
        displayTextService.initializeSingleLabeledDisplayText(jsonNode, "exit", exitButton);
    }

    @FXML
    private void initialize() {
        initializeDisplay();
    }

    @FXML
    private void leaveApp(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void switchToAnswerHistory() throws IOException {
        App.switchScene(ANSWER_HISTORY);
    }

    @FXML
    private void startGame() throws IOException {
        App.switchScene(DIFFICULTY_SELECT);
    }
}
