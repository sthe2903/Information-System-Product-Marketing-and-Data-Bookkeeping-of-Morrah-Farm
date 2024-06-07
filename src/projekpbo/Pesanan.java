/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekpbo;

/**
 *
 * @author mahes
 */
public class Pesanan {
    int id_pesanan;
    String namaPembeli;
    String namaProduk;
    int jumlahPembelian;
    int totalHarga;
    String status;

    public Pesanan(int id_pesanan, String namaPembeli, String namaProduk, int jumlahPembelian, int totalHarga, String status) {
        this.id_pesanan = id_pesanan;
        this.namaPembeli = namaPembeli;
        this.namaProduk = namaProduk;
        this.jumlahPembelian = jumlahPembelian;
        this.totalHarga = totalHarga;
        this.status = status;
    }

    public int getId_pesanan() {
        return id_pesanan;
    }

    public void setId_pesanan(int id_pesanan) {
        this.id_pesanan = id_pesanan;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public int getJumlahPembelian() {
        return jumlahPembelian;
    }

    public void setJumlahPembelian(int jumlahPembelian) {
        this.jumlahPembelian = jumlahPembelian;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }
   
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
