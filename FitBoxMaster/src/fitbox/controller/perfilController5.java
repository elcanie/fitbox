/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.controller.dao.Dal;
import fitbox.model.Jugador;
import fitbox.model.Usuario;
import fitbox.model.datosUsuario;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import jfx.messagebox.MessageBox;

/**
 *
 * @author usuario
 */
public class perfilController5 implements Initializable, ControlledScreen {

    private ScreensController myController = new ScreensController(ScreensFramework.inicialStage);
    private datosUsuario usuario;
    private Recurso recurso;
    @FXML
    private CheckBox nombre;
    @FXML
    private CheckBox genero;
    @FXML
    private CheckBox apellidos;
    @FXML
    private CheckBox peso;
    @FXML
    private CheckBox edad;
    @FXML
    private CheckBox actividad;
    @FXML
    private CheckBox fechaNac;
    @FXML
    private CheckBox logros;
    @FXML
    private CheckBox aceptar;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        usuario.setMostrarNombre(nombre.isSelected());
        usuario.setMostrarGenero(genero.isSelected());
        usuario.setMostrarApellidos(apellidos.isSelected());
        usuario.setMostrarPeso(peso.isSelected());
        usuario.setMostrarEdad(edad.isSelected());
        usuario.setMostrarActividad(actividad.isSelected());
        usuario.setMostrarFechaNac(fechaNac.isSelected());
        usuario.setMostrarLogros(logros.isSelected());
        recurso.putObject("usuario", usuario);

        if (aceptar.isSelected() == true) {
            //GUARDAR EN LA BASE DE DATOS!!!!!
            
            Usuario u = new Usuario(0, usuario.getNombre(), usuario.getPass());
            Dal.getDal().insert(u);
            int id = ((Usuario)Dal.getDal().find(Usuario.USUARIOSBYNOMBREYPASS, new Object[]{u.getNombre(),u.getPassword()}, Usuario.class).get(0)).getId();
            Jugador j =  new Jugador(id, usuario.getApellidos() , usuario.getSexo(), 
                    Double.parseDouble(usuario.getAltura()), Double.parseDouble(usuario.getPeso()), 
                    usuario.getCorreo(), 0, 1, 1,
                    usuario.getAnyo()+"/"+usuario.getMes()+"/"+usuario.getDia());
            Dal.getDal().insertConId(j);
            
            myController.loadScreenInicial(ScreensFramework.PANTALLA_LOGIN, ScreensFramework.PANTALLA_LOGIN_FXML, null);
//            myController.setScreen(ScreensFramework.PANTALLA_LOGIN);
        }else{
            
        int answer = MessageBox.show(ScreensFramework.inicialStage,
                    "Debe aceptar los términos del contrato para poder registrarse.",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);
        
        }
    }

    @FXML
    private void handleButtonAnterior(ActionEvent event) {

        usuario.setMostrarNombre(nombre.isSelected());
        usuario.setMostrarGenero(genero.isSelected());
        usuario.setMostrarApellidos(apellidos.isSelected());
        usuario.setMostrarPeso(peso.isSelected());
        usuario.setMostrarEdad(edad.isSelected());
        usuario.setMostrarActividad(actividad.isSelected());
        usuario.setMostrarFechaNac(fechaNac.isSelected());
        usuario.setMostrarLogros(logros.isSelected());
        usuario.setAceptar(aceptar.isSelected());
        recurso.putObject("usuario", usuario);
        myController.loadScreenInicial(ScreensFramework.PANTALLA_PERFIL4, ScreensFramework.PANTALLA_PERFIL4_FXML, recurso);
//        myController.setScreen(ScreensFramework.PANTALLA_PERFIL4);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuario = new datosUsuario();
        recurso = (Recurso) rb;
        usuario = (datosUsuario) recurso.getObject("usuario");
        try {
            nombre.setSelected(usuario.isMostrarNombre());
        } catch (Exception e) {
        }
        try {
            genero.setSelected(usuario.isMostrarGenero());
        } catch (Exception e) {
        }
        try {
            apellidos.setSelected(usuario.isMostrarApellidos());
        } catch (Exception e) {
        }
        try {
            peso.setSelected(usuario.isMostrarPeso());
        } catch (Exception e) {
        }
        try {
            edad.setSelected(usuario.isMostrarEdad());
        } catch (Exception e) {
        }
        try {
            actividad.setSelected(usuario.isMostrarActividad());
        } catch (Exception e) {
        }
        try {
            fechaNac.setSelected(usuario.isMostrarFechaNac());
        } catch (Exception e) {
        }
        try {
            logros.setSelected(usuario.isMostrarLogros());
        } catch (Exception e) {
        }
        try {
            aceptar.setSelected(usuario.isAceptar());
        } catch (Exception e) {
        }

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}
