/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import com.sai.javafx.calendar.DatePicker;
import com.sai.javafx.calendar.FXCalendar;
import fitbox.controller.dao.Dal;
import fitbox.model.Calendario;
import fitbox.model.Usuario;
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
import javafx.event.EventHandler;
import javafx.event.EventType;
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
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

/**
 *
 * @author Elias
 */
public class ConsultarVistaSemanalController implements Initializable {

    @FXML
    ListView lunesList, martesList, miercolesList, juevesList, viernesList, sabadoList, domingoList;
    
    @FXML
    HBox hPanel;
    static FXCalendar fxcalendar;

    public ConsultarVistaSemanalController() {
    }
    
    private static ConsultarVistaSemanalController consultarVistaSemanal;
    
    public static void cambiarFecha(){
        if(consultarVistaSemanal!=null)
       consultarVistaSemanal.updateVista(fxcalendar.getSelectedYear(), fxcalendar.getSelectedMonth(), fxcalendar.getSelectedDate(),true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        consultarVistaSemanal = this;
                fxcalendar = new FXCalendar();
		hPanel.getChildren().addAll(fxcalendar);
        LocalDate hoy = new LocalDate();
        updateVista(hoy.getYear(),hoy.getMonthOfYear(),hoy.getDayOfMonth(),false);
    }

    public void updateVista(int _año, int _mes, int _dia,boolean fx) {
        System.out.println(_año+" "+_mes+" "+_dia);
        LocalDate now;
        if(fx)
        now = new LocalDate(_año, _mes+1, _dia);
        else now = new LocalDate(_año, _mes, _dia);
        int lunes = now.withDayOfWeek(DateTimeConstants.MONDAY).getDayOfMonth();
        int martes = now.withDayOfWeek(DateTimeConstants.TUESDAY).getDayOfMonth();
        int miercoles = now.withDayOfWeek(DateTimeConstants.WEDNESDAY).getDayOfMonth();
        int jueves = now.withDayOfWeek(DateTimeConstants.THURSDAY).getDayOfMonth();
        int viernes = now.withDayOfWeek(DateTimeConstants.FRIDAY).getDayOfMonth();
        int sabado = now.withDayOfWeek(DateTimeConstants.SATURDAY).getDayOfMonth();
        int domingo = now.withDayOfWeek(DateTimeConstants.SUNDAY).getDayOfMonth();
        System.out.println("Lunes: "+lunes+"  "+"Domingo: "+domingo);
        Dal dal = Dal.getDal();
        List<Calendario> calendarios = dal.find(Calendario.CALENDARIOBYJUGADORID, new Object[]{10}, Calendario.class);
        System.out.println("Actividades: " + calendarios.size());
        List<String> listLunesStr = new LinkedList<>();
        List<String> listMartesStr = new LinkedList<>();
        List<String> listMiercolesStr = new LinkedList<>();
        List<String> listJuevesStr = new LinkedList<>();
        List<String> listViernesStr = new LinkedList<>();
        List<String> listSabadoStr = new LinkedList<>();
        List<String> listDomingoStr = new LinkedList<>();
        martesList.setItems(null);
        lunesList.setItems(null);
        miercolesList.setItems(null);
        int mes2;
        if(lunes>domingo){ 
        mes2 = now.plusMonths(1).getMonthOfYear();
        }else{
        mes2 = now.getMonthOfYear();
        }

        
        for (Calendario cal : calendarios) {
            if (cal.getFecha().getYear() == now.getYear() && (cal.getFecha().getMonthOfYear()==now.getMonthOfYear() || cal.getFecha().getMonthOfYear()==mes2)) {
                if (cal.getFecha().getDayOfMonth() == lunes) {
                    listLunesStr.add(cal.getIdActividad() + "");
                } else if (cal.getFecha().getDayOfMonth() == martes) {
                    listMartesStr.add(cal.getId() + "");
                } else if (cal.getFecha().getDayOfMonth() == miercoles) {
                    listMiercolesStr.add(cal.getId() + "");
                } else if (cal.getFecha().getDayOfMonth() == jueves) {
                    listJuevesStr.add(cal.getId() + "");
                } else if (cal.getFecha().getDayOfMonth() == viernes) {
                    listViernesStr.add(cal.getId() + "");
                } else if (cal.getFecha().getDayOfMonth() == sabado) {
                    listSabadoStr.add(cal.getId() + "");
                } else if (cal.getFecha().getDayOfMonth() == domingo) {
                    listDomingoStr.add(cal.getId() + "");
                }
            }
        }
        ObservableList<String> listLunes = FXCollections.observableArrayList(listLunesStr);
        lunesList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        if (item != null) {
                            super.updateItem(item, empty);
                            if (!isEmpty()) {
                                text = new Text(item.toString());
                                text.setWrappingWidth(lunesList.getPrefWidth());
                                setGraphic(text);
                            }
                        }
                    }
                };

