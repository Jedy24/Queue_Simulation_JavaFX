/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package queue_simulation_2021130021;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jevon
 */
public class FXML_SimulasiController implements Initializable {

    @FXML
    private TextField txtwaktu;
    @FXML
    private Button btnclose;
    @FXML
    private ListView<String> listcust;
    @FXML
    private ListView<String> listcustservice;
    @FXML
    private Button btnsimulasi;
    @FXML
    private TextArea txareahasil;

    /**
     * Initializes the controller class.
     */
    
    Random acak = new Random();
    Timer t = new Timer();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    
    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; //Inisialisasi character
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            sb.append(randomChar);
        }
        return sb.toString();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txareahasil.setWrapText(true);
        listcust.getItems().clear();
        listcustservice.getItems().clear();

        ObservableList<CustModel> data = FXMLDocumentController.dt_cust.Load();
        if (data.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data Customer kosong", ButtonType.OK);
            a.showAndWait();
            txareahasil.getScene().getWindow().hide();
        } else {
            for (int i = 0; i < data.size(); i++) {
                listcust.getItems().addAll(data.get(i).getIdCust() + " - " + data.get(i).getNama());
            }
            ObservableList<CustServiceModel> data2 = FXMLDocumentController.dt_cust_service.Load();
            if (data.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data Customer Service kosong", ButtonType.OK);
                a.showAndWait();
                txareahasil.getScene().getWindow().hide();
            } else {
                for (int i = 0; i < data2.size(); i++) {
                    listcustservice.getItems().addAll(data2.get(i).getIdCustService() + " - " + data2.get(i).getNama());
                }
            }
        } 
    }    

    @FXML
    private void closeklik(ActionEvent event) {
        btnclose.getScene().getWindow().hide();
    }

    @FXML
    private void simulasiklik(ActionEvent event) {
        int wkt = Integer.parseInt(txtwaktu.getText());        
        t.scheduleAtFixedRate(new TimerTask(){           
            @Override
            public void run(){
                Platform.runLater(() -> {
                   int p = acak.nextInt(listcust.getItems().size());
                   int b = acak.nextInt(listcustservice.getItems().size());
                   String desc = generateRandomString(10);

                   LayananModel j = new LayananModel();
                   j.setNolayanan(FXMLDocumentController.dt_layanan.autonum(sdf.format(new java.util.Date())) );        
                   j.setIdCustService(listcust.getItems().get(p).substring(0, 6));
                   j.setTanggal(Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

                   DetilModel s = new DetilModel();        
                   s.setNolayanan(j.getNolayanan());
                   s.setIdCust(listcustservice.getItems().get(b).substring(0, 6));        
                   s.setDesclayanan(desc);
                   
                   FXMLDocumentController.dt_layanan.setLayananModel(j);
                   FXMLDocumentController.dt_layanan.setDetilModel(s);
                   if(FXMLDocumentController.dt_layanan.saveall()){
                      txareahasil.setText(txareahasil.getText()+
                      j.getNolayanan()+" - "+ listcust.getItems().get(p)+" : "+
                      listcustservice.getItems().get(b)+" "+String.format("%s\n", desc));
//                      txareahasil.setScrollTop(Double.MAX_VALUE);        
                   }
             });
            }        
        }, 0, 60000*wkt);   
    }
}
