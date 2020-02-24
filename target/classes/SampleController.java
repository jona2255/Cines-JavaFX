import control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * The type Sample controller.
 */
public class SampleController implements Initializable {
    private final ObservableList<PieChart.Data> dataCharts = FXCollections.observableArrayList();


    /**
     * The Titulo film.
     */
    String tituloFilm;
    /**
     * The Titulo ciclo.
     */
    String tituloCiclo;
    /**
     * The Titulo cine.
     */
    String tituloCine;

    /**
     * The Conexion xml.
     */
    ConexionXML conexionXML;
    /**
     * The Images.
     */
    List<String> images;
    /**
     * The Url.
     */
    String url = "http://www.gencat.cat/llengua/cinema/";

    /**
     * The Films.
     */
    List<Film> films;
    /**
     * The Sesions.
     */
    List<Sesion> sesions;
    /**
     * The Cinemas.
     */
    List<Cinema> cinemas;
    /**
     * The Cicles.
     */
    List<Cicle> cicles;

    private double x=0, y=0;

    /**
     * The List observable sesions envio.
     */
    ObservableList<Sesion> listObservableSesionsEnvio =FXCollections.observableArrayList();
    /**
     * The List observable films envio.
     */
    ObservableList<Film> listObservableFilmsEnvio = FXCollections.observableArrayList();

    /**
     * The List observable films.
     */
    ObservableList<String> listObservableFilms =FXCollections.observableArrayList();
    /**
     * The List observable cicles.
     */
    ObservableList<String> listObservableCicles =FXCollections.observableArrayList();
    /**
     * The List observable cines.
     */
    ObservableList<String> listObservableCines =FXCollections.observableArrayList();


    // atributos de la pelicula
    @FXML
    private ListView<String> listViewFilms;
    @FXML
    private Text textTitleFilm;
    @FXML
    private ImageView imageFilm;
    @FXML
    private Text direcctorFilm;
    @FXML
    private Text directorTitle;
    @FXML
    private Text añoFilm;
    @FXML
    private Text añoTitle;
    @FXML
    private TextField textFieldPelicula;
    @FXML
    private Text sinopsiFilm;
    @FXML
    private Text sinopsiTitle;
    @FXML
    private Button buttonBuscar;

    // atributos del ciclo
    @FXML
    private ListView<String> listViewCiclos;
    @FXML
    private Text textTitleCiclo;
    @FXML
    private Text infoCiclo;
    @FXML
    private ImageView imageCiclo;

    // atributos de Cines
    @FXML
    private ListView<String> listViewCines;
    @FXML
    private Text textTitleCine;
    @FXML
    private Text provinciaTitle;
    @FXML
    private Text provinciaCine;
    @FXML
    private Text LocalidadTitle;
    @FXML
    private Text localidadCine;
    @FXML
    private Text direccionTitle;
    @FXML
    private Text direccionCine;
    @FXML
    private TextField textFieldCine;
    @FXML
    private Button buttonBuscarCine;

    // Atributos para diagramas
    @FXML
    private PieChart pieChart;

    @FXML
    private Pane pane;
    @FXML
    private BarChart<?, ?> sesionesChart;

    @FXML
    private CategoryAxis xChart;

    @FXML
    private NumberAxis yChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            pane.setVisible(false);
            connectedXML();
            loadFilms();
            loadCiclos();
            loadCines();
            opaqueInfoMovie();
            opaqueInfoCicle();
            opaqueInfoCine();
            diagrama();
            diagrama2();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Diaggrama films/años
    private void diagrama(){
        List<Integer> años = films.stream()
                .map(film -> film.getAny())
                .filter(i -> i > 0 && i < 3000).distinct()
                .sorted(Comparator.comparingInt(integer -> integer))
                .collect(Collectors.toList());

        for (Integer i: años) {
            long numResultat= films.stream()
                    .filter(film1 -> film1.getAny() == i)
                    .count();
            dataCharts.add(new PieChart.Data(i.toString(), numResultat));
        }

        pieChart.setData(dataCharts);
        pieChart.setLegendSide(Side.LEFT);

        final Label label = new Label();
        pane.getChildren().add(label);
        label.setFont(Font.font("SanSerif", FontWeight.BLACK, 20));

        pieChart.getData().stream().forEach(data -> {
            data.getNode().addEventHandler(MouseEvent.ANY, e->{
                int intValue = (int) data.getPieValue();
                pane.setVisible(true);
                if(intValue==1){
                    label.setText(intValue + " pelicula");
                }else {
                    label.setText(intValue + " peliculas");
                }
            });
        });
    }

