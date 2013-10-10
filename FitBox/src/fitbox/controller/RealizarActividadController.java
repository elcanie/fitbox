/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class RealizarActividadController implements Initializable, ControlledScreen {

    private ScreensController myController;
    @FXML
    private AnchorPane zonavideo;
    MediaControl reproductor;
    private static final String MEDIA_URL = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
    private MediaControl mediaControl;
    private MediaPlayer mediaPlayer;

 



    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ScreensFramework.stage.setWidth(784);
        ScreensFramework.stage.setHeight(549);

        mediaPlayer = new MediaPlayer(new Media(MEDIA_URL));
        mediaPlayer.setAutoPlay(true);
        mediaControl = new MediaControl(mediaPlayer);
        mediaControl.setMinSize(514, 455);
        mediaControl.setPrefSize(514, 455);
        mediaControl.setMaxSize(514, 455);
        zonavideo.getChildren().add(mediaControl);
        
    }

}