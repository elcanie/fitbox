/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import com.mysql.jdbc.PreparedStatement;
import fitbox.controller.dao.Conexion;
import fitbox.controller.dao.Dal;
import fitbox.model.Evento;
import fitbox.model.Usuario;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.net.URL;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Alejandro
 */
public class RealizarEventosController implements Initializable, ControlledScreen {

    private ScreensController myController;

    @FXML
    private Button botonGO;
    @FXML
    private TableView tablaEventos;
    @FXML
    private ListView listaPuntuacion;
    @FXML
    private TableColumn nombreT, descripcionT, fechaT;

    private Usuario user;
    private Recurso recurso;
    

    public void mostrarPuntuaciones(int id) {
        String consulta="select * from puntuacion_evento where idEvento="+id+";";
        
       Connection conexion = (Connection) Conexion.getConexion();
        try {
            Statement s = conexion.createStatement();
            ResultSet rs = rs = s.executeQuery(consulta);
            
            
            
 
            //List<Evento> eventos = dal.find(consulta, new Object[]{user.getId()}, Evento.class);
            //Dal dal = Dal.getDal(); List<Evento> eventos = dal.find(Evento.TODOS_EVENTOS_USUARIOINICIADO, new Object[]{user.getId()}, Evento.class);
            //ObservableList<Evento> datos = FXCollections.observableArrayList(eventos);
            //listaPuntuacion.setItems(datos);
            
            
            /*
            ---Ejemplo con listView
            Dal dal = Dal.getDal();
            Collection<Evento> datosE = dal.find(Evento.TODOS_EVENTOS_USUARIOINICIADO,new Object[]{user.getId()},Evento.class);
            //Collection<Evento> datosE = dal.find(Evento.TODOS_EVENTOS,new Object[]{},Evento.class);
            Iterator<Evento> itdatosE = datosE.iterator();
            Collection<String> eventosString = new ArrayList();
            Evento e;
            System.out.println("tallaEventos: "+datosE.size());
            while(itdatosE.hasNext()){
            e=itdatosE.next();
            eventosString.add(e.toString());
            }
            
            ObservableList<String> eventos =FXCollections.observableArrayList(eventosString);
            listaEventos.setItems(eventos);
            */
        } catch (SQLException ex) {
            System.out.println("Fallo en mostrar puntuaciones (RealizarEventoController)");
        }
    }

    public void mostrarEventos() {
        Dal dal = Dal.getDal();
        List<Evento> eventos = dal.find(Evento.TODOS_EVENTOS_USUARIOINICIADO, new Object[]{user.getId()}, Evento.class);
        ObservableList<Evento> datos = FXCollections.observableArrayList(eventos);
        tablaEventos.setItems(datos);
    }
    
    @FXML
    private void handleButtonGO(ActionEvent event) {
    
    
    }
    
    @FXML
    public void mouseClicked(MouseEvent t) {
       int id = ((Evento)((TableView) t.getSource()).getItems().get(((TableView) t.getSource()).getSelectionModel().getSelectedIndex())).getId();
       mostrarPuntuaciones(id);
    }
        

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.recurso = (Recurso) rb;
        this.user = (Usuario) recurso.getObject("usuario");
        
        nombreT.setCellValueFactory(new PropertyValueFactory<Evento, String>("nombre"));
        descripcionT.setCellValueFactory(new PropertyValueFactory<Evento, String>("descripcion"));
        fechaT.setCellValueFactory(new PropertyValueFactory<Evento, String>("fecha"));
        ScreensFramework.stage.setWidth(732);
        ScreensFramework.stage.setHeight(425);
        
        mostrarEventos();

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

}
