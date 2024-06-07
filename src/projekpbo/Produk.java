/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekpbo;

/**
 *
 * @author mahes
 */
public class Produk {
    private int id_produk;
    private String nama;
    private int stok;
    private int harga;
    private String deskripsi;

    public Produk(){
        
    };

    public Produk(int id_produk) {
        this.id_produk = id_produk;
    }


    public Produk(String nama, int harga, int stok, String deskripsi) {
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }

    public Produk(int id, String nama, int stok, int harga, String deskripsi) {
        this.id_produk = id;
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }
    
    
    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    
    public int getId() {
        return id_produk;
    }

    public String getNama() {
        return nama;
    }

    public int getStok() {
        return stok;
    }

    public int getHarga() {
        return harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

}
