/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.controller.dao.Dal;
import fitbox.model.Amigo;
import fitbox.model.Jugador;
import fitbox.model.Usuario;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RUBEN
 */
public class AgregarAmigoController implements Initializable, ControlledScreen {

    /**
     * Initializes the controller class.
     */
    private ScreensController myController;
    private Recurso recurso;
    private Usuario jugador;
    @FXML
    Button btnAgregar;
    @FXML
    ListView listaAmigos, listaJugadores;
    ObservableList<String> dataAmigos;
    ObservableList<String> dataJugadores;

    @FXML
    public void agregar(ActionEvent event) {
        Dal dal = Dal.getDal();
        String item = (String) listaJugadores.getSelectionModel().getSelectedItem();
        int index = (int) listaJugadores.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            String[] array = item.split("-");
            dataAmigos.add(item);
            Amigo a = new Amigo(jugador.getId(), Integer.parseInt(array[0].trim()));
            dal.insertConId(a);
            cargarJugadoresFitbox();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        recurso = (Recurso) rb;
        jugador = (Usuario) recurso.getObject("usuario");
        myController = (ScreensController) recurso.getObject("controller");
        //Stage ventana = (Stage) btnAgregar.getScene().getWindow();
       // ventana.centerOnScreen();
        cargarJugadoresFitbox();
        cargarAmigos();
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    private void cargarAmigos() {
        Dal dal = Dal.getDal();
        dataAmigos = FXCollections.observableArrayList();
        Usuario usuario = (Usuario) recurso.getObject("usuario");
        List<Amigo> amigos = dal.find(Amigo.JugadorID, new Object[]{(usuario.getId())}, Amigo.class);
        for (Amigo amigo : amigos) {
            List<Jugador> jugadoresAmigo = dal.find(Jugador.JUGADORBYUSUARIO, new Object[]{amigo.getIdAmigo()}, Jugador.class);
            Jugador jAmigo = jugadoresAmigo.get(0);
            dataAmigos.add(jAmigo.getId() + " - " + jAmigo.getApellidos());

        }
        //cargarAmigos y añadirlos al data y asignarlo a la lista luego


        listaAmigos.setItems(dataAmigos); //To change body of generated methods, choose Tools | Templates.


    }

    private void cargarJugadoresFitbox() {
        Dal dal = Dal.getDal();
        dataJugadores=null;
        dataJugadores = FXCollections.observableArrayList();
        List<Jugador> jugadores = dal.find(Jugador.TODOS_JUGADAROES, new Object[]{}, Jugador.class);
        List<Amigo> amigos = dal.find(Amigo.JugadorID, new Object[]{(jugador.getId())}, Amigo.class);
        boolean existe = false;
        for (Jugador j : jugadores) {
            for (Amigo amigo : amigos) {
                if (j.getId() == amigo.getIdAmigo()||j.getId()==jugador.getId()) {
                    existe = true;
                }
            }
            if (!existe) {
                dataJugadores.add(j.getId() + " - " + j.getApellidos());
            }

            existe = false;

        }

        listaJugadores.setItems(dataJugadores);



    }
}
