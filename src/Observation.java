
public class Observation{
	//instance data
	
	
	/**
	 * Constructs an Observation object with the specified parameters and sets
	 * the other instance data to their default values. toString will output a
	 * short observation, if this constructor is used.
	 * @param the weather station id
	 * @param description Short description of the current weather
	 * @param temp temperature
	 * @param windDir wind direction in degrees
	 */
	public Observation(String id, String description, double temp, int windDir) {
		
		
	}
	
	/**
	 * Constructs an Observation object with the specified parameters.
	 * toString will output a full observation, if this constructor is used.
	 * @param the weather station id
	 * @param description Short description of the current weather
	 * @param temp temperature
	 * @param windDir wind direction in degrees
	 * @param windSpeed wind speed in knots
	 * @param pressure barometric pressure in mb
	 * @param humidity relative humidity
	 */
	public Observation(String id, String description, double temp, int windDir, double windSpeed, double pressure, int humidity) {
	
	
	}
	
	/**
	 * @return a String representing this observation
	 */
	public String toString() {
		return null;
		
	}
	
	/**
	 * Determines if the temperature of this observation is colder than other.
	 * @param other the other other observation
	 * @return true if this observation's temperature is colder than other; otherwise false.
	 */
	public boolean colderThan(Observation other) {
		return false;
	}
	
	/**
	 * Returns the Beaufort number on the Beaufort wind force scale.
	 * @return the Beaufot number for the current observation
	 * For more details, see https://en.wikipedia.org/wiki/Beaufort_scale
	 */
	public int getBeaufortNumber() {
		return -1;
	}
	
	   /**
	    * Returns a string representation of the wind conditions. Summarize the
	    * Beaufort number into 4 categories; calm; breezy; wind flags out; storm 
	    * @return a string representation of the wind conditions
	    */
	   public String getWindConditions() {
		   return null;
	   }
	/**
	 * Gets the weather station ID
	 * @return the weather station id
	 */
	public String getId() {
		return null;
	}

	/**
	 * @return the temp
	 */
	public double getTemp() {
		return Integer.MIN_VALUE;
	}

}