    private void diagrama2(){
        List<String> localidad = sesions.stream()
                .map(sesion -> sesion.getLocalidad())
                .sorted()
                .distinct()
                .collect(Collectors.toList());

        for (String s: localidad){
            long numResultat = sesions.stream()
                    .filter(sesion -> sesion.getLocalidad().equals(s))
                    .count();
            XYChart.Series set1 = new XYChart.Series<>();
            set1.getData().add(new XYChart.Data(s,numResultat));
            sesionesChart.getData().addAll(set1);
        }
    }

    private void connectedXML() throws JAXBException, IOException {
        conexionXML = new ConexionXML();
        conexionXML.connectedFilms();
        conexionXML.connectedSessions();
        conexionXML.connectedCinema();
        conexionXML.connectedCicles();

        sesions = conexionXML.getSesions();
        cinemas = conexionXML.getCinemas();
        films = conexionXML.getFilms();
        cicles = conexionXML.getCicles();
    }

    private void loadFilms() {

        List<String> listaTitle = films.stream().map(film -> film.getTitol()).collect(Collectors.toList());
        images = films.stream().map(film -> film.getImage()).collect(Collectors.toList());

        listObservableFilms.addAll(listaTitle);
        listViewFilms.getItems().addAll(listObservableFilms);
    }

    private void loadCiclos(){
        List<String> listaTitle = cicles.stream().map(sesion -> sesion.getNombre()).collect(Collectors.toList());
        listObservableCicles.addAll(listaTitle);
        listViewCiclos.getItems().addAll(listObservableCicles);
    }

    private void loadCines(){
        List<String> listaTitle = cinemas.stream().map(Cinema::getNombre).collect(Collectors.toList());
        listObservableCines.addAll(listaTitle);
        listViewCines.getItems().addAll(listObservableCines);
    }

    /**
     * Display selected.
     *
     * @param mouseEvent the mouse event
     */
    public void displaySelected(MouseEvent mouseEvent) {

        if(mouseEvent.getSource() == listViewFilms){
            String filmTitle = listViewFilms.getSelectionModel().getSelectedItem();
            List<Sesion> listaSesionesFilm;
            if(filmTitle==null|| filmTitle.isEmpty()){
                textTitleFilm.setText("No has seleccionado ninguna pelicula");
            } else {
                visibleInfoMovie();
                textTitleFilm.setText(filmTitle);
                for (Film f: films) {
                    if(f.getTitol().equals(filmTitle)){
                        imageFilm.setImage(new Image(url+f.getImage()));
                        direcctorFilm.setText(f.getDireccio());
                        añoFilm.setText(String.valueOf(f.getAny()));
                        sinopsiFilm.setText(f.getSinopsi());
                        listObservableSesionsEnvio.clear();

                        //atributos que envio a la nueva ventana (sesiones)
                        tituloFilm = f.getTitol();
                        listaSesionesFilm = sesions.stream().filter(sesion -> f.getIdFilm() == sesion.getIdFilm()).collect(Collectors.toList());
                        listObservableSesionsEnvio.addAll(listaSesionesFilm);

                        listaSesionesFilm.clear();
                    }
                }
            }
        } else if(mouseEvent.getSource() == listViewCiclos){
            String cicleTitle = listViewCiclos.getSelectionModel().getSelectedItem();

            List<Film> listaFilmsCicle = new ArrayList<>();

            if(textTitleCiclo == null){
                textTitleCine.setText("No has seleccionado ninguna pelicula");
            } else {
                visibleInfoCicle();
                textTitleCiclo.setText(cicleTitle);

                for (Cicle c : cicles) {
                    if (c.getNombre().equals(cicleTitle)) {
                        infoCiclo.setText(c.getInfo());
                        imageCiclo.setImage(new Image(url + c.getImg()));

                        listObservableFilmsEnvio.clear();
                        //atributos que envio a la nueva ventana (films)

                        tituloCiclo = c.getNombre();

                        sesions.forEach(sesion -> {
                            if (sesion.getCicleID() == c.getIdCiclo()) {
                                films.forEach(film -> {
                                    if (film.getIdFilm() == sesion.getIdFilm()) {
                                        listaFilmsCicle.add(film);
                                    }
                                });
//                            listaFilmsCicle = films.stream().filter(film -> film.getIdFilm() == sesion.getCicleID()).collect(Collectors.toList());
                            }
                        });

                        listObservableFilmsEnvio.addAll(listaFilmsCicle);
                        listaFilmsCicle.clear();

                    }
                }
            }
        } else if(mouseEvent.getSource() == listViewCines){
            String titleCine = listViewCines.getSelectionModel().getSelectedItem();
            textTitleCine.setText(titleCine);

            List<Sesion> listaSesionesCines = new ArrayList<>();

            if(titleCine==null|| titleCine.isEmpty()){
                textTitleCine.setText("No has seleccionado ninguna pelicula");
            } else {
                visibleInfoCine();
                textTitleCine.setText(titleCine);
                for (Cinema f: cinemas) {
                    if(f.getNombre().equals(titleCine)){
                        provinciaCine.setText(f.getProvincia());
                        localidadCine.setText(f.getLocalidad());
                        direccionCine.setText(f.getAdreça());
                        listObservableSesionsEnvio.clear();

                        //atributos que envio a la nueva ventana (sesiones)
                        tituloCine = f.getNombre();
                        listaSesionesCines = sesions.stream().filter(sesion -> f.getIdCinema() == sesion.getIdCinema()).collect(Collectors.toList());
                        listObservableSesionsEnvio.addAll(listaSesionesCines);

                        listaSesionesCines.clear();
                    }
                }
            }
        }
    }

