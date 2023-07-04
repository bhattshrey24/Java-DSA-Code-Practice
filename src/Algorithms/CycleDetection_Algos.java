package Algorithms;

import Graph.WeightedDirectedGraph_Imp;
import Graph.WeightedUndirectedGraph_Imp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

import static Algorithms.DSU.UnionByRank;
import static Algorithms.DSU.findLeaderWithPathCompression;

public class CycleDetection_Algos {
    // These are some famous ways using which we can detect a cycle in a graph
    // All are coded by me except DFS ones so it maybe 100% right or not like the concept is right

    public static void main(String args[]) {
        WeightedUndirectedGraph_Imp grfUn = new WeightedUndirectedGraph_Imp(5);
        grfUn.addEdge(0, 1, -1); // -1 because weight does not matter here
        grfUn.addEdge(1, 2, -1);
        grfUn.addEdge(1, 3, -1);
        grfUn.addEdge(3, 4, -1);
//        grfUn.addEdge(4, 5, -1);
//        grfUn.addEdge(5, 3, -1);
        dfsCycleDetectionInUndirected(grfUn, grfUn.adj.length);
        bfsCycleDetectionInUndirectedGraph(grfUn,grfUn.adj.length);

        DSU.Pair edges[] = new DSU.Pair[]{ // we made a separate edge graph for DSU because
                // DSU will consider each edge as undirected edge only but in our normal graph
                // we make edge as undirected by making source point to vertex and vertex point to source
                // so this way if we are traversing DSU cycle finding algo then it will get lets say (0,1) edge and it
                // will make suppose 0 as leader and union them then it will get edge (1,0) since its an
                // undirectional graph but 1 and 0 are already put in same group so DSU will think it found a
                // cycle but thats wrong
                new DSU.Pair(0, 1),
                new DSU.Pair(1, 2),
                new DSU.Pair(1, 3),
                new DSU.Pair(3, 4),
//                new DSU.Pair(4, 5),
//                new DSU.Pair(5, 3)
        };
        dsuCycleDetection(edges, 5);

        WeightedDirectedGraph_Imp grfD = new WeightedDirectedGraph_Imp(6);
        grfD.addEdge(0, 1, 0); // here putting weight for bellmanford does not matter here
        grfD.addEdge(1, 4, 1);
        grfD.addEdge(4, 3, 2);
        grfD.addEdge(3, 2, 3);
        grfD.addEdge(1, 2, 4);
        grfD.addEdge(4, 5, 5);
//        grfD.addEdge(5, 6, -6);
//        grfD.addEdge(6, 7, -7);
//        grfD.addEdge(7, 4, -8);

        dfsCycleDetectionInDirected(grfD, grfD.adj.length);
        kahnsCycleDetection(grfD, grfD.adj.length);
        bellmanFordNegativeCycleDetection(grfD, grfD.adj.length);

    }

    //todo
    // Cycle Detection using BFS
    // https://www.youtube.com/watch?v=BPlrALf1LDU
    // Must watch the video because he explained the intution very well which is based
    // on the fact that bfs moves in radius.

    // This algo is just for undirected graph , For using BFS in directed graph
    // I guess its not that important but look into it if you have time
    public static void bfsCycleDetectionInUndirectedGraph(WeightedUndirectedGraph_Imp grf, int noOfVertices) {
        boolean cycleFound = false;
        boolean[] isVisited = new boolean[noOfVertices];
        for (int i = 0; i < noOfVertices; i++) {
            if (!isVisited[i]) { // basically running BFS check for every component of the graph
                if (bfsCycleCheck(i, grf, isVisited)) {
                    cycleFound = true;
                    break;
                }
            }
        }
       if(cycleFound){
           System.out.println("Cycle Found In Undirected Graph by BFS");
       }else{
           System.out.println("No Cycle Found In Undirected Graph by BFS");
       }
    }
    public static boolean bfsCycleCheck(int currVertex, WeightedUndirectedGraph_Imp grf, boolean[] isVisited) {
        ArrayDeque<BFSPair> q = new ArrayDeque<>();

        q.add(new BFSPair(currVertex, -1)); // adding starting vertex with -1
        // because it has no parent

        while (!q.isEmpty()) {
            BFSPair removed = q.remove();
            isVisited[removed.v] = true;

            for (WeightedUndirectedGraph_Imp.Edge neighbor : grf.adj[removed.v]) {
                if (isVisited[neighbor.vertice]) {
                    if (neighbor.vertice != removed.parent) { // This means we found a cycle because otherwise
                        // this would not have been possible because BFS runs in radius and it branches out
                        // and the branches will only meet if there is a cycle.
                        return true;
                    }
                } else {
                    q.add(new BFSPair(neighbor.vertice, removed.v));
                }
            }
        }
        return false;
    }
    static class  BFSPair {
        int v;
        int parent;

