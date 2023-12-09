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
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jevon
 */
public class FXML_Display_Cust_ServiceController implements Initializable {

    @FXML
    private TableView<CustServiceModel> tbvcustservice;
    @FXML
    private Button btnclose;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsetelah;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnedit;
    @FXML
    private TextField txtcari;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }

    public void showdata(){
        ObservableList<CustServiceModel> data=FXMLDocumentController.dt_cust_service.Load();
        if(data!=null){            
            tbvcustservice.getColumns().clear();            
            tbvcustservice.getItems().clear();
            
            TableColumn col=new TableColumn("Id Customer Service");
            col.setCellValueFactory(new PropertyValueFactory<CustServiceModel, String>("idCustService"));
            tbvcustservice.getColumns().addAll(col);
            
            col=new TableColumn("Nama");
            col.setCellValueFactory(new PropertyValueFactory<CustServiceModel, String>("nama"));
            tbvcustservice.getColumns().addAll(col);
            
            col=new TableColumn("Tanggal Lahir");
            col.setCellValueFactory(new PropertyValueFactory<CustServiceModel, String>("tgllahir"));
            tbvcustservice.getColumns().addAll(col);
            
            col=new TableColumn("Usia");
            col.setCellValueFactory(new PropertyValueFactory<CustServiceModel, String>("umur"));
            tbvcustservice.getColumns().addAll(col);
            
            col=new TableColumn("Jenis Kelamin");
            col.setCellValueFactory(new PropertyValueFactory<CustServiceModel, String>("jeniskelamin"));
            tbvcustservice.getColumns().addAll(col);
            
            col=new TableColumn("Tempat Tinggal");
            col.setCellValueFactory(new PropertyValueFactory<CustServiceModel, String>("tempattinggal"));
            tbvcustservice.getColumns().addAll(col);

            tbvcustservice.setItems(data);
        }else {  
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvcustservice.getScene().getWindow().hide();
        }                
    }
    
    @FXML
    private void closeklik(ActionEvent event) {
        btnclose.getScene().getWindow().hide();
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvcustservice.getSelectionModel().selectFirst();        
        tbvcustservice.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvcustservice.getSelectionModel().selectAboveCell();       
        tbvcustservice.requestFocus();  
    }

    @FXML
    private void setelahklik(ActionEvent event) {
        tbvcustservice.getSelectionModel().selectBelowCell();        
        tbvcustservice.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvcustservice.getSelectionModel().selectLast();        
        tbvcustservice.requestFocus();   
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_Input_Cust_Service.fxml"));    
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
    private void hapusklik(ActionEvent event) {
        CustServiceModel s= new CustServiceModel();       
        s = tbvcustservice.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dt_cust_service.delete(s.getIdCustService())){
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
    private void editklik(ActionEvent event) {
        CustServiceModel s= new CustServiceModel();
        s = tbvcustservice.getSelectionModel().getSelectedItem();
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_Input_Cust_Service.fxml"));    
            Parent root = (Parent)loader.load();
            FXML_Input_Cust_ServiceController isidt = (FXML_Input_Cust_ServiceController)loader.getController();
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
    private void cariData(KeyEvent event) {
        CustServiceModel s = new CustServiceModel();
        String key = txtcari.getText();
        if(key!=""){
        ObservableList<CustServiceModel> data=FXMLDocumentController.dt_cust_service.CariCustService(key,key);
        if(data!=null){            
            tbvcustservice.getColumns().clear();
            tbvcustservice.getItems().clear();
            
            TableColumn col=new TableColumn("Id Customer Service");
            col.setCellValueFactory(new PropertyValueFactory<CustServiceModel, String>("idCustService"));
            tbvcustservice.getColumns().addAll(col);
            
            col=new TableColumn("Nama");
            col.setCellValueFactory(new PropertyValueFactory<CustServiceModel, String>("nama"));
            tbvcustservice.getColumns().addAll(col);
            
            col=new TableColumn("Tanggal Lahir");
            col.setCellValueFactory(new PropertyValueFactory<CustServiceModel, String>("tgllahir"));
            tbvcustservice.getColumns().addAll(col);
            
            col=new TableColumn("Usia");
            col.setCellValueFactory(new PropertyValueFactory<CustServiceModel, String>("umur"));
            tbvcustservice.getColumns().addAll(col);
            
            col=new TableColumn("Jenis Kelamin");
            col.setCellValueFactory(new PropertyValueFactory<CustServiceModel, String>("jeniskelamin"));
            tbvcustservice.getColumns().addAll(col);
            
            col=new TableColumn("Tempat Tinggal");
            col.setCellValueFactory(new PropertyValueFactory<CustServiceModel, String>("tempattinggal"));
            tbvcustservice.getColumns().addAll(col);
            
            tbvcustservice.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvcustservice.getScene().getWindow().hide();
        }            
            } else{
               showdata();
            }        
    }
}
