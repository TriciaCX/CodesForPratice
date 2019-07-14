package interviewTop100;

import java.util.HashMap;
import java.util.Map;

public class leetcode1
{
	/**
	 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
示例:
给定 nums = [2, 7, 11, 15], target = 9
因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
	 * @return
	 */
	public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0;i<nums.length;i++) {
        	map.put(nums[i],i);
        }
        for(int i=0;i<nums.length;i++) {
            int cha = target-nums[i];
            if(map.containsKey(cha) && map.get(cha)!=i) {
            	res[1] = i;
            	res[0] = map.get(cha);
            }
        }
        return res;
	}
	
	//改进hash，先查再放
	 public int[] twoSumII(int[] nums, int target) {
	        Map<Integer, Integer> map = new HashMap<>();
	        for(int i = 0; i< nums.length; i++) {
	            if(map.containsKey(target - nums[i])) {
	                return new int[] {map.get(target-nums[i]),i};
	            }
	            map.put(nums[i], i);
	        }
	        throw new IllegalArgumentException("No two sum solution");
	    }

	
}
