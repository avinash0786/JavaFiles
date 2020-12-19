import java.util.Scanner;

public class jagged_sum
{
    public static void main(String ...XX)
    {
        System.out.println("Array 1");
        Scanner in =new Scanner(System.in);
        System.out.println("ENter size of ROW ");
        int row=in.nextInt();
        int col;
        int twoD[][]=new int[row][];
        for(int i=0;i<row;i++)
        {
            System.out.println("ENter size of COL of ROW:  "+(i+1));
            col=in.nextInt();
            twoD[i]=new int[col];
            for(int j=0;j<col;j++)
            {
                System.out.println("ENter value for i= "+i+" j= "+j);
                twoD[i][j]=in.nextInt();
            }
        }
        System.out.println("Array 1");
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<twoD[i].length;j++)
            {
                System.out.print(twoD[i][j]+" ");
            }
            System.out.println(" ");
        }
        ////////////////////////////////////////////////////
        System.out.println("Array 2");
        System.out.println("ENter size of ROW ");
        int row2=in.nextInt();
        int col2;
        int twoD2[][]=new int[row2][];
        for(int i=0;i<row2;i++)
        {
            System.out.println("ENter size of COL of ROW:  "+(i+1));
            col2=in.nextInt();
            twoD2[i]=new int[col2];
            for(int j=0;j<col2;j++)
            {
                System.out.println("Enter value for i= "+i+" j= "+j);
                twoD2[i][j]=in.nextInt();
            }
        }
        System.out.println("Array 2");
        for(int i=0;i<row2;i++)
        {
            for(int j=0;j<twoD2[i].length;j++)
            {
                System.out.print(twoD2[i][j]+" ");
            }
            System.out.println(" ");
        }
        System.out.println("------------");
        ////////////// ADDITION OF JAGGED ARRAY/////////////
        ////////////CREATING ARRAY ////////////////////
        int frow=max(row,row2);
        int farray[][]=new int[frow][];
        for(int i=0;i<frow;i++)
        {
            int temp=max(twoD2[i].length,twoD[i].length);
            farray[i]=new int[temp];                           ///////////////        OK
        }
        System.out.println("Array sum");
        ////////////CALCULATING SUM////////////////////
        for(int i=0;i<row2;i++)
        {
            for(int j=0;j<twoD2[i].length;j++)
            {
                farray[i][j]+=twoD2[i][j];
            }
        }
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<twoD[i].length;j++)
            {
                farray[i][j]+=twoD[i][j];
            }
        }

        for(int i=0;i<frow;i++)
        {
            for(int j=0;j<farray[i].length;j++)
            {
                System.out.print(farray[i][j]+" ");
            }
            System.out.println(" ");
        }
    }

    public static int max(int a,int b)
    {
        if(a>b)return a;
        else return b;
    }
}
