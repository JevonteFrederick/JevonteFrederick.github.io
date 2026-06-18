package ContactService;

//By Jevonte Frederick

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContactServiceTest {

	@Test
	//ContactService Add Test.
	void testContactServiceAdd() {
		ContactService service = new ContactService();
		Contact Jim = new Contact("123456789", "Jim", "Jam", "2013337890", "123 egg street, No town, NZ");

		service.addContact(Jim);
	}

	@Test
	//ContactService Test Adding Duplicate IDs. Should throw exception.
	void testContactServiceAddDuplicate() {
		ContactService service = new ContactService();
		Contact Jim = new Contact("123456788", "Jim", "Jam", "2013337890", "123 egg street, No town, NZ");
		Contact Kim = new Contact("123456788", "Kim", "Lam", "2013335890", "123 doom street, A town, PX");

		service.addContact(Jim);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addContact(Kim);
		});
	}
	
	@Test
	//ContactService Test Delete.
	void testContactServiceDelete() {
		ContactService service = new ContactService();
		Contact Jim = new Contact("123456779", "Jim", "Jam", "2013337890", "123 egg street, No town, NZ");
		Contact Kim = new Contact("125346771", "Kim", "Lam", "2013335890", "123 doom street, A town, PX");
		
		service.addContact(Jim);
		service.addContact(Kim);
		service.deleteContact("123456779");
	}
	
	@Test
	//ContactService Test Delete with ID that doesn't exist. Should throw exception.
	void testContactServiceDeleteWrongID() {
		ContactService service = new ContactService();
		Contact Jim = new Contact("123451789", "Jim", "Jam", "2013337890", "123 egg street, No town, NZ");
		Contact Kim = new Contact("125341781", "Kim", "Lam", "2013335890", "123 doom street, A town, PX");
		
		service.addContact(Jim);
		service.addContact(Kim);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.deleteContact("123456689");
		});
	}
	
	@Test
	//ContactService Test Update.
	void testContactServiceUpdate() {
		ContactService service = new ContactService();
		Contact Jim = new Contact("122456789", "Jim", "Jam", "2013337890", "123 egg street, No town, NZ");
		Contact Kim = new Contact("122346781", "Kim", "Lam", "2013335890", "123 doom street, A town, PX");
		
		service.addContact(Jim);
		service.addContact(Kim);
		Assertions.assertTrue(service.updateContact("122346781", "Kim", "Lam", "2013445890", "123 boom street, A town, PX"));
	}
	
	@Test
	//ContactService Test Update. Should throw exception
	void testContactServiceUpdateWrongID() {
		ContactService service = new ContactService();
		Contact Jim = new Contact("125456789", "Jim", "Jam", "2013337890", "123 egg street, No town, NZ");
		Contact Kim = new Contact("125346781", "Kim", "Lam", "2013335890", "123 doom street, A town, PX");
		
		service.addContact(Jim);
		service.addContact(Kim);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.updateContact("625346781", "Kim", "Lam", "2013445890", "123 boom street, A town, PX");
		});
	}
}
