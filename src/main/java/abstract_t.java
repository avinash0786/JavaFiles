abstract class geometricObject
{
    private String color="White";
    private boolean filled;
    private java.util.Date dateGenerated;
    /*  Construct a default geometricObject object */
    protected geometricObject()
    {
        dateGenerated=new java.util.Date();
    }
    /* Construct a geometric object with color ad filled value */
    protected geometricObject(String color, Boolean filled)
    {
        dateGenerated=new java.util.Date();
        this.color=color;
        this.filled=filled;
    }
    // Return Color
    public String getColor()
    {
        return color;
    }
    public void setColor(String color)
    {
        this.color=color;
    }
    public boolean idFilled()
    {
        return filled;
    }
    public void setFilled(boolean filled)
    {
        this.filled=filled;
    }
    public java.util.Date getDateGenerated(){
        return dateGenerated;
    }
    public String toString()
    {
        return "Created on: "+dateGenerated+"\n Color: "+color+" \n Filled: "+filled;
    }
}
/* --Abstract class is like a regular class, but we cannot create INSTANCE of abstract
class using the new operator.
--An abstract class is defined without implementation.
--Its implementation is provided by the subclass
--A class that contains abstract methods must be defined as abstract.
--The constructor in the abstract class is defined protected, because it is used only by subclasses.
--When we create an instance of a concrete subclass , its superclass's constructor is invoked to initialize data,
fields defined in the superclass.

-- The geometricObject abstract class defines the common features (data and methods) for geometric objects and provide,
appropriate objects constructor. Because we dont know how to compute areas and perimeter of geometric objects,
getarea and getPerimeter are defined as abstract methods,
These methods are implemented in the subclasses.
* */
//    SUBCLASS        SUPERCLASS
class Circle extends geometricObject
{
    private double radius;
    public Circle(){};
    public Circle(double radius)
    {
        this.radius=radius;
    }
    public Circle(double radius, String color, boolean filled)
    {
        this.radius=radius;
        setColor(color);
        setFilled(filled);
    }
    public double getRadius()
    {
        return radius;
    }
    public void setRadius(double radius)
    {
        System.out.println("Radius set to: "+radius);
        this.radius=radius;
    }
    public double getArea()
    {
        return radius*radius*Math.PI;
    }
    public double getDiameter()
    {
        return 2*radius;
    }
    public double getPerimeter()
    {
        return 2*radius*Math.PI;
    }
    public void printCircle()
    {
        System.out.println("The circle is created on: "+getDateGenerated()+ "and the radius is: "+radius);
    }

}
class Rectangle extends geometricObject
{
    public double width;
    public double height;

    public Rectangle(){}
    public Rectangle(double height, double width, String color, boolean filled)
    {
        this.height=height;
        this.width=width;
        setColor(color);
        setFilled(filled);
    }
    public double getWidth()
    {
        return width;
    }
    public double getHeight()
    {
        return height;
    }
    public void setWidth(double width)
    {
        this.width=width;
    }
    public void setHeight(double height)
    {
        this.height=height;
    }
    public double getArea()
    {
        return width*height;
    }
    public double getPerimeter()
    {
        return 2*(width+height);
    }


}

//   POLYMORPHISM means that a variable of supertype can refer to a subtype object
/*  A type defined by a subclass is called by subtype, a type defined by a superclass is supertype
A subclass is a specialization of its superclass with additional new features
Every instance of a subclass is also an instance of its superclass , but not vice versa,
therefore we can pass an intance of subclass to a parameter of its superclass type
* */

public class abstract_t
{
    public static void displayObject(geometricObject ob)
    {
        System.out.println("Created on: "+ob.getDateGenerated()+" Color:"+ob.getColor());
    }
    public static void main(String...aa)
    {
        Circle c1=new Circle(5,"Blue",true);
        System.out.println("The circle area is: "+c1.getArea());
        System.out.println("The circle color is: "+c1.getColor());
        System.out.println("The circle diameter is: "+c1.getDiameter());
        System.out.println("THe circle radius is: "+c1.getRadius());

        Rectangle r1=new Rectangle(2,10,"Red", false);
        System.out.println("THe Rectangle area is : "+r1.getArea());
        System.out.println("The rectangls perimeter is: "+r1.getPerimeter());
        //   --- POLYMORPHISM ---
        displayObject(new Circle(5,"Orange",true));
        displayObject(new Rectangle(3,5,"pink",false));
// A method can be implemented inn several class along the inheritance chain, THe JVM decides which method to invoke at runtime
    System.out.println(c1.toString());  // tostring method defined in Object class
//

    }
}

/*a super class defines common behavior for related subclasses.
* An interface can be used to define common behavior for classes(including unrelated classes)
*
* An abstract class cannot be used to create objects.
* An abstract class can contain abstract methods, which are implemented in concrete subclasses.
* In inheritance hierarchy classes become more specific and concrete with each new subclass.
* If we move form subclass back up to superclass , the classes become more general and less specific
*
* *Sometimes a superclass is  so abstract that it cannot have any specific instances.
* Such a class is referred to as an abstract class.
*
*
* --Contary to the convention, a subclass is not a subset of its superclass. infact a subclass usually contain
* more information and methods than its superclass.
* --Private data field in a superclass are not accessible outside the class .
* therefore they cannot be accessed directly in a subclass
* --- Java does not allow multiple inheritance,
* however, multiple inheritance can be implemented using interfaces.
*
* --Super keyword refer to the superclass and can be used to invoke the,
* superclass's methods and constructor.
* --this refrence, introduces the use of the keyword this to refrence the calling object.
* --the keyword suprer refers to the class in which super appears .
* used in 2 ways:
* 1. TO call the superclass constructor
* 2. To call a superclass method
*
*           --- DYNAMIC BINDING ----
* Dynamic binding works as follows:
* Suppose an object ob is an instance of classes c1, c2, c3 ,.. cn, where C1 is subclass of C2,
* C2 is subclass of C3, and Cn is Object class.
* If ob invokes a function p , the JVM searches the implementation of the method p in C1,C2,C3,...Cn.
* in this order until it is found.
* Once an implementation is found, the search stops and the first-found implementation is involved
* */