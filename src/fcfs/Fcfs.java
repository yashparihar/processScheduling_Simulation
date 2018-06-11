/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fcfs;

import javafx.application.Application;
//import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
//import javafx.scene.layout.FlowPane;

//import javafx.scene.layout.VBox;
//import javafx.stage.Modality;
//import static javafx.stage.Modality.APPLICATION_MODAL;
import javafx.stage.Stage;
//import javafx.stage.Window;


/**
 *
 * @author Yash
 */
public class Fcfs extends Application {
    
    
      @FXML
      TextField txtid = new TextField();
      
    @FXML
    RadioButton rad_fcfs = new RadioButton();
    RadioButton rad_sjf = new RadioButton();
    RadioButton rad_srt = new RadioButton();
    RadioButton rad_rr = new RadioButton();
    @FXML
    Button btn_start = new Button();
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
   
  
        Parent root = FXMLLoader.load(getClass().getResource("start_page.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Process Scheduling Simulator");
        stage.show();
        
             
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

  
}
