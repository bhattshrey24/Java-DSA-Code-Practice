package Algorithms;

import Graph.WeightedDirectedGraph_Imp;
import Graph.WeightedUndirectedGraph_Client;
import Graph.WeightedUndirectedGraph_Imp;
import org.jetbrains.annotations.NotNull;

import java.util.PriorityQueue;

public class Dijkstra2 {

    public static void main(String args[]) {
        WeightedDirectedGraph_Imp grf = new WeightedDirectedGraph_Imp(6);
        grf.addEdge(0, 1, 8);
        grf.addEdge(1, 2, 2);
        grf.addEdge(2, 3, 9);
        grf.addEdge(0, 3, 4);
        grf.addEdge(3, 4, 3);
        grf.addEdge(4, 5, 1);
        grf.addEdge(2, 5, 5);

        dijkstra2(grf, 0);
    }



    // First see 'Dijkstra1' then see this.

    // In Dijkstra1 we find min path from vertex A to vertex B here
    // we will find the shortest path from A to all other vertexes just
    // like we do in Bellman ford. If you are given only +ve weights, and
    // you have to find path from
    // vertex A to all other vertex then use this Dijkstra method
    // cause obviously TC of dijkstra is better than bellman ford but
    // if your graph has -ve weight then you have to use bellman ford only
    public static void dijkstra2(WeightedDirectedGraph_Imp grf, int startVertex) {
        int ans[] = new int[grf.adj.length];

        boolean isVisited[] = new boolean[grf.adj.length];
        PriorityQueue pq = new PriorityQueue<Pair>();

        pq.add(new Pair(startVertex, 0));

        while (!pq.isEmpty()) {
            Pair removedNode = (Pair) pq.remove();

            if (isVisited[removedNode.vertex]) {
                continue;
            }

            isVisited[removedNode.vertex] = true;

            ans[removedNode.vertex] = removedNode.cost; // observe here we are not stopping
            // like in Dijkstra1 when we reach destination node we
            // stop but here we are not. This loop will only stop when queue is empty.
            // This works because dijkstra states that if you are marking a vertex
            // visited ie. you are visiting it for the first time
            // with some weight then that weight is the smallest positive weight
            // to reach from source to that vertex since there are no -ve weights
            // allowed, and we are using PQ. We basically process all
            // vertices and then come out of loop

            for (WeightedDirectedGraph_Imp.Edge neighbor : grf.adj[removedNode.vertex]) {
                if (!isVisited[neighbor.vertice]) {
                    pq.add(new Pair(
                            neighbor.vertice,
                            removedNode.cost + neighbor.weight));
                }
            }

        }

        System.out.println("Solution: ");
        for (int ele : ans) {
            System.out.print(ele + " ");
        }

    }


    static class Pair implements Comparable<Pair> {
        int vertex;
        int cost;

        Pair(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(@NotNull Pair o) {
            return this.cost - o.cost;
        }
    }
}
