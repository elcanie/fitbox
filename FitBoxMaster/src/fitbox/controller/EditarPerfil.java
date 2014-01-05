/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;


import fitbox.controller.dao.Dal;
import fitbox.model.Jugador;
import fitbox.model.Usuario;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Elias
 */
public class EditarPerfil implements Initializable, ControlledScreen {

    @FXML
    TextField nombreText, apellidosText, alturaText, pesoText,
            correoText, correo2Text, contraseñaText, contraseña2Text, fechaText;
    @FXML
    RadioButton hombreRadio, mujerRadio;
    @FXML
    Label errorGenero;
    @FXML AnchorPane panelIzq;
    @FXML AnchorPane panelDer;
    @FXML ImageView imagenIzq;
    @FXML ImageView imagenDer;
    private Recurso recurso;
    private ScreensController myController = new ScreensController(ScreensFramework.stage);
    private Usuario usuario;
    private Jugador jugador;
    private String genero;

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("ok");
        recurso = (Recurso) rb;
        usuario = (Usuario) recurso.getObject("usuario");
        jugador = (Jugador) Dal.getDal().find(Jugador.JUGADORBYUSUARIO, new Object[]{usuario.getId()}, Jugador.class).get(0);

        //ScreensFramework.getStage2().getScene().getStylesheets().add("/com/sai/javafx/calendar/styles/calendar_styles.css");

//        FXCalendar.dateTxtField.setText(jugador.getFecha().getYear() + "/" + cal.getFecha().getMonthOfYear() + "/" + cal.getFecha().getDayOfMonth());
        nombreText.setText(usuario.getNombre());
        apellidosText.setText(jugador.getApellidos());
        alturaText.setText("" + jugador.getAltura());
        fechaText.setText(jugador.getNacimiento());
        pesoText.setText(jugador.getPeso() + "");
        correoText.setText(jugador.getCorreo());
        correo2Text.setText(jugador.getCorreo());
        if (jugador.getGenero().trim().equalsIgnoreCase("mujer")) {
            mujerRadio.setSelected(true);
        }
        if (jugador.getGenero().trim().equalsIgnoreCase("hombre")) {
            hombreRadio.setSelected(true);
        }
    }

    @FXML
    public void editar(ActionEvent event) {
        if (comprobarCampos()) {
            usuario.setNombre(nombreText.getText());
            if (!contraseñaText.getText().trim().equals("")) {
                usuario.setFactor(contraseñaText.getText());
            }
            jugador.setAltura(Double.parseDouble(alturaText.getText()));
            jugador.setPeso(Double.parseDouble(pesoText.getText()));
            jugador.setGenero(genero);
            jugador.setCorreo(correoText.getText());
            jugador.setNacimiento(fechaText.getText());
            jugador.setApellidos(apellidosText.getText());
            Dal.getDal().update(usuario);
            Dal.getDal().update(jugador);
            myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
            //myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);
        }
    }

    @FXML
    public void invertirGeneroM(ActionEvent event) {
        mujerRadio.setSelected(false);
        hombreRadio.setSelected(true);

    }

    @FXML
    public void invertirGeneroH(ActionEvent event) {
        mujerRadio.setSelected(true);
        hombreRadio.setSelected(false);
    }

    @FXML
    public void cancelar(ActionEvent event) {
        myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
       // myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);
    }

    private boolean comprobarCampos() {
        boolean response = true;
        if(!fechaText.getText().matches("\\d{4}/\\d{2}/\\d{2}")){
        fechaText.setStyle("-fx-background-color: red");
        response = false;
        }else{fechaText.setStyle("-fx-background-color: white");}
        if (nombreText.getText().trim().equals("") ){
            
            nombreText.setStyle("-fx-background-color: red");
            response = false;
        } else {
            String nombre_regex = "[a-zA-Z_áéíóú]+";
            if(!nombreText.getText().matches(nombre_regex)){
                nombreText.setStyle("-fx-background-color: red");
            response=false;
            }else
            nombreText.setStyle("-fx-background-color: white");
        }
        if (apellidosText.getText().trim().equals("")) {
            apellidosText.setStyle("-fx-background-color: red");
            response = false;
        } else {
             String apellidos_regex = "[a-zA-Z_áéíóú ]+";
            if(!apellidosText.getText().matches(apellidos_regex)){
                apellidosText.setStyle("-fx-background-color: red");
            response=false;
            }else
            apellidosText.setStyle("-fx-background-color: white");
        }
        if (alturaText.getText().trim().equals("")) {
            alturaText.setStyle("-fx-background-color: red");
            response = false;
        } else {
            try {
                Double.parseDouble(alturaText.getText());
                alturaText.setStyle("-fx-background-color: white");
            } catch (java.lang.NumberFormatException e) {
                alturaText.setStyle("-fx-background-color: red");
                response = false;
            }

        }
        if (pesoText.getText().trim().equals("")) {
            pesoText.setStyle("-fx-background-color: red");
            response = false;
        } else {
            try {
                Double.parseDouble(pesoText.getText());
                pesoText.setStyle("-fx-background-color: white");
            } catch (java.lang.NumberFormatException e) {
                pesoText.setStyle("-fx-background-color: red");
                response = false;
            }
        }

        if (correoText.getText().trim().equals("")) {
            correoText.setStyle("-fx-background-color: red");
            response = false;
        } else {
            String email_regex = "[a-zA-Z_]+@[a-zA-Z]+.[a-zA-Z.]+";
            if(!correoText.getText().matches(email_regex)){
                correoText.setStyle("-fx-background-color: red");
            response = false;
            }
            else
            correoText.setStyle("-fx-background-color: white");
        }
        if (correo2Text.getText().trim().equals("")
                || !correoText.getText().equals(correo2Text.getText())) {
            correo2Text.setStyle("-fx-background-color: red");
            response = false;
        } else {
            correo2Text.setStyle("-fx-background-color: white");
        }
        if (!contraseñaText.getText().equals(contraseña2Text.getText())) {
            contraseña2Text.setStyle("-fx-background-color: red");
            contraseñaText.setStyle("-fx-background-color: red");
            response = false;
        } else {
            contraseña2Text.setStyle("-fx-background-color: white");
            contraseñaText.setStyle("-fx-background-color: white");
        }
        if (!hombreRadio.isSelected() && !mujerRadio.isSelected()) {
            errorGenero.setText("Elija su genero");
            response = false;
        }else{
       if(hombreRadio.isSelected())
        genero = "hombre";
       if(mujerRadio.isSelected())
           genero = "mujer";
        }
        return response;
    }
    
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
        
        jugador = (Jugador) Dal.getDal().find(Jugador.JUGADORBYUSUARIO, new Object[]{usuario.getId()}, Jugador.class).get(0);
        ScreensFramework.getStage2().getScene().getStylesheets().add("/com/sai/javafx/calendar/styles/calendar_styles.css");
//        FXCalendar.dateTxtField.setText(jugador.getFecha().getYear() + "/" + cal.getFecha().getMonthOfYear() + "/" + cal.getFecha().getDayOfMonth());
        nombreText.setText(usuario.getNombre());
        apellidosText.setText(jugador.getApellidos());
        alturaText.setText("" + jugador.getAltura());
        pesoText.setText(jugador.getPeso() + "");
        correoText.setText(jugador.getCorreo());
        correo2Text.setText(jugador.getCorreo());
        fechaText.setText(jugador.getNacimiento());
        if (jugador.getGenero().trim().equalsIgnoreCase("mujer")) {
            mujerRadio.setSelected(true);
        }
        if (jugador.getGenero().trim().equalsIgnoreCase("hombre")) {
            hombreRadio.setSelected(true);
        }
        
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

    
}
