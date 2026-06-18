package TaskService;

//By: Jevonte Frederick

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskServiceTest {

	@Test
	//TaskService Add Test.
	void testTaskServiceAdd() {
		TaskService service = new TaskService();
		Task Send = new Task("1234567790", "Send Info", "Send info to someone.");
		Task Check = new Task("1234567791", "Check Info", "Check info is correct and up to date.");
		
		service.addTask(Send);
		service.addTask(Check);
	}

	@Test
	//TaskService Test Adding Duplicate IDs. Should throw exception.
	void testTaskServiceAddDuplicate() {
		TaskService service = new TaskService();
		Task Send = new Task("1234567699", "Send Info", "Send info to someone.");
		Task Check = new Task("1234567699", "Check Info", "Check info is correct and up to date.");
		
		service.addTask(Send);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addTask(Check);
		});
	}
	
	@Test
	//TaskService Test Delete.
	void testTaskServiceDelete() {
		TaskService service = new TaskService();
		Task Send = new Task("1234466890", "Send Info", "Send info to someone.");
		Task Check = new Task("7454478936", "Check Info", "Check info is correct and up to date.");
		
		service.addTask(Check);
		service.addTask(Send);
		service.deleteTask("1234466890");
		service.addTask(Send);
	}
	
	@Test
	//TaskService Test Delete with ID that doesn't exist. Should throw exception.
	void testTaskServiceDeleteWrongID() {
		TaskService service = new TaskService();
		Task Send = new Task("1234567290", "Send Info", "Send info to someone.");
		Task Check = new Task("7454678336", "Check Info", "Check info is correct and up to date.");
		
		service.addTask(Check);
		service.addTask(Send);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.deleteTask("1234566892");
		});
	}
	
	@Test
	//TaskService Test Update.
	void testTaskServiceUpdate() {
		TaskService service = new TaskService();
		Task Send = new Task("1234567890", "Send Info", "Send info to someone.");
		Task Check = new Task("1454678936", "Check Info", "Check info is correct and up to date.");
		
		service.addTask(Check);
		service.addTask(Send);
		Assertions.assertTrue(service.updateTask("1454678936", "Check Info", "Check info is correct by next week."));
	}
	
	@Test
	//TaskService Test Update. Should throw exception
	void testTaskServiceUpdateWrongID() {
		TaskService service = new TaskService();
		Task Send = new Task("2234527890", "Send Info", "Send info to someone.");
		Task Check = new Task("2454628936", "Check Info", "Check info is correct and up to date.");
		
		service.addTask(Check);
		service.addTask(Send);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.updateTask("6414673936", "Check Info", "Check info is correct by next week.");
		});
	}
}
