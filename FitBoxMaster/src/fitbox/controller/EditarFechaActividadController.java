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
public class EditarFechaActividadController implements Initializable, ControlledScreen {

    @FXML
    TextArea tDescripcion;
    @FXML
    Label nombreActividad;
    @FXML
    TextField hora, minutos;
    @FXML
    HBox hPanel;
    static FXCalendar fxcalendar;
    private Recurso recurso;
    private ScreensController myController = new ScreensController(ScreensFramework.stage);
    private Actividad actividad;

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ScreensFramework.stage.setWidth(320);
        ScreensFramework.stage.setHeight(475);
        recurso = (Recurso) rb;
        actividad = (Actividad) recurso.getObject("actividad");
        fxcalendar = new FXCalendar();
        ScreensFramework.getStage2().getScene().getStylesheets().add("/com/sai/javafx/calendar/styles/calendar_styles.css");
        hPanel.getChildren().addAll(fxcalendar);
        Calendario cal = ((Calendario) recurso.getObject("calendario"));
        FXCalendar.dateTxtField.setText(cal.getFecha().getYear() + "-" + cal.getFecha().getMonthOfYear() + "-" + cal.getFecha().getDayOfMonth());
        hora.setText(cal.getFecha().getHourOfDay() + "");
        minutos.setText(cal.getFecha().getMinuteOfHour() + "");
        nombreActividad.setText(actividad.getNombre().toUpperCase());
        tDescripcion.setText(actividad.getDescripcion());
    }

    @FXML
    public void editar(ActionEvent event) {
        int horaInt = 0, minutosInt = 0;
        try {
            horaInt = Integer.parseInt(hora.getText());
            minutosInt = Integer.parseInt(minutos.getText());
            System.out.println("hora: " + horaInt);
            System.out.println("min: " + minutosInt);
            if (horaInt < 24 && horaInt >= 0 && minutosInt <= 59 && minutosInt >= 0) {
                Calendario cal = ((Calendario) recurso.getObject("calendario"));
                
                cal.getValores()[1]=FXCalendar.dateTxtField.getText()+" "+horaInt+":"+minutosInt+":00";
                Dal.getDal().update(cal);
                recurso.putObject("calendario", cal);
                myController.loadScreen(ScreensFramework.PANTALLA_VISTAMENSUAL, ScreensFramework.PANTALLA_VISTAMENSUAL_FXML, recurso);
                myController.setScreen(ScreensFramework.PANTALLA_VISTAMENSUAL);
            }
        } catch (Exception e) {
            System.out.println("Solo numeros");
        }

        System.out.println("Ponga una hora y minutos correctos");
    }

    @FXML
    public void cancelar(ActionEvent event) {
        myController.loadScreen(ScreensFramework.PANTALLA_VISTADIARIA, ScreensFramework.PANTALLA_VISTADIARIA_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_VISTADIARIA);
    }
}
