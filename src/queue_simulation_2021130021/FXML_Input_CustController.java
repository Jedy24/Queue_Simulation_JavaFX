/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package queue_simulation_2021130021;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jevon
 */
public class FXML_Input_CustController implements Initializable {

    @FXML
    private Button btncancel;
    @FXML
    private Button btnsave;
    @FXML
    private TextField txtumur;
    @FXML
    private TextField txturut;
    @FXML
    private TextField txtidcust;
    @FXML
    private Button btnclose;
    @FXML
    private TextField txtnama;

    /**
     * Initializes the controller class.
     */
    
    boolean editdata = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void execute(CustModel d){
        if (!d.getIdCust().isEmpty()) {
            editdata = true;
            txtidcust.setText(d.getIdCust());
            txturut.setText(String.valueOf(d.getNoUrut()));
            txtnama.setText(d.getNama());
            txtumur.setText(String.valueOf(d.getUmur()));
            
            txtidcust.setEditable(false);
            txturut.requestFocus();
        }
    }
    
    @FXML
    private void cancelklik(ActionEvent event) {
        txtidcust.setText("");
        txturut.setText("");
        txtnama.setText("");
        txtumur.setText("");
        txtidcust.requestFocus();
    }

    @FXML
    private void saveklik(ActionEvent event) {
        CustModel n = new CustModel();        
        n.setIdCust(txtidcust.getText());
        
        int noUrut = Integer.parseInt(txturut.getText());
        n.setNoUrut(noUrut);
        
        n.setNama(txtnama.getText());
        
        int usia = Integer.parseInt(txtumur.getText());
        n.setUmur(usia);  
        
        FXMLDocumentController.dt_cust.setCustModel(n);
        if(editdata){
            if(FXMLDocumentController.dt_cust.update()){
               Alert a = new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtidcust.setEditable(true);        
               cancelklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            } }else if(FXMLDocumentController.dt_cust.validasi(n.getIdCust())<=0){
               if(FXMLDocumentController.dt_cust.insert()){
                    Alert a = new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
                    a.showAndWait();            
                    cancelklik(event);
            } else {
               Alert a = new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtidcust.requestFocus();
        }
    }

    @FXML
    private void closeklik(ActionEvent event) {
        btnclose.getScene().getWindow().hide();
    }
    
}
