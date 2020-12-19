import java.util.Scanner;

public class q1
{
    static Scanner inp= new Scanner(System.in);
    public static void main(String ...f)
    {
        System.out.println("Enter size of matrix");
        int n=inp.nextInt();
        int matrix[][]=new int [n+2][n+2];
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
                {   System.out.println("Enter value");
                    matrix[i][j]=inp.nextInt();}
        }
        for(int i=0;i<n+2;i++)
        {
            for(int j=0;j<n+2;j++)
            {System.out.print(matrix[i][j]+" ");}
            System.out.println();
        }
        boolean qnegative=false;

        /////////////////////////////////
        for(int i=0;i<n+2;i++)
        { for(int j=0;j<n+2;j++)
            { if(matrix[i][j]==1)
                {
                    if(matrix[i][j+1]==1)qnegative=true;
                    else if (matrix[i][j-1]==1)qnegative=true;
                    else if (matrix[i+1][j]==1)qnegative=true;
                    else if (matrix[i-1][j]==1)qnegative=true;
                    /////diagonal
                    else if (matrix[i-1][j-1]==1)qnegative=true;
                    else if (matrix[i-1][j+1]==1)qnegative=true;
                    else if (matrix[i+1][j-1]==1)qnegative=true;
                    else if (matrix[i+1][j+1]==1)qnegative=true;
                    else continue;
                }
            }
        }
        ///////CHECK////////
        if(qnegative==true)System.out.println("Matrix is QUEEN NEGATIVE");
        else System.out.println("Matrix is QUEEN POSITIVE");
    }
}