        BFSPair(int v, int parent) {
            this.v = v;
            this.parent = parent;
        }
    }

    //todo
    // Cycle Detection using DFS
    // https://www.youtube.com/watch?v=Y9NFqI6Pzd4
    public static void dfsCycleDetectionInUndirected(WeightedUndirectedGraph_Imp grf, int noOfVertices) { // Observe its
        // undirected and its different for directed graph approach because here while checking
        // neighbors of a vertex we can encounter our DFS parent as well (i.e.
        // parent node because of DFS path ) because all edges are bidirectional therefore we have to keep a
        // parent array as well
        boolean[] isVisited = new boolean[noOfVertices];
        boolean isCyclePresent = false;
        for (int i = 0; i < noOfVertices; i++) {
            if (!isVisited[i]) {
                if (dfsForUnDirectedGrf(i, -1, isVisited, grf)) {// -1 means initial node jis se DFS start hora hai uska
                    // koi parent nhi hai
                    isCyclePresent = true;
                    break;
                }
            }
        }
        if (isCyclePresent) {
            System.out.println("Cycle Detected in Undirected Graph by DFS");
        } else {
            System.out.println("No Cycle Detected in Undirected Graph by DFS");
        }
    }

    public static boolean dfsForUnDirectedGrf(int currVertex, int parent, boolean[] isVis, WeightedUndirectedGraph_Imp grf) {
        // Mark
        isVis[currVertex] = true;
        // Add and work
        for (WeightedUndirectedGraph_Imp.Edge neighbor : grf.adj[currVertex]) {
            if (!isVis[neighbor.vertice]) {
                if (dfsForUnDirectedGrf(neighbor.vertice, currVertex, isVis, grf)) {
                    return true;
                }
            } else if (neighbor.vertice != parent) {
                return true;
            }
        }
        //isVis[currVertex]=false; // No need of doing this because I guess in undirected graph it doesn't matter
        // like no dfs call will block other paths
        return false;// i.e. if we reached here means no cycle was detected so return false
    }

    // https://www.youtube.com/watch?v=uzVUw90ZFIg
    public static void dfsCycleDetectionInDirected(WeightedDirectedGraph_Imp grf, int noOfVertices) {

        boolean[] isVis = new boolean[noOfVertices];
        boolean[] isVisInCurrDfsPath = new boolean[noOfVertices]; // By keeping 2 visited array , we can improve
        // Our TC and not do useless work.
        // The isVis helps us to not check already checked path by dfs and isVisInCurrDfsPath helps us to find
        // cycle
        boolean isCycleDetected = false;

        for (int i = 0; i < noOfVertices; i++) {
            if (!isVis[i]) {
                isCycleDetected = dfsForDirectedGrf(grf, i, isVis, isVisInCurrDfsPath);
                if (isCycleDetected) {
                    break;
                }
            }
        }
        if (isCycleDetected) {
            System.out.println("Cycle Detected in Directed Graph By DFS");
        } else {
            System.out.println("No Cycle Detected in Directed Graph By DFS");
        }
    }

    public static boolean dfsForDirectedGrf(WeightedDirectedGraph_Imp grf, int currVertex, boolean[] isVis, boolean[] isVisForCurrDfsPath) {
        // Mark
        isVis[currVertex] = true;
        isVisForCurrDfsPath[currVertex] = true;

        // Add and work
        for (WeightedDirectedGraph_Imp.Edge neighbor : grf.adj[currVertex]) {
            if (!isVis[neighbor.vertice]) {
                if (dfsForDirectedGrf(grf, neighbor.vertice, isVis, isVisForCurrDfsPath)) {  // We are telling df
                    // to go check if there is any cycle present in this path
                    return true;
                }
            } else if (isVisForCurrDfsPath[neighbor.vertice]) { // if we come here means current neighbor is visited
                // so we check whether we visited this neighbor in current DFS? if yes means we found a cycle
                return true;
            }

        }

        // marking it false while backtracking so that current DFS call can traverse all possible paths
        isVisForCurrDfsPath[currVertex] = false;

        return false;// i.e. if we reached here means no cycle was detected so return false
    }


    //todo
    // Cycle Detection using Kahns Algo (Kahns is explained in 'topologicalSort' class in 'sorting' package)

