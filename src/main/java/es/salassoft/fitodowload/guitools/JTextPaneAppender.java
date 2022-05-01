/*    */ package es.salassoft.fitodowload.guitools;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import javax.swing.JTextPane;
/*    */ import javax.swing.text.BadLocationException;
/*    */ import javax.swing.text.Style;
/*    */ import javax.swing.text.StyleConstants;
/*    */ import javax.swing.text.StyledDocument;
/*    */ import org.apache.log4j.AppenderSkeleton;
/*    */ import org.apache.log4j.Layout;
/*    */ import org.apache.log4j.Level;
/*    */ import org.apache.log4j.spi.LoggingEvent;
/*    */ 
/*    */ public class JTextPaneAppender
/*    */   extends AppenderSkeleton
/*    */ {
/* 17 */   private JTextPane _jtextpane = null;
/*    */   
/*    */   public Style _styleNormal;
/*    */   
/*    */   public Style _styleBold;
/*    */   public Style _styleRed;
/*    */   public Style _styleBlue;
/* 24 */   private Layout _pl = new FlexibleLayout();
/*    */   
/*    */   private JTextPaneAppender() {}
/*    */   
/*    */   public JTextPaneAppender(JTextPane j) {
/* 29 */     this._jtextpane = j;
/*    */     
/* 31 */     StyledDocument doc = this._jtextpane.getStyledDocument();
/*    */     
/* 33 */     this._styleNormal = doc.addStyle("regular", null);
/* 34 */     StyleConstants.setForeground(this._styleNormal, Color.BLACK);
/*    */     
/* 36 */     this._styleBlue = doc.addStyle("blue", null);
/* 37 */     StyleConstants.setForeground(this._styleBlue, Color.BLUE);
/*    */     
/* 39 */     this._styleBold = doc.addStyle("bold", this._styleNormal);
/* 40 */     StyleConstants.setBold(this._styleBold, true);
/* 41 */     this._styleRed = doc.addStyle("red", this._styleBold);
/* 42 */     StyleConstants.setForeground(this._styleRed, Color.RED);
/*    */   }
/*    */   
/*    */   protected void append(LoggingEvent event)
/*    */   {
/* 47 */     String toLog = this._pl.format(event);
/* 48 */     if (this._jtextpane != null) {
/* 49 */       if (event.getLevel().equals(Level.ERROR)) { logError(toLog);
/* 50 */       } else if (event.getLevel().equals(Level.FATAL)) { logFatal(toLog);
/* 51 */       } else if (event.getLevel().equals(Level.WARN)) { logWarning(toLog);
/* 52 */       } else if (event.getLevel().equals(Level.INFO)) logTrace(toLog);
/*    */     }
/*    */   }
/*    */   
/*    */   private void logTrace(String s) {
/* 57 */     log(s, this._styleNormal);
/*    */   }
/*    */   
/*    */   private void logWarning(String s) {
/* 61 */     log(s, this._styleBlue);
/*    */   }
/*    */   
/*    */   private void logFatal(String s) {
/* 65 */     logError(s);
/*    */   }
/*    */   
/*    */   private void logError(String s) {
/* 69 */     log(s, this._styleRed);
/*    */   }
/*    */   
/*    */   private void log(String s, Style style) {
/* 73 */     if (s == null) return;
/* 74 */     if (this._jtextpane == null) return;
/* 75 */     StyledDocument doc = this._jtextpane.getStyledDocument();
/*    */     try {
/* 77 */       doc.insertString(doc.getLength(), s, style);
/*    */     }
/*    */     catch (BadLocationException localBadLocationException) {}
/*    */   }
/*    */   
/*    */   public void close() {}
/*    */   
/*    */   public boolean requiresLayout()
/*    */   {
/* 86 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\salas\Downloads\fitoDowload\fitoDowload\fitodowloader.jar!\es\salassoft\fitodowload\guitools\JTextPaneAppender.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */