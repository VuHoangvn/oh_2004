package DAO;

import DTO.Processor;

public class ProcessorDAO {
	private Processor[] processor_list;
	private static ProcessorDAO instance;
	
	private ProcessorDAO() {
		super();
	}
	
	private ProcessorDAO(int numOfProcessor) {
		this.setProcessor(numOfProcessor);
	}

	
	public static ProcessorDAO getInstance(int numberOfProcessor) {
		if(instance == null) {
			instance = new ProcessorDAO(numberOfProcessor);
		}
		return instance;
	}


	public Processor[] getProcessor() {
		return processor_list;
	}

	public void setProcessor(int number) {
		this.processor_list = new Processor[number];
		for(int i = 0; i < number; i++) {
			processor_list[i] = new Processor(i);
		}
	}
}
