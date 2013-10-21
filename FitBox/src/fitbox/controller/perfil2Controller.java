/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.model.Actividad;
import fitbox.model.datosUsuario;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import jfx.messagebox.MessageBox;

/**
 *
 * @author Alejandro
 */
public class perfil2Controller implements Initializable, ControlledScreen {

    @FXML
    private Button siguienteBT;
    @FXML
    private Button anteriorBT;
    @FXML
    private TextField diasT;
    @FXML
    private CheckBox correrCB;
    @FXML
    private CheckBox bicicletaCB;
    @FXML
    private CheckBox pesasCB;
    @FXML
    private CheckBox futbolCB;
    @FXML
    private CheckBox baloncestoCB;
    @FXML
    private CheckBox abdominalesCB;
    @FXML
    private CheckBox otrosCB;
    @FXML
    private TextField otrosEjerTF;
    @FXML
    private CheckBox categoria1;
    @FXML
    private CheckBox categoria2;
    @FXML
    private CheckBox categoria3;
    @FXML
    private CheckBox categoria4;
    private datosUsuario usuario;
    private ScreensController myController;
    private Recurso recurso;

    @FXML
    private void handleButtonSiguiente(ActionEvent event) {
        boolean valido = true;
        int answer;
        int diasE;
        try {
            diasE = Integer.parseInt(diasT.getText());
            if (diasE <= 0) {
                valido = false;
                answer = MessageBox.show(ScreensFramework.stage,
                        "Formado de días no válido. Introduzca valor numérico",
                        "Information dialog",
                        MessageBox.ICON_INFORMATION | MessageBox.OK);
            }
        } catch (Exception e) {
            valido = false;
            answer = MessageBox.show(ScreensFramework.stage,
                    "Formado de días no válido. Introduzca valor numérico",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);

        }
        if (valido) {
            usuario.setDias(diasT.getText());
            usuario.setCorrerCB(correrCB.isSelected());
            usuario.setBicicletaCB(bicicletaCB.isSelected());
            usuario.setPesasCB(pesasCB.isSelected());
            usuario.setFutbolCB(futbolCB.isSelected());
            usuario.setBaloncestoCB(baloncestoCB.isSelected());
            usuario.setAbdominalesCB(abdominalesCB.isSelected());
            usuario.setOtrosCB(otrosCB.isSelected());
            usuario.setOtrosEjerTF(otrosEjerTF.getText());
            usuario.setCategoria1(categoria1.isSelected());
            usuario.setCategoria2(categoria2.isSelected());
            usuario.setCategoria3(categoria3.isSelected());
            usuario.setCategoria4(categoria4.isSelected());

            recurso.putObject("usuario", usuario);
            myController.loadScreen(ScreensFramework.PANTALLA_PERFIL3, ScreensFramework.PANTALLA_PERFIL3_FXML, recurso);
            myController.setScreen(ScreensFramework.PANTALLA_PERFIL3);

        }

    }

    @FXML
    private void handleButtonAtras(ActionEvent event) {

        boolean valido = true;
        int answer;
        int diasE;
        try {
            diasE = Integer.parseInt(diasT.getText());
            if (diasE <= 0) {
                valido = false;
                answer = MessageBox.show(ScreensFramework.stage,
                        "Formado de días no válido. Introduzca valor numérico",
                        "Information dialog",
                        MessageBox.ICON_INFORMATION | MessageBox.OK);
            }
        } catch (Exception e) {
         

        }
        if (valido) {

            usuario.setDias(diasT.getText());
            usuario.setCorrerCB(correrCB.isSelected());
            usuario.setBicicletaCB(bicicletaCB.isSelected());
            usuario.setPesasCB(pesasCB.isSelected());
            usuario.setFutbolCB(futbolCB.isSelected());
            usuario.setBaloncestoCB(baloncestoCB.isSelected());
            usuario.setAbdominalesCB(abdominalesCB.isSelected());
            usuario.setOtrosCB(otrosCB.isSelected());
            usuario.setOtrosEjerTF(otrosEjerTF.getText());
            usuario.setCategoria1(categoria1.isSelected());
            usuario.setCategoria2(categoria2.isSelected());
            usuario.setCategoria3(categoria3.isSelected());
            usuario.setCategoria4(categoria4.isSelected());

            myController.loadScreen(ScreensFramework.PANTALLA_PERFIL1, ScreensFramework.PANTALLA_PERFIL1_FXML, recurso);
            myController.setScreen(ScreensFramework.PANTALLA_PERFIL1);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuario = new datosUsuario();
        recurso = (Recurso) rb;
        usuario = (datosUsuario) recurso.getObject("usuario");
        diasT.setText(usuario.getDias());
        try {
            correrCB.setSelected(usuario.getCorrerCB());
        } catch (Exception e) {
        }
        try {
            bicicletaCB.setSelected(usuario.getBicicletaCB());
        } catch (Exception e) {
        }
        try {
            pesasCB.setSelected(usuario.getPesasCB());
        } catch (Exception e) {
        }
        try {
            futbolCB.setSelected(usuario.getFutbolCB());
        } catch (Exception e) {
        }
        try {
            baloncestoCB.setSelected(usuario.getBaloncestoCB());
        } catch (Exception e) {
        }
        try {
            abdominalesCB.setSelected(usuario.getAbdominalesCB());
        } catch (Exception e) {
        }
        try {
            otrosCB.setSelected(usuario.getOtrosCB());
        } catch (Exception e) {
        }
        try {
            categoria1.setSelected(usuario.getCategoria1());
        } catch (Exception e) {
        }
        try {
            categoria2.setSelected(usuario.getCategoria2());
        } catch (Exception e) {
        }
        try {
            categoria3.setSelected(usuario.getCategoria3());
        } catch (Exception e) {
        }
        try {
            categoria4.setSelected(usuario.getCategoria4());
        } catch (Exception e) {
        }

        otrosEjerTF.setText(usuario.getOtrosEjerTF());


    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}
