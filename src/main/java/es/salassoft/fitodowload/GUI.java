package es.salassoft.fitodowload;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;

import org.apache.log4j.Level;

import es.salassoft.fitodowload.guitools.MainLogger;

public class GUI extends JFrame
{
  private JTextPane textArea;
  private Core core;
  private JButton btoDescargar;
  private JLabel lblRutaDescarga;
  
  public GUI() { setResizable(false);
    setTitle("FitoDowload");
    
    setDefaultCloseOperation(2);
    setSize(401, 502);
    this.core = new Core();
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
    

    getContentPane().setLayout(null);
    
  

    this.textArea = new JTextPane();
    this.textArea.setEditable(false);
    this.textArea.addCaretListener(new javax.swing.event.CaretListener() {
      public void caretUpdate(CaretEvent e) {
        String s = GUI.this.textArea.getText();
        System.out.println(s.length());
      }
    });
    MainLogger.init(
      new es.salassoft.fitodowload.guitools.JTextPaneAppender(this.textArea), 
      Level.INFO);
    JScrollPane scrollPanelTextArea = new JScrollPane(this.textArea);
     scrollPanelTextArea.setBounds(10, 80, 370, 256);
     getContentPane().add(scrollPanelTextArea);
   

    

    this.btoDescargar = new JButton("Descargar");
    this.btoDescargar.setBounds(6, 341, 374, 35);
    this.btoDescargar.setEnabled(true);
    this.btoDescargar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        GUI.this.ejecutarDescarga();
      }
      
    });
    getContentPane().add(this.btoDescargar);
    
    this.lblRutaDescarga = new JLabel("Carpeta Descarga: " + this.core.getDirectorioDescargas());
    this.lblRutaDescarga.setFont(new Font("Tahoma", 0, 14));
    this.lblRutaDescarga.setBounds(10, 34, 305, 17);
    getContentPane().add(this.lblRutaDescarga);
    
    JButton btnRuta = new JButton("Seleccionar Carpeta Descarga");
    btnRuta.setBounds(10, 11, 226, 23);
    btnRuta.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        GUI.this.seleccionarRutaDescarga();
      }
      
    });
    getContentPane().add(btnRuta);

  }
  
  private void seleccionarRutaDescarga() {
    JFileChooser j = new JFileChooser();
    j.setFileSelectionMode(1);
    Integer opt = Integer.valueOf(j.showSaveDialog(this));
    String path = j.getSelectedFile().getAbsolutePath();
    this.lblRutaDescarga.setText("Carpeta Descarga: " + path);
    this.core.setDirectorioDescargas(path);
  }
  
  private void cargarCultivos() {
 		 core.ejecutarDescarga();
  }
  




  

  private void ejecutarDescarga()
  {
    Thread hiloCarga = new Thread(new Runnable()
    {
      public void run() {
		GUI.this.btoDescargar.setEnabled(false);
        GUI.this.core.ejecutarDescarga();
		GUI.this.btoDescargar.setEnabled(true);
      }
    }, "Descarga exceles");
    hiloCarga.start();
  }
  
  
  public static void main(String[] args)
  {
    GUI gui = new GUI();
    
    gui.setVisible(true);
  }
}



