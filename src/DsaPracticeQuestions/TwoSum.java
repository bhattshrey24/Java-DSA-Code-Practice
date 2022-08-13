package DsaPracticeQuestions;

import java.util.HashMap;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int ans[]=new int[2];
        HashMap hm = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            int valToBeFoun= target-nums[i];
            if(hm.containsKey(valToBeFoun)){
                ans[0] = i;
                ans[1] = (int)hm.get(valToBeFoun);
                break;
            }else{
                hm.put(nums[i],i);
            }
        }
        return ans;
    }
}
