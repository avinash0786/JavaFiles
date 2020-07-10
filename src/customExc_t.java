import java.io.FileInputStream;
import java.io.IOException;

class InvalidRadiusException extends Exception  // custom exception
{
    private double radius;

    //Constructing a Exception, calling derived class constructor,i.e. Exception
    // having 4 constructor;
    public InvalidRadiusException(double radius)
    {
        super("Invalid Radius: "+radius);
        this.radius=radius;
    }
    // returning radius
    public double getRadius()
    {
        return radius;
    }

    private static void printFile() throws IOException
    {
        try(FileInputStream inp=new FileInputStream("File.txt"))
        {
            int data=inp.read();
            while(data!=-1)
            {
                System.out.println((char)data);
                data=inp.read();
            }
/* When the try block finishes execution the FileInputStream is closed automatically,
  try-with-resource
block,read some data from the FileInputStream , and have the FileInputStream closed automatically
once the execution leaves the try-with-resource block.
*/
        }
    }
}
 class CustomException
 {
     private double radius;
     public static int nos=0;
     public CustomException() throws InvalidRadiusException
     {
         this(1.0);
     }
     public CustomException(double r) throws InvalidRadiusException
     {
         setRadius(r);
         nos++;
     }
     public void setRadius(double r) throws InvalidRadiusException
     {
         if(r>=0)
             radius=r;
         else
             throw new InvalidRadiusException(r);
     }
     public double getRadius()
     {
         return radius;
     }
     public static int getNos()
     {
         return nos;
     }
     public double findArea()
     {
         return radius*radius*Math.PI;
     }
 }
public class customExc_t
{
    public static void main(String ...aa)
    {
        try
        {
            new CustomException();
            new CustomException(23);
            new CustomException(-9);
        }
        catch (InvalidRadiusException ex)
        {
            System.out.println("Error: "+ex);
        }
        System.out.println("No od objects Created: "+CustomException.getNos());
    }
}
/*
-- CUSTOM EXCEPTION--
-Custom exception class is extended by java.lang.exception class.
-THe Exception class extends java.lang.Throwable.



*/