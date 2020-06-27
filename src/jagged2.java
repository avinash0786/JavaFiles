import java.util.Scanner;

public class jagged2
{
    public static void main(String ...a)
    {
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

        for(int i=0;i<row;i++)
        {
            for(int j=0;j<twoD[i].length;j++)
            {
                System.out.print(twoD[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
}
//  3 3 3
//