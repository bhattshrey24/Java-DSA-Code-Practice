package Graph;

import java.util.LinkedList;

public class WeightedDirectedGraph_Imp {

    public LinkedList<Edge> adj[];// array of type 'LinkedList<Edge>' Aka adjacency matrix

    public WeightedDirectedGraph_Imp(int NumberOfVertices) {
        adj = new LinkedList[NumberOfVertices];// we are initializing adj array , we are just telling its size

        for (int i = 0; i < NumberOfVertices; i++) { // assuming vertices starts from 0
            adj[i] = new LinkedList<Edge>();// we are connecting a empty linked list to every vertex i.e element of
            // array adj
        }
    }

   public class Edge { // it will look like a[1]=>(v:2,w:8)->(v:4,w:5)
        public int vertice;
        public int weight;

        Edge(int v, int w) {
            vertice = v;
            weight = w;
        }
    }

    public void addEdge(int source, int destination, int weight) {
        adj[source].add(new Edge(destination, weight));// simply adding a node in linkedlist of vertice 'source'
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
