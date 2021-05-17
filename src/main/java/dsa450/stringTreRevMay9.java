package dsa450;

import java.util.*;

public class stringTreRevMay9 {
    public static void main(String[] args) {

//        System.out.println("Longest Valid paran: "+longestValidParentheses(")(((()))"));
    }
    public static int longestValidParentheses(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        System.out.println(Arrays.toString(s.toCharArray()));
        for (int i = 1; i < s.length(); i++) {
            //only calculate when we encounter a closing bracket, bcoz they will count to the valid parans
            if (s.charAt(i) == ')') {
                System.out.println("index:::::: "+i);
                //there are 2 cases first is simple () so we just need to check this config and add 2 the prev ans
                if (s.charAt(i - 1) == '(') {
                    System.out.println("match 1");
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }
                // second conf is the (()())
                //the centre are already counted and valid but for last ) we need to find its matching (
                //and that is at  i- dp[i-dp[i-1]-1  index,
                // so go and add the answer
                else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    System.out.println("match 2");
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                System.out.println(Arrays.toString(dp));
                System.out.println();
                maxans = Math.max(maxans, dp[i]);
            }
        }
        System.out.println(Arrays.toString(dp));
        return maxans;
    }
    public static List<Integer> findSubstring(String str, String[] words) {
        if (str==null || str.length()==0 || words==null || words.length==0)
            return new ArrayList<>();
        Map<String,Integer> map=new HashMap<>();
        for (String word : words) {
            map.put(word,map.getOrDefault(word,0)+1);
        }
        int len=words[0].length();
        int totalWords=words.length;
        List<Integer> op=new ArrayList<>();

        for (int i = 0; i < str.length()-totalWords*len; i++) {
            Map<String,Integer> seen=new HashMap<>(map);
            for (int j = 0; j < totalWords; j++) {
                int index=i+j*len;
                String word=str.substring(index,index+len);
                if (seen.containsKey(word)) {
                    int count=seen.get(word);
                    if (count==1)
                        seen.remove(word);
                    else
                        seen.put(word,count-1);
                    if (seen.isEmpty()){
                        op.add(i);
                        break;
                    }
                }
                else break;
            }
        }
        return op;
    }
}
