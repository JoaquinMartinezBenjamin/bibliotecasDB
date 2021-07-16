package Libros;

import java.sql.*;

public class ejecucion {
conectate con;
  
  public ejecucion (){
    con = new conectate();
  } 
  
  /*Añade un nuevo registro*/
   public void nuevoLibro(int isbn,String titulo, String editorial, String pais, int anio){
      try {            
           try (PreparedStatement pstm = con.getConnection().prepareStatement("insert into " + 
                 "public.libro(isbn,titulo,editorial,pais,anio) " +
                    " values(?,?,?,?,?)")) {
               pstm.setInt(1,isbn);
               pstm.setString(2,titulo);
               pstm.setString(3,editorial);  
               pstm.setString(4,pais);  
               pstm.setInt(5,anio);
             
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
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(isbn) as total FROM public.libro");
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
            " isbn,titulo,editorial,pais,anio" +
            " FROM public.libro" +
            " ORDER BY isbn");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String isbn = res.getString("isbn");
            String titulo= res.getString("titulo");
            String editorial= res.getString("editorial");
              String pais= res.getString("pais");
                String anio= res.getString("anio");
            
            data[i][0] = isbn;            
            data[i][1] = titulo;            
            data[i][2] = editorial;            
            data[i][3] = pais;  
            data[i][4] = anio;  
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
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(isbn) as total FROM \n" +
"(\n" +
"\n" +
"select libro.isbn,titulo,editorial,pais,anio\n" +
"from libro \n" +
"inner join  ejemplar on estado_conservacion = 'm'\n" +
"where pais = 'mexico'\n" +
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
         PreparedStatement pstm = con.getConnection().prepareStatement("select libro.isbn,titulo,editorial,pais,anio\n" +
"from libro \n" +
"inner join  ejemplar on estado_conservacion = 'm'\n" +
"where pais = 'mexico'");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String isbn = res.getString("isbn");
            String titulo= res.getString("titulo");
            String editorial= res.getString("editorial");
              String pais= res.getString("pais");
                String anio= res.getString("anio");
            
            data[i][0] = isbn;            
            data[i][1] = titulo;            
            data[i][2] = editorial;            
            data[i][3] = pais;  
            data[i][4] = anio;  
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
         PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(isbn) as total FROM \n" +
"(\n" +
"select * from libro where titulo like '%dato%' \n" +
") as contar");
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
         PreparedStatement pstm = con.getConnection().prepareStatement("select isbn, titulo,editorial,pais,anio\n" +
" from libro where titulo like '%dato%' \n" +
"");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String isbn = res.getString("isbn");
            String titulo= res.getString("titulo");
            String editorial= res.getString("editorial");
              String pais= res.getString("pais");
                String anio= res.getString("anio");
            
            data[i][0] = isbn;            
            data[i][1] = titulo;            
            data[i][2] = editorial;            
            data[i][3] = pais;  
            data[i][4] = anio;  
            i++;
         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 }

public void deleteLibro(String cod){  
            try {                
                PreparedStatement pstm = con.getConnection().prepareStatement("delete from public.libro where isbn = ?");            
                
                pstm.setInt(1, Integer.parseInt(cod));                   
                pstm.execute();
                pstm.close();            
            }catch(SQLException e){
            System.out.println(e);
            }            
   }

public void updateLibro(int isbn, String titulo, String editorial, String pais, int anio){
       try {            
            PreparedStatement pstm = con.getConnection().prepareStatement("update public.libro " +
            "set isbn = ? ," +
            "titulo = ? ," +
            "editorial = ?," +
                     "pais = ?," +
                     "anio = ?" +
              "where isbn = ? ");             
            pstm.setInt(1, isbn);                   
            pstm.setString(2, titulo);
            pstm.setString(3, editorial);
            pstm.setString(4, pais); 
                pstm.setInt(5,anio); 
                  pstm.setInt(6, isbn); 
          
            pstm.execute();
            pstm.close();            
         }catch(SQLException e){
         System.out.println(e);
      }
   }

   
}