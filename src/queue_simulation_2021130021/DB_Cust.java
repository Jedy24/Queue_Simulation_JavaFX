/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queue_simulation_2021130021;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Jevon
 */
public class DB_Cust {
    private CustModel dt = new CustModel();
    public CustModel getCustModel(){
        return (dt);
    }
    public void setCustModel(CustModel s){
        dt = s;
    }
    
    public ObservableList<CustModel>  Load() {
        try {
            ObservableList<CustModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select idCust, noUrut, nama, umur from customer");
            
            int i = 1;
            while (rs.next()) {
                CustModel d=new CustModel();
                d.setIdCust(rs.getString("idCust"));
                d.setNoUrut(rs.getInt("noUrut"));
                d.setNama(rs.getString("nama"));
                d.setUmur(rs.getInt("umur"));                
                
                tableData.add(d);                
                i++;            
            }
            con.tutupKoneksi();            
            return tableData;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
        }
    }
    
    public ObservableList<CustModel>  CariCust(String kode, String nama) {
        try {    
            ObservableList<CustModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from customer WHERE idCust LIKE '" + kode + "%' OR nama LIKE '" + nama + "%'");
        
            int i = 1;
            while(rs.next()){
                CustModel d = new CustModel();
                d.setIdCust(rs.getString("idCust"));
                d.setNoUrut(rs.getInt("noUrut"));
                d.setNama(rs.getString("nama"));
                d.setUmur(rs.getInt("umur"));

            tableData.add(d);
            i++;
            }
            con.tutupKoneksi();
            return tableData;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public int validasi(String nomor) {
        int val = 0;
        try {         
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from customer where idCust = '" + nomor + "'");
            while (rs.next()) {                
                val = rs.getInt("jml");            
            }            
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        return val;
    }
    
    public boolean insert() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {       
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into customer (idCust, noUrut, nama, umur) values (?,?,?,?)");
            con.preparedStatement.setString(1, getCustModel().getIdCust());           
            con.preparedStatement.setInt(2, getCustModel().getNoUrut());
            con.preparedStatement.setString(3, getCustModel().getNama());           
            con.preparedStatement.setInt(4, getCustModel().getUmur());                
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
    }
    
    public boolean delete(String nomor) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from customer where idCust = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }

    public boolean update() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update customer set noUrut = ?, nama = ?, umur = ? where  idCust = ? ");
            con.preparedStatement.setInt(1, getCustModel().getNoUrut());
            con.preparedStatement.setString(2, getCustModel().getNama());
            con.preparedStatement.setInt(3, getCustModel().getUmur());
            con.preparedStatement.setString(4, getCustModel().getIdCust());
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
    } 
    
    public void CetakReportCust(){
        Koneksi con = new Koneksi();        
        String is = "./src/queue_simulation_2021130021/ReportCustomer.jasper";   
        Map map = new HashMap(); 
        map.put("judul", "Report Data Customer");
        con.bukaKoneksi();        
        try{
           JasperPrint jasperPrint = JasperFillManager.fillReport(is, map,  con.dbKoneksi);
           JasperViewer.viewReport(jasperPrint, false);
        }
        catch(Exception ex){
            ex.printStackTrace();
        } con.tutupKoneksi();    
    }
}
