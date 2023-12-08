/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package queue_simulation_2021130021;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jevon
 */
public class FXML_Input_Cust_ServiceController implements Initializable {

    @FXML
    private Button btnclose;
    @FXML
    private TextField txtidcustservice;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtumur;
    @FXML
    private TextField txttempat;
    @FXML
    private DatePicker dtplahir;
    @FXML
    private Button btnsave;
    @FXML
    private Button btncancel;
    @FXML
    private ComboBox<String> cmbjeniskelamin;

    /**
     * Initializes the controller class.
     */
    
    boolean editdata = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbjeniskelamin.setItems(FXCollections.observableArrayList("Pria", "Wanita"));
        cmbjeniskelamin.setEditable(false);
    }    

    public void execute(CustServiceModel d){
        if (!d.getIdCustService().isEmpty()) {
            editdata = true;
            txtidcustservice.setText(d.getIdCustService());
            txtnama.setText(d.getNama());
            
            Date tgllahir = d.getTgllahir();
            if (tgllahir != null) {
                dtplahir.getEditor().setText(new SimpleDateFormat("yyyy-MM-dd").format(tgllahir));
            }
            
            txtumur.setText(String.valueOf(d.getUmur()));
            
            String jeniskelamin = d.getJeniskelamin();
            if (jeniskelamin != null) {
                cmbjeniskelamin.setValue(jeniskelamin);
            }
            txttempat.setText(d.getTempattinggal());
            txtidcustservice.setEditable(false);
            txtnama.requestFocus();
        }
    }
        
    @FXML
    private void closeklik(ActionEvent event) {
        btnclose.getScene().getWindow().hide();
    }

    @FXML
    private void saveklik(ActionEvent event) {
        CustServiceModel n = new CustServiceModel();        
        n.setIdCustService(txtidcustservice.getText());
        n.setNama(txtnama.getText());
        
        java.sql.Date selectedDate = null;
        LocalDate localDate = dtplahir.getValue();
        if (localDate != null) {
            selectedDate = java.sql.Date.valueOf(localDate);
        }
        n.setTgllahir(selectedDate);
        
        int usia = Integer.parseInt(txtumur.getText());
        n.setUmur(usia);
        
        String jk = cmbjeniskelamin.getValue();
        n.setJeniskelamin(jk);
        
        n.setTempattinggal(txttempat.getText());
        
        
        FXMLDocumentController.dt_cust_service.setCustServiceModel(n);
        if(editdata){
            if(FXMLDocumentController.dt_cust_service.update()){
               Alert a = new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtidcustservice.setEditable(true);        
               cancelklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            } }else if(FXMLDocumentController.dt_cust_service.validasi(n.getIdCustService())<=0){
               if(FXMLDocumentController.dt_cust_service.insert()){
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
            txtidcustservice.requestFocus();
        }
    }

    @FXML
    private void cancelklik(ActionEvent event) {
        txtidcustservice.setText("");
        txtnama.setText("");
        dtplahir.setValue(null);
        txtumur.setText("");
        cmbjeniskelamin.setValue(null);
        txttempat.setText("");
        txtidcustservice.requestFocus();
    }
    
}
