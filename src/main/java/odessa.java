import java.util.Arrays;
import java.util.Scanner;

public class odessa {

    public static void enctypt(String str, int key){
        System.out.println(str);
        char[] arr=str.toCharArray();
        int[] val=new int[arr.length];
        if (key>25){
            Arrays.setAll(val,i->arr[i]);
            System.out.println(Arrays.toString(val));
            System.out.println("---------------");
            for (int i = 0; i < arr.length; i++) {
                if (val[i]==46)
                    continue;
                if (val[i]>64 && val[i]<91)
                    val[i]+=32;
                else
                    val[i]-=32;
            }
        }
        else {
            Arrays.setAll(val,i->arr[i]);
        }

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(val));

        for (int i = 0; i < arr.length; i++) {
            if (val[i]==46) {
                val[i] = 32;
                continue;
            }
            if (val[i]>64 && val[i]<91)
                val[i]=65+(val[i]+key-65)%26;
            else
                val[i]=97+(val[i]+key-97)%26;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i]= (char) val[i];
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(new String(arr));
    }
    public static void main(String[] args) {
        Scanner inp=new Scanner(System.in);
//        int t=inp.nextInt();
//        while (t-->0){
//            int key=inp.nextInt();
//            String str=inp.next();
//            enctypt(str,key);
//        }
        enctypt("asz.wLE",29);
    }
}
