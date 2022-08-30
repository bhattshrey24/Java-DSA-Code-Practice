package Graph;

import java.util.LinkedList;

public class WeightedUndirectedGraph_Imp {

    public LinkedList<Edge> adj[];// array of linked List

    public WeightedUndirectedGraph_Imp(int NumberOfVertices) {
        adj = new LinkedList[NumberOfVertices];// we are initializing adj array , we are just telling its size like int []a;
        // a=new int[v];

        for (int i = 0; i < NumberOfVertices; i++) { // assuming vertices starts from 0
            adj[i] = new LinkedList<Edge>();// we are connecting a empty linked list to every vertex i.e element of
            // array adj
        }

    }

    public static class Edge { // Our graph is made of linkedlist of 'Edge' class instead of 'Integer'
        // the graph will look like a[1]=>(v:2,w:8)->(v:4,w:5)
        public int vertice;
        public int weight;

        Edge(int v, int w) {
            vertice = v;
            weight = w;
        }
    }

    public void addEdge(int source, int destination, int weight) {
        // creating an undirected graph therefore I connected source with destination as
        // well as destination with source
        adj[source].add(new Edge(destination, weight));// simply adding the a node in linkedlist of vertice 'source'
        adj[destination].add(new Edge(source, weight));// if we simply remove this line then it becomes directed graph
    }

    public void displayGraph() {
        for (int i = 0; i < adj.length; i++) {
            System.out.print("a[" + i + "]=>");
            for (Edge edge : adj[i]) {
                System.out.print("| v:" + edge.vertice + " w:" + edge.weight + "|-->");
            }
            System.out.println();
        }
        System.out.println();
    }
}