                return cell;
            }
        });
        lunesList.setItems(listLunes);
        ObservableList<String> listDomingo = FXCollections.observableArrayList(listDomingoStr);
        domingoList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        if (item != null) {
                            super.updateItem(item, empty);
                            if (!isEmpty()) {
                                text = new Text(item.toString());
                                text.setWrappingWidth(domingoList.getPrefWidth());
                                setGraphic(text);
                            }
                        }
                    }
                };

                return cell;
            }
        });
        domingoList.setItems(listDomingo);
        ObservableList<String> listSabado = FXCollections.observableArrayList(listSabadoStr);
        sabadoList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        if (item != null) {
                            super.updateItem(item, empty);
                            if (!isEmpty()) {
                                text = new Text(item.toString());
                                text.setWrappingWidth(sabadoList.getPrefWidth());
                                setGraphic(text);
                            }
                        }
                    }
                };

                return cell;
            }
        });
        sabadoList.setItems(listSabado);
        ObservableList<String> listViernes = FXCollections.observableArrayList(listViernesStr);
        viernesList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        if (item != null) {
                            super.updateItem(item, empty);
                            if (!isEmpty()) {
                                text = new Text(item.toString());
                                text.setWrappingWidth(viernesList.getPrefWidth());
                                setGraphic(text);
                            }
                        }
                    }
                };

                return cell;
            }
        });
        viernesList.setItems(listViernes);
        ObservableList<String> listJueves = FXCollections.observableArrayList(listJuevesStr);
        juevesList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        if (item != null) {
                            super.updateItem(item, empty);
                            if (!isEmpty()) {
                                text = new Text(item.toString());
                                text.setWrappingWidth(juevesList.getPrefWidth());
                                setGraphic(text);
                            }
                        }
                    }
                };

                return cell;
            }
        });
        juevesList.setItems(listJueves);
        ObservableList<String> listMiercoles = FXCollections.observableArrayList(listMiercolesStr);
        miercolesList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        if (item != null) {
                            super.updateItem(item, empty);
                            if (!isEmpty()) {
                                text = new Text(item.toString());
                                text.setWrappingWidth(miercolesList.getPrefWidth());
                                setGraphic(text);
                            }
                        }
                    }
                };

                return cell;
            }
        });
        miercolesList.setItems(listMiercoles);
        ObservableList<String> listMartes = FXCollections.observableArrayList(listMartesStr);
        martesList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        if (item != null) {
                            super.updateItem(item, empty);
                            if (!isEmpty()) {
                                text = new Text(item.toString());
                                text.setWrappingWidth(martesList.getPrefWidth());
                                setGraphic(text);
                            }
                        }
                    }
                };

                return cell;
            }
        });
        martesList.setItems(listMartes);
    }
    
    @FXML
    public void siguienteSemana(ActionEvent e){
        System.out.println("Siguiente");
    }
    
    @FXML
    public void anteriorSemana(ActionEvent e){
        System.out.println("Anterior");
    }
    
    @FXML
    public void cambioMes(InputMethodEvent e){
        System.out.println("otro mes");
    }
    
    @FXML
    public void cambioDia(ActionEvent e){
        System.out.println("otro dia");
    }
}
