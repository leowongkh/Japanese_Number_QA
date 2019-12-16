package hk.edu.nihongokoza.controller;

import hk.edu.nihongokoza.App;
import hk.edu.nihongokoza.service.DisplayTextService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

import static hk.edu.nihongokoza.constant.JSONKeysConstant.MainMenuKeys.*;
import static hk.edu.nihongokoza.constant.JSONKeysConstant.SceneName.MAIN_MENU_SCENE_NAME;
import static hk.edu.nihongokoza.constant.SceneNameConstant.ANSWER_HISTORY;
import static hk.edu.nihongokoza.constant.SceneNameConstant.DIFFICULTY_SELECT;

public class MainMenuController {

    private DisplayTextService displayTextService = DisplayTextService.getInstance();

    @FXML
    private Label titleLabel;

    @FXML
    private Button startButton;

    @FXML
    private Button answerHistoryButton;

    @FXML
    private Button exitButton;

    public MainMenuController() {
    }

    private void initializeDisplay() {
        displayTextService.initializeSingleLabeledDisplayText(MAIN_MENU_SCENE_NAME, titleLabel, TITLE_KEY);
        displayTextService.initializeSingleLabeledDisplayText(MAIN_MENU_SCENE_NAME, startButton, START_GAME_KEY);
        displayTextService.initializeSingleLabeledDisplayText(MAIN_MENU_SCENE_NAME,  answerHistoryButton, ANSWER_HISTORY_KEY);
        displayTextService.initializeSingleLabeledDisplayText(MAIN_MENU_SCENE_NAME, exitButton, EXIT_KEY);
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
