package pers.hywel.algorithm.hashtable;

import java.util.*;
import java.util.HashMap;
import java.util.HashSet;

/**
 * HashMap基本实践
 * @author hywel
 *
 * 解决问题：
 * 1. 给定一个target,求数组里相加等于这个target的下标 (twoSum)
 * 2. 判断两个字符串是否拥有相同结构 (isIsomorphicWithouMap , isIsomorphicUseMap)
 * 3. 给定两个数组，求两个数组中相同元素的最小下标和 (findRestaurant)
 * 4. 求一个字符串中第一个不重复的字符下标 (firstUniqChar , firstUniqChar2)
 * 5. 取两个数组中的相同元素集 (intersect)
 * 6. 是否存在一组重复元素，该组元素的下标差小于给定的k值 (containsNearbyDuplicateWithHashMap, containsNearbyDuplicate)
 *
 */
public class HashMapPracticalMain {
    public static void main(String[] args) {
        String s = "我爱我";
        String t = "你是你";
//        System.out.println(isIsomorphic1(s,t));
    }



    /**
     * 给定一个target，求map里边相加等于这个target的两个元素的下标
     *
     * 例如给定target: 9，数组: [2, 7, 11, 15]
     * 则返回2和7的下标[0,1]
     *
     * solution：
     * 依此将target-nums[i]到map里边查找
     * 有则返回结果，没有则将nums[i]添加到map
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        int[] result = new int[]{-1,-1};
        for(int i=0;i<nums.length;i++){
            if(hashMap.containsKey(target-nums[i])){
                result[0] = hashMap.get(target-nums[i]);
                result[1] = i;
            } else {
                hashMap.put(nums[i],i);
            }
        }
        return result;
    }

    /**
     * 判断两个字符串是否同构
     *
     * 例如：
     * Input: s = "egg", t = "add"
     * Output: true
     *
     * Input: s = "foo", t = "bar"
     * Output: false
     *
     * Input: s = "paper", t = "title"
     * Output: true
     *
     * solution:
     *方案一： 不使用Map解决方案（不允许出现中文字符比对）
     *方案二：依此将字母添加到map，先从map里查找，有则比对value是否等于对应的char2，没有则添加
     * @param s
     * @param t
     * @return
     */
    public static boolean isIsomorphicWithouMap(String s, String t) {
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        int n = s.length();
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        for (int i = 0; i < n; ++i) {
            if (m1[sChar[i]] != m2[tChar[i]]) {return false;}
            m1[sChar[i]] = i + 1;
            m2[tChar[i]] = i + 1;
        }
        return true;
    }

    /**
     * 使用Map解决
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphicUseMap(String s, String t) {
        if(s.length()!=t.length()){return false;}
        if(s.length()<=1){return true;}
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        HashMap<Character,Character> hashMap = new HashMap<>(s.length()>36?36:s.length());
        for(int i=0;i<sChar.length;i++){
            //如果第一个字符串出现重复字符，判断第二个字符串对应位置是否也重复
            if(hashMap.containsKey(sChar[i])){
                if(hashMap.get(sChar[i])!=tChar[i]){return false; }
            }else {
                //如果第二个字符串出现重复，则不相同
                if(hashMap.containsValue(tChar[i])){return false;}
                hashMap.putIfAbsent(sChar[i],tChar[i]);
            }
        }
        return true;
    }


    /**
     * Minimum Index Sum of Two Lists
     *
     * 求两个给定数组中，相同元素的最小下标和
     * @param list1
     * @param list2
     * @return
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new LinkedList<>();
        int minSum = Integer.MAX_VALUE;
        for (int i=0;i<list1.length;i++) {map.put(list1[i], i);}
        for (int i=0;i<list2.length;i++) {
            Integer j = map.get(list2[i]);
            if (j != null && i + j <= minSum) {
                if (i + j < minSum) { res.clear(); minSum = i+j; }
                res.add(list2[i]);
            }
        }
        return res.toArray(new String[res.size()]);
    }



    /**
     * 第一个不重复的字符
     *
     * 给定一个字符串，查找第一个不重复的字符下标
     * @param s
     * @return
     *
     * //自己解法，利用HashMap存<字符,下标>,相同字符将下标置为-1
     */
    public int firstUniqChar(String s) {
        if(null==s||"".equals(s)){return -1;}

        HashMap<Character,Integer> hashMap = new HashMap<>();
        char[] array = s.toCharArray();
        for(int i = 0;i<array.length;i++){
            if(hashMap.containsKey(array[i])){
                hashMap.put(array[i],-1);
            }
            else {
                hashMap.put(array[i],i);
            }
        }

        int min = -1;
        for(int i: hashMap.values()){
            if(i!=-1){
                if(min == -1 || i<min){min = i;}
            }
        }
        return min;
    }
    /**
     * 寻找字符串中第一个不重复元素
     * Leetcode大神解法
     */
    public int firstUniqChar2(String s) {
        int[] letterFre = new int[256];
        for(int i=0;i<s.length();i++){
            letterFre[s.charAt(i)-'a']++;
        }
        for(int i=0;i<s.length();i++){
            if(letterFre[s.charAt(i)-'a']==1){return i;}
        }
        return -1;
    }


    /**
     * 求两个数组的交叉元素
     *
     * Follow up:
     * 1. 如果所给数组均是排好序的，怎么优化？
     * 2. 如果数组1相对数组2很小，哪种算法会更好？
     * 3. 如果数组nums2存储在磁盘，内存有限，不能一次性将全部数组加载到内存怎么办?
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for(int i=0;i<nums1.length;i++){
            if(hashMap.containsKey(nums1[i])){
                int fre = hashMap.get(nums1[i]);
                hashMap.put(nums1[i],fre+1);
            }else {
                hashMap.put(nums1[i],1);
            }
        }

        List<Integer> result = new LinkedList<>();
        for(int i=0;i<nums2.length;i++){
            if(hashMap.containsKey(nums2[i])){
                int fre = hashMap.get(nums2[i]);
                result.add(nums2[i]);
                if(fre==1){
                    hashMap.remove(nums2[i]);
                }else {
                    hashMap.put(nums2[i],fre-1);
                }
            }
        }


        int[] res = new int[result.size()];
        int i = 0;
        for(Integer num:result){
            res[i] = num;
            i++;
        }
        return res;
    }

    /**
     * 是否存在一组重复元素，该组元素的下标差小于给定的k值
     *
     * @param nums
     * @param k
     * @return
     *
     * 自己方法实现：利用HashMap
     */
    public boolean containsNearbyDuplicateWithHashMap(int[] nums, int k) {
        if(null==nums||nums.length<=1) {return false;}
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        HashMap<Integer,Integer> res = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(hashMap.containsKey(nums[i])){
                int gap = i - hashMap.get(nums[i]);
                if(!res.containsKey(nums[i])) {res.put(nums[i],gap);}
                else { if(res.get(nums[i])>gap){res.put(nums[i],gap);}}
            }
            hashMap.put(nums[i],i);
        }
        if(res.isEmpty()){return false;}
        for(int i:res.values()){
            if(i>k){return false;}
        }
        return true;
    }
    /**
     * 是否存在一组重复元素，该组元素的下标差小于给定的k值
     *
     * @param nums
     * @param k
     * @return
     *
     * LeetCode大神实现：利用HashSet维持一个window
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            if(i>k){set.remove(nums[i-k-1]);}
            if(!set.add(nums[i])) {return true;}
        }
        return false;
    }
}
