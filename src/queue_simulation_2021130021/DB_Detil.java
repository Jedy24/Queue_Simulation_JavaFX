/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queue_simulation_2021130021;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jevon
 */
public class DB_Detil {
    private DetilModel dt = new DetilModel();
    public DetilModel getDetilModel(){
        return (dt);
    }
    public void setDetilModel(DetilModel s){
        dt = s;
    }
    
    public ObservableList<DetilModel> Load(String kode) {     
        try {
            ObservableList<DetilModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select d.nolayanan, d.desclayanan, c.idCust, c.nama " + 
                    "from detillayanan d join customer c on(d.idCust = c.idCust) WHERE nolayanan LIKE '" + kode + "'");
            int i = 1;               
            while (rs.next()) {
                DetilModel d=new DetilModel();
                d.setNolayanan(rs.getString("nolayanan"));                
                d.setIdCust(rs.getString("idCust"));
                d.setNama(rs.getString("nama"));
                d.setDesclayanan(rs.getString("desclayanan"));  
                
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from detillayanan where nolayanan = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into detillayanan (nolayanan, idCust, desclayanan) values (?,?,?)");
            con.preparedStatement.setString(1, getDetilModel().getNolayanan());           
            con.preparedStatement.setString(2, getDetilModel().getIdCust());
            con.preparedStatement.setString(3, getDetilModel().getDesclayanan());            
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
    
    public boolean delete(String nomor, String kode) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from detillayanan where nolayanan  = ? and idCust = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.setString(2, kode);
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }
    
    public boolean deleteall(String nomor) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from detillayanan where nolayanan  = ?");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update detillayanan set idCust = ?, desclayanan = ?  where  nolayanan = ? ");
            con.preparedStatement.setString(1, getDetilModel().getIdCust());
            con.preparedStatement.setString(2, getDetilModel().getDesclayanan());
            con.preparedStatement.setString(3, getDetilModel().getNolayanan());
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
