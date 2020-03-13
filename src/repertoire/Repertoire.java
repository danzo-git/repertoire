/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repertoire;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
/**
 *
 * @author personnel
 */
public class Repertoire extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false); // Pas de modification de la taille de fenetre (évite les problèmes de redimensionnement)
	stage.getIcons().add(new Image("repertoire/images/ico.png")); // Logo sur la fenetre
	stage.setTitle("Repertoire Electronique"); // Titre de la fenetre
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
