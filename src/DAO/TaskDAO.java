package DAO;

import DTO.Task;

public class TaskDAO {
	private Task[] taskList;
	private static TaskDAO instance;
	
	private TaskDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	private TaskDAO(int numOfTask) {
		super();
		this.setTaskList(numOfTask);
	}
	
	
	public static TaskDAO getInstance(int numberOfTask) {
		if(instance == null) {
			instance = new TaskDAO(numberOfTask);
		}
		return instance;
	}


	public Task[] getTaskList() {
		return taskList;
	}

	public void setTaskList(int number) {
		this.taskList = new Task[number];
		
		for(int i = 0; i < number; i++) {
			taskList[i] = new Task(i);
		}
		
	}
}
