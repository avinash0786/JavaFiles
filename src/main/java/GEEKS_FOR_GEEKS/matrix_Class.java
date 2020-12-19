package GEEKS_FOR_GEEKS;

import java.util.ArrayList;

public class matrix_Class {
    public static void snakePattern(int arr[][]){   //printing matrix in snake pattern
        for (int i = 0; i < arr.length; i++) {
            if(i%2==0){
                for (int j = 0; j < arr[i].length; j++) {
                    System.out.print(arr[i][j]+" ");
                }
            }
            else {
                for (int j = arr[i].length-1; j >=0; j--) {
                    System.out.print(arr[i][j]+" ");
                }
            }
            System.out.println();
        }
    }
    public static void transposeMatrix(int[][] arr){    //traversing only the upper triangle
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr[i].length; j++) {
                swapMatix(arr,i,j);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void swapMatix(int [][] mat,int i,int j){
        System.out.println("Swap i: "+i+" j: "+j);
        int temp=mat[i][j];
        mat[i][j]=mat[j][i];
        mat[j][i]=temp;
    }
    public static void spiralPrint(int[][] arr){
        int c=arr[0].length;
        int r=arr.length;
        System.out.println("Row: "+r+" col: "+c);
        int top=0,left=0,bottom=r-1,right=c-1;

        while(top<=bottom && left<=right){
            for(int i =left; i<=right;i++){
                System.out.print(arr[top][i]+" ");
            }
            top++;
            for (int i = top; i <=bottom; i++) {
                System.out.print(arr[i][right]+" ");
            }
            right--;
            if(top<=bottom){
                for (int i = right; i >=left; i--) {
                    System.out.print(arr[bottom][i]+" ");
                }
                bottom--;
            }
            if (left<=right){
                for (int i = bottom; i >=top; i--) {
                    System.out.print(arr[i][left]+" ");
                }
                left++;
            }
        }
    }
    public static void search(int[][]arr,int key){
        int r=arr.length;
        int c=arr[0].length;
        int i=0;
        int j=c-1;
        while(i<r && j>=0){
            if(arr[i][j]==key){
                System.out.println(key+" at i: "+i+", j: "+j);
                return;
            }
            else if(arr[i][j]>key)
                j--;
            else
                i++;
        }
        System.out.println(key+" not found !");
    }
    static int[][] sumMatrix(int A[][], int B[][])
    {
        int [][] fin=new int[A.length][A[0].length];
        System.out.println(A.length+" --- "+A[0].length);
        for (int i = 0; i < A.length; i++) {
            System.out.println("i: "+i);
            for (int j = 0; j < A[0].length; j++) {
                fin[i][j]=A[i][j]+B[i][j];
                System.out.println("j: "+j);
                System.out.println(fin[i][j]);
            }
        }
        return fin;
    }
    static ArrayList<Integer> sumTriangles(int matrix[][], int n)
    {
        ArrayList<Integer> op=new ArrayList<>();
        int lSum=0;
        int rSum=0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                lSum+=matrix[i][j];
                rSum+=matrix[j][i];
//                System.out.println(matrix[i][j]+" "+matrix[j][i]+" rsum: "+rSum+" lsum: "+lSum);
            }
        }
        op.add(lSum);
        op.add(rSum);
        return op;
    }
    static int[][] multiplyMatrix(int A[][], int B[][])
    {
        int [][] mul=new int[A.length][B[0].length];
        if(A[0].length!=B.length)
            return mul;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                for (int k = 0; k < B[0].length; k++) {
                    mul[i][k]+=(A[i][j]*B[j][k]);
                }
            }
        }
        return mul;
    }
    static int determinantOfMatrix(int matrix[][], int n)
    {
        int det=0;
        return det;
    }
    static ArrayList<Integer> boundaryTraversal(int matrix[][], int n, int m)
    {
        ArrayList<Integer> op=new ArrayList<>();
        if(n==1){
            for (int i = 0; i < m; i++) {
                op.add(matrix[0][i]);
            }
            return op;
        }
        if(m==1){
            for (int i = 0; i < n; i++) {
                op.add(matrix[i][0]);
            }
            return op;
        }
        for (int i = 0; i < matrix[0].length; i++) {
            op.add(matrix[0][i]);
        }
        for (int i = 1; i < matrix.length; i++) {
            op.add(matrix[i][matrix[0].length-1]);
        }
        for (int i = matrix[0].length-2; i >=0; i--) {
            op.add(matrix[matrix.length-1][i]);
        }
        for (int i = matrix.length-2; i >0; i--) {
            op.add(matrix[i][0]);
        }
        return op;
    }
    static void exchangeColumns(int matrix[][])
    {
        if(matrix[0].length<=1)
            return;
        for (int i = 0; i < matrix.length; i++) {
            int temp=matrix[i][matrix[0].length-1];
            matrix[i][matrix[0].length-1]=matrix[i][0];
            matrix[i][0]=temp;
        }
    }
    static ArrayList<Integer> spirallyTraverse(int matrix[][], int r, int c)
    {
        ArrayList<Integer> op=new ArrayList<>();

        int top=0,right=matrix[0].length-1;
        int left=0, bottom=matrix[0].length-1;

        while ( top<=bottom && left<=right ){
            //upper traverse
            for (int i = left; i < right; i++) {
                op.add(matrix[left][i]);
            }
            top++;
            for (int i = top; i < bottom; i++) {
                op.add(matrix[right][i]);
            }
            right--;
            if(top<=bottom){
                for (int i = right; i >=left; i--) {
                    op.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if (left<=right){
                for (int i = bottom; i >=top; i--) {
                    op.add(matrix[left][i]);
                }
                left--;
            }
        }
        return op;
    }
    static boolean search(int matrix[][], int n, int m, int x)
    {
        int i=0;
        int j=m-1;
        while(i<n && j>=0){
            if(matrix[i][j]==x)
                return true;
            else if(matrix[i][j]<x)
                j--;
            else
                i++;
        }
        return false;
    }
    void booleanMatrix(int matrix[][])
    {
        int[]row=new int[matrix.length];
        int[] col=new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j]==1)
                {
                    row[i]=1;
                    col[j]=1;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(row[i]==1 || col[j]==1)
                    matrix[i][j]=1;
            }
        }
    }
    public static void main(String[] args) {

//        System.out.println(boundaryTraversal(new int[][]{{1, 2, 3}, {4, 5, 6},{1, 2, 3}},3,3));
//        snakePattern(new int[][]{{1,2,3},{4,5,6},{7,8,9},{12,23,34,45,65}});
//        transposeMatrix(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
//        spiralPrint(new int[][]{{1,2,3},{4,5,6},{7,8,9},{10,11,12}});
//        search(new int[][]{{1,2,3},{4,5,6},{7,8,9},{10,11,12}},12);
//        sumMatrix(new int[][]{{1, 2, 3}, {4, 5, 6}},new int[][]{{1, 3, 3}, {2, 3, 3}});
//        sumTriangles(new int[][]{{1, 2, 3}, {4, 5, 6},{1, 2, 3}},3);
    }
}
