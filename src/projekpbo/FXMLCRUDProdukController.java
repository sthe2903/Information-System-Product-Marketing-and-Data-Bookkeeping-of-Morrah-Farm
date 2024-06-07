/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projekpbo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import static projekpbo.FXMLRegisterController.infoBox;


/**
 * FXML Controller class
 *
 * @author mahes
 */
public class FXMLCRUDProdukController implements Initializable {

    @FXML
    private TableView<Produk> tvProduk;
    @FXML
    private TableColumn<Produk, Integer> colID;
    @FXML
    private TableColumn<Produk, String> colNama;
    @FXML
    private TableColumn<Produk, Integer> colStok;
    @FXML
    private TableColumn<Produk, Integer> colHarga;
    @FXML
    private TableColumn<Produk, String> colDeskripsi;
    @FXML
    private TextField tfNama;
    @FXML
    private TextField tfStok;
    @FXML
    private TextField tfHarga;
    @FXML
    private TextArea taDeskripsi;
    @FXML
    private ImageView ivGambar;
    @FXML
    private Button btnAddImage;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button menuBack;
    @FXML
    private Button menuLogout;
    
    private FileInputStream fis;
    
    int jlhStok;
    String userRole = FXMLLoginController.role;

    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FXMLCRUDProdukController.class.getName());
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showProduk();
        
         // menambahkan event handler untuk tabel view
        tvProduk.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // check jika user melakukan 1 kali klik
                Produk selectedData = tvProduk.getSelectionModel().getSelectedItem(); // ambil data yang dipilih
                if (selectedData != null) { // pastikan data yang dipilih tidak kosong
                    // set data pada form
                    tfNama.setText(selectedData.getNama());
                    tfStok.setText(Integer.toString(selectedData.getStok()));
                    tfHarga.setText(Integer.toString(selectedData.getHarga()));
                    taDeskripsi.setText(selectedData.getDeskripsi());
                    // dan set data lainnya sesuai dengan field pada tabel view
                }
            }
        });
    }
    // ...

    public int getStokProduk(String namaProduk) throws SQLException {
        int stok = 0;
        Connection conn = dbConnection.getDBConnection();
        String query = "SELECT stok FROM produk WHERE nama = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, namaProduk);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            stok = rs.getInt("stok");
        }
        rs.close();
        stmt.close();
        conn.close();
        return stok;
    }

    public void updateStokProduk(String namaProduk, int stok) throws SQLException {
        Connection conn = dbConnection.getDBConnection();
        String query = "UPDATE produk SET stok = ? WHERE nama = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, stok);
        stmt.setString(2, namaProduk);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    
    public void getStokValue(){
        Produk selectedDataStok = tvProduk.getSelectionModel().getSelectedItem();
        jlhStok = selectedDataStok.getStok();
    }
    
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/morrah_farm_projectpbo", "root", "");
            return conn;
        }catch(SQLException ex){
            System.out.println("Error: "+ ex.getMessage());
            return null;
        }
    }
    
    public ObservableList<Produk> getProdukList(){
        ObservableList<Produk> produkList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM produk";
        Statement st;
        ResultSet rs;
         try{
             st = conn.createStatement();
             rs = st.executeQuery(query);
             Produk produk;
             while(rs.next()){
                 // ambil data gambar dari database
//                 Blob blob = rs.getBlob("gambar");
//                 InputStream inputStream = blob.getBinaryStream();
//                 Image image = new Image(inputStream);
                 produk = new Produk(rs.getInt("id_produk"), rs.getString("nama"), rs.getInt("stok"), rs.getInt("harga"), rs.getString("deskripsi"));
                 produkList.add(produk);
             }
         }catch(Exception ex){
             ex.printStackTrace();
         }
         return produkList;
    }
    
    public void showProduk(){
        ObservableList<Produk> list = getProdukList();
        
        colID.setCellValueFactory(new PropertyValueFactory<Produk, Integer>("id"));
        colNama.setCellValueFactory(new PropertyValueFactory<Produk, String>("nama"));
        colStok.setCellValueFactory(new PropertyValueFactory<Produk, Integer>("stok"));
        colHarga.setCellValueFactory(new PropertyValueFactory<Produk, Integer>("harga"));
        colDeskripsi.setCellValueFactory(new PropertyValueFactory<Produk, String>("deskripsi"));
        
        tvProduk.setItems(list);
    }

    @FXML
    private void handleActionInsert(ActionEvent event) {
        String nama = tfNama.getText();
        int stok = Integer.parseInt(tfStok.getText());
        int harga = Integer.parseInt(tfHarga.getText());
        String deskripsi = taDeskripsi.getText();

        if (validateInput(nama, stok, harga, deskripsi)) {

            try {
                // Membuka koneksi ke database
                Connection conn = getConnection();

                // Membuat query untuk menyimpan data registrasi ke dalam database
                String query = "INSERT INTO produk (nama, stok, deskripsi, harga) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, nama);
                statement.setInt(2, stok);
                statement.setString(3, deskripsi);
                statement.setInt(4, harga);

                // Eksekusi query
                statement.executeUpdate();
                showProduk();
//
//                // Menutup koneksi ke database
//                statement.close();
//                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            // Tampilkan pesan error atau lakukan tindakan sesuai kebutuhan
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Penambahan Produk gagal. Mohon isi semua field dengan benar.");
            alert.showAndWait();
        }
        
        tfNama.setText("");
        tfStok.setText("");
        tfHarga.setText("");
        taDeskripsi.setText("");
    }
    
    private boolean validateInput(String nama, int stok, int harga, String deskripsi) {
        // Lakukan validasi input sesuai kebutuhan, misalnya memeriksa apakah email valid,
        // password cukup kuat, dan username tidak kosong
        return !nama.isEmpty() && stok > 0 && harga > 0 && !deskripsi.isEmpty();
    }


    
    @FXML
    private void handleActionDelete(ActionEvent event) {
        // Mendapatkan data produk yang dipilih dari tabel
        Produk selectedData = tvProduk.getSelectionModel().getSelectedItem();
        if (selectedData != null) { // Pastikan data yang dipilih tidak kosong
            // Tampilkan konfirmasi sebelum menghapus data
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmasi");
            alert.setContentText("Anda yakin akan menghapus data ini?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    // Membuka koneksi ke database
                    Connection conn = getConnection();

                    // Membuat query untuk menghapus data produk dari database
                    String query = "DELETE FROM produk WHERE id_produk = ?";
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setInt(1, selectedData.getId());

                    // Eksekusi query
                    statement.executeUpdate();
                    showProduk();

                    // Menutup koneksi ke database
                    statement.close();
                    conn.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Tampilkan pesan error atau lakukan tindakan sesuai kebutuhan
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Hapus data gagal. Mohon pilih data yang akan dihapus.");
            alert.showAndWait();
        }

        tfNama.setText("");
        tfStok.setText("");
        tfHarga.setText("");
        taDeskripsi.setText("");
    }

    @FXML
    private void handleActionUpdate(ActionEvent event) {
        // Ambil data yang dipilih dari tabel
        Produk selectedData = tvProduk.getSelectionModel().getSelectedItem();

        if (selectedData != null) { // Pastikan ada data yang dipilih
            String nama = tfNama.getText();
            int stok = Integer.parseInt(tfStok.getText());
            int harga = Integer.parseInt(tfHarga.getText());
            String deskripsi = taDeskripsi.getText();

            if (validateInput(nama, stok, harga, deskripsi)) { // Lakukan validasi input

                try {
                    // Membuka koneksi ke database
                    Connection conn = getConnection();

                    // Membuat query untuk mengupdate data produk yang dipilih
                    String query = "UPDATE produk SET nama=?, stok=?, harga=?, deskripsi=? WHERE id_produk=?";
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setString(1, nama);
                    statement.setInt(2, stok);
                    statement.setInt(3, harga);
                    statement.setString(4, deskripsi);
                    statement.setInt(5, selectedData.getId());

                    // Eksekusi query
                    statement.executeUpdate();
                    showProduk();

                    // Menutup koneksi ke database
                    statement.close();
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
        } else {
            // Tampilkan pesan error atau lakukan tindakan sesuai kebutuhan
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Update Produk gagal. Mohon pilih data terlebih dahulu.");
            alert.showAndWait();
        }
        
        tfNama.setText("");
        tfStok.setText("");
        tfHarga.setText("");
        taDeskripsi.setText("");
    }
    
    
    @FXML
    private void handleActionProduk(ActionEvent event) {
        
    }

    @FXML
    private void handleActionBack(ActionEvent event) throws IOException {
        try{
            if("admin".equals(userRole)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAdminn.fxml"));
            Parent root = loader.load();

            // Buat scene baru
            Scene scene = new Scene(root);

            // Get the stage information
            Stage stage = (Stage) menuBack.getScene().getWindow();

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
        }else if("produksi".equals(userRole)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLProduksi.fxml"));
            Parent root = loader.load();

            // Buat scene baru
            Scene scene = new Scene(root);

            // Get the stage information
            Stage stage = (Stage) menuBack.getScene().getWindow();

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
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
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
    
//    @FXML
//    private void handleActionAddImage(ActionEvent event) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Pilih Gambar Produk");
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
//        );
//        File selectedFile = fileChooser.showOpenDialog(null);
//
//        if (selectedFile != null) {
//            try {
//                fis = new FileInputStream(selectedFile);
//                Image image = new Image(fis);
//                ivGambar.setImage(image);
//            } catch (FileNotFoundException ex) {
//                System.out.println("Error: "+ ex.getMessage());
//            }
//        }
//    }


}
