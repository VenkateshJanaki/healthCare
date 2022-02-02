import core.data.*;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.OutputStream;

public class Activity1 {
   public static void main(String[] args) {
	      Activity1.avoidSSLError();
		  String stationID = "KSEA"; // Sea-Tac Airport
		  String id = "KSEA";
		  DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/" + id + ".xml");
		  ds.setCacheTimeout(15 * 60);
		  System.out.println(getConciseForecast(stationID));
          ds.load();
          //ds.printUsageString();
          double temp = ds.fetchFloat("temp_f");
          String loc = ds.fetchString("location");
          System.out.println("The temperature at " + loc + " is " + temp + "F");
          System.out.println("Temperature: " + temp);
   }
   
	  public static void avoidSSLError() {
		   
		   TrustManager[] trustAllCerts = new TrustManager[]{
			        new X509TrustManager() {
		
			            public java.security.cert.X509Certificate[] getAcceptedIssuers()
			            {
			                return null;
			            }
			            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
			            {
			                //No need to implement.
			            }
			            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
			            {
			                //No need to implement.
			            }
			        }
			};
		
			// Install the all-trusting trust manager
			try 
			{
			    SSLContext sc = SSLContext.getInstance("SSL");
			    sc.init(null, trustAllCerts, new java.security.SecureRandom());
			    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			} 
			catch (Exception e) 
			{
			    System.out.println(e);
			}
	   }

   
   /**
    * Returns the current temperature in fahrenheit at the specified weather station
    * @param id the weather station id
    * @return the temperature
    */
   public static double getTempF(String id) {
	   DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/" + id + ".xml");
		  ds.setCacheTimeout(15 * 60);
		  ds.load();
    //ds.printUsageString();
    double temp = ds.fetchFloat("temp_f");
	   return temp;
   }
   /**
    * Returns a concise forecast for the specified weather station id
    * @param id the weather station id
    * @return a concise forecast 
    */
   public static String getConciseForecast(String id) {
	   Activity1.avoidSSLError();
	   DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/" + id + ".xml");
		  ds.setCacheTimeout(15 * 60);
		  ds.load();
       //ds.printUsageString();
       double temp = ds.fetchFloat("temp_f");
       String loc = ds.fetchString("location");
	   return "The temperature at " + loc + " is " + temp + "F";
   }
}
