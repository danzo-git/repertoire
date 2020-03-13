/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repertoire;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;

public class fonctions {
    //fonction de connection a la bd
    static final String BD_URL = "jdbc:mysql://localhost:3306/repertoire_electronique";
    static final String USER = "root";
    static final String PASS = "";
     static Connection con=null;
     static Statement stmt=null;
    public static void connection() {
       
        try{
          con=DriverManager.getConnection(BD_URL,USER,PASS);  //connection a la bd
          stmt=con.createStatement(); // creation d'un objet pour gerer les requetes a la bd
          System.out.print("connection ok\n");
        }

        catch (SQLException ex) {
                System.out.print("connection a la base de donnée impossible\n");
                System.out.print(ex.getMessage());
                Alert alert;
                alert = new Alert(Alert.AlertType.WARNING,"impossible de se connecter a la base de donnée");
                alert.showAndWait(); 
         }
    /*finally{
      //le block finally est utiliser ici pour fermer les resources
      System.out.print("fermeture......\n");
      try{
         if(stmt!=null)
            con.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(con!=null)
            con.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }*/
}
    public static ArrayList donnees() {
        ArrayList list = new ArrayList() ;
         try{
      ResultSet rs=stmt.executeQuery("select noms,num1 from contacth ORDER BY noms"); //selection le nom et le numero de chaque contact
      while(rs.next()){
        String a=rs.getString("noms")+"\n"+rs.getString("num1");
        list.add(a);
        
      }
     
    } catch (SQLException ex) {
      System.out.print(ex.getMessage()+"\n");
    }
        return list;
    }
    
    public static ArrayList donnees(String r) {
        ArrayList list = new ArrayList() ;
         try{
      ResultSet rs=stmt.executeQuery("SELECT noms,num1 FROM contacth WHERE noms LIKE '%"+r+"%' OR num1 LIKE '%"+r+"%' ORDER BY noms"); //selection le nom et le numero de chaque contact
      while(rs.next()){
        String a=rs.getString("noms")+"\n"+rs.getString("num1");
        list.add(a);
        
      }
     
    } catch (SQLException ex) {
      System.out.print(ex.getMessage()+"\n");
    }
        return list;
    }
     public static  boolean inserer(String noms,String num1,String num2,String email,String mention){
        try{
            String requete = "INSERT INTO contacth(noms,num1,num2,email,mention) VALUES ("+"'"+noms+"','"+num1+"','"+num2+"','"+email+"','"+mention+"')";
        stmt.executeUpdate(requete);
        return true;
      } catch (SQLException ex) {
      System.out.print(ex.getMessage()+"\n");
    }
      return false;
    }
     
    public static  boolean supprime(String e){
        try{
        String sql = "DELETE FROM contacth WHERE num1="+e;
        stmt.executeUpdate(sql);
        return true;
      } catch (SQLException ex) {
      System.out.print(ex.getMessage()+"\n");
    }
      return false;
    }
    public static  boolean modifier(String e,String noms,String num1,String num2,String email,String mention){
        try{
        String sql = "UPDATE contacth "+
                     "SET noms='"+noms+"',`num1`='"+num1+"',`num2`='"+num2+"',`email`='"+email+"',`mention`='"+mention+"' WHERE num1="+e;

        stmt.executeUpdate(sql);
        return true;
      } catch (SQLException ex) {
      System.out.print(ex.getMessage()+"\n");
    }
      return false;
    }
     public static ArrayList recuperation(String e) {
         ArrayList tab=new  ArrayList();
            try{
      ResultSet rs=stmt.executeQuery("SELECT * FROM contacth WHERE num1='"+e+"'"); 
      while(rs.next()){
        tab.add(rs.getString("noms"));
        tab.add(rs.getString("num1"));
        tab.add(rs.getString("num2"));
        tab.add(rs.getString("email"));
        tab.add(rs.getString("mention"));
      }
     
    } catch (SQLException ex) {
      System.out.print(ex.getMessage()+"\n");
    }
        return tab;

     }
     public static  boolean verif(String e){
        try{
        ResultSet rs=stmt.executeQuery("SELECT * FROM contacth WHERE num1='"+e+"'"); 
      while(rs.next()){
          return true;
      }
      } catch (SQLException ex) {
      System.out.print(ex.getMessage()+"\n");
    }
      return false;
    }
}
