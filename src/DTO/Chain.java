package DTO;

import java.util.Arrays;
import java.util.Random;


public class Chain {
	private static Chain instance;
	private Task[] list;
	private Node root;
	private int size;
	private int count = 0;
		

	private Chain(Task[] taskList) {
		this.setList(taskList);
		root = new Node(list[0].getId());
		makeChain(root);
		traverse(root);
		PostOrderTraverse(root);
	}
	
	
	public static Chain getInstance(Task[] taskList) {
		if(instance == null) {
			instance = new Chain(taskList);
		}
		return instance;
	}




	public Task[] getTask() {
		return list; 
	}
	
	

	public void setList(Task[] taskList) {
		int taskLength = taskList.length;
		this.size = taskLength;
		
		list = new Task[taskLength];
		Random rand = new Random();
	    boolean generating=true;
	    int counting=0;
	    while(generating)
	    {
	        int i = rand.nextInt(taskLength);
	        if (Arrays.asList(list).contains(taskList[i]))
	        {}
	        else
	        {
	            list[counting]= taskList[i];
	            counting++;
	        }
	        if(counting >= taskLength)
	        {
	            generating=false;
	        }
	    }
	    
	}
	
	public Node getRoot() {
		return root;
	}



	public void setRoot(Node root) {
		this.root = root;
	}



	public void makeChain(Node node) {
		if(count == list.length-1) {
			return;
		}
		else {
//			System.out.println(count++);
			node.addChild(new Node(list[++count].getId()));
//			System.out.println(node.getChildren().get(0).getData().getId());
			makeChain(node.getChildren().get(0));
		}
	}
	
	public void traverse(Node node) {
		node.setEstStT(list);
//		node.setLstStT();
//		System.out.println("id: "+ node.getData().getId());
//		System.out.println("execution time: " + node.getData().getExecution_time());
//		System.out.println("easiest start time: " + node.getData().getEstStT());
//		System.out.println("latest start time: " + node.getData().getLstStT());
//		System.out.println("deadtime: " + node.getData().getDeadtime());
//		System.out.println("----------");
		if(!node.getChildren().isEmpty()) {
			traverse(node.getChildren().get(0));
		}
		return;
	}
	
	public void PostOrderTraverse(Node node) {
		if(!node.getChildren().isEmpty()) {
			PostOrderTraverse(node.getChildren().get(0));
		}
		node.setLstStT(list);
//		System.out.println(list[node.getId()].getLstStT());
//		System.out.println("id: "+ list[node.getId()].getId());
//		System.out.println("execution time: " + list[node.getId()].getExecution_time());
//		System.out.println("easiest start time: " + list[node.getId()].getEstStT());
//		System.out.println("latest start time: " + list[node.getId()].getLstStT());
//		System.out.println("deadtime: " + list[node.getId()].getDeadtime());
//		System.out.println("----------");
	}
}
