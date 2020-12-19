package DS_A;

public class Run_Stack
{
    public static void main(String ...gg)
    {
        System.out.println("STACK ELEMENTS");
        Stack s1=new Stack();
        s1.push(62);
        s1.push(76);
        s1.pop();
        s1.push(45);
        s1.peek();
        s1.push(23);
        s1.showStack();
        System.out.println("-------------------------");
        s1.push(56);
        s1.push(67);
        s1.peek();
        s1.pop();
        s1.push(45);
        s1.push(23);

        s1.showStack();
    }
}
