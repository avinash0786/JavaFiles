package DS_A;

public class parenthesis_matcher
{
    public static boolean matcher(char [] arr)
    {
        long a=System.currentTimeMillis();
        Stack s=new Stack();
        for(int i:arr)
        {
            //System.out.println(t);
            if(i ==40 )
                {s.push(i);}
            else if(i ==41)
                {
                    if(s.head==null)
                    {
                        return false;
                    }
                    s.pop();
                }
        }
        if(s.head==null)
            return true;
        else
            return false;
    }

    public static void main(String ...ss)
    {
        char [] arr={'(','a','+','b',')','(',')'};
        //int temp=arr[5];
        //System.out.println(temp);
        System.out.println(matcher(arr));
    }
}
