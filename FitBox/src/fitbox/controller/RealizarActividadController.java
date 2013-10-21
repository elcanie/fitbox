/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.Cronometro;
import fitbox.view.MediaControl;
import fitbox.controller.ScreensController;
import fitbox.controller.ScreensController;
import fitbox.controller.ScreensController;
import fitbox.controller.dao.Dal;
import fitbox.model.Actividad;
import fitbox.model.Jugador;
import fitbox.model.Usuario;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import jfx.messagebox.MessageBox;

public class RealizarActividadController implements Initializable, ControlledScreen {

    private ScreensController myController;
    @FXML
    private AnchorPane zonavideo;
    @FXML
    private WebView videoMuestra;
    @FXML
    private Label crono1;
    @FXML
    private Label crono2;
    private Cronometro crono;
    double puntos = 0;
    double puntosAct;
    private Recurso recurso;
    private String URL;// = "UNvAy1N6jvU";
    private Usuario usuario;
    private Actividad actividad;
    private Dal dal;
    private Jugador j;
    
    
    private final String MEDIA_URL = "<iframe width=\"508\" height=\"419\" src=\"http://www.youtube.com/embed/"+URL+"\" frameborder=\"0\" allowfullscreen></iframe>";
   
    @FXML
    public void grabar(){
        if(crono==null){
            crono = new Cronometro(this);
            Thread t = new Thread(crono);
            t.start();
        }
    }
    @FXML
    public void pausaGrabacion(){
        crono.finalize();
        int answer = MessageBox.show(ScreensFramework.stage,
						"¿Desea finalizar el ejercicio?",
						"Information dialog", 
						MessageBox.ICON_INFORMATION| MessageBox.OK | MessageBox.CANCEL);
 
		if (answer == MessageBox.OK) {
                    puntos = crono.getPuntos();
                    answer = MessageBox.show(ScreensFramework.stage,
						"Genial!! Has acumulado "+puntos+"\nAcumula puntos y gana puestos en el ranking!!",
						"Information dialog", 
						MessageBox.ICON_INFORMATION| MessageBox.OK);
                    
 
		if (answer == MessageBox.OK) {
                    ScreensFramework.stage.setWidth(921);
        ScreensFramework.stage.setHeight(590);
        myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);
        
        j.getPuntos();
        
        
//CONSULTA PARA OBTENER PUNTOS ACTUALES Pun
        
        //UPDATE PARA ACTUALIZAR LOS PUNTOS DEL USUARIO (PUNTOS ACT+PUNTOS)
        
		} else if (answer == MessageBox.CANCEL) {
		}
                    
		} else if (answer == MessageBox.CANCEL) {
                    crono.resume();
		}
            
        
    }
    public void actualizaLabels(final int h, final int m, final int s, final int c){

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                crono1.setText(h + ":" + m + ":" + s+ ":"+c);
                crono2.setText(h + ":" + m + ":" + s+ ":"+c);
            }
        });
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dal = Dal.getDal();
        this.recurso=(Recurso)rb;
        
        usuario=(Usuario) recurso.getObject("usuario");
        j = (Jugador)dal.find(Jugador.JUGADORBYUSUARIO, new Object[]{usuario.getId()}, Jugador.class).get(0);
        actividad=(Actividad) recurso.getObject("actividad");
        URL = actividad.getVideo();
        
        ScreensFramework.stage.setWidth(791);
        ScreensFramework.stage.setHeight(578);
        WebEngine webEngine= videoMuestra.getEngine();
        webEngine.loadContent(MEDIA_URL);
        videoMuestra.setLayoutX(-5);
        videoMuestra.setLayoutY(-4);
        //this.URL=dal.find(, parametros, null)
                //zonavideo.getChildren().add(videoMuestra);
        
//        mediaPlayer = new MediaPlayer(new Media(MEDIA_URL));
//        mediaPlayer.setAutoPlay(true);
//        mediaControl = new MediaControl(mediaPlayer);
//        mediaControl.setMinSize(514, 455);
//        mediaControl.setPrefSize(514, 455);
//        mediaControl.setMaxSize(514, 455);
//        zonavideo.getChildren().add(mediaControl);
        
    }
    @FXML
    public void home(){
        
        int answer = MessageBox.show(ScreensFramework.stage,
						"¿Desea volver al menú principal?",
						"Information dialog", 
						MessageBox.ICON_INFORMATION| MessageBox.OK | MessageBox.CANCEL);
 
		if (answer == MessageBox.OK) {
                    ScreensFramework.stage.setWidth(921);
        ScreensFramework.stage.setHeight(590);
        myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL); 
		} else if (answer == MessageBox.CANCEL) {
		}
    }

}