package Important_Misc;

import Algorithms.Dijkstra1;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PQ_Min_Max_And_CustomClass {
    public static void main(String args[]) {
        min_PQ();
        max_PQ();
        customClassPQ();
    }

    public static void min_PQ() {
        PriorityQueue<Integer> minPQInt = new PriorityQueue<>(); // Normal min PQ
        minPQInt.add(20);
        minPQInt.add(10);
        minPQInt.add(30);
        // Displaying
        displayPQ(minPQInt, minPQInt.size());

        PriorityQueue<Character> minPQCh = new PriorityQueue<>();
        minPQCh.add('a');
        minPQCh.add('c');
        minPQCh.add('b');
        // Displaying
        displayPQ(minPQCh, minPQCh.size());

        PriorityQueue<String> minPQStr = new PriorityQueue<>();
        minPQStr.add("aaa");
        minPQStr.add("aac");
        minPQStr.add("aab");
        // Displaying
        displayPQ(minPQStr, minPQStr.size());
    }

    public static void max_PQ() {
        // Below are the 2 ways in which we can make max pq
        PriorityQueue<Integer> maxPQInt1 = new PriorityQueue<>(Collections.reverseOrder()); // Normal min PQ
        maxPQInt1.add(10);
        maxPQInt1.add(30);
        maxPQInt1.add(20);
        // Displaying
        displayPQ(maxPQInt1, maxPQInt1.size());

        PriorityQueue<Integer> maxPQInt2 = new PriorityQueue<>((x, y) -> Integer.compare(y, x)); // Normal min PQ
        maxPQInt2.add(10);
        maxPQInt2.add(30);
        maxPQInt2.add(20);
        // Displaying
        displayPQ(maxPQInt2, maxPQInt2.size());

        // Same way we can do for these two as well
        PriorityQueue<Character> maxPQCh = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<String> minPQStr = new PriorityQueue<>(Collections.reverseOrder());
    }

    public static void customClassPQ() { // Shows how we can create PQ on custom class object
        PriorityQueue<Pair1> minPQPair = new PriorityQueue<>(); // Will use comparable that we specified in Pair1 class
        minPQPair.add(new Pair1(1, 20));
        minPQPair.add(new Pair1(2, 10));
        minPQPair.add(new Pair1(3, 30));
        displayPQForPair(minPQPair,minPQPair.size());

        PriorityQueue<Pair1> maxPQPair = new PriorityQueue<>(Collections.reverseOrder()); // Whatever
        // order we defined it will reverse it. We can make max pq by changing
        // in pair class as well by doing o.cost-this.cost
        maxPQPair.add(new Pair1(1, 20));
        maxPQPair.add(new Pair1(2, 10));
        maxPQPair.add(new Pair1(3, 30));
        displayPQForPair(maxPQPair,maxPQPair.size());

        PriorityQueue<Pair1> minPqUsingComparator
                = new PriorityQueue<Pair1>( // this will override the comparable that we defined in Pair1 class
                new Comparator<Pair1>() {
                    @Override
                    public int compare(Pair1 o1, Pair1 o2) {
                        return o1.cost - o2.cost;
                    }
                });
        minPqUsingComparator.add(new Pair1(1, 20));
        minPqUsingComparator.add(new Pair1(2, 10));
        minPqUsingComparator.add(new Pair1(3, 30));
        displayPQForPair(minPqUsingComparator,minPqUsingComparator.size());
    }

    static class Pair1 implements Comparable<Pair1> {
        int vertex;
        int cost;

        Pair1(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(@NotNull Pair1 o) {
            return this.cost - o.cost;
        }
    }

    public static <T> void displayPQ(PriorityQueue<T> pq, int pqSize) { // made a generic function so that I can pass nay time of PQ
        for (int i = 0; i < pqSize; i++) {
            System.out.println(pq.remove() + " ");
        }
        System.out.println();
    }
    public static void displayPQForPair(PriorityQueue<Pair1> pq, int pqSize) { // made a generic function so that I can pass nay time of PQ
        for (int i = 0; i < pqSize; i++) {
            Pair1 rem = pq.remove();
            System.out.println("[ "+rem.vertex + " , "+rem.cost+" ]");
        }
        System.out.println();
    }

}
