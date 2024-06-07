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
import java.time.ZoneId;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mahes
 */
public class FXMLTugasKaryawanController implements Initializable {

    @FXML
    private TableView<TugasKaryawan> tvTugas;
    @FXML
    private TableColumn<TugasKaryawan, Integer> colID;
    @FXML
    private TableColumn<TugasKaryawan, String> colNama;
    @FXML
    private TableColumn<TugasKaryawan, String> colDivisi;
    @FXML
    private TableColumn<TugasKaryawan, String> colWaktu;
    @FXML
    private TableColumn<TugasKaryawan, String> colDeskripsi;
    @FXML
    private TableColumn<TugasKaryawan, String> colStatus;
    @FXML
    private ComboBox<String> comboDivisi;
    @FXML
    private ComboBox<String> comboNama;
    @FXML
    private DatePicker DateWaktu;
    @FXML
    private TextArea taDeskripsi;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnBack;
    @FXML
    private Button menuLogout;
    @FXML
    private ImageView menuBack1;
    @FXML
    private Label lblUserId;
    @FXML
    private Label lblStatus;

    public static String userID;

    public static String status;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(status);
        userID = lblUserId.getText();
        lblUserId.setVisible(false);
        // Mengatur item pada ComboBox
        comboDivisi.getItems().addAll("produksi", "peternak");
        
        // Menambahkan event handler saat pilihan ComboBox berubah
        comboDivisi.setOnAction(this::handleComboBoxAction);
        comboNama.setOnAction(this::handleComboBoxAction);
        
        try {
            showTugasKaryawan();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTugasKaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         // menambahkan event handler untuk tabel view
        tvTugas.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // check jika user melakukan 1 kali klik
                TugasKaryawan selectedData = tvTugas.getSelectionModel().getSelectedItem(); // ambil data yang dipilih
                if (selectedData != null) { // pastikan data yang dipilih tidak kosong
                    // set data pada form
                    comboNama.getSelectionModel().select(selectedData.getUsername());
                    comboDivisi.getSelectionModel().select(selectedData.getRole());
                    DateWaktu.setValue(selectedData.getWaktu());
                    taDeskripsi.setText(selectedData.getDeskripsi());
                    // dan set data lainnya sesuai dengan field pada tabel view
                }
            }
        });
    }    
    
    private void showTugasKaryawan() throws SQLException{
        ObservableList<TugasKaryawan> list = getTugasList();
        
        // Menghubungkan kolom-kolom pada TableView dengan properti yang sesuai dalam model data
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("username"));
        colDivisi.setCellValueFactory(new PropertyValueFactory<>("role"));
        colWaktu.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        colDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tvTugas.setItems(list);
    }
    
    public ObservableList<TugasKaryawan> getTugasList() throws SQLException{
        ObservableList<TugasKaryawan> tugasList = FXCollections.observableArrayList();
        Connection conn = dbConnection.getDBConnection();
        String query = "SELECT k.id, u.username, u.role, k.waktu, k.deskripsi, k.status " + "FROM karyawan_tugas k " + "JOIN users u ON k.user_id = u.id_user";
        Statement st;
        ResultSet rs;
         try{
             st = conn.createStatement();
             rs = st.executeQuery(query);
             TugasKaryawan tugas;
             while(rs.next()){
                Date waktuDate = rs.getDate("waktu");
                LocalDate waktu = waktuDate.toLocalDate();
                 // ambil data gambar dari database
//                 Blob blob = rs.getBlob("gambar");
//                 InputStream inputStream = blob.getBinaryStream();
//                 Image image = new Image(inputStream);
                 tugas = new TugasKaryawan(rs.getInt("id"), rs.getString("username"), rs.getString("role"), waktu, rs.getString("deskripsi"), rs.getString("status"));
                 tugasList.add(tugas);
             }
         }catch(Exception ex){
             ex.printStackTrace();
         }
         return tugasList;
    }
    
//    private void populateTableView() {
//        try {
//            Connection connection = dbConnection.getDBConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "SELECT k.id, u.username, u.role, k.waktu, k.deskripsi, k.status " + "FROM karyawan_tugas k " + "JOIN users u ON k.user_id = u.id_user"
//            );
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String nama = resultSet.getString("username");
//                String divisi = resultSet.getString("role");
//                Date waktuDate = resultSet.getDate("waktu");
//                LocalDate waktu = waktuDate.toLocalDate();
//                String deskripsi = resultSet.getString("deskripsi");
//                String status = resultSet.getString("status");
//
//                TugasKaryawan data = new TugasKaryawan(id, nama, divisi, waktu, deskripsi, status);
//                tvTugas.getItems().add(data);
//            }
//
//            resultSet.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }=
    
    
    private void handleComboBoxAction(ActionEvent event) {
        // Mendapatkan pilihan yang dipilih dari ComboBox
        String selectedOption = comboDivisi.getValue();

        // Menampilkan data sesuai dengan pilihan ComboBox
        if ("produksi".equals(selectedOption)) {
            try {
                Connection connection = dbConnection.getDBConnection();
                Statement statement = connection.createStatement();

                // Mengeksekusi query untuk mengambil data dari database
                String query = "SELECT username FROM users WHERE role = 'produksi'";
                ResultSet resultSet = statement.executeQuery(query);

                // Membuat ObservableList untuk menyimpan data dari database
                ObservableList<String> data = FXCollections.observableArrayList();
                while (resultSet.next()) {
                    String value = resultSet.getString("username");
                    data.add(value);
                }

                // Mengatur data pada ComboBox
                comboNama.setItems(data);

                // Menutup koneksi ke database
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("peternak".equals(selectedOption)) {
            try {
                Connection connection = dbConnection.getDBConnection();
                Statement statement = connection.createStatement();

                // Mengeksekusi query untuk mengambil data dari database
                String query = "SELECT username FROM users WHERE role = 'peternak'";
                ResultSet resultSet = statement.executeQuery(query);

                // Membuat ObservableList untuk menyimpan data dari database
                ObservableList<String> data = FXCollections.observableArrayList();
                while (resultSet.next()) {
                    String value = resultSet.getString("username");
                    data.add(value);
                }

                // Mengatur data pada ComboBox
                comboNama.setItems(data);

                // Menutup koneksi ke database
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        String selectedUsername = comboNama.getValue();

        // Menghubungkan ke database dan mendapatkan user_id berdasarkan username
        try {
            Connection connection = dbConnection.getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_user FROM users WHERE username = ?");
            preparedStatement.setString(1, selectedUsername);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String userId = resultSet.getString("id_user");
                lblUserId.setText(userId);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
//    private void showStatus(){
//        try {
//            Connection connection = dbConnection.getDBConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT status FROM karyawan_tugas WHERE id = ?");
//            preparedStatement.setString(1, selectedUsername);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                String status = resultSet.getString("status");
//                lblUserId.setText(status);
//            }
//
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @FXML
    private void handleActionInsert(ActionEvent event) {
        String selectedDivisi = comboDivisi.getValue();
        String selectedNama = comboNama.getValue();
        LocalDate selectedDate = DateWaktu.getValue();
        String deskripsi = taDeskripsi.getText();
        String userId = lblUserId.getText();

        if (selectedDivisi != null && selectedNama != null && selectedDate != null && !deskripsi.isEmpty()) {
            try {
                Connection connection = dbConnection.getDBConnection();
                // Mendapatkan user_id berdasarkan divisi dan nama menggunakan PreparedStatement
//                String getUserIdQuery = "SELECT id_user FROM users WHERE username = ?";
//                PreparedStatement statement = connection.prepareStatement(getUserIdQuery);
////                statement.setString(1, selectedDivisi);
//                statement.setString(1, selectedNama);
//                ResultSet resultSet = statement.executeQuery();
//                int userId = resultSet.getInt("id_user");

                // Mengeksekusi query untuk memasukkan data ke dalam database
                String insertQuery = "INSERT INTO karyawan_tugas (user_id, waktu, deskripsi) VALUES (?, ?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setString(1, userId);
                insertStatement.setDate(2, java.sql.Date.valueOf(selectedDate));
                insertStatement.setString(3, deskripsi);
                insertStatement.executeUpdate();
                showTugasKaryawan();

//                insertStatement.close();
//                insertStatement.close();
//                connection.close();


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Konfirmasi Berhasil");
                alert.setContentText("Data berhasil ditambahkan");

                // Mengatur kembali nilai-nilai default atau mengosongkan nilai-nilai input
                comboDivisi.setValue(null);
                comboNama.setValue(null);
                DateWaktu.setValue(null);
                taDeskripsi.clear();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Mohon isi semua field sebelum memasukkan data ke dalam database.");
        }
    }

    @FXML
    private void handleActionDelete(ActionEvent event) {
        TugasKaryawan selectedData = tvTugas.getSelectionModel().getSelectedItem();
        if (selectedData != null) { // Pastikan data yang dipilih tidak kosong
            // Tampilkan konfirmasi sebelum menghapus data
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmasi");
            alert.setContentText("Anda yakin akan menghapus data ini?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    // Membuka koneksi ke database
                    Connection conn = dbConnection.getDBConnection();

                    // Membuat query untuk menghapus data produk dari database
                    String query = "DELETE FROM karyawan_tugas WHERE id = ?";
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setInt(1, selectedData.getId());

                    // Eksekusi query
                    statement.executeUpdate();
                    showTugasKaryawan();

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

        comboDivisi.setValue(null);
        comboNama.setValue(null);
        DateWaktu.setValue(null);
        taDeskripsi.clear();
    }

    @FXML
    private void handleActionUpdate(ActionEvent event) {
        TugasKaryawan selectedData = tvTugas.getSelectionModel().getSelectedItem();

        if (selectedData != null) { // Pastikan ada data yang dipilih
            String username = comboNama.getValue();
            String role = comboDivisi.getValue();
            String userId = lblUserId.getText();
            LocalDate waktu = DateWaktu.getValue();
            String deskripsi = taDeskripsi.getText();

            if (validateInput(username, role, deskripsi)) { // Lakukan validasi input

                try {
                    // Membuka koneksi ke database
                    Connection conn = dbConnection.getDBConnection();

                    // Membuat query untuk mengupdate data produk yang dipilih
                    String query = "UPDATE karyawan_tugas SET user_id=?, waktu=?, deskripsi=? WHERE id=?";
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setString(1, userId);
                    statement.setDate(2, java.sql.Date.valueOf(waktu));
                    statement.setString(3, deskripsi);
                    statement.setInt(4, selectedData.getId());

                    // Eksekusi query
                    statement.executeUpdate();
                    showTugasKaryawan();
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Konfirmasi Berhasil");
                    alert.setContentText("Data berhasil diubah");

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
        
        comboDivisi.setValue(null);
        comboNama.setValue(null);
        DateWaktu.setValue(null);
        taDeskripsi.clear();
    }
    
        private boolean validateInput(String username, String role, String deskripsi) {
        // Lakukan validasi input sesuai kebutuhan, misalnya memeriksa apakah email valid,
        // password cukup kuat, dan username tidak kosong
        return !username.isEmpty() && !role.isEmpty() && !deskripsi.isEmpty();
    }


    @FXML
    private void handleActionBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAdminn.fxml"));
        Parent root = loader.load();

        // Buat scene baru
        Scene scene = new Scene(root);

        // Get the stage information
        Stage stage = (Stage) btnBack.getScene().getWindow();

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
            stage.setTitle("Morrah Farm - Halaman Login");
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

    
}
