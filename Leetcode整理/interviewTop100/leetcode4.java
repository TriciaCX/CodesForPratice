package interviewTop100;

import java.util.Arrays;

public class leetcode4
{
/**
 * 寻找两个有序数组中的中位数
 * 
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

示例 1:

nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0
示例 2:

nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5

 */
	//简单排序，不符合复杂度要求
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int len1 = nums1.length;
		int len2 = nums2.length;
        int[] nums = new int[len1+len2];
        for(int i=0;i<len1;i++) {
        	nums[i] = nums1[i];
        }
        for(int j=len1;j<len1+len2;j++) {
        	nums[j] = nums2[j-len1];
        }
        Arrays.sort(nums);
        int len = len1+len2;
        if(len%2==0) {
        	return (double)(nums[len/2-1]+nums[len/2])/2;
        }else {
        	return (double)nums[len/2];
        }
	}
	
	/**
	 * 时间复杂度是 O(log(m+n))
	 * 两个有序数组求中位数，问题一般化为，求两个有序数组的第k个数，当k = (m+n)/2时为原问题的解。
	 * 怎么求第k个数？分别求出第一个和第二个数组的第 k / 2个数 a 和 b，然后比较 a 和 b，当a < b ，说明第 k 个数位于 a数组的第 k / 2个数后半段，或者b数组的 第 k / 2 个数前半段，问题规模缩小了一半，然后递归处理就行。
	 */
    public double findMedianSortedArraysII(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        //处理任何一个nums为空数组的情况
        if (m == 0) {
            if (n % 2 != 0)
                return 1.0 * nums2[n / 2];
            return (nums2[n / 2] + nums2[n / 2 - 1]) / 2.0;
        }
        if (n == 0) {
            if (m % 2 != 0)
                return 1.0 * nums1[m / 2];
            return (nums1[m / 2] + nums1[m / 2 - 1]) / 2.0;
        }
        int total = m + n;
        //总数为奇数，找第 total / 2 + 1 个数
        if ((total & 1) == 1) {
            return find_kth(nums1, 0, nums2, 0, total / 2 + 1);
        }
        //总数为偶数，找第 total / 2 个数和第total / 2 + 1个数的平均值
        return (find_kth(nums1, 0, nums2, 0, total / 2) + find_kth(nums1, 0, nums2, 0, total / 2 + 1)) / 2.0;

    }

    //寻找a和 b数组中，第k个数字
    double find_kth(int[] a, int a_begin, int[] b, int b_begin, int k) {
        //当a 或 b 超过数组长度，则第k个数为另外一个数组第k个数
        if (a_begin >= a.length)
            return b[b_begin + k - 1];
        if (b_begin >= b.length)
            return a[a_begin + k - 1];
        //k为1时，两数组最小的那个为第一个数
        if (k == 1)
            return Math.min(a[a_begin], b[b_begin]);

        int mid_a = Integer.MAX_VALUE;
        int mid_b = Integer.MAX_VALUE;
        //mid_a / mid_b 分别表示 a数组、b数组中第 k / 2 个数
        if (a_begin + k / 2 - 1 < a.length)
            mid_a = a[a_begin + k / 2 - 1];
        if (b_begin + k / 2 - 1 < b.length)
            mid_b = b[b_begin + k / 2 - 1];
        
        //如果a数组的第 k / 2 个数小于b数组的第 k / 2 个数，表示总的第 k 个数位于 a的第k / 2个数的后半段，或者是b的第 k / 2个数的前半段
        //由于范围缩小了 k / 2 个数，此时总的第 k 个数实际上等于新的范围内的第 k - k / 2个数，依次递归
        if (mid_a < mid_b)
            return find_kth(a, a_begin + k / 2, b, b_begin, k - k / 2);
        //否则相反
        return find_kth(a, a_begin, b, b_begin + k / 2, k - k / 2);
    }
    
    
}
