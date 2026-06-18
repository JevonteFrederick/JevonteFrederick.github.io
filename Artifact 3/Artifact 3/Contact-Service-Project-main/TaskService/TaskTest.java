package TaskService;

//By: Jevonte Frederick

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskTest {
	
	//Task test. Should pass.
	@Test
	void testTask() {
		Task Send = new Task("1234567890", "Send Info", "Send info to someone.");
	}
	
	//Task ID character test. Should throw invalid ID exception.
	@Test
	void testTaskIDLength() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Task Send = new Task("12345678901", "Send Info", "Send info to someone.");
		});
	}
	
	//Task ID null test. Should throw invalid ID exception.
	@Test
	void testTaskIDNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Task Send = new Task(null, "Send Info", "Send info to someone.");
		});
	}
	
	//Task name character Test. Should throw invalid name Exception
	@Test
	void testTaskNameLength() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Task Send = new Task("1234567890", "gogogogogogogogogogog", "Send info to someone.");
		});
	}
	
	//Task name null Test. Should throw invalid name Exception
	@Test
	void testTaskNameNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Task Send = new Task("1234567890", null, "Send info to someone.");
		});
	}
	
	//Task description character Test. Should throw invalid description Exception
	@Test
	void testTaskDescriptionLength() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Task Send = new Task("1234567890", "Send Info", "nonononononononononononononononononnononononononono");
		});
	}
	
	//Task description null Test. Should throw invalid description Exception
	@Test
	void testTaskDescriptionNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Task Send = new Task("1234567890", "Send Info", null);
		});
	}
}
