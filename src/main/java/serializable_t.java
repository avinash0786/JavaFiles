import java.io.*;
import java.util.Date;

class C implements Serializable
{   // marking transient , tell the JVM to ignore them when writing the object stream
    private int v1; // Only varaible serializable
    private static double v2;  // not serializable as Static
    private transient  Aas v3=new Aas();  //Not serializable as marked transient
}
class Aas
{
    //code
}
public class serializable_t
{
    public static void main(String...ss) throws IOException, ClassNotFoundException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("object.dat"));
        out.writeUTF("John Doe");
        out.writeDouble(564.231);
        out.writeObject(new Date());
        out.close();

        ObjectInputStream in=new ObjectInputStream(new FileInputStream("object.dat"));
        String name=in.readUTF();
        Double val=in.readDouble();
        Date d=(Date)(in.readObject());
        System.out.println("Name: "+name+ " \nVal: "+val+ "\nDate: "+d);
        in.close();
    }
}
/*
Serializable interface is a marker interface. Since it has no methods, we dont need to add additional
code in our class that implements Serializable.
Implementing this interface enables the java Serializable mechanism to automate the process
os storing object array.
--When a serialzable object is stored, the class of the object is encoded;
this inludes the class name and the signature, the value of the objects instance
variable, and the closure of any objects refrenced by the object.
The value of objects static variable are not stored.
 */

/*
** THE readObject may throw java.lang.ClassNotfoundException , because when the
JVM restores an object, it first loads the class for the object if the class has not
* been loaded.
NOt every object can be written to an output stream. Object that can be so written are said to be
* serializable.
* A serializable object is an instance of the java.io.serializable interface,  so the
* object's class must implement Serializable.
*
ObjectInputStream/ ObjectOutputStream classes can be used to read/write Serializable objects.
--Serialize objects means to convert its state to a byte stream so that way stream
can be reverted back into a copy of the objects.
--we need serialization because hard disk or network infrastructre are hardware component
and we cannot send java objects because it understands just bytes and not java objects,
Serialization refer to the translation of java objet state itno bytes to send it over
the network or store it in hard disk.
 */