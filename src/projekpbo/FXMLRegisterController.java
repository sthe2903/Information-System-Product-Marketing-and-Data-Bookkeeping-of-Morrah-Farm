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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author mahes
 */
public class FXMLRegisterController implements Initializable {

    @FXML
    private TextField fieldEmail;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private TextField fieldUsername;

    @FXML
    private Button btnRegister;

    @FXML
    private Hyperlink linkLogin;
    
    @FXML
    private Button btnKembali;
    
     @FXML
    public void actionKembali(ActionEvent event) {
        try {
            // Load file FXML untuk halaman register
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
            
            // Buat scene baru
            Scene scene = new Scene(root);
            
            // Get the stage information
            Stage stage = (Stage) btnKembali.getScene().getWindow();
            
            // Set scene baru ke stage
            stage.setScene(scene);
            stage.setTitle("Morrah Farm");
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
    }

    @FXML
    public void actionRegister(ActionEvent event) {
        String email = fieldEmail.getText();
    String password = fieldPassword.getText();
    String username = fieldUsername.getText();

    // Lakukan validasi input, misalnya pastikan email valid, password cukup kuat, dsb.
    // Jika input tidak valid, tampilkan pesan error atau lakukan tindakan sesuai kebutuhan

    // Contoh kode untuk melakukan register dan menyimpan data ke dalam database menggunakan JDBC
    if (validateInput(email, password, username)) {
//        // Lakukan register
//        System.out.println("Email: " + email);
//        System.out.println("Password: " + password);
//        System.out.println("Username: " + username);

        infoBox("Registrasi sukses!", "Sukses", "Register");

        // Menyimpan data registrasi ke dalam database
        try {
            // Membuka koneksi ke database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/morrah_farm_projectpbo", "root", "");

            // Membuat query untuk menyimpan data registrasi ke dalam database
            String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);

            // Eksekusi query
            statement.executeUpdate();

            // Menutup koneksi ke database
            statement.close();
            conn.close();

            // Tampilkan pesan sukses atau lakukan tindakan sesuai kebutuhan

            // Mengarahkan ke tampilan login setelah registrasi selesai
            try {
            // Load file FXML untuk halaman register
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
            Parent root = loader.load();
            
            // Buat scene baru
            Scene scene = new Scene(root);
            
            // Get the stage information
            Stage stage = (Stage) linkLogin.getScene().getWindow();
            
            // Set scene baru ke stage
            stage.setScene(scene);
            stage.setTitle("Halaman Login");
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
    public void viewLogin(ActionEvent event) {
        try {
            // Load file FXML untuk halaman register
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
            Parent root = loader.load();
            
            // Buat scene baru
            Scene scene = new Scene(root);
            
            // Get the stage information
            Stage stage = (Stage) linkLogin.getScene().getWindow();
            
            // Set scene baru ke stage
            stage.setScene(scene);
            stage.setTitle("Halaman Login");
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
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private boolean validateInput(String email, String password, String username) {
    // Lakukan validasi input sesuai kebutuhan, misalnya memeriksa apakah email valid,
    // password cukup kuat, dan username tidak kosong
    return !email.isEmpty() && email.contains("@") && !password.isEmpty() && !username.isEmpty();
}
    
}
