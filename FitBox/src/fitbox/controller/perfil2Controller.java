/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.model.Actividad;
import fitbox.model.datosAct;
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
    private datosAct actividad;
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
            actividad.setDias(diasT.getText());
            actividad.setCorrerCB(correrCB.isSelected());
            actividad.setBicicletaCB(bicicletaCB.isSelected());
            actividad.setPesasCB(pesasCB.isSelected());
            actividad.setFutbolCB(futbolCB.isSelected());
            actividad.setBaloncestoCB(baloncestoCB.isSelected());
            actividad.setAbdominalesCB(abdominalesCB.isSelected());
            actividad.setOtrosCB(otrosCB.isSelected());
            actividad.setOtrosEjerTF(otrosEjerTF.getText());
            actividad.setCategoria1(categoria1.isSelected());
            actividad.setCategoria2(categoria2.isSelected());
            actividad.setCategoria3(categoria3.isSelected());
            actividad.setCategoria4(categoria4.isSelected());

            recurso.putObject("actividad", actividad);
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
            valido = false;
            answer = MessageBox.show(ScreensFramework.stage,
                    "Formado de días no válido. Introduzca valor numérico",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);

        }
        if (valido) {

            actividad.setDias(diasT.getText());
            actividad.setCorrerCB(correrCB.isSelected());
            actividad.setBicicletaCB(bicicletaCB.isSelected());
            actividad.setPesasCB(pesasCB.isSelected());
            actividad.setFutbolCB(futbolCB.isSelected());
            actividad.setBaloncestoCB(baloncestoCB.isSelected());
            actividad.setAbdominalesCB(abdominalesCB.isSelected());
            actividad.setOtrosCB(otrosCB.isSelected());
            actividad.setOtrosEjerTF(otrosEjerTF.getText());
            actividad.setCategoria1(categoria1.isSelected());
            actividad.setCategoria2(categoria2.isSelected());
            actividad.setCategoria3(categoria3.isSelected());
            actividad.setCategoria4(categoria4.isSelected());

            myController.loadScreen(ScreensFramework.PANTALLA_PERFIL1, ScreensFramework.PANTALLA_PERFIL1_FXML, recurso);
            myController.setScreen(ScreensFramework.PANTALLA_PERFIL1);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actividad = new datosAct();
        recurso = (Recurso) rb;
        if (recurso != null) {
            actividad = (datosAct) recurso.getObject("actividades");
            diasT.setText(actividad.getDias());
            correrCB.setSelected(actividad.getCorrerCB());
            bicicletaCB.setSelected(actividad.getBicicletaCB());
            pesasCB.setSelected(actividad.getPesasCB());
            futbolCB.setSelected(actividad.getFutbolCB());
            baloncestoCB.setSelected(actividad.getBaloncestoCB());
            abdominalesCB.setSelected(actividad.getAbdominalesCB());
            otrosCB.setSelected(actividad.getOtrosCB());
            otrosEjerTF.setText(actividad.getOtrosEjerTF());
            categoria1.setSelected(actividad.getCategoria1());
            categoria2.setSelected(actividad.getCategoria2());
            categoria3.setSelected(actividad.getCategoria3());
            categoria4.setSelected(actividad.getCategoria4());

        }
        ScreensFramework.stage.setWidth(718);
        ScreensFramework.stage.setHeight(369);
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}
