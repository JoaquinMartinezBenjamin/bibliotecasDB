/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecabd;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class Connbd{
    Connection conn;
    Statement statement = null;
    ResultSet resultSet = null;
    public Connection conectar(){
        Connection connection=null;
        try{
            Class.forName("org.postgresql.Driver");
           } catch(ClassNotFoundException e){
            System.out.println("No se encuentra el driver para la conexion");
            e.printStackTrace();
            return connection;
                                             }
        try{
            connection =DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/Biblioteca","postgres","topicos2016");
           }catch(SQLException e){
            System.out.println("Fallo la conexion ");
            e.printStackTrace();
            return connection;
                                 }
        if(connection!=null){
           // System.out.println("Conexion realizada!");
        }
        return connection;
         }

    public void cerrar(Connection c){
        try{
            c.close();
        }catch(Exception exception){
            exception.printStackTrace();         
        }
                                     }


}