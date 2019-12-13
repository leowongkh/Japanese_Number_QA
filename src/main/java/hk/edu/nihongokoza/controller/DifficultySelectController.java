package hk.edu.nihongokoza.controller;

import com.fasterxml.jackson.databind.JsonNode;
import hk.edu.nihongokoza.App;
import hk.edu.nihongokoza.constant.Difficulties;
import hk.edu.nihongokoza.service.DifficultyService;
import hk.edu.nihongokoza.service.DisplayTextService;
import hk.edu.nihongokoza.service.JSONService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static hk.edu.nihongokoza.constant.Difficulties.*;
import static hk.edu.nihongokoza.constant.JSONKeysConstant.SceneName.DIFFICULTY_SELECT_SCENE_NAME;
import static hk.edu.nihongokoza.constant.SceneNameConstant.IN_GAME;
import static hk.edu.nihongokoza.constant.SceneNameConstant.MAIN_MENU;

public class DifficultySelectController {

    private static final ArrayList<Difficulties> DIFFICULTIES_LIST = new ArrayList<>(List.of(EASY, MEDIUM, HARD, EXPERT));
    private static final ObservableList<Difficulties> DIFFICULTIES_OBSERVABLE_LIST = FXCollections.observableArrayList(DIFFICULTIES_LIST);
    private JsonNode jsonNode = JSONService.getInstance().getJSONNode(DIFFICULTY_SELECT_SCENE_NAME);
    private DisplayTextService displayTextService = DisplayTextService.getInstance();
    private DifficultyService difficultyService = DifficultyService.getInstance();
    @FXML
    private Label titleLabel;

    @FXML
    private Label maxBoundLabel;

    @FXML
    private Label numberRangeDisplayLabel;

    @FXML
    private Label numberRangeToDisplayLabel;

    @FXML
    private Button startButton;

    @FXML
    private Button returnButton;

    @FXML
    private ComboBox<Difficulties> difficultySelectComboBox;

    public DifficultySelectController() throws IOException {
    }

    private void initializeComboBox() {
        difficultySelectComboBox.setItems(DIFFICULTIES_OBSERVABLE_LIST);
        difficultySelectComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            var decimalFormatSymbols = new DecimalFormatSymbols(Locale.JAPAN);
            var formatter = new DecimalFormat("###,###,###", decimalFormatSymbols);
            maxBoundLabel.setText(formatter.format(newValue.getBound()));
        });
        difficultySelectComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Difficulties object) {
                return object.name();
            }

            @Override
            public Difficulties fromString(String string) {
                try {
                    return Difficulties.valueOf(string);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }

    private void initializeDisplay() {
        displayTextService.initializeSingleComboBoxPromptText(jsonNode, "difficulty_prompt", difficultySelectComboBox);
        displayTextService.initializeSingleLabeledDisplayText(jsonNode, "title", titleLabel);
        displayTextService.initializeSingleLabeledDisplayText(jsonNode, "number_range", numberRangeDisplayLabel);
        displayTextService.initializeSingleLabeledDisplayText(jsonNode, "number_range_to", numberRangeToDisplayLabel);
        displayTextService.initializeSingleLabeledDisplayText(jsonNode, "unknown_number", maxBoundLabel);
        displayTextService.initializeSingleLabeledDisplayText(jsonNode, "start", startButton);
        displayTextService.initializeSingleLabeledDisplayText(jsonNode, "return", returnButton);
    }

    @FXML
    private void initialize() {
        initializeDisplay();
        initializeComboBox();
    }

    @FXML
    private void startGame() throws IOException {
        difficultyService.setDifficulty(difficultySelectComboBox.getValue());
        App.switchScene(IN_GAME);
    }

    @FXML
    private void returnToMainMenu() throws IOException {
        App.switchScene(MAIN_MENU);
    }
}
