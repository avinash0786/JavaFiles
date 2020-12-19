import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class tcsCodeVita
{
    public static final String[] units = {
            "", "one", "two", "three", "four", "five", "six", "seven",
            "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    public static final String[] tens = {
            "",        // 0
            "",        // 1
            "twenty",  // 2
            "thirty",  // 3
            "forty",   // 4
            "fifty",   // 5
            "sixty",   // 6
            "seventy", // 7
            "eighty",  // 8
            "ninety"   // 9
    };

    public static String convert(final int n) {
        if (n == 100)
            return "hundred";
        if (n == 0)
            return "zero" ;
        if (n < 20)
            return units[n];
        if (n < 100)
            return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
        return "";
    }
    static ArrayList<Character> vov= new ArrayList<>();
    public static void init(){
        vov.add('a');vov.add('e');vov.add('i');vov.add('o');vov.add('u');
    }
    public static int vovels(String str)
    {
        int count=0;
        for (int i = 0; i <str.length() ; i++) {
            char s=str.charAt(i);
            if(vov.contains(s))
                count++;
        }
        return count;
    }
    public static int pairSum(int []arr,int sum)
    {
        Map<Integer, Integer> pairs = new HashMap<Integer, Integer>();
        int low=0;
        int count=0;
        int high=arr.length-1;
        Arrays.sort(arr);
        while(low<=high)
        {
            int curSum=arr[low]+arr[high];
            if(curSum==sum) {
                if(pairs.containsKey(arr[low])){
                    if(pairs.get(arr[low])==arr[high])
                        continue;
                }
                else if(pairs.containsKey(arr[high])){
                    if(pairs.get(arr[high])==arr[low])
                        continue;
                }
                pairs.put(arr[low], arr[high]);
                count++;
            }
            if(curSum>sum) high--;
            else low++;
        }
        return count;
    }
    public static int printSumPairs(int []input, int k){
        int count=0;
        Map<Integer, Integer> pairs = new HashMap<Integer, Integer>();
        for(int i=0;i<input.length;i++){
            if(pairs.containsKey(input[i])) {
                System.out.println(input[i] + ", " + pairs.get(input[i]));
                count++;
            }
            else
                pairs.put(k-input[i], input[i]);
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        init();

        //System.out.println(pairSum(new int[]{1,2,3,4,5,6},9));
//                for (int i = 0; i <101 ; i++) {
//            System.out.println(convert(i)+" -- "+vovels(convert(i)));
//        }
        int n=in.nextInt();
        int[] value=new int[n];
        for (int i = 0; i < n; i++) {
            value[i]=in.nextInt();
        }
//        for (int i : value) {
//            System.out.print(i+" ");
//        }
        int vovSum=0;
        int vovArray[]=new int[n];
        for (int i = 0; i < n; i++) {
            int t=vovels(convert(value[i]));
            vovArray[i]=t;
            vovSum+=t;
        }
        for (int i : vovArray) {
            System.out.print(i+" ");
        }
        System.out.println("Vov Sum: "+vovSum);

        int pairSum=printSumPairs(value,vovSum);
        if(pairSum>100)
        {
            System.out.println("greater 100");
            return;
        }
        System.out.println(convert(pairSum));



/*
5

1 2 3 4 5
 */

//        //int t = in.nextInt();
//        for (int i = 0; i <101 ; i++) {
//            System.out.println(convert(i)+" vovels: "+vovels(convert(i)));
//        }
    }
}
/* question 1
14
--AB--AB---A--

String v =in.next();
        char voter[]=v.toCharArray();
//        for (char c : voter) {
//            System.out.print(c+" ");
//        }
        int i=0;
        int A=0;
        int B=0;
        while(voter[i]=='-' && i<t)
        {
            i++;
        }
        if(i==t)
        {
            return;
        }
        int sti=i+1;
        for (int j = 0; j <i ; j++) {
            voter[j]=voter[i];
        }
        i=t-1;
        while(voter[i]=='-' && i>=0)
        {
            i--;
        }
        int endi=i;
        if(voter[i]=='B'){
            for (int j = voter.length-1; j >i ; j--) {
                voter[j]=voter[i];
            }
        }
        for (int s = sti; s <endi; s++) {
            if(voter[s]=='-')
            {
                int cur=s;
                while (voter[s]=='-'){
                    s++;
                }
//                System.out.println("Cur: "+cur+" val: "+voter[cur-1]);
//                System.out.println("End: "+s+" val: "+voter[s]);
                int num=s-cur;
                boolean conflict=!(voter[cur-1]==voter[s]);
//                System.out.println("Conf: "+conflict);
//                System.out.println("Num: "+num);
                if(conflict){
                    if(voter[cur-1]=='B'){
                        for (int j = cur; j < cur+num/2; j++) {
                            voter[j]=voter[cur-1];
                        }
                        for (int j = s-1; j >= s-num/2; j--) {
                            voter[j]=voter[s];
                        }
                    }
//                    else {
//                        for (int j = 1; j <=num ; j++) {
//                            voter[cur+j]=voter[s];
//                        }
//                    }
                }
                else {
                    for (int j = 0; j <num ; j++) {
                        voter[cur+j]=voter[cur-1];
                    }
                }
            }
        }
        for (char c : voter) {
            if(c=='A') A++;
            if(c=='B') B++;
        }
        if(A==B)
        {
            System.out.println("Coalition government");
            return;
        }
        char winner=(A>B)?'A':'B';
//        for (char c : voter) {
//            System.out.print(c+" ");
//        }
        System.out.println(winner);
 */
