/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import java.sql.*;

public class ejecucion {
conectate con;
  
  public ejecucion (){
    con = new conectate();
  } 
  
  /*Añade un nuevo registro*/
   public void nuevoUsuario(int no_usuario,String nombre, String domicilio){
      try {            
           try (PreparedStatement pstm = con.getConnection().prepareStatement("insert into " + 
                 "public.usuario(no_usuario,nombre,domicilio) " +
                    " values(?,?,?)")) {
               pstm.setInt(1, no_usuario);
               pstm.setString(2,nombre);
               pstm.setString(3,domicilio);                        
             
               pstm.execute();
           }            
         }catch(SQLException e){
         System.out.println(e);
      }
   }       

      public Object [][] getDatos(){
      int registros = 0;
      //obtenemos la cantidad de registros existentes en la tabla
      try{         
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(no_usuario) as total FROM public.usuario ");
          try (ResultSet res = pstm.executeQuery()) {
              res.next();
              registros = res.getInt("total");
          }
      }catch(SQLException e){
         System.out.println(e);
      }    
       
      Object[][] data = new String[registros][3];  
    //realizamos la consulta sql y llenamos los datos en "Object"
      try{    
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT " +
            " no_usuario, nombre, domicilio" +
            " FROM public.usuario" +
            " ORDER BY no_usuario");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String user = res.getString("no_usuario");
            String nom = res.getString("nombre");
            String dom = res.getString("domicilio");
            
            data[i][0] = user;            
            data[i][1] = nom;            
            data[i][2] = dom;            
         
            i++;
         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 }
  public Object [][] getDatosConsulta1(){
      int registros = 0;
      //obtenemos la cantidad de registros existentes en la tabla
      try{         
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(no_usuario) as total FROM (\n" +
"select * from usuario \n" +
"where domicilio like '%colon%' )as consulta");
          try (ResultSet res = pstm.executeQuery()) {
              res.next();
              registros = res.getInt("total");
          }
      }catch(SQLException e){
         System.out.println(e);
      }    
       
      Object[][] data = new String[registros][3];  
    //realizamos la consulta sql y llenamos los datos en "Object"
      try{    
         PreparedStatement pstm = con.getConnection().prepareStatement("select no_usuario,nombre,"
                 + "domicilio from usuario \n" +
"where domicilio like '%colon%' ");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String user = res.getString("no_usuario");
            String nom = res.getString("nombre");
            String dom = res.getString("domicilio");
            
            data[i][0] = user;            
            data[i][1] = nom;            
            data[i][2] = dom;            
         
            i++;
         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 }
   public Object [][] getDatosConsulta2(){
      int registros = 0;
      //obtenemos la cantidad de registros existentes en la tabla
      try{         
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(no_usuario) as total FROM (\n" +
"select usuario.no_usuario,usuario.nombre, usuario.domicilio from usuario\n" +
"inner join prestamo on usuario.no_usuario = prestamo.no_usuario\n" +
"where prestamo.fecha < current_date)as consulta");
          try (ResultSet res = pstm.executeQuery()) {
              res.next();
              registros = res.getInt("total");
          }
      }catch(SQLException e){
         System.out.println(e);
      }    
       
      Object[][] data = new String[registros][3];  
    //realizamos la consulta sql y llenamos los datos en "Object"
      try{    
         PreparedStatement pstm = con.getConnection().prepareStatement("select usuario.no_usuario,usuario.nombre, usuario.domicilio from usuario\n" +
"inner join prestamo on usuario.no_usuario = prestamo.no_usuario\n" +
"where prestamo.fecha < current_date");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String user = res.getString("no_usuario");
            String nom = res.getString("nombre");
            String dom = res.getString("domicilio");
            
            data[i][0] = user;            
            data[i][1] = nom;            
            data[i][2] = dom;            
         
            i++;
         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 }
public void deleteUsuario(String cod){  
            try {                
                PreparedStatement pstm = con.getConnection().prepareStatement("delete from public.usuario where no_usuario = ?");            
                
                pstm.setInt(1, Integer.parseInt(cod));                   
                pstm.execute();
                pstm.close();            
            }catch(SQLException e){
            System.out.println(e);
            }            
   }

public void updateUsuario(int no_usuario, String nombre, String domicilio){
       try {            
            PreparedStatement pstm = con.getConnection().prepareStatement("update public.usuario " +
            "set no_usuario = ? ," +
            "nombre = ? ," +
            "domicilio = ?" +
              "where no_usuario = ? ");             
            pstm.setInt(1, no_usuario);                   
            pstm.setString(2, nombre);
            pstm.setString(3, domicilio);
            pstm.setInt(4, no_usuario); 
          
            pstm.execute();
            pstm.close();            
         }catch(SQLException e){
         System.out.println(e);
      }
   }

   
}
