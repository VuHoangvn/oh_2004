package DTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Node {
	private int id;
	private List<Node> children = new ArrayList<Node>();
	private Node parent;
	
	public Node(int id) {
		 this.id = id;
	 }
	 
	 public void addChild(Node child) {
		child.setParent(this);
	 	this.children.add(child);
	 }
	 
//	 public void addChildren(List<Node> children) {
//		 children.forEach(each -> each.setParent(this));
//		 this.children = new ArrayList<Node>();
//		 this.children.addAll(children);
//	 }
	 
	 public List<Node> getChildren() {
		 return children;
	 }
	 
	 public int getId() {
		 return id;
	 }
	 
	 public void setId(int id) {
		 this.id = id;
	 }
	 
	 private void setParent(Node parent) {
		 this.parent = parent;
	 }
	 
	 public Node getParent() {
		 return parent;
	 }
	 
	 public void setEstStT(Task[] list) {
		 if(this.parent == null) {
			 list[this.id].setEstStT(0);
			 list[this.id].setDeadtime();
		 }else {
			 list[this.id].setEstStT(list[this.parent.getId()].getEstStT() + list[this.parent.getId()].getExecution_time());
			 list[this.id].setDeadtime();
		 }
	 }
	 
	 public void setLstStT(Task[] taskList) {
		 int lst = taskList[this.id].getDeadtime()- taskList[this.id].getExecution_time();
			 
		 if(this.children.isEmpty()) {
			 taskList[this.id].setLstStT(lst);
		 }else {
			 int [] list = new int[this.children.size()];
			 int i = 0;
			 for(Node node : this.children) {
				 list[i++] = taskList[node.getId()].getLstStT() - taskList[this.id].getExecution_time();
			 }
			 
		     int min = Arrays.stream(list).min().getAsInt();
			 taskList[this.id].setLstStT(lst < min ? lst : min);
		 }
		 
	 }
	 
}
