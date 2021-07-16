
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

public class RegistrarLibro extends JFrame {
    
    private JButton aceptar,otroreg;
    private JLabel etiqueta1,etiqueta2,etiqueta3,etiqueta4,etiqueta5;
    private JPanel panelalta;
    private JTextField Text1,Text2,Text3,Text4,Text5;
 
    private int ancho=400;
    private int alto=300;
    
    public RegistrarLibro(){
       //propiedades de la ventana
       
       setSize(ancho, alto);
       getContentPane().setLayout(null);
       setTitle("Capturando datos...");
       setResizable(false);
       //panel para insertar  nuevos registros-----------------
        panelalta=new JPanel();
        Border grabado = BorderFactory.createEtchedBorder();
        Border  titulo= BorderFactory.createTitledBorder(grabado,"Registrar nuevo libro");
        panelalta.setBorder(titulo);
        panelalta.setBounds(5,5,300,220);
        panelalta.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(panelalta);
        
        
        etiqueta1 = new JLabel();
        etiqueta1.setText("ISBN:");
        etiqueta1.setForeground(Color.BLACK);
        panelalta.add(etiqueta1);
        
        Text1=new JTextField(20);
        panelalta.add(Text1);
        
        etiqueta2 =new JLabel();
        etiqueta2.setText("Titulo:");
        etiqueta2.setForeground(Color.BLACK);
        panelalta.add(etiqueta2);
        
        Text2=new JTextField(20);
        panelalta.add(Text2);

        etiqueta3 =new JLabel();
        etiqueta3.setText("Editorial:");
        etiqueta3.setForeground(Color.BLACK);
        panelalta.add(etiqueta3);
        
        Text3=new JTextField(20);
        panelalta.add(Text3);
        
           
        etiqueta4 =new JLabel();
        etiqueta4.setText("País:");
        etiqueta4.setForeground(Color.BLACK);
        panelalta.add(etiqueta4);
        
        Text4=new JTextField(20);
        panelalta.add(Text4);
        
           
        etiqueta5 =new JLabel();
        etiqueta5.setText("Anio:");
        etiqueta5.setForeground(Color.BLACK);
        panelalta.add(etiqueta5);
        
        Text5=new JTextField(20);
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
                    int isbn=Integer.parseInt(Text1.getText()) ;
                    String titulo=Text2.getText();
                    String editorial= Text3.getText();
                    String pais= Text4.getText();
                    int anio= Integer.parseInt(Text5.getText()) ;
                    insertarRegistro(isbn,titulo,editorial,pais,anio);
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
   
    public void insertarRegistro(int isbn,String titulo, String editorial,String pais, int anio){
     ////****************************
                   Connection con=null;
                   Connbd c = new Connbd();
                   try{
                   con= c.conectar();
       
          PreparedStatement ps=con.prepareStatement( "INSERT INTO biblioteca.libro VALUES (?,?,?,?,?)");
            ps.setInt(1,isbn);
            ps.setString(2,titulo);
            ps.setString(3,editorial);
            ps.setString(4,pais);
            ps.setInt(5,anio);
            ResultSet rs=ps.executeQuery();
            rs.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        if(con!=null){
            JOptionPane.showMessageDialog (null,"Libro registrado con exito");
            c.cerrar(con);
        }
    }
    
    public Container extraerContentPane(){
      return getContentPane();
                                         }
}