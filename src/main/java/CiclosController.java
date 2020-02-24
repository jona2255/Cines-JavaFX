import control.Film;
import control.Sesion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CiclosController implements Initializable {

    ObservableList<Film> listObservableFilms = FXCollections.observableArrayList();

    String tituloCiclo;

    @FXML
    private Text cicloTitle;

    @FXML
    private TableView<Film> tableViewPeliculas;

    @FXML
    private TableColumn<Film, String> tableColumnTitleFilm;

    @FXML
    private TableColumn<Film, String> tableColumnDirectorFilm;

    @FXML
    private TableColumn<Film, String> tableColumnEstrenoFilm;

    @FXML
    private TableColumn<Film, String> tableColumnIdiomaFilm;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // recibe los atributos del film para mostrar
    public void recibeInfoSesiones(String tituloCiclo, ObservableList<Film> listObservableFilms) {
        this.listObservableFilms = listObservableFilms;
        this.tituloCiclo = tituloCiclo;

        añadirPeliculas();
    }

    // Muestra todos los films del ciclo seleccionado
    private void añadirPeliculas() {
        tableColumnDirectorFilm.setCellValueFactory(new PropertyValueFactory("direccio"));
        tableColumnTitleFilm.setCellValueFactory(new PropertyValueFactory("titol"));
        tableColumnIdiomaFilm.setCellValueFactory(new PropertyValueFactory("idioma"));
        tableColumnEstrenoFilm.setCellValueFactory(new PropertyValueFactory("any"));

        tableViewPeliculas.setItems(listObservableFilms);
        cicloTitle.setText(tituloCiclo);
    }

}
