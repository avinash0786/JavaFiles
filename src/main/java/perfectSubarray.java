import java.util.*;
import java.io.*;
public class perfectSubarray {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t=in.nextInt();
        for(int i=0;i<t;i++)
        {
            int nos=0;
            int n=in.nextInt();
            int []arr=new int[n];
            ArrayList<Integer> cur=new ArrayList<>();

            for(int j=0;j<n;j++)
                arr[j]=in.nextInt();
            createPowerSet(arr,cur,0);
            System.out.println(op);
            for(int k=0;k<op.size();k++)
            {
                int sum=0;
                ArrayList<Integer> er =op.get(k);
                for(int e:er)
                    sum+=e;
                System.out.println("sum: "+sum+" k: "+k);
                if(Math.sqrt(sum) %1==0)
                    nos++;
            }

            System.out.println("Case #"+(i+1)+": "+nos); //Case #4: 0
            op.clear();
        }
    }
    public static ArrayList<ArrayList<Integer>> op=new ArrayList<>();

    private static void createPowerSet(int [] arr,ArrayList<Integer> cur,int index)
    {
        if(index==arr.length)
        {
            op.add(cur);
            return;
        }
        ArrayList<Integer> qq=new ArrayList<>();
        qq.addAll(cur);
        qq.add(arr[index]);
        createPowerSet(arr,qq,index+1);
        createPowerSet(arr,cur,index+1);
    }
}
