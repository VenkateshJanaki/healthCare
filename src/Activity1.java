import core.data.*;

public class Activity1 {
   public static void main(String[] args) {
		  String stationID = "KSEA"; // Sea-Tac Airport
	      System.out.println("The temperature at KSEA is " + getTempF(stationID) + "F");
		  System.out.println(getConciseForecast(stationID));
   }
   
   /**
    * Returns the current temperature in fahrenheit at the specified weather station
    * @param id the weather station id
    * @return the temperature
    */
   public static double getTempF(String id) {
	   return Integer.MIN_VALUE;
   }
   /**
    * Returns a concise forecast for the specified weather station id
    * @param id the weather station id
    * @return a concise forecast 
    */
   public static String getConciseForecast(String id) {
	   return null;
   }
}
