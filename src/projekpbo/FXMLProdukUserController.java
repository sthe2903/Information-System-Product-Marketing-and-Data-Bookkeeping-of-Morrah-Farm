/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projekpbo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import static projekpbo.FXMLLoginController.IDuser;
import static projekpbo.FXMLLoginController.Namausername;

/**
 * FXML Controller class
 *
 * @author mahes
 */
public class FXMLProdukUserController implements Initializable {
    @FXML
    private TableView<Produk> tvProduk;
    @FXML
    private TableColumn<Produk, String> tcNama;
    @FXML
    private TableColumn<Produk, Integer> tcStok;
    @FXML
    private TableColumn<Produk, String> tcDeskripsi;
    @FXML
    private TableColumn<Produk, Integer> tcHarga;
    @FXML
    private Label lblDeskripsi;
    @FXML
    private Label lblHarga;
    @FXML
    private Label lblNama;
    @FXML
    private Button menuBack;
    @FXML
    private Button menuLogout;
    @FXML
    private Button btnBeli;
    @FXML
    private TextField tfStok;
    @FXML
    private Label lblIdUser;
    @FXML
    private Label lblUsername;
    @FXML
    private Label produkID;


    
    String strUsername;
//    int idProdukk;
    int jumlahPembelian;
    int total_harga;

    
    private void showProduk() throws SQLException{
        ObservableList<Produk> list = getProdukList();
        
        tcNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        tcHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        tcStok.setCellValueFactory(new PropertyValueFactory<>("stok"));
        tcDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));

        tvProduk.setItems(list);
    }
    


    
    public ObservableList<Produk> getProdukList() throws SQLException{
        ObservableList<Produk> produkList = FXCollections.observableArrayList();
        Connection conn = dbConnection.getDBConnection();
        String query = "SELECT * FROM produk";
        PreparedStatement pst;
        ResultSet rs;
        try{
                 pst = conn.prepareStatement(query);
                 rs = pst.executeQuery();
                 Produk produk;
                 while(rs.next()){
                    produk = new Produk(rs.getInt("id_produk"),rs.getString("nama"), rs.getInt("stok"),rs.getInt("harga"), rs.getString("deskripsi"));
                    produkList.add(produk);
                }


             }catch(Exception ex){
                 ex.printStackTrace();
             }
        return produkList;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String idUser = FXMLLoginController.IDuser;
        lblIdUser.setText(idUser);
        
        strUsername = FXMLLoginController.Namausername;
        lblUsername.setText(strUsername);
        
        try {
            showProduk();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTugasKaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // set item click event handler
        tvProduk.setOnMouseClicked(event -> {
            // ambil item yang dipilih dari tabel
            Produk selectedProduk = tvProduk.getSelectionModel().getSelectedItem();
            
            // tampilkan data dari item yang dipilih ke dalam label dan gambar
            lblNama.setText(selectedProduk.getNama());
            lblDeskripsi.setText(selectedProduk.getDeskripsi());
            tfStok.setText(String.valueOf(selectedProduk.getStok()));
            lblHarga.setText(String.valueOf(selectedProduk.getHarga()));
            produkID.setText(String.valueOf(selectedProduk.getId()));
//            ivGambar.setImage(new Image(new ByteArrayInputStream(selectedProduk.getGambar())));
        });
    }

    @FXML
    private void handleActionBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLUserr.fxml"));
        Parent root = loader.load();

        // Buat scene baru
        Scene scene = new Scene(root);

        // Get the stage information
        Stage stage = (Stage) menuBack.getScene().getWindow();

        // Set scene baru ke stage
        stage.setScene(scene);
        stage.setTitle("Morrah Farm - Halaman User");
        // Tampilkan stage
        stage.show();

        // Ambil ukuran layar
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Ambil ukuran tampilan JavaFX
        double sceneWidth = stage.getWidth();
        double sceneHeight = stage.getHeight();

        // Hitung posisi X dan Y agar tampilan JavaFX berada di tengah layar
        double posX = (screenBounds.getWidth() - sceneWidth) / 2;
        double posY = (screenBounds.getHeight() - sceneHeight) / 2;

        // Set posisi tampilan JavaFX pada layar
        stage.setX(posX);
        stage.setY(posY);
    }

    @FXML
    private void handleActionLogout(ActionEvent event) throws IOException {
        // buat sebuah dialog baru
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Logout");
        alert.setHeaderText("Apakah Anda yakin ingin keluar dari aplikasi?");
        alert.setContentText("Pilih 'OK' untuk logout, atau 'Cancel' untuk batal.");

        // tampilkan dialog dan tunggu hingga pengguna memberikan respons
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
            Parent root = loader.load();

            // Buat scene baru
            Scene scene = new Scene(root);

            // Get the stage information
            Stage stage = (Stage) menuLogout.getScene().getWindow();

            // Set scene baru ke stage
            stage.setScene(scene);
            stage.setTitle("Morrah Farm - Halaman Utama");
            // Tampilkan stage
            stage.show();

            // Ambil ukuran layar
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Ambil ukuran tampilan JavaFX
            double sceneWidth = stage.getWidth();
            double sceneHeight = stage.getHeight();

            // Hitung posisi X dan Y agar tampilan JavaFX berada di tengah layar
            double posX = (screenBounds.getWidth() - sceneWidth) / 2;
            double posY = (screenBounds.getHeight() - sceneHeight) / 2;

            // Set posisi tampilan JavaFX pada layar
            stage.setX(posX);
            stage.setY(posY);
        } else {
            // pengguna memilih untuk batal logout
            alert.close();
        }
    }
    
