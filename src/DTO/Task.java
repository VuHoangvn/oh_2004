package DTO;

import java.util.Random;

public class Task {
	private int id;
	private int execution_time;
	private int deadtime;
	private int estStT; // the easiest time that task can start
	private int lstStT;	// the latest time that task can start
	private int tardiness = 0;
	
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Task(int id) {
		super();
		this.setId(id);
		this.setExecution_time();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getExecution_time() {
		return execution_time;
	}

	public void setExecution_time() {
		Random rand = new Random();
		// the execution_time in range [5,10]
		this.execution_time = rand.nextInt(6) + 5;
	}

	public int getDeadtime() {
		return deadtime;
	}

	public void setDeadtime() {
		Random rand = new Random();
		// the deadtime in range [estStT, 1.5*estStT]
		this.deadtime = (int) ((this.estStT + this.execution_time) *(1+ (rand.nextInt(6) * 0.1)));
	}

	public int getEstStT() {
		return estStT;
	}

	public void setEstStT(int estStT) {
		this.estStT = estStT;
		this.setDeadtime();
	}

	
	public int getLstStT() {
		return lstStT;
	}

	public void setLstStT(int lstStT) {
		this.lstStT = lstStT;
	}

	public int getTardiness() {
		return tardiness;
	}

	public void setTardiness(int tardiness) {
		this.tardiness = tardiness;
	}
	
}
