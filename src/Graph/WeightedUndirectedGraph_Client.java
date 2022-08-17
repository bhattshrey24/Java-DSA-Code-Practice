package Graph;

public class WeightedUndirectedGraph_Client {
    public static void main(String[] args) {
        WeightedUndirectedGraph_Imp UDgraph = new WeightedUndirectedGraph_Imp(3);
        UDgraph.addEdge(0, 1, 10);
        UDgraph.addEdge(1, 2, 20);
        UDgraph.addEdge(2, 0, 30);
        UDgraph.displayGraph();
    }
}
