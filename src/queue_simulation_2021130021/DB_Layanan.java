/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queue_simulation_2021130021;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jevon
 */
public class DB_Layanan {
    private LayananModel dt = new LayananModel();   
    private HashMap<String, DetilModel> dt2 = new HashMap<String, DetilModel>();
    
    public LayananModel getLayananModel(){ return(dt);}
    public void setLayananModel(LayananModel s){ dt=s;}
    
    public HashMap<String, DetilModel> getDetilModel(){ return(dt2);}
    public void setDetilModel(DetilModel d){ dt2.put(d.getNolayanan(), d);}
    
    public ObservableList<LayananModel> Load() {
        try {
            ObservableList<LayananModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select nolayanan, idCustService, tanggal from layanan");
            
            int i = 1;
            while (rs.next()) {
                LayananModel d = new LayananModel();
                d.setNolayanan(rs.getString("nolayanan"));
                d.setIdCustService(rs.getString("idCustService"));
                d.setTanggal(rs.getDate("tanggal"));

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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into layanan (nolayanan, idCustService, tanggal) values (?,?,?)");
            con.preparedStatement.setString(1, getLayananModel().getNolayanan());           
            con.preparedStatement.setString(2, getLayananModel().getIdCustService());
            con.preparedStatement.setDate(3, getLayananModel().getTanggal());            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update layanan set idCustService = ?, tanggal = ?  where  nolayanan = ? ");
            con.preparedStatement.setString(1, getLayananModel().getIdCustService());
            con.preparedStatement.setDate(2, getLayananModel().getTanggal());
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
    
    public String autonum(String tahun){         
        String tmp = "";
        try {          
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select max(nolayanan) as n from layanan where nolayanan like '"+tahun+"%'");
            while (rs.next()) {              
                tmp = tahun + String.format("%02d", Integer.parseInt(rs.getString("n").substring(4))+1);            		}
                con.tutupKoneksi();
                return tmp;
            } 
            catch (Exception e) {            
                e.printStackTrace();            
                return tmp;        
            }    
    }
    
    public boolean saveall() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.dbKoneksi.setAutoCommit(false);
            
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from layanan where nolayanan = ?");
            con.preparedStatement.setString(1, getLayananModel().getNolayanan());
            con.preparedStatement.executeUpdate();
            
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into layanan (nolayanan, idCustService , tanggal) values (?,?,?)");
            con.preparedStatement.setString(1, getLayananModel().getNolayanan());
            con.preparedStatement.setString(2, getLayananModel().getIdCustService());
            con.preparedStatement.setDate(3, getLayananModel().getTanggal());
            con.preparedStatement.executeUpdate();
            
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from detillayanan where nolayanan = ?");
            con.preparedStatement.setString(1, getLayananModel().getNolayanan());
            con.preparedStatement.executeUpdate();
            
            for(DetilModel sm:dt2.values()){
               con.preparedStatement = con.dbKoneksi.prepareStatement("insert into detillayanan (nolayanan, idCust, desclayanan) values (?,?,?)");
               con.preparedStatement.setString(1, sm.getNolayanan());
               con.preparedStatement.setString(2, sm.getIdCust());
               con.preparedStatement.setString(3, sm.getDesclayanan());
               con.preparedStatement.executeUpdate();
            }
            
            con.dbKoneksi.commit();
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
