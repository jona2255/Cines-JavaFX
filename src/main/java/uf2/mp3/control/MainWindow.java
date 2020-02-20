package uf2.mp3.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    private int i;
    private Stage stage;
    ObservableList<String> names = FXCollections.observableArrayList(
            "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");

    @FXML
    Button btn01;
    @FXML
    ListView<String> lsvLlista01;
    @FXML
    TextField txtDada;
    @FXML
    AnchorPane anchorMain;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        i=0;
        lsvLlista01.setItems(names);
        btn01.setStyle("-fx-background-radius: 15;");
        txtDada.setStyle("-fx-background-radius: 15");

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void btnClick(MouseEvent mouseEvent) {
        names.add(txtDada.getText());
    }

    public void clickChart(MouseEvent mouseEvent) {
       stage.close();
    }
}
