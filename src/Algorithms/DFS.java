package Algorithms;

import Graph.WeightedUndirectedGraph_Imp;

import java.util.Stack;

public class DFS {
    public static void main(String args[]) {
        WeightedUndirectedGraph_Imp grf = new WeightedUndirectedGraph_Imp(10);
        grf.addEdge(0, 1, 10);
        grf.addEdge(1, 2, 10);
        grf.addEdge(2, 3, 10);
        grf.addEdge(3, 0, 10);
        grf.addEdge(3, 4, 10);
        grf.addEdge(4, 5, 10);
        grf.addEdge(5, 6, 10);
        grf.addEdge(6, 4, 10);

        dfsPathUtil(grf, 0);
        dfsIterative(grf, 4);

    }


    //RMWA
    public static void dfsPathUtil(WeightedUndirectedGraph_Imp grf, int startVertex) {
        dfsPath(grf, startVertex, new boolean[grf.adj.length], startVertex + "");
    }

   // Here the stack is the memory stack which recursion will create
    public static void dfsPath(WeightedUndirectedGraph_Imp grf, int currVertex, boolean[] isVisited, String psf) {
        // Check
        if (isVisited[currVertex]) { // base case
            return;
        }
        // Mark
        isVisited[currVertex] = true;

        // Work
        System.out.println(psf);

        // Add
        for (WeightedUndirectedGraph_Imp.Edge neighbor : grf.adj[currVertex]) {
            if (!isVisited[neighbor.vertice]) {
                dfsPath(grf, neighbor.vertice, isVisited, psf + neighbor.vertice);
            }
        }

    }

    public static void dfsIterative(WeightedUndirectedGraph_Imp grf, int eleToBeFound) {
        Stack stack = new Stack(); // The only difference btw dfs iterative implementation and bfs
        // implementation is that we used stack here

        Pair node = new Pair(-1, ""); // -1 means no element found
        boolean isVisited[] = new boolean[grf.adj.length];

        stack.push(new Pair(0, 0 + ""));// starting from 0


        while (!stack.isEmpty()) {
            //Remove
            Pair removedVertex = (Pair) stack.pop();
            //Check
            if (isVisited[removedVertex.vertice]) {
                continue;
            }
            //Mark
            isVisited[removedVertex.vertice] = true;

            //Work
            if (removedVertex.vertice == eleToBeFound) {
                node = removedVertex;
                break;
            }

            //Add
            for (WeightedUndirectedGraph_Imp.Edge neighbor : grf.adj[removedVertex.vertice]) {
                if (!isVisited[neighbor.vertice]) {
                    stack.push(new Pair(neighbor.vertice, removedVertex.psf + neighbor.vertice));
                }
            }

        }
        System.out.println("Element " + node.vertice + " Found , Path DFS took : " + node.psf);
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
