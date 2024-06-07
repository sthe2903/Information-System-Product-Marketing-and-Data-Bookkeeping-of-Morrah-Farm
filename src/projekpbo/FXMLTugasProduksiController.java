/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projekpbo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mahes
 */
public class FXMLTugasProduksiController implements Initializable {

    @FXML
    private TableColumn<TugasKaryawan, String> colNama;
    @FXML
    private Button menuBack;
    @FXML
    private Button menuLogout;
    @FXML
    private TableView<TugasKaryawan> tvTugas;
    @FXML
    private TableColumn<TugasKaryawan, String> colWaktu;
    @FXML
    private TableColumn<TugasKaryawan, String> colDeskripsi;
    @FXML
    private TableColumn<TugasKaryawan, String> colStatus;
    @FXML
    private Label lblNama;
    @FXML
    private Label lblIsiNama;
    @FXML
    private Label lblWaktu;
    @FXML
    private Label lblIsiWaktu;
    @FXML
    private Label lblDeskripsi;
    @FXML
    private Label lblIsiDeskripsi;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblIsiStatus;
    @FXML
    private Button btnKonfirmasi;
    @FXML
    private Label userLogin;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String Namaa = FXMLLoginController.Namausername;
        userLogin.setText(Namaa);
        try {
            showTugasProduksi();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTugasKaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         // menambahkan event handler untuk tabel view
        tvTugas.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // check jika user melakukan 1 kali klik
                TugasKaryawan selectedData = tvTugas.getSelectionModel().getSelectedItem(); // ambil data yang dipilih
                if (selectedData != null) { // pastikan data yang dipilih tidak kosong
                    lblIsiNama.setText(selectedData.getUsername());
                    lblIsiWaktu.setText(String.valueOf(selectedData.getWaktu()));
                    lblIsiDeskripsi.setText(selectedData.getDeskripsi());
                    lblIsiStatus.setText(String.valueOf(selectedData.getStatus()));
                }
            }
        });
    }    
    
    private void showTugasProduksi() throws SQLException{
        ObservableList<TugasKaryawan> list = getTugasList();
        
        colNama.setCellValueFactory(new PropertyValueFactory<>("username"));
        colWaktu.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        colDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tvTugas.setItems(list);
    }
    


    
    public ObservableList<TugasKaryawan> getTugasList() throws SQLException{
        ObservableList<TugasKaryawan> tugasList = FXCollections.observableArrayList();
        Connection conn = dbConnection.getDBConnection();
        String query = "SELECT u.username, k.waktu, k.deskripsi, k.status FROM karyawan_tugas k JOIN users u ON k.user_id = u.id_user WHERE u.username = ?";
        PreparedStatement pst;
        ResultSet rs;
        try{
                 pst = conn.prepareStatement(query);
                 pst.setString(1, userLogin.getText());
                 rs = pst.executeQuery();
                 TugasKaryawan tugas;
                 while(rs.next()){
                    Date waktuDate = rs.getDate("waktu");
                    LocalDate waktu = waktuDate.toLocalDate();
                    tugas = new TugasKaryawan(userLogin.getText(), waktu, rs.getString("deskripsi"), rs.getString("status"));
                    tugasList.add(tugas);
                }


             }catch(Exception ex){
                 ex.printStackTrace();
             }
        return tugasList;
    }

    @FXML
    private void handleActionBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLProduksi.fxml"));
        Parent root = loader.load();

        // Buat scene baru
        Scene scene = new Scene(root);

        // Get the stage information
        Stage stage = (Stage) menuBack.getScene().getWindow();

        // Set scene baru ke stage
        stage.setScene(scene);
        stage.setTitle("Morrah Farm - Halaman Produksi");
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

    @FXML
    private void handleActionKonfirm(ActionEvent event) throws SQLException {
        TugasKaryawan selectedData = tvTugas.getSelectionModel().getSelectedItem();
        if (selectedData != null) {
            // Ubah status menjadi "selesai" di database
            updateStatusTugas(selectedData.getUsername(), selectedData.getWaktu(), "selesai");

            // Update tampilan
            showTugasProduksi();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Berhasil");
            alert.setContentText("Konfirmasi Tugas Berhasil.");
            alert.showAndWait();
            
            lblIsiNama.setText("");
            lblIsiWaktu.setText("");
            lblIsiDeskripsi.setText("");
            lblIsiStatus.setText("");
        }
    }
    
    private void updateStatusTugas(String username, LocalDate waktu, String status) throws SQLException {
        Connection conn = dbConnection.getDBConnection();
        String query = "UPDATE karyawan_tugas SET status = ? WHERE user_id = (SELECT id_user FROM users WHERE username = ?) AND waktu = ?";
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, status);
            pst.setString(2, username);
            pst.setDate(3, java.sql.Date.valueOf(waktu));
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
