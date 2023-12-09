/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package queue_simulation_2021130021;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Jevon
 */
public class FXML_Display_LayananController implements Initializable {

    @FXML
    private Button btnclose;
    @FXML
    private TableView<LayananModel> tbvlayanan;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnsetelah;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnawal; 
    @FXML
    private Button btnhapus;
    @FXML
    private TableView<DetilModel> tbvdetil;
    @FXML
    private TextField txtnolayanan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
        tbvlayanan.getSelectionModel().selectFirst();
        txtnolayanan.setText(tbvlayanan.getSelectionModel().getSelectedItem().getNolayanan());
        showdatadetil();
    }    
    
    public void showdata(){
        ObservableList<LayananModel> data=FXMLDocumentController.dt_layanan.Load();
        if(data!=null){          
            tbvlayanan.getColumns().clear();
            tbvlayanan.getItems().clear();
            
            TableColumn col = new TableColumn("No. Layanan");
            col.setCellValueFactory(new PropertyValueFactory<LayananModel, String>("nolayanan"));
            tbvlayanan.getColumns().addAll(col);
            
            col = new TableColumn("ID Customer Service");
            col.setCellValueFactory(new PropertyValueFactory<LayananModel, String>("idCustService"));
            tbvlayanan.getColumns().addAll(col);
            
            col = new TableColumn("Tanggal Layanan");
            col.setCellValueFactory(new PropertyValueFactory<LayananModel, String>("tanggal"));
            tbvlayanan.getColumns().addAll(col);
            
            tbvlayanan.setItems(data);
                                              
        } else {  
            Alert a = new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvlayanan.getScene().getWindow().hide();;
        }                
    }
    
    public void showdatadetil(){
        ObservableList<DetilModel> data = FXMLDocumentController.dt_detil.Load(txtnolayanan.getText());
        if(data!=null){  
            tbvdetil.getColumns().clear();
            tbvdetil.getItems().clear();
            
            TableColumn col = new TableColumn("Nomor Layanan");
            col.setCellValueFactory(new PropertyValueFactory<DetilModel, String>("nolayanan"));
            tbvdetil.getColumns().addAll(col);
            
            col = new TableColumn("ID Customer");
            col.setCellValueFactory(new PropertyValueFactory<DetilModel, String>("idCust"));
            tbvdetil.getColumns().addAll(col);
            
            col = new TableColumn("Nama Customer");
            col.setCellValueFactory(new PropertyValueFactory<DetilModel, String>("nama"));
            tbvdetil.getColumns().addAll(col);
            
            col = new TableColumn("Deskripsi Layanan");
            col.setCellValueFactory(new PropertyValueFactory<DetilModel, String>("desclayanan"));
            tbvdetil.getColumns().addAll(col);

            tbvdetil.setItems(data);
                                            
        } else {  
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvdetil.getScene().getWindow().hide();
        }   
        tbvdetil.getSelectionModel().selectLast();
    }
    
    @FXML
    private void akhirklik(ActionEvent event) {
        tbvlayanan.getSelectionModel().selectLast(); 
        tbvlayanan.requestFocus();  
        txtnolayanan.setText(tbvlayanan.getSelectionModel().getSelectedItem().getNolayanan());
        showdatadetil();
    }

    @FXML
    private void setelahklik(ActionEvent event) {
        tbvlayanan.getSelectionModel().selectBelowCell(); 
        tbvlayanan.requestFocus();  
        txtnolayanan.setText(tbvlayanan.getSelectionModel().getSelectedItem().getNolayanan());
        showdatadetil();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvlayanan.getSelectionModel().selectAboveCell(); 
        tbvlayanan.requestFocus();  
        txtnolayanan.setText(tbvlayanan.getSelectionModel().getSelectedItem().getNolayanan());
        showdatadetil();
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvlayanan.getSelectionModel().selectFirst(); 
        tbvlayanan.requestFocus();  
        txtnolayanan.setText(tbvlayanan.getSelectionModel().getSelectedItem().getNolayanan());
        showdatadetil();
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        Alert a=new Alert(Alert.AlertType.CONFIRMATION, "Data Layanan akan dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        
        if(a.getResult()==ButtonType.YES){
            FXMLDocumentController.dt_detil.deleteall(txtnolayanan.getText());
            FXMLDocumentController.dt_layanan.delete(txtnolayanan.getText());
                      
            Alert b=new Alert(Alert.AlertType.INFORMATION, "Data Layanan berhasil dihapus", ButtonType.OK);
            b.showAndWait();
            } else {
                Alert b=new Alert(Alert.AlertType.ERROR, "Data Layanan gagal dihapus", ButtonType.OK);
                b.showAndWait();               
            }    
        showdata();
        showdatadetil(); 
    }

    @FXML
    private void klikdata(MouseEvent event) {
        txtnolayanan.setText(tbvlayanan.getSelectionModel().getSelectedItem().getNolayanan());
        showdatadetil();
    }

    @FXML
    private void closeklik(ActionEvent event) {
        btnclose.getScene().getWindow().hide();
    }
    
}
