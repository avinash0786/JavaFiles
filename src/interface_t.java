interface Edible
{
    public abstract String howToEat();
}
abstract class animal
{
    public abstract String sound();
}
class chicken extends animal implements Edible
{
    public String  sound()
    {
        return "Chicken: cock-a-dood";
    }
    public String howToEat()
    {
        return "Chicken : Fry it";
    }
}
class tiger extends animal
{
    public String sound()
    {
        return "Tiger:  ROOARr";
    }
}
abstract class fruit implements Edible
{
    //
}
class apple extends fruit
{
    public String howToEat()
    {
        return "Apple: Make slices";
    }
}
class orange extends fruit
{
    public String howToEat()
    {
        return "ORanges: Make Juice";
    }
}


public class interface_t
{

    public static void main(String...aa)
    {
        Object[] ob={new tiger(),new chicken(), new apple(),new orange()};
        for(int i=0;i<ob.length;i++)
        {
            if(ob[i] instanceof Edible)
                System.out.println(((Edible)ob[i]).howToEat());
            if(ob[i] instanceof animal)
                System.out.println(((animal)ob[i]).sound());
        }
    }
}
/*
--An interface is a class-like construct that contains only constants and abstract methods
-- in many ways an interface is similar to an abstract class, but its intent is to specify common
behavior for objects of related classes or unrelated classes .
--An interface is treated like a special class in java .
Each interface is compiled into  a seperate byte code file , just like a regular class,
-- we can create an instance from a interface using the new keyword.
we can use interface as a data type for reference variable, as a result of casting .
--the relationship between a class nad a interface is known as interface inheritance,
--  ALL THE VARIABLE IN INTERFACE ARE PUBLIC STATIC FINAL, and
ALL METHODS ARE PUBLIC ABSTRACT , so no need to specify explicitly.
--no constructor in interface

-- A class can implement multiple interfaces , but it can only extend one superclass.
(no multiple inheritance in class)
(multiple inheritance using interfaces)
class apple implements intfac1, intfac2, intfac3, ...intfacN  { // code  }

a interface can inherit other interface using extends keyword

* **/