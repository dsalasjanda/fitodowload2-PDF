/*     */ package es.salassoft.fitodowload.guitools;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextPane;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.event.CaretEvent;
/*     */ import org.apache.log4j.Level;
/*     */ 
/*     */ public class TestFrame extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  23 */   private JPanel jContentPane = null;
/*     */   
/*  25 */   private JScrollPane jScrollPane = null;
/*     */   
/*  27 */   private JTextPane jTextPane = null;
/*     */   
/*  29 */   private JButton jButton = null;
/*     */   
/*  31 */   private JPanel jPanel = null;
/*     */   
/*  33 */   private JButton jButton1 = null;
/*     */   
/*  35 */   private JButton jButton2 = null;
/*     */   
/*  37 */   private JButton jButton3 = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private JScrollPane getJScrollPane()
/*     */   {
/*  45 */     if (this.jScrollPane == null) {
/*  46 */       this.jScrollPane = new JScrollPane();
/*  47 */       this.jScrollPane.setMaximumSize(new Dimension(800, 300));
/*  48 */       this.jScrollPane.setMinimumSize(new Dimension(800, 300));
/*  49 */       this.jScrollPane.setPreferredSize(new Dimension(800, 300));
/*  50 */       this.jScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "JtextPane Log", 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
/*  51 */       this.jScrollPane.setViewportView(getJTextPane());
/*     */     }
/*  53 */     return this.jScrollPane;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private JTextPane getJTextPane()
/*     */   {
/*  62 */     if (this.jTextPane == null) {
/*  63 */       this.jTextPane = new JTextPane();
/*  64 */       this.jTextPane.setBackground(Color.BLUE);
/*  65 */       this.jTextPane.setEditable(false);
/*  66 */       this.jTextPane.addCaretListener(new javax.swing.event.CaretListener() {
/*     */         public void caretUpdate(CaretEvent e) {
/*  68 */           String s = TestFrame.this.getJTextPane().getText();
/*  69 */           System.out.println(s.length());
/*     */         }
/*     */       });
/*     */     }
/*  73 */     return this.jTextPane;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private JButton getJButton()
/*     */   {
/*  82 */     if (this.jButton == null) {
/*  83 */       this.jButton = new JButton();
/*  84 */       this.jButton.setText("AddError");
/*  85 */       this.jButton.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/*  87 */           MainLogger.logError("An Error");
/*     */         }
/*     */       });
/*     */     }
/*     */     
/*  92 */     return this.jButton;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/* 101 */     if (this.jPanel == null) {
/* 102 */       this.jPanel = new JPanel();
/* 103 */       this.jPanel.setLayout(new BoxLayout(getJPanel(), 1));
/* 104 */       this.jPanel.add(getJButton(), null);
/* 105 */       this.jPanel.add(getJButton1(), null);
/* 106 */       this.jPanel.add(getJButton2(), null);
/* 107 */       this.jPanel.add(getJButton3(), null);
/*     */     }
/* 109 */     return this.jPanel;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private JButton getJButton1()
/*     */   {
/* 118 */     if (this.jButton1 == null) {
/* 119 */       this.jButton1 = new JButton();
/* 120 */       this.jButton1.setText("Add Exception");
/* 121 */       this.jButton1.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/*     */           try {
/* 124 */             throw new Exception("This is a custom exception");
/*     */           } catch (Exception e1) {
/* 126 */             MainLogger.logError(e1);
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/* 131 */     return this.jButton1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private JButton getJButton2()
/*     */   {
/* 140 */     if (this.jButton2 == null) {
/* 141 */       this.jButton2 = new JButton();
/* 142 */       this.jButton2.setText("Add Warning");
/* 143 */       this.jButton2.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 145 */           MainLogger.logWarn("This is a warning!!");
/*     */         }
/*     */       });
/*     */     }
/* 149 */     return this.jButton2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private JButton getJButton3()
/*     */   {
/* 158 */     if (this.jButton3 == null) {
/* 159 */       this.jButton3 = new JButton();
/* 160 */       this.jButton3.setText("Add Trace");
/* 161 */       this.jButton3.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 163 */           MainLogger.logTrace("This is a Trace message!");
/*     */         }
/*     */       });
/*     */     }
/* 167 */     return this.jButton3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 175 */     SwingUtilities.invokeLater(new Runnable() {
/*     */       public void run() {
/* 177 */         TestFrame thisClass = new TestFrame();
/* 178 */         thisClass.setDefaultCloseOperation(3);
/* 179 */         thisClass.setVisible(true);
/* 180 */         MainLogger.init(
/* 181 */           new JTextPaneAppender(thisClass.getJTextPane()), 
/* 182 */           Level.TRACE);
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public TestFrame()
/*     */   {
/* 192 */     initialize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void initialize()
/*     */   {
/* 201 */     setSize(800, 600);
/* 202 */     setMaximumSize(new Dimension(800, 600));
/* 203 */     setMinimumSize(new Dimension(800, 600));
/* 204 */     setPreferredSize(new Dimension(800, 600));
/* 205 */     setContentPane(getJContentPane());
/* 206 */     setTitle("JFrame");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/* 215 */     if (this.jContentPane == null) {
/* 216 */       this.jContentPane = new JPanel();
/* 217 */       this.jContentPane.setLayout(new BorderLayout());
/* 218 */       this.jContentPane.add(getJScrollPane(), "South");
/* 219 */       this.jContentPane.add(getJPanel(), "North");
/*     */     }
/* 221 */     return this.jContentPane;
/*     */   }
/*     */ }


/* Location:              C:\Users\salas\Downloads\fitoDowload\fitoDowload\fitodowloader.jar!\es\salassoft\fitodowload\guitools\TestFrame.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */