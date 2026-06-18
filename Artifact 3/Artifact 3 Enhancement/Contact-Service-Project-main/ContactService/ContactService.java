package ContactService;

//By Jevonte Frederick

public class ContactService {
	//Initialize data access object for contacts
	ContactDAO DAO = new ContactDAO();

	//AddContact Method
	public void addContact(Contact newContact) {
		if(!DAO.addContact(newContact)){
			throw new IllegalArgumentException("ID could not be added");
		}
	}
	
	//Delete contact method
	public void deleteContact(String contactID) {
		if(!DAO.deleteContact(contactID)){
			throw new IllegalArgumentException("ID does not exist in database.");
		}
	}
	
	//Update contact method
	public boolean updateContact(String contactID, String firstName, String lastName, String phoneNumber, String address) {
		if(!DAO.updateContact(contactID, firstName, lastName, phoneNumber, address)){
			throw new IllegalArgumentException("ID does not exist in database.");
		}
		else{
			return true;
		}
	}

	//Read contact method
	public void readContacts() {
        ContactDAO DAO = new ContactDAO();
		DAO.readContacts();
    }
}
