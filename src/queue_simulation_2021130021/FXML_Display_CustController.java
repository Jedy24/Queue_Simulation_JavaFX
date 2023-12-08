/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package queue_simulation_2021130021;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jevon
 */
public class FXML_Display_CustController implements Initializable {

    @FXML
    private TextField txtcari;
    @FXML
    private Button btnedit;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnsetelah;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnclose;
    @FXML
    private TableView<CustModel> tbvcust;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    
    public void showdata(){
        ObservableList<CustModel> data=FXMLDocumentController.dt_cust.Load();
        if(data!=null){            
            tbvcust.getColumns().clear();            
            tbvcust.getItems().clear();
            
            TableColumn col=new TableColumn("Id Customer");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("idCust"));
            tbvcust.getColumns().addAll(col);
            
            col=new TableColumn("No. Urut");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("noUrut"));
            tbvcust.getColumns().addAll(col);
            
            col=new TableColumn("Nama");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("nama"));
            tbvcust.getColumns().addAll(col);
            
            col=new TableColumn("Usia");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("umur"));
            tbvcust.getColumns().addAll(col);

            tbvcust.setItems(data);
        }else {  
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvcust.getScene().getWindow().hide();
        }                
    }    
    
    @FXML
    private void cariData(javafx.scene.input.KeyEvent event) {
        CustModel s = new CustModel();
        String key = txtcari.getText();
        if(key!=""){
        ObservableList<CustModel> data=FXMLDocumentController.dt_cust.CariCust(key,key);
        if(data!=null){            
            tbvcust.getColumns().clear();
            tbvcust.getItems().clear();
            
            TableColumn col=new TableColumn("Id Customer");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("idCust"));
            tbvcust.getColumns().addAll(col);
            
            col=new TableColumn("No. Urut");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("noUrut"));
            tbvcust.getColumns().addAll(col);
            
            col=new TableColumn("Nama");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("nama"));
            tbvcust.getColumns().addAll(col);
            
            col=new TableColumn("Usia");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("umur"));
            tbvcust.getColumns().addAll(col);
            
            tbvcust.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvcust.getScene().getWindow().hide();
        }            
            } else{
               showdata();
            }  
    }

    @FXML
    private void editklik(ActionEvent event) {
        CustModel s= new CustModel();
        s = tbvcust.getSelectionModel().getSelectedItem();
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_Input_Cust.fxml"));    
            Parent root = (Parent)loader.load();
            FXML_Input_CustController isidt = (FXML_Input_CustController)loader.getController();
            isidt.execute(s);                
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e){   
            e.printStackTrace(); 
        }
        showdata();  
        awalklik(event);
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        CustModel s= new CustModel();       
        s = tbvcust.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dt_cust.delete(s.getIdCust())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();           
           awalklik(event);       
        }   
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_Input_Cust.fxml"));    
            Parent root = (Parent)loader.load();        
            Scene scene = new Scene(root);        
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);        
            stg.setIconified(false);        
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e){   
            e.printStackTrace();
        }
        showdata();        
        awalklik(event);
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvcust.getSelectionModel().selectLast();        
        tbvcust.requestFocus(); 
    }

    @FXML
    private void setelahklik(ActionEvent event) {
        tbvcust.getSelectionModel().selectBelowCell();        
        tbvcust.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvcust.getSelectionModel().selectAboveCell();       
        tbvcust.requestFocus();  
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvcust.getSelectionModel().selectFirst();        
        tbvcust.requestFocus();
    }

    @FXML
    private void closeklik(ActionEvent event) {
        btnclose.getScene().getWindow().hide();
    }
}
