package models;

import java.util.ArrayList;

/**
 * @author Yashvi Pithadia
 *
 */
public class SearchObj {

	String query;
	double flesch;
	double fkgl;
	ArrayList<Display> display;
	/**
	 * Fetch Query
	 * @return Query
	 */
	public String getQuery() {
		return query;
	}
	/**
	 * Store Query
	 * @param query: Query
	 */
	public void setQuery(String query) {
		this.query = query;
	}
	/**
	 * Fetch Flesch Index value for every division
	 * @return Flesch Index value for every division
	 */
	public double getFlesch() {
		return flesch;
	}
	/**
	 * Store Flesch Index value for every division
	 * @param flesch: Flesch Index value for every division
	 */
	public void setFlesch(double flesch) {
		this.flesch = flesch;
	}
	/**
	 * Fetch FKGL value for every division
	 * @return FKGL value for every division
	 */
	public double getFkgl() {
		return fkgl;
	}
	/**
	 * Store FKGL value for every division
	 * @param fkgl: FKGL value for every division
	 */
	public void setFkgl(double fkgl) {
		this.fkgl = fkgl;
	}
	/**
	 * Fetch Array of Content to display
	 * @return Array of Content to display
	 */
	public ArrayList<Display> getDisplay() {
		return display;
	}
	/**
	 * Store Array of Content to display
	 * @param display:Array of Content to display 
	 */
	public void setDisplay(ArrayList<Display> display) {
		this.display = display;
	}
	
}
