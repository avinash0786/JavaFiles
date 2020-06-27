import java.util.*;
import java.io.*;
public class allocation {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // no of testcases
        int [] result=new int[t];  //storing result
        for (int i=0;i<t;i++)
        {   //System.out.println();
            //System.out.println("run: "+i);
            ArrayList<Integer> list=new ArrayList<>();
            int nHouse=in.nextInt();
            int budget=in.nextInt();
            //System.out.println("budget: "+budget);
            int count=0;
            for(int j=0;j<nHouse;j++)
                list.add(in.nextInt());
            Collections.sort(list);
            //System.out.println(list);
            int spend=0;
            int k=0;
            while(spend<=budget)
            {
                spend+=list.get(k);
                if(spend==budget)
                {
                    count++;break;
                }
                if(spend>budget)
                    break;
                //System.out.println(spend);
                k++;
                count++;
            }
            result[i]=count;
            list.clear();
            //System.out.println("---------------------");
        }
        for(int i=0;i<t;i++)
            System.out.println("Case #"+(i+1)+": "+result[i]);//Case #1: 2
        in.close();
    }
}