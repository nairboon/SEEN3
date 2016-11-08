package we.are.en3.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * The Data DataPoint: class represents a data store
 * (e.g. database table or csv-file)  
 *
 */
public class DataPoint implements IsSerializable{
	
	//instance variable (data store fields)
	private String date;
	private double averageTemperature;
	private double averageTemperatureUncertainty;
	private String city;
	private String country;
	private String latitude;
	private String longitude;

	//constructors
	public DataPoint() {}
	public DataPoint(
			String dt, 
			double AverageTemperature,
			double AverageTemperatureUncertainty,
			String City,
			String Country,
			String Latitude,
			String Longitude) {
		this.setAverageTemperature(AverageTemperature);
		this.setAverageTemperatureUncertainty(AverageTemperatureUncertainty);
		this.setCity(City);
		this.setCountry(Country);
		this.setLatitude(Latitude);
		this.setLongitude(Longitude);
	}
	
	//getters and setters
	public String getDate() {
		return this.date;
	}	
	public void setAverageTemperature(String dt) {
		this.date = dt;
	}
	
	public double getAverageTemperature() {
		return this.averageTemperature;
	}	
	public void setAverageTemperature(double AverageTemperature) {
		this.averageTemperature = AverageTemperature;
	}	
	
	public double getAverageTemperatureUncertainty() {
		return this.averageTemperatureUncertainty;
	}	
	public void setAverageTemperatureUncertainty(double AverageTemperatureUncertainty) {
		this.averageTemperatureUncertainty = AverageTemperatureUncertainty;
	}
	
	public String getCity() {
		return this.city;
	}	
	public void setCity(String City) {
		this.city = City;
	}
	
	public String getCountry() {
		return this.country;
	}	
	public void setCountry(String Country) {
		this.country = Country;
	}
	
	public String getLatitude() {
		return this.latitude;
	}	
	public void setLatitude(String Latitude) {
		this.latitude=Latitude;
	}
	
	public String getLongitude() {
		return this.longitude;
	}	
	public void setLongitude(String Longitude) {
		this.longitude=Longitude;
	}
}

