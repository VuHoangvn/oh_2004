package DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import DTO.*;

public class GA {
	private static GA instance;
	private Map<Integer, Integer> solution = new HashMap<Integer, Integer>();
	private List<Map<Integer, Integer>> population = new ArrayList<Map<Integer, Integer>>();
	private List<Node> list = new ArrayList<Node>();
	private Processor[] processList;
	private Task[] taskList;
	private int[] population_tardiness;
	
	public GA (List<Node> list, Processor[] processList,Task[] taskList) {
		this.list = list;
		this.processList = processList;
		this.taskList = taskList;
	}
	
	public static GA getInstance(List<Node> list, Processor[] processList, Task[] taskList) {
		if(instance == null) {
			instance = new GA(list, processList, taskList);
		}
		return instance;
	}

	/*
	 * mã hóa 
	 */
	
	public void encode(List<Node> list, Processor[] processList) {
		if(list.isEmpty()) {
			return;
		}
		List<Node> listChild = new ArrayList<Node>();
		int temp = 0;
		Random rand = new Random();
		for(int i = 0; i < list.size(); i++) {
			temp = rand.nextInt(processList.length);
			solution.put(list.get(i).getId(), temp);
			for(int j = 0; j < list.get(i).getChildren().size(); j++) {
				listChild.add(list.get(i).getChildren().get(j));
			}
		}
		
		encode(listChild, processList);
	}
	
	/*
	 * Tạo quần thể
	 */
	
	public void initPopulation() {
		for(int i = 0; i < 100; i++) {
			encode(list, processList);
			population.add(solution);		
			solution = new HashMap<Integer, Integer>();
		}
		
//		for(Map<Integer, Integer> x: population) {
//			System.out.println("Task     :     Processor ");
//			for (Map.Entry<Integer, Integer> entry : x.entrySet()) {
//			    int key = entry.getKey();
//			    int value = entry.getValue();
//			    System.out.println("   " + key + "   :   " + value);
//			}
//		}
	}
	
	/*
	 * tính độ trễ  
	 */

	public void cal_tardiness() {
		this.population_tardiness = new int[population.size()];
		int i = 0;
		for(Map<Integer, Integer> x: population) {
			for(Processor p: processList) {
				p.setCurrent_time(0);
			}
			int sum = 0;
			
			for (Map.Entry<Integer, Integer> entry : x.entrySet()) {
			    int key = entry.getKey();
			    int value = entry.getValue();
			    processList[value].addCurrent_time(taskList[key].getExecution_time());
			    taskList[key].setTardiness(taskList[key].getDeadtime()-processList[value].getCurrent_time());	//trế thì tardiness sẽ là số âm 
			}
			
			for(Task t : taskList) {
				sum += t.getTardiness();
			}
			
			population_tardiness[i] = sum;
			i++;
		}
		
//		for(int j = 0; j < population.size(); j++) {
//			System.out.println(population_tardiness[j]);
//		}
		
	}
	/*
	 * hàm mục tiêu
	 */
	
	public void costFunction() {
		cal_tardiness();
	}
	
	
	/*
	 * crossover
	 */
	
	public void one_cut_crossover() {
		Random rand = new Random();
		int n = rand.nextInt(population.size());
		Map<Integer, Integer> parent1 = population.get(n);
		int m = rand.nextInt(population.size());
		Map<Integer, Integer> parent2 = population.get(m);
		
		int mid = parent1.size() / 2;
		int index = 0;
		Map<Integer, Integer> child1 = new HashMap<Integer, Integer>();
		Map<Integer, Integer> child2 = new HashMap<Integer, Integer>();
		int[][] arr1 = new int[parent1.size()][2];
		int[][] arr2 = new int[parent1.size()][2];
		
		for (Map.Entry<Integer, Integer> entry : parent1.entrySet()) {
		    int key = entry.getKey();
		    int value = entry.getValue();
		    arr1[index][0] = key;
		    arr1[index][1] = value;
		    index ++;
		}
		
		index = 0;
		
		for (Map.Entry<Integer, Integer> entry : parent2.entrySet()) {
		    int key = entry.getKey();
		    int value = entry.getValue();
		    arr2[index][0] = key;
		    arr2[index][1] = value;
		    index ++;
		}
		
		for(int i = 0; i < parent1.size(); i++) {
			if(i < mid) {
				child1.put(arr1[i][0], arr1[i][1]);
				child2.put(arr2[i][0], arr2[i][1]);
			}
			else {
				child1.put(arr1[i][0], arr2[i][1]);
				child2.put(arr2[i][0], arr1[i][1]);
			}
		}
		
		population.set(n, child1);
		population.set(m, child2);
//		System.out.println(parent1);
//		System.out.println(parent2);
//		System.out.println(child1);
//		System.out.println(child2);
	}
	
	/*
	 * mutation
	 */
	
	public void mutation() {
		Random rand = new Random();
		int n = rand.nextInt(population.size());
		Map<Integer, Integer> prototype = population.get(n);
		System.out.println(prototype);
		n = rand.nextInt(taskList.length);
		int m = rand.nextInt(processList.length);
		while(m == prototype.get(n)) {
			m = rand.nextInt(processList.length);
		}
		prototype.replace(n,m);
		System.out.println(prototype);
	}
	
	/*
	 * local-improvement
	 */
	
	public void local_improvement() {
		
	}
	
	/*
	 * replace parents_child
	 */
	
	public void replace() {
		
	}
	public void getSolution() {
		
	}


	public void setSolution(Map<Integer, Integer> solution) {
		this.solution = solution;
	}
}
