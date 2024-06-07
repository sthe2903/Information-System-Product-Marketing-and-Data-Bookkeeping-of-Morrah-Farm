/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projekpbo;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author mahes
 */
public class FXMLAdminController implements Initializable {
    
    @FXML
    private Button btnProduk;

    @FXML
    private Button btnLogout;
    
    @FXML
    private Label MenuBack;

    @FXML
    private AnchorPane slider;

    @FXML
    private Button btnAbout;

    @FXML
    private Button btnBeranda;

    @FXML
    private Button btnTugas;
    @FXML
    private Label NamaUser;
    @FXML
    private Button btnPesanan;
    @FXML
    private Label Menu;


    @FXML
    private void handleActionAbout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAbout.fxml"));
        Parent root = loader.load();

        // Buat scene baru
        Scene scene = new Scene(root);

        // Get the stage information
        Stage stage = (Stage) btnAbout.getScene().getWindow();

        // Set scene baru ke stage
        stage.setScene(scene);
        stage.setTitle("Morrah Farm - Halaman About Us");
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
    private void handleActionBeranda(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAdminn.fxml"));
        Parent root = loader.load();

        // Buat scene baru
        Scene scene = new Scene(root);

        // Get the stage information
        Stage stage = (Stage) btnBeranda.getScene().getWindow();

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

//    private void handleActionBlog(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBlog.fxml"));
//        Parent root = loader.load();
//
//        // Buat scene baru
//        Scene scene = new Scene(root);
//
//        // Get the stage information
//        Stage stage = (Stage) btnBlog.getScene().getWindow();
//
//        // Set scene baru ke stage
//        stage.setScene(scene);
//        stage.setTitle("Morrah Farm - Halaman Utama");
//        // Tampilkan stage
//        stage.show();
//
//        // Ambil ukuran layar
//        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//
//        // Ambil ukuran tampilan JavaFX
//        double sceneWidth = stage.getWidth();
//        double sceneHeight = stage.getHeight();
//
//        // Hitung posisi X dan Y agar tampilan JavaFX berada di tengah layar
//        double posX = (screenBounds.getWidth() - sceneWidth) / 2;
//        double posY = (screenBounds.getHeight() - sceneHeight) / 2;
//
//        // Set posisi tampilan JavaFX pada layar
//        stage.setX(posX);
//        stage.setY(posY);
//    }

    @FXML
    private void handleActionTugas(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTugasKaryawan.fxml"));
        Parent root = loader.load();

        // Buat scene baru
        Scene scene = new Scene(root);

        // Get the stage information
        Stage stage = (Stage) btnTugas.getScene().getWindow();

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
    private void handleActionProduk(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCRUDProduk.fxml"));
        Parent root = loader.load();

        // Buat scene baru
        Scene scene = new Scene(root);

        // Get the stage information
        Stage stage = (Stage) btnProduk.getScene().getWindow();

        // Set scene baru ke stage
        stage.setScene(scene);
        stage.setTitle("Morrah Farm - Halaman Produk");
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
        Stage stage = new Stage();
        // buat sebuah dialog baru
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Logout");
        alert.setHeaderText("Apakah Anda yakin ingin keluar dari aplikasi?");
        alert.setContentText("Pilih 'OK' untuk logout, atau 'Cancel' untuk batal.");

        // tampilkan dialog dan tunggu hingga pengguna memberikan respons
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // Tutup tampilan sebelumnya
            Stage previousStage = (Stage) btnLogout.getScene().getWindow();
            previousStage.close();

            // buka halaman login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
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
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String Namaa = FXMLLoginController.Namausername;
        NamaUser.setText("Selamat Datang "+Namaa);
//        slider.setTranslateX(-176);
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(false);
                MenuBack.setVisible(true);
            });
        });

        MenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(true);
                MenuBack.setVisible(false);
            });
        });
    }    

    @FXML
    private void handleActionPesanan(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPesanan.fxml"));
        Parent root = loader.load();

        // Buat scene baru
        Scene scene = new Scene(root);

        // Get the stage information
        Stage stage = (Stage) btnPesanan.getScene().getWindow();

        // Set scene baru ke stage
        stage.setScene(scene);
        stage.setTitle("Morrah Farm - Halaman Pesanan");
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
    
}
