import core.data.*;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.OutputStream; 

public class WeatherBot {
	private String id; 
	
	public void avoidSSLError() {
		   
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
    * Constructs a WeatherBot object with the specified weather station id
    * @param stationID the weather station id
    */
   public WeatherBot(String stationID) {
	   id = stationID;  
   }
   
   
   /**
    * Gets an Observation object with the station id, short weather description, temperature
    * and wind direction.
    * @return an Observation object
    */
   public Observation getShortObservation() {
	   avoidSSLError();
	   DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/" + id + ".xml");
	   ds.setCacheTimeout(15 * 60);
	   ds.load();
	   int windegree= ds.fetchInt("wind_degrees");
	   double temp = ds.fetchDouble("temp_f"); 
	   String summary = ds.fetchString("weather");
	   Observation ob1 = new Observation(id, summary, temp, windegree);  	   
 
	   return ob1; 
   }
   /**
    * Gets an Observation object with the station id, short weather description, temperature,
    * wind direction, wind speed in knots, barometric pressure in mb, and the relative humidity.
    * @return an Observation object
    */
   public Observation getLongObservation() {
	   avoidSSLError();
	   DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/" + id + ".xml");
	   ds.setCacheTimeout(15 * 60);
	   ds.load();
	   int windegree= ds.fetchInt("wind_degrees");
	   double temp = ds.fetchDouble("temp_f"); 
	   String summary = ds.fetchString("weather");
	  double windSpeed = ds.fetchDouble("wind_kt"); 
		double pressure = ds.fetchDouble("pressure_mb"); 
		int humidity = ds.fetchInt("relative_humidity"); 		
	   Observation ob2 = new Observation(id, summary, temp, windegree, windSpeed, pressure, humidity);  	   
 
	   return ob2; 
   }
   
   public static void main(String[] args) {

	   WeatherBot bot = new WeatherBot("KSEA");
	   Observation ob1 = bot.getShortObservation();
	   Observation ob2 = bot.getLongObservation();
	   System.out.println(ob1.toString());
	   System.out.println(ob2.toString());
	   
	   WeatherBot bot2 = new WeatherBot("KRNT");
	   Observation ob3 = bot2.getLongObservation();
	   System.out.println(ob3.toString());
	   
	   if (ob2.colderThan(ob3)) {
		   System.out.println("It is colder at " +ob2.getId() + " than "  + ob3.getId());
	   }
	   else {
		   System.out.println("It is warmer at " +ob2.getId() + " than "  + ob3.getId());
	   }
	   
	   System.out.println(ob2.getId() +": " + ob2.getWindConditions());
	   System.out.println(ob3.getId() +": " + ob3.getWindConditions());
   }
   


}



