import java.util.ArrayList;

public class generics_t
{

    public static <E> void print(E[] lst)
    {   System.out.println();
        for(int i=0;i<lst.length;i++)
        {
            System.out.println(lst[i]+" ");
        }
    }
    public static void main(String ...aa)
    {
        ArrayList<Integer> ll=new ArrayList<>();
        ll.add(23);
        ll.add(12);
        ll.add(11);
        int a=ll.get(2);
        System.out.println(ll);
        Integer[] in={1,2,3,4,5};
        String[] str={"apple","mango","Banana"};
        generics_t.<Integer>print(in);
        generics_t.<String>print(str);

    }
}
/*
Generics enables us to detect error at compile time rather than runtime.
Generics lets us parameterize type. With this capability we can define a class or a method
with generics type that the compiler can replace with concrete type.
--Casting is not needed to retrive a value from a list with a specified element type, because
the compiler already knows the element type.
-- A Generic type can be defined for a class or interface.
A concrete must be defined when using the class to create an object or using the class
or interface to declare a reference variable.
--Generic type can be defined for a static method

 */