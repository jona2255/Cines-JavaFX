package uf2.mp3.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    private int i;
    ObservableList<String> names = FXCollections.observableArrayList(
            "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");

    @FXML
    Button btn01;
    @FXML
    ListView<String> lsvLlista01;
    @FXML
    TextField txtDada;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        i=0;
        lsvLlista01.setItems(names);

    }


    public void btnClick(MouseEvent mouseEvent) {
        names.add(txtDada.getText());
    }
}
