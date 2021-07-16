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
public class EliminarUsuario extends JFrame {
    
   
    private JButton aceptar,otroreg;
    private JLabel etiqueta1;
    private JPanel panelalta;
    public  JTextField Text1;
 
    private int ancho=400;
    private int alto=300;
    
    public EliminarUsuario(){
       //propiedades de la ventana
        setSize(ancho, alto);
        getContentPane().setLayout(null);
        setResizable(false);
       //panel para insertar  nuevos registros-----------------
        panelalta=new JPanel();
        Border grabado = BorderFactory.createEtchedBorder();
        Border  titulo= BorderFactory.createTitledBorder(grabado,"Eliminar usuario");
        panelalta.setBorder(titulo);
        panelalta.setBounds(5,5,380,220);
        panelalta.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(panelalta);
        
        etiqueta1 = new JLabel();
        etiqueta1.setText("Introduzca el numero del usuario a borrar:");
        etiqueta1.setForeground(Color.BLACK);
        panelalta.add(etiqueta1);
        
        Text1=new JTextField(15);
        panelalta.add(Text1);
        //----------propiedades del boton aceptar
        aceptar=new JButton("Eliminar");
        getContentPane().add(aceptar);
        aceptar.setBounds(10,240,120, 25);

        //--------propiedades del boton otro registro------
        otroreg=new JButton("Limpiar");
        getContentPane().add(otroreg);
        otroreg.setBounds(136,240,120, 25);

       
       ActionListener accion = new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evento){
                   Object obj=evento.getSource();
                   if (obj==aceptar){
                    int valor=Integer.parseInt(Text1.getText()) ;
                    if(buscarRegistro(valor)){
                    eliminarRegistro(valor);
                                              }
                                     }
                  
                   if (obj==otroreg){
                       Text1.setText (null);
                   }
               }
       };
       aceptar.addActionListener(accion);
       otroreg.addActionListener(accion);
    }
    
    public boolean buscarRegistro(int no_usuario){
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
                JOptionPane.showMessageDialog (null,"Registro no encontrado");
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
    
    public void eliminarRegistro(int no_usuario){
                  Connection con=null;
                  Connbd c = new Connbd();
            try{
            con= c.conectar();
            PreparedStatement ps=con.prepareStatement( "delete from biblioteca.usuario where no_usuario= ?;");
            ps.setInt(1,no_usuario);
            ResultSet rs=ps.executeQuery();
            rs.close();
              }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        if(con!=null){
            JOptionPane.showMessageDialog (null,"Usuario eliminado con exito");
            c.cerrar(con);
        }
            }
 
    public Container extraerContentPane(){
           return getContentPane();
                                        }
}