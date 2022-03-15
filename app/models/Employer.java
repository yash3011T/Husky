package models;

public class Employer {
	
	long owner_id;
	String username;
	String country;
	String role;
	String time_zone;
	
	public long getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(long owner_id) {
		this.owner_id = owner_id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}	
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getTime_zone() {
		return time_zone;
	}
	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}

}
