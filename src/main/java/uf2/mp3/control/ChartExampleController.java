package uf2.mp3.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChartExampleController implements Initializable {
    private ObservableList<PieChart.Data> dataCharts;
    private List<Integer> dadesInts = new ArrayList<>();

    @FXML PieChart pieChart01;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataCharts = FXCollections.observableArrayList();
        loadDataPieChart();
        pieChart01.setData(dataCharts);
        pieChart01.setTitle("Llenguatges de programació més usats");
    }

    private void loadDataPieChart() {
        dataCharts.add(new PieChart.Data("Java",30));
        dataCharts.add(new PieChart.Data("C#",25));
        dataCharts.add(new PieChart.Data("Python", 25));
        dataCharts.add(new PieChart.Data("Ruby",10));
        dataCharts.add(new PieChart.Data("R",10));
    }
}
