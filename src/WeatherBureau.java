/*
 * Arrays and ArrayLists of objects
 */

import core.data.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class WeatherBureau {
	WeatherStation[] stations;
	private DataSource ds; 
	
	/**
	 * Constructor that initializes stations by connecting, loading
	 * and fetching the weather stations serviced by the National 
	 * Weather Service
	 */
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


	public WeatherBureau() {
		avoidSSLError();
		ds = DataSource.connect("http://weather.gov/xml/current_obs/index.xml").load();
		
	}
	
	/**
	 * Gets all the weather stations as an array
	 * @return he weather stations as an array
	 */

	public WeatherStation[] getAllStationsArray() {
		stations = ds.fetchArray("WeatherStation", "station/station_name", 
                "station/station_id", "station/state",
                "station/latitude", "station/longitude");
		System.out.println("Total stations: " + stations.length);
		return stations;
	}
	
	/**
	 * Gets all the weather stations as an ArrayList
	 * @return he weather stations as an ArrayList
	 */
	public ArrayList<WeatherStation> getAllStationsList(){
		ArrayList<WeatherStation> stationsList = ds.fetchList("WeatherStation", "station/station_name", 
                "station/station_id", "station/state",
                "station/latitude", "station/longitude");
		System.out.println("Total stations: " + stationsList.size());
		return stationsList;
	}
	
	/**
	 * Gets the list of weather stations in the specified state
	 * @param state the state to filter with
	 * @return the list of weather stations in the specified state
	 */

	public ArrayList<WeatherStation> getStationsInState(String state){
		ArrayList <WeatherStation> finalStateList = new ArrayList <WeatherStation>(); 
		ArrayList<WeatherStation> stationList = getAllStationsList(); 
		for(int i=0; i<stationList.size(); i++ ) {
			WeatherStation ws = stationList.get(i); 
			String stateName = ws.getState(); 
			if(stateName.equals(state)) {
				finalStateList.add(ws); 
			}
			
		}	
		
		return finalStateList;
	}

	/**
	 * Finds the Weather Station in the specified state with the coldest temperature.
	 * @param state the state
	 * @return An Observation for the weather station with the coldest temperature
	 */
	public Observation getColdestInState(String state) {
		 ArrayList <WeatherStation> stationsListByState = getStationsInState(state); 
		 WeatherStation coldestStation = stationsListByState.get(0); 
		 WeatherBot coldWeatherBot = new WeatherBot(coldestStation.getId());
		 Observation colderobs = coldWeatherBot.getShortObservation();
		 for(int i = 1; i< stationsListByState.size(); i++) {
			 WeatherStation temp = stationsListByState.get(i); 
			 WeatherBot tempWeatherBot = new WeatherBot(temp.getId());
			 Observation tempobs = tempWeatherBot.getShortObservation();
			 
			 if(tempobs.colderThan(colderobs)) {
				 coldestStation = temp; 
				 coldWeatherBot= tempWeatherBot;
				 colderobs = tempobs;
			 }
		 }
		return colderobs;
	}
	
	/**
	 * Gets a list of all weather stations in a state sorted by their name.
	 * @param state the state
	 * @return list of all weather stations in a state sorted by their name
	 */

	public WeatherStation[] getStationsInStateSortedByName(String state) {
		  
		ArrayList<WeatherStation> stationList = getStationsInState(state);
			
		WeatherStation[] finalStateList = new WeatherStation[stationList.size()];
		for(int i=0; i<stationList.size(); i++) {
			WeatherStation firstWS = stationList.get(i); 
			String stateName = firstWS.getName(); 
			//System.out.println("Before Sort: " + stateName); 
			for(int j = i+1; j<stationList.size(); j++) {
				WeatherStation secondWS = stationList.get(j); 
				String nextStateName = secondWS.getName(); 
			if(stateName.compareTo(nextStateName) > 0) {
				firstWS = secondWS; 
				stateName = nextStateName; 
			}
			}
		//System.out.println("After Sort: " + stateName); 	
		finalStateList[i]=firstWS;  
		}
		//System.out.println("Completed");
		return finalStateList;
	}
	/**
	 * Sorts the array of WeatherStation using the Insertion Sort algorithm
	 * @param arr the array of WeatherStation
	 */
	public void insertionSort(WeatherStation[] arr) {
		int index;
		int sortedIndex;
		WeatherStation newValue; 
		//System.out.println("Length " + arr.length);
		for(index = 1; index < arr.length; index++) {
			newValue = arr[index]; 
			String stateName = newValue.getName(); 
			sortedIndex = index; 
			WeatherStation temp = arr[sortedIndex-1];
			String NameTemp = temp.getName(); 
			System.out.println(stateName + " ---- " + NameTemp);
			while(sortedIndex > 0 && stateName.compareTo(NameTemp)>0) {
				temp = arr[sortedIndex-1];
				NameTemp = temp.getName(); 
				sortedIndex--; 
				//System.out.println(stateName + " ---- " + NameTemp);
				//System.out.println(sortedIndex);
				
			}
			arr[sortedIndex] = newValue; 
		
			
		}
		for (WeatherStation filtered : arr) {
			   System.out.println(filtered.getName());
		   }
	}
	public static void main(String[] args) {
		
	   WeatherBureau bureau = new WeatherBureau();
	   WeatherStation[] stations = bureau.getAllStationsArray();
	   for (WeatherStation ws : stations) {
		   //System.out.println("  " + ws.getId() + ": " + ws.getName());
	   }
	   //System.out.println("Total number of stations: " + stations.length);
	   

	   
	   System.out.println("Getting weather stations in Washington");
	   ArrayList<WeatherStation> waStations = bureau.getStationsInState("WA");
	   for (WeatherStation ws : waStations) {
		   //System.out.println("  " + ws.getId() + ": " + ws.getName());
	   }
	   System.out.println("Total number of stations: " + waStations.size());
	   
	   System.out.println();
	   System.out.println("Getting coldest station in Washington");
	   Observation ob = bureau.getColdestInState("WA");
	   System.out.println("Coldest station is - " + ob);
	   System.out.println(ob);
	   
	   System.out.println();
	   System.out.println("Sorting stations in Washington");
	   WeatherStation[] filteredStations = bureau.getStationsInStateSortedByName("WA");
	   for (WeatherStation ws : filteredStations) {
		   //System.out.println("  " + ws.getId() + ": " + ws.getName());
	   }


   }
}