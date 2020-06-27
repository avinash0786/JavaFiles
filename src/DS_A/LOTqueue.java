package DS_A;

public class LOTqueue
{
    LOTqData head;
    public BstData LOTenque(BstData d)
    {
        LOTqData cont=new LOTqData();
        cont.refDta=d;
        if(head==null)
            head=cont;
        else
        {
            LOTqData n=head;
            while(n.next!=null)n=n.next;
            n.next=cont;
        }
        return d;
    }
    public BstData LOTdeque()
    {
        LOTqData cont=new LOTqData();
        cont.refDta=head.refDta;
        head=head.next;
        return cont.refDta;
    }

}