//    private void inputPembelian(){
//        int idUser = Integer.parseInt(lblIdUser.getText());
//        int idProduk = idProdukk;
//        int jlhPembelian = Integer.parseInt(tfStok.getText());
//        int totHarga = totalHarga;
//
//        if (validateInput(nama, stok, harga, deskripsi)) {
//
//            try {
//                // Membuka koneksi ke database
//                Connection conn = dbConnection.getDBConnection();
//
//                // Membuat query untuk menyimpan data registrasi ke dalam database
//                String query = "INSERT INTO pembelian (user_id, produk_id, pembelian_jumlah, harga_total, status) VALUES (?, ?, ?, ?, ?)";
//                PreparedStatement statement = conn.prepareStatement(query);
//                statement.setString(1, nama);
//                statement.setInt(2, stok);
//                statement.setString(3, deskripsi);
//                statement.setInt(4, harga);
//
//                // Eksekusi query
//                statement.executeUpdate();
//                showProduk();
////
////                // Menutup koneksi ke database
////                statement.close();
////                conn.close();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        } else {
//            // Tampilkan pesan error atau lakukan tindakan sesuai kebutuhan
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setContentText("Penambahan Produk gagal. Mohon isi semua field dengan benar.");
//            alert.showAndWait();
//        }
//    }
    
//    private void getIdProduk() throws SQLException{
//    Connection connection = null;
//    PreparedStatement preparedStatement = null;
//    ResultSet resultSet = null;
//    try {
//        // Koneksi ke database
//        connection = dbConnection.getDBConnection();
//
//        // Query ke tabel pengguna dengan username dan password
//        String query = "SELECT * FROM produk";
//        preparedStatement = connection.prepareStatement(query);
//        resultSet = preparedStatement.executeQuery();
//
//        // Periksa hasil query apakah ada pengguna yang sesuai
//        if (resultSet.next()) {
//            idProdukk = resultSet.getInt("id_produk");
//        }
//    } finally {
//        // Tutup semua resource
//        if (resultSet != null) {
//            resultSet.close();
//        }
//        if (preparedStatement != null) {
//            preparedStatement.close();
//        }
//        if (connection != null) {
//            connection.close();
//        }
//    }
//    }

        
    @FXML
    private void actionHandleBeli(ActionEvent event) throws IOException {
        Produk selectedProduk = tvProduk.getSelectionModel().getSelectedItem();
        int stokTersedia = selectedProduk.getStok();
        int stokDibeli = Integer.parseInt(tfStok.getText());
        int idProdukk = Integer.parseInt(produkID.getText());

        if (stokDibeli <= stokTersedia) {
            // Menghitung total harga
            int hargaPerBarang = selectedProduk.getHarga();
            int totalHarga = hargaPerBarang * stokDibeli;
            
            
            int idUser = Integer.parseInt(lblIdUser.getText());
            int idProduk = idProdukk;
            System.out.println(idProduk);
            int jlhPembelian = Integer.parseInt(tfStok.getText());

            if (validateInput(idUser, idProduk, jlhPembelian, totalHarga)) {

                try {
                    // Membuka koneksi ke database
                    Connection conn = dbConnection.getDBConnection();

                    // Membuat query untuk menyimpan data registrasi ke dalam database
                    String query = "INSERT INTO pembelian (user_id, produk_id, pembelian_jumlah, harga_total, status) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setInt(1, idUser);
                    statement.setInt(2, idProduk);
                    statement.setInt(3, jlhPembelian);
                    statement.setInt(4, totalHarga);
                    statement.setString(5, "order");

                    // Eksekusi query
                    statement.execute();
    //
    //                // Menutup koneksi ke database
    //                statement.close();
    //                conn.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPembelian.fxml"));
                Parent root = loader.load();

    //            // Mengirim total harga ke controller pembelian
    //            FXMLPembelianController pembelianController = loader.getController();
    //            pembelianController.setTotalHarga(totalHarga);
    //                System.out.println(totalHarga);

                // Buat scene baru
                Scene scene = new Scene(root);

                // Get the stage information
                Stage stage = (Stage) btnBeli.getScene().getWindow();

                // Set scene baru ke stage
                stage.setScene(scene);
                stage.setTitle("Morrah Farm - Halaman Pembelian");
                // Tampilkan stage
                stage.show();

                // Ambil ukuran layar
                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

                // Ambil ukuran tampilan JavaFX
                double sceneWidth = stage.getWidth();
                double sceneHeight = stage.getHeight();

                // Hitung posisi X dan Y agar tampilan JavaFX berada di tengah layar
                double posX = (screenBounds.getWidth() - sceneWidth) / 2;
                double posY = (screenBounds.getHeight() - sceneHeight) / 2;

                // Set posisi tampilan JavaFX pada layar
                stage.setX(posX);
                stage.setY(posY);
            }else {
                // Tampilkan pesan error atau lakukan tindakan sesuai kebutuhan
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Penambahan Produk gagal. Mohon isi semua field dengan benar.");
                alert.showAndWait();
            }
        }
         else {
                // Stok tidak mencukupi, tampilkan pesan error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Stok tidak mencukupi");
                alert.setContentText("Stok yang tersedia hanya " + stokTersedia);
                alert.showAndWait();
            }
    }
    
        
    private boolean validateInput(int idUser, int idProduk, int jlhPembelian, int totalHarga) {
        // Lakukan validasi input sesuai kebutuhan, misalnya memeriksa apakah email valid,
        // password cukup kuat, dan username tidak kosong
        return idUser > 0 && idProduk > 0 && jlhPembelian > 0 && totalHarga > 0;
    }
}
