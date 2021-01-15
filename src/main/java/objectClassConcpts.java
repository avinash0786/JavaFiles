import java.util.ArrayList;
import java.util.List;

public class objectClassConcpts {
    public static void main(String[] args) {
//        mango m1=new mango();
        mango g1=new guava();
        g1.funMango();
//        g1.funGuava();
        g1.over();
        guava g2=new guava();
        g2.funGuava();
        g2.over();
        System.out.println(g1.toString());
        System.out.println(g1.getClass());
        immutableCircle c1=new immutableCircle(12);
        System.out.println(c1.getArea());
        /////////////////   GENERIC     ///////////////////////
        myList m1=new myList();
        m1.makeList("Apple","mango");
        m1.makeList(12,43);
    }
}

class myList{
    // The type paramenter T is scoped to the method and is independent of type parameter of other method
    public <T> List<T> makeList(T t1, T t2){
        List<T> res=new ArrayList<>();
        res.add(t1);
        res.add(t2);
        System.out.println(res);
        return res;
    }
}

class mango{
    mango(){
        System.out.println("Mango: Default no param constructor");
    }
    void funMango(){
        System.out.println("Mango:  function");
    }
    void over(){
        System.out.println("Mango over-riding");
    }
}
class guava extends mango{
    guava(){
        System.out.println("Guava:  Default constructor");
    }
    void funGuava(){
        System.out.println("Guava : function");
    }
    public String toString(){
        return "This is Guava class!";
    }
    void over(){
        System.out.println("Guava over-riding");
    }
}
class immutableCircle{
    private final int radius;

    immutableCircle(int radius) {
        this.radius = radius;
    }
    int getArea(){return radius*radius;}
    int getRadius(){return radius;}
}


/*
    CLASSES AND OBJECTS
--Objects have state and behaviours.
--Class- a class can be defined as a template/blueprint that describe the behaviour/state that teh obect of its type.

The advantage is that the same functionality is called with two diﬀerent numbers of inputs. While invoking the
method according to the input we are passing, (In this case either one string value or two string values) the
corresponding method is executed.
Methods can be overloaded:
Based on the number of parameters passed.
    Example: method(String s) and method(String s1, String s2).
Based on the order of parameters.
    Example: method(int i, float f) and method(float f, int i)).

Note: Methods cannot be overloaded by changing just the return type (int method() is considered the same as String
method() and will throw a RuntimeException if attempted). If you change the return type you must also change the
parameters in order to overload.

-----Method Overriding and Overloading are two forms of polymorphism supported by Java.
Method overloading (also known as static Polymorphism) is a way you can have two (or more) methods (functions)
with same name in a single class.

public class Shape{
    //It could be a circle or rectangle or square
    private String type;

    //To calculate area of rectangle
    public Double area(Long length, Long breadth){
        return (Double) length * breadth;
    }

     //To calculate area of a circle
     public Double area(Long radius){
        return (Double) 3.14 * r * r;
    }
}

Why is this called static polymorphism?
Well that's because which overloaded methods is to be invoked is decided at compile time, based on the actual
number of arguments and the compile-time types of the argument

-------Method Overriding
Well, method overriding (yes you guess it right, it is also known as dynamic polymorphism) is somewhat more
interesting and complex topic.
In method overriding we overwrite the method body provided by the parent class. Got it? No? Let's go through an
example.
public abstract class Shape{

    public abstract Double area(){
        return 0.0;
    }
}
So we have a class called Shape and it has method called area which will probably return the area of the shape

public class Circle extends Shape {
    private Double radius = 5.0;
    // See this annotation @Override, it is telling that this method is from parent
    // class Shape and is overridden here
    @Override
    public Double area(){
        return 3.14 * radius * radius;
    }
}
Similarly rectangle class:
 public class Rectangle extends Shape {
    private Double length = 5.0;
    private Double breadth= 10.0;
    // See this annotation @Override, it is telling that this method is from parent
    // class Shape and is overridden here
    @Override
    public Double area(){
        return length * breadth;
    }

public class AreaFinder{

    public static void main(String[] args){
        //This will create an object of circle class
        Shape circle = new Circle();
        //This will create an object of Rectangle class
        Shape rectangle = new Rectangle();

        // Drumbeats ......
        //This should print 78.5
        System.out.println("Shape of circle : "+circle.area());
        //This should print 50.0
        System.out.println("Shape of rectangle: "+rectangle.area());


Note: You should not call static members on objects, but on classes. While it does not make a diﬀerence for the
JVM, human readers will appreciate it.
static members are part of the class and exists only once per class. Non-static members exist on instances, there
is an independent copy for each instance. This also means that you need access to an object of that class to access
its members.

IMMUTABLE CLASS
--immutable objects are instance whose state dosent change after it has been initialized;
by making all fields final and private;



--The visibility of default constructor is same as the visibility of the class;


------VISIBILITY/ ACCESS SPECIFIER
1. Private -- only class and member function
2. Public -- visible everywhere
3. Default/ Package Visibility
4. Protected- only to package and subclass

Access Modiﬁer          Visibility      Inheritance
Private                 Class only      Can't be inherited
No modiﬁer / Package    In package      Available if subclass in package
Protected               In package      Available in subclass
Public                  Everywhere      Available in subclass

final--keyword
finalize-- last function in class to execute
finally--in try catch block
 */
