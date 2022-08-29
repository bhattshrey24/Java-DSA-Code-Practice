package Algorithms;

public class DSU {
    // It stands for Disjoint Set Union
    // Here we have 2 functions Find and Union whose time complexity is Approximately O(1) each
    // In DSU we try to put similar objects in one group now
    // similarity depends on the question you are trying to solve
    // Every group has a leader ie. a number/object that represents the group. And leader
    // is the only object in the group which will be pointing to itself others will be
    // pointing to their parents

    // IMP - we can only use DSU in a question in which the similarity which is defined in the
    // question is transitive ie. suppose 1 is similar to 2 and 2 is similar to 3
    // then 1, 2 and 3 all should be similar. Just do some questions you'll get the idea


    public static void main(String args[]) {
        Pair edges[] = new Pair[]{new Pair(0, 1),
                new Pair(1, 2),
                new Pair(2, 4),
                new Pair(1, 3),
                new Pair(5, 6),
                new Pair(7, 8)};

        NormalDSUWithArray(9, edges);
        BestTimeComplexityDSUWithArray(9, edges);

    }

    // This is normal DSU implementation without path compression and union by rank
    // or size so its TC is O(logN)
    public static void NormalDSUWithArray(int numOfVertx, Pair[] Edges) { // These edges define
        // the similarities in question you will have to either find or they will
        // give you pair of edges that are similar

        //If we don't know how many total objects are their from start then use
        // Hashmap otherwise use array
        int[] parent = new int[numOfVertx];

        for (int i = 0; i < parent.length; i++) { // this is preprocessing
            parent[i] = i;// initially everyone is pointing
            // itself i.e. they are in their own set/leaders
        }

        for (Pair edge : Edges) {
            union(edge.u, edge.v, parent); // calling union for every edge ie. putting
            // similar objects in same group
        }

    }

    // Difference between leader and parent:-
    // Leader means leader of the whole group whereas
    // parent simply means parent of the current element
    public static int findLeader(int x, int[] arr) { // this is simple
        // Find operation here TC will be height of tree ie. O(logn)

        if (arr[x] == x) { //base case : Only leader will be pointing to
            // itself so if current element is pointing to itself it means it's the leader
            return x;
        }
        int parent = arr[x];
        return findLeader(parent, arr); // since we reached here it means I'm not the leader
        // of this group so we are saying recursion to go ask my parent if he is the
        // leader of the group or not.
    }

    public static void union(int u, int v, int[] parent) { // Simple union. It's TC is same as
        // the TC of find() which we are using here since we are using find()
        // without path compression therefore its TC is log(n)

        int loa = findLeader(u, parent); // loa means leader of a
        int lob = findLeader(v, parent);
        if (loa != lob) {
            parent[lob] = loa; // here I'm making lob point to loa ie. loa is the
            // ultimate leader of both groups but we can also make lob as ultimate leader
        }
    }

    public static void BestTimeComplexityDSUWithArray(int numOfVertx, Pair[] Edges) {
        int[] parent = new int[numOfVertx];

        // Preprocessing
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;// initially everyone is pointing
            // itself i.e. they are in their own set
        }
        int[] Rank = new int[numOfVertx];
        for (int i = 0; i < Rank.length; i++) {
            Rank[i] = 1; // giving every vertex(i.e. set) size = 1
            // because they all are initially pointing to themselves
            // therefore height/rank of tree of every set is 1.
        }

        for (Pair edge : Edges) {
            UnionByRank(edge.u, edge.v, parent, Rank); // Putting similar
            // objects in similar group
        }

    }

    public static int findLeaderWithPathCompression(int x, int[] arr) {
        if (arr[x] == x) {
            return x;
        }
        int parentOfCurrEle = arr[x];
        int leaderOfGroup = findLeaderWithPathCompression(parentOfCurrEle, arr); // have faith that recursion will return the parent of this group

        arr[x] = leaderOfGroup;// this is sort of backtracking because this will be executed
        // when stack falls
        // so what we are doing is that we have faith that recursion will give us the
        // leader of the group so the leader is stored in 'leaderOfGroup' so in above line we are
        // just pointing current element directly to leader
        return leaderOfGroup;
    }

    public static void UnionByRank(int u, int v, int[] parent, int[] rank) {// Rank simply
        // means height of tree. Since TC of union depends of TC of find() that we use in it and
        // since here the find() that we are using is using path compression so its TC is O(1) that's
        // why TC of this Union is also O(1)

        int loa = findLeaderWithPathCompression(u, parent); // loa means leader of 'a'
        int lob = findLeaderWithPathCompression(v, parent);

        if (loa != lob) {
            if (rank[loa] > rank[lob]) { // we always connect smaller height tree to bigger
                // height tree so that resultant height doesn't increase more than bigger tree's height
                parent[lob] = loa; // making loa as the ultimate leader of both groups
            } else if (rank[loa] < rank[lob]) {
                parent[loa] = lob;// making lob as the ultimate leader of both groups

            } else { // this is the condition for rank[a]==rank[b] and only in this case the
                // height/rank of tree will increase since both trees has same height
                parent[lob] = loa;// I'm making loa as ultimate leader but we could also make lob
                // as ultimate leader

                rank[loa]++; // increase the rank of the new group by
                // increasing the count stored at leaders index
            }
        }
    }

    public static void UnionBySize(int u, int v, int[] parent, int[] size) { // This is another way to
        // compress tree here instead of height we compare the size ie. how many
        // nodes/objects/elements are their in the group

        int loa = findLeaderWithPathCompression(u, parent);
        int lob = findLeaderWithPathCompression(v, parent);
        if (loa != lob) {
            if (size[loa] >= size[lob]) { // we always connect smaller size tree with bigger size tree
                // here we also merged the case of equal size tree because it doesn't matter who is
                // parent of whom if sizes are equal unlike in the case of rank

                parent[lob] = loa;// making loa as the ultimate leader of the new group

                size[loa] += size[lob];// since whole tree with leader 'a' is added to 'b' ie.
                // we add all the members to the newly created group

            } else {// this is executed if size [loa]<size[lob]
                parent[loa] = lob;
                size[lob] += size[loa];
            }
        }

    }

    public static class Pair {
        int u, v;

        public Pair(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

}
