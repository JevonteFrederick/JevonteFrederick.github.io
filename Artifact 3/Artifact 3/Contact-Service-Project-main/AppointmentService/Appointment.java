package AppointmentService;

import java.util.Date;

//By Jevonte Frederick

public class Appointment{
	
	//Declare appointment variables
	private String appointmentID;
	private Date date;
	private String description;
	
	//Constructor
	public Appointment(String ID, Date date, String description) {
		//Throw exception if ID is null or greater than 10 characters.
		if (ID == null || ID.length() > 10) {
			throw new IllegalArgumentException("Invalid appointment ID.");
		}
		//Throw exception if date is null or before current date.
		if (date == null || date.before(new Date())) {
			throw new IllegalArgumentException("Invalid date.");
		}
		//Throw exception if description is null or greater than 50 characters.
		if (description == null || description.length() > 50) {
			throw new IllegalArgumentException("Invalid description.");
		}
		
		//Set attributes if no exceptions are thrown.
		this.appointmentID = ID;
		this.date = date;
		this.description = description;
	}
	
	//Get ID method
	public String getAppointmentID() {
		return appointmentID;
	}
}