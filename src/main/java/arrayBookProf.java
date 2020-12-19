import java.util.Arrays;

public class arrayBookProf
{
    public static void main(String[] args) {
        int arr[]=new int[3];
        System.out.println("array size: "+ arr.length);

        Arrays.setAll(arr, i -> i+2); // The array becomes {2, 3, 4 }

        for (int i : arr) {
            System.out.print(i+" ");
        }
        String strArr[]=new String[4];
        Arrays.fill(strArr, "abc");
        int[][] myltiDim ={
                {2,3,4},
                {6,7,8},
                {12,23,45}
        };
        System.out.println();
        for (String i : strArr) {
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println("---------------------");
        System.out.println(Arrays.toString(strArr));
        System.out.println(Arrays.deepToString(myltiDim));
        char a=(char) 65.5;
//        System.out.println(a);
        char b=65;      //char when concanitated with string will take the char value
        char c=(int)'B';
        char d= (char) (b+c);
        System.out.println((int)b);
        System.out.println((int)'1');
        System.out.println("1" +'B');
        System.out.println((int) '3');
        System.out.println(3+'b');
        System.out.println((int) c);
        System.out.println(1+b);
        System.out.println(b);
    }
}
/*

The array size can be determined using a public final field called length:
System.out.println(array.length); // Prints 0 in this case.
Note: array.length returns the actual size of the array and not the number of array elements which were assigned
a value, unlike ArrayList.size() which returns the number of array elements which were assigned a value.

 */