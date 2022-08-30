package Algorithms;

import Graph.WeightedUndirectedGraph_Client;
import Graph.WeightedUndirectedGraph_Imp;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Kruskal {


    public static void main(String args[]) {

        WeightedUndirectedGraph_Imp grf = new WeightedUndirectedGraph_Imp(6);
        grf.addEdge(0, 1, 8);
        grf.addEdge(1, 2, 2);
        grf.addEdge(2, 3, 9);
        grf.addEdge(0, 3, 4);
        grf.addEdge(3, 4, 3);
        grf.addEdge(4, 5, 1);
        grf.addEdge(2, 5, 5);

        ArrayList<Pair> edges = new ArrayList<>();
        for (int i = 0; i < grf.adj.length; i++) { // we are simply converting given
            // adjacency matrix to ArrayList of Pair
            for (WeightedUndirectedGraph_Imp.Edge edge : grf.adj[i]) {
                edges.add(new Pair(i, edge.vertice, edge.weight));
            }
        }

        kruskal(edges, 6);
    }
    // First see Prims and DSU then see kruskal

    // This is algorithm also helps us find MSP.
    // refer : https://www.youtube.com/watch?v=YU3Bvek4wjs&t=98s

    // Basically we will process all edges one by one so first sort the edges based
    // on weights now we simply process edges from top to bottom and connect them using DSU
    // if vertices are in same group then no need to connect because it means you have already
    // connected them using smaller weights. Sorting ensures that we always choose the smallest
    // possible edge and DSU makes sure that graph is connected.


    // Difference between prims and kruskal :-
    // https://www.gatevidyalay.com/prims-and-kruskal-algorithm-difference/
    // One major difference is that the tree that we are making in prims is always
    // connected whereas the tree that we are making in kruskal usually remains disconnected
    // because we pick edges randomly based on smallest edge weights
    public static void kruskal(ArrayList<Pair> edges, int numOfVertices) { // here edges[r][c] r represents the vertex 'v' and c represents vertex 'u' and the weight of these edges will be stored in the matrix
        //Preprocessing for DSU
        int parent[] = new int[numOfVertices];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        int rank[] = new int[numOfVertices];
        for (int i = 0; i < rank.length; i++) {
            rank[i] = 1;
        }

        int mspWeight = 0;
        Collections.sort(edges); // Sorting the edges based on the edge weight ,  Collections.sort
        // will sort based on edge weight only because in Pair class we defined compareTo()
        // and Collections will compare 2 objects using the implementation that we provided
        // in that function.

        for (Pair edge : edges) {
            boolean flag = UnionByRank(edge.u, edge.v, parent, rank); // We did a little
            // change in this union function, here it will do union and return whether
            // it was able to perform union of the vertex u and v or not. If yes it was able
            // to do union then it means this is the best edge that could connect u and v so
            // we add its weight to our answer ie. mspWight. If not then simply do nothing
            // cause it means that u and v are already paired with better edge and we
            // simply have to ignore this edge
            if (flag) {
                mspWeight += edge.weight;
            }
        }

        System.out.println("MSP weight is : " + mspWeight);
    }

    public static int findLeaderWithPathCompression(int x, int[] arr) {
        if (arr[x] == x) {
            return x;
        }
        int parentOfCurrEle = arr[x];
        int leaderOfGroup = findLeaderWithPathCompression(parentOfCurrEle, arr);
        arr[x] = leaderOfGroup;
        return leaderOfGroup;
    }

    public static boolean UnionByRank(int u, int v, int[] parent, int[] rank) {
        int loa = findLeaderWithPathCompression(u, parent);
        int lob = findLeaderWithPathCompression(v, parent);

        if (loa != lob) {
            if (rank[loa] > rank[lob]) {
                parent[lob] = loa;
            } else if (rank[loa] < rank[lob]) {
                parent[loa] = lob;
            } else {
                parent[lob] = loa;
                rank[loa]++;
            }
            return true; // this means u and v were not in the same group and so we
            // were able to perform union
        } else {
            return false;// this means they were already in the same groups
        }
    }

    static class Pair implements Comparable<Pair> {
        int v;
        int u;
        int weight;

        Pair(int v, int u, int weight) {
            this.v = v;
            this.u = u;
            this.weight = weight;
        }

        @Override
        public int compareTo(@NotNull Pair o) {
            return this.weight - o.weight;
        }
    }
}
