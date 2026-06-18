package TaskService;

//By: Jevonte Frederick

import java.util.HashMap;

public class TaskService {
	//Initialize data structure for tasks
	private HashMap<String, Task> taskMap = new HashMap<String, Task>();
	
	//AddTask Method
	public void addTask(Task newTask) {
		//Throw exception if new task uses existing ID
		if (taskMap.containsKey(newTask.getTaskID())) {
			throw new IllegalArgumentException("Cannot add task. Task ID is already used.");
		}
		//Else, add task to map
		else {
			taskMap.put(newTask.getTaskID(), newTask);
		}
	}
	
	//Delete task method
	public void deleteTask(String taskID) {
		//Throw exception if ID is not in map
		if (!taskMap.containsKey(taskID)) {
			throw new IllegalArgumentException("Cannot delete task. Task ID does not exist.");
		}
		//Else, remove task from map
		else {
			taskMap.remove(taskID);
		}
	}
	
	//Update task method
	public boolean updateTask(String taskID, String name, String description) {
		//throw exception if map does not contain task ID
		if (!taskMap.containsKey(taskID)) {
			throw new IllegalArgumentException("Cannot update task. Task ID does not exist.");
		}
		//else update task
		else {
			Task updatedTask = new Task(taskID, name, description);
			taskMap.replace(taskID, updatedTask); 
			//Return true when task is successfully updated in map
			return (updatedTask == taskMap.get(taskID));
		}
	}
}
