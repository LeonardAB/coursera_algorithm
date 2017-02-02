/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursera;

/**
 *
 * @author Dipta Mahardhika
 */
public class QuickFindUF {
    private int[] id;
    
    public QuickFindUF(int N){ //constructor
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i]=i; //initially the contents of array are the same with the index
        }
    }
    
    public boolean connected(int p, int q) { //to check whether two points are connected or not
        return id[p]==id[q]; //check whether point p and q are in the same connected component
    } //this method is cost expensive because if we need to check all connection, the time will be N^2
      //N^2 don't scale to technology, too expensive
      //if we have a computer that is 10x faster with 10x memory, we cannot solve 
      //problem 10x faster. actually it will be 10x slower since it is quadratic.
    
    public void union (int p, int q){ //connect to points
        int pid = id[p]; //it is necessary to take this into separate variable because if not,
                         //when id[p] is changed to the new id, 
                         //if there is another point with the same id as *previous* id[p],
                         //it won't be checked
        int qid = id[q];
        for (int i = 0; i < id.length; i++) { //run thru all elements
            if (id[i]==pid) { //when element tha belongs to component pid, 
                id[i]= qid;   //change it into qid
            }
            
        }
    }  //this method is cost expensive because if we need to connect all connection, the time will be N^2
       //N^2 don't scale to technology, too expensive
       //if we have a computer that is 10x faster with 10x memory, we cannot solve 
       //problem 10x faster. actually it will be 10x slower since it is quadratic.
}
