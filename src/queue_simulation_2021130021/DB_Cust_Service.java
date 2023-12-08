/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queue_simulation_2021130021;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jevon
 */
public class DB_Cust_Service {
    private CustServiceModel dt = new CustServiceModel();
    public CustServiceModel getCustServiceModel(){
        return (dt);
    }
    public void setCustServiceModel(CustServiceModel s){
        dt = s;
    }
    
    public ObservableList<CustServiceModel>  Load() {
        try {
            ObservableList<CustServiceModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select idCustService, nama, tgllahir, umur, jeniskelamin, tempattinggal from customerservice");
            
            int i = 1;
            while (rs.next()) {
                CustServiceModel d=new CustServiceModel();
                d.setIdCustService(rs.getString("idCustService"));                
                d.setNama(rs.getString("nama"));
                d.setTgllahir(rs.getDate("tgllahir"));
                d.setUmur(rs.getInt("umur"));
                d.setJeniskelamin(rs.getString("jeniskelamin"));
                d.setTempattinggal(rs.getString("tempattinggal"));

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
    
    public ObservableList<CustServiceModel>  CariCustService(String kode, String nama) {
        try {    
            ObservableList<CustServiceModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from customerservice WHERE idCustService LIKE '" + kode + "%' OR nama LIKE '" + nama + "%'");
        int i = 1;
        while(rs.next()){
            CustServiceModel d = new CustServiceModel();
            d.setIdCustService(rs.getString("idCustService"));
            d.setNama(rs.getString("nama"));
            d.setTgllahir(rs.getDate("tgllahir"));
            d.setUmur(rs.getInt("umur"));
            d.setJeniskelamin(rs.getString("jeniskelamin"));
            d.setTempattinggal(rs.getString("tempattinggal"));

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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from customerservice where idCustService = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into customerservice (idCustService, nama, tgllahir, umur, jeniskelamin, tempattinggal) values (?,?,?,?,?,?)");
            con.preparedStatement.setString(1, getCustServiceModel().getIdCustService());           
            con.preparedStatement.setString(2, getCustServiceModel().getNama());
            con.preparedStatement.setDate(3, getCustServiceModel().getTgllahir());           
            con.preparedStatement.setInt(4, getCustServiceModel().getUmur());           
            con.preparedStatement.setString(5, getCustServiceModel().getJeniskelamin());        
            con.preparedStatement.setString(6, getCustServiceModel().getTempattinggal());        
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from customerservice where idCustService = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update customerservice set nama = ?, tgllahir = ?, umur = ?, jeniskelamin = ?, tempattinggal = ?  where  idCustService = ? ");
            con.preparedStatement.setString(1, getCustServiceModel().getNama());
            con.preparedStatement.setDate(2, getCustServiceModel().getTgllahir());
            con.preparedStatement.setInt(3, getCustServiceModel().getUmur());
            con.preparedStatement.setString(4, getCustServiceModel().getJeniskelamin());
            con.preparedStatement.setString(5, getCustServiceModel().getTempattinggal());
            con.preparedStatement.setString(6, getCustServiceModel().getIdCustService());
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
}
