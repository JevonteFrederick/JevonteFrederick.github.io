package AppointmentService;

//By: Jevonte Frederick

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Date;

class AppointmentTest {
	
	//Appointment test.
	@Test
	void testAppointment() {
		Date appointmentDate = new Date(System.currentTimeMillis() + 30000000);
		Appointment talk = new Appointment("1234567890", appointmentDate, "Appointment for a discussion.");
	}
	
	//Create appointment with past date. Should throw exception.
	@Test
	void testAppointmentDateException() {
		Date appointmentDate = new Date(System.currentTimeMillis() - 30000000);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Appointment talk = new Appointment("1234567890", appointmentDate, "Appointment for a discussion.");
		});
	}
	
	//Create appointment with null date. Should throw exception.
	@Test
	void testAppointmentNullDate() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Appointment talk = new Appointment("12345678930", null, "Appointment for a discussion.");
		});
	}
	
	//Create appointment with ID over 10 characters. Should throw exception.
	@Test
	void testAppointmentLongID() {
		Date appointmentDate = new Date(System.currentTimeMillis() + 30000000);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Appointment talk = new Appointment("12345678930", appointmentDate, "Appointment for a discussion.");
		});
	}
	
	//Create appointment with null ID. Should throw exception.
	@Test
	void testAppointmentNullID() {
		Date appointmentDate = new Date(System.currentTimeMillis() + 30000000);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Appointment talk = new Appointment(null, appointmentDate, "Appointment for a discussion.");
		});
	}
	
	//Create appointment with description over 50 characters. Should throw exception.
	@Test
	void testAppointmentLongDescription() {
		Date appointmentDate = new Date(System.currentTimeMillis() + 30000000);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Appointment talk = new Appointment("1234567890", appointmentDate, "Appointment for something. I don't know. This description is too long.");
		});
	}
	
	//Create appointment with null description. Should throw exception.
	@Test
	void testAppointmentNullDescription() {
		Date appointmentDate = new Date(System.currentTimeMillis() + 30000000);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Appointment talk = new Appointment("1234567890", appointmentDate, null);
		});
	}
}
