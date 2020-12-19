public class dynamicCONNExCTIVITY
{
    private int [] id;
    public dynamicCONNExCTIVITY(int n)
    {
        id=new int[n];
        for(int i=1;i<n;i++)
            id[i]=i;
    }
    public boolean connected(int p,int q)
    {
        return id[p]==id[q];
    }
    public void union(int p,int q)
    {
        int pid=id[p];
        int qid=id[q];
        for(int i=0;i<id.length;i++)
        {
            if(id[i]==pid)
                id[i]=qid;
        }
    }

    public static void main(String ...ss)
    {
        dynamicCONNExCTIVITY g1=new dynamicCONNExCTIVITY(10);
        g1.union(1,4);
        g1.union(4,5);
        g1.union(2,3);
        g1.union(3,7);
        g1.union(6,3);
        g1.union(1,0);
        System.out.println( g1.connected(7,3));
        System.out.println(g1.connected(1,7));
    }
}
