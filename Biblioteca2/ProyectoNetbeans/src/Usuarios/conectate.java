
package Usuarios;

import java.sql.*; 

public class conectate { 
   static String bd = "Biblioteca"; 
   static String login = "postgres"; 
   static String password = "topicos2016"; 
   static String url = "jdbc:postgresql://localhost:5432/"+bd; 
 
   Connection conn = null; 
    
   public conectate() { 
      try{          
         Class.forName("org.postgresql.Driver");          
         conn = DriverManager.getConnection(url,login,password); 
         if (conn!=null){ 
            System.out.println("Conexión a base de datos "+bd+". exitosa"); 
         } 
      }catch(SQLException e){ 
         System.out.println(e); 
      }catch(ClassNotFoundException e){ 
         System.out.println(e); 
      } 
   } 
    
   public Connection getConnection(){ 
      return conn; 
   } 
 
   public void desconectar(){ 
      conn = null; 
   } 
}