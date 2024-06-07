/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package projekpbo;

import java.sql.Connection;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author mahes
 */
public class ProjekPBO extends Application {
    
    Connection conn = null;
    Statement statement = null;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        conn = dbConnection.getDBConnection();
        
        Scene scene = new Scene(root);
        stage.setTitle("Morrah Farm");
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
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
