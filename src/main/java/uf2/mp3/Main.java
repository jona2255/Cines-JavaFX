package uf2.mp3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uf2.mp3.control.MainWindow;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/mainWindow.fxml"));
        AnchorPane root = loader.load();
        MainWindow mainWindow = loader.getController();
        mainWindow.setStage(stage);
        stage.setTitle("testFX");
        Scene sc = new Scene(root);
        sc.getStylesheets().add("css/styleCharts.css");
        stage.setScene(sc);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
