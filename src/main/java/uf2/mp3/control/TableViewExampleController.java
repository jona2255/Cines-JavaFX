package uf2.mp3.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import uf2.mp3.model.Item;

import java.net.URL;
import java.util.ResourceBundle;

public class TableViewExampleController implements Initializable {
    @FXML
    TableView tblTaula01;

    private ObservableList<Item> dataTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataTable = FXCollections.observableArrayList();
        setTblTaula01();

    }

    private void setTblTaula01() {
        TableColumn columna1 = new TableColumn("c1");
        TableColumn columna2 = new TableColumn("c2");

        columna1.setCellValueFactory(new PropertyValueFactory<Item,String>("columna1"));
        columna2.setCellValueFactory(new PropertyValueFactory<Item,String>("columna2"));
        tblTaula01.getColumns().addAll(columna1,columna2);
        tblTaula01.setItems(dataTable);


        //afegir dades
        dataTable.add(new Item("dada1","valor1"));
        dataTable.add(new Item("dada2","valor2"));

    }


}
