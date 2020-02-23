package uf2.mp3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uf2.mp3.control.FilmsController;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/filmsWindow.fxml"));
            AnchorPane root = loader.load();
            FilmsController filmsController = loader.getController();
            filmsController.setStage(stage);
            stage.setTitle("testFX");
            Scene sc = new Scene(root);
            sc.getStylesheets().add("css/styleCharts.css");
            stage.setScene(sc);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
