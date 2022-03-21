package models;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author Yashvi Pithadia
 *
 */
public class Display {
	long owner_id;
	long time_submitted;
	String title;
	String type;
	String skills;
	long skill_id;
	
	
	/**
	 * To fetch owner ID
	 * @return owner ID
	 */
	public long getOwner_id() {
		return owner_id;
	}
	/**Store owner ID
	 * @param owner_id: Owner ID from query 
	 */
	public void setOwner_id(long owner_id) {
		this.owner_id = owner_id;
	}
	
	/**
	 * Fetch Project Title
	 * @return Project Title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Store Project Title
	 * @param title: Project Title
	 */
	public void setTitle(String title) {
		this.title = title;
	}	
	

	/**
	 * fetch Skill ID
	 * @return Skill ID
	 */
	public long getSkill_id() {
		return skill_id;
	}
	/**
	 * Store Skill ID
	 * @param skill_id: Skill ID
	 */
	public void setSkill_id(long skill_id) {
		this.skill_id = skill_id;
	}
	
	public long getTime_submitted() {
		return time_submitted;
	}
	public void setTime_submitted(long time_submitted) {
		this.time_submitted = time_submitted;
	}
	
	public String getStringDate() {
		Date date = new Date(time_submitted);
		SimpleDateFormat sdate;
		sdate = new SimpleDateFormat("MMM dd, yyyy");
		return sdate.format(date);
	}
	
	/**
	 * Fetch Project Type
	 * @return Project Type
	 */
	public String getType() {
		return type;
	}
	/**
	 * Store Project Type
	 * @param type: Project Type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Fetch Skill Name
	 * @return Skill Name
	 */
	public String getSkills() {
		return skills;
	}
	/**
	 * Store Skill Name
	 * @param skills: Skill Name
	 */
	public void setSkills(String skills) {
		this.skills = skills;
	}
	
}

