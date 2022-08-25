package Algorithms;

import Graph.WeightedDirectedGraph_Client;
import Graph.WeightedDirectedGraph_Imp;
import Graph.WeightedUndirectedGraph_Imp;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class BFS {
    public static void main(String arg[]) {
        WeightedUndirectedGraph_Imp grf = new WeightedUndirectedGraph_Imp(10);
        grf.addEdge(0, 1, 10);
        grf.addEdge(1, 2, 10);
        grf.addEdge(2, 3, 10);
        grf.addEdge(3, 0, 10);
        grf.addEdge(3, 4, 10);
        grf.addEdge(4, 5, 10);
        grf.addEdge(5, 6, 10);
        grf.addEdge(6, 4, 10);

        BFS.printBfsPath(grf, 2);
        BFS.bfsSearch(grf, 6);
    }

    // It is equivalent to level order of tree
    // (rm*wa*) Remove work mark add
    // If you want to find the shortest path in terms of edges use BFS since it moves in radius
    // TC is O(V+E)
    public static void printBfsPath(WeightedUndirectedGraph_Imp grf, int startVertex) {
        ArrayDeque que = new ArrayDeque<Pair>();
        boolean isVisited[] = new boolean[grf.adj.length];
        que.add(new Pair(startVertex, startVertex + ""));

        while (!que.isEmpty()) {
            //Remove
            Pair removedNode = (Pair) que.remove();

            //Check
            if (isVisited[removedNode.vertice]) {
                continue;
            }

            //Mark
            isVisited[removedNode.vertice] = true;

            //Work
            System.out.println(removedNode.psf);// I will simply print every path that
            // bfs take so you'll observe in the output that it first it will print 0 radius
            // nodes then nodes at radius 1 then nodes at radius 2 etc

            //Add
            for (WeightedUndirectedGraph_Imp.Edge neighbor : grf.adj[removedNode.vertice]) {
                que.add(new Pair(neighbor.vertice, removedNode.psf + neighbor.vertice));
            }
        }
    }

    public static void bfsSearch(WeightedUndirectedGraph_Imp grf, int nodeToBeFound) {
        ArrayDeque que = new ArrayDeque<Pair>();
        boolean isVisited[] = new boolean[grf.adj.length];

        Pair node = new Pair(-1, ""); // -1 means node not found

        que.add(new Pair(0, 0 + ""));// Starting search from node 0

        while (!que.isEmpty()) {

            //Remove
            Pair removedNode = (Pair) que.remove();

            //Check
            if (isVisited[removedNode.vertice]) {
                continue;
            }

            //Mark
            isVisited[removedNode.vertice] = true;

            //Work
            if (removedNode.vertice == nodeToBeFound) {
                node = removedNode;
                break;
            }

            //Add
            for (WeightedUndirectedGraph_Imp.Edge neighbor : grf.adj[removedNode.vertice]) {
                if (!isVisited[neighbor.vertice]) {
                    que.add(new Pair(neighbor.vertice, removedNode.psf + neighbor.vertice));
                }
            }
        }
        System.out.println("Element found and its value is : " + node.vertice + " and the path bfs had taken is :" + node.psf);

    }

    static class Pair {
        int vertice;
        String psf;

        Pair(int vertice, String psf) {
            this.vertice = vertice;
            this.psf = psf;
        }
    }

}
