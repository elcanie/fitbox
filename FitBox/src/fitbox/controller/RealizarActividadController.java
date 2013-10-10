/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.AdvancedMedia.MediaControl;
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

    @FXML
    private Label label;
    private ScreensController myController;
    private ScreensFramework main;
    @FXML
    private AnchorPane zonavideo;
    MediaControl reproductor;
    private static final String MEDIA_URL = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
    private MediaControl mediaControl;
    private MediaPlayer mediaPlayer;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    public void setMain(ScreensFramework main) {
        this.main = main;
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mediaPlayer = new MediaPlayer(new Media(MEDIA_URL));
        mediaPlayer.setAutoPlay(true);
        mediaControl = new MediaControl(mediaPlayer);
        mediaControl.setMinSize(480, 280);
        mediaControl.setPrefSize(480, 280);
        mediaControl.setMaxSize(480, 280);
        zonavideo.getChildren().add(mediaControl);
    }

}