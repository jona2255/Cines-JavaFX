package uf2.mp3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/chartExample.fxml"));
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
