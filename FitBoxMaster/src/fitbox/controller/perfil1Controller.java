/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.model.datosUsuario;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import jfx.messagebox.MessageBox;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;

/**
 *
 * @author Alejandro
 */
public class perfil1Controller implements Initializable, ControlledScreen {

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @FXML
    private TextField nombreText;
    @FXML
    private TextField apellidosText;
    @FXML
    private ComboBox diaComboB;
    @FXML
    private ComboBox mesComboB;
    @FXML
    private ComboBox anyoComboB;
    @FXML
    private TextField alturaText;
    @FXML
    private TextField pesoText;
    @FXML
    private TextField correoText;
    @FXML
    private PasswordField contrasenyaText;
    @FXML
    private PasswordField repContrasenyaText;
    @FXML
    private Button siguienteBt;
    @FXML
    private Label comprobacionCorreoLabel;
    @FXML
    private Label comprobacionContraseñaLabel;
    @FXML
    private RadioButton hombreRadioBt;
    @FXML
    private RadioButton mujerRadioBt;
    @FXML
    private ImageView imagContraseñaKO;
    @FXML
    private ImageView imagContraseñaOK;
    @FXML
    private ImageView imagCorreoKO;
    @FXML
    private ImageView imagCorreoOK;
    @FXML
    private ToggleGroup genero;
    private String nombre;
    private String apellidos;
    private String dia;
    private String mes;
    private String anyo;
    private String altura;
    private String peso;
    private String correo;
    private String pass;
    private String repPass;
    private String sexo;
    private datosUsuario usuario;
    private ScreensController myController;
    private Recurso recurso;

    @FXML
    private void handleButtonAnterior(ActionEvent event) {

        myController.loadScreen(ScreensFramework.PANTALLA_LOGIN, ScreensFramework.PANTALLA_LOGIN_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_LOGIN);

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        nombre = nombreText.getText();
        apellidos = apellidosText.getText();

        try {
            dia = diaComboB.getSelectionModel().getSelectedItem().toString();
        } catch (Exception e) {
            dia = "";
        }

        try {
            mes = mesComboB.getSelectionModel().getSelectedItem().toString();
        } catch (Exception e) {
            mes = "";
        }

        try {
            anyo = anyoComboB.getSelectionModel().getSelectedItem().toString();
        } catch (Exception e) {
            anyo = "";
        }

        altura = alturaText.getText();
        peso = pesoText.getText();
        correo = correoText.getText();
        pass = contrasenyaText.getText();
        repPass = repContrasenyaText.getText();

        try {
            String aux = genero.getSelectedToggle().toString();
            if (aux.equals("RadioButton[id=hombreRadioBt, styleClass=radio-button]")) {
                sexo = "hombre";
            } else {
                sexo = "mujer";
            }
        } catch (Exception e) {
            sexo = "";
        }
        comprobarCampos();
    }

    @FXML
    private void comprobarCampos() {

        if (nombre.equals("") || apellidos.equals("") || dia.equals("") || mes.equals("") || anyo.equals("")
                || altura.equals("") || peso.equals("") || correo.equals("") || pass.equals("")
                || repPass.equals("") || sexo.equals("")) {

            int answer = MessageBox.show(ScreensFramework.stage,
                    "Por favor, rellena todos los campos.",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);

        } else {
            confirmarFormulario();
        }
    }

