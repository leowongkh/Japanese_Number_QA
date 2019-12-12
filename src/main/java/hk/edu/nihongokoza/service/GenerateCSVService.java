package hk.edu.nihongokoza.service;

import hk.edu.nihongokoza.viewmodel.AnswerHistoryViewModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

import static hk.edu.nihongokoza.constant.FileDialogType.SAVE;
import static hk.edu.nihongokoza.constant.FileExtensionConstant.CSV_EXTENSION;
import static hk.edu.nihongokoza.constant.JSONKeysConstant.*;
import static hk.edu.nihongokoza.constant.JSONKeysConstant.SceneName.COMMON_SCENE_NAME;

public class GenerateCSVService {
    private static GenerateCSVService instance;

    private GetJSONTextService getJSONTextService = GetJSONTextService.getInstance();

    private FileIOService fileIOService = FileIOService.getInstance();

    private GenerateCSVService() {
    }

    public static GenerateCSVService getInstance() {
        if (instance == null) {
            instance = new GenerateCSVService();
        }
        return instance;
    }

    private String encapsulateWithDoubleQuote(String value) {
        return "\"" + value + "\"";
    }

    private String prepareCSVLine(Stream<String> stringStream) {
        return stringStream
                .map(s -> s.contains(",") || s.contains("\"") ? encapsulateWithDoubleQuote(s) : s)
                .reduce("", (s, s2) -> s2 == null || s2.isBlank() ? s : s.isBlank() ? s2 : s + "," + s2);
    }

    private void clearFile(File file) {
        try (var printWriter = new PrintWriter(file)) {
            printWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendToFile(String stringToAdd, File file) {
        try (var printWriter = new PrintWriter(new FileWriter(file, true))) {
            printWriter.println(stringToAdd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createAndSaveCSVFileForAnswerHistory(TableView<AnswerHistoryViewModel> tableView) {
        var csvDescription = getJSONTextService.getSymbol(COMMON_SCENE_NAME, CSV_SYMBOL_KEY);
        var csvExtensionFilter = new FileChooser.ExtensionFilter(csvDescription, CSV_EXTENSION);
        var dialogTitle = getJSONTextService.getSymbol(COMMON_SCENE_NAME, FILE_SAVE_TITLE_KEY);

        var file = fileIOService.openOrSaveSingleFile(tableView.getScene().getWindow(),
                SAVE,
                dialogTitle,
                csvExtensionFilter);

        if (file == null) {
            return;
        }

        clearFile(file);

        var firstLine = prepareCSVLine(tableView.getColumns().stream().map(TableColumn::getText));
        appendToFile(firstLine, file);

        tableView.getItems().stream()
                .map(answerHistoryViewModel ->
                        answerHistoryViewModel.getDifficulty() + ","
                                + answerHistoryViewModel.getQuestion() + ","
                                + answerHistoryViewModel.getQuestionType() + ","
                                + answerHistoryViewModel.getCorrectAnswer() + ","
                                + answerHistoryViewModel.getYourAnswer()
                )
                .forEach(s -> appendToFile(s, file));
    }

}
