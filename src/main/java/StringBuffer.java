class StringBuff
{
    public static void main(String ...vv)
    {
        StringBuffer str=new StringBuffer("Geeks for GEeks");
        System.out.println("buffer ensure capacity: "+str.capacity()+"  method capacity= "+str.capacity());
        str.ensureCapacity(30);
        System.out.println("Ensure capacity: "+str.capacity());
        str.setLength(10);
        System.out.println("new length: "+str.length());
        System.out.println("capacity: "+str.capacity());
        System.out.println(str);

        //getchar and tochar
        String s1=new String("relloww");
        String s2=new String("relloww");
        System.out.println(s1==s2);
        System.out.println(s1.charAt(0)<s1.charAt(0));
        System.out.println(s1.equals(s2));
        System.out.println(s2);

    }
}
