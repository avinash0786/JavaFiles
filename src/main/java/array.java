public class array
{
    static public void main(String [] args) {
        int[] array = {1, 3, 5, 8, 9};     //preffered way of declaring array
        int array2[] = {1, 2, 3, 4, 5, 5};
        //System.out.println(array[3]);
        //FOR LOOPS

        for (int i = 0; i < 5; i++)
        {
            System.out.print(array[i]+ " ");//change is permanent
        }
        //iterate over elements in array , transfer elements in element variable
        for (int element: array)
        {
            System.out.println(element); //change is temporary
        }
    }
}
/*
 *         int[] array = {1, 3, 5, 8, 9};     //preffered way of declaring array
 * declare
 * int [] arr;    or
 * int arr[];
 *
 * instanciation
 * arr=new[5];
 * -------------------------------
 * int [] arr=new int [5];
 * arr= new int[]{1, 2, 3, 4, 5};
 *
 * ----------------------
 * int a[]=new int[5];
 */
