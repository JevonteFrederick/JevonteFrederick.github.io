package ContactService;

//By Jevonte Frederick

import java.util.HashMap;

public class ContactService {
	//Initialize data structure for contacts
	private HashMap<String, Contact> contactList = new HashMap<String, Contact>();
	
	//AddContact Method
	public void addContact(Contact newContact) {
		//Throw exception if new contact uses existing ID
		if (contactList.containsKey(newContact.getContactID())) {
			throw new IllegalArgumentException("Cannot add contact. Contact ID is already used.");
		}
		//Else, add contact to map
		else {
			contactList.put(newContact.getContactID(), newContact);
		}
	}
	
	//Delete contact method
	public void deleteContact(String contactID) {
		//Throw exception if ID is not in map
		if (!contactList.containsKey(contactID)) {
			throw new IllegalArgumentException("Cannot delete contact. Contact ID does not exist.");
		}
		//Else, remove contact
		else {
			contactList.remove(contactID);
		}
	}
	
	//Update contact method
	public boolean updateContact(String contactID, String firstName, String lastName, String phoneNumber, String address) {
		//throw exception if map does not contain contact ID
		if (!contactList.containsKey(contactID)) {
			throw new IllegalArgumentException("Cannot update contact. Contact ID does not exist.");
		}
		//else update contact
		else {
			Contact updatedContact = new Contact(contactID, firstName, lastName, phoneNumber, address);
			contactList.replace(contactID, updatedContact); 
			//Return true when contact is successfully updated in map
			return (updatedContact == contactList.get(contactID));
		}
	}
}
