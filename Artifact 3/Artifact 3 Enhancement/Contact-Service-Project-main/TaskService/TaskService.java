package TaskService;

//By: Jevonte Frederick

public class TaskService {
	//Initialize data access object for tasks
	TaskDAO DAO = new TaskDAO();	
	
	//AddTask Method
	public void addTask(Task newTask) {
		if(!DAO.addTask(newTask)){
			throw new IllegalArgumentException("ID could not be added");
		}	
	}
	
	//Delete task method
	public void deleteTask(String taskID) {
		if(!DAO.deleteTask(taskID)){
			throw new IllegalArgumentException("ID does not exist in database.");
		}
	}
	
	//Update task method
	public boolean updateTask(String taskID, String name, String description) {
		if(!DAO.updateTask(taskID, name, description)){
			throw new IllegalArgumentException("ID does not exist in database.");
		}
		else{
			return true;
		}
	}

	//Read task method
	public void readTasks() {
		DAO.readTasks();	
	}
}
