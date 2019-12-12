package hk.edu.nihongokoza;

import hk.edu.nihongokoza.service.LocaleService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

import static hk.edu.nihongokoza.constant.SceneNameConstant.MAIN_MENU;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        LocaleService.getInstance().setCurrentLocale(Locale.CHINESE);
        scene = new Scene(loadFXML(MAIN_MENU));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/"+ fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void switchScene(String fxmlName) throws IOException {
        var stage = ((Stage) scene.getWindow());
        scene = new Scene(loadFXML(fxmlName));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}