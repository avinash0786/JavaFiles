package CP_training;

import java.util.Arrays;
import java.util.Stack;

//LInked list && stack
public class feb17 {
    public static void main(String[] args) {
        nxtGrtBruteForce(new int[]{11,8,3,6,10,2,5,1,7,9,3,12});
        System.out.println("Optimized usign Stack");
        nextGreater(new int[]{11,8,3,6,10,2,5,1,7,9,3,12});
    }
    public static void nxtGrtBruteForce(int[] arr){ //  O(n^2)
        int[] grt=new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i-1; j>=0 ; j--) {
                if (grt[j]!=0)
                    continue;
                if (arr[i]>arr[j])
                    grt[j]=arr[i];
                else
                    break;
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(grt));
    }
    //-----------------LINKED LIST--------------------

    public static void reverse(){
    }



    //------------------STACK------------------
    /*
    in optimized way we try to skip the repetitive checking of already assinged elements
    i.e the continue stmt in brute force
     */
    public static void nextGreater(int[]arr){   //  O(n)
        Stack<Integer> S=new Stack<>();
        int[] grt=new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (!S.isEmpty() && arr[S.peek()]<arr[i]){
                grt[S.peek()]=arr[i];   //  OR   arr[S.peek()]=i;
                S.pop();
            }
            S.push(i);
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(grt));
    }
    public static void carParade(int[] arr){
        Stack<Integer> stk=new Stack<>();
        int carToSearch=1;
        for (int i = 0; i < arr.length; i++) {
            if (!stk.isEmpty() && stk.peek()==carToSearch){
                i--;
                stk.pop();
                carToSearch++;
            }
            else if (carToSearch==arr[i])
                carToSearch++;
            else {
                stk.push(arr[i]);
            }
        }
        while (!stk.isEmpty()){
            if (stk.peek()==carToSearch){
                stk.pop();
                carToSearch++;
            }
            else {
                System.out.println("no");
                break;
            }
        }
    }
}
