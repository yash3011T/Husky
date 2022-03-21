package models;

/**
 * @author Abhishek Mittal
 *
 */
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
	
	/**
	 * Fetch Owner ID
	 * @return Owner ID
	 */
	public long getOwner_id() {
		return owner_id;
	}
	/**
	 * Store Owner ID
	 * @param owner_id: Owner ID
	 */
	public void setOwner_id(long owner_id) {
		this.owner_id = owner_id;
	}
	
	/**
	 * Fetch User Name
	 * @return User Name
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Store User Name
	 * @param username: User Name
	 */
	public void setUsername(String username) {
		this.username = username;
	}	
	
	/**
	 * Fetch Owner's Country Name
	 * @return Owner's Country Name
	 */
	public String getCountry() {
		return country;
	}
	/**Store Owner's Country Name
	 * @param country: Owner's Country Name
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * Fetch Owner Role
	 * @return Owner ID
	 */
	public String getRole() {
		return role;
	}
	/**
	 * Store Owner Role
	 * @param role: Owner Role
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * Fetch Registration date 
	 * @return Registration date 
	 */
	public long getReg_date() {
		return reg_date;
	}
	/**
	 * Store Registration date 
	 * @param reg_date Registration date 
	 */
	public void setReg_date(long reg_date) {
		this.reg_date = reg_date;
	}
	
	
	/**
	 * Fetch Limited Account Value
	 * @return Limited Account Value
	 */
	public String getLimited_acc() {
		return limited_acc;
	}
	
	/**Store Limited Account Value
	 * @param limited_acc:Limited Account Value
	 */
	public void setLimited_acc(String limited_acc) {
		this.limited_acc = limited_acc;
	}
	
	/**
	 * Fetch Display Name of Owner
	 * @return Display Name of Owner
	 */
	public String getDisplay_name() {
		return display_name;
	}
	
	/**
	 * Store Display Name of Owner
	 * @param display_name: Display Name of Owner
	 */
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	
	/**
	 * Fetch Owners Chosen Role
	 * @return Owners Chosen Role
	 */
	public String getChosen_role() {
		return chosen_role;
	}
	
	/**
	 * Store Owners Chosen Role
	 * @param chosen_role: Owners Chosen Role
	 */
	public void setChosen_role(String chosen_role) {
		this.chosen_role = chosen_role;
	}

	/**
	 * Fetch Data if Email ID is verified
	 * @return Data if Email ID is verified
	 */
	public String getEmail_ver() {
		return email_ver;
	}
	
	/**
	 * Store Data if Email ID is verified
	 * @param email_ver: Data if Email ID is verified
	 */
	public void setEmail_ver(String email_ver) {
		this.email_ver = email_ver;
	}
	
	/**
	 * Fetch Primary Currency Type
	 * @return Primary Currency Type
	 */
	public String getPri_curr_name() {
		return pri_curr_name;
	}
	
	/**Store Primary Currency Type
	 * @param pri_curr_name: Primary Currency Type
	 */
	public void setPri_curr_name(String pri_curr_name) {
		this.pri_curr_name = pri_curr_name;
	}
	
	
}
