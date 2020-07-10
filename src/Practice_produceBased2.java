import java.util.Stack;

public class Practice_produceBased2
{
    static int top;
    static int top1=0;
    public static void pushQueus(Stack s1,Stack s2, int x)
    {
        while (!(s1.isEmpty()))
            s2.push(s1.pop());
        s1.push(x);
        while (!(s2.isEmpty()))
            s1.push(s2.pop());
    }
    public static int removequeue(Stack s1)
    {
        return (int) s1.pop();
    }
    public static void peekQueue(Stack s1)
    {
        System.out.println((int) s1.peek());
    }

    public static void main(String ...a)
    {


        /*Stack<Integer> s1=new Stack<>();
        Stack<Integer> s2=new Stack<>();
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t=in.nextInt();
        for(int i=0;i<t;i++)
        {
            int val=in.nextInt();
            if(val==3)
                peekQueue(s1);
            else if(val==2)
                System.out.println(removequeue(s1));
            else if(val==1)
            {
                int ele=in.nextInt();
                pushQueus(s1,s2,ele);
            }
        }

        pushQueus(s1,s2,12);
        pushQueus(s1,s2,32);
        pushQueus(s1,s2,43);
        peekQueue(s1);
        System.out.println(removequeue(s1));
        peekQueue(s1);*/

        /*Queue<Integer> q1=new LinkedList<>();
        q1.add(12);
        q1.add(23);
        q1.add(11);

        System.out.println(q1.poll());
        System.out.println(q1.peek());
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));*/

    }
}
