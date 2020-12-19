import java.io.*;

public class serializable_array_t
{
    public static void main(String ...a) throws IOException, ClassNotFoundException
    {
        int [] num={1,2,3,4,5,6};
        String [] str={"Jhon","Apple","mango","pinaapple"};

        ObjectOutputStream op=new ObjectOutputStream(new FileOutputStream("array.dat",true));

        op.writeObject(num);
        op.writeObject(str);

        ObjectInputStream in=new ObjectInputStream(new FileInputStream("array.dat"));

        int [] newNum=(int [])(in.readObject());
        String [] newString=(String[])(in.readObject());

        for(int i=0;i<newNum.length;i++)
            System.out.println(newNum[i]);

        for(int i=0;i<newString.length;i++)
            System.out.println(newString[i]);
    }
}
