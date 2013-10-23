/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import com.sai.javafx.calendar.FXCalendar;
import fitbox.controller.dao.Dal;
import fitbox.model.Actividad;
import fitbox.model.Calendario;
import fitbox.model.Usuario;

import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 *
 * @author Elias
 */
public class ConsultarVistaMensualController implements Initializable, ControlledScreen {

    @FXML
    ListView semana1List, semana2List, semana3List, semana4List, semana5List;
    LocalDate now;
    private Recurso recurso;
    private Usuario user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ScreensFramework.stage.setWidth(492);
        ScreensFramework.stage.setHeight(451);
        this.recurso = (Recurso) rb;
        this.user = (Usuario) recurso.getObject("usuario");
        LocalDate hoy = new LocalDate();
        updateVista(hoy.getYear(), hoy.getMonthOfYear(), hoy.getDayOfMonth());
    }

    public void updateVista(int _año, int _mes, int _dia) {
        now = new LocalDate(_año, _mes, _dia);
        List<Calendario> calendarios = Dal.getDal().find(Calendario.CALENDARIOSPORAÑODIAYJUGADOR, new Object[]{now.getYear() + "/" + now.getMonthOfYear() + "/%", user.getId()}, Calendario.class);
        System.out.println(calendarios.size());
        LinkedList<Calendario> semana1 = new LinkedList<Calendario>();
        LinkedList<Calendario> semana2 = new LinkedList<Calendario>();
        LinkedList<Calendario> semana3 = new LinkedList<Calendario>();
        LinkedList<Calendario> semana4 = new LinkedList<Calendario>();
        LinkedList<Calendario> semana5 = new LinkedList<Calendario>();

        LinkedList<LinkedList<Calendario>> lista = new LinkedList<LinkedList<Calendario>>();
        lista.add(semana1);
        lista.add(semana2);
        lista.add(semana3);
        lista.add(semana4);
        lista.add(semana5);
        DateTime dateTime = new DateTime();

        DateTime lastDate = dateTime.dayOfMonth().withMaximumValue();
        int diasMes = lastDate.dayOfMonth().withMaximumValue().getDayOfMonth();
        System.out.println(diasMes);
        for (int i = 0; i < lista.size(); i++) {
            for (int y = 1; y <= 7; y++) {
                boolean flag = true;
                if (i * 7 + y <= diasMes) {
                    for (Calendario cal : calendarios) {

                        if (flag && cal.getFecha().getDayOfMonth() == ((i * 7) + y)) {
                            System.out.println(cal.getFecha().getDayOfMonth() + "==" + (i * 7 + y));
                            lista.get(i).add(cal);
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        lista.get(i).add(new Calendario(0, "2013/10/" + (i * 7 + y) + " 00:00:00", 99999, 0, 10));
                    }
                }
            }
        }
        ObservableList<Calendario> semana1O = FXCollections.observableArrayList(semana1);
        semana1List.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            text = new Text(((Calendario) item).toString());
                            if (((Calendario) item).getIdActividad() >= 99999) {
                                this.setStyle("-fx-background-color: yellow");
                            }
                            text.setWrappingWidth(semana1List.getPrefWidth() / 7 - 8);
                            setGraphic(text);
                        }
                    }
                };

                return cell;
            }
        });
        semana1List.setItems(semana1O);
        ObservableList<Calendario> semana2O = FXCollections.observableArrayList(semana2);
        semana2List.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            text = new Text(item.toString());
                            if (((Calendario) item).getIdActividad() >= 99999) {
                                this.setStyle("-fx-background-color: yellow");
                            }
                            text.setWrappingWidth(semana1List.getPrefWidth() / 7 - 8);
                            setGraphic(text);
                        }
                    }
                };

                return cell;
            }
        });
        semana2List.setItems(semana2O);
        ObservableList<Calendario> semana3O = FXCollections.observableArrayList(semana3);
        semana3List.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            text = new Text(item.toString());
                            if (((Calendario) item).getIdActividad() >= 99999) {
                                this.setStyle("-fx-background-color: yellow");
                            }
                            text.setWrappingWidth(semana1List.getPrefWidth() / 7 - 8);
                            setGraphic(text);
                        }
                    }
                };

                return cell;
            }
        });
        semana3List.setItems(semana3O);
        ObservableList<Calendario> semana4O = FXCollections.observableArrayList(semana4);
        semana4List.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            text = new Text(item.toString());
                            if (((Calendario) item).getIdActividad() >= 99999) {
                                this.setStyle("-fx-background-color: yellow");
                            }
                            text.setWrappingWidth(semana1List.getPrefWidth() / 7 - 8);
                            setGraphic(text);
                        }
                    }
                };

                return cell;
            }
        });
        semana4List.setItems(semana4O);
        ObservableList<Calendario> semana5O = FXCollections.observableArrayList(semana5);
        semana5List.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            text = new Text(item.toString());
                            if (((Calendario) item).getIdActividad() >= 99999) {
                                this.setStyle("-fx-background-color: yellow");
                            }
                            text.setWrappingWidth(semana1List.getPrefWidth() / 7 - 8);
                            setGraphic(text);
                        }
                    }
                };

                return cell;
            }
        });
        semana5List.setItems(semana5O);
    }

    @FXML
    public void mouselClicked(MouseEvent t) {

        if (t.getClickCount() == 2) {
            System.out.println("Double cliked " + ((ListView) t.getSource()).getItems().get(((ListView) t.getSource()).getSelectionModel().getSelectedIndex()));
            recurso.putObject("calendario", (Calendario) ((ListView) t.getSource()).getItems().get(((ListView) t.getSource()).getSelectionModel().getSelectedIndex()));
            myController.loadScreen(ScreensFramework.PANTALLA_VISTADIARIA, ScreensFramework.PANTALLA_VISTADIARIA_FXML, recurso);
            myController.setScreen(ScreensFramework.PANTALLA_VISTADIARIA);
        }
    }

    @FXML
    public void anteriorMes(ActionEvent a) {
        System.out.println("Anterior");
        now = now.minusMonths(1);
        updateVista(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth());
    }

    @FXML
    public void siguienteMes(ActionEvent a) {
        System.out.println("Siguiente");
        now = now.plusMonths(1);
        updateVista(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth());
    }

    @FXML
    public void vistaSemanal(ActionEvent a) {
        System.out.println("Semanal");
        myController.loadScreen(ScreensFramework.PANTALLA_VISTASEMANAL, ScreensFramework.PANTALLA_VISTASEMANAL_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_VISTASEMANAL);
    }

    @FXML
    public void menuPrincipal(ActionEvent a) {
        System.out.println("Principal");
        myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);
    }
    ScreensController myController;

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.

    }
}