    // We will run kahns algo and if the result is incomplete i.e. if
    // we are not able to find full topological order then there is a cycle because at some point
    // there was no vertex with 0 indegree which means it was a cycle therefore our result was
    // incomplete. So simply check in the end whether the length of result is same as num of nodes
    public static void kahnsCycleDetection(WeightedDirectedGraph_Imp grf, int noOfVertices) {
        // preprocessing
        int[] indegree = new int[noOfVertices];
        for (int i = 0; i < noOfVertices; i++) {
            for (WeightedDirectedGraph_Imp.Edge edge : grf.adj[i]) {
                indegree[edge.vertice] += 1;
            }
        }
        // running the algo
        ArrayDeque<Integer> que = new ArrayDeque<Integer>();
        ArrayList<Integer> kahnsAns = new ArrayList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                que.add(i);
            }
        }
        while (!que.isEmpty()) {
            int removedVertex = que.remove();
            kahnsAns.add(removedVertex);
            for (WeightedDirectedGraph_Imp.Edge children : grf.adj[removedVertex]) {
                indegree[children.vertice] -= 1;
                if (indegree[children.vertice] == 0) {
                    que.add(children.vertice);
                }
            }
        }

        if (kahnsAns.size() == noOfVertices) {
            System.out.println("No Cycle Detected By Kahns");
        } else {
            System.out.println("Cycle Detected By Kahns");
        }
    }

    //todo
    // Cycle Detection using DSU (using this we can find the edge which is making the graph cyclic)
    // We can only find cycle in undirected graph using DSU
    // WE CANNOT FIND CYCLE IN DIRECTED GRAPH USING DSU because dsu cannot understand directions of edge
    // it can only group similar objects

    // while doing union with graph's edges if we get an edge who's u and v vertex already
    // have same leader i.e. they are in the same group then it means this is a cycle
    public static void dsuCycleDetection(DSU.Pair edges[], int noOfVertices) {
        // Preprocessing
        int[] parent = new int[noOfVertices];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        int[] rank = new int[noOfVertices];
        Arrays.fill(rank, 1);

        // Running DSU
        boolean isCycleFound = false;

        for (DSU.Pair edge : edges) {
            int loU = findLeaderWithPathCompression(edge.u, parent);
            int loV = findLeaderWithPathCompression(edge.v, parent);
            if (loU == loV) { // i.e. if leader of edges are same means we found a cycle
                isCycleFound = true;
                break;
            } else {
                UnionByRank(edge.u, edge.v, parent, rank);
            }
        }
        if (isCycleFound) {
            System.out.println("Cycle Found by DSU method");
        } else {
            System.out.println("No Cycle Found by DSU method");
        }
    }

    //todo
    // Cycle Detection using BellmanFord (This is special because using bellmanFord we can find NEGATIVE cycle)

    // if the answer of bellman ford which is stored in array changes in vth iteration means
    // there is a negative cycle
    public static void bellmanFordNegativeCycleDetection(WeightedDirectedGraph_Imp grf, int noOfVertices) {

        // Saving our graph's edges in an arraylist so that it's easier to
        // implement bellman ford and the code looks cleaner too
        ArrayList<BellmanFord.Pair> listOfEdges = new ArrayList<>(); // this will contain all edges of our graph
        for (int i = 0; i < noOfVertices; i++) {
            for (WeightedDirectedGraph_Imp.Edge edge : grf.adj[i]) {
                listOfEdges.add(new BellmanFord.Pair(i, edge.vertice, edge.weight));
            }
        }

        boolean isArrayChanged = bellmanFord(grf, 0, listOfEdges);

        if (isArrayChanged) {
            System.out.println("Negative cycle detected");
        } else {
            System.out.println("No negative cycle detected");
        }

    }
    public static boolean bellmanFord(WeightedDirectedGraph_Imp grf, int startVertx, ArrayList<BellmanFord.Pair> listOfEdges) {
        // step 1 initializing array ie. preprocessing
        int ans[] = new int[grf.adj.length];
        for (int i = 0; i < ans.length; i++) {
            if (i == startVertx) {
                ans[i] = 0;
            } else {
                ans[i] = Integer.MAX_VALUE;
            }
        }

        boolean isArrayChanged = false;
        int checkArray[] = new int[ans.length];

        // running bellman ford
        for (int i = 0; i < (grf.adj.length); i++) { // Processing V times and not V-1 because we want to
            // check if ans array changes after V-1th iteration
            for (int j = 0; j < listOfEdges.size(); j++) {
                BellmanFord.Pair edge = listOfEdges.get(j);
                if (ans[edge.source] == Integer.MAX_VALUE) {
                    continue;
                }
                if (ans[edge.source] + edge.weight < ans[edge.destination]) {
                    ans[edge.destination] = ans[edge.source] + edge.weight;
                }

            }

            if (i == grf.adj.length - 2) { //Just copying the v-1th iteration array so that we can check later
                // and '-2' because vertex starts from 0
                int j = 0;
                for (int ele : ans) {
                    checkArray[j] = ele;
                    j++;
                }
            }

        }

        for (int i = 0; i < ans.length; i++) {
            if (ans[i] != checkArray[i]) {
                isArrayChanged = true;
                break;
            }
        }

        return isArrayChanged;
    }

}
