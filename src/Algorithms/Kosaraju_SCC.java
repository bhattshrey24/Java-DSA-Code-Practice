package Algorithms;

import Graph.WeightedDirectedGraph_Imp;
import Graph.WeightedUndirectedGraph_Imp;

import java.util.Scanner;
import java.util.Stack;

public class Kosaraju_SCC {

    // Kosaraju is the name of the algorithm which helps us find Strongly connected
    // components (SCC) in a graph.

    // For understanding see this (Very nicely explained) - https://www.youtube.com/watch?v=QtdE7QPsWiU

    // Connected component as the name suggest is part of the graph that is connected i.e.
    // a component or a part of graph in which we can go from any vertices to any
    // vertices. The part of the graph where this is possible is called connected component
    // This term is used with undirected graph and it's easy to find no of connected components
    // in an undirected graph i.e. simply find how many DFS calls are required to traverse
    // the whole graph because each DFS call will traverse and mark all vertices of a particular
    // component as visited because graph is undirected.

    // SCC is same as connected components but this is defined for directed graph
    // this is different because here since graph is directed so we can't say
    // no.of DFS calls = No. Of SCC (For explanation of this either refer notes or video)
    // SSC != no.of cycles in the graph because we want minimum components right (For explanation
    // of this also see the above mentioned video)
    // Definition - SCC is simply minimum number of components in a directed graph in which we can
    // go to any vertices from any vertices.
    // SCC only exists in a directed graph
    // SCC can be made of one or more cycles

    // ALGORITHM :-
    // Step 1 - Apply DFS to given graph and add vertices in a stack while backtracking
    // Step 2 - Reverse all edges in the same graph (by assigning 1 weight to reverse edges)
    // Step 3 - Keep a count variable and remove elements from stack and apply DFS from those
    //          removed vertices in reversed graph and the number of DFSs now is your answer

    public static void main(String[] args) {
        WeightedDirectedGraph_Imp grf = new WeightedDirectedGraph_Imp(6);
        grf.addEdge(0, 1, 8);
        grf.addEdge(1, 2, 2);
        grf.addEdge(2, 3, -9);
        grf.addEdge(0, 3, 4);
        grf.addEdge(3, 4, 3);
        grf.addEdge(4, 5, 1);
        grf.addEdge(2, 5, -5);

        kosaraju(grf);
    }

    public static void kosaraju(WeightedDirectedGraph_Imp gf) {
        Stack<Integer> solSt = new Stack<>();
        //STEP 1

        boolean[] visited = new boolean[gf.adj.length];

        for (int i = 0; i < visited.length; i++) {
            if(!visited[i]){
                dfsRecursion(gf, visited, i, solSt);
            }
        }

        //STEP 2 (Reversing)
        // we are reversing in the same graph by assigning new edges with weight 1 which represents
        // reversed edges

        for (int i = 0; i < gf.adj.length; i++) {// reversing and adding edges in same graph
            for (WeightedDirectedGraph_Imp.Edge nbr : gf.adj[i]) {
                if (nbr.weight == 1) { // if the weight is 1 means we already reversed the edge so no need to do it again
                    continue;
                }
                gf.addEdge(nbr.vertice, i, 1); // adding reverse edge in same graph
            }
        }

        //STEP 3

        int count = 0;

        boolean[] vis = new boolean[gf.adj.length];

        while (!solSt.isEmpty()) {
            int poppedVertex = solSt.pop(); // vertex from which we will apply DFS
            if (!vis[poppedVertex]) {
                dfsOnReversedGraph(gf, vis, poppedVertex);
                count++;
            }
        }

        System.out.println("ANS is :" + count);

    }

    public static void dfsOnReversedGraph(WeightedDirectedGraph_Imp gf, boolean[] visited, int vertx) {
        if (visited[vertx]) { // base case
            return;
        }
        visited[vertx] = true; // marking visited

        for (WeightedDirectedGraph_Imp.Edge nbr : gf.adj[vertx]) {
            if (nbr.weight == 1) { // i.e. if current edge is the reversed one. We only
                // care about reverse edge now
                dfsOnReversedGraph(gf, visited, nbr.vertice); // adding neighbors
            }
        }
    }

    public static void dfsRecursion(WeightedDirectedGraph_Imp gf, boolean[] visited, int vertx, Stack<Integer> sol) {

        if (visited[vertx]) { // base case
            return;
        }
        visited[vertx] = true; // marking visited

        for (WeightedDirectedGraph_Imp.Edge nbr : gf.adj[vertx]) {
            dfsRecursion(gf, visited, nbr.vertice, sol); // adding neighbors
        }
        // backtracking
        sol.add(vertx);
    }

}
