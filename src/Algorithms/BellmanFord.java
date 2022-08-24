package Algorithms;

import Graph.WeightedDirectedGraph_Imp;
import java.util.ArrayList;


public class BellmanFord {
    public static void main(String args[]) {
        WeightedDirectedGraph_Imp grf = new WeightedDirectedGraph_Imp(6);
        grf.addEdge(0, 1, 8);
        grf.addEdge(1, 2, 2);
        grf.addEdge(2, 3, -9);
        grf.addEdge(0, 3, 4);
        grf.addEdge(3, 4, 3);
        grf.addEdge(4, 5, 1);
        grf.addEdge(2, 5, -5);

        // Saving our graph's edges in an arraylist so that it's easier to
        // implement bellman ford and the code looks cleaner too
        ArrayList<Pair> listOfEdges = new ArrayList<>(); // this will contain all edges of our graph
        for (int i = 0; i < grf.adj.length; i++) {
            for (WeightedDirectedGraph_Imp.Edge edge : grf.adj[i]) {
                listOfEdges.add(new Pair(i, edge.vertice, edge.weight));
            }
        }

        bellmanFord(grf, 0, listOfEdges);

    }

    // This is also Single Source Shortest Path Algorithm
    // Unlike dijkstra It can work fine with -ve edge but obviously not with -ve cycle
    // It uses Dynamic Programming.
    // TC = O(V*E) which is worse than Dijkstra
    // Auxiliary Space: O(V) // because we take an extra array where we store path

    //Learn The Algorithm
    // Here we process all edges one by one (V-1) times where 'V' is number of vertices
    // In first iteration it solves(ie. calculates the smallest
    // distance which may not be the ultimate smallest distance
    // but is smallest at current instant) for all vertices which are at distance 1
    // from source then in next iteration it solves for vertices which are at distance 2
    // using the solution of distance 1 ie. previous iteration and so on . So if suppose you
    // have 2 vertices A and B and there are 2 paths to reach B from A like one is direct and
    // other one consists of 3 edges so the path with 1 edge will be considered in 1st
    // iteration of bellman ford and in 3rd iteration we will compare this 1 edge path with 3 edge path
    // and choose the smaller one

    // in the end in our solution array we will have The Shortest distance from source to all vertices.

    // We iterate (V-1) times because in any graph max path length between 2 vertices can never be more
    // than V-1 and since bellman ford works on path distance ie. in ith iteration it calculates answer for
    // path with i distance from source and since max path length cannot be more than V-1
    // therefore we iterate v-1 times
    public static void bellmanFord(WeightedDirectedGraph_Imp grf, int startVertx, ArrayList<Pair> listOfEdges) { // this logic works for
        // both directed and
        // undirected

        // step 1 initializing array ie. preprocessing
        int ans[] = new int[grf.adj.length];

        for (int i = 0; i < ans.length; i++) {
            if (i == startVertx) {
                ans[i] = 0; // we are putting 0 in startVertex
                // because we cannot have a -ve path from source to source cause then
                // it will be a -ve cycle and bellman ford doesn't work for them
            } else {
                ans[i] = Integer.MAX_VALUE; // infinity means any path from source
                // vertex to here will be better
            }
        }

        // step 2 running bellman ford v-1 times
        for (int i = 0; i < (grf.adj.length - 1); i++) { // simply processing each edge v-1 times

            for (int j = 0; j < listOfEdges.size(); j++) { // processing each edge

                Pair edge = listOfEdges.get(j);

                if (ans[edge.source] == Integer.MAX_VALUE) {// To avoid error we do this because mathematically
                    // adding something to infinity is infinity and in computer if we add
                    // something to infinity ie. Integer.Max_value while doing 'ans[edge.source] + edge.weight' will
                    // result in error or a '-ve' value so to avoid this.
                    continue;
                }

                if (ans[edge.source] + edge.weight < ans[edge.destination]) {//this 'if' condition means
                    // "Mujhse mere neighbor tk directly jana" ie. ans[edge.source] + edge.weight < ans[edge.destination] is cheaper than
                    // indirect going to my neighbor (which was basically calculated
                    // in previous iteration) ie. ans[edge.destination] then choose the direct
                    // path and store it in your neighbor else dont do anything
                    ans[edge.destination] = ans[edge.source] + edge.weight;
                }

            }
        }

        // Displaying the answer
        System.out.println("Final ANSWER :-");
        for (int el : ans) { // if in directed graph any of these comes infinty means we cant go there by any
            // path
            System.out.print(el + " ");
        }

    }

    static class Pair {
        int source;
        int destination;
        int weight;

        Pair(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

}
