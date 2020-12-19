import java.util.Scanner;

public class arrayrpogram
{
    public static void main(String ...a)
    {
        String sub[];
        int marks[];
        Scanner in=new Scanner(System.in);
        Scanner st=new Scanner(System.in);
        System.out.println("enter no of subjects: ");
        int n=in.nextInt();
        sub=new String[n];
        marks=new int[n];
        System.out.println("enter NAME of subjects: ");
        for(int i=0;i<n;i++)
            sub[i]=st.next();
        System.out.println("enter MARKS IN subjects: ");
        for(int i=0;i<n;i++)
            {
                System.out.println(sub[i]+"  marks: ");
                marks[i]=in.nextInt();
            }
        System.out.println("Percentage in "+marks.length+" subjects is "+(int)parcentage(marks)+" %");
    }

    public static double parcentage(int aa[])
    {
        int n=aa.length;
        double sum=0;
        double total=n*100;
        double percent=0;
        for(int i:aa)
            sum+=i;
        percent=(sum/total)*100;
        return percent;
    }
}
