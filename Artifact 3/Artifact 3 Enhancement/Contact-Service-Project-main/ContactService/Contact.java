package ContactService;

//By Jevonte Frederick

public class Contact{
	
	//Declare contact variables
	private String contactID;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	
	//Constructor
	public Contact(String ID, String firstName, String lastName, String phoneNumber, String address) {
		//Throw exception if ID is null or greater than 10 characters.
		if (ID == null || ID.length() > 10) {
			throw new IllegalArgumentException("Invalid Contact ID.");
		}
		//Throw exception if firstName is null or greater than 10 characters.
		if (firstName == null || firstName.length() > 10) {
			throw new IllegalArgumentException("Invalid first Name.");
		}
		//Throw exception if lastName is null or greater than 10 characters.
		if (lastName == null || lastName.length() > 10) {
			throw new IllegalArgumentException("Invalid last Name.");
		}
		//Throw exception if phoneNumber is null or greater than 10 characters.
		if (phoneNumber == null || phoneNumber.length() > 10) {
			throw new IllegalArgumentException("Invalid phone number.");
		}
		//Throw exception if address is null or greater than 30 characters.
		if (address == null || address.length() > 30) {
			throw new IllegalArgumentException("Invalid address.");
		}
		
		//Set attributes if no exceptions are thrown.
		this.contactID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	//Getter methods
	public String getContactID() {
		return contactID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}
}