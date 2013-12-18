
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
import fitbox.model.BaseDeDatos;
import fitbox.model.Desafio;
import fitbox.model.Jugador;
import fitbox.model.Ranking;
import fitbox.model.Usuario;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private ScreensController myController = new ScreensController(ScreensFramework.stage);
    @FXML
    private TextField textNombre, fechaIni;
    @FXML
    private ComboBox comboActividad;
    @FXML
    private ListView listaRival, listaDesafios;
    @FXML
    private Button btnDesafiar,goButton;
    @FXML
    private HBox hBoxFin;
    private Recurso recurso;
    private FXCalendar fxcalendar2;
    ObservableList<String> dataDesafios;
    LocalDate fecha;
boolean segundaVez=false;
    /**
     * Initializes the controller class.
     */
    @FXML
    public void atras(ActionEvent event) {

        myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
        //myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);

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
               
                List<Desafio> lista = Dal.getDal().find(Desafio.desafioPorIdDesafio, new Object[]{arrayItem[0]}, Desafio.class);
                Desafio desafio = lista.get(0);
                desafio.setEstado(1);
               Dal.getDal().updateRuben(desafio);
                //cargarListaDesafios();
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
        int indice2 = listaRival.getSelectionModel().getSelectedIndex();
        if (indice2 != -1 && !(nombreEvento.equals("")) && indice != -1) {
            String actividadNombre = (String) comboActividad.getItems().get(indice);
            List<Actividad> lista = dal.find(Actividad.ENCONTRAR_ACTIVIDADporNOMBRE, new Object[]{actividadNombre}, Actividad.class);
            Actividad actividad = lista.get(0);
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
                Jugador j = (Jugador) Dal.getDal().find(Jugador.JUGADORBYUSUARIO, new Object[]{((Usuario)recurso.getObject("usuario")).getId()},Jugador.class).get(0);
                System.out.println(j.getId()+"---"+jugador.getId());
                Desafio d = new Desafio(3, nombreEvento, fechaInicio, fechaFin, 0, ((Usuario)recurso.getObject("usuario")).getId() , jugador.getId(), actividad.getId(),0,0);
                dal.insert(d);
                MessageBox.show(ScreensFramework.stage,
                    "Ha insertado un nuevo desafío",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);
            }
        } else {
            MessageBox.show(ScreensFramework.stage,
                    "Hay campos sin rellenar",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);
        }


    }
List<Desafio> desafiosMios,desafiosDondeEstoy,desafios;
Usuario usuario;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ScreensFramework.stage.setWidth(921);
        ScreensFramework.stage.setHeight(590);
