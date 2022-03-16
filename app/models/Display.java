package models;

public class Display {
	long owner_id;
	long time_submitted;
	String title;
	String type;
	String skills[];
	String description;
	
	
	public long getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(long owner_id) {
		this.owner_id = owner_id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	
	public long getTime_submitted() {
		return time_submitted;
	}
	public void setTime_submitted(long time_submitted) {
		this.time_submitted = time_submitted;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String[] getSkills() {
		return skills;
	}
	public void setSkills(String skills[]) {
		this.skills = skills;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}

