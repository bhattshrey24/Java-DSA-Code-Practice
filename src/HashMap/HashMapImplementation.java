package HashMap;

import java.util.*;

public class HashMapImplementation<K, V> {

    private int size; // n

    private class HMNode {
        K key;
        V value;

        public HMNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<HMNode>[] buckets;// array of linkedList , (N=bucket.length)

    // LAMBDA = SIZE/BUCKETS.LENTGH I.E. n/N

    private double K = 2.0; // this value was deduced by scientist i.e. they did lot of trial and error and
    // found that we get best time complexity if K is 2.0

    public HashMapImplementation() {

        initBuckets(4);// we're just initializing 4 buckets
        size = 0;// i.e. initially HM is empty
    }

    private void initBuckets(int N) {// simply initializing the buckets with empty linkedList
        buckets = new LinkedList[N]; //creating array of given size
        for (int bi = 0; bi < buckets.length; bi++) {
            buckets[bi] = new LinkedList<HashMapImplementation<K, V>.HMNode>(); // we have to initialize with empty
            // LinkedList otherwise it will give
            // null pointer exception later
        }
    }

    //todo
    // PUT Method's Working:-
    // The Working is that we give the key to hashFuction and it return the index in
    // which either the keys-value pair is present or where it should be inserted ,
    // then we simply linearly check in that bucket only that weather the key is
    // already present or not if yes then simply update the value if not then add
    // the key-value pair.
    // In put operation if only value of the key changed then its cool but if key
    // was not present and we added a new key-value pair in this case we have to
    // check if the lambda is reached more than 'K' or not if not then cool dont do
    // anything if yes then simply do Rehashing. In Rehashing we simply make an
    // array of size twice the previous one and copy all the elements in it by
    // calling put.

    public void put(K key, V value) {
        int bi = hashfn(key); // bi = bucket index which will be given to us by hash function
        int di = getIndexWithinBucket(key, bi);// di = data index , i.e. getIndexWithinBucket() will simply match the
        // key with all the elements in the bucket and return the index of
        // node(i.e. HMNode) if match was found otherwise returns -1

        if (di != -1) {// node already present so simply modify the value
            HMNode node = buckets[bi].get(di);
            node.value = value;
        } else {// insert
            HMNode node = new HMNode(key, value);
            buckets[bi].add(node);
            size++;
        }

        double lambda = size * 1.0 / buckets.length; // observe its 'double' not int cause agar lamda 'K' se thorda bhi
        // upr
        // hua toh we
        // rehash . 'size' is 'int' therefore we convert it to double by
        // doing size * 1.0

        if (lambda > K) {
            reHash();
        }
    }

    private void reHash() {
        LinkedList<HMNode>[] oldBuckets = buckets;

        initBuckets(oldBuckets.length * 2); // initialize the buckets with new array of 2 times the size of oldBucket

        size = 0;// cause ab hum nai bucket mei daalenge node toh currently new bucket is empty

        for (int i = 0; i < oldBuckets.length; i++) {
            for (HMNode node : oldBuckets[i]) {
                put(node.key, node.value); // simply put it in new buckets , size will be updated inside put function
            }
        }
    }

    private int getIndexWithinBucket(K key, int bi) {
        int idx = 0;
        for (HMNode hmn : buckets[bi]) {
            if (hmn.key.equals(key)) { // comparing the keys if they are same or not
                return idx;
            }
            idx++;
        }

        return -1;// if we reached here means we haven't found key so return -1
    }

    private int hashfn(K key) {
        int hc = key.hashCode();
        int abshc = Math.abs(hc);// abshc = absolute hash code , in case hashCode() returns -ve code so we are
        // just converting it to positive

        return abshc % buckets.length; // doing modulus because we need a number from 0 to bucket.length - 1 only cause
        // it will act as a index .
    }

    public boolean containsKey(K key) {
        int bi = hashfn(key);
        int di = getIndexWithinBucket(key, bi);
        if (di != -1) {
            return true;
        } else {
            return false;
        }
    }

    public V get(K key) { // returns the value of key if its present otherwise it returns null
        int bi = hashfn(key);
        int di = getIndexWithinBucket(key, bi);

        if (di != -1) {
            return buckets[bi].get(di).value;
        } else {
            return null;
        }
    }

    public V remove(K key) {// it removes the node and returns it value if its present otherwise returns
        // null
        int bi = hashfn(key);
        int di = getIndexWithinBucket(key, bi);
        if (di != -1) {
            HMNode node = buckets[bi].remove(di);// the 'remove()' function of LinkedList returns the node that is
            // deleted
            size--;
            return node.value;
        } else {
            return null;
        }
    }

    public ArrayList<K> keySet() {// returns all the Keys present in HashMap
        ArrayList<K> keys = new ArrayList<>();
        for (int i = 0; i < buckets.length; i++) {
            for (HMNode node : buckets[i]) {
                keys.add(node.key);
            }
        }
        return keys;
    }

    public int size() {
        return size;
    }
}
