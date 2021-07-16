/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecabd;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RegistrarPrestamo extends JFrame {
     private JButton aceptar,otroreg,otroejem;
    private JLabel etiqueta1,etiqueta2,etiqueta3,etiqueta4,etiqueta5;
    private JPanel panelalta;
    private  JTextField Text1,Text2,Text3,Text4,Text5;
    private JComboBox combo,combo2;
    
    private int contadorEjemplares=0;
 
    private int ancho=400;
    private int alto=300;
    
    public RegistrarPrestamo(){
        setSize(ancho, alto);
        getContentPane().setLayout(null);
       // setTitle("Capturando datos...");
        setResizable(false);


        //panel para insertar  nuevos registros-----------------
        panelalta=new JPanel();
        Border grabado = BorderFactory.createEtchedBorder();
        Border  titulo= BorderFactory.createTitledBorder(grabado,"Registrar nuevo prestamo");
        panelalta.setBorder(titulo);
        panelalta.setBounds(5,5,280,220);
        panelalta.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(panelalta);
        
        
        etiqueta1 = new JLabel();
        etiqueta1.setText("Folio:");
        etiqueta1.setForeground(Color.BLACK);
        panelalta.add(etiqueta1);
        
        Text1=new JTextField(15);
        //Text1.setEditable(false);
        panelalta.add(Text1);
        
        etiqueta2 =new JLabel();
        etiqueta2.setText("No_usuario:");
        etiqueta2.setForeground(Color.BLACK);
        panelalta.add(etiqueta2);
        
        Text2=new JTextField(10);
        panelalta.add(Text2);

        
         etiqueta3 =new JLabel();
        etiqueta3.setText("Id bibliotecario:");
        etiqueta3.setForeground(Color.BLACK);
        panelalta.add(etiqueta3);
        
          Text3=new JTextField(10);
        panelalta.add(Text3);
        
        etiqueta4 =new JLabel();
        etiqueta4.setText("Id ejemplar:");
        etiqueta4.setForeground(Color.BLACK);
        panelalta.add(etiqueta4);
        
          Text4=new JTextField(17);
        panelalta.add(Text4);
        
        etiqueta5 =new JLabel();
        etiqueta5.setText("Observaciones :");
        etiqueta5.setForeground(Color.BLACK);
        panelalta.add(etiqueta5);
        
          Text5=new JTextField(17);
        panelalta.add(Text5);
        
        //----------propiedades del boton aceptar
        aceptar=new JButton("Registrar");
        getContentPane().add(aceptar);
        aceptar.setBounds(10,240,120, 25);

        //--------propiedades del boton otro registro------
        otroreg=new JButton("Otro registro");
        getContentPane().add(otroreg);
        otroreg.setBounds(136,240,120, 25);

        otroejem=new JButton("Otro ejemplar");
        getContentPane().add(otroejem);
        otroejem.setBounds(262,240,120, 25);
         buscarRegistros buscar = new buscarRegistros();
                       
        
 ActionListener accion = new java.awt.event.ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent evento){
                   Object obj=evento.getSource();
                   
                   
                   if (obj==aceptar){
                      
                       if(contadorEjemplares==0){
                    int folio=Integer.parseInt(Text1.getText()) ;
                    int no_usuario=Integer.parseInt(Text2.getText()); 
                    int id_bibliotecario= Integer.parseInt(Text3.getText()) ;
                    
                    int id_ejemplar= Integer.parseInt(Text4.getText()) ;
                    String observaciones=Text5.getText();
                    //validacion de datos
                  if(buscar.buscarUsuario(no_usuario)
                  && buscar.buscarBibliotecario(id_bibliotecario)
                  && buscar.buscarEjemplar(id_ejemplar)){
                    insertarRegistro(folio,no_usuario,id_bibliotecario,id_ejemplar,observaciones);
                  }
                  //***********************
                       }else{
                           int folio=Integer.parseInt(Text1.getText()) ;
                    int no_usuario=Integer.parseInt(Text2.getText()); 
                    int id_bibliotecario= Integer.parseInt(Text3.getText()) ;
                        
                    int id_ejemplar= Integer.parseInt(Text4.getText()) ;
                    String observaciones=Text5.getText();
                           nuevoEjemplar(folio,no_usuario,id_bibliotecario); }
                                    }
                
                   if (obj==otroreg){
                   Text1.setText ("");
                   Text2.setText ("");
                   Text3.setText ("");
                   Text4.setText ("");
                   Text5.setText ("");
                   Text1.setEditable(true);
                   Text2.setEditable(true);
                   Text3.setEditable(true);
                   contadorEjemplares=0;
                   }
                   
                   if(obj==otroejem){
                          Text4.setText ("");
                          Text5.setText ("");
                             Text1.setEditable(false);
                   Text2.setEditable(false);
                   Text3.setEditable(false);
                    }
                   }
               
       };
       aceptar.addActionListener(accion);
       otroreg.addActionListener(accion);
       otroejem.addActionListener(accion);
    }
   
