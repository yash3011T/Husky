package models;

import java.util.ArrayList;

public class SearchObj {

	String query;
	double flesch;
	double fkgl;
	ArrayList<Display> display;
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public double getFlesch() {
		return flesch;
	}
	public void setFlesch(double flesch) {
		this.flesch = flesch;
	}
	public double getFkgl() {
		return fkgl;
	}
	public void setFkgl(double fkgl) {
		this.fkgl = fkgl;
	}
	public ArrayList<Display> getDisplay() {
		return display;
	}
	public void setDisplay(ArrayList<Display> display) {
		this.display = display;
	}
	
}
