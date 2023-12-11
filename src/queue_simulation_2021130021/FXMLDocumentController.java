/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package queue_simulation_2021130021;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Jevon
 */
public class FXMLDocumentController implements Initializable {
    
    public static DB_Cust_Service dt_cust_service = new DB_Cust_Service();
    public static DB_Cust dt_cust = new DB_Cust();
    public static DB_Layanan dt_layanan = new DB_Layanan();
    public static DB_Detil dt_detil = new DB_Detil();
    
    @FXML
    private MenuItem DisplayCustService;
    @FXML
    private MenuItem DisplayCust;
    @FXML
    private MenuItem InputCustService;
    @FXML
    private MenuItem InputCust;
    @FXML
    private MenuItem simulation;
    @FXML
    private MenuItem displaylayanan;
    @FXML
    private MenuItem reportcust;
    @FXML
    private MenuItem reportcustservice;
    @FXML
    private MenuItem reportlayanan;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void DisplayCustServiceClick(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_Display_Cust_Service.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){ 
            e.printStackTrace();
        }
    }

    @FXML
    private void DisplayCustClick(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_Display_Cust.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){ 
            e.printStackTrace();
        }
    }

    @FXML
    private void InputCustServiceClick(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_Input_Cust_Service.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){ 
            e.printStackTrace();   
        }
    }

    @FXML
    private void InputCustClick(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_Input_Cust.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){ 
            e.printStackTrace();   
        }
    }


    @FXML
    private void simulationklik(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_Simulasi.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){ 
            e.printStackTrace();   
        }
    }

    @FXML
    private void displayLayananKlik(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_Display_Layanan.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){ 
            e.printStackTrace();   
        }
    }  

    @FXML
    private void reportcustklik(ActionEvent event) {
        dt_cust.CetakReportCust();
    }

    @FXML
    private void reportcustserviceklik(ActionEvent event) {
        dt_cust_service.CetakReportCustService();
    }

    @FXML
    private void reportlayananklik(ActionEvent event) {
    }
}
