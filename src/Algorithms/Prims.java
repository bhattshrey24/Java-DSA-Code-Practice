package Algorithms;

import Graph.WeightedUndirectedGraph_Imp;
import org.jetbrains.annotations.NotNull;

import java.util.PriorityQueue;

public class Prims {
    public static void main(String args[]) {

        WeightedUndirectedGraph_Imp grf = new WeightedUndirectedGraph_Imp(10);

        grf.addEdge(0, 1, 10);
        grf.addEdge(0, 3, 4);
        grf.addEdge(3, 1, 7);
        grf.addEdge(3, 2, 15);
        grf.addEdge(1, 2, 8);


        prims(grf);

    }
    // Prims is simply Minimum Spanning Tree calculating algorithm

    // Now Spanning tree is suppose you are given a graph with 'v'
    // vertices, now make a new graph from the given graph which has only
    // (v-1) number of edges and all nodes should be connected. So all such
    // graphs which you can make from the given graph which follows
    // above specified conditions are called spanning trees. Basically
    // converting given graph to tree. Now minimum spanning tree is simply
    // the spanning tree with minimum total weight(total weight means
    // total weight that we get when we sum all V-1 edges)

    //Learn the algorithm then understand the reasoning

    // The algorithm is very similar to Dijkstra but logic is very different
    // Difference between dijkstra and prims is that dijkstra finds the smallest
    // weighted path between source to destination vertex it does not care about
    // whether the edges it uses makes the graph connected or not whereas prims
    // will try it's best to choose the best possible weights but it won't let graph
    // to be disconnected. And prims make sure that graph does not
    // become disconnected by assigning 1 edge to all vertices except starting vertex.

    // Code/Algorithm wise prims is very similar to Dijkstra with just one difference
    // that here we just add the edge weight of the neighbor(ie. neighbor.weight) whereas
    // in Dijkstra we add the cumulative weight so far of the path that dijkstra
    // took(ie. removedNode.weight +neighbor.weight). But logically both are very different
    // dijkstra tries to find minimum PATH between 2 Nodes whereas Prims tries to
    // find minimum path, but it won't let graph to be disconnected basically will generate MSP
    // See the '15 may' vid from 2:54:00 onwards to understand prims
    // see https://www.youtube.com/watch?v=f__RNJ1IAvY&t=21s or '15 may' vid from 3:48:00 onwards to understand the difference with examples

    // Prims only works for undirected graph because Prims algorithm
    // assumes that all vertices are connected. But in a directed graph,
    // every node is not reachable from every other node. So, Prims
    // algorithm fails due to this reason. And I guess it works for negative edge as well

    // The graph must be connected, meaning that there is a path between any pair of vertices.
    // If the graph is not connected, there will be multiple spanning trees, one for each
    // connected component

    //TC=O(ELogE) which is same as ELogV because we know that at max number of
    // edges can be E=V^2. So if you put this in ELogE you'll get ELogV

    // Currently we are using PQ which is Heap(or some people say it
    // Binary Heap cause every vertex has 2 child) but if we use
    // Fibonacci Heap then we can further improve the TC of prims to VlogV but
    // you'll have to make fibonacci heap on your own (because java does not provide one)
    // and it is complicated and they won't even ask this in interview .
    // This is just for your knowledge

    public static void prims(WeightedUndirectedGraph_Imp grf) {

        PriorityQueue pq = new PriorityQueue<Pair>();
        boolean isVisited[] = new boolean[grf.adj.length];

        int weightOfMsp = 0;

        pq.add(new Pair(0, 0)); // weight 0 because we won't assign
        // an edge to source vertex since 1 vertex has to have 0 edge
        // assigned to it otherwise how will we be able to generate a msp with V-1 edges

        while (!pq.isEmpty()) {

            Pair removedNode = (Pair) pq.remove();

            if (isVisited[removedNode.vertex]) {
                continue;
            }

            isVisited[removedNode.vertex] = true;

            weightOfMsp += removedNode.weight; // If we reached till here
            // means this edge should be added so simply add it to msp basically we are assigning
            // this edge to the removed vertex

            for (WeightedUndirectedGraph_Imp.Edge neighbor : grf.adj[removedNode.vertex]) {
                if (!isVisited[neighbor.vertice]) {
                    pq.add(new Pair(neighbor.vertice, neighbor.weight)); // Observe
                    // here we are not doing 'removedNode.weight + neighbor.weight' instead we
                    // are just doing 'neighbor.weight' ie. just adding the weight of neighbor
                    // and not the cumulative weight of the path so far
                }
            }
        }


        System.out.println("Weight Of MSP is : " + weightOfMsp);


    }

    static class Pair implements Comparable<Pair> {
        int vertex;
        int weight;

        Pair(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(@NotNull Pair o) {
            return this.weight - o.weight;
        }
    }
}
