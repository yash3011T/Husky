package models;

/**
 * @author Yash Trivedi
 *
 */
public class FleschSetter {
	String title;
	double FleshReadability;
	double FleschKincade;
	
	/**
	 * Fetch Flesch Readability Index 
	 * @return Flesch Readability Index
	 */
	public double getFleshReadability() {
		return FleshReadability;
	}
	/**
	 * Store Flesch Readability Index
	 * @param fleshReadability: Flesch Readability Index
	 */
	public void setFleshReadability(double fleshReadability) {
		FleshReadability = fleshReadability;
	}
	/**Fetch Flesch Kincade Grade Level value
	 * @return Flesch Kincade Grade Level value
	 */
	public double getFleschKincade() {
		return FleschKincade;
	}
	/**
	 * Store Flesch Kincade Grade Level value
	 * @param fleschKincade: Flesch Kincade Grade Level value
	 */
	public void setFleschKincade(double fleschKincade) {
		FleschKincade = fleschKincade;
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
}
