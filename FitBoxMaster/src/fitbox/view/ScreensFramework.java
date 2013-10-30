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
    public static final String PANTALLA_VISTAMENSUAL = "VistaMensual";
    public static final String PANTALLA_VISTAMENSUAL_FXML = "/fitbox/view/ConsultarVistaMensual.fxml";
    public static final String PANTALLA_VISTADIARIA = "VistaDiaria";
    public static final String PANTALLA_VISTADIARIA_FXML = "/fitbox/view/ConsultarVistaDiaria.fxml";
    public static final String PANTALLA_VISTASEMANAL = "VistaSemanal";
    public static final String PANTALLA_VISTASEMANAL_FXML = "/fitbox/view/ConsultarVistaSemanal.fxml";
    public static final String PANTALLA_EDITARFECHAACTIVIDAD = "EditarFechaActividad";
    public static final String PANTALLA_EDITARFECHAACTIVIDAD_FXML = "/fitbox/view/EditarFechaActividad.fxml";
    public static final String PANTALLA_EDITARPERFIL = "EditarPerfil";
    public static final String PANTALLA_EDITARPERFIL_FXML = "/fitbox/view/EditarPerfil.fxml";
    public static Stage stage;
    public static final String PANTALLA_ACTIVIDADES = "AccederActividades";
    public static final String PANTALLA_ACTIVIDADES_FXML = "/fitbox/view/AccederActividades.fxml";
    public static final String PANTALLA_LOGIN = "LOGIN";
    public static final String PANTALLA_LOGIN_FXML = "/fitbox/view/Login.fxml";
    public static final String PANTALLA_SEGUIMIENTO = "SeguimientoVideos";
    public static final String PANTALLA_SEGUIMIENTO_FXML = "/fitbox/view/videos.fxml";
    public static final String PANTALLA_REALIZARACTIVIDAD = "RealizarActividad";
    public static final String PANTALLA_REALIZARACTIVIDAD_FXML = "/fitbox/view/RealizarActividad.fxml";
    public static final String PANTALLA_PERFIL1_FXML = "/fitbox/view/perfil1.fxml";
    public static final String PANTALLA_PERFIL1 = "Perfil1";
    public static final String PANTALLA_PERFIL2_FXML = "/fitbox/view/perfil2.fxml";
    public static final String PANTALLA_PERFIL2 = "Perfil2";
    public static final String PANTALLA_PERFIL3_FXML = "/fitbox/view/perfil3.fxml";
    public static final String PANTALLA_PERFIL3 = "Perfil3";
    public static final String PANTALLA_PERFIL4_FXML = "/fitbox/view/perfil4.fxml";
    public static final String PANTALLA_PERFIL4 = "Perfil4";
    public static final String PANTALLA_PERFIL5_FXML = "/fitbox/view/perfil5.fxml";
    public static final String PANTALLA_PERFIL5 = "Perfil5";
    public static final String PANTALLA_CLASIFICACION = "Clasificacion";
    public static final String PANTALLA_CLASIFICACION_FXML = "/fitbox/view/Clasificacion.fxml";

    @Override
    public void start(Stage primeraPantalla) throws Exception {
        //To change body of generated methods, choose Tools | Templates.

        /**
         *
         */
        this.stage = primeraPantalla;
        /*
         ScreensController mainContainer = new ScreensController(stage);
         mainContainer.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL,
         ScreensFramework.PANTALLA_PRINCIPAL_FXML, null);

         mainContainer.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);

         /*ScreensController mainContainer = new ScreensController(stage);
         mainContainer.loadScreen(ScreensFramework.PANTALLA_PERFIL1,
         ScreensFramework.PANTALLA_PERFIL1_FXML, null);

         */
        ScreensController mainContainer = new ScreensController(stage);
        mainContainer.loadScreen(ScreensFramework.PANTALLA_LOGIN,
                ScreensFramework.PANTALLA_LOGIN_FXML, null);

        mainContainer.setScreen(ScreensFramework.PANTALLA_LOGIN);


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

    public static Stage getStage2() {
        return stage;
    }
}