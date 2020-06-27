import java.util.ArrayList;
import java.util.List;

public class lanbda_t
{
    interface drawable
    {
        public void draw();
    }
    interface IntegerMath
    {
        int operation(int a, int b);
    }
    public int operateBinary(int a,int b,IntegerMath op){
        return op.operation(a,b);
    }
    public static void main(String ...aa)
    {
        //   without using lambda expression using anonymous class
        drawable d=new drawable() {
            @Override
            public void draw() {
                System.out.println("THis is drawable using anonymous class");
            }
        };
        d.draw();
        // using lambda expressions
        drawable d2=()->{
            System.out.println("Drawable using lambda exprssion ");
        };
        d2.draw();

        lanbda_t app=new lanbda_t();
        IntegerMath add=(a,b)->a+b;
        IntegerMath sub=(a,b)->a-b;
        System.out.println("23+45= "+app.operateBinary(23,45,add));
        System.out.println("112-13= "+app.operateBinary(112,13,sub));

        List<Integer> l1=new ArrayList<Integer>();
        l1.add(23);
        l1.add(34);
        l1.add(22);
        l1.add(44);
        //Using lambda expression in for exh loop
        l1.forEach((n)->System.out.println("Value: "+n));

    }
}
/*       (argument list) -> {// body code }
--It provides a clear and consice way to represent one method interface using an expression.
--It is used to provide the implementation of an interface which has functionality interace
////FUNCTIONAL INTERFACE///
--- Lambda expressions provide implementation for functional interfaces, an interface which has only
one abstract method is called a functional interface.
* LAMBDA expression looks like a method declaration, that take more than one paramater
* -- One issue with anonymous class is that if the implementation of anonymous clas is
* very simple, such as an interface that contains only one method, then the syntax  of
* anonymous class may seem  unwieldy and unclear.
* In these casse we usually try to pass functionality as an arguments to another method,
*
* */