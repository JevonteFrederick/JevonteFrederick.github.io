package AppointmentService;

//By: Jevonte Frederick

import java.util.HashMap;

public class AppointmentService {
	//Initialize data structure for appointments
	private HashMap<String, Appointment> appointmentMap = new HashMap<String, Appointment>();
	
	//Add appointment method
	public void addAppointment(Appointment newAppointment) {
		//Throw exception if new appointment uses existing ID
		if (appointmentMap.containsKey(newAppointment.getAppointmentID())) {
			throw new IllegalArgumentException("Cannot add appointment. Appointment ID is already used.");
		}
		//Else, add appointment to map
		else {
			appointmentMap.put(newAppointment.getAppointmentID(), newAppointment);
		}
	}
	
	//Delete appointment method
	public void deleteAppointment(String appointmentID) {
		//Throw exception if ID is not in map
		if (!appointmentMap.containsKey(appointmentID)) {
			throw new IllegalArgumentException("Cannot delete appointment. Appointment ID does not exist.");
		}
		//Else, remove appointment from map
		else {
			appointmentMap.remove(appointmentID);
		}
	}
}
