/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpsit.html.db;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author CENTRO TELEFONIA
 */
public class DataBaseToJson {

    /**
     * @param args the command line arguments
     */
    public static ResultSet RecuperaDati() throws Exception {
        //Registrazione del driver
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //ottenere la connessione
        String mysqlUrl = "jdbc:mysql://localhost:3306/es_1_tpsit?";
        String userName = "root";
        String passWord = "HA45@BMV";
        Connection con = DriverManager.getConnection(mysqlUrl, userName, passWord);
        System.out.println("Connection established......");
        //Creazione della dichiarazione
        Statement stmt = con.createStatement();
        //Recuperare i record
        ResultSet rs = stmt.executeQuery("select * from anagrafica");
        return rs;
    }

    public static void main(String[] args) throws SQLException, Exception {
        // TODO code application logic here
        //Creazione di un oggetto JSONObject
        JSONObject jsonObject = new JSONObject();
        //Creazione di un array json
        JSONArray array = new JSONArray();
        ResultSet rs = RecuperaDati();
         //inserimento dei dati ResutlSet nell'oggetto json
        while (rs.next()) {
            JSONObject record = new JSONObject();
           //inserimento di coppie chiave-valore nell'oggetto json
            record.put("IdPersona", rs.getInt("IdPersona"));
            record.put("Cognome", rs.getString("Cognome"));
            record.put("Nome", rs.getString("Nome"));

            array.add(record);
        }
        jsonObject.put("Anagrafica", array);
        try {
            FileWriter file = new FileWriter("Java-e-Database-mysql\\JavaHTTPServer\\src\\main\\java\\tpsit\\html\\db\\dbJson.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JSON file creato.....");
    }
}
    
