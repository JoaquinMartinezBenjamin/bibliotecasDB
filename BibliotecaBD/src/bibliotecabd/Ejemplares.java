/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecabd;

import java.awt.*;
import  javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class Ejemplares extends JFrame{
    
    private JButton aceptar;
    private JLabel etiqueta;
    //private JScrollPane desplazador;
    
    private String[] lista;
  
    public Ejemplares(){
        //-----propiedades de la ventana-----------
        setTitle("LIBROS DADOS DE ALTA EN LA BIBLIOTECA");
        setSize(460,300);
        setLocation(240, 200);
        getContentPane().setLayout(null);

        //------propiedades de la etiqueta----------
        etiqueta=new JLabel("Ejemplares registrados");
        etiqueta.setBounds(80,20,250,25);
        etiqueta.setForeground(Color.BLUE);
        etiqueta.setFont(new Font("Arial",Font.PLAIN,20));
        getContentPane().add(etiqueta);

        //-------propiedades del bototn aceptar----------
        aceptar=new JButton();
        aceptar.setText("Actualizar");
        getContentPane().add(aceptar);
        aceptar.setBounds(300, 10, 120, 30);
        ActionListener accion;
        accion = new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evento){
                Object obj=evento.getSource();
                if (obj==aceptar){
                    VisualizarArchivo();
                   
                }
            }};
        aceptar.addActionListener(accion);
       VisualizarArchivo();
      
    }//Fin del Constructor
    
    
     public void VisualizarArchivo() {
          int idEjemplar;
          int isbn;
          String estadoConservacion;
          boolean prestado;
          String observaciones;
          
                    String[] NombreColumnas = {"Id_ejemplar","ISBN","Estado conservación","Prestado","Observaciones"};
                    DefaultTableModel tabla = new DefaultTableModel(null,NombreColumnas);
                    JTable table = new JTable(tabla);
                    table.setBackground(Color.BLUE);
                    table.setForeground(Color.WHITE);
                    table.setFont(new Font("Arial",Font.PLAIN,14));
                    table.setPreferredScrollableViewportSize(new Dimension(50, 70));
                    JScrollPane deslizador = new JScrollPane(table);
                     deslizador.setBounds(25,50,400,370);
                    
                   
                    
                    lista=new String[5];
                    
                    ////****************************
                   
                   Connection con=null;
                   Connbd c = new Connbd();
           
        try{
            con= c.conectar();
            Statement stmt = con.createStatement();
          
ResultSet rs = stmt.executeQuery("SELECT * FROM biblioteca.ejemplar");
while (rs.next()) {
                                  idEjemplar=rs.getInt(1);
                                  isbn=rs.getInt(2);
                                  estadoConservacion=rs.getString(3);
                                  prestado=rs.getBoolean(4);
                                  observaciones=rs.getString(5);
                                  lista[0]=idEjemplar+"";
                                  lista[1]=isbn+"";
                                  lista[2]=estadoConservacion+"";
                                  lista[3]=prestado+"";
                                  lista[4]=observaciones+"";
                                  
                                  tabla.addRow(lista);
                   }
rs.close();
stmt.close();

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        if(con!=null){
            c.cerrar(con);
        }
        getContentPane().add(deslizador);
       }//cierra metodo
  
    
        public Container extraerContentPane(){
        return getContentPane();
                                             }
        
        
         
}
