package Sorting;

import Graph.WeightedDirectedGraph_Imp;

import java.util.ArrayDeque;
import java.util.LinkedList;

public class TopologicalSort {
    public static void main(String[] args) {
        WeightedDirectedGraph_Imp grf = new WeightedDirectedGraph_Imp(6);
        grf.addEdge(0, 2, 0);
        grf.addEdge(0, 1, 0);
        grf.addEdge(4, 1, 0);
        grf.addEdge(4, 5, 0);
        grf.addEdge(2, 3, 0);
        grf.addEdge(3, 5, 0);
        topologicalSortUsingDFS(grf);
        topologicalSortUsingKahnAlgo(grf);
    }


    // A topological sort is a sort in which parent vertices should appear before their
    // children and grand children and grand-grand children and so on.There is no rule for
    // sibling they can appear in any order.A graph can have more than
    // one possible valid arrangement of topological sort

    // Using topological sort we try to linearize the graph i.e. trying to
    // convert it into a linear structure

    //Condition:
    // Topological sort is only available for directed acyclic graph(Because otherwise
    // we won't be able to define who is father of whom like in undirected we
    // cant determine who is father of whom and also in cyclic graph we can't define
    // who is father of whom)

    // There are 2 ways to implement topological sort
    // 1) Recursive way i.e. by using DFS TC=O(E+V)
    // 2) Using Kahn's Algo . This is an iterative approach TC=O(E+V) because
    // TC of calculating indegree is O(E+V) since we travel all edges. We can also
    // detect cycle using kahns algo , simply run kahn algo and count how many
    // vertices you printed if they are not equal to the total number of vertex
    // then cycle is detected since topological won't be able to print all vertices if
    // there is a cycle
    public static void topologicalSortUsingDFS(WeightedDirectedGraph_Imp grf) { // we know dfs moves
        // in depth so we can simply run a dfs and while backtracking we
        // can add children into linkedList.
        // Keep in mind we run dfs for each vertex otherwise we might miss
        // a component , see subhesh bhaiya video if doubt

        LinkedList<Integer> ll = new LinkedList<Integer>();
        boolean isVisited[] = new boolean[grf.adj.length];

        for (int i = 0; i < grf.adj.length; i++) {
            DFS(grf, isVisited, i, ll); // since array are sort of passed by reference
            // therefore isVisited will keep track of all the visited vertices, and
            // we won't do any duplicate work in subsequent DFS calls
        }

        // Displaying the solution
        System.out.println("Printing solution using DFS: ");
        for (int ele : ll) {
            System.out.print(ele + " ");
        }
        System.out.println("");
    }

    public static void DFS(WeightedDirectedGraph_Imp grf, boolean[] isVisited,
                           int currVertex, LinkedList<Integer> ll) {

        if (isVisited[currVertex]) { // Check
            return;
        }

        isVisited[currVertex] = true; // Mark

        // Work (But here we don't have to do any work so leave it blank)

        for (WeightedDirectedGraph_Imp.Edge nbr : grf.adj[currVertex]) {
            if (!isVisited[nbr.vertice]) {
                DFS(grf, isVisited, nbr.vertice, ll);
            }
        }

        // (Backtracking) Add in linkedList as addFirst
        ll.addFirst(currVertex);
    }

    public static void topologicalSortUsingKahnAlgo(WeightedDirectedGraph_Imp grf) { // In this method, instead of adding children
        // first (which we did in dfs approach) we add parent first. In this the 1st node
        // that we add to the answer is the node which is not dependent on any other node
        // i.e. which does not have a father(i.e. 0 number of indegree) and there will always be a node which
        // does not have a father because cycle in a graph is not allowed in topological
        // Sort. Then simply we decrease the dependency of its children by one since we
        // printed one of their father. Now we find next node which has 0 dependency and
        // print it and reduce its children dependency by one and so on

        // Learn the algo

        // Preprocessing
        // creating indegree array in which we put indegree(ie. incoming edge) of each vertex/node
        int indegree[] = new int[grf.adj.length];
        for (int i = 0; i < grf.adj.length; i++) {
            for (WeightedDirectedGraph_Imp.Edge edge : grf.adj[i]) {
                indegree[edge.vertice] += 1; // i.e. like suppose for i=0 we will
                // traverse all children of '0' and add 1 to it because '0' is one of their
                // father.
            }
        }
        ArrayDeque<Integer> que = new ArrayDeque<Integer>();

        // Add all vertex which has indegree 0 to the queue because they are ready to be
        // printed
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                que.add(i);
            }
        }
        System.out.println("Solution using kahn algo : ");

        while (!que.isEmpty()) {
            int removedVertex = que.remove();

            System.out.print(removedVertex + " "); // Simply print because we only add a
            // vertex to the queue when all its dependencies are finished i.e. already printed

            for (WeightedDirectedGraph_Imp.Edge children : grf.adj[removedVertex]) {
                indegree[children.vertice] -= 1; // decrease the dependency since we
                // printed one of its father
                if (indegree[children.vertice] == 0) { // ie. if all dependency of the
                    // children are finished then simply add to queue because now its
                    // ready to be printed
                    que.add(children.vertice);
                }
            }

        }
    }

}