public void nuevoEjemplar(int folio, int no_usuario, int id_bibliotecario){
if(contadorEjemplares<3){
                              
                  
                    
                    int id_ejemplar= Integer.parseInt(Text4.getText()) ;
                    String observaciones=Text5.getText();
                   
                   otroEjemplar(folio,no_usuario,id_bibliotecario,id_ejemplar,observaciones);
                    }else{JOptionPane.showMessageDialog(null,"Lo sentimos, solo se permiten tres ejemplares por prestamo");
}
}

    public void insertarRegistro(int folio,int no_usuario, int id_bibliotecario, int id_ejemplar, String  observaciones){
     ////****************************
                   Connection con=null;
                   Connbd c = new Connbd();
               try{
            con= c.conectar();
            
            
            PreparedStatement ps=con.prepareStatement( "INSERT INTO biblioteca.prestamo VALUES (?,current_date, ?,?);"
                    + "INSERT INTO biblioteca.det_prestamo VALUES(?,?,?);"
                    + "update biblioteca.ejemplar set prestado= true where id_ejemplar= ?;");
                 
            ps.setInt(1,folio);
          //  ps.setString(2,no);
            ps.setInt(2,no_usuario);
            ps.setInt(3,id_bibliotecario);
            ps.setInt(4,folio);
            ps.setInt(5,id_ejemplar);
            
            ps.setString(6,observaciones);
            ps.setInt(7,id_ejemplar);
           ResultSet rs=ps.executeQuery();
           
           rs.close();
        
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        if(con!=null){
            JOptionPane.showMessageDialog (null,"Operacion completada");
                contadorEjemplares++;
            c.cerrar(con);
        }
    }
    
    
    public void otroEjemplar(int folio,int no_usuario, int id_bibliotecario, int id_ejemplar, String  observaciones){
     ////****************************
                   Connection con=null;
                   Connbd c = new Connbd();
               try{
            con= c.conectar();
            PreparedStatement ps=con.prepareStatement(  "INSERT INTO biblioteca.det_prestamo VALUES(?,?,?);"
                    + "update biblioteca.ejemplar set prestado= true where id_ejemplar= ?;");
                  
          
            ps.setInt(1,folio);
            ps.setInt(2,id_ejemplar);
            ps.setString(3,observaciones);
            ps.setInt(4,id_ejemplar);
           ResultSet rs=ps.executeQuery();
           rs.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        if(con!=null){
                contadorEjemplares++;
            JOptionPane.showMessageDialog (null,"Operacion completada");
            c.cerrar(con);
        }
    }
    
    
    
    
    
    public boolean validarUsuario(){
    return false; 
    }
    
    
    
    public Container extraerContentPane(){
           return getContentPane();
                                         }
}
