public class lambda_exmple
{
    interface A
    {
        String myName(String name);
    }
    interface B
    {
        double sum(int a, int b);
    }
    interface C
    {
        void show(int a, double b);
    }
    interface D
    {
        int length(int []a);
    }

    public static void main(String ...aa)
    {
         A a1= name -> name; //using lambda
        /*
        A a1=new A() {
             @Override
             public String myName(String name) {
                 return name;
             }
         };   */

         B b1= (a, b) -> a+b; // lambda expression
         /*
          B b1=new B(){
             public double sum(int a, int b)
             {
                 return a+b;
             }
         };   */
        C c2=(a,b)->System.out.println("a:"+a+" b:"+b);

         /*C c1=new C() {
             @Override
             public void show(int a, double b) {
                 System.out.println(" A: "+a+" B: "+b);
             }
         };*/
         D d1= a -> a.length;

         System.out.println(a1.myName("Avinash kumar"));
         System.out.println("Sum of 23, 45: "+b1.sum(23,45));
         c2.show(12,54);
         System.out.println("THe length of array  {1,2,3,4} is:"+d1.length(new int[]{1,2,3,4}));
    }
}
/*
Write a program to defined and invoke following methods by using Lambda expressions. These
methods are present in functional interfaces A, B, C and D respectively.
Also defined all the mentioned interface with their methods.
String myName(String s); //display s
double sum(int a, int b); //return addition of a and b
void show(int a, double b); //display a and b
int length(int [] a); //return length of array a
*
* */
