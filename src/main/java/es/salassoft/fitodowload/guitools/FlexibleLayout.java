/*    */ package es.salassoft.fitodowload.guitools;
/*    */ 
/*    */ import org.apache.log4j.Level;
/*    */ import org.apache.log4j.spi.LoggingEvent;
/*    */ 
/*    */ public class FlexibleLayout extends org.apache.log4j.Layout
/*    */ {
/*  8 */   private String NL = System.getProperty("line.separator");
/*    */   
/*    */ 
/*    */ 
/*    */   public String format(LoggingEvent event)
/*    */   {
/* 14 */     StringBuffer sb = new StringBuffer();
/* 15 */     sb.append(event.getLevel().toString()).append(": ");
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 20 */     sb.append(event.getMessage()).append(this.NL);
/* 21 */     if (event.getThrowableInformation() != null) {
/* 22 */       String[] s = event.getThrowableStrRep();
/* 23 */       for (int i = 0; i < s.length; i++) {
/* 24 */         sb.append(s[i]).append(this.NL);
/*    */       }
/*    */     }
/*    */     
/* 28 */     return sb.toString();
/*    */   }
/*    */   
/*    */   public boolean ignoresThrowable()
/*    */   {
/* 33 */     return false;
/*    */   }
/*    */   
/*    */   public void activateOptions() {}
/*    */ }


/* Location:              C:\Users\salas\Downloads\fitoDowload\fitoDowload\fitodowloader.jar!\es\salassoft\fitodowload\guitools\FlexibleLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */