package models;

public class FleschSetter {
	String title;
	double FleshReadability;
	double FleschKincade;
	
	public double getFleshReadability() {
		return FleshReadability;
	}
	public void setFleshReadability(double fleshReadability) {
		FleshReadability = fleshReadability;
	}
	public double getFleschKincade() {
		return FleschKincade;
	}
	public void setFleschKincade(double fleschKincade) {
		FleschKincade = fleschKincade;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
