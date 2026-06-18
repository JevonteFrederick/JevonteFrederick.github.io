package AppointmentService;

//By: Jevonte Frederick

public class AppointmentService {
	//Initialize data access object for appointments
	AppointmentDAO DAO = new AppointmentDAO();

	// Add appointment
	public void addAppointment(Appointment newAppointment) {
		if(!DAO.addAppointment(newAppointment)){
			throw new IllegalArgumentException("ID could not be added");
		}
	}
	
	// Delete appointment
	public void deleteAppointment(String appointmentID) {		
		if(!DAO.deleteAppointment(appointmentID)){
			throw new IllegalArgumentException("ID does not exist in database.");
		}
	}

	// Read appointments
	public void readAppointments() {
        AppointmentDAO DAO = new AppointmentDAO();
		DAO.readAppointments();
    }
}
