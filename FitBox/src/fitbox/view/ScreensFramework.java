/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.view;

import fitbox.controller.ScreensController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * 
 * 
 * 
 * @author RUBEN
 */
public class ScreensFramework extends Application{
    public static final String PANTALLA_PRINCIPAL= "PantallaPrincipal"; 
     public static final String PANTALLA_PRINCIPAL_FXML = "/fitbox/view/PantallaPrincipal.fxml"; 
     public static final String PANTALLA_REALIZARATCIVIDAD="RealizarActividad";
     public static final String PANTALLA_REALIZARACTIVIDAD_FXML = "/fitbox/view/RealizarActividad.fxml"; 

     public Stage stage;
   /*  public static final String PANTALLA_PRUEBA= "Actividad"; 
     public static final String PANTALLA_PRUEBA_FXML = "Actividad.fxml";*/
    @Override
    public void start(Stage primeraPantalla) throws Exception {
         //To change body of generated methods, choose Tools | Templates.
        
     /**
     *
     */
        this.stage=primeraPantalla;
       ScreensController mainContainer = new ScreensController(); 
       mainContainer.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, 
                            ScreensFramework.PANTALLA_PRINCIPAL_FXML,this); 
        /*mainContainer.loadScreen(ScreensFramework.PANTALLA_REALIZARATCIVIDAD, 
                            ScreensFramework.PANTALLA_REALIZARACTIVIDAD_FXML,this); */
      // mainContainer.loadScreen(ScreensFramework.PANTALLA_PRUEBA,ScreensFramework.PANTALLA_PRUEBA_FXML);

       mainContainer.setScreen(ScreensFramework.PANTALLA_PRINCIPAL); 

       Group root = new Group(); 
       root.getChildren().addAll(mainContainer); 
       Scene scene = new Scene(root); 
       stage.setScene(scene); 
       stage.show();
                                          
    }
        public static void main(String[] args) {
        launch(args);
    }
        public Stage getStage(){
            return stage;
            
        }

    
}
