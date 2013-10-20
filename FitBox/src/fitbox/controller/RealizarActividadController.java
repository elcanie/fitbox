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
import fitbox.view.ControlledScreen;
import fitbox.view.ScreensFramework;
import java.net.URL;
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
    
    
    
    private static final String MEDIA_URL = "<iframe width=\"508\" height=\"419\" src=\"http://www.youtube.com/embed/UNvAy1N6jvU?fs=1\" frameborder=\"0\" allowfullscreen></iframe>";
   
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
        if (crono!=null){
            crono.pause();
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
        //WebView videoMuestra = new WebView();
        ScreensFramework.stage.setWidth(791);
        ScreensFramework.stage.setHeight(578);
        WebEngine webEngine= videoMuestra.getEngine();
        webEngine.loadContent(MEDIA_URL);
        videoMuestra.setLayoutX(-5);
        videoMuestra.setLayoutY(-4);
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
        myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, null);
        myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);        
    }
    

}