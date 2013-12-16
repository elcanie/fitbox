/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.controller.dao.Dal;
import fitbox.model.Actividad;
import fitbox.model.Usuario;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RUBEN
 */
public class AccederActividadesController implements Initializable, ControlledScreen {

    /**
     * Initializes the controller class.
     */
    private ScreensController myController = new ScreensController(ScreensFramework.stage);
    @FXML
    ListView listaCategorias;
    private Usuario usuario;
    @FXML
    VBox panelActividades;
    //@FXML  AnchorPane panelActividades;
    private Hashtable<String, Actividad> botonActividad = new Hashtable<String, Actividad>();
    @FXML
    Button botonHome;
    private Recurso recurso;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.recurso = (Recurso) rb;
        usuario = (Usuario) recurso.getObject("usuario");
        cargarCategorias();
        ScreensFramework.stage.setResizable(true);
        ScreensFramework.stage.sizeToScene();
        listaCategorias.getSelectionModel().selectFirst();
        cargarActividades(null);

        //myController.setSize(600, 400);
    }

    public void cargarCategorias() {
        ObservableList<String> data = FXCollections.observableArrayList();
        data.addAll("Torso", "Cardio", "Pierna", "Abdominales", "Hit", "Estiramientos");
        listaCategorias.setItems(data);


    }

    @FXML
    public void goToHome(MouseEvent event) {
        myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);

    }

    @FXML
    public void cargarActividades(MouseEvent event) {
        panelActividades.getChildren().clear();
        Dal dal = Dal.getDal();
        int indice = listaCategorias.getSelectionModel().getSelectedIndex();
        String elemento = (String) listaCategorias.getItems().get(indice);
        elemento = elemento.toLowerCase();
        //La sentencia SQL debe cambiar a todas las actividades de esa categoria
        Collection<Actividad> datos = dal.find(Actividad.TODOS_ACTIVIDADESbyCATEGORIA, new Object[]{elemento}, Actividad.class);
        Iterator<Actividad> it = datos.iterator();
        Actividad actividad = null;

        ArrayList<HBox> listHB = new ArrayList<>();
        HBox nuevaHBox = new HBox();
        nuevaHBox.setAlignment(Pos.CENTER_LEFT);
        nuevaHBox.setSpacing(20);
        panelActividades.getChildren().setAll(nuevaHBox);
        listHB.add(nuevaHBox);
        while (it.hasNext()) {

            if (listHB.get(listHB.size() - 1).getChildren().size() > 8) {
                nuevaHBox = new HBox();
                nuevaHBox.setAlignment(Pos.CENTER_LEFT);
                nuevaHBox.setSpacing(20);
                panelActividades.getChildren().add(nuevaHBox);
                listHB.add(nuevaHBox);
            }

            actividad = it.next();
            Image im = new Image(getClass().getResource("/imagenes/" + actividad.getNombreImagen() + ".jpg").toExternalForm());
            ImageView imageView = new ImageView(im);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            Button boton = new Button(actividad.getNombre());
            boton.setStyle("    -fx-background-radius: 10; \n"
                    + "\n"
                    + "    -fx-background-insets: 0,1,2; \n"
                    + "\n"
                    + "        -fx-background-color:#0099FF;");
            boton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    if (t.getButton().equals(MouseButton.PRIMARY)) {
                        if (t.getClickCount() == 2) {
                            Button botonPulsado = (Button) t.getSource();
                            Actividad actividad = botonActividad.get(botonPulsado.getText());
                            System.out.println("botonPulsado.getText():--> " + botonPulsado.getText());
                            Stage s = new Stage();
                            s.setTitle("Realizar "+botonPulsado.getText());
                            s.getIcons().add(new Image("/imagenes/fito5.png"));
                            s.setResizable(false);
                            Parent root = null;
                            try {
                                Recurso resource = new Recurso();
                                resource.putObject("actividad", actividad);
                                resource.putObject("usuario", usuario);
                                resource.putObject("controller", myController);
                                root = FXMLLoader.load(getClass().getResource("/fitbox/view/DescripcionActividad.fxml"), resource);
                            } catch (IOException ex) {
                                Logger.getLogger(AccederActividadesController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            Scene scene = new Scene(root);
                            s.setScene(scene);
                            s.show();
                            ScreensFramework.stage.toBack();

                        }
                    }  //To change body of generated methods, choose Tools | Templates.
                }
            });

            VBox vb = new VBox();
            vb.getChildren().add(imageView);
            vb.getChildren().add(boton);
            vb.setAlignment(Pos.CENTER);
            vb.setSpacing(5);
            listHB.get(listHB.size() - 1).getChildren().add(vb);
            botonActividad.put(actividad.getNombre(), actividad);

        }
    }

       //Metodos barra de botones
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
    public void Actualizar(MouseEvent event) throws IOException {
        
        cargarCategorias();
        listaCategorias.getSelectionModel().selectFirst();
        cargarActividades(null);
    }
    
     @FXML
    public void abrirEventos(MouseEvent event) throws IOException {
       if(!ScreensFramework.cargarPantalla(ScreensFramework.PANTALLA_EVENTO))
         myController.loadScreen(ScreensFramework.PANTALLA_EVENTO, ScreensFramework.PANTALLA_EVENTO_FXML, recurso);
    }

    @FXML //es cerrarSesion
    public void abrirAjustes(MouseEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_LOGIN, ScreensFramework.PANTALLA_LOGIN_FXML, recurso);
    }

    @FXML
    private void home() {
       if(!ScreensFramework.cargarPantalla(ScreensFramework.PANTALLA_PRINCIPAL))
        myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
    }
    
    //FIN METODOS BARRA BOTONES
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
}
