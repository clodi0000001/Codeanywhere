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
        // TODO code application logic here
        //Registering the Driver
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost:3306/es_1_tpsit?";
        String userName = "root";
        String passWord = "HA45@BMV";
        Connection con = DriverManager.getConnection(mysqlUrl, userName, passWord);
        System.out.println("Connection established......");
        //Creating the Statement
        Statement stmt = con.createStatement();
        //Retrieving the records
        ResultSet rs = stmt.executeQuery("select * from anagrafica");
        return rs;
    }

    public static void main(String[] args) throws SQLException, Exception {
        // TODO code application logic here
        //Creating a JSONObject object
        JSONObject jsonObject = new JSONObject();
        //Creating a json array
        JSONArray array = new JSONArray();
        ResultSet rs = RecuperaDati();
        //Inserting ResutlSet data into the json object
        while (rs.next()) {
            JSONObject record = new JSONObject();
            //Inserting key-value pairs into the json object
            record.put("IdPersona", rs.getInt("IdPersona"));
            record.put("Cognome", rs.getString("Cognome"));
            record.put("Nome", rs.getString("Nome"));

            array.add(record);
        }
        jsonObject.put("Anagrafica", array);
        try {
            FileWriter file = new FileWriter("C:\\Users\\CENTRO TELEFONIA\\OneDrive\\Desktop\\git_tpisit_1\\Java-e-Database-mysql\\JavaHTTPServer\\src\\main\\java\\tpsit\\html\\db\\dbJson.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("JSON file creato.....");
    }
}
    