//        ScreensFramework.getStage2().getScene().getStylesheets().add("/com/sai/javafx/calendar/styles/calendar_styles.css");
        fecha = new LocalDate();
        fxcalendar2 = new FXCalendar();
        fechaIni.setText(fecha.getDayOfMonth() + "/" + fecha.getMonthOfYear() + "/" + fecha.getYear());
        hBoxFin.getChildren().addAll(fxcalendar2);
        recurso = (Recurso) rb;
        dataDesafios = FXCollections.observableArrayList();
        usuario = (Usuario) recurso.getObject("usuario");
        desafiosMios = BaseDeDatos.getBD().getDesafiosCreadosPorMi(usuario.getId());
        System.out.println("--->"+desafiosMios.size());
        desafiosDondeEstoy = BaseDeDatos.getBD().getdesafiosDondeSoyRivalBD(usuario.getId());
        System.out.println("--->"+desafiosDondeEstoy.size());
        desafios = new LinkedList<>();
        desafios.addAll(desafiosMios);
        desafios.addAll(desafiosDondeEstoy);
        System.out.println("D-->"+desafios.size());
        for(Desafio desafio : desafios)
            System.out.println("->"+desafio.getFechaInicio()+"vs"+desafio.getFechaFin());
        inicializarActividades();
        cargarAmigos();
       //cargarListaDesafios();
        cargarTabDetalles();
        segundaVez = true;


    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;//To change body of generated methods, choose Tools | Templates.
    }

    private void inicializarActividades() {
        ObservableList<String> data = FXCollections.observableArrayList();
        Dal dal = Dal.getDal();
        Collection<Actividad> actividades = BaseDeDatos.getBD().getActividades();
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

//    private void cargarListaDesafios() {
//        dataDesafios = null;
//        dataDesafios = FXCollections.observableArrayList();
//        //Dal dal = Dal.getDal();
//        
//        int mesActual = fecha.getMonthOfYear();
//        int diaActual = fecha.getDayOfMonth();
//        int añoActual = fecha.getYear();
//        boolean error = false;
//        //To change body of generated methods, choose Tools | Templates.
//        for (Desafio desafio : desafios) {
//            if (desafio.getEstado() == 0) {
//                String fechaDesafio[] = desafio.getFechaFin().split("/");
//                int año = Integer.parseInt(fechaDesafio[2]);
//                int mes = Integer.parseInt(fechaDesafio[1]);
//                int dia = Integer.parseInt(fechaDesafio[0]);
//
//                if (añoActual > año) {
//                    error = true;
//                } else if (añoActual <= año && mesActual > mes) {
//                    error = true;
//                } else if (añoActual <= año & mesActual <= mes && diaActual > dia) {
//                    error = true;
//                }
//                if (!error) {
//                    dataDesafios.add(desafio.getId() + "-" + desafio.getNombre() + " Te ha desafiado: " + desafio.getRival().getApellidos() + "  Fecha límite: " + desafio.getFechaFin());
//                }
//            }
//
//        }
//        listaDesafios.setItems(dataDesafios);
//
//    }
    //To change body of generated methods, choose Tools | Templates.

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
    
    
    @FXML
    TableView tabla;
    @FXML
    TableColumn nombreColumn,actividadColumn,fechaLimiteColumn,
            rivalColumn,tusPuntosColumn,estadoColumn,puntosRivalColumn;
    
    @FXML    
    public void tabCambia(Event event){
        if(segundaVez){cargarTabDetalles();}
    }

    private void cargarTabDetalles() {
        tusPuntosColumn.setCellValueFactory(
                new PropertyValueFactory<Desafio, String>("misPuntos"));
        actividadColumn.setCellValueFactory(
                new PropertyValueFactory<Desafio, String>("actividad"));
        fechaLimiteColumn.setCellValueFactory(
                new PropertyValueFactory<Desafio, String>("fechaFin"));
        rivalColumn.setCellValueFactory(
                new PropertyValueFactory<Desafio, String>("nombreRival"));
        estadoColumn.setCellValueFactory(
                new PropertyValueFactory<Desafio, String>("realState"));
        puntosRivalColumn.setCellValueFactory(
                new PropertyValueFactory<Desafio, String>("susPuntos"));
        nombreColumn.setCellValueFactory(
                new PropertyValueFactory<Desafio, String>("nombre"));
        
        ObservableList listDetalles = FXCollections.observableArrayList();
       // Dal dal = Dal.getDal();
        Usuario usuario = (Usuario) recurso.getObject("usuario");
        int mesActual = fecha.getMonthOfYear();
        int diaActual = fecha.getDayOfMonth();
        int añoActual = fecha.getYear();
        
        //To change body of generated methods, choose Tools | Templates.
        for (Desafio desafio : desafios) {
            if (desafio.getEstado() == 0) {
                boolean error = false;
                String fechaDesafio[] = desafio.getFechaFin().split("/");
                int año = Integer.parseInt(fechaDesafio[2]);
                int mes = Integer.parseInt(fechaDesafio[1]);
                int dia = Integer.parseInt(fechaDesafio[0]);

                if (añoActual > año) {
                    desafio.setEstado(4);
                    Dal.getDal().update(desafio);
               
                } else if (añoActual <= año && mesActual > mes) {
                    System.out.println("mes: "+mes);
                    desafio.setEstado(4);
                    Dal.getDal().update(desafio);
                   
                } else if (añoActual <= año & mesActual <= mes && diaActual > dia) {
                    System.out.println("dia");
                    desafio.setEstado(4);
                    Dal.getDal().update(desafio);
                   
                }
          
                    

            }
        listDetalles.add(desafio);
        }    
        System.out.println(desafios.size());
    tabla.setItems(listDetalles);
    }
    
    @FXML
    public void seleccionDesafio(MouseEvent t){
        TableView tabla = ((TableView)t.getSource());
        if(((TableView)t.getSource()).getSelectionModel().getSelectedIndex()!=-1){
        Desafio d = ((Desafio)tabla.getItems().get(((TableView)t.getSource()).getSelectionModel().getSelectedIndex()));
        System.out.println("Seleccionado: "+d.getId());
        
        if(d.getEstado()==0 || d.getEstado()==2){
            goButton.setDisable(false);
            recurso.putObject("desafio", d);
myController.loadScreen(ScreensFramework.PANTALLA_REALIZARACTIVIDAD, ScreensFramework.PANTALLA_REALIZARACTIVIDAD_FXML, recurso);

        }else{
        goButton.setDisable(true);
        }
        }
    }
    
    
    
    
}

