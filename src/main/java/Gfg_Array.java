import java.util.Scanner;

public class Gfg_Array
{
    public static boolean perfectSq(long number)
    {
        long root=(long)Math.sqrt(number);
        return root*root==number;
    }
    public static void main(String ...a)
    {
        Scanner inp=new Scanner(System.in);
        int t= inp.nextInt();
















//        while(t-->0)
//        {
//            int n= inp.nextInt();
//            int[] val=new int[n];
//            int[] avl=new int[n];
//            int mis=0;
//            int rep=0;
//            for(int i=0;i<n;i++)
//            {
//                val[i]= inp.nextInt();
//                if(avl[val[i]-1]==-1)
//                    rep=val[i];
//                else
//                    avl[val[i]-1]=-1;
//            }
//            for(int i=0;i<n;i++)
//            {
//                if(avl[i]==0)
//                {
//                    mis=i+1;
//                    break;
//                }
//            }
//            System.out.println(rep+" "+mis);
//        }








//        for(int i=0;i<t;i++)
//        {
//            long n= inp.nextLong();
//            long left;
//            long right;
//            if(perfectSq(n))
//            {
//                left=(long)Math.sqrt(n)-1;
//                right=(long)Math.sqrt(n)+1;
//            }
//            else
//            {
//                left=(long)Math.floor(Math.sqrt(n));
//                right=(long)Math.ceil(Math.sqrt(n));
//            }
//
//            if((long)Math.abs(left*left-n)<(long)Math.abs(right*right-n))
//                System.out.println(left*left);
//            else
//                System.out.println(right*right);
//        }
    }
}
