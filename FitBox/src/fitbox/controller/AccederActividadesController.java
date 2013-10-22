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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private ScreensController myController;
    @FXML
    ListView listaCategorias;
    private Usuario usuario;
    @FXML
    AnchorPane panelActividades;
    private Hashtable<String, Actividad> botonActividad = new Hashtable<String, Actividad>();
    @FXML
    Button botonHome;
    private Recurso recurso;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.recurso=(Recurso)rb;
        usuario=(Usuario)recurso.getObject("usuario");
        cargarCategorias();
        ScreensFramework.stage.setResizable(true);
        ScreensFramework.stage.setWidth(916);
        ScreensFramework.stage.setHeight(522);
        ScreensFramework.stage.centerOnScreen();

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
        myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);
        
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
        int i = 0, j = 0, contador = 1;
        int contadorImagen = 0;
        while (it.hasNext()) {
            if (i % 3 == 0 && i != 0) {
                j++;
                contador++;
                i = 0;
            }
            actividad = it.next();
            Image im = new Image(getClass().getResource("/imagenes/" + actividad.getNombreImagen() + ".jpg").toExternalForm());
            ImageView imageView = new ImageView(im);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            Button boton = new Button(actividad.getNombre());
            boton.setStyle("    -fx-background-radius: 10; \n" +
"\n" +
"    -fx-background-insets: 0,1,2; \n" +
"\n" +
"        -fx-background-color:#0099FF;");
            boton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    Button botonPulsado = (Button) t.getSource();
                    
                    Actividad actividad = botonActividad.get(botonPulsado.getText());
                    Stage s = new Stage();
                    Parent root = null;
                    try {
                        Recurso resource = new Recurso();
                        resource.putObject("actividad", actividad);
                        resource.putObject("usuario", usuario);
                        resource.putObject("controller",myController);
                        root = FXMLLoader.load(getClass().getResource("/fitbox/view/DescripcionActividad.fxml"), resource);
                    } catch (IOException ex) {
                        Logger.getLogger(AccederActividadesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Scene scene = new Scene(root);
                    s.setScene(scene);
                    s.show();
                    ScreensFramework.stage.toBack();

                    //To change body of generated methods, choose Tools | Templates.
                }
            });
            panelActividades.getChildren().add(boton);
            boton.setLayoutX(26 + ((i * imageView.getFitWidth()) + (50 * i)));
            boton.setLayoutY(25 + ((contador * imageView.getFitHeight()) + (j * boton.getHeight()) + (30 * j)));
            botonActividad.put(actividad.getNombre(), actividad);
            
            panelActividades.getChildren().add(imageView);
            imageView.setLayoutX(20 + ((i * imageView.getFitWidth()) + (50 * i)));
            imageView.setLayoutY(20 + ((j * imageView.getFitHeight()) + (30 * j) + (boton.getHeight() * j)));
            i++;
            contadorImagen++;
            
        }
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
}
