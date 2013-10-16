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
public class ScreensFramework extends Application {

    public static final String PANTALLA_PRINCIPAL = "PantallaPrincipal";
    public static final String PANTALLA_PRINCIPAL_FXML = "/fitbox/view/PantallaPrincipal.fxml";
    public static Stage stage;
     public static final String PANTALLA_ACTIVIDADES = "AccederActividades";
    public static final String PANTALLA_ACTIVIDADES_FXML = "/fitbox/view/AccederActividades.fxml";
    public static final String PANTALLA_REALIZARACTIVIDAD = "RealizarActividad";
    public static final String PANTALLA_REALIZARACTIVIDAD_FXML = "/fitbox/view/RealizarActividad.fxml";
    public static final String PANTALLA_PERFIL1_FXML = "/fitbox/view/perfil1.fxml";
    public static final String PANTALLA_PERFIL1 = "Perfil1";
    public static final String PANTALLA_PERFIL2_FXML = "/fitbox/view/perfil2.fxml";
    public static final String PANTALLA_PERFIL2 = "Perfil2";
    

    @Override
    public void start(Stage primeraPantalla) throws Exception {
        //To change body of generated methods, choose Tools | Templates.

        /**
         *
         */
        this.stage = primeraPantalla;
        
        /*ScreensController mainContainer = new ScreensController(stage);
        mainContainer.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL,
                ScreensFramework.PANTALLA_PRINCIPAL_FXML, null);

        mainContainer.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);*/
        
        ScreensController mainContainer = new ScreensController(stage);
        mainContainer.loadScreen(ScreensFramework.PANTALLA_PERFIL1,
                ScreensFramework.PANTALLA_PERFIL1_FXML, null);

        mainContainer.setScreen(ScreensFramework.PANTALLA_PERFIL1);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getStage() {
        return stage;

    }
}
