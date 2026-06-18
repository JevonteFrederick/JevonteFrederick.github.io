package TaskService;

//By: Jevonte Frederick

public class Task{
	
	//Declare contact variables
	private String taskID;
	private String name;
	private String description;
	
	//Task Constructor
	public Task(String ID, String name, String description) {
		//Throw exception if ID is null or greater than 10 characters.
		if (ID == null || ID.length() > 10) {
			throw new IllegalArgumentException("Invalid task ID.");
		}
		//Throw exception if name is null or greater than 20 characters.
		if (name == null || name.length() > 20) {
			throw new IllegalArgumentException("Invalid name.");
		}
		//Throw exception if description is null or greater than 50 characters.
		if (description == null || description.length() > 50) {
			throw new IllegalArgumentException("Invalid description.");
		}
		
		//Set attributes if no exceptions are thrown.
		this.taskID = ID;
		this.name = name;
		this.description = description;
	}
	
	//Getter methods
	public String getTaskID() {
		return taskID;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}