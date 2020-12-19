import java.util.Scanner;

public class class_moday1
{
    private static int apple=19;
    public static void main(String args[])
    {   System.out.println(class_moday1.apple);
        Scanner s=new Scanner(System.in);
        String name =s.nextLine();
        Scanner sb=new Scanner(name);
        char gender=s.next().charAt(0);
        int age=s.nextInt();
        long mno=s.nextLong();
        double cgpa=s.nextDouble();
        System.out.println("Name:"+name);
        System.out.println("Boolean result:"+sb.hasNext());
        System.out.println("Gender:"+gender);
        System.out.println("Age:"+age);
        System.out.println("Mobile no:"+mno);
        System.out.println("Cgpa:"+cgpa);

    }
}
