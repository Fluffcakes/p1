package p1;

import java.util.Stack;

//For this problem, you may assume you can use the Stack we started together in class
public class Queue<T>{

  //instance vars to be added by student
  
  private Stack<T> in = new Stack<T>();
  private Stack<T> out = new Stack<T>();
	private int size = 0;

  //constructor to be added by student
  
  /*
  enQueue(q, x): 
  
  While stack1 is not empty, push everything from stack1 to stack2.
  Push x to stack1 (assuming size of stacks is unlimited).
  Push everything back to stack1.

  deQueue(q): 

  If stack1 is empty then error
  Pop an item from stack1 and return it*/
  
  //remove and return the element at the beginning of the list
  public T remove(){
      if (out.size() == 0)
      {
          return null;
      }
      size--;
      return out.pop();
      /*if (out.size() == 0 && in.size() == 0) {
          return null;
      }
      if (out.size() > 0) {
          return out.pop();
      } else if (out.size() == 0) {
          while (in.size() > 0) {
              out.push(in.pop());
          }
          return out.pop();
      }*/
  }


  //add element at the end of the list
  public void add(T el){
      while (out.size() != 0) {
          in.push(out.pop());
      }
      out.push(el);
      while (in.size() != 0) {
          out.push(in.pop());
      }
      size++;
      /*if (out.size() == 0) {
          in.push(el);
          size++;
      } else {
          while (out.size() > 0) {
              in.push(out.pop());
              size++;
          }
          in.push(el);
          size++;
      }*/
  }

  //return a String representation of the list
  //formatted as [el1, el2, el3, ..., elN]
  public String toString(){
      return "[" + in + "]";  
  }

  public int size(){
    return out.size();
  }

}