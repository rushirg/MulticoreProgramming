/*
Author: Rushikesh Gaidhani
*/
import java.util.concurrent.atomic.AtomicReference; 

class LinkedlistStack{

	static AtomicReference<Node> head = new AtomicReference<Node>();

	static class Node{
		int data;
		Node next;
		Node(int data, Node next){
			this.data = data;
			this.next = next;
		}
	}

	public void push(int data){
	
		while(true){
			Node temp = head.get();
			Node newNode = new Node(data, temp);
			if(head.compareAndSet(temp, newNode))
				break;
		}
	}

	public int pop(){
		
		int popData = -1;
		while(true){
			Node temp = head.get();
			if(temp == null)
				return popData;
			popData = temp.data;
			if(head.compareAndSet(temp, temp.next))
				break;
		}
		return popData;
	}
}

class RandomStackOperations extends Thread{

	LinkedlistStack stackObj;
	
	RandomStackOperations(LinkedlistStack stackObj){
		this.stackObj = stackObj;
	}

	public void run(){
		for(int i=0;i<150000; i++){
			if(Math.random() < 0.5 ){
				stackObj.push(i);
				//comment out to-verify the result
				//System.out.println(Thread.currentThread().getName() + " Pushed: " + i);
			}
			else{
				int popItem = stackObj.pop();
				//comment out to-verify the result
				//System.out.println(Thread.currentThread().getName() + " Poped: " + popItem);
			}
		}
	}
}

public class LinkedlistAsStack{

	public static void main(String[] args) {
				
		LinkedlistStack obj1 = new LinkedlistStack();
		
		//Prepopulate stack with 50000 elements
		for(int i=0; i<50000; i++)
			obj1.push(i);

		for( int i=0; i<4; i++){
			Thread t = new Thread(new RandomStackOperations(obj1));
			t.start();
		}	
	}
}
