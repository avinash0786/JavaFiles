package CP_training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class practice05Mar {
    public static void main(String[] args) {
//        System.out.println("Valid paran: "+isValid("()"));
//        nextGreater(new int[]{5,15,10,8,6,12,9,18});
        nextGreaterElement(new int[]{2,4},new int[]{1,2,3,4});
    }
    public static boolean matchingComb(char temp, char stkTop){
        if (temp==')' && stkTop=='(')
            return true;
        else if (temp=='}' && stkTop=='{')
            return true;
        else return temp == ']' && stkTop == '[';
    }
    public static boolean isValid(String s) {
        Stack<Character> stk=new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char tmp=s.charAt(i);
            if (tmp=='(' || tmp=='{' || tmp=='[')
                stk.push(tmp);
            else {
                if (stk.isEmpty())
                    return false;
                if (matchingComb(tmp,stk.peek()))
                    stk.pop();
                else
                    return false;
            }
        }
        return stk.isEmpty();
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] op=new int[nums1.length];
        int i=0;
        HashMap<Integer,Integer> map=nextGreater(nums2);
        System.out.println(map);
        System.out.println(Arrays.toString(nums1));
        for (int j = 0; j < nums1.length; j++) {
            op[j]=map.get(nums1[j]);
        }
        System.out.println(Arrays.toString(op));
        return op;
    }
    public static HashMap<Integer,Integer> nextGreater(int[] arr){
        int n=arr.length;
        Stack<Integer> stack=new Stack<>();
        stack.push(arr[n-1]);
        int[] nextGrt=new int[n];
        nextGrt[n-1]=-1;
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(arr[n-1],-1);
        for (int i = n-2; i >=0; i--) {
            while (!stack.isEmpty() && stack.peek()<=arr[i])
                stack.pop();
            int ngt=stack.isEmpty()?-1:stack.peek();
            nextGrt[i]=ngt;
            map.put(arr[i],ngt);
            stack.push(arr[i]);
        }
        System.out.println(Arrays.toString(nextGrt));
        return map;
    }
    public int[] nextGreaterElementsCircular(int[] nums) {
        int n=nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * n - 1; i >= 0; --i) {
            while (!stack.empty() && nums[stack.peek()] <= nums[i % n]) {
                stack.pop();
            }
            res[i % n] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(i % n);
        }
        return res;
    }
}

/*
FIXED: class 9-5
---routine---
morning
6-7 running
----Class----
5-7 sleep/misc
8-1 study/misc
1-6 sleep(Night)

 */