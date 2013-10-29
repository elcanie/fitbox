/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import com.sai.javafx.calendar.FXCalendar;
import static fitbox.controller.ConsultarVistaSemanalController.fxcalendar;
import fitbox.controller.dao.Dal;
import fitbox.model.Actividad;
import fitbox.model.Calendario;
import fitbox.model.Jugador;
import fitbox.model.Usuario;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.joda.time.DateTime;

/**
 * FXML Controller class
 *
 * @author Elias
 */
public class EditarPerfil implements Initializable, ControlledScreen {

    @FXML
    TextField nombreText, apellidosText, alturaText, pesoText,
            correoText, correo2Text, contraseñaText, contraseña2Text;
    @FXML
    HBox hPanel;
    static FXCalendar fxcalendar;
    private Recurso recurso;
    private ScreensController myController;
    private Usuario usuario;
    private Jugador jugador;

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ScreensFramework.stage.setWidth(428);
        ScreensFramework.stage.setHeight(513);
        recurso = (Recurso) rb;
        usuario = (Usuario) recurso.getObject("usuario");
        jugador = (Jugador) Dal.getDal().find(Jugador.JUGADORBYUSUARIO, new Object[]{usuario.getId()}, Jugador.class).get(0);
        fxcalendar = new FXCalendar();
        ScreensFramework.getStage2().getScene().getStylesheets().add("/com/sai/javafx/calendar/styles/calendar_styles.css");
        hPanel.getChildren().addAll(fxcalendar);
//        FXCalendar.dateTxtField.setText(jugador.getFecha().getYear() + "/" + cal.getFecha().getMonthOfYear() + "/" + cal.getFecha().getDayOfMonth());
    }

    @FXML
    public void editar(ActionEvent event) {
        if (comprobarCampos()) {
            Dal.getDal().insert(usuario);
            Dal.getDal().insertConId(jugador);
            myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
            myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);
        }
    }

    @FXML
    public void cancelar(ActionEvent event) {
        myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);
    }

    private boolean comprobarCampos() {
        if(nombreText.getText().trim().equals("")){
            nombreText.setStyle("-fx-background-color: red");
            return false;
        }else{
        
        }
        return true;
    }
}
