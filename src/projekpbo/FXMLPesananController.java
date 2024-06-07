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
import java.sql.Statement;
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
import javafx.stage.Screen;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author mahes
 */
public class FXMLPesananController implements Initializable {

    @FXML
    private Button menuBack;
    @FXML
    private Button menuLogout;
    @FXML
    private Button btnKonfirmasi;
    @FXML
    private Label userLogin;
    @FXML
    private TableColumn<Pesanan, String> colNamaPembeli;
    @FXML
    private TableColumn<Pesanan, String> colNamaProduk;
    @FXML
    private TableColumn<Pesanan, Integer> colJumlahPembelian;
    @FXML
    private TableColumn<Pesanan, Integer> colTotalHarga;
    @FXML
    private TableColumn<Pesanan, String> colStatus;
    @FXML
    private TableView<Pesanan> tvPesanan;
    @FXML
    private TableColumn<Pesanan, Integer> colID;
    @FXML
    private Label lblIsiNamaPembeli;
    @FXML
    private Label lblIsiNamaProduk;
    @FXML
    private Label lblIsiJumlahPembelian;
    @FXML
    private Label lblIsiTotalHarga;
    @FXML
    private Label lblNamaPembeli;
    @FXML
    private Label lblNamaProduk;
    @FXML
    private Label lblJumlahProduk;
    @FXML
    private Label lblTotalHarga;

FXMLCRUDProdukController jlhstok = new FXMLCRUDProdukController();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showPesanan();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTugasKaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // menambahkan event handler untuk tabel view
        tvPesanan.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // check jika user melakukan 1 kali klik
                Pesanan selectedData = tvPesanan.getSelectionModel().getSelectedItem(); // ambil data yang dipilih
                if (selectedData != null) { // pastikan data yang dipilih tidak kosong
                    // set data pada form
                    lblIsiNamaPembeli.setText(selectedData.getNamaPembeli());
                    lblIsiNamaProduk.setText(selectedData.getNamaProduk());
                    lblIsiJumlahPembelian.setText(Integer.toString(selectedData.getJumlahPembelian()));
                    lblIsiTotalHarga.setText(Integer.toString(selectedData.getTotalHarga()));
                    // dan set data lainnya sesuai dengan field pada tabel view
                }
            }
        });
    }    

    @FXML
    private void handleActionBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAdminn.fxml"));
        Parent root = loader.load();

        // Buat scene baru
        Scene scene = new Scene(root);

        // Get the stage information
        Stage stage = (Stage) menuBack.getScene().getWindow();

        // Set scene baru ke stage
        stage.setScene(scene);
        stage.setTitle("Morrah Farm - Halaman Manager");
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
    
    private void showPesanan() throws SQLException{
        ObservableList<Pesanan> list = getPesananList();
        
        // Menghubungkan kolom-kolom pada TableView dengan properti yang sesuai dalam model data
        colID.setCellValueFactory(new PropertyValueFactory<>("id_pesanan"));
        colNamaPembeli.setCellValueFactory(new PropertyValueFactory<>("namaPembeli"));
        colNamaProduk.setCellValueFactory(new PropertyValueFactory<>("namaProduk"));
        colJumlahPembelian.setCellValueFactory(new PropertyValueFactory<>("jumlahPembelian"));
        colTotalHarga.setCellValueFactory(new PropertyValueFactory<>("totalHarga"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tvPesanan.setItems(list);
    }
    
    public ObservableList<Pesanan> getPesananList() throws SQLException{
        ObservableList<Pesanan> pesananList = FXCollections.observableArrayList();
        Connection conn = dbConnection.getDBConnection();
        String query = "SELECT p.id_pembelian, u.username, k.nama, p.pembelian_jumlah, p.harga_total, p.status FROM pembelian p JOIN users u ON p.user_id = u.id_user JOIN produk k ON p.produk_id = k.id_produk";
        Statement st;
        ResultSet rs;
         try{
             st = conn.createStatement();
             rs = st.executeQuery(query);
             Pesanan pesanan;
             while(rs.next()){
                 pesanan = new Pesanan(rs.getInt("id_pembelian"), rs.getString("username"), rs.getString("nama"), rs.getInt("pembelian_jumlah"), rs.getInt("harga_total"), rs.getString("status"));
                 pesananList.add(pesanan);
             }
         }catch(Exception ex){
             ex.printStackTrace();
         }
         return pesananList;
    }

    @FXML
    private void handleActionKonfirm(ActionEvent event) {
        Pesanan selectedData = tvPesanan.getSelectionModel().getSelectedItem(); // Ambil data yang dipilih dari tabel
        if (selectedData != null) { // Pastikan data yang dipilih tidak kosong
            // Konfirmasi perubahan status
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmasi Pesanan");
            alert.setHeaderText("Apakah Anda yakin ingin mengkonfirmasi pesanan ini?");
            alert.setContentText("Pilih 'OK' untuk mengkonfirmasi, atau 'Cancel' untuk batal.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // Lakukan perubahan status menjadi "Sudah Dikonfirmasi"
                selectedData.setStatus("Sudah Dikonfirmasi");

                // Perbarui data dalam tabel
                tvPesanan.refresh();

                // Perbarui nilai status ke database
                try {
                    Connection conn = dbConnection.getDBConnection();
                    String query = "UPDATE pembelian SET status = 'Sudah Dikonfirmasi' WHERE id_pembelian = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1, selectedData.getId_pesanan());
                    stmt.executeUpdate();
                    stmt.close();
                    conn.close();
                    
                    // Mendapatkan jumlah stok produk sebelumnya
                    int stokSebelumnya = jlhstok.getStokProduk(selectedData.getNamaProduk());

                    // Mendapatkan jumlah pembelian produk
                    int jumlahPembelian = selectedData.getJumlahPembelian();

                    // Mengurangi stok produk dengan jumlah pembelian
                    int stokSesudah = stokSebelumnya - jumlahPembelian;

                    // Memperbarui stok produk di database
                    jlhstok.updateStokProduk(selectedData.getNamaProduk(), stokSesudah);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // Tampilkan pesan sukses
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Konfirmasi Sukses");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Pesanan telah berhasil dikonfirmasi.");
                successAlert.showAndWait();
            }
        } else {
            // Tampilkan pesan kesalahan jika tidak ada data yang dipilih
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Kesalahan");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Tidak ada pesanan yang dipilih.");
            errorAlert.showAndWait();
        }
    }
}
