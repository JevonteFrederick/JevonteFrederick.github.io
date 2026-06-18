package ContactService;

//By: Jevonte Frederick

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContactTest {
	
	//Contact test. Should pass.
	@Test
	void testContactOne() {
		Contact Jim = new Contact("123456789", "Jim", "Jam", "2013337890", "123 egg street, No town, NZ");
	}
	
	//Contact ID character test. Should throw invalid ID exception.
	@Test
	void testContactIDLength() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Contact Jim = new Contact("12345678945", "Jim", "Jam", "2013337890", "123 egg street, No town, NZ");
		});
	}
	//Contact ID null test. Should throw invalid ID exception.
	@Test
	void testContactIDNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Contact Jim = new Contact(null, "Jim", "Jam", "2013337890", "123 egg street, No town, NZ");
		});
	}
	
	//Contact FirstName character Test. Should throw invalid firstName Exception
	@Test
	void testContactFirstNameLength() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Contact Jim = new Contact("123456789", "Jimbilinoesy", "Jam", "2013337890", "123 egg street, No town, NZ");
		});
	}
	//Contact FirstName null Test. Should throw invalid firstName Exception
	@Test
	void testContactFirstNameNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Contact Jim = new Contact("123456789", null, "Jam", "2013337890", "123 egg street, No town, NZ");
		});
	}
	
	//Contact lastName character Test. Should throw invalid lastName Exception
	@Test
	void testContactlastNameLength() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Contact Jim = new Contact("123456789", "Jim", "Jammb78136278643482", "2013337890", "123 egg street, No town, NZ");
		});
	}
	//Contact lastName null Test. Should throw invalid lastName Exception
	@Test
	void testContactlastNameNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Contact Jim = new Contact("123456789", "Jam", null, "2013337890", "123 egg street, No town, NZ");
		});
	}
	
	//Contact phoneNumber character Test. Should throw invalid phoneNumber Exception
	@Test
	void testPhoneNumberLength() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Contact Jim = new Contact("123456789", "Jim", "Jam", "20198233337890", "123 egg street, No town, NZ");
		});
	}
	//Contact phoneNumber null Test. Should throw invalid phoneNumber Exception
	@Test
	void testPhoneNumberNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Contact Jim = new Contact("123456789", "Jim", "Jam", null, "123 egg street, No town, NZ");
		});
	}
	
	//Contact address character Test. Should throw invalid address Exception
	@Test
	void testAddressLength() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Contact Jim = new Contact("123456789", "Jim", "Jam", "2013337890", "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");;
		});
	}
	//Contact address null Test. Should throw invalid address Exception
	@Test
	void testAddressNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Contact Jim = new Contact("123456789", "Jim", "Jam", "2013337890", null);
		});
	}
	
	@Test
	void testContactTwo() {
		Contact Carl = new Contact("123496789", "Carl", "Pillman", "20143852", "47 Garn street, Redwood, OG");
	}
}
