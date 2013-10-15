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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class ConsultarVistaSemanal implements Initializable {

    @FXML
    ListView lunesList;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
               LocalDate now = new LocalDate();
              
int lunes = Integer.parseInt(now.withDayOfWeek(DateTimeConstants.MONDAY).toString());
int domingo = Integer.parseInt(now.withDayOfWeek(DateTimeConstants.SUNDAY).toString());
        
        ObservableList<String> list = FXCollections.observableArrayList("correr",null,"saltar");
    lunesList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
        @Override
        public ListCell<String> call(ListView<String> list) {
            final ListCell cell = new ListCell() {
                private Text text;

                @Override
                public void updateItem(Object item, boolean empty) {
                    if(item!=null){
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
    lunesList.setItems(list);
    }
    
}
