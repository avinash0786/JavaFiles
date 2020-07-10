public class string
{
    public static void main(String [] args)
    {
        String mystr="Hello world";
        System.out.println(mystr);
        int lenstr=mystr.length();   //length of string
        System.out.println(lenstr);
        //using functions associated with string
        System.out.println(mystr.toUpperCase());  //upper, lower
        String str2= "apple";

        String newst=mystr+str2;       //concatinate 2 strings
        System.out.println(newst);
        System.out.println(newst.replace('p','a'));//replace

        System.out.println(newst.indexOf('w'));//index of character
        System.out.println("tO CHAR ARRAY:  ");
        //for(char a:newst.toCharArray())System.out.println(a+" ");
        String[] ne="abc".split("");
        for(String a:ne) System.out.println(a);
    }
}
