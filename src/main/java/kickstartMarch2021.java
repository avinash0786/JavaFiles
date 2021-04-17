import java.util.*;
import java.io.*;
public class kickstartMarch2021 {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int row=in.nextInt();
            int col=in.nextInt();
            int [][] matrix =new int[row][col];
            
            //INIT MATRIX
            for (int j = 0; j < row; j++)
                for (int k = 0; k < col; k++) 
                    matrix[j][k]= in.nextInt();
            
            int n = rabbitBox(matrix);
            System.out.println("Case #" + i + ": " +n);
        }
    }

    public static void printMat(int[][] grid){
        for (int[] ints : grid) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("-------");
    }
    public static int rabbitBox(int[][] grid){
        int box=0;
        int row=grid.length;
        int col=grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int a=i,b=j;
                System.out.println("i: "+i+" j: "+j);
                printMat(grid);
                //EAST
                while (b<col-1){
                    int cur=grid[i][b];
                    int adj=grid[i][b+1];
                    if (Math.abs(cur-adj)>1){
                        if (adj>cur){
                            box+=(adj-1-cur);
                            grid[i][b]+=(adj-1-cur);
                        }
                        else {
                            box+=(cur-1-adj);
                            grid[i][b+1]+=(cur-1-adj);
                        }
                    }
                    b++;
                }

                //WEST
                a=i;
                b=j;
                while (b>0){
                    int cur=grid[i][b];
                    int adj=grid[i][b-1];
                    if (Math.abs(cur-adj)>1){
                        if (adj>cur){
                            box+=(adj-1-cur);
                            grid[i][b]+=(adj-1-cur);
                        }
                        else {
                            box+=(cur-1-adj);
                            grid[i][b-1]+=(cur-1-adj);
                        }
                    }
                    b--;
                }

                //NORTH
                a=i;
                b=j;
                while (a>0){
                    int cur=grid[a][j];
                    int adj=grid[a-1][j];
                    if (Math.abs(cur-adj)>1){
                        if (adj>cur){
                            box+=(adj-1-cur);
                            grid[i][j]+=(adj-1-cur);
                        }
                        else {
                            box+=(cur-1-adj);
                            grid[a-1][j]+=(cur-1-adj);
                        }
                    }
                    a--;
                }

                //SOUTH
                a=i;
                b=j;
                while (a<row-1){
//                    System.out.println("South");
                    int cur=grid[a][j];
                    int adj=grid[a+1][j];
                    if (Math.abs(cur-adj)>1){
                        if (adj>cur){
                            box+=(adj-1-cur);
                            grid[a][j]+=(adj-1-cur);
                        }
                        else {
                            box+=(cur-1-adj);
                            grid[a+1][j]+=(cur-1-adj);
                        }
                    }
                    a++;
                }
            }
        }
        return box;
    }


    //      1-->TRUE  0--> FALSE
    public static int lShaped(boolean[][] matrix){
        int row=matrix.length;
        int col=matrix[0].length;
        int res=0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

            }
        }
        return res;
    }
    

    public static int kGoodNess(String str, int k){
        char[] arr=str.toCharArray();
        int N=arr.length-1;
        int godScore=0;
        int eq=0;
        for (int i = 0; i <=N/2; i++) {
            if(arr[i]!=arr[N-i])
                godScore++;
            else
                eq++;
            System.out.println((i+1)+"  "+(N-i+1)+"  "+(arr[i]==arr[N-i]));
        }
        System.out.println("God sc: "+godScore+" k: "+k);
        if (godScore==k)
            return 0;
        else
            return Math.abs(k-godScore);
    }
}
