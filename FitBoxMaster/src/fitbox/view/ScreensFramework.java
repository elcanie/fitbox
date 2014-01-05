/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.view;

import fitbox.controller.ScreensController;
import java.util.HashMap;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 *
 *
 *
 *
 * @author RUBEN
 */
public class ScreensFramework extends Application {
    public static float width=0;
    public static float height=0;
    static Group  padre = new Group();
    public static VBox anchorPantalla = new VBox();
    private static boolean bool = true;
    public static final String PANTALLA_PRINCIPAL = "Pantalla principal";
    //public static final String PANTALLA_PRINCIPAL_FXML = "/fitbox/view/PantallaPrincipal.fxml";
    public static final String PANTALLA_PRINCIPAL_FXML = "/fitbox/view/PantallaPrincipal_2.fxml";
    public static final String PANTALLA_VISTAMENSUAL = "Calendario vista mensual";
    public static final String PANTALLA_VISTAMENSUAL_FXML = "/fitbox/view/ConsultarVistaMensual.fxml";
    public static final String PANTALLA_VISTADIARIA = "Calendario vista diaria";
    public static final String PANTALLA_VISTADIARIA_FXML = "/fitbox/view/ConsultarVistaDiaria.fxml";
    public static final String PANTALLA_VISTASEMANAL = "Calendario vista semanal";
    public static final String PANTALLA_VISTASEMANAL_FXML = "/fitbox/view/ConsultarVistaSemanal.fxml";
    public static final String PANTALLA_EDITARFECHAACTIVIDAD = "Editar fecha de actividad";
    public static final String PANTALLA_EDITARFECHAACTIVIDAD_FXML = "/fitbox/view/EditarFechaActividad.fxml";
    public static final String PANTALLA_EDITARPERFIL = "Editar perfil";
    public static final String PANTALLA_EDITARPERFIL_FXML = "/fitbox/view/EditarPerfil.fxml";
    public static Stage stage;
    public static Stage inicialStage;
    public static final String PANTALLA_ACTIVIDADES = "Acceder a Actividades";
    public static final String PANTALLA_ACTIVIDADES_FXML = "/fitbox/view/AccederActividades.fxml";
    public static final String PANTALLA_LOGIN = "Iniciar sesión";
    public static final String PANTALLA_LOGIN_FXML = "/fitbox/view/Login.fxml";
    public static final String PANTALLA_SEGUIMIENTO = "Vídeos";
    public static final String PANTALLA_SEGUIMIENTO_FXML = "/fitbox/view/videos.fxml";
    public static final String PANTALLA_REALIZARACTIVIDAD = "Realizar actividad";
    public static final String PANTALLA_REALIZARACTIVIDAD_FXML = "/fitbox/view/RealizarActividad.fxml";
    public static final String PANTALLA_PERFIL1_FXML = "/fitbox/view/perfil1.fxml";
    public static final String PANTALLA_PERFIL1 = "Registrarse (1)";
    public static final String PANTALLA_PERFIL2_FXML = "/fitbox/view/perfil2.fxml";
    public static final String PANTALLA_PERFIL2 = "Registrarse (2)";
    public static final String PANTALLA_PERFIL3_FXML = "/fitbox/view/perfil3.fxml";
    public static final String PANTALLA_PERFIL3 = "Registrarse (3)";
    public static final String PANTALLA_PERFIL4_FXML = "/fitbox/view/perfil4.fxml";
    public static final String PANTALLA_PERFIL4 = "Registrarse (4)";
    public static final String PANTALLA_PERFIL5_FXML = "/fitbox/view/perfil5.fxml";
    public static final String PANTALLA_PERFIL5 = "Registrarse (5)";
    public static final String PANTALLA_CLASIFICACION = "Clasificación";
    public static final String PANTALLA_CLASIFICACION_FXML = "/fitbox/view/Clasificacion.fxml";
    public static final String PANTALLA_DESAFIO = "Desafío";
    public static final String PANTALLA_DESAFIO_FXML = "/fitbox/view/CrearYApuntarDesafio.fxml";
    public static final String PANTALLA_EVENTO = "Realizar evento";
    public static final String PANTALLA_EVENTO_FXML = "/fitbox/view/RealizarEvento.fxml";
    public static String tituloVentanaNombreUsuario = "";
    public static HashMap<String, Scene> pantallas = new HashMap<>();
    public static HashMap<String, AnchorPane> paneles = new HashMap<>();
    @Override
    public void start(Stage primeraPantalla) throws Exception {
        //To change body of generated methods, choose Tools | Templates.

        /**
         *
         */
        
        //this.stage = primeraPantalla;
        this.inicialStage = primeraPantalla;
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
        mainContainer.loadScreenInicial(ScreensFramework.PANTALLA_LOGIN,
                ScreensFramework.PANTALLA_LOGIN_FXML, null);

        mainContainer.setScreen(ScreensFramework.PANTALLA_LOGIN);


        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        inicialStage.setScene(scene);
        inicialStage.getIcons().add(new Image("/imagenes/fito5.png"));
        stage.getIcons().add(new Image("/imagenes/fito5.png"));
        //ScreensFramework.inicialStage.initStyle(StageStyle.UNDECORATED);
        inicialStage.show();
        inicialStage.toFront();

        redimensionarStage();
        
        
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
    
    public static void ponerLimitesMinimosCero(){
         ScreensFramework.stage.setMinWidth(0);
        ScreensFramework.stage.setMinHeight(0);
      }
      
       public static void ponerLimitesMinimos(){
        stage.setMinWidth(950);
        stage.setMinHeight(580);
      }
       
       public static void añadirPantaña(String name, Scene pantalla){ 
           pantallas.put(name, pantalla);
       }
       
       public static boolean cargarPantalla(String name){ 
           Scene s = pantallas.get(name);
           if(s!=null){
           stage.setScene(s);
           stage.setTitle(name + tituloVentanaNombreUsuario);
           return true;
           }else{
               return false;
           }
//           AnchorPane ap = paneles.get(name);
//           if(ap!=null){    
//           anchorPantalla.getChildren().clear();
//           anchorPantalla.getChildren().add(ap);
//           anchorPantalla.setStyle("-fx-background-color: red");
//           ap.setVisible(true);
//           stage.setTitle(name + tituloVentanaNombreUsuario);
//           return true;
//           }else{
//               return false;
//           }
           
       }
       
       private void redimensionarStage(){
           stage.sceneProperty().addListener(new ChangeListener<Scene>(){

               @Override
               public void changed(ObservableValue<? extends Scene> ov, Scene t, Scene t1) {
                   if(bool){ stage.setWidth(stage.getWidth()+1);stage.setHeight(stage.getHeight()+1);}
           else {stage.setWidth(stage.getWidth()-1);stage.setHeight(stage.getHeight()-1);}
           bool = !bool;
               }
           
       });
           
           
           
       }
}