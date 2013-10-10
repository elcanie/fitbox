/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication12;

import java.util.Calendar;
import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

/**
 *
 * @author Elias
 */
public class JavaFXApplication12 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
       LocalDate now = new LocalDate();
System.out.println(now.withDayOfWeek(DateTimeConstants.MONDAY));
System.out.println(now.withDayOfWeek(DateTimeConstants.SUNDAY));
        
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        
        Scene scene = new Scene(root);
        
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