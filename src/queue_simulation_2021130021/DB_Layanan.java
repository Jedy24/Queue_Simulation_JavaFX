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
public class DB_Layanan {
    private LayananModel dt = new LayananModel();
    public LayananModel getLayananModel(){
        return (dt);
    }
    public void setLayananModel(LayananModel s){
        dt = s;
    }
    
    public ObservableList<LayananModel>  Load() {
        try {
            ObservableList<LayananModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select nolayanan, desclayanan, idCust from layanan");
            
            int i = 1;
            while (rs.next()) {
                LayananModel d = new LayananModel();
                d.setNolayanan(rs.getString("nolayanan"));
                d.setDesclayanan(rs.getString("desclayanan"));
                d.setIdCust(rs.getString("idCust"));

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
    
    public int validasi(String nomor) {
        int val = 0;
        try {         
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from layanan where nolayanan = '" + nomor + "'");
            while (rs.next()) {                
                val = rs.getInt("jml");            
            }            
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        return val;
    }
    
    public int cariMax() {
        int val = 0;
        try {         
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select max(nolayanan) as nomax from layanan");
            while (rs.next()) {                
                val = rs.getInt("nomax");            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into layanan (nolayanan, desclayanan, idCust) values (?,?,?)");
            con.preparedStatement.setString(1, getLayananModel().getNolayanan());           
            con.preparedStatement.setString(2, getLayananModel().getDesclayanan());
            con.preparedStatement.setString(3, getLayananModel().getIdCust());            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from layanan where nolayanan = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update layanan set desclayanan = ?, idCust = ?  where  nolayanan = ? ");
            con.preparedStatement.setString(1, getLayananModel().getDesclayanan());
            con.preparedStatement.setString(2, getLayananModel().getIdCust());
            con.preparedStatement.setString(3, getLayananModel().getNolayanan());
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
