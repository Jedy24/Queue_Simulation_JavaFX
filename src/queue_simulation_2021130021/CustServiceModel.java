/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queue_simulation_2021130021;

import java.sql.Date;

/**
 *
 * @author Jevon
 */
public class CustServiceModel {
    private String idCustService, nama, jeniskelamin, tempattinggal;
    private Date tgllahir;
    private int umur;

    public String getIdCustService() {
        return idCustService;
    }

    public void setIdCustService(String idCustService) {
        this.idCustService = idCustService;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public String getTempattinggal() {
        return tempattinggal;
    }

    public void setTempattinggal(String tempattinggal) {
        this.tempattinggal = tempattinggal;
    }

    public Date getTgllahir() {
        return tgllahir;
    }

    public void setTgllahir(Date tgllahir) {
        this.tgllahir = tgllahir;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }
}
