/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.controller.dao.Dal;
import fitbox.model.BaseDeDatos;
import fitbox.model.Calendario;
import fitbox.model.Evento;
import fitbox.model.Jugador;
import fitbox.model.Noticia;
import fitbox.model.TablaActividad;
import fitbox.model.Usuario;
import fitbox.view.Clock;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.PauseTransition;
import javafx.animation.PauseTransitionBuilder;
import javafx.animation.SequentialTransition;
import javafx.animation.SequentialTransitionBuilder;
import javafx.animation.Timeline;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;

import javafx.scene.control.ListView;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;



import javafx.stage.Stage;
import javafx.util.Duration;
import org.joda.time.LocalDate;

/**
 * FXML Controller class
 *
 * @author RUBEN
 */
public class PantallaPrincipalController implements Initializable, ControlledScreen {

    /**
     * Initializes the controller class.
     */
    private Stage stageEquipo;
    private Stage stageClasificacion;
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
    private ImageView image;
    private ImageView image1;
    private ImageView image2;
    @FXML
    ProgressIndicator barraCargando;
    @FXML
    private ImageView Imagendesafios;
    @FXML
    private ImageView ImagenClasif;
    @FXML
    private ImageView ImagenAmigos;
    @FXML
    private Label labelActualizada;
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
       if(!ScreensFramework.cargarPantalla(ScreensFramework.PANTALLA_EDITARPERFIL))
        myController.loadScreen(ScreensFramework.PANTALLA_EDITARPERFIL, ScreensFramework.PANTALLA_EDITARPERFIL_FXML, recurso);
        
     

    }

    @FXML
    public void abrirActividades(MouseEvent event) throws IOException {
        if(!ScreensFramework.cargarPantalla(ScreensFramework.PANTALLA_ACTIVIDADES))
        myController.loadScreen(ScreensFramework.PANTALLA_ACTIVIDADES, ScreensFramework.PANTALLA_ACTIVIDADES_FXML, recurso);
        



    }

    @FXML
    public void abrirCalendario(MouseEvent event) throws IOException {
        if(!ScreensFramework.cargarPantalla(ScreensFramework.PANTALLA_VISTAMENSUAL))
        myController.loadScreen(ScreensFramework.PANTALLA_VISTAMENSUAL, ScreensFramework.PANTALLA_VISTAMENSUAL_FXML, recurso);
 


    }

    @FXML
    public void abrirVideos(MouseEvent event) throws IOException {
        if(!ScreensFramework.cargarPantalla(ScreensFramework.PANTALLA_SEGUIMIENTO))
        myController.loadScreen(ScreensFramework.PANTALLA_SEGUIMIENTO, ScreensFramework.PANTALLA_SEGUIMIENTO_FXML, recurso);
        

    }

    @FXML
    public void Actualizar(MouseEvent event) throws IOException, SQLException {

        actualizar();
    }

    @FXML
    public void abrirEquipo(MouseEvent event) throws IOException {
        if (stageEquipo == null) cargarEquipo();
        
            stageEquipo.show();
            stageEquipo.toFront();
       
    }

    @FXML
    public void abrirDesafios(MouseEvent event) throws IOException {
        if(!ScreensFramework.cargarPantalla(ScreensFramework.PANTALLA_DESAFIO))
        myController.loadScreen(ScreensFramework.PANTALLA_DESAFIO, ScreensFramework.PANTALLA_DESAFIO_FXML, recurso);
        
        
        
    }

    @FXML
    public void abrirClasificacion(ActionEvent event) throws IOException {

        if (stageClasificacion == null) cargarClasificacion();
       
            stageClasificacion.show();
            stageClasificacion.toFront();

    }

    @FXML
    public void abrirEventos(MouseEvent event) throws IOException {
        if(!ScreensFramework.cargarPantalla(ScreensFramework.PANTALLA_EVENTO))
        myController.loadScreen(ScreensFramework.PANTALLA_EVENTO, ScreensFramework.PANTALLA_EVENTO_FXML, recurso);
        

    }

    //es cerrarSesion
    @FXML 
    public void abrirAjustes(MouseEvent event) throws IOException {
//        if(stageClasificacion != null) stageClasificacion.close();
//        if(stageEquipo != null) stageEquipo.close();
        myController.loadScreen(ScreensFramework.PANTALLA_LOGIN, ScreensFramework.PANTALLA_LOGIN_FXML, recurso);

    }

    public void setMain(ScreensFramework main) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {



        this.recurso = (Recurso) rb;
        this.user = (Usuario) recurso.getObject("usuario");


        inicioReloj();

        inicioGaleria();
        cargarNoticias();
        cargarEventos();
        cargarResumen();

        cargarTablaActividades();



        ScreensFramework.stage.setResizable(true);
        ScreensFramework.stage.sizeToScene();
        //ScreensFramework.stage.centerOnScreen();

        ScreensFramework.stage.setMinWidth(970);
        ScreensFramework.stage.setMinHeight(580);



    }

    public void cargarEventos() {


        Collection<Evento> datosE = BaseDeDatos.getBD().getEventosUsuarioIniciado(user);
        //Collection<Evento> datosE = dal.find(Evento.TODOS_EVENTOS,new Object[]{},Evento.class);
//         Iterator<Evento> itdatosE = datosE.iterator();
//         Collection<String> eventosString = new ArrayList();
//         Evento e;
//         System.out.println("tallaEventos: "+datosE.size());
//         while(itdatosE.hasNext()){
//             e=itdatosE.next();
//            eventosString.add(e.toString());
//         }

        ObservableList<Evento> eventos = FXCollections.observableArrayList(datosE);
        listaEventos.setItems(eventos);


    }

    private void cargarNoticias() {

        List<Noticia> datos = BaseDeDatos.getBD().getNoticias();
//         Iterator<Noticia> itdatos = datos.iterator();
////         Collection<String> noticiasString = new ArrayList();
////         while(itdatos.hasNext()){
////         noticiasString.add(itdatos.next().toString());
////         }

        ObservableList<Noticia> noticias = FXCollections.observableArrayList(datos);
        listaNews.setItems(noticias);

    }

    public void cargarResumen() {

        Jugador jugador = BaseDeDatos.getBD().getJugador(user.getId());
//        Iterator<Jugador> itjugador = listjugador.iterator();
//        Jugador jugador = null;
//        if(itjugador.hasNext()) jugador = itjugador.next();

        //Hoy tienes X actividades para realizar y has realizado Y.
        LocalDate f = new LocalDate();
        String a = f.getYear() + "/" + f.getMonthOfYear() + "/" + f.getDayOfMonth() + "%";
        List<Calendario> calendarios = BaseDeDatos.getBD().getCALENDARIOSPORAÑODIAYJUGADOR(a, user.getId());
        Iterator<Calendario> it = calendarios.iterator();
        Calendario cal = null;
        System.out.println("talla: " + calendarios.size());

        int ActPorHacer = 0;
        int ActHechas = 0;
        while (it.hasNext()) {
            cal = it.next();
            cal.getFecha();
            if (cal.getEstadoActividad() == 0) {
                ActPorHacer++;
            } else {
                ActHechas++;
            }

        }
        String texto;
        if (calendarios.isEmpty()) {
            texto = "No tienes actividades programadas para hoy.";
        } else {
            texto = "Hoy tienes " + calendarios.size() + " actividades para realizar y has realizado " + ActHechas + ".";
            if (ActPorHacer != 0) {
                texto = texto + "\nRealiza las " + ActPorHacer + " actividades restantes!";
            } else {
                texto = texto + "\nHas realizado todas las actividades de hoy!";
            }
        }

        DecimalFormat df = new DecimalFormat("#.##");
        texto = texto + "\nTienes " + df.format(jugador.getPuntos()) + " puntos de jugador.";

        textoResumen.setText(texto);

    }

    public void cargarTablaActividades() {
//        Dal dal = Dal.getDal(); 

        List<TablaActividad> actividades = BaseDeDatos.getBD().getTablaActividadesUsuario(user.getId());

        ObservableList<TablaActividad> datos = FXCollections.observableArrayList(actividades);
        columnaHora.setCellValueFactory(new PropertyValueFactory<Calendario, String>("fecha"));
        columnaActividad.setCellValueFactory(new PropertyValueFactory<ResultSet, String>("nombre"));
        tablaActividad.setItems(datos);

    }

    public void inicioReloj() {
        if (clock == null) {
            clock = new Clock(Color.rgb(57, 168, 155), Color.rgb(53, 64, 62));
            clock.setLayoutX(10);
            clock.setLayoutY(30);
            clock.getTransforms().add(new Scale(0.4f, 0.4f, 0, 0));
            zonaReloj.getChildren().add(clock);
            clock.play();
        }
    }

    public void inicioGaleria() {




        Image[] images = new Image[3];
        images[0] = new Image("http://kalorien-guru.de/wp-content/uploads/2013/06/MQ-30.jpg", false);
        images[1] = new Image("http://i2.wp.com/runfitners.com/wp-content/uploads/2012/04/imagen-con-frase-motivadora-para-corredores11.jpg", false);
        images[2] = new Image("http://3.bp.blogspot.com/-dLwJkzM8iZU/UTywrD6b5CI/AAAAAAAABF8/T0BtZKjnbIY/s1600/imagen-con-frase-motivadora-para-corredores5050.jpg", false);



        image = new ImageView(images[0]);
        image1 = new ImageView(images[1]);
        image2 = new ImageView(images[2]);

        image.autosize();
        image1.autosize();
        image2.autosize();
        image.setFitHeight(412);
        image.setFitWidth(600);
        image1.setFitHeight(412);
        image1.setFitWidth(600);
        image2.setFitHeight(412);
        image2.setFitWidth(600);


        //redimensionar la imagen principal
        redimensionarImagenes();




        panelGaleria.getChildren().addAll(image, image1, image2);
        panelGaleria.toFront();

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
        //myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);
    }

    private void actualizar() throws SQLException {



        System.out.println("Actualizando ventana principal");
        cargarNoticias();
        cargarEventos();
        cargarResumen();
        cargarTablaActividades();
        System.out.println("Ventana principal Actualizada");


        labelActualizada.toFront();
        labelActualizada.setVisible(true);
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                System.out.println("timer..." + labelActualizada.getOpacity());

                if (labelActualizada.getOpacity() <= 0) {
                    labelActualizada.setVisible(false);
                    labelActualizada.setOpacity(1);
                    this.stop();
                } else {
                    labelActualizada.setOpacity(labelActualizada.getOpacity() - 0.05);
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();



    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void redimensionarImagenes() {
        panelGaleria.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                image.setFitWidth(panelGaleria.getWidth());
                image1.setFitWidth(panelGaleria.getWidth());
                image2.setFitWidth(panelGaleria.getWidth());
            }
        });

        panelGaleria.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                image.setFitHeight(panelGaleria.getHeight());
                image1.setFitHeight(panelGaleria.getHeight());
                image2.setFitHeight(panelGaleria.getHeight());
            }
        });

    }

    @FXML
    private void girarImagenDesafiosOn() {
        Imagendesafios.setRotate(20);
    }

    @FXML
    private void girarImagenDesafioOf() {
        Imagendesafios.setRotate(0);
    }

    @FXML
    private void girarImagenAmigosOn() {
        ImagenAmigos.setRotate(20);
    }

    @FXML
    private void girarImagenAmigosOf() {
        ImagenAmigos.setRotate(0);
    }

    @FXML
    private void girarImagenClasifOn() {
        ImagenClasif.setRotate(20);
    }

    @FXML
    private void girarImagenClasifOf() {
        ImagenClasif.setRotate(0);
    }

    private void cargarClasificacion() {

        stageClasificacion = new Stage();
        Parent root = null;
        try {
            recurso.putObject("controller", myController);
            root = FXMLLoader.load(getClass().getResource(ScreensFramework.PANTALLA_CLASIFICACION_FXML), recurso);
        } catch (IOException ex) {
            Logger.getLogger(AgregarAmigoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
        stageClasificacion.setScene(scene);
        //stageClasificacion.setResizable(false);
        stageClasificacion.setMinHeight(560);
        stageClasificacion.setMinWidth(420);
        stageClasificacion.setTitle("Clasificación " + ScreensFramework.tituloVentanaNombreUsuario);
        stageClasificacion.getIcons().add(new Image("/imagenes/Podium-icon.png"));
    }

    private void cargarEquipo() {
        stageEquipo = new Stage();
        Parent root = null;
        try {
            recurso.putObject("controller", myController);
            root = FXMLLoader.load(getClass().getResource("/fitbox/view/AgregarAmigo.fxml"), recurso);
        } catch (IOException ex) {
            Logger.getLogger(AgregarAmigoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
        stageEquipo.setScene(scene);
        stageEquipo.setResizable(false);
        stageEquipo.setTitle("Agregar Amigos " + ScreensFramework.tituloVentanaNombreUsuario);
        stageEquipo.getIcons().add(new Image("/imagenes/Groups-Meeting-Dark-icon.png"));
    }
}
