/*
 Represents information about a NWS weather station
*/

public class WeatherStation {
   
   /**
    * Constructor 
    * @param name The name of the station
    * @param id the id for the station
    * @param state the state in which this station is located
    * @param lat latitude of this station
    * @param lng longitude of this station
    */
   public WeatherStation(String name, String id, String state, double lat, double lng) {
    }
   
  /**
   * Gets the ID of the weather station
   * @return the ID of the weather station
   */
   public String getId() { 
      return null;
   }
   
   /**
    * Gets the name of this station
    * @return the name of this station
    */
   public String getName() { 
      return null;
   }
   
   /**
    * Determines if this weather station is located in the specified state
    * @param state the state
    * @return true if this weather station is in state; otherwise false
    */
   public boolean isLocatedInState(String state) {
      return false;
   }
   
}