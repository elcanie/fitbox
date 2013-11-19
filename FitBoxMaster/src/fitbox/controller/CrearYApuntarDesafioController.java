/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import com.sai.javafx.calendar.FXCalendar;
import static fitbox.controller.ConsultarVistaSemanalController.fxcalendar;
import fitbox.controller.dao.Dal;
import fitbox.model.Actividad;
import fitbox.model.Amigo;
import fitbox.model.Desafio;
import fitbox.model.Jugador;
import fitbox.model.Usuario;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import jfx.messagebox.MessageBox;
import org.joda.time.LocalDate;

/**
 * FXML Controller class
 *
 * @author RUBEN
 */
public class CrearYApuntarDesafioController implements Initializable, ControlledScreen {

    private ScreensController myController;
    @FXML
    private TextField textNombre, fechaIni;
    @FXML
    private ComboBox comboActividad;
    @FXML
    private ListView listaRival, listaDesafios;
    @FXML
    private Button btnDesafiar;
    @FXML
    private HBox hBoxFin;
    private Recurso recurso;
    private FXCalendar fxcalendar2;
    ObservableList<String> dataDesafios;
    LocalDate fecha;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void atras(ActionEvent event) {

        myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);

    }

    @FXML
    void aceptarDesafio(MouseEvent event) {


        String item = (String) listaDesafios.getSelectionModel().getSelectedItem();
        int index = listaDesafios.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            int answer = MessageBox.show(ScreensFramework.stage,
                    "¿Desea apuntarse a este desafio?",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
            if (answer == MessageBox.OK) {
                String arrayItem[] = item.split("-");
                Dal dal = Dal.getDal();
                List<Desafio> lista = dal.find(Desafio.desafioPorIdDesafio, new Object[]{arrayItem[0]}, Desafio.class);
                Desafio desafio = lista.get(0);
                desafio.setEstado(1);
                dal.updateRuben(desafio);
                cargarListaDesafios();
                MessageBox.show(ScreensFramework.stage,
                    " ¡Ha aceptado el desafio !",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);
                
            }
        } else {
            MessageBox.show(ScreensFramework.stage,
                    "Señale un desafio",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);
        }


    }

    @FXML
    public void crearDesafio(ActionEvent event) {

        Dal dal = Dal.getDal();
        String nombreEvento = textNombre.getText();
        int indice = comboActividad.getSelectionModel().getSelectedIndex();
        String actividadNombre = (String) comboActividad.getItems().get(indice);
        List<Actividad> lista = dal.find(Actividad.ENCONTRAR_ACTIVIDADporNOMBRE, new Object[]{actividadNombre}, Actividad.class);
        Actividad actividad = lista.get(0);
        int indice2 = listaRival.getSelectionModel().getSelectedIndex();
        if (indice2 != -1 && !(nombreEvento.equals("")) && indice != -1) {
            String rival = (String) listaRival.getItems().get(indice2);
            int idRival = Integer.parseInt(rival.split("-")[0].trim());
            List<Jugador> listaJugador = dal.find(Jugador.JUGADORBYUSUARIO, new Object[]{idRival}, Jugador.class);
            Jugador jugador = listaJugador.get(0);
            String fechaFormat[] = fechaIni.getText().split("/");
            int añoInicio = Integer.parseInt(fechaFormat[2]);
            int añoFin = fxcalendar2.getSelectedYear();
            int mesInicio = Integer.parseInt(fechaFormat[1]);
            int mesFin = fxcalendar2.getSelectedMonth() + 1;
            int diaInicio = Integer.parseInt(fechaFormat[0]);
            int diaFin = fxcalendar2.getSelectedDate();
            String fechaInicio = diaInicio + "/" + mesInicio + "/" + añoInicio;
            boolean error = false;
            String fechaFin = diaFin + "/" + mesFin + "/" + añoFin;
            if (añoInicio > añoFin) {
                error = true;
            } else if (añoInicio <= añoFin && mesInicio > mesFin) {
                error = true;
            } else if (añoInicio <= añoFin & mesInicio <= mesFin && diaInicio > diaFin) {
                error = true;
            }
            //buscar el id 

            if (error) {
                MessageBox.show(ScreensFramework.stage,
                        "Fecha inicio superior a fecha fin",
                        "Information dialog",
                        MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL);
            } else {
                Desafio d = new Desafio(3, nombreEvento, fechaInicio, fechaFin, 0, (Jugador) recurso.getObject("usuario"), jugador, actividad);
                dal.insert(d);
            }
        } else {
            MessageBox.show(ScreensFramework.stage,
                    "Hay campos sin rellenar",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ScreensFramework.stage.setWidth(600);
        ScreensFramework.stage.setHeight(420);
        ScreensFramework.getStage2().getScene().getStylesheets().add("/com/sai/javafx/calendar/styles/calendar_styles.css");
        fecha = new LocalDate();
        fxcalendar2 = new FXCalendar();
        fechaIni.setText(fecha.getDayOfMonth() + "/" + fecha.getMonthOfYear() + "/" + fecha.getYear());
        hBoxFin.getChildren().addAll(fxcalendar2);
        recurso = (Recurso) rb;
        dataDesafios = FXCollections.observableArrayList();
        inicializarActividades();
        cargarAmigos();
        cargarListaDesafios();




    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;//To change body of generated methods, choose Tools | Templates.
    }

    private void inicializarActividades() {
        ObservableList<String> data = FXCollections.observableArrayList();
        Dal dal = Dal.getDal();
        Collection<Actividad> actividades = dal.find(Actividad.TODOS_ACTIVIDADES, new Object[]{}, Actividad.class);
        Iterator<Actividad> it = actividades.iterator();
        Actividad actividad = null;
        while (it.hasNext()) {
            actividad = it.next();
            data.add(actividad.getNombre());

        }
        comboActividad.setItems(data); //To change body of generated methods, choose Tools | Templates.
    }

    private void cargarAmigos() {
        Dal dal = Dal.getDal();
        ObservableList<String> data = FXCollections.observableArrayList();
        Usuario usuario = (Usuario) recurso.getObject("usuario");
        List<Amigo> amigos = dal.find(Amigo.JugadorID, new Object[]{(usuario.getId())}, Amigo.class);
        for (Amigo amigo : amigos) {
            List<Jugador> jugadoresAmigo = dal.find(Jugador.JUGADORBYUSUARIO, new Object[]{amigo.getIdAmigo()}, Jugador.class);
            Jugador jAmigo = jugadoresAmigo.get(0);
            data.add(jAmigo.getId() + " - " + jAmigo.getApellidos());

        }
        //cargarAmigos y añadirlos al data y asignarlo a la lista luego


        listaRival.setItems(data); //To change body of generated methods, choose Tools | Templates.

    }

    private void cargarListaDesafios() {
        dataDesafios=null;
        dataDesafios = FXCollections.observableArrayList();
        Dal dal = Dal.getDal();
        Usuario usuario = (Usuario) recurso.getObject("usuario");
        int mesActual = fecha.getMonthOfYear();
        int diaActual = fecha.getDayOfMonth();
        int añoActual = fecha.getYear();
        boolean error = false;
        //To change body of generated methods, choose Tools | Templates.
        List<Desafio> desafios = dal.find(Desafio.desafioPorId, new Object[]{usuario.getId()}, Desafio.class);
        for (Desafio desafio : desafios) {
            if (desafio.getEstado() == 0) {
                String fechaDesafio[] = desafio.getFechaFin().split("/");
                int año = Integer.parseInt(fechaDesafio[2]);
                int mes = Integer.parseInt(fechaDesafio[1]);
                int dia = Integer.parseInt(fechaDesafio[0]);

                if (añoActual > año) {
                    error = true;
                } else if (añoActual <= año && mesActual > mes) {
                    error = true;
                } else if (añoActual <= año & mesActual <= mes && diaActual > dia) {
                    error = true;
                }
                if (!error) {
                    dataDesafios.add(desafio.getId() + "-" + desafio.getNombre() + " Te ha desafiado: " + desafio.getRival().getApellidos() + "  Fecha límite: " + desafio.getFechaFin());
                }
            }

        }
        listaDesafios.setItems(dataDesafios);

    }
    //To change body of generated methods, choose Tools | Templates.
}