    /**
     * Page sesion.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void PageSesion(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        Parent root = loader.load(getClass().getResource("sesion.fxml").openStream());

        SesionController sesionController = loader.getController();
        sesionController.recibeInfoSesiones(tituloFilm, listObservableSesionsEnvio);

        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Page films ciclo.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void PageFilmsCiclo(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        Parent root = loader.load(getClass().getResource("ciclos.fxml").openStream());

        CiclosController ciclosController = loader.getController();
        ciclosController.recibeInfoSesiones(tituloCiclo, listObservableFilmsEnvio);

        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }


    /**
     * Visible info movie.
     */
    public void visibleInfoMovie(){
        textTitleFilm.setVisible(true);
        direcctorFilm.setVisible(true);
        directorTitle.setVisible(true);
        añoFilm.setVisible(true);
        añoTitle.setVisible(true);
        sinopsiFilm.setVisible(true);
        sinopsiTitle.setVisible(true);
    }

    /**
     * Opaque info movie.
     */
    public void opaqueInfoMovie(){
        textTitleFilm.setVisible(false);
        direcctorFilm.setVisible(false);
        directorTitle.setVisible(false);
        añoFilm.setVisible(false);
        añoTitle.setVisible(false);
        sinopsiFilm.setVisible(false);
        sinopsiTitle.setVisible(false);
    }

    /**
     * Visible info cicle.
     */
    public void visibleInfoCicle(){
        textTitleCiclo.setVisible(true);
        infoCiclo.setVisible(true);
        imageCiclo.setVisible(true);

    }

    /**
     * Opaque info cicle.
     */
    public void opaqueInfoCicle(){
        textTitleCiclo.setVisible(false);
        infoCiclo.setVisible(false);
        imageCiclo.setVisible(false);

    }

    /**
     * Visible info cine.
     */
    public void visibleInfoCine(){
        textTitleCine.setVisible(true);
        provinciaTitle.setVisible(true);
        provinciaCine.setVisible(true);
        LocalidadTitle.setVisible(true);
        localidadCine.setVisible(true);
        direccionTitle.setVisible(true);
        direccionCine.setVisible(true);
    }

    /**
     * Opaque info cine.
     */
    public void opaqueInfoCine(){
        textTitleCine.setVisible(false);
        provinciaTitle.setVisible(false);
        provinciaCine.setVisible(false);
        LocalidadTitle.setVisible(false);
        localidadCine.setVisible(false);
        direccionTitle.setVisible(false);
        direccionCine.setVisible(false);
    }

    /**
     * Buscador.
     *
     * @param mouseEvent the mouse event
     */
    public void buscador(MouseEvent mouseEvent) {
        listObservableFilms.clear();
        String titulo = textFieldPelicula.getText();

        List<String> listaTitle = films.stream().filter(film -> film.getTitol().toLowerCase().contains(titulo)).map(film -> film.getTitol()).collect(Collectors.toList());

        listObservableFilms.addAll(listaTitle);
        listViewFilms.getItems().clear();
        listViewFilms.getItems().addAll(listObservableFilms);

    }

    /**
     * Buscador cines.
     *
     * @param mouseEvent the mouse event
     */
    public void buscadorCines(MouseEvent mouseEvent) {
        listObservableCines.clear();
        String titulo = textFieldCine.getText();

        List<String> listaTitle = cinemas.stream().filter(cinema -> cinema.getNombre().toLowerCase().contains(titulo)).map(Cinema::getNombre).collect(Collectors.toList());

        listObservableCines.addAll(listaTitle);
        listViewCines.getItems().clear();
        listViewCines.getItems().addAll(listObservableCines);

    }

}
