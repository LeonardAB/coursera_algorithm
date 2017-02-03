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
     private int[] sz; //for weighing improvement method
         
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
        if (i==j) return;
        if (sz[i]<sz[j]) {id[i]=j; sz[j]+=sz[i];}    //this will ensure that the larger tree is put higher
        else              {id[j]=i; sz[i]+=sz[j];}   //the implication is that when a node is unioned, 
                                                     //at most the depth will only added by 1, 
                                                     //but the size of that branch double. 
                                                     //so in max, there can only be lgN (2 log N) operation
       // id[i] = j; //this line is turned off if we use the weighing improvement
    }
    
}
