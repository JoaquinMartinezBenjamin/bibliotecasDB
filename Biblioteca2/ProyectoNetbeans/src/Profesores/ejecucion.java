/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Profesores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;

public class ejecucion {
Usuarios.conectate con;
  
  public ejecucion (){
    con = new Usuarios.conectate();
  } 
  
  /*Añade un nuevo registro*/
   public void nuevoProfesor(int no_usuario,int no_tarjeta, String depto_ad, String titulo){
      try {            
           try (PreparedStatement pstm = con.getConnection().prepareStatement("insert into " + 
                 "public.profesor(no_usuario,no_tarjeta,depto_ad,titulo) " +
                    " values(?,?,?,?)")) {
               pstm.setInt(1, no_usuario);
               pstm.setInt(2,no_tarjeta);
               pstm.setString(3,depto_ad); 
               pstm.setString(4,titulo);   
             
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
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(no_usuario) as total FROM public.profesor ");
          try (ResultSet res = pstm.executeQuery()) {
              res.next();
              registros = res.getInt("total");
          }
      }catch(SQLException e){
         System.out.println(e);
      }    
       
      Object[][] data = new String[registros][4];  
    //realizamos la consulta sql y llenamos los datos en "Object"
      try{    
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT " +
            " no_usuario, no_tarjeta,depto_ad,titulo" +
            " FROM public.profesor" +
            " ORDER BY no_usuario");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String no_usuario = res.getString("no_usuario");
            String no_tarjeta = res.getString("no_tarjeta");
            String departamento = res.getString("depto_ad");
            String titulo = res.getString("titulo");
            
            data[i][0] = no_usuario;            
            data[i][1] = no_tarjeta;            
            data[i][2] = departamento;            
            data[i][3] = titulo;   
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
"select * from profesor\n" +
"where titulo like '%ingenier%' )as consulta ");
          try (ResultSet res = pstm.executeQuery()) {
              res.next();
              registros = res.getInt("total");
          }
      }catch(SQLException e){
         System.out.println(e);
      }    
       
      Object[][] data = new String[registros][4];  
    //realizamos la consulta sql y llenamos los datos en "Object"
      try{    
         PreparedStatement pstm = con.getConnection().prepareStatement("select * from profesor\n" +
"where titulo like '%ingenier%' ");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String no_usuario = res.getString("no_usuario");
            String no_tarjeta = res.getString("no_tarjeta");
            String departamento = res.getString("depto_ad");
            String titulo = res.getString("titulo");
            
            data[i][0] = no_usuario;            
            data[i][1] = no_tarjeta;            
            data[i][2] = departamento;            
            data[i][3] = titulo;   
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
"\n" +
"select profesor.no_usuario, no_tarjeta, titulo,fecha from profesor\n" +
"inner join prestamo on prestamo.no_usuario = profesor.no_usuario\n" +
") as consulta");
          try (ResultSet res = pstm.executeQuery()) {
              res.next();
              registros = res.getInt("total");
          }
      }catch(SQLException e){
         System.out.println(e);
      }    
       
      Object[][] data = new String[registros][4];  
    //realizamos la consulta sql y llenamos los datos en "Object"
      try{    
         PreparedStatement pstm = con.getConnection().prepareStatement("select profesor.no_usuario, no_tarjeta,depto_ad,fecha from profesor\n" +
"inner join prestamo on prestamo.no_usuario = profesor.no_usuario");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String no_usuario = res.getString("no_usuario");
            String no_tarjeta = res.getString("no_tarjeta");
            String departamento = res.getString("depto_ad");
            String titulo = res.getString("fecha");
            
            data[i][0] = no_usuario;            
            data[i][1] = no_tarjeta;            
            data[i][2] = departamento;            
            data[i][3] = titulo;   
            i++;
         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 }
public void deleteProfesor(String cod){  
            try {                
                PreparedStatement pstm = con.getConnection().prepareStatement("delete from public.profesor where no_usuario = ?");            
                
                pstm.setInt(1, Integer.parseInt(cod));                   
                pstm.execute();
                pstm.close();            
            }catch(SQLException e){
            System.out.println(e);
            }            
   }

public void updateProfesor(int no_usuario,int no_tarjeta, String depto_ad, String titulo){
       try {            
            PreparedStatement pstm = con.getConnection().prepareStatement("update public.profesor " +
            "set no_usuario = ? ," +
            "no_tarjeta = ? ," +
            "depto_ad = ?," +
            "titulo = ?" +
             "where no_usuario = ? ");             
            pstm.setInt(1, no_usuario);                   
            pstm.setInt(2,no_tarjeta);
            pstm.setString(3, depto_ad);
            pstm.setString(4, titulo); 
            pstm.setInt(5, no_usuario);
            pstm.execute();
            pstm.close();            
         }catch(SQLException e){
         System.out.println(e);
      }
   }

   
}
