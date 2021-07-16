
package Prestamos;

import java.sql.*;

public class ejecucion {
conectate con;
  
  public ejecucion (){
    con = new conectate();
  } 
  
  /*Añade un nuevo registro*/
   public void nuevoPrestamo(int folioprestamo,int nousuario, int idbibliotecario){
      try {            
           try (PreparedStatement pstm = con.getConnection().prepareStatement("insert into " + 
                 "public.prestamo "+
                    " values(?,current_date,?,?)")) {
               pstm.setInt(1,folioprestamo);
               pstm.setInt(2,nousuario);
               pstm.setInt(3,idbibliotecario);  
          
             
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
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(folio_prestamo) as total FROM public.prestamo");
          try (ResultSet res = pstm.executeQuery()) {
              res.next();
              registros = res.getInt("total");
          }
      }catch(SQLException e){
         System.out.println(e);
      }    
       
      Object[][] data = new String[registros][5];  
    //realizamos la consulta sql y llenamos los datos en "Object"
      try{    
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT " +
            " folio_prestamo,fecha,no_usuario,id_biblio" +
            " FROM public.prestamo" +
            " ORDER BY folio_prestamo");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String folio_prestamo = res.getString("folio_prestamo");
            String fecha= res.getString("fecha");
            String no_usuario= res.getString("no_usuario");
            String id_biblio= res.getString("id_biblio");
           
            
            data[i][0] = folio_prestamo;            
            data[i][1] = fecha;            
            data[i][2] = no_usuario;            
            data[i][3] = id_biblio;  
           
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
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(folio_prestamo) as total FROM public.prestamo where fecha< current_date");
          try (ResultSet res = pstm.executeQuery()) {
              res.next();
              registros = res.getInt("total");
          }
      }catch(SQLException e){
         System.out.println(e);
      }    
       
      Object[][] data = new String[registros][5];  
    //realizamos la consulta sql y llenamos los datos en "Object"
      try{    
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT folio_prestamo, fecha,"
                 + " no_usuario, id_biblio FROM public.prestamo where fecha< current_date");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String folio_prestamo = res.getString("folio_prestamo");
            String fecha= res.getString("fecha");
            String no_usuario= res.getString("no_usuario");
            String id_biblio= res.getString("id_biblio");
           
            
            data[i][0] = folio_prestamo;            
            data[i][1] = fecha;            
            data[i][2] = no_usuario;            
            data[i][3] = id_biblio;  
           
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
"select usuario.no_usuario, nombre, domicilio from  usuario\n" +
"inner join prestamo on prestamo.no_usuario = usuario.no_usuario\n" +
"where fecha < current_date) as consulta");
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
         PreparedStatement pstm = con.getConnection().prepareStatement("select usuario.no_usuario, nombre, domicilio from  usuario\n" +
"inner join prestamo on prestamo.no_usuario = usuario.no_usuario\n" +
"where fecha < current_date");
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
public void deletePrestamo(String cod){  
            try {                
                PreparedStatement pstm = con.getConnection().prepareStatement("delete from public.prestamo where folio_prestamo = ?");            
                
                pstm.setInt(1, Integer.parseInt(cod));                   
                pstm.execute();
                pstm.close();            
            }catch(SQLException e){
            System.out.println(e);
            }            
   }

public void updatePrestamo(int folio_prestamo, int no_usuario,int id_biblio){
       try {            
            PreparedStatement pstm = con.getConnection().prepareStatement("update public.prestamo " +
            "set folio_prestamo = ? ," +
            "fecha = current_date ," +
            "no_usuario = ?," +
            "id_biblio = ?" +
                     
              "where folio_prestamo = ? ");             
            pstm.setInt(1,folio_prestamo);                   
            pstm.setInt(2, no_usuario);
            pstm.setInt(3, id_biblio);
            pstm.setInt(4, folio_prestamo); 
              
          
            pstm.execute();
            pstm.close();            
         }catch(SQLException e){
         System.out.println(e);
      }
   }

   
}