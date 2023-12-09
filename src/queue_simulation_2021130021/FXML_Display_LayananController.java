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
    private TableView<?> tbvdetil;
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
    }    
    
    public void showdata(){
        ObservableList<LayananModel> data=FXMLDocumentController.dt_layanan.Load();
        if(data!=null){          
            tbvlayanan.getColumns().clear();
            tbvlayanan.getItems().clear();
            
            TableColumn col = new TableColumn("No. Layanan");
            col.setCellValueFactory(new PropertyValueFactory<LayananModel, String>("nolayanan"));
            tbvlayanan.getColumns().addAll(col);
            
            col=new TableColumn("Deskripsi Layanan");
            col.setCellValueFactory(new PropertyValueFactory<LayananModel, String>("desclayanan"));
            tbvlayanan.getColumns().addAll(col);
            
            col=new TableColumn("ID Customer");
            col.setCellValueFactory(new PropertyValueFactory<LayananModel, String>("idCust"));
            tbvlayanan.getColumns().addAll(col);
            
            tbvlayanan.setItems(data);
                                              
        }else {  
            Alert a = new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvlayanan.getScene().getWindow().hide();;
        }                
    }
    
    @FXML
    private void akhirklik(ActionEvent event) {
    }

    @FXML
    private void setelahklik(ActionEvent event) {
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
    }

    @FXML
    private void awalklik(ActionEvent event) {
    }

    @FXML
    private void hapusklik(ActionEvent event) {
    }
    
}
