
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.model.Usuario;
import fitbox.view.Clock;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.PauseTransition;
import javafx.animation.PauseTransitionBuilder;
import javafx.animation.SequentialTransition;
import javafx.animation.SequentialTransitionBuilder;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author RUBEN
 */
public class PantallaPrincipalController implements Initializable, ControlledScreen {

    /**
     * Initializes the controller class.
     */
    private FadeTransition fadeTransition;
    private FadeTransition fadeTransition1;
    private FadeTransition fadeTransition2;
    private FadeTransition fadeTransition3;
    private PauseTransition pauseTransition;
    private SequentialTransition sequentialTransition;
    ScreensController myController;
    private Recurso recurso;
    private Usuario user;
    private Clock clock;
    @FXML
    Parent root;
    @FXML
    private AnchorPane zonaReloj, panelGaleria, ventana;
    @FXML
    private ListView listaEventos, listaNews;
    @FXML
    private TableView tablaActividad;
    @FXML
    private TableColumn columnaHora, columnaActividad;
    @FXML
    private TextArea textoResumen;

    @FXML
    public void abrirPerfil(MouseEvent event) throws IOException {
        //  myController.setScreen(ScreensFramework.PANTALLA_PERFIL);
        // ResourceBundle d=new ResourceBundle();
    }

    @FXML
    public void abrirActividades(MouseEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_ACTIVIDADES, ScreensFramework.PANTALLA_ACTIVIDADES_FXML, null);
        myController.setScreen(ScreensFramework.PANTALLA_ACTIVIDADES);

    }

    @FXML
    public void abrirCalendario(MouseEvent event) throws IOException {
               myController.loadScreen(ScreensFramework.PANTALLA_REALIZARACTIVIDAD,ScreensFramework.PANTALLA_REALIZARACTIVIDAD_FXML,null);
                       myController.setScreen(ScreensFramework.PANTALLA_REALIZARACTIVIDAD);


    }

    @FXML
    public void abrirEstadisticas(MouseEvent event) throws IOException {
        //  myController.setScreen(ScreensFramework.PANTALLA_ESTADISTICAS);
    }

    @FXML
    public void actualizar(MouseEvent event) throws IOException {
        //  myController.setScreen(ScreensFramework.PANTALLA_ACTUALIZAR);
    }

    @FXML
    public void abrirEquipo(MouseEvent event) throws IOException {
        // myController.setScreen(ScreensFramework.PANTALLA_EQUIPO);
    }

    @FXML
    public void abrirEventos(MouseEvent event) throws IOException {
        //  myController.setScreen(ScreensFramework.PANTALLA_EVENTOS);
    }

    @FXML
    public void abrirClasificacion(MouseEvent event) throws IOException {
        // myController.setScreen(ScreensFramework.PANTALLA_CLASIFICACION);
    }

    @FXML
    public void abrirAjustes(MouseEvent event) throws IOException {
        //myController.setScreen(ScreensFramework.PANTALLA_AJUSTES);
    }

    public void setMain(ScreensFramework main) {



    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //  Stage stage=(Stage)lo.getScene().getWindow();
        // stage.setResizable(false);
        this.recurso=(Recurso)rb;
        this.user=(Usuario)recurso.getObject("usuario");

        inicioReloj();
        inicioGaleria();
        cargarEventos();
        
        ScreensFramework.stage.setWidth(921);
        ScreensFramework.stage.setHeight(590);
        ScreensFramework.stage.setResizable(false);
        ScreensFramework.stage.centerOnScreen();



    }

    public void cargarEventos() {
        /*
         Dal dal = Dal.getDal();
         Collection<Evento> datos = dal.find(Evento.TODOS_EVENTOS_USUARIOINICIADO,Evento.class);
         ObservableList<String> eventos=FXCollections.observableArrayList(datos);
         listaEventos.setItems(eventos);
       
         */
    }

    public void cargarResumen() {
        //Hoy tienes X actividades para realizar y has realizado Y.
    }

    public void cargarTablaActividades() {
        /*   ObservableList<Actividad> datos=FXCollections.observableArrayList();
         columnaHora.setCellValueFactory(new PropertyValueFactory("hora"));
         columnaActividad.setCellFactory(new PropertyValueFactory("nombre"));
         tablaActividad.setItems(datos);
         */
    }

    public void inicioReloj() {
        clock = new Clock(Color.rgb(57, 168, 155), Color.rgb(53, 64, 62));
        clock.setLayoutX(10);
        clock.setLayoutY(30);
        clock.getTransforms().add(new Scale(0.4f, 0.4f, 0, 0));
        zonaReloj.getChildren().add(clock);
        clock.play();
    }

    public void inicioGaleria() {




        Image[] images = new Image[3];
        images[0] = new Image("http://kalorien-guru.de/wp-content/uploads/2013/06/MQ-30.jpg", false);
        images[1] = new Image("http://i2.wp.com/runfitners.com/wp-content/uploads/2012/04/imagen-con-frase-motivadora-para-corredores11.jpg", false);
        images[2] = new Image("http://3.bp.blogspot.com/-dLwJkzM8iZU/UTywrD6b5CI/AAAAAAAABF8/T0BtZKjnbIY/s1600/imagen-con-frase-motivadora-para-corredores5050.jpg", false);


        ImageView image = new ImageView(images[0]);
        ImageView image1 = new ImageView(images[1]);
        ImageView image2 = new ImageView(images[2]);

        image.setFitHeight(500);
        image.setFitWidth(586);
        image1.setFitHeight(500);
        image1.setFitWidth(586);
        image2.setFitHeight(500);
        image2.setFitWidth(586);

        panelGaleria.getChildren().addAll(image, image1, image2);

        fadeTransition = FadeTransitionBuilder.create()
                .duration(Duration.seconds(5))
                .node(image2)
                .fromValue(1)
                .toValue(0)
                .build();

        pauseTransition = PauseTransitionBuilder.create()
                .duration(Duration.seconds(4))
                .build();

        fadeTransition1 = FadeTransitionBuilder.create()
                .duration(Duration.seconds(5))
                .node(image1)
                .fromValue(1)
                .toValue(0)
                .build();
        fadeTransition2 = FadeTransitionBuilder.create()
                .duration(Duration.seconds(5))
                .node(image2)
                .fromValue(0)
                .toValue(1)
                .build();

        sequentialTransition = SequentialTransitionBuilder.create()
                .node(image1)
                .children(fadeTransition, pauseTransition, fadeTransition1, pauseTransition, fadeTransition2)
                .cycleCount(Timeline.INDEFINITE)
                .autoReverse(true)
                .build();
        sequentialTransition.play();
    }

    @FXML
    private void goToMain(ActionEvent event) {
        myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
}
