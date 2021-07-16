/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecabd;

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

public class RegistrarEjemplar extends JFrame {
    
    private JButton aceptar,otroreg;
    private JLabel etiqueta1,etiqueta2,etiqueta3,etiqueta4,etiqueta5;
    private JPanel panelalta;
    private JTextField Text1,Text2,Text3,Text4,Text5;
    private JComboBox combo,combo2;
 
    private int ancho=400;
    private int alto=300;
    
    public RegistrarEjemplar(){
       //propiedades de la ventana
       
       setSize(ancho, alto);
       getContentPane().setLayout(null);
       setTitle("Capturando datos...");
       setResizable(false);
       //panel para insertar  nuevos registros-----------------
        panelalta=new JPanel();
        Border grabado = BorderFactory.createEtchedBorder();
        Border  titulo= BorderFactory.createTitledBorder(grabado,"Registrar nuevo ejemplar");
        panelalta.setBorder(titulo);
        panelalta.setBounds(5,5,300,220);
        panelalta.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(panelalta);
        
        
        etiqueta1 = new JLabel();
        etiqueta1.setText("Id ejemplar:");
        etiqueta1.setForeground(Color.BLACK);
        panelalta.add(etiqueta1);
        
        Text1=new JTextField(17);
        panelalta.add(Text1);
        
        etiqueta2 =new JLabel();
        etiqueta2.setText("ISBN:");
        etiqueta2.setForeground(Color.BLACK);
        panelalta.add(etiqueta2);
        
        Text2=new JTextField(15);
        panelalta.add(Text2);

        etiqueta3 =new JLabel();
        etiqueta3.setText("Estado conservación:");
        etiqueta3.setForeground(Color.BLACK);
        panelalta.add(etiqueta3);
        
        combo =new JComboBox(); 
        combo.addItem("b"); 
        combo.addItem("m");
        combo.addItem("r");
        combo.addItem("x");
        panelalta.add(combo);
           
       
        
           
        etiqueta5 =new JLabel();
        etiqueta5.setText("Observaciones:");
        etiqueta5.setForeground(Color.BLACK);
        panelalta.add(etiqueta5);
        
        Text5=new JTextField(15);
        panelalta.add(Text5);
        
        
        //----------propiedades del boton aceptar
        aceptar=new JButton("Registrar");
        getContentPane().add(aceptar);
        aceptar.setBounds(10,240,120, 25);

        //--------propiedades del boton otro registro------
        otroreg=new JButton("Otro registro");
        getContentPane().add(otroreg);
        otroreg.setBounds(136,240,120, 25);
        ActionListener accion = new java.awt.event.ActionListener(){
               public void actionPerformed(java.awt.event.ActionEvent evento){
                   Object obj=evento.getSource();
                   if (obj==aceptar){
                    int id_ejemplar=Integer.parseInt(Text1.getText()) ;
                    int isbn=Integer.parseInt(Text2.getText());
                    String estadoConservacion= (String)combo.getSelectedItem();
                  
                    String observaciones= Text5.getText();
                    insertarRegistro(id_ejemplar,isbn,estadoConservacion,observaciones);
                   }
                   
                 
                   if (obj==otroreg) {
                       Text1.setText(null);
                       Text2.setText(null);
                       Text3.setText(null);
                       Text4.setText(null);
                       Text5.setText(null);
                       }
               }
       };
       aceptar.addActionListener(accion);
       otroreg.addActionListener(accion);
    }
   
    public void insertarRegistro(int id_ejem,int isbn,String estadoConservacion,String observaciones){
     ////****************************
                   Connection con=null;
                   Connbd c = new Connbd();
                   try{
                   con= c.conectar();
       
          PreparedStatement ps=con.prepareStatement( "INSERT INTO biblioteca.ejemplar VALUES (?,?,?,?,?)");
            ps.setInt(1,id_ejem);
            ps.setInt(2,isbn);
            ps.setString(3,estadoConservacion);
            ps.setBoolean(4,false);
            ps.setString(5,observaciones);
            ResultSet rs=ps.executeQuery();
            rs.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        if(con!=null){
            JOptionPane.showMessageDialog (null,"Operacion completada");
            c.cerrar(con);
        }
    }
    
    public Container extraerContentPane(){
      return getContentPane();
                                         }
}
