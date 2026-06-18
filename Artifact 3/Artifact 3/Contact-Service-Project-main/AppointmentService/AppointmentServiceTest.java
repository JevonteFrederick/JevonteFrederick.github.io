package AppointmentService;

//By: Jevonte Frederick

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Date;

class AppointmentServiceTest {

	//AppointmentService add test.
	@Test
	void testAppointmentServiceAdd() {
		AppointmentService service = new AppointmentService();
		Date appointmentDate = new Date(System.currentTimeMillis() + 1);
		Appointment talk = new Appointment("1234567890", appointmentDate, "Appointment for a discussion.");
		
		appointmentDate = new Date(System.currentTimeMillis() + 99999999);
		Appointment followUp = new Appointment("1234567891", appointmentDate, "Appointment for Follow-up.");
		
		service.addAppointment(talk);
		service.addAppointment(followUp);
	}

	//AppointmentService add test. Should throw exception for duplicate IDs
	@Test
	void testAppointmentServiceAddException() {
		AppointmentService service = new AppointmentService();
		Date appointmentDate = new Date(System.currentTimeMillis() + 1);
		Appointment talk = new Appointment("1234567890", appointmentDate, "Appointment for a discussion.");
		
		appointmentDate = new Date(System.currentTimeMillis() + 99999999);
		Appointment followUp = new Appointment("1234567890", appointmentDate, "Appointment for Follow-up.");
		
		service.addAppointment(talk);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		service.addAppointment(followUp);
		});
	}
	
	//AppointmentService delete test. 
	@Test
	void testAppointmentServiceDelete() {
		AppointmentService service = new AppointmentService();
		Date appointmentDate = new Date(System.currentTimeMillis() + 1);
		Appointment talk = new Appointment("1234567890", appointmentDate, "Appointment for a discussion.");
		
		appointmentDate = new Date(System.currentTimeMillis() + 99999999);
		Appointment followUp = new Appointment("1234567891", appointmentDate, "Appointment for Follow-up.");
		
		service.addAppointment(talk);
		service.addAppointment(followUp);
		
		service.deleteAppointment("1234567890");
	}
	
	//AppointmentService delete test. Should throw exception for non-existent ID.
	@Test
	void testAppointmentServiceDeleteException() {
		AppointmentService service = new AppointmentService();
		Date appointmentDate = new Date(System.currentTimeMillis() + 1);
		Appointment talk = new Appointment("1234567890", appointmentDate, "Appointment for a discussion.");
		
		appointmentDate = new Date(System.currentTimeMillis() + 99999999);
		Appointment followUp = new Appointment("1234567891", appointmentDate, "Appointment for Follow-up.");
		
		service.addAppointment(talk);
		service.addAppointment(followUp);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		service.deleteAppointment("1234523891");
		});
	}
}