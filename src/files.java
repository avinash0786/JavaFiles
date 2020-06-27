import java.io.File;
import java.util.Date;

public class files
{
    public static void main(String ...ss)
    {
        File f1=new File("new.txt");
        System.out.println("Does new.text exists: "+f1.exists());
        System.out.println("The file has: "+f1.length()+" bytes");
        System.out.println("Can it be read: "+f1.canRead());
        System.out.println("Is a directory: "+f1.isDirectory());
        System.out.println("IS a file: "+f1.isFile());
        System.out.println("Is it absolute: "+f1.isAbsolute());
        System.out.println("IS hidden: "+f1.isHidden());
        System.out.println("Absolute file fath is: "+f1.getAbsolutePath());
        System.out.println("Last modified on: "+new Date(f1.lastModified()));
    }
}
