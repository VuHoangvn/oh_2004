package Main;

import java.util.ArrayList;
import java.util.List;

import DAO.GA;
import DAO.ProcessorDAO;
import DAO.TaskDAO;
import DTO.Chain;
import DTO.Node;
import DTO.Processor;
import DTO.Task;

public class Main {
	public static void main(String[] args) {
		Task[] taskList = TaskDAO.getInstance(10).getTaskList();
		Processor[] processorList = ProcessorDAO.getInstance(3).getProcessor();
		Chain chain = Chain.getInstance(taskList); 
		
		Node root = chain.getRoot();
		List<Node> list = new ArrayList<Node>();
		list.add(root);
		GA ga = GA.getInstance(list, processorList, taskList);
		ga.initPopulation();
		ga.costFunction();
		ga.one_cut_crossover();
		ga.mutation();

	}
}
