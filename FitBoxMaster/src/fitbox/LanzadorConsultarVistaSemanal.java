/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox;

import fitbox.controller.ConsultarVistaDiariaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Elias
 */
public class LanzadorConsultarVistaSemanal extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fitbox/view/ConsultarVistaSemanal.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/com/sai/javafx/calendar/styles/calendar_styles.css");

        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}