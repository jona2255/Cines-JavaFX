package uf2.mp3.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChartExampleController implements Initializable {
    private ObservableList<PieChart.Data> dataCharts;
    private List<Integer> dadesInts = new ArrayList<>();

    @FXML
    PieChart pieChart01;
    @FXML
    BarChart<?,?> barChart01;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataCharts = FXCollections.observableArrayList();
        loadDataPieChart();
        pieChart01.setData(dataCharts);
        pieChart01.setTitle("Llenguatges de programació més usats");

        loadDataBarChart();
        barChart01.setTitle("Salari anual");
        x.setLabel("Llenguatges");
        y.setLabel("Euros");
    }

    private void loadDataPieChart() {
        dataCharts.add(new PieChart.Data("Java",30));
        dataCharts.add(new PieChart.Data("C#",25));
        dataCharts.add(new PieChart.Data("Python", 25));
        dataCharts.add(new PieChart.Data("Ruby",10));
        dataCharts.add(new PieChart.Data("R",10));
    }

    private void loadDataBarChart() {
        XYChart.Series set1 = new XYChart.Series<>();
        XYChart.Series set2 = new XYChart.Series<>();

        set1.getData().add(new XYChart.Data<>("Java", 50000));
        set1.getData().add(new XYChart.Data<>("C#", 45000));
        set1.getData().add(new XYChart.Data<>("Python", 35000));
        set1.getData().add(new XYChart.Data<>("Ruby", 35000));
        set1.getData().add(new XYChart.Data<>("R", 60000));

        barChart01.getData().addAll(set1);
        set1.setName("2019");

        set2.getData().add(new XYChart.Data<>("Java", 60000));
        set2.getData().add(new XYChart.Data<>("C#", 40000));
        set2.getData().add(new XYChart.Data<>("Python", 45000));
        set2.getData().add(new XYChart.Data<>("Ruby", 55000));
        set2.getData().add(new XYChart.Data<>("R", 60000));

        barChart01.getData().addAll(set2);
        set2.setName("2020");
    }
}
