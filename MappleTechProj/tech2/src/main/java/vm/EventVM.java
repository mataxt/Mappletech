package vm;

import java.sql.Date;

public class EventVM {

	private Integer eventID;
	private String  title;
	private String  description;
	private String  creator;
	private Date 	date;
	private String	image;
	
	public EventVM() {
		
	}

	public EventVM(Integer eventID, String title, String description,
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
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getCreator() {
		return creator;
	}

	public Date getDate() {
		return date;
	}

	public String getImage() {
		return image;
	}
}
