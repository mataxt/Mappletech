package model;

import java.io.Serializable;
import java.sql.Date;

public class Event implements Serializable{

	private Integer eventID;
	private String  title;
	private String  description;
	private String  creator;
	private Date 	date;
	private String	image;
	
	public Event() {
		
	}

	public Event(Integer eventID, String title, String description,
			String creator, Date date, String image) {
		super();
		this.eventID = eventID;
		this.title = title;
		this.description = description;
		this.creator = creator;
		this.date = date;
		this.image = image;
	}
	

	public Integer getEventID() {
		return eventID;
	}

	public void setEventID(Integer eventID) {
		this.eventID = eventID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
