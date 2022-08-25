package Algorithms;

import Graph.WeightedDirectedGraph_Imp;
import Graph.WeightedUndirectedGraph_Imp;

public class FloydWarshall {
    public static void main(String args[]) {
        WeightedDirectedGraph_Imp grf = new WeightedDirectedGraph_Imp(4);
        grf.addEdge(0, 1, 3);
        grf.addEdge(1, 0, 8);
        grf.addEdge(1, 2, 2);
        grf.addEdge(2, 3, 1);
        grf.addEdge(2, 0, 5);
        grf.addEdge(3, 0, 2);
        grf.addEdge(0, 3, 7);
        floydWarshall(grf);
    }
    // This is All Pairs Shortest Path Algorithm i.e. we will get the shortest
    // path possible from all vertex to all vertex

    // There should be no self loop because they are useless since
    // moving in self loop won't result in smaller path.

    // After 1st iteration the answer 2D matrix
    // will have the shortest path for all vertex from all vertex via
    // vertex 1 , algo will choose whether direct/previous path is better or new
    // path via vertex 1 is better. We will use it to calculate for iteration 2 where we
    // will calculate path via vertex 2 and so on. We will run it V number of
    // times and in the end answer 2d matrix will be our answer

    // Here infinity means no path possible
    // This algorithm can also work with negative weights

    //TC - O(V^3)
    public static void floydWarshall(WeightedDirectedGraph_Imp grf) {
        int ans[][] = new int[grf.adj.length][grf.adj.length];

        // Pre-processing
        for (int r = 0; r < ans.length; r++) {
            for (int c = 0; c < ans[0].length; c++) {
                if (r != c) { // Except for diagonals we put infinity
                    // everywhere indicating that graph is initially empty
                    // i.e. we can't reach any node from any node ie. there is no edge except the node
                    // itself i.e. eg we can reach vertex 0 to vertex 0 at a cost 0
                    ans[r][c] = Integer.MAX_VALUE;
                }
            }
        }

        // Simply converting given graph(which was in the form of adjacency matrix) to 2d matrix
        for (int r = 0; r < grf.adj.length; r++) {
            for (int c = 0; c < grf.adj[r].size(); c++) {
                ans[r][grf.adj[r].get(c).vertice] = grf.adj[r].get(c).weight;
            }
        }

        // Running the Algo
        for (int z = 0; z < grf.adj.length; z++) { // we run algo jitni vertices hai utni times
            // cause one by one we find path via each vertex

            for (int r = 0; r < ans.length; r++) {
                for (int c = 0; c < ans[r].length; c++) {
                    if (r == z || c == z || r == c) {// we don't calculate for
                        // same row or same col via which you are finding path
                        // because it is of no use because suppose you are
                        // finding path via vertex 1 ie. z=1 and you are processing r=1 and
                        // c=2 so if you put it in below formula you'll get
                        // Math.min(ans[1][2], ans[1][1]+[1][2]) => Math.min(ans[1][2], 0+[1][2])
                        // => Math.min(ans[1][2], ans[1][2]) => ans[1][2].  And we don't calculate for
                        // diagonal because they are already optimized ie. 0.
                        continue;
                    } else if (ans[r][z] == Integer.MAX_VALUE || ans[z][c] == Integer.MAX_VALUE) { // No
                        // need to calculate in this case because then if we do ans[r][z]+ans[r][z] in below
                        // formula you will get irregular answer cause you cant add anything
                        // to infinity. And obviously even if we could add to infinity that's
                        // of no use because see -> Math.min(ans[r][c], infinity) => ans[r][c]
                        continue;
                    }
                    ans[r][c] = Math.min(ans[r][c], ans[r][z] + ans[z][c]);
                }
            }
        }

        System.out.println("Printing Solution :  ");
        for (int r = 0; r < ans.length; r++) {
            for (int c = 0; c < ans.length; c++) {
                System.out.print(ans[r][c] + " ");
            }
            System.out.println();
        }


    }
}
