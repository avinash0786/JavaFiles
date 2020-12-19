package DS_A;

public class D_Lrun
{
    public static void main(String ...ff)
    {
        D_linkedList l1=new D_linkedList();
        l1.insertAtStart(234,"Tarun");
        l1.insertAtStart(897,"Avinash");
        l1.insertAtStart(345,"Rahul");
        l1.insertAtStart(5675,"Pradeep");
        l1.insertAtStart(454,"Yogesh");
        l1.insertAtStart(111,"Palkin Gupta");

        l1.insertAtIndex(2,456,"Aman");
        l1.insertAtIndex(3,747,"Kabir Singh");
        l1.insertAtIndex(4,757,"Ritika Shrivastava");
        l1.insertAtIndex(5,5747,"Varun Gupta");


        System.out.println("-----------TRAVERSING FROM HEAD------------");
        l1.showFromhead();
        System.out.println("-----------TRAVERSING FROM TAIL------------");
        l1.showFromTail();

        /////////////////////////////////Stack runner
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
