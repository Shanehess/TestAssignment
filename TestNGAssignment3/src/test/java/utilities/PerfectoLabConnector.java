      package test.java.utilities;

      import java.io.BufferedReader;
      import java.io.IOException;
      import java.io.InputStreamReader;
      import java.io.PrintWriter;
      import java.net.Socket;
      import java.net.UnknownHostException;

      import org.apache.commons.lang3.StringUtils;

     
      public class PerfectoLabConnector
      {
	//  private static final Logger _logger =
	// LoggerFactory.getLogger(PerfectoLabConnector.class);
//	     * / protected static final String LOCALHOST = "localhost";
//	     * / private static final String GET_HOST = "getHost";
//	     * / private static final String GET_USER = "getUser";
//	     * / private static final String GET_PASSWORD = "getPassword";
//	     * / private static final String GET_EXECUTION_ID = "getExecutionId";
//	     * / private static final String SET_EXECUTION_ID = "setExecutionId";
//	     * / private static final String GET_DEVICE_ID = "getDeviceId";
//	     * / private static final String NO_DEVICE = "No device";
	      public static final String ECLIPSE_EXECUTION_ID = "eclipseExecutionId";
	      private Socket _socket;
	      private PrintWriter _out;
	      private BufferedReader _in;

	     
	      public PerfectoLabConnector(int port)      throws IOException
	      {
		   this("localhost", port);
		      }

	     
	      public PerfectoLabConnector(String host, int port)      throws IOException
	      {
		      try
		      {
			  this._socket = new Socket(host, port);
			   this._out = new PrintWriter(this._socket.getOutputStream(), true);
			  this._in = new BufferedReader(new InputStreamReader(this._socket.getInputStream()));
			      }
		      catch (UnknownHostException e)
		      {
			   System.out.println("Can't connect to Perfecto Lab for Eclipse plugin - unknown host: " + host + e);
			   throw e;
			      }
		      catch (IOException e)
		      {
			   System.out.println(
					"Failed to connect to Perfecto Lab for Eclipse plugin on host: " + host + " and port: " + port
							+ ". Possible reasons are: The code doesn't run in Eclipse or the Perfecto Lab view is not open. In these cases use the MobileDriver constructor that receives the host, user and password."
							+ e);
			     
			     
			  throw e;
			      }
		      }

	     
	      public void close()      throws IOException
	      {
		      try
		      {
			   this._socket.close();
			   System.out.println("Closed eclipse socket");
			      }
		      catch (IOException e)
		      {
			   System.out.println("Failed to close socket");
			   e.printStackTrace(System.out);
			   throw e;
			      }
		      }

	     
	      public String getExecutionId()      throws IOException
	      {
		   this._out.println("getExecutionId");
		   String executionId = this._in.readLine();
		   System.out.println("Interactive connector session execution Id: " + executionId);
		   return executionId;
		      }

	     
	      public String getDeviceId()      throws IOException
	      {
		   this._out.println("getDeviceId");
		   String deviceId = this._in.readLine();
		     
		   String msg = "Received eclipse device id: " + deviceId;
		   System.out.println(msg);
		   if ("No device".equals(deviceId)) {
			   deviceId = null;
			      }
		   return deviceId;
		      }

	     
	      public String getHost()      throws IOException
	      {
		   this._out.println("getHost");
		   String host = this._in.readLine();
		     
		   String msg = "Received eclipse host: " + host;
		   System.out.println(msg);
		   return host;
		      }

	     
	      public String getUser()      throws IOException
	      {
		  this._out.println("getUser");
		   String user = this._in.readLine();
		     
		   String msg = "Received eclipse user: " + user;
		   System.out.println(msg);
		   return user;
		      }

	     
	      public String getPassword()      throws IOException
	      {
		   this._out.println("getPassword");
		   String password = this._in.readLine();
		      String msg;
		   if (StringUtils.isEmpty(password)) {
			   msg = "Received empty eclipse password.";
			      } else {
			   msg = "Received eclipse password.";
			      }
		   System.out.println(msg);
		   return password;
		      }

	     
	      public void setExecutionId(String executionId)
	      {
		   this._out.println("setExecutionId " + executionId);
		   System.out.println("Set execution id: " + executionId);
		      }

	     
	      public static void main(String[] args)      throws IOException
	      {
		   PerfectoLabConnector clientSocket = new PerfectoLabConnector(3287);
		   clientSocket.getExecutionId();
		   clientSocket.getHost();
		   clientSocket.getUser();
		   clientSocket.getPassword();
		   clientSocket.getExecutionId();
		   clientSocket.close();
		   System.out.println("done");
		      }
	      }


 /* Location: C:\Users\anand_jayaram\Downloads\pm-webdriver-9.8.0.0.jar Qualified
 * Name: com.perfectomobile.selenium.util.PerfectoLabConnector JD-Core Version:
 * 0.7.0.1*/
 