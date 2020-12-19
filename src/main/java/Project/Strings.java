package Project;
public class Strings
{
    public static String replace(String word,char gone,char here)
    {
        char[] cArray=word.toCharArray();
        char[] cmod=new char[cArray.length];
        int i=0;
        for(char c:cArray)
        {
            if(c==gone)
                cmod[i]=here;
            else cmod[i]=c;
            i++;
        }
        return new String(cArray);
    }


    public static void main(String ...dd)
    {
        String aa="new doc";  //interned string object
        String bb="new doc";
        String cc=aa.concat(bb);
        System.out.println(cc);
        System.out.println(aa.equals(bb));
        System.out.println(aa==bb);  //compare variable refrence value
        String s="apple is red and mango is yellow";
        String [] words=s.split(" ");
        System.out.println("Clearing screen...");
        for (int i = 0; i < 25; ++i) System.out.println();
        System.out.println("Clearing screen...");


    }

}
/* not premitive
new object is created in a heap
stored as a array of characters
aa.lenght
aa.charAt
aa.toCharArray
string.indexOf(string s)
aa.split(pattern)
replace(string, word to replace, with)
toCharArray
*
*/