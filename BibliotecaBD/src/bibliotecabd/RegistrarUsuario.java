
package bibliotecabd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.border.*;
public class RegistrarUsuario extends JFrame {
    
   
    private JButton aceptar,otroreg;
    private JLabel etiqueta1,etiqueta2,etiqueta3;
    private JPanel panelalta;
    public  JTextField Text1,Text2,Text3;
 
    private int ancho=400;
    private int alto=300;
    public RegistrarUsuario(){
        setSize(ancho, alto);
        getContentPane().setLayout(null);
        setTitle("Capturando datos...");
        setResizable(false);


        //panel para insertar  nuevos registros-----------------
        panelalta=new JPanel();
        Border grabado = BorderFactory.createEtchedBorder();
        Border  titulo= BorderFactory.createTitledBorder(grabado,"Registrar nuevo usuario");
        panelalta.setBorder(titulo);
        panelalta.setBounds(5,5,380,220);
        panelalta.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(panelalta);
        
        
        etiqueta1 = new JLabel();
        etiqueta1.setText("Nuevo numero de usuario:");
        etiqueta1.setForeground(Color.BLACK);
        panelalta.add(etiqueta1);
        
        Text1=new JTextField(15);
        panelalta.add(Text1);
        
        etiqueta2 =new JLabel();
        etiqueta2.setText("Nombre del nuevo usuario:");
        etiqueta2.setForeground(Color.BLACK);
        panelalta.add(etiqueta2);
        
        Text2=new JTextField(15);
        panelalta.add(Text2);

        
         etiqueta3 =new JLabel();
        etiqueta3.setText("Domicilio del nuevo usuario:");
        etiqueta3.setForeground(Color.BLACK);
        panelalta.add(etiqueta3);
        
        Text3=new JTextField(15);
        panelalta.add(Text3);
        
        
        
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
                    int valor=Integer.parseInt(Text1.getText()) ;
                    String nomb=Text2.getText();
                    String dom= Text3.getText();
                    insertarRegistro(valor,nomb,dom);
                                    }
                
                   if (obj==otroreg){
                   Text1.setText (null);
                   Text2.setText (null);
                   Text3.setText (null);
               }
               }
       };
       aceptar.addActionListener(accion);
       otroreg.addActionListener(accion);
    }
   
    public void insertarRegistro(int no_usuario,String nombre_usuario, String domicilio_usuario){
     ////****************************
                   Connection con=null;
                   Connbd c = new Connbd();
               try{
            con= c.conectar();
            PreparedStatement ps=con.prepareStatement( "INSERT INTO biblioteca.usuario VALUES (?, ?, ?)");
            ps.setInt(1,no_usuario);
            ps.setString(2,nombre_usuario);
            ps.setString(3,domicilio_usuario);
           ResultSet rs=ps.executeQuery();
           rs.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        if(con!=null){
            JOptionPane.showMessageDialog (null,"Usuario registrado con exito");
            c.cerrar(con);
        }
    }
    
    public Container extraerContentPane(){
           return getContentPane();
                                         }
}