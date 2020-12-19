package DS_A;

public class Stack
{
    Stack_dta head;
    public int push(int d)
    {
        Stack_dta node=new Stack_dta();
        node.data=d;
        if(head==null)
        {
            head=node;
        }
        else
        {
            node.next=head;
            head=node;
        }
        return d;
    }
    ////////pop////////
    public int pop()
    {
        if (head == null) {
            System.out.println("Stack is EMPTY !");
        }
        else
        {
            int da=head.data;
            Stack_dta n=head;
            head=n.next;
            return da;
        }
        return 0;
    }

    public void showStack()
    {
        if (head == null) {
            System.out.println("Stack is EMPTY !");
        }
        else
        {Stack_dta n=head;
        while (n!=null)
        {
            System.out.println(n.data);
            n=n.next;
        }}
        //System.out.println(n.data);
    }
    public void peek()
    {
        if (head == null) {
            System.out.println("Stack is EMPTY !");
        }
        else
        {Stack_dta n=head;
        System.out.println("The top element is: "+n.data);}
    }
}

