
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

public class RegistrarBibliotecario extends JFrame {
     private JButton aceptar,otroreg;
    private JLabel etiqueta1,etiqueta2,etiqueta3,etiqueta4;
    private JPanel panelalta;
    private  JTextField Text1,Text2,Text3,Text4;
    private JComboBox combo,combo2;
 
    private int ancho=400;
    private int alto=300;
    
    public RegistrarBibliotecario(){
        setSize(ancho, alto);
        getContentPane().setLayout(null);
        setTitle("Capturando datos...");
        setResizable(false);


        //panel para insertar  nuevos registros-----------------
        panelalta=new JPanel();
        Border grabado = BorderFactory.createEtchedBorder();
        Border  titulo= BorderFactory.createTitledBorder(grabado,"Registrar nuevo bibliotecario");
        panelalta.setBorder(titulo);
        panelalta.setBounds(5,5,280,220);
        panelalta.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(panelalta);
        
        
        etiqueta1 = new JLabel();
        etiqueta1.setText("Nuevo numero de bibliotecario:");
        etiqueta1.setForeground(Color.BLACK);
        panelalta.add(etiqueta1);
        
        Text1=new JTextField(5);
        panelalta.add(Text1);
        
        etiqueta2 =new JLabel();
        etiqueta2.setText("Nombre:");
        etiqueta2.setForeground(Color.BLACK);
        panelalta.add(etiqueta2);
        
        Text2=new JTextField(17);
        panelalta.add(Text2);

        
         etiqueta3 =new JLabel();
        etiqueta3.setText("Turno:");
        etiqueta3.setForeground(Color.BLACK);
        panelalta.add(etiqueta3);
        
         combo =new JComboBox(); 
        combo.addItem("Matutino"); 
        combo.addItem("Vespertino"); 
        panelalta.add(combo);
        
        etiqueta4 =new JLabel();
        etiqueta4.setText("Horario:");
        etiqueta4.setForeground(Color.BLACK);
        panelalta.add(etiqueta4);
        
         combo2 =new JComboBox(); 
        combo2.addItem("7-14"); 
        combo2.addItem("14-21"); 
        combo2.addItem("8-15"); 
        panelalta.add(combo2);
        
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
                    int id_biblio=Integer.parseInt(Text1.getText()) ;
                    String nomb=Text2.getText();
                    String turno= (String)combo.getSelectedItem();
                    String horario= (String)combo2.getSelectedItem();
                    insertarRegistro(id_biblio,nomb,turno,horario);
                                    }
                
                   if (obj==otroreg){
                   Text1.setText (null);
                   Text2.setText (null);
                
               }
               }
       };
       aceptar.addActionListener(accion);
       otroreg.addActionListener(accion);
    }
   
    public void insertarRegistro(int id_biblio,String nombre, String turno, String horario){
     ////****************************
                   Connection con=null;
                   Connbd c = new Connbd();
               try{
            con= c.conectar();
            PreparedStatement ps=con.prepareStatement( "INSERT INTO biblioteca.bibliotecario VALUES (?, ?, ?,?)");
            ps.setInt(1,id_biblio);
            ps.setString(2,nombre);
            ps.setString(3,turno);
            ps.setString(4,horario);
            
           ResultSet rs=ps.executeQuery();
           rs.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        if(con!=null){
            JOptionPane.showMessageDialog (null,"Bibliotecario registrado con exito");
            c.cerrar(con);
        }
    }
    
    public Container extraerContentPane(){
           return getContentPane();
                                         }
}
    

