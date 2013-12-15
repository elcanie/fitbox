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
        ScreensFramework.stage.setWidth(921);
        ScreensFramework.stage.setHeight(590);
        this.recurso = (Recurso) rb;
        this.user = (Usuario) recurso.getObject("usuario");
        LocalDate hoy = new LocalDate();
        updateVista(hoy.getYear(), hoy.getMonthOfYear(), hoy.getDayOfMonth());
    }
DateTime dateTime = new DateTime();
    public void updateVista(int _año, int _mes, int _dia) {
        now = new LocalDate(_año, _mes, _dia);
        //System.out.println("Hoy es:"+_año+"-"+_mes+"-"+_dia);
        List<Calendario> calendarios = Dal.getDal().find(Calendario.CALENDARIOSPORAÑODIAYJUGADOR, new Object[]{now.getYear() + "-" + now.getMonthOfYear() + "-%", user.getId()}, Calendario.class);
        List<Calendario> calendarios2 = Dal.getDal().find(Calendario.CALENDARIOSPORAÑODIAYJUGADOR, new Object[]{now.getYear() + "/" + now.getMonthOfYear() + "/%", user.getId()}, Calendario.class);
        calendarios.addAll(calendarios2);
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
        

        DateTime lastDate = dateTime.dayOfMonth().withMaximumValue();
        int diasMes = lastDate.dayOfMonth().withMaximumValue().getDayOfMonth();

        for (int i = 0; i < lista.size(); i++) {
            for (int y = 1; y <= 7; y++) {
                boolean flag = true;
                if (i * 7 + y <= diasMes) {
                    for (Calendario cal : calendarios) {

                        if (flag && cal.getFecha().getDayOfMonth() == ((i * 7) + y)) {
                           // System.out.println(cal.getEvento()+"Evento");
                            lista.get(i).add(new Calendario(0, "2013/"+dateTime.getMonthOfYear()+"/" + (i * 7 + y) + " 00:00:00", 99999,null,0,  user.getId()));
                            flag = false;
                        }
                    }
                    if (flag) {
                        lista.get(i).add(new Calendario(0, "2013/"+dateTime.getMonthOfYear()+"/" + (i * 7 + y) + " 00:00:00", 99990,null,0,  user.getId()));
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
                            if(((Calendario) item).getIdActividad()!=null && ((Calendario) item).getIdActividad()<= 9999){
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
                            if(((Calendario) item).getIdActividad()!=null && ((Calendario) item).getIdActividad()<= 9999){
                                //if (((Calendario) item).getIdActividad()!=null) {
                                    this.setStyle("-fx-background-color: yellow");
                                }else if(((Calendario) item).getEvento()!=null) this.setStyle("-fx-background-color: green");
                                else this.setStyle("-fx-background-color: grey");
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
                             if(((Calendario) item).getIdActividad()!=null && ((Calendario) item).getIdActividad()<= 9999){
                                //if (((Calendario) item).getIdActividad()!=null) {
                                if(!this.getStyle().contains("green") || !this.getStyle().contains("blue"))
                                    this.setStyle("-fx-background-color: yellow");
                                else   this.setStyle("-fx-background-color: blue");
                                }else if(((Calendario) item).getEvento()!=null) this.setStyle("-fx-background-color: green");
                                else this.setStyle("-fx-background-color: grey");
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
                            System.out.println("Estilo->"+this.getStyle());
                            if(((Calendario) item).getIdActividad()!=null && ((Calendario) item).getIdActividad()<= 9999){
                                //if (((Calendario) item).getIdActividad()!=null) {
                                if(!this.getStyle().contains("green") || !this.getStyle().contains("blue"))
                                    this.setStyle("-fx-background-color: yellow");
                                else   this.setStyle("-fx-background-color: blue");
                                }else if(((Calendario) item).getEvento()!=null) this.setStyle("-fx-background-color: green");
                                else this.setStyle("-fx-background-color: grey");
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
            recurso.putObject("calendario", (Calendario) ((ListView) t.getSource()).getItems().get(((ListView) t.getSource()).getSelectionModel().getSelectedIndex()));
            myController.loadScreen(ScreensFramework.PANTALLA_VISTADIARIA, ScreensFramework.PANTALLA_VISTADIARIA_FXML, recurso);
            myController.setScreen(ScreensFramework.PANTALLA_VISTADIARIA);
        }
    }

    @FXML
    public void anteriorMes(ActionEvent a) {
        now = now.minusMonths(1);
        dateTime = dateTime.minusMonths(1);
        updateVista(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth());
    }

    @FXML
    public void siguienteMes(ActionEvent a) {
        now = now.plusMonths(1);
        dateTime = dateTime.plusMonths(1);
        updateVista(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth());
    }

    @FXML
    public void vistaSemanal(ActionEvent a) {
        myController.loadScreen(ScreensFramework.PANTALLA_VISTASEMANAL, ScreensFramework.PANTALLA_VISTASEMANAL_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_VISTASEMANAL);
    }

    @FXML
    public void menuPrincipal(ActionEvent a) {
        myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);
    }
    ScreensController myController;

    
    //Metodos barra de botones
    @FXML
    public void abrirPerfil(MouseEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_EDITARPERFIL, ScreensFramework.PANTALLA_EDITARPERFIL_FXML, recurso);
    }

    @FXML
    public void abrirActividades(MouseEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_ACTIVIDADES, ScreensFramework.PANTALLA_ACTIVIDADES_FXML, recurso);

    }

    @FXML
    public void abrirCalendario(MouseEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_VISTAMENSUAL, ScreensFramework.PANTALLA_VISTAMENSUAL_FXML, recurso);


    }

    @FXML
    public void abrirVideos(MouseEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_SEGUIMIENTO, ScreensFramework.PANTALLA_SEGUIMIENTO_FXML, recurso);

    }

    @FXML
    public void Actualizar(MouseEvent event) throws IOException {
    }
    
     @FXML
    public void abrirEventos(MouseEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_EVENTO, ScreensFramework.PANTALLA_EVENTO_FXML, recurso);
    }

    @FXML //es cerrarSesion
    public void abrirAjustes(MouseEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_LOGIN, ScreensFramework.PANTALLA_LOGIN_FXML, recurso);
    }

    @FXML
    private void home() {
        myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
    }
    
    //FIN METODOS BARRA BOTONES

    
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.

    }
}
