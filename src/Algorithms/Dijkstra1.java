package Algorithms;

import Graph.WeightedUndirectedGraph_Imp;
import org.jetbrains.annotations.NotNull;

import java.util.PriorityQueue;

public class Dijkstra1 {
    public static void main(String args[]) {
        WeightedUndirectedGraph_Imp grf = new WeightedUndirectedGraph_Imp(10);
        grf.addEdge(0, 1, 10);
        grf.addEdge(1, 2, 10);
        grf.addEdge(2, 3, 10);
        grf.addEdge(3, 0, 40);
        grf.addEdge(3, 4, 2);
        grf.addEdge(4, 5, 3);
        grf.addEdge(5, 6, 3);
        grf.addEdge(6, 4, 8);

        dijkstra(grf, 0, 6);

    }

    // Dijkstra AKA Single Source Shortest Path
    // We use Dijkstra if we want to find the shortest path from A to B in terms of weight
    // Dijkstra may or may not work for -ve weights
    // Dijkstra is same as BFS the difference is just that here instead of
    // Queue we will use PriorityQueue
    // It Uses Greedy Approach since it chooses the best path at every step
    // Tc of dijkstra is : O(ELogV)
    public static void dijkstra(WeightedUndirectedGraph_Imp grf, int startVertex, int destinationVertex) {
        PriorityQueue pq = new PriorityQueue<Pair>();

        boolean isVisited[] = new boolean[grf.adj.length];

        Pair ans = new Pair(-1, -1, "");

        pq.add(new Pair(startVertex, 0, startVertex + ""));

        while (!pq.isEmpty()) {
                Pair removedNode = (Pair) pq.remove();

                if (isVisited[removedNode.vertex]) {
                    continue;
                }

                isVisited[removedNode.vertex] = true;

                if (removedNode.vertex == destinationVertex) {
                    ans = removedNode;
                    break;
                }

                for (WeightedUndirectedGraph_Imp.Edge neighbor : grf.adj[removedNode.vertex]) {
                    if (!isVisited[neighbor.vertice]) {
                        pq.add(new Pair(
                                neighbor.vertice,
                                removedNode.cost + neighbor.weight,
                                removedNode.psf + neighbor.vertice)); // weight is simply edge
                        // cost/weight that is joining removedNode with neighbor
                    }
                }
        }
        System.out.println("Found Element " + ans.vertex + " at a cost of " + ans.cost + " units " +
                "distance and the path taken by dijkstra is : " + ans.psf);
    }

    static class Pair implements Comparable<Pair> { // We have to implement  Comparable and
        // override 'compareTo()' because otherwise PriorityQueue will not be able to know which
        // Pair is smaller or bigger
        int vertex;
        int cost;
        String psf;

        Pair(int vertex, int cost, String psf) {
            this.vertex = vertex;
            this.cost = cost;
            this.psf = psf;
        }

        @Override
        public int compareTo(@NotNull Pair o) { // We are telling how to compare 2 objects

            return this.cost - o.cost; //In my opinion what happens is suppose this function
            // is called for Pair1.compareTo(Pair2) and Pair 1 has cost '4' and Pair 2 has
            // cost '5'. Now 'this' represents Pair1 and 'o' represent Pair 2. Now when this
            // function returns -ve then it chooses the 'this' object ie. Pair 1
            // and when it returns +ve then it chooses the 'o' object ie. Pair 2. So now here if we
            // do 'this.cost-o.cost' so it will be Pair1.cost-Pair2.cost = 4-5 = -1 = -ve so it will
            // choose Pair 1 but if we do 'o.cost-this.cost' then it will result in
            // Pair2.cost-Pair1.cost = 5-4 = +ve so since its +ve therefore it will choose Pair 2
        }
    }
}
