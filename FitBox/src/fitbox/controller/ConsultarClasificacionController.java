/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fitbox.controller;

import fitbox.view.ControlledScreen;
import fitbox.view.ScreensFramework;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author Jose
 */
public class ConsultarClasificacionController implements Initializable, ControlledScreen{
    private ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }

        public void initialize(URL url, ResourceBundle rb) {
        ScreensFramework.stage.setWidth(380);
        ScreensFramework.stage.setHeight(308);
        //Seleccionar tabla y realizar consulta.
        

        
    }
        public void amigos(){
            //Realizar consulta SQL from=amigos
        }
        
        public void general(){
            //Realizar consulta SQL from=*
        }
        
}
