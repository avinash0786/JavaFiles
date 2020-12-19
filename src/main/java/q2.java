import java.util.Scanner;
public class q2
{
    static Scanner inp= new Scanner(System.in);
    public static void main(String ...ff)
    {
        System.out.println("ENter no of students: ");
        int n=inp.nextInt();
        int array[]=new int[n];
        for(int i=0;i<array.length;i++)
            array[i]=inp.nextInt();
        int check[]=new int[n];
        int c=0;

        for(int i:array)
            System.out.print(i+" ");

        for(int i=0;i<array.length-1;i++)
        {
            if(array[i+1]>array[i])
            {
                check[c]=i;
                for(int j=i;j<array.length-1;j++)
                {
                    if(array[j+1]>array[j])
                        continue;
                    else
                    {
                        ++c;
                        check[c]=j;
                        System.out.println("j : "+j);
                        i=j;
                    }
                }
            }
            else
            {
                ++c;
                check[c]=i;
                for(int j=i;j<array.length-1;j++)
                {
                    if(array[j+1]<array[j])
                        continue;
                    else
                    {
                        ++c;
                        check[c]=j;
                        System.out.println("c here: "+c);
                        i=j;
                    }
                }
            }
        }


        System.out.println("chec: ");
        for(int i:check)
            System.out.print(i+" ");


    }
}
