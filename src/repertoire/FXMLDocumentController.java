/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repertoire;

import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author personnel
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Button modifier;
    @FXML private Button supp;
    @FXML private Button clear;
    
    @FXML private Label label;
    
    @FXML private  ListView<String> list;
    @FXML private TextField recherche;
    
    @FXML
    private TextField noms;
    @FXML private TextField num1;
    @FXML private TextField num2;
    @FXML private TextField email;
    @FXML private TextField mention;
    
    private ObservableList<String> data = FXCollections.observableArrayList();
    
    String element=null;
    
    @FXML
    private void initial(ActionEvent event) {
        noms.clear();num1.clear();num2.clear();email.clear();mention.clear();recherche.clear();
        modifier.setDisable(true);supp.setDisable(true);
        element=null;
    }
     private void initial() {
         num1.clear();num2.clear();noms.clear();email.clear();mention.clear();recherche.clear();
        modifier.setDisable(true);supp.setDisable(true);
         element=null;
    }
    
     @FXML
    private void ajouter(ActionEvent event) {
        String nom=noms.getText();
        String nume1=num1.getText();
        String nume2=num2.getText();
        String mail=email.getText();
        String mention0=mention.getText();
        System.out.print(mention0);
        
        if(nom.length()<=1 || nume1.length()<=1 ){
            
            Alert alert = new Alert(Alert.AlertType.ERROR,"veillez remplir les champs");
            alert.showAndWait(); 
        }
        else{
            try{
            if(repertoire.fonctions.verif(nume1)){
            Alert alert = new Alert(Alert.AlertType.WARNING,"ce numero : "+nume1+" que vous essayer d'ajouter\nest deja attribué a un autre contact"
                    + " merci de ressayer !");
            alert.showAndWait(); 
            }
            else{
            boolean b=repertoire.fonctions.inserer(nom,nume1,nume2,mail,mention0);
            if(b==false){
            Alert alert;
            alert = new Alert(Alert.AlertType.WARNING,"une erreur est survenu veillez reessayer svp!");
            alert.showAndWait(); 
            }
            else{
                    System.out.print("ll");
                affichage();
                initial();
            }
                    }
            }
        catch(Exception e){
            System.out.print("erreur");
        }}
    }
    
     @FXML
    private void modifier(ActionEvent event) {
        try{
        String nom=noms.getText();
        String nume1=num1.getText();
        String nume2=num2.getText();
        String mail=email.getText();
        String mention0=mention.getText();
        
        if(nom.length()<=1 || nume1.length()<=1 ){
            Alert alert = new Alert(Alert.AlertType.WARNING,"veillez remplir les champs");
            alert.showAndWait(); 
        }
        else{
            boolean b=repertoire.fonctions.modifier(element,nom,nume1,nume2,mail,mention0);
            if(b==false){
            Alert alert;
            alert = new Alert(Alert.AlertType.WARNING,"une erreur est survenu veillez reessayer svp!");
            alert.showAndWait(); 
        }
        else{
            affichage();
            initial();
        }
        }
        }catch(Exception e){
            System.out.print("erreur");
        }
    }
    @FXML
    private void supprimer(ActionEvent event) {
        try{
        boolean b=repertoire.fonctions.supprime(element);
        if(b==false){
            Alert alert;
            alert = new Alert(Alert.AlertType.WARNING,"une erreur est survenu veillez reessayer svp!");
            alert.showAndWait(); 
        }
        else{
            affichage();
            initial();
        }
        }catch(Exception e){
            System.out.print("erreur");
        }
    }
    
    @FXML
    private void recherch(String rech) {
        try{
        list.getItems().clear();
        
        if(rech.length()==0){
             ArrayList donnees =repertoire.fonctions.donnees(); //recupere nom et num1 dans la table contacth
        for(int i=0;i<donnees.size();i++) data.add((String)donnees.get(i));
        list.setItems(data); 
        }
        else{
             ArrayList donnees =repertoire.fonctions.donnees(rech); //recupere nom et num1 dans la table contacth
        for(int i=0;i<donnees.size();i++) data.add((String)donnees.get(i));
        list.setItems(data);
        }
        }catch(Exception e){
            System.out.print("erreur");
        }
    }
    public void affichage(){
        try{
        list.getItems().clear();
        ArrayList donnees =repertoire.fonctions.donnees(); //recupere nom et num1 dans la table contacth
        for(int i=0;i<donnees.size();i++) data.add((String)donnees.get(i));
        list.setItems(data);
        }catch(Exception e){
            System.out.print("erreur");
        }
        
        
    }
    public void recupe_info(String num){
        try{
        ArrayList donnees =repertoire.fonctions.recuperation(num);
        noms.setText((String)donnees.get(0));
        num1.setText((String)donnees.get(1));
        num2.setText((String)donnees.get(2));
        email.setText((String)donnees.get(3));
        mention.setText((String)donnees.get(4));
        }catch(Exception e){
            
        }
    }
    @FXML
    private void about(ActionEvent event) {
       //Parent root;
    try {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("about.fxml"));
        Parent parent = fxmlLoader.load();
        AboutController dialogController = fxmlLoader.<AboutController>getController();
        //dialogController.setAppMainObservableList(tvObservableList);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("About");
        stage.setResizable(false); // Pas de modification de la taille de fenetre (évite les problèmes de redimensionnement)
	stage.getIcons().add(new Image("repertoire/images/ico.png"));
        stage.showAndWait();

        /*root = FXMLLoader.load(getClass().getResource("about.fxml"));
        Stage stage = new Stage();
        stage.setTitle("About");
        stage.setResizable(false); // Pas de modification de la taille de fenetre (évite les problèmes de redimensionnement)
	stage.getIcons().add(new Image("repertoire/images/ico.png"));
        stage.setScene(new Scene(root));
        stage.show();*/

    } catch (IOException e) {e.printStackTrace();}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        repertoire.fonctions.connection(); //connection bd
        
        affichage();
        
        num1.getStyleClass().add("num1");
        num2.getStyleClass().add("num2");
        email.getStyleClass().add("email");
        mention.getStyleClass().add("mention");
        noms.getStyleClass().add("nom");
        
        modifier.setDisable(true);
        supp.setDisable(true);
        
        recherche.textProperty().addListener((observable,oldv,newv) -> {
        recherch(newv);
    });
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String str) {
                String nom=str.substring(0,str.indexOf('\n'));
                String num=str.substring(str.indexOf('\n')+1);
               
                recupe_info(num);
                element=num;
                modifier.setDisable(false);
                supp.setDisable(false);
                }
           });
       
        num1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,14}([\\+]\\d{0,15})?")) {
                    num1.setText(oldValue);
                }
            }
        });
        num2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,14}([\\+]\\d{0,15})?")) {
                    num2.setText(oldValue);
                }
            }
        });
    }

   
         
}
