package pers.hywel.algorithm.hashtable;

import java.util.*;
import java.util.HashSet;

/**
 * @author hywel
 */
public class HashSetPracticalMain {
    public static void main(String[] args) {
        int n = 9/10;
        System.out.println(n);
    }

    /**
     * 判断是否存在重复元素
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        HashSet hashSet = new HashSet();
        for(int num:nums){
            if(hashSet.contains(num)){return true;}
            hashSet.add(num);
        }
        return false;
    }

    /**
     * 返回单身元素，没有则返回-1
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        HashSet hashSet = new HashSet();
        for(int num:nums){
            if(hashSet.contains(num)){
                hashSet.remove(num);
            }else {
                hashSet.add(num);
            }
        }
        if (hashSet.iterator().hasNext()){
            return (int)hashSet.iterator().next();
        }else {
            return -1;
        }
    }

    /**
     * 判断两个数组中的相同元素
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> hashSet = new HashSet<>();
        for(int num:nums1){
            hashSet.add(num);
        }

        HashSet<Integer> resultSet = new HashSet<>();
        for(int num:nums2){
            if(hashSet.contains(num)){resultSet.add(num);}
        }
        int[] result = new int[resultSet.size()];
        int i = 0;
        for(Integer j:hashSet){
            result[i] = j;
            i++;
        }
        return result;
    }

    /**
     * happy数，每次算各位上的平方和，如果最终结果为1，则是happy数。如果进入循环结果不为1，则不是
     *
     * Input: 19
     * Output: true
     * Explanation:
     * 1^2 + 9^2 = 82
     * 8^2 + 2^2 = 68
     * 6^2 + 8^2 = 100
     * 1^2 + 0^2 + 0^2 = 1
     *
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        HashSet<Integer> hashSet = new HashSet<>();
        int sum = n;
        while (true){
            int digit = sum;
            sum = 0;
            while (digit>0){
                int remainder = digit%10;
                sum += remainder*remainder;
                digit = digit/10;
            }
            if(1==sum){return true;}
            if(hashSet.contains(sum)){return false;}
            else { hashSet.add(sum); }
        }
    }
}
