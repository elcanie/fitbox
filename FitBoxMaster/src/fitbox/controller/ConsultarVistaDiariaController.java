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
import java.io.IOException;
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
    private Calendario calendario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ScreensFramework.stage.setWidth(309);
        ScreensFramework.stage.setHeight(447);
        this.recurso = (Recurso) rb;
        this.user = (Usuario) recurso.getObject("usuario");
        this.calendario = (Calendario) recurso.getObject("calendario");
        if(calendario!=null)
        updateVista(calendario.getFecha().getYear(), calendario.getFecha().getMonthOfYear(), calendario.getFecha().getDayOfMonth());
        else{
        LocalDate hoy = new LocalDate();
                updateVista(hoy.getYear(), hoy.getMonthOfYear(), hoy.getDayOfMonth());
        }
    }

    public void updateVista(int _año, int _mes, int _dia) {
        System.out.println(_año + " " + _mes + " " + _dia);
        diaLabel.setText("Dia: " + _dia);
        now = new LocalDate(_año, _mes, _dia);

        Dal dal = Dal.getDal();
        List<Calendario> calendarios = dal.find(Calendario.CALENDARIOBYJUGADORID, new Object[]{user.getId()}, Calendario.class);

        System.out.println("Actividades: " + calendarios.size());
        List<Calendario> listDiaStr = new LinkedList<>();


        diaListView.setItems(FXCollections.observableArrayList(listDiaStr));


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

                                Actividad ac = (Actividad) Dal.getDal().find(Actividad.ENCONTRAR_ACTIVIDADporID, new Object[]{((Calendario) item).getIdActividad()}, Actividad.class).get(0);
                                if (ac != null) {
                                    text = new Text(calendario.getFecha().getHourOfDay()+" "+ac.getNombre());
                                } else {
                                    text = new Text("");
                                }

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

            recurso.putObject("actividad", act);
            List<String> buttonLabels = new ArrayList<>(2);
            buttonLabels.add("Editar Actividad");
            buttonLabels.add("Realizar Actividad");

            DialogFX dialog = new DialogFX(DialogFX.Type.QUESTION);
            dialog.setTitleText("Elija una opcion");
            //dialog.setMessage("This is an example of an QUESTION dialog box, created using DialogFX. This also demonstrates the automatic wrapping of text in DialogFX. Would you like to continue?");
            dialog.addButtons(buttonLabels, 0, 1);
            int answer = dialog.showDialog();

            if (answer == 0) {
                myController.loadScreen(ScreensFramework.PANTALLA_EDITARFECHAACTIVIDAD, ScreensFramework.PANTALLA_EDITARFECHAACTIVIDAD_FXML, recurso);
                myController.setScreen(ScreensFramework.PANTALLA_EDITARFECHAACTIVIDAD);
            }
            if (answer == 1) {
                myController.loadScreen(ScreensFramework.PANTALLA_REALIZARACTIVIDAD, ScreensFramework.PANTALLA_REALIZARACTIVIDAD_FXML, recurso);
                myController.setScreen(ScreensFramework.PANTALLA_REALIZARACTIVIDAD);
            }
        }
    }
    
    //Metodos barra de botones
    
      @FXML
    public void abrirPerfil(MouseEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_EDITARPERFIL, ScreensFramework.PANTALLA_EDITARPERFIL_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_EDITARPERFIL);
    }

    @FXML
    public void abrirActividades(MouseEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_ACTIVIDADES, ScreensFramework.PANTALLA_ACTIVIDADES_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_ACTIVIDADES);

    }

    @FXML
    public void abrirCalendario(MouseEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_VISTAMENSUAL, ScreensFramework.PANTALLA_VISTAMENSUAL_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_VISTAMENSUAL);


    }

    @FXML
    public void abrirVideos(MouseEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_SEGUIMIENTO, ScreensFramework.PANTALLA_SEGUIMIENTO_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_SEGUIMIENTO);


    }

    @FXML
    public void Actualizar(MouseEvent event) throws IOException{
        
    }

    @FXML
    public void abrirEquipo(MouseEvent event) throws IOException {
        // myController.setScreen(ScreensFramework.PANTALLA_EQUIPO);
    }

    @FXML
    public void abrirDesafios(MouseEvent event) throws IOException {
         myController.loadScreen(ScreensFramework.PANTALLA_DESAFIO, ScreensFramework.PANTALLA_DESAFIO_FXML, recurso);
         myController.setScreen(ScreensFramework.PANTALLA_DESAFIO);
    }

    @FXML
    public void abrirClasificacion(ActionEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_CLASIFICACION, ScreensFramework.PANTALLA_CLASIFICACION_FXML, recurso);
         myController.setScreen(ScreensFramework.PANTALLA_CLASIFICACION);
    }

    @FXML
    public void abrirAjustes(MouseEvent event) throws IOException {
        //myController.setScreen(ScreensFramework.PANTALLA_AJUSTES);
    }
   
    @FXML
    private void home(){
        myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);    
    }
    
    
}
