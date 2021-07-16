
package bibliotecabd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class Principal extends JFrame{
 public JTabbedPane jTabbedPane;
 public JButton salir;
 private JScrollPane barra;
 
 public Principal() {
        setSize(1000, 750); 
        setTitle("BIBLIOTECA PUBLICA");
        setLocation(300, 159); 
        inicializaComponentes();  
    }

    private void inicializaComponentes(){
        salir=new JButton("SALIR");
        salir.setBounds(100,30,100,40);
        JPanel panelUsuarios = new JPanel();
        JPanel panelLibros = new JPanel();
        JPanel panelBibliotecarios = new JPanel();
        JPanel vacio= new JPanel();
        JPanel panelPrestamos = new JPanel();
        JPanel ejemplares = new JPanel();
        
        
        panelUsuarios.setLayout(new GridLayout(0,2,5,5));
        
         RegistrarUsuario users = new RegistrarUsuario();
         Listado lis = new Listado();
         EliminarUsuario eu = new EliminarUsuario();
         
          panelUsuarios.add(lis.extraerContentPane());
          panelUsuarios.add(users.extraerContentPane());
          panelUsuarios.add(vacio);
          panelUsuarios.add(eu.extraerContentPane());
 
         Libros l = new Libros(); 
         RegistrarLibro rl= new RegistrarLibro();
         EliminarLibro el= new EliminarLibro();
         panelLibros.setLayout(new GridLayout(0,2,5,5));
         ejemplares.setLayout(new GridLayout(0,2,5,5));
         
          Ejemplares e = new Ejemplares();
          RegistrarEjemplar re= new RegistrarEjemplar();
          
         ejemplares.add(e.extraerContentPane());
          ejemplares.add(re.extraerContentPane());
          
          panelLibros.add(el.extraerContentPane());
          panelLibros.add(l.extraerContentPane());
          panelLibros.add(rl.extraerContentPane());
          
         // panelLibros.add(vacio);
         
          RegistrarBibliotecario rb = new RegistrarBibliotecario();
          ListadoBibliotecarios lb= new ListadoBibliotecarios();
          EliminarBibliotecario eb= new EliminarBibliotecario();
          panelBibliotecarios.setLayout(new GridLayout(0,2,5,5));
          panelBibliotecarios.add(rb.extraerContentPane());
          panelBibliotecarios.add(lb.extraerContentPane());
          panelBibliotecarios.add(eb.extraerContentPane());
          
           panelPrestamos.setLayout(new GridLayout(0,2,5,5));
           Prestamos p = new Prestamos();
           panelPrestamos.add(p.extraerContentPane());
           DetallePrestamo dp= new DetallePrestamo();
           RegistrarPrestamo rp= new RegistrarPrestamo();
           panelPrestamos.add(dp.extraerContentPane());
           panelPrestamos.add(rp.extraerContentPane());
           
           
          
                     
                     
                  
    //  barra= new JScrollPane(panelLibros);
    //                 barra.setBounds(25,50,500,500);
                  
        
        jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("Usuarios",panelUsuarios);
        jTabbedPane.addTab("Prestamos",panelPrestamos);
        jTabbedPane.addTab("Devoluciones",null, null);
        jTabbedPane.addTab("Libros",panelLibros);
        jTabbedPane.addTab("Ejemplares",ejemplares);
        jTabbedPane.addTab("Bibliotecarios",panelBibliotecarios);
       add(jTabbedPane,BorderLayout.CENTER);

    }

    public static void main(String[] args){
        Principal mp=new Principal();
        mp.setVisible(true);
    }
}
