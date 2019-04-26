package DTO;

public class Processor {
	private int id;
	private int current_time = 0;

	public int getCurrent_time() {
		return current_time;
	}

	public void setCurrent_time(int current_time) {
		this.current_time = current_time;
	}
	
	public void addCurrent_time(int num) {
		this.current_time = this.current_time + num;
	}

	public Processor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Processor(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
