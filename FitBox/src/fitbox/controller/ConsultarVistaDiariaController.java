/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import static fitbox.controller.ConsultarVistaSemanalController.fxcalendar;
import fitbox.controller.dao.Dal;
import fitbox.model.Calendario;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.joda.time.LocalDate;

/**
 *
 * @author Elias
 */
public class ConsultarVistaDiariaController implements Initializable {

    static Scene scene;
    private static ConsultarVistaDiariaController consultarVistaDiaria;

    public static void setScene(Scene _scene) {
        scene = _scene;
    }
    @FXML
    private ListView diaListView;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label diaLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalDate hoy = new LocalDate();
        updateVista(hoy.getYear(),hoy.getMonthOfYear(),16);
    }
   public void updateVista(int _año, int _mes, int _dia) {
        System.out.println(_año+" "+_mes+" "+_dia);
        diaLabel.setText("Dia: "+_dia);
        LocalDate now = new LocalDate(_año, _mes, _dia);

        Dal dal = Dal.getDal();
        List<Calendario> calendarios = dal.find(Calendario.CALENDARIOBYJUGADORID, new Object[]{10}, Calendario.class);
        System.out.println("Actividades: " + calendarios.size());
        List<Calendario> listDiaStr = new LinkedList<>();


        diaListView.setItems(null);
        
        for (Calendario cal : calendarios)
            if (cal.getFecha().getDayOfMonth()==now.getDayOfMonth() && cal.getFecha().getYear() == now.getYear() && (cal.getFecha().getMonthOfYear()==now.getMonthOfYear()))
                listDiaStr.add(cal);
                
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
                                text = new Text(((Calendario)item).toString2());
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

   

}

