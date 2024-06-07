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
import java.sql.ResultSet;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
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
public class FXMLLoginController implements Initializable {
    
    @FXML
    private PasswordField fieldPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink linkRegister;

    @FXML
    private TextField fieldUsername;
    
    @FXML
    private Button btnKembali;
    @FXML
    public static Label NamaUser;

    
    public static String Namausername;
    public static String IDuser;
    public static String role;
    
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
    public void viewRegister(ActionEvent event) {
        try {
            // Load file FXML untuk halaman register
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLRegister.fxml"));
            Parent root = loader.load();
            
            // Buat scene baru
            Scene scene = new Scene(root);
            
            // Get the stage information
            Stage stage = (Stage) linkRegister.getScene().getWindow();
            
            // Set scene baru ke stage
            stage.setScene(scene);
            stage.setTitle("Halaman Register");
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
    public void actionLogin(ActionEvent event) throws SQLException, IOException{
        String username = fieldUsername.getText();
        String password = fieldPassword.getText();

    // Check jika field username atau password kosong
    if (username.isEmpty() || password.isEmpty()) {
        showAlert(AlertType.ERROR, btnLogin.getScene().getWindow(), "Error", "Username dan password harus diisi.");
        return;
    }

    // Lakukan proses login, misalnya dengan memeriksa username dan password pada database
    boolean loginSuccessful = performLogin(username, password);

    if (loginSuccessful) {
        // Jika login sukses, tampilkan pesan sukses dan pindahkan ke halaman utama
        infoBox("Login sukses!", "Sukses", "Login");
//        try {
//            // Load file FXML untuk halaman utama setelah login
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMain.fxml"));
//            Parent root = loader.load();
//
//            // Buat scene baru
//            Scene scene = new Scene(root);
//
//            // Get the stage information
//            Stage stage = (Stage) btnLogin.getScene().getWindow();
//
//            // Set scene baru ke stage
//            stage.setScene(scene);
//            stage.setTitle("Morrah Farm - Halaman Utama");
//            // Tampilkan stage
//            stage.show();
//            
//            // Ambil ukuran layar
//            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//
//            // Ambil ukuran tampilan JavaFX
//            double sceneWidth = stage.getWidth();
//            double sceneHeight = stage.getHeight();
//
//            // Hitung posisi X dan Y agar tampilan JavaFX berada di tengah layar
//            double posX = (screenBounds.getWidth() - sceneWidth) / 2;
//            double posY = (screenBounds.getHeight() - sceneHeight) / 2;
//
//            // Set posisi tampilan JavaFX pada layar
//            stage.setX(posX);
//            stage.setY(posY);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    } else {
        // Jika login gagal, tampilkan pesan error
        showAlert(AlertType.ERROR, btnLogin.getScene().getWindow(), "Error", "Username atau password salah.");
    }
    }
    
    private boolean performLogin(String username, String password) throws SQLException, IOException {
    // Lakukan proses login, misalnya dengan memeriksa username dan password pada database
    // Contoh implementasi sederhana:
    // Lakukan koneksi ke database, query ke tabel pengguna dengan username dan password,
    // dan periksa hasil query apakah ada pengguna yang sesuai
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    boolean loginSuccessful = false;
    role = "";
    Namausername = "";
    IDuser = "";
    try {
        // Koneksi ke database
        connection = DriverManager.getConnection("jdbc:mysql://localhost/morrah_farm_projectpbo", "root", "");

        // Query ke tabel pengguna dengan username dan password
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();

        // Periksa hasil query apakah ada pengguna yang sesuai
        if (resultSet.next()) {
            // Jika ada, login sukses
            loginSuccessful = true;
            // Ambil informasi role dari hasil query
            role = resultSet.getString("role");
            Namausername = resultSet.getString("username");
            IDuser = resultSet.getString("id_user");
        }
    } finally {
        // Tutup semua resource
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
    // Lakukan pengaturan tampilan berdasarkan role pengguna
    if (loginSuccessful) {
        // Jika login sukses, cek role pengguna
        if ("admin".equals(role)) {
            // Jika role pengguna adalah admin, tampilkan tampilan admin
            // Misalnya dengan mengganti halaman FXMLMain.fxml menjadi FXMLAdmin.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAdminn.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Morrah Farm - Halaman Admin");
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
        } else if ("user".equals(role)) {
            // Jika role pengguna adalah user, tampilkan tampilan user
            // Misalnya dengan mengganti halaman FXMLMain.fxml menjadi FXMLUser.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLUserr.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Morrah Farm - Halaman User");
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
        } else if ("produksi".equals(role)) {
            // Jika role pengguna adalah user, tampilkan tampilan user
            // Misalnya dengan mengganti halaman FXMLMain.fxml menjadi FXMLUser.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLProduksi.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Morrah Farm - Halaman Produksi");
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
        } else if ("peternak".equals(role)) {
            // Jika role pengguna adalah user, tampilkan tampilan user
            // Misalnya dengan mengganti halaman FXMLMain.fxml menjadi FXMLUser.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPeternak.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Morrah Farm - Halaman Peternak");
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
    }
    return loginSuccessful;
}
    
    public static void showNamaPengguna() throws SQLException {
        NamaUser.setText(Namausername);
}

    
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
