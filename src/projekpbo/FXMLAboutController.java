/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projekpbo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mahes
 */
public class FXMLAboutController implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private Button btnLogout;
    @FXML
    private TextField tfNamaPemilik;
    @FXML
    private TextField tfNamaManager;
    @FXML
    private TextField tfAlamat;
    @FXML
    private TextArea taDeskripsi;
    @FXML
    private TextField tfContact;
    @FXML
    private Button btnUpdate;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        taDeskripsi.setWrapText(true);
            try {
            // Membuka koneksi ke database
            Connection conn = dbConnection.getDBConnection();

            // Membuat query untuk mengambil data dari tabel
            String selectQuery = "SELECT * FROM about_us";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                // Mengisi nilai-nilai form dengan data yang diambil dari database
                String namaPemilik = resultSet.getString("nama_pemilik");
                String namaManager = resultSet.getString("nama_manager");
                String alamat = resultSet.getString("alamat");
                String deskripsi = resultSet.getString("deskripsi");
                String contact = resultSet.getString("contact");

                tfNamaPemilik.setText(namaPemilik);
                tfNamaManager.setText(namaManager);
                tfAlamat.setText(alamat);
                taDeskripsi.setText(deskripsi);
                tfContact.setText(contact);
            }

            // Menutup koneksi ke database
            resultSet.close();
            selectStatement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    

    @FXML
    private void actionHandleBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAdminn.fxml"));
        Parent root = loader.load();

        // Buat scene baru
        Scene scene = new Scene(root);

        // Get the stage information
        Stage stage = (Stage) btnBack.getScene().getWindow();

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
    }

    @FXML
    private void actionHandleLogout(ActionEvent event) throws IOException {
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
            Stage stage = (Stage) btnLogout.getScene().getWindow();

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

    @FXML
    private void actionHandleUpdate(ActionEvent event) {
        String namaPemilik = tfNamaPemilik.getText();
        String namaManager = tfNamaManager.getText();
        String alamat = tfAlamat.getText();
        String deskripsi = taDeskripsi.getText();
        String contact = tfContact.getText();

        if (validateInput(namaPemilik, namaManager, alamat, deskripsi, contact)) {
            try {
                // Membuka koneksi ke database
                Connection conn = dbConnection.getDBConnection();

                // Membuat query untuk memeriksa keberadaan data dalam tabel
                String checkQuery = "SELECT * FROM about_us";
                PreparedStatement checkStatement = conn.prepareStatement(checkQuery);
                ResultSet resultSet = checkStatement.executeQuery();

                if (resultSet.next()) {
                    // Data sudah ada di database, lakukan update
                    String updateQuery = "UPDATE about_us SET nama_pemilik=?, nama_manager=?, alamat=?, deskripsi=?, contact=?";
                    PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
                    updateStatement.setString(1, namaPemilik);
                    updateStatement.setString(2, namaManager);
                    updateStatement.setString(3, alamat);
                    updateStatement.setString(4, deskripsi);
                    updateStatement.setString(5, contact);
                    updateStatement.executeUpdate();
                    updateStatement.close();
                } else {
                    // Data belum ada di database, lakukan insert
                    String insertQuery = "INSERT INTO about_us (nama_pemilik, nama_manager, alamat, deskripsi, contact) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
                    insertStatement.setString(1, namaPemilik);
                    insertStatement.setString(2, namaManager);
                    insertStatement.setString(3, alamat);
                    insertStatement.setString(4, deskripsi);
                    insertStatement.setString(5, contact);
                    insertStatement.executeUpdate();
                    insertStatement.close();
                }
                
                // Tampilkan pesan error atau lakukan tindakan sesuai kebutuhan
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Berhasil");
                alert.setContentText("Update Data About Us Berhasil.");
                alert.showAndWait();

                // Menutup koneksi ke database
                resultSet.close();
                checkStatement.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Tampilkan pesan error atau lakukan tindakan sesuai kebutuhan
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Update Produk gagal. Mohon isi semua field dengan benar.");
            alert.showAndWait();
        }
    }
    
        private boolean validateInput(String namaPemilik, String namaManager, String alamat, String deskripsi, String contact) {
        // Lakukan validasi input sesuai kebutuhan, misalnya memeriksa apakah email valid,
        // password cukup kuat, dan username tidak kosong
        return !namaPemilik.isEmpty() && !namaManager.isEmpty() && !alamat.isEmpty() && !deskripsi.isEmpty() && !deskripsi.isEmpty();
    }

    
}
