package GEEKS_FOR_GEEKS;

import java.util.Arrays;
import java.util.Stack;

public class stackClass {
    static boolean isBalanced(String str){
        Stack<Character> stack=new Stack<>();
        for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i)=='(' || str.charAt(i)=='{' || str.charAt(i)=='[')
                    stack.push(str.charAt(i));
                else {
                    if (stack.empty())
                        return false;
                    if (!matching(stack.peek(),str.charAt(i)))
                        return false;
                    else
                        stack.pop();
                }
        }
        return stack.empty();
    }

    static private boolean matching(char a, char b) {
        return (a=='(' && b==')') || (a=='{' && b=='}') || (a=='[' && b==']');
    }

    static void stockSpan(int[]arr,int n){
        Stack<Integer> stk=new Stack<>();
        stk.push(0);
        System.out.println("Span: "+1);
        for (int i = 1; i < n; i++) {
            while (!stk.isEmpty() && arr[stk.peek()]<=arr[i])
                stk.pop();      //  stk.isEmpty()?i+1:i-stk.peek()
            System.out.println("Span: "+(stk.isEmpty()?i+1:i-stk.peek()));
            stk.push(i);
        }
    }
    static void prevGreater(int[] arr){
        Stack<Integer> stk=new Stack<>();
        stk.push(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            while (!stk.isEmpty() && stk.peek()<=arr[i])
                stk.pop();
            System.out.println("Prev greater: "+((stk.isEmpty())?-1:stk.peek()));
            stk.push(arr[i]);
        }
    }
    static int[] prevSmaller(int[] arr){
        Stack<Integer> stk=new Stack<>();
        int[] op=new int[arr.length];
        stk.push(0);
        op[0]=-1;
        for (int i = 1; i < arr.length; i++) {
            while (!stk.isEmpty() && arr[stk.peek()]>=arr[i])
                stk.pop();
            int t=(stk.isEmpty())?-1:stk.peek();
//            System.out.println("Prev smaller: "+t);
            op[i]=t;
            stk.push(i);
        }
        System.out.println(Arrays.toString(op));
        return op;
    }
    static int[] nextSmaller(int[] arr){
        Stack<Integer> stk=new Stack<>();
        int[] op=new int[arr.length];
        stk.push(arr.length-1);
        op[arr.length-1]=arr.length;
        for (int i = arr.length-2; i >=0; i--) {
            while (!stk.isEmpty() && arr[stk.peek()]>=arr[i])
                stk.pop();
            int t=(stk.isEmpty())?arr.length:stk.peek();
//            System.out.println("Prev smaller: "+t);
            op[i]=t;
            stk.push(i);
        }
        System.out.println(Arrays.toString(op));
        return op;
    }

    static int maxAreaNaive(int[] arr){
        int res=0;
        for (int i = 0; i < arr.length; i++) {
            int cur=arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j]>=arr[i])
                    cur+=arr[i];
                else
                    break;
            }
            for (int j = i-1; j >=0 ; j--) {
                if (arr[j]>=arr[i])
                    cur+=arr[i];
                else
                    break;
            }
            res=Math.max(res,cur);
        }
        return res;
    }
    static int maxAreaBetter(int[] arr){
        int[] PS=prevSmaller(arr);
        int[] NS=nextSmaller(arr);
        int res=0;
        for (int i = 0; i < arr.length; i++) {
            int curr=arr[i];
            curr+=(i-PS[i]-1)*arr[i];
            curr+=(NS[i]-i-1)*arr[i];
            res=Math.max(res,curr);
        }
        return res;
    }

    static int largestAreaEffi(int[] arr){
        int res=0;
        Stack<Integer> stack =new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            int cur=0;
            while (!stack.empty() && arr[stack.peek()]>=arr[i]){
                int t=stack.pop();
                cur=arr[t]*(stack.empty()?i:(i-stack.peek()-1));
                res=Math.max(res,cur);
            }
            stack.push(i);
        }
        while (!stack.empty()){
            int t=stack.pop();
            int curr=arr[t]*(stack.empty()?arr.length:(arr.length-stack.peek()-1));
            res=Math.max(res,curr);
        }
        return res;
    }
    public static void main(String[] args) {
        int qq=6;
        System.out.println(qq++ +" " + qq++ +" " + qq++);
        System.out.println("Largest area histogram:  "+largestAreaEffi(new int[]{6,2,5,4,1,5,6}));
//        System.out.println(t.empty());      //// stack.empty() is of class util.stack and check if stack is empty
//        System.out.println(t.isEmpty()); // stack.isEmpty() is of class util.vector and check if vector is empty
//        System.out.println("Balanced paran: "+isBalanced("}}"));
//        prevGreater(new int[]{20,30,10,5,16,56,23,49});
//        System.out.println("Max area Naive: "+maxAreaNaive(new int[]{6,2,5,4,1,5,6}));
//        for (int i : prevSmaller(new int[]{6,2,5,4,1,5,6})) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
//        for (int i : nextSmaller(new int[]{6,2,5,4,1,5,6})) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
        System.out.println("Max area better: "+maxAreaBetter(new int[]{6,2,5,4,1,5,6}));
//        stockSpan(new int[]{60,10,20,15,35,40,50},7);
//        stackDS stack=new stackDS();
//        System.out.println(stack.getSize());
//        stack.push(23);
//        stack.pop();
//        stack.push(12);
//        stack.push(44);
//        System.out.println(stack.getSize());
//        System.out.println(stack.peek());
//        stack.pop();
//        System.out.println(stack.peek());
//        stack.pop();
//        stack.pop();
    }
}
class stackDS{
    node HEAD;
    int size;
    void push(int x){
        node temp=new node(x);
        if (HEAD==null){
            HEAD=temp;
        }
        else {
            temp.next=HEAD;
            HEAD=temp;
        }
        size++;
        System.out.println("Pushed: "+x);
    }
    int pop(){
        if (HEAD==null){
            System.out.println("Error Pop: Underflow !");
            return 0;
        }
        int res=HEAD.data;
        HEAD=HEAD.next;
        size--;
        System.out.println("Popped: "+res);
        return res;
    }
    int getSize(){return size;}
    boolean isEmpty(){return HEAD == null;}
    int peek(){return HEAD.data;}
}
/*
INSERT DELETE FORM SAME END
isEmpty--return true false if empty
push--adding to stack
pop--removing form stack
peek--return the top element
size--return no of elem

APPLICATIONS:-
-function call
-checking for balanced param
-reversing items
-infix to prefix/postfix
-eval of postfix/prefix
-stock span problem
-undo/redo OR forward/backward
 */