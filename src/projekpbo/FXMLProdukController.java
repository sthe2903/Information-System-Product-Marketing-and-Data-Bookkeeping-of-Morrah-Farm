/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projekpbo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author mahes
 */
public class FXMLProdukController implements Initializable {

    
    @FXML
    private TextArea fieldDeskripsi;

    @FXML
    private TextField fieldNama;

    @FXML
    private Button btnSimpan;

    @FXML
    private Button btnKembali;

    @FXML
    private TextField fieldStok;

    @FXML
    public void actionSimpan(ActionEvent event) {
        String nama = fieldNama.getText();
        String stok = fieldStok.getText();
        String deskripsi = fieldDeskripsi.getText();

        // Lakukan validasi input, misalnya pastikan email valid, password cukup kuat, dsb.
        // Jika input tidak valid, tampilkan pesan error atau lakukan tindakan sesuai kebutuhan

        // Contoh kode untuk melakukan register dan menyimpan data ke dalam database menggunakan JDBC
        if (validateInput(nama, stok, deskripsi)) {
    //        // Lakukan register
    //        System.out.println("Email: " + email);
    //        System.out.println("Password: " + password);
    //        System.out.println("Username: " + username);

            infoBox("Produk Berhasil di tambahkan!", "Sukses", "Tambah Produk");

            // Menyimpan data registrasi ke dalam database
            try {
                // Membuka koneksi ke database
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/morrah_farm_projectpbo", "root", "");

                // Membuat query untuk menyimpan data registrasi ke dalam database
                String query = "INSERT INTO produk (nama, stok, deskripsi) VALUES (?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, nama);
                statement.setString(2, stok);
                statement.setString(3, deskripsi);

                // Eksekusi query
                statement.executeUpdate();

                // Menutup koneksi ke database
                statement.close();
                conn.close();

                // Tampilkan pesan sukses atau lakukan tindakan sesuai kebutuhan

                // Mengarahkan ke tampilan login setelah registrasi selesai
                try {
                // Load file FXML untuk halaman register
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAdmin.fxml"));
                Parent root = loader.load();

                // Buat scene baru
                Scene scene = new Scene(root);

                // Get the stage information
                Stage stage = (Stage) btnSimpan.getScene().getWindow();

                // Set scene baru ke stage
                stage.setScene(scene);
                stage.setTitle("Halaman Produk");
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Tampilkan pesan error atau lakukan tindakan sesuai kebutuhan
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Registrasi gagal. Mohon isi semua field dengan benar.");
            alert.showAndWait();
        }
    }
    
    private boolean validateInput(String nama, String stok, String deskripsi) {
        // Lakukan validasi input sesuai kebutuhan, misalnya memeriksa apakah email valid,
        // password cukup kuat, dan username tidak kosong
        return !nama.isEmpty() && !stok.isEmpty() && !deskripsi.isEmpty();
}
    
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    public void actionKembali(ActionEvent event) {

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
