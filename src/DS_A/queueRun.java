package DS_A;

public class queueRun
{
    public static void main(String ...ff)
    {
        queue q1=new queue();
        q1.enqueue(23);
        q1.enqueue(11);
        q1.enqueue(56);
        q1.dequeue();
        q1.queueShow();
        System.out.println("---------------------------");
        q1.enqueue(67);
        q1.queueShow();
    }
}
