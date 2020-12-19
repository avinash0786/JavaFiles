package GEEKS_FOR_GEEKS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class searchingContest {
    public static void main(String ...a)
    {
        Scanner inp=new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t=inp.nextInt();
        while(t-->0)
        {
            int n=inp.nextInt();
            int []arr=new int[n];
            for(int i=1;i<n;i++)
                arr[i]=inp.nextInt();
            int inc=arr[0];
            int exc=0;
            int excNew;
            for(int i=0;i<n;i++)
            {
                excNew=Math.max(inc,exc);
                inc=exc+arr[i];
                exc=excNew;
            }
            System.out.println(Math.max(inc,exc));
        }
    }
}
/*
// first question matrix
for(int i=0;i<t;i++)
        {
            int n=inp.nextInt();
            int m=inp.nextInt();
            int [][] matrix=new int[n][m];
            int []res=new int[n];
            String ones="";
            String zeros="";
            for(int j=0;j<m;j++)
            {
                ones=ones.concat("1 ");
                zeros=zeros.concat("0 ");
            }
            for(int j=0;j<n;j++)
            {
                for(int k=0;k<m;k++) {
                    matrix[j][k] = inp.nextInt();
                    res[j]+=matrix[j][k];
                }
            }
            for(int s=0;s<res.length;s++)
            {
                if(res[s]>0)
                    System.out.println(ones);
                else
                    System.out.println(zeros);
            }
        }

// Second question
while(t-->0)
        {
            int n=inp.nextInt();
            int count=1+(int)(Math.log(n)/Math.log(2));
            if(count%2==0)
            {
                n=n^(1<<(count/2));
                n=n^(1<<(count/2)-1);
                System.out.println(n);
            }
            else {
                n=n^(1<<(count/2));
                System.out.println(n);
            }
        }

 */