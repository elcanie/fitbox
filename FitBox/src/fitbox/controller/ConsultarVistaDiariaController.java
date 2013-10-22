/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.controller.dao.Dal;
import fitbox.model.Actividad;
import fitbox.model.Calendario;

import fitbox.model.Usuario;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import jfxtras.labs.dialogs.DialogFX;
import org.joda.time.LocalDate;

/**
 *
 * @author Elias
 */
public class ConsultarVistaDiariaController implements Initializable, ControlledScreen {

    static Scene scene;
    private static ConsultarVistaDiariaController consultarVistaDiaria;
    private Recurso recurso;
    private Usuario user;

    public static void setScene(Scene _scene) {
        scene = _scene;
    }
    @FXML
    private ListView diaListView;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label diaLabel;
    LocalDate now;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.recurso = (Recurso) rb;
        this.user = (Usuario) recurso.getObject("usuario");
        LocalDate hoy = new LocalDate();
        updateVista(hoy.getYear(), hoy.getMonthOfYear(), 16);
    }

    public void updateVista(int _año, int _mes, int _dia) {
        System.out.println(_año + " " + _mes + " " + _dia);
        diaLabel.setText("Dia: " + _dia);
        now = new LocalDate(_año, _mes, _dia);

        Dal dal = Dal.getDal();
        List<Calendario> calendarios = dal.find(Calendario.CALENDARIOBYJUGADORID, new Object[]{user.getId()}, Calendario.class);

        System.out.println("Actividades: " + calendarios.size());
        List<Calendario> listDiaStr = new LinkedList<>();


        diaListView.setItems(null);


        for (Calendario cal : calendarios) {
            if (cal.getFecha().getDayOfMonth() == now.getDayOfMonth() && cal.getFecha().getYear() == now.getYear() && (cal.getFecha().getMonthOfYear() == now.getMonthOfYear())) {
                listDiaStr.add(cal);
            }
        }


        ObservableList<Calendario> listDia = FXCollections.observableArrayList(listDiaStr);
        diaListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        if (item != null) {
                            super.updateItem(item, empty);
                            if (!isEmpty()) {

                                text = new Text(((Calendario) item).toString2());

                                text.setWrappingWidth(diaListView.getPrefWidth());
                                setGraphic(text);
                            }
                        }
                    }
                };

                return cell;
            }
        });
        diaListView.setItems(listDia);

    }
    ScreensController myController;

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    public void vistaSemanal(ActionEvent a) {
        System.out.println("Semanal");
        myController.loadScreen(ScreensFramework.PANTALLA_VISTASEMANAL, ScreensFramework.PANTALLA_VISTASEMANAL_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_VISTASEMANAL);
    }

    @FXML
    public void vistaMensual(ActionEvent a) {
        System.out.println("Mensual");
        myController.loadScreen(ScreensFramework.PANTALLA_VISTAMENSUAL, ScreensFramework.PANTALLA_VISTAMENSUAL_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_VISTAMENSUAL);
    }

    @FXML
    public void anteriorDia(ActionEvent a) {
        System.out.println("Anterior");
        now = now.minusDays(1);
        updateVista(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth());
    }

    @FXML
    public void siguienteDia(ActionEvent a) {
        System.out.println("Siguiente");
        now = now.plusDays(1);
        updateVista(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth());
    }

    @FXML
    public void mouselClicked(MouseEvent t) {

        if (t.getClickCount() == 2) {
            System.out.println("Double cliked " + ((ListView) t.getSource()).getItems().get(((ListView) t.getSource()).getSelectionModel().getSelectedIndex()));
            Calendario cal = (Calendario) ((ListView) t.getSource()).getItems().get(((ListView) t.getSource()).getSelectionModel().getSelectedIndex());
            List<Actividad> acts = Dal.getDal().find(Actividad.ENCONTRAR_ACTIVIDADporID, new Object[]{cal.getIdActividad()}, Actividad.class);
            Actividad act = (acts.size() > 0) ? acts.get(0) : null;
            Recurso resource = new Recurso();
            resource.putObject("Actividad", act);
            List<String> buttonLabels = new ArrayList<>(2);
            buttonLabels.add("Editar Actividad");
            buttonLabels.add("Realizar Actividad");

            DialogFX dialog = new DialogFX(DialogFX.Type.QUESTION);
            dialog.setTitleText("Elija una opcion");
            //dialog.setMessage("This is an example of an QUESTION dialog box, created using DialogFX. This also demonstrates the automatic wrapping of text in DialogFX. Would you like to continue?");
            dialog.addButtons(buttonLabels, 0, 1);
            int answer = dialog.showDialog();

            if (answer == 0) {
                myController.loadScreen(ScreensFramework.PANTALLA_VISTAMENSUAL, ScreensFramework.PANTALLA_VISTAMENSUAL_FXML, recurso);
                myController.setScreen(ScreensFramework.PANTALLA_VISTAMENSUAL);
            }
            if (answer == 1) {
                myController.loadScreen(ScreensFramework.PANTALLA_REALIZARACTIVIDAD, ScreensFramework.PANTALLA_REALIZARACTIVIDAD_FXML, recurso);
                myController.setScreen(ScreensFramework.PANTALLA_REALIZARACTIVIDAD);
            }
        }
    }
}
