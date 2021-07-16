/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Devoluciones;
import java.sql.*;

public class ejecucion {
conectate con;
  
  public ejecucion (){
    con = new conectate();
  } 
  
  /*Añade un nuevo registro*/
   public void nuevaDevolucion(int folio_prestamo,int id_ejemplar, String observ){
      try {            
         try (PreparedStatement pstm = con.getConnection().prepareStatement("insert into " + 
                 "public.devolucion "+
                    " values(?,current_date,?,?)")) {
               pstm.setInt(1,folio_prestamo);
               pstm.setInt(2,id_ejemplar);
               pstm.setString(3,observ);  
             
             
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
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(folio_prestamo) as total FROM public.devolucion");
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
            " folio_prestamo,fecha_dev,id_ejemplar,observ" +
            " FROM public.devolucion" +
            " ORDER BY folio_prestamo");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String folio = res.getString("folio_prestamo");
            String fecha= res.getString("fecha_dev");
            String ejemplar= res.getString("id_ejemplar");
            String observaciones= res.getString("observ");
               
            
            data[i][0] = folio;            
            data[i][1] = fecha;            
            data[i][2] = ejemplar;            
            data[i][3] = observaciones;  
           
            i++;
         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 }

public void deleteDevolucion(String cod){  
            try {                
                PreparedStatement pstm = con.getConnection().prepareStatement("delete from public.devolucion where folio_prestamo = ?");            
                
                pstm.setInt(1, Integer.parseInt(cod));                   
                pstm.execute();
                pstm.close();            
            }catch(SQLException e){
            System.out.println(e);
            }            
   }

  public Object [][] getDatosConsulta(){
      int registros = 0;
      //obtenemos la cantidad de registros existentes en la tabla
      try{         
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(no_usuario) as total FROM \n" +
"(\n" +
"\n" +
"select  no_usuario, id_biblio, fecha_dev,id_ejemplar, observ\n" +
" from prestamo right join devolucion on fecha_dev <= current_date \n" +
"where prestamo.folio_prestamo = devolucion.folio_prestamo \n" +
") as consulta");
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
         PreparedStatement pstm = con.getConnection().prepareStatement("select  no_usuario, id_biblio, fecha_dev,id_ejemplar, observ\n" +
" from prestamo right join devolucion on fecha_dev <= current_date \n" +
"where prestamo.folio_prestamo = devolucion.folio_prestamo ");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String no_usuario = res.getString("no_usuario");
            String id_biblio= res.getString("id_biblio");
            String fecha_dev= res.getString("fecha_dev");
            String id_ejemplar= res.getString("id_ejemplar");
            String observaciones= res.getString("observ");
            
            data[i][0] = no_usuario;            
            data[i][1] = id_biblio;            
            data[i][2] = fecha_dev;            
            data[i][3] = id_ejemplar;  
            data[i][4] = observaciones; 
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
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(no_usuario) as total FROM \n" +
"(\n" +
"\n" +
"SELECT no_usuario, id_biblio,fecha,fecha_dev,id_ejemplar, observ FROM \n" +
"(\n" +
"\n" +
"select  no_usuario, id_biblio, fecha,fecha_dev,id_ejemplar, observ\n" +
" from prestamo right join devolucion on fecha_dev < current_date\n" +
"where prestamo.folio_prestamo = devolucion.folio_prestamo \n" +
") as consulta\n" +
"where fecha_dev- fecha >2\n" +
"\n" +
")as consulta2");
          try (ResultSet res = pstm.executeQuery()) {
              res.next();
              registros = res.getInt("total");
          }
      }catch(SQLException e){
         System.out.println(e);
      }    
       
      Object[][] data = new String[registros][6];  
    //realizamos la consulta sql y llenamos los datos en "Object"
      try{    
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT no_usuario, id_biblio,fecha,fecha_dev,id_ejemplar, observ FROM \n" +
"(\n" +
"\n" +
"select  no_usuario, id_biblio, fecha,fecha_dev,id_ejemplar, observ\n" +
" from prestamo right join devolucion on fecha_dev < current_date\n" +
"where prestamo.folio_prestamo = devolucion.folio_prestamo \n" +
") as consulta\n" +
"where fecha_dev- fecha >2");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String no_usuario = res.getString("no_usuario");
            String id_biblio= res.getString("id_biblio");
            String fecha = res.getString("fecha");
            String fecha_dev= res.getString("fecha_dev");
            String id_ejemplar= res.getString("id_ejemplar");
            String observaciones= res.getString("observ");
            
            data[i][0] = no_usuario;            
            data[i][1] = id_biblio;    
            data[i][2] = fecha;  
            data[i][3] = fecha_dev;            
            data[i][4] = id_ejemplar;  
            data[i][5] = observaciones; 
            i++;
         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 }

public void updateDevolucion(int folio_prestamo, int id_ejemplar, String observ){
       try {            
            PreparedStatement pstm = con.getConnection().prepareStatement("update public.devolucion " +
            "set folio_prestamo = ? ," +
            "fecha_dev = current_date," +
             "id_ejemplar = ? ," +
            "observ = ?" +
                    
              "where folio_prestamo = ? ");             
            pstm.setInt(1, folio_prestamo);                   
            pstm.setInt(2, id_ejemplar);
            pstm.setString(3, observ);
            pstm.setInt(4,folio_prestamo); 
               
            pstm.execute();
            pstm.close();            
         }catch(SQLException e){
         System.out.println(e);
      }
   }

   
}