/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.controller.dao.Dal;
import fitbox.model.Actividad;
import fitbox.model.Usuario;
import fitbox.model.Video;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author Marcos
 */
public class videosController implements Initializable, ControlledScreen {
    //por hacer: que se note al apretar el boton home
    //           ajustar la ventana, scrolls mas grandes
    //           
    @FXML
    private VBox vBoxGrande;
    
    @FXML
    private VBox vBoxGrandeS;
    
    @FXML 
    private Label lblEmpty;
    private ScreensController myController = new ScreensController(ScreensFramework.stage);
    private Usuario usuario;
    private Recurso recurso;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.recurso=(Recurso)rb;
        usuario=(Usuario) recurso.getObject("usuario");
        insertarvideosV();
        insertarvideosS();

        ScreensFramework.stage.setResizable(true);
        //ScreensFramework.stage.setWidth(1450);
        //ScreensFramework.stage.setHeight(710);
        
        
        
        
        
//        
    }  
    
     private void cargarVideo(String url, WebView w){
         //url="<iframe width=\"300\" height=\"210\" src=\"http://www.youtube.com/embed/-Tp2hCHJr3o\" frameborder=\"0\" allowfullscreen></iframe>";
                 
        String content_Url = "<iframe width=\"300\" height=\"210\" src=\"http://www.youtube.com/embed/"+url+"\" frameborder=\"0\" allowfullscreen></iframe>";
       //String content_Url = "<iframe width=\"300\" height=\"210\" src=\""+url+"?fs=1\" frameborder=\"0\" allowfullscreen></iframe>";
       
       
        WebEngine webEngine = w.getEngine();
        webEngine.loadContent(content_Url);
        
    
    } 
    
    private void insertarvideosV(){ 
     
        
        Dal dal = Dal.getDal();
        

        
        Collection<Actividad> datos = dal.find(Actividad.TODOS_ACTIVIDADES, new Object[]{}, Actividad.class);
        System.out.println("talla: "+datos.size());
        Iterator<Actividad> it = datos.iterator();
        Actividad actividad = null;
        
        
      ArrayList<HBox> listHB = new ArrayList<>();  
      HBox nuevaHBox = new HBox(); 
      nuevaHBox.setAlignment(Pos.CENTER_LEFT);
      nuevaHBox.setSpacing(5);
      vBoxGrande.getChildren().setAll(nuevaHBox);
      listHB.add(nuevaHBox);
        
    while(it.hasNext()){
        if( listHB.get(listHB.size()-1).getChildren().size()>3){
            nuevaHBox = new HBox(); 
            nuevaHBox.setAlignment(Pos.CENTER_LEFT);
            nuevaHBox.setSpacing(5);
            vBoxGrande.getChildren().add(nuevaHBox);
            listHB.add(nuevaHBox);
        }
        
        actividad=it.next();
        
        VBox newvBox = new VBox();
        Label newLabel = new Label();
        newLabel.setFont(new Font("Times New Roman",20));
        newLabel.setTextFill(Color.WHITESMOKE);
        newLabel.setText(actividad.getNombre());
        
        WebView newWeb = new WebView();
        cargarVideo(actividad.getVideo(), newWeb);
        newWeb.setPrefSize(318, 229);
        newWeb.setMinSize(318,229);
        
        newvBox.getChildren().add(newWeb);
        newvBox.getChildren().add(newLabel);
        
        listHB.get(listHB.size()-1).getChildren().add(newvBox);
        
    }
    
    }
    
    private void insertarvideosS(){ 
      
       Dal dal = Dal.getDal();
        

        
        Collection<Video> datos = dal.find(Video.VIDEObyUSUARIO, new Object[]{usuario.getId()}, Video.class);
        Iterator<Video> it = datos.iterator();
        Video video = null;  
        if(datos.size()>0) lblEmpty.setVisible(false);
        else return;
        
      ArrayList<HBox> listHB = new ArrayList<>();  
      HBox nuevaHBox = new HBox(); 
      nuevaHBox.setAlignment(Pos.CENTER_LEFT);
      nuevaHBox.setSpacing(5);
      vBoxGrandeS.getChildren().setAll(nuevaHBox);
      listHB.add(nuevaHBox);
      
      if(datos.size()>0) lblEmpty.setVisible(false);
      
    while(it.hasNext()){
        if( listHB.get(listHB.size()-1).getChildren().size()>3){
            nuevaHBox = new HBox(); 
            nuevaHBox.setAlignment(Pos.CENTER_LEFT);
            nuevaHBox.setSpacing(5);
            vBoxGrandeS.getChildren().add(nuevaHBox);
            listHB.add(nuevaHBox);
        }
        
        video=it.next();
        
        VBox newvBox = new VBox();
        Label newLabel = new Label();
        newLabel.setFont(new Font("Times New Roman",20));
        newLabel.setTextFill(Color.WHITESMOKE);
        newLabel.setText(video.getNombre());
        
        WebView newWeb = new WebView();
        cargarVideo(video.getURL(), newWeb);
        newWeb.setPrefSize(318, 229);
        newWeb.setMinSize(318,229);
        
        newvBox.getChildren().add(newWeb);
        newvBox.getChildren().add(newLabel);
        
        listHB.get(listHB.size()-1).getChildren().add(newvBox);
        
    }
    
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
      
    
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
    private void home() {
       if(!ScreensFramework.cargarPantalla(ScreensFramework.PANTALLA_PRINCIPAL))
        myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
    }
    
    //FIN METODOS BARRA BOTONES

    public void setMain(ScreensFramework main) {
    }

    

    

    @FXML
    private void goToMain(ActionEvent event) {
       myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
    }

    
    
}


