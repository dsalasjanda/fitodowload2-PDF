package es.salassoft.fitodowload.guitools;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class MainLogger
{
  public static Logger log = Logger.getLogger("Logger");
  
  private static boolean init = false;
  

  public static void init(Appender appender, Level l)
  {
    if (!init) {
      log.addAppender(appender);
    }
    
    init = true;
  }
  
  public static void logWarn(String s)
  {
    log.warn(s);
  }
  
  public static void logError(String s) {
    log.error(s);
  }
  
  public static void logError(Throwable ex) {
    log.fatal(new String(), ex);
  }
  
  public static void logTrace(String s) {
    log.info(s);
  }
}


