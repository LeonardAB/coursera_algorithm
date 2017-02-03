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
public class QuickUnionUF {
     private int[] id;
         
    public QuickUnionUF(int N){ //constructor
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i]=i; //initially the contents of array are the same with the index
        }
    }
    
    private int root(int i){
        while (i != id[i]) {  //as long as the node is not the rood            
            i=id[i];          //keep climbing the tree
        }
        return i;
    }
    
    public boolean connected(int p, int q){
        return root(p)==root(q);  //this makes the algorith expensive due to the "finding root" operation
    }
    
    public void union (int p, int q) {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
    
}