    @FXML
    private void confirmarFormulario() {
        //FALTA VALIDAR FECHA
        boolean valido = true;

        comprobacionCorreoLabel.setText("");
        comprobacionContraseñaLabel.setText("");
        imagCorreoOK.setVisible(false);
        imagCorreoKO.setVisible(false);
        imagContraseñaOK.setVisible(false);
        imagContraseñaKO.setVisible(false);

        //valida el correo
        boolean valid = validateEmail(correo);
        if (valid) {
            imagCorreoOK.setVisible(true);
        } else {
            valido = false;
            imagCorreoKO.setVisible(true);
            comprobacionCorreoLabel.setText("Formato incorrecto");
        }

        //valida que las contraseñas coincidan
        if (pass.equals(repPass)) {
            imagContraseñaOK.setVisible(true);
        } else {
            valido = false;
            imagContraseñaKO.setVisible(true);
            comprobacionContraseñaLabel.setText("No coinciden");

        }

        //altura valida
        int answer;
        int altE;
        try {
            altE = Integer.parseInt(altura);
            if (altE <= 0) {
                valido = false;
                answer = MessageBox.show(ScreensFramework.stage,
                        "Altura no válida.",
                        "Information dialog",
                        MessageBox.ICON_INFORMATION | MessageBox.OK);
            }
        } catch (Exception e) {
            valido = false;
            answer = MessageBox.show(ScreensFramework.stage,
                    "Altura no válida.",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);

        }

        //peso valido
        float pesoE;
        int ans;
        try {
            pesoE = Float.parseFloat(peso);
            if (pesoE <= 0) {
                valido = false;
                ans = MessageBox.show(ScreensFramework.stage,
                        "Peso no válido.",
                        "Information dialog",
                        MessageBox.ICON_INFORMATION | MessageBox.OK);
            }
        } catch (Exception e) {
            valido = false;
            ans = MessageBox.show(ScreensFramework.stage,
                    "Peso no válido.",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);
        }



        Calendar c = Calendar.getInstance();
        int diaActual = c.get(Calendar.DATE);
        int mesActual = c.get(Calendar.MONTH+1);
        int anyoActual = c.get(Calendar.YEAR);

        int diaR = Integer.parseInt(dia);
        int anyoR = Integer.parseInt(anyo);
        int mesR = 0;
        switch (mes) {
            case "Enero":
                mesR = 1;
                break;
            case "Febrero":
                mesR = 2;
                break;
            case "Marzo":
                mesR = 3;
                break;
            case "Abril":
                mesR = 4;
                break;
            case "Mayo":
                mesR = 5;
                break;
            case "Junio":
                mesR = 6;
                break;
            case "Julio":
                mesR = 7;
                break;
            case "Agosto":
                mesR = 8;
                break;
            case "Septiembre":
                mesR = 9;
                break;
            case "Octubre":
                mesR = 10;
                break;
            case "Noviembre":
                mesR = 11;
                break;
            case "Diciembre":
                mesR = 12;
                break;
        }
        boolean mostrarAvisoFecha = false;
        if (anyoR > anyoActual) {
            valido = false;
            mostrarAvisoFecha = true;
        } else if (anyoR == anyoActual && mesR > mesActual) {
            valido = false;
            mostrarAvisoFecha = true;
        } else if (anyoR == anyoActual && mesR == mesActual && diaR > diaActual) {
            valido = false;
            mostrarAvisoFecha = true;
        }
        System.out.println("Fecha actual:"+diaActual+"/"+mesActual+"/"+anyoActual);
        System.out.println("Fecha registro:"+diaR+"/"+mesR+"/"+anyoR);
        if (mostrarAvisoFecha == true) {
            int a = MessageBox.show(ScreensFramework.stage,
                    "Fecha invalida.",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);

        }



        if (valido) {
            usuario.setNombre(nombre);
            usuario.setApellidos(apellidos);
            usuario.setDia(dia);
            usuario.setMes(mes);
            usuario.setAnyo(anyo);
            usuario.setAltura(altura);
            usuario.setPeso(peso);
            usuario.setCorreo(correo);
            usuario.setPass(pass);
            usuario.setSexo(sexo);

            //siguiente
            recurso = new Recurso();
            recurso.putObject("usuario", usuario);
            myController.loadScreen(ScreensFramework.PANTALLA_PERFIL2, ScreensFramework.PANTALLA_PERFIL2_FXML, recurso);
            myController.setScreen(ScreensFramework.PANTALLA_PERFIL2);
        }


    }

    @FXML
    private void rellenarComboAnyo() {

        ObservableList<String> options =
                FXCollections.observableArrayList();
        for (int i = 1900; i < 2030; i++) {

            options.add(Integer.toString(i));

        }
        anyoComboB.setItems(options);
    }

    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rellenarComboAnyo();
        usuario = new datosUsuario();
        recurso = (Recurso) rb;
        if (recurso != null) {
            usuario = (datosUsuario) recurso.getObject("usuario");
            nombreText.setText(usuario.getNombre());
            apellidosText.setText(usuario.getApellidos());
            diaComboB.setValue(usuario.getDia());
            mesComboB.setValue(usuario.getMes());
            anyoComboB.setValue(usuario.getAnyo());


            if (usuario.getSexo().equals("hombre")) {
                hombreRadioBt.setSelected(true);
            } else {
                mujerRadioBt.setSelected(true);
            }
            alturaText.setText(usuario.getAltura());
            pesoText.setText(usuario.getPeso());
            correoText.setText(usuario.getCorreo());

        }


        ScreensFramework.stage.setWidth(718);
        ScreensFramework.stage.setHeight(400);
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}
