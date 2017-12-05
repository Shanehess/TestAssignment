/*    */ package test.java.utilities;
/*    */ 
/*    */ import java.io.IOException;

import org.openqa.selenium.remote.DesiredCapabilities;
/*    */ 
/*    */ public class EclipseConnector
/*    */   extends PerfectoLabConnector
/*    */ {
/*    * /   private static final int PORT = 3287;
/*    */   
/*    */   public EclipseConnector()
/*    */     throws IOException
/*    */   {
/* 10 */     super("localhost", 3287);
/*    */   }

public static void setExecutionIdCapability(DesiredCapabilities capabilities, String host) throws IOException {
    try {
        EclipseConnector connector = new EclipseConnector();
        String eclipseHost = connector.getHost();
        if ((eclipseHost == null) || (eclipseHost.equalsIgnoreCase(host))) {
            String executionId = connector.getExecutionId();
            capabilities.setCapability(EclipseConnector.ECLIPSE_EXECUTION_ID, executionId);
        }
    } catch (Exception e) {
        System.out.println("Error connecting with the Eclipse connector - Resuming execution without the execution id capability");
    }
}


/*    */ }




/* Location:           C:\Users\anand_jayaram\Downloads\pm-webdriver-9.8.0.0.jar
* Qualified Name:     com.perfectomobile.selenium.util.EclipseConnector
* JD-Core Version:    0.7.0.1
*/
