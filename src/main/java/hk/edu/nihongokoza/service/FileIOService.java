package hk.edu.nihongokoza.service;

import hk.edu.nihongokoza.constant.FileDialogType;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

import static hk.edu.nihongokoza.constant.FileDialogType.MULTIPLE;

public class FileIOService {
    private static FileIOService instance;

    private FileIOService() {
    }

    public static FileIOService getInstance() {
        if (instance == null) {
            instance = new FileIOService();
        }
        return instance;
    }

    /**
     * Opens or Saves a single file, depending on the flag
     *
     * @param window           Owner window
     * @param fileDialogType   The type of action. Determines whether the file is to be opened or saved.
     * @param title            Title of the dialog
     * @param extensionFilters Extensions for the file types allowed
     * @return File that is opened or saved, <code>null</code> if <code>fileDialogType</code> is
     * not {@link FileDialogType#SINGLE SINGLE} or {@link FileDialogType#SAVE SAVE}
     */
    public File openOrSaveSingleFile(Window window, FileDialogType fileDialogType, String title,
                                     FileChooser.ExtensionFilter... extensionFilters) {
        if (fileDialogType == MULTIPLE) {
            throw new IllegalArgumentException("Illegal fileDialogType: " + fileDialogType.name());
        }
        var fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(extensionFilters);
        switch (fileDialogType) {
            case SINGLE:
                return fileChooser.showOpenDialog(window);
            case SAVE:
                return fileChooser.showSaveDialog(window);
            default:
                throw new IllegalArgumentException("Illegal fileDialogType: " + fileDialogType.name());
        }
    }
}
