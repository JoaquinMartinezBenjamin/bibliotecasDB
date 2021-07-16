/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecabd;

import java.sql.*;
import javax.swing.*;

public class buscarRegistros {
    
    public boolean estadoEjemplar(int id_ejemplar){
          boolean encontrado= false;
           Connection con=null;
            Connbd c = new Connbd();
            try{
            con= c.conectar();
            PreparedStatement ps=con.prepareStatement( "select * from biblioteca.ejemplar where id_ejemplar= ?;");
            ps.setInt(1,id_ejemplar);
            ResultSet rs=ps.executeQuery();
            
            while (rs.next()) {
                encontrado=true;
                              }
            if (encontrado==false){
                JOptionPane.showMessageDialog (null,"Ejemplar no encontrado");
            }
            rs.close();
            }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        if(con!=null){
           c.cerrar(con);
                }
        return encontrado;
   }
    
          public boolean buscarEjemplar(int id_ejemplar){
          boolean encontrado= false;
           Connection con=null;
            Connbd c = new Connbd();
            try{
            con= c.conectar();
            PreparedStatement ps=con.prepareStatement( "select * from biblioteca.ejemplar where id_ejemplar= ?;");
            ps.setInt(1,id_ejemplar);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                encontrado=true;
                              }
            if (encontrado==false){
                JOptionPane.showMessageDialog (null,"Ejemplar no encontrado");
            }
            rs.close();
            }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        if(con!=null){
           c.cerrar(con);
                }
        return encontrado;
   }
    
       public boolean buscarPrestamo(int folio_prestamo){
          boolean encontrado= false;
           Connection con=null;
            Connbd c = new Connbd();
            try{
            con= c.conectar();
            PreparedStatement ps=con.prepareStatement( "select * from biblioteca.prestamo where prestamo= ?;");
            ps.setInt(1,folio_prestamo);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                encontrado=true;
                              }
            if (encontrado==false){
                JOptionPane.showMessageDialog (null,"Registro de prestamo no encontrado");
            }
            rs.close();
            }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        if(con!=null){
           c.cerrar(con);
                }
        return encontrado;
   }
    
       public boolean buscarBibliotecario(int id_biblio){
          boolean encontrado= false;
           Connection con=null;
            Connbd c = new Connbd();
            try{
            con= c.conectar();
            PreparedStatement ps=con.prepareStatement( "select * from biblioteca.bibliotecario where id_biblio= ?;");
            ps.setInt(1,id_biblio);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                encontrado=true;
                              }
            if (encontrado==false){
                JOptionPane.showMessageDialog (null,"Bibliotecario no encontrado");
            }
            rs.close();
            }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        if(con!=null){
           c.cerrar(con);
                }
        return encontrado;
   }
    
    public boolean buscarUsuario(int no_usuario){
          boolean encontrado= false;
          Connection con=null;
          Connbd c = new Connbd();
            try{
            con= c.conectar();
            PreparedStatement ps=con.prepareStatement( "select * from biblioteca.usuario where no_usuario= ?;");
            ps.setInt(1,no_usuario);
            ResultSet rs=ps.executeQuery();
           while (rs.next()) {
                encontrado=true;
                             }
            if (encontrado==false){
                JOptionPane.showMessageDialog (null,"Usuario no encontrado");
            }
            rs.close();
            }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        if(con!=null){
            c.cerrar(con);
        }
        return encontrado;
        }
    
}
