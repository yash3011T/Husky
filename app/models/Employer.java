package models;

public class Employer {
	
	long owner_id;
	String username;
	String country;
	String role;
	long reg_date;
	String limited_acc;
	String display_name;
	String chosen_role;
	String email_ver;
	String pri_curr_name;
	
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
	
	public long getReg_date() {
		return reg_date;
	}
	public void setReg_date(long reg_date) {
		this.reg_date = reg_date;
	}
	
	
	public void setLimited_acc(String limited_acc) {
		this.limited_acc = limited_acc;
	}
	
	public String getLimited_acc() {
		return limited_acc;
	}
	
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	
	public String getDisplay_name() {
		return display_name;
	}
	
	public void setChosen_role(String chosen_role) {
		this.chosen_role = chosen_role;
	}
	
	public String getChosen_role() {
		return chosen_role;
	}
	
	public void setEmail_ver(String email_ver) {
		this.email_ver = email_ver;
	}
	
	public String getEmail_ver() {
		return email_ver;
	}
	
	public void setPri_curr_name(String pri_curr_name) {
		this.pri_curr_name = pri_curr_name;
	}
	
	public String getPri_curr_name() {
		return pri_curr_name;
	}
	
	
}
