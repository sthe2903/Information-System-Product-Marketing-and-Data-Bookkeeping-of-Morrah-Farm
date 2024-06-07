/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekpbo;

import java.time.LocalDate;

/**
 *
 * @author mahes
 */
public class TugasKaryawan {
    private int id;
    private String username;
    private String role;
    private LocalDate waktu;
    private String deskripsi;
    private String status;
    
    
    public TugasKaryawan(int id, String username, String role, LocalDate waktu, String deskripsi, String status) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.waktu = waktu;
        this.deskripsi = deskripsi;
        this.status = status;
    }

    public TugasKaryawan(String username, LocalDate waktu, String deskripsi, String status) {
        this.username = username;
        this.waktu = waktu;
        this.deskripsi = deskripsi;
        this.status = status;
    }
    
    public TugasKaryawan(int id, String username, String role, LocalDate waktu, String deskripsi) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.waktu = waktu;
        this.deskripsi = deskripsi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getWaktu() {
        return waktu;
    }

    public void setWaktu(LocalDate waktu) {
        this.waktu = waktu;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
