package HashMap;

import HashMap.HashMapImplementation;

public class HMClient {
    public static void main(String[] args) {
        HashMapImplementation<String, Integer> hm = new HashMapImplementation<>();
        System.out.println("Contains Red : " + hm.containsKey("Red"));
        hm.put("Violet", 50);
        hm.put("Purple", 20);
        hm.put("Brown", 85);
        System.out.println("Contains Purple : " + hm.containsKey("Purple"));
    }
}
