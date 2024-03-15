/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author mateo
 */
public class Conexion {
    Connection con;
    String url="jdbc:mysql://localhost:3306/libreria_books";
    String usuario = "root";
    String clave = "";
    
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, clave);
        } catch (Exception e) {
        }
        return con;
    }
}
