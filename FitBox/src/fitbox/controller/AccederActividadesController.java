/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.controller.dao.Dal;
import fitbox.model.Actividad;
import fitbox.model.Usuario;
import fitbox.view.ControlledScreen;
import fitbox.view.ScreensFramework;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author RUBEN
 */
public class AccederActividadesController implements Initializable, ControlledScreen {

    /**
     * Initializes the controller class.
     */
    private ScreensController myController;
    private ScreensFramework main;
    @FXML ListView listaCategorias;
    @FXML AnchorPane panelActividades;
    private ArrayList<Button> botonesActividades=new ArrayList<Button>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarCategorias();
        ScreensFramework.stage.setResizable(true);
        ScreensFramework.stage.setWidth(762);
        ScreensFramework.stage.setHeight(522);
        //myController.setSize(600, 400);
    }
    public void cargarCategorias(){
        ObservableList<String> data= FXCollections.observableArrayList();
        data.addAll("Torso","Cardio","Pierna","Abdominales","Hit","Estiramientos");
        listaCategorias.setItems(data);
        
        
    }
    @FXML
    public void cargarActividades(MouseEvent event){
        Dal dal = Dal.getDal();
       int indice=listaCategorias.getSelectionModel().getSelectedIndex();
       String elemento=(String)listaCategorias.getItems().get(indice);
       elemento=elemento.toLowerCase();
       
      Collection<Actividad> datos = dal.find(Actividad.TODOS_ACTIVIDADES,Actividad.class);
      Iterator<Actividad> it=datos.iterator();
        Actividad actividad=null;
        int i=0,j=0,contador=1;
        while(it.hasNext()) {
            if(i%2==0 && i!=0){j++;contador++;}
            actividad=it.next();
            Image im=new Image (getClass().getResource("/imagenes/"+elemento+i+".jpg").toExternalForm());
            ImageView imageView=new ImageView(im);
            Button boton=new Button(actividad.getNombre());
            boton.setLayoutX(20+(i*imageView.getScaleX()+(10*i)));
            boton.setLayoutY(20+(contador*imageView.getScaleY()+(j*boton.getScaleY())+(30*j)));
            boton.setOnMouseClicked(new EventHandler<MouseEvent>(){

                @Override
                public void handle(MouseEvent t) {
                    System.out.println(t.getButton().name()); //To change body of generated methods, choose Tools | Templates.
                }
            
            }
                    );
            botonesActividades.add(boton);
            imageView.setLayoutX(20+(i*imageView.getScaleX()+(10*i)));
            imageView.setLayoutY(20+(j*imageView.getScaleY()+(30*j)+(boton.getScaleY()*j)));
            panelActividades.getChildren().add(imageView);
            i++;
        
       }
    }
    @Override
    public void setMain(ScreensFramework main) {
        this.main = main;
        main.getStage().setResizable(true);

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
}
