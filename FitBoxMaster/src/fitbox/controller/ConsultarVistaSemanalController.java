/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import com.sai.javafx.calendar.DatePicker;
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
import java.util.ArrayList;
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
import jfxtras.labs.dialogs.DialogFX;
import jfxtras.labs.dialogs.DialogFX.Type;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

/**
 *
 * @author Elias
 */
public class ConsultarVistaSemanalController implements Initializable, ControlledScreen {


    @FXML
    ListView lunesList, martesList, miercolesList, juevesList, viernesList, sabadoList, domingoList;
    @FXML
    HBox hPanel;
    static FXCalendar fxcalendar;
    LocalDate now;
    private Recurso recurso;
    private Usuario user;

    public ConsultarVistaSemanalController() {
    }
    private static ConsultarVistaSemanalController consultarVistaSemanal;

    public static void cambiarFecha() {
        if (consultarVistaSemanal != null) {
            consultarVistaSemanal.updateVista(fxcalendar.getSelectedYear(), fxcalendar.getSelectedMonth(), fxcalendar.getSelectedDate(), true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
  ScreensFramework.stage.setWidth(921);
        ScreensFramework.stage.setHeight(590);
        this.recurso = (Recurso) rb;
        this.user = (Usuario) recurso.getObject("usuario");
    //    ScreensFramework.getStage2().getScene().getStylesheets().add("/com/sai/javafx/calendar/styles/calendar_styles.css");
        consultarVistaSemanal = this;
        fxcalendar = new FXCalendar();
        hPanel.getChildren().addAll(fxcalendar);
        LocalDate hoy = new LocalDate();
        updateVista(hoy.getYear(), hoy.getMonthOfYear(), hoy.getDayOfMonth(), false);
    }

    public void updateVista(int _año, int _mes, int _dia, boolean fx) {
        System.out.println(_año + " " + _mes + " " + _dia);


        if (fx) {
            now = new LocalDate(_año, _mes + 1, _dia);
        } else {
            now = new LocalDate(_año, _mes, _dia);
        }
        int lunes = now.withDayOfWeek(DateTimeConstants.MONDAY).getDayOfMonth();
        int martes = now.withDayOfWeek(DateTimeConstants.TUESDAY).getDayOfMonth();
        int miercoles = now.withDayOfWeek(DateTimeConstants.WEDNESDAY).getDayOfMonth();
        int jueves = now.withDayOfWeek(DateTimeConstants.THURSDAY).getDayOfMonth();
        int viernes = now.withDayOfWeek(DateTimeConstants.FRIDAY).getDayOfMonth();
        int sabado = now.withDayOfWeek(DateTimeConstants.SATURDAY).getDayOfMonth();
        int domingo = now.withDayOfWeek(DateTimeConstants.SUNDAY).getDayOfMonth();
        System.out.println("Lunes: " + lunes + "  " + "Domingo: " + domingo);
        Dal dal = Dal.getDal();
        List<Calendario> calendarios = dal.find(Calendario.CALENDARIOBYJUGADORID, new Object[]{user.getId()}, Calendario.class);
        System.out.println("Actividades: " + calendarios.size());
        List<Calendario> listLunesStr = new LinkedList<>();
        List<Calendario> listMartesStr = new LinkedList<>();
        List<Calendario> listMiercolesStr = new LinkedList<>();
        List<Calendario> listJuevesStr = new LinkedList<>();
        List<Calendario> listViernesStr = new LinkedList<>();
        List<Calendario> listSabadoStr = new LinkedList<>();
        List<Calendario> listDomingoStr = new LinkedList<>();
        martesList.setItems(null);
        lunesList.setItems(null);
        miercolesList.setItems(null);
        int mes2;
        if (lunes > domingo) {
            mes2 = now.plusMonths(1).getMonthOfYear();
        } else {
            mes2 = now.getMonthOfYear();
        }


        for (Calendario cal : calendarios) {
            if (cal.getFecha().getYear() == now.getYear() && (cal.getFecha().getMonthOfYear() == now.getMonthOfYear() || cal.getFecha().getMonthOfYear() == mes2)) {
                if (cal.getFecha().getDayOfMonth() == lunes) {
                    listLunesStr.add(cal );
                } else if (cal.getFecha().getDayOfMonth() == martes) {
                    listMartesStr.add(cal );
                } else if (cal.getFecha().getDayOfMonth() == miercoles) {
                    listMiercolesStr.add(cal );
                } else if (cal.getFecha().getDayOfMonth() == jueves) {
                    listJuevesStr.add(cal );
                } else if (cal.getFecha().getDayOfMonth() == viernes) {
                    listViernesStr.add(cal );
                } else if (cal.getFecha().getDayOfMonth() == sabado) {
                    listSabadoStr.add(cal );
                } else if (cal.getFecha().getDayOfMonth() == domingo) {
                    listDomingoStr.add(cal );
                }
            }
        }
        ObservableList<Calendario> listLunes = FXCollections.observableArrayList(listLunesStr);
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
                                Actividad ac = (Actividad) Dal.getDal().find(Actividad.ENCONTRAR_ACTIVIDADporID, new Object[]{((Calendario)item).getIdActividad()}, Actividad.class).get(0);
                                if(ac!=null)
                                text = new Text(ac.getNombre());
                                else
                                text = new Text("");
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
        ObservableList<Calendario> listDomingo = FXCollections.observableArrayList(listDomingoStr);
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
                                 Actividad ac = (Actividad) Dal.getDal().find(Actividad.ENCONTRAR_ACTIVIDADporID, new Object[]{((Calendario)item).getIdActividad()}, Actividad.class).get(0);
                                if(ac!=null)
                                text = new Text(ac.getNombre());
                                else
                                text = new Text("");
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
        ObservableList<Calendario> listSabado = FXCollections.observableArrayList(listSabadoStr);
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
                                Actividad ac = (Actividad) Dal.getDal().find(Actividad.ENCONTRAR_ACTIVIDADporID, new Object[]{((Calendario)item).getIdActividad()}, Actividad.class).get(0);
                                if(ac!=null)
                                text = new Text(ac.getNombre());
                                else
                                text = new Text("");
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
        ObservableList<Calendario> listViernes = FXCollections.observableArrayList(listViernesStr);
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
                                Actividad ac = (Actividad) Dal.getDal().find(Actividad.ENCONTRAR_ACTIVIDADporID, new Object[]{((Calendario)item).getIdActividad()}, Actividad.class).get(0);
                                if(ac!=null)
                                text = new Text(ac.getNombre());
                                else
                                text = new Text("");
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
        ObservableList<Calendario> listJueves = FXCollections.observableArrayList(listJuevesStr);
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
                                Actividad ac = (Actividad) Dal.getDal().find(Actividad.ENCONTRAR_ACTIVIDADporID, new Object[]{((Calendario)item).getIdActividad()}, Actividad.class).get(0);
                                if(ac!=null)
                                text = new Text(ac.getNombre());
                                else
                                text = new Text("");
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
        ObservableList<Calendario> listMiercoles = FXCollections.observableArrayList(listMiercolesStr);
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
                                Actividad ac = (Actividad) Dal.getDal().find(Actividad.ENCONTRAR_ACTIVIDADporID, new Object[]{((Calendario)item).getIdActividad()}, Actividad.class).get(0);
                                if(ac!=null)
                                text = new Text(ac.getNombre());
                                else
                                text = new Text("");
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
        ObservableList<Calendario> listMartes = FXCollections.observableArrayList(listMartesStr);
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
                                Actividad ac = (Actividad) Dal.getDal().find(Actividad.ENCONTRAR_ACTIVIDADporID, new Object[]{((Calendario)item).getIdActividad()}, Actividad.class).get(0);
                                if(ac!=null)
                                text = new Text(ac.getNombre());
                                else
                                text = new Text("");
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
    public void siguienteSemana(ActionEvent e) {
        System.out.println("Siguiente");

        now = now.plusWeeks(1);
        updateVista(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), false);
    }

    @FXML
    public void anteriorSemana(ActionEvent e) {
        System.out.println("Anterior");
        now = now.minusWeeks(1);
        updateVista(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), false);
    }

    @FXML
    public void mouselClicked(MouseEvent t) {

        if (t.getClickCount() == 2) {
            System.out.println("Double cliked " + ((ListView) t.getSource()).getItems().get(((ListView) t.getSource()).getSelectionModel().getSelectedIndex()));

            List<String> buttonLabels = new ArrayList<>(2);
            buttonLabels.add("Editar Actividad");
            buttonLabels.add("Realizar Actividad");

            DialogFX dialog = new DialogFX(Type.QUESTION);
            dialog.setTitleText("Elija una opcion");
            //dialog.setMessage("This is an example of an QUESTION dialog box, created using DialogFX. This also demonstrates the automatic wrapping of text in DialogFX. Would you like to continue?");
            dialog.addButtons(buttonLabels, 0, 1);
            int answer = dialog.showDialog();

            if (answer == 0) {
         myController.loadScreen(ScreensFramework.PANTALLA_EDITARFECHAACTIVIDAD, ScreensFramework.PANTALLA_EDITARFECHAACTIVIDAD_FXML, recurso);
                //myController.setScreen(ScreensFramework.PANTALLA_EDITARFECHAACTIVIDAD);
            }
            if (answer == 1) {
                myController.loadScreen(ScreensFramework.PANTALLA_REALIZARACTIVIDAD, ScreensFramework.PANTALLA_REALIZARACTIVIDAD_FXML, recurso);
                //myController.setScreen(ScreensFramework.PANTALLA_REALIZARACTIVIDAD);
            }
        }
    }

    @FXML
    public void vistaMensual(ActionEvent a) {
        System.out.println("Mensual");
        myController.loadScreen(ScreensFramework.PANTALLA_VISTAMENSUAL, ScreensFramework.PANTALLA_VISTAMENSUAL_FXML, recurso);
        //myController.setScreen(ScreensFramework.PANTALLA_VISTAMENSUAL);
    }
    ScreensController myController = new ScreensController(ScreensFramework.stage);

    
       //Metodos barra de botones
    @FXML
    public void abrirPerfil(MouseEvent event) throws IOException {
       if(!ScreensFramework.cargarPantalla(ScreensFramework.PANTALLA_EDITARPERFIL))
        myController.loadScreen(ScreensFramework.PANTALLA_EDITARPERFIL, ScreensFramework.PANTALLA_EDITARPERFIL_FXML, recurso);
    }

    @FXML
    public void abrirActividades(MouseEvent event) throws IOException {
       if(!ScreensFramework.cargarPantalla(ScreensFramework.PANTALLA_ACTIVIDADES))
        myController.loadScreen(ScreensFramework.PANTALLA_ACTIVIDADES, ScreensFramework.PANTALLA_ACTIVIDADES_FXML, recurso);

    }

    @FXML
    public void abrirCalendario(MouseEvent event) throws IOException {
       if(!ScreensFramework.cargarPantalla(ScreensFramework.PANTALLA_VISTAMENSUAL))
        myController.loadScreen(ScreensFramework.PANTALLA_VISTAMENSUAL, ScreensFramework.PANTALLA_VISTAMENSUAL_FXML, recurso);


    }

    @FXML
    public void abrirVideos(MouseEvent event) throws IOException {
       if(!ScreensFramework.cargarPantalla(ScreensFramework.PANTALLA_SEGUIMIENTO))
        myController.loadScreen(ScreensFramework.PANTALLA_SEGUIMIENTO, ScreensFramework.PANTALLA_SEGUIMIENTO_FXML, recurso);

    }

    @FXML
    public void Actualizar(MouseEvent event) throws IOException {
    }
    
     @FXML
    public void abrirEventos(MouseEvent event) throws IOException {
       if(!ScreensFramework.cargarPantalla(ScreensFramework.PANTALLA_EVENTO))
         myController.loadScreen(ScreensFramework.PANTALLA_EVENTO, ScreensFramework.PANTALLA_EVENTO_FXML, recurso);
    }

    @FXML //es cerrarSesion
    public void abrirAjustes(MouseEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_LOGIN, ScreensFramework.PANTALLA_LOGIN_FXML, recurso);
    }
    @FXML
    public void abrirEventosACT(ActionEvent event) throws IOException {
        abrirEventos(null);   
    }
    @FXML 
    public void abrirAjustesACT(ActionEvent event) throws IOException {
        abrirAjustes(null); 
      }
    @FXML
    public void ActualizarACT(ActionEvent event) throws IOException {
        Actualizar(null);
      }

    @FXML
    private void home() {
       if(!ScreensFramework.cargarPantalla(ScreensFramework.PANTALLA_PRINCIPAL))
        myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
    }
    
    //FIN METODOS BARRA BOTONES
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
}
