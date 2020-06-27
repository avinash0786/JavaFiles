class person extends Object
{
    public String toString()
    {
        return "Person Class";
    }
}
class Student extends person
{
    public String toString()
    {
        return "Student Class";
    }
}
class graduateStudent extends Student
{
    public graduateStudent()
    {
        System.out.println("GraduateStudent CLas called");
    }
}

public class DynamicBinding
{
    public static void m(Object x)
    {
        System.out.println(x.toString());
    }

    public static void main(String ...aa)
    {
        m(new graduateStudent());
        m(new Student());
        m(new person());
        m(new Object());

        // casting objects
        Object ob=new Student();   //  implicit casting
        //as student is subclass of object class, hence it can be done
        //Student b=ob; // error because object is not a instance of Student,
        //explicit casting
        Student s=(Student) ob;
//  it is always possible to cast an instance of a subclass to a variable of a  superclass(known as upcasting)
// because instance of a subclass is always a instance of superclass
//when casting an instance of a superclass to a variable of its subclass(known as downcasting)
// explicit casting must be used to confirm the intention to the compiler
// For casting to be successful, the object to be cast is an instance of the subclass


    }
}
