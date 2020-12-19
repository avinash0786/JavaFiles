import java.util.Scanner;
public class arrrayclass
{
    public static void main(String ...a)
    {
        Scanner in=new Scanner(System.in);
        int marks[];
        marks=new int[5];
        for(int i=0;i<marks.length;i++)
            marks[i]=in.nextInt();
        int sum=0;
        int max=marks[0];
        int min=marks[0];
        for(int i:marks)
            {
                sum+=i;
                if(i>max)
                    max=i;
                if(i<min)
                    min=i;

            }
        System.out.println("  ");
        System.out.println("Sum= "+sum);
        System.out.println("MAx= "+max);
        System.out.println("Min= "+min);

    }
}
/*
* marks=new int[3];
        marks[0]=10;
        marks[1]=30;
        marks[2]=50;
        for(int i:marks)
            System.out.println(i);
* */