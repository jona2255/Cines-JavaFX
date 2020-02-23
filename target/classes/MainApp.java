import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, JAXBException {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Cines");
        Scene scene = new Scene(root);

        stage.setResizable(false);
        stage.setScene(scene);
//        stage.setResizable(false);
        stage.show();
    }
}
