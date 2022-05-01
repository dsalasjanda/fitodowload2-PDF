/*    */ package es.salassoft.fitodowload.guitools;
/*    */ 
/*    */ import javax.swing.BoxLayout;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class LoadingPanel extends JPanel
/*    */ {
/*    */   public LoadingPanel()
/*    */   {
/* 12 */     BoxLayout layoutMgr = new BoxLayout(this, 3);
/* 13 */     setLayout(layoutMgr);
/*    */     
/* 15 */     ClassLoader cldr = getClass().getClassLoader();
/* 16 */     java.net.URL imageURL = cldr.getResource("img/bar-circle.gif");
/* 17 */     ImageIcon imageIcon = new ImageIcon(imageURL);
/* 18 */     JLabel iconLabel = new JLabel();
/* 19 */     iconLabel.setIcon(imageIcon);
/* 20 */     imageIcon.setImageObserver(iconLabel);
/*    */     
/* 22 */     JLabel label = new JLabel("Descragando...");
/* 23 */     add(iconLabel);
/* 24 */     add(label);
/*    */   }
/*    */ }


/* Location:              C:\Users\salas\Downloads\fitoDowload\fitoDowload\fitodowloader.jar!\es\salassoft\fitodowload\guitools\LoadingPanel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */