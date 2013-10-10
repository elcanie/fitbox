/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import com.sai.javafx.calendar.FXCalendar;
import fitbox.controller.dao.Dal;
import fitbox.model.Usuario;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Elias
 */
public class ConsultarCalendarioController implements Initializable {
static Scene scene;
    public static void setScene(Scene _scene) {
scene = _scene;
    }

    @FXML
    private TableView tablaConsultarCalendario;
    @FXML
    private AnchorPane anchorPane;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        VBox vb = new VBox();
		vb.setSpacing(10);
		
		Label lbl = new Label("Select the date : ");
		
		HBox hb = new HBox();
		hb.setSpacing(10);
		hb.getChildren().addAll(lbl, new FXCalendar());
		
		vb.getChildren().addAll(hb);
		anchorPane.getChildren().add(vb);
    
    tablaConsultarCalendario.setEditable(true);
    tablaConsultarCalendario.setMaxWidth(520);
    TableColumn id = new TableColumn("Id");
    TableColumn nombre = new TableColumn("Nombre");
    TableColumn password = new TableColumn("Password");
    id.setMinWidth(100);
    nombre.setMinWidth(150);
    password.setMinWidth(270);
    id.setCellValueFactory(new PropertyValueFactory<Integer, String>("Id"));
    nombre.setCellValueFactory(new PropertyValueFactory<String, String>("Nombre"));
    password.setCellValueFactory(new PropertyValueFactory("Password"));
    tablaConsultarCalendario.getColumns().addAll(id, nombre, password);
    
    Dal dal = Dal.getDal();

    //ObservableList<Usuario> acabado = FXCollections.observableList(dal.find(Usuario.TODOS_USUARIOS, Usuario.class));
   // tablaConsultarCalendario.setItems(acabado);
        
    }
    
}
