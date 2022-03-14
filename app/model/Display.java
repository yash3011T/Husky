package model;

public class Display {
	long owner_id;
	long time_submitted;
	String title;
	String types;
	String skills;
	
	public long getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(long owner_id) {
		this.owner_id = owner_id;
	}
	public long getTime_submitted() {
		return time_submitted;
	}
	public void setTime_submitted(long time_submitted) {
		this.time_submitted = time_submitted;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return types;
	}
	public void setType(String types) {
		this.types = types;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
}
