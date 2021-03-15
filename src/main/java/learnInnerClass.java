public class learnInnerClass {
    class internal{
        private internal(){
            System.out.println("Internal class constructor");
        }
        void intFunc(){
            System.out.println("internal fn called");
        }
    }


    void makeInternal(){
        internal sa=new internal();
        sa.intFunc();
    }
    public static void main(String[] args) {
        outerLearn o1=new outerLearn();
        o1.showOuter();
        learnInnerClass dq=new learnInnerClass();
        dq.makeInternal();
        localInner1 obj=new localInner1();
        obj.display();
    }
}

abstract class exampleAbs{
    abstract void myMethod();
}
class extAbs extends exampleAbs{

    @Override
    void myMethod() {
        System.out.println("This is abstract method extended");
    }
}

class outerLearn{
    int a;
    public void showOuter(){
        System.out.println("THis is class outer show methos");
        class innerLearn{
            innerLearn(){
                System.out.println("Inner class learn constructor");
            }
            void display(){
                System.out.println("Display fn of inner class");
            }
        }
        innerLearn iner=new innerLearn();
        iner.display();
    }


}
class localInner1{
    private int data=30;//instance variable
    void display(){
        class Local{
            void msg(){System.out.println(data);}
        }
        Local l=new Local();
        l.msg();
    }
}
class IntStack {
    private IntStackNode head;
    // IntStackNode is the inner class of the class IntStack
    // Each instance of this inner class functions as one link in the
    // Overall stack that it helps to represent
    private static class IntStackNode {
        private int val;
        private IntStackNode next;
        private IntStackNode(int v, IntStackNode n) {
            val = v;
            next = n;
        }
    }
    public IntStack push(int v) {
        head = new IntStackNode(v, head);
        return this;
    }
    public int pop() {
        int x = head.val;
        head = head.next;
        return x;
    }
}
/*
Method Overriding and Overloading are two forms of polymorphism supported by Java.
---Method Overloading
Method overloading (also known as static Polymorphism) is a way you can have two (or more) methods (functions)
with same name in a single class.

---Method Overriding
Well, method overriding (yes you guess it right, it is also known as dynamic polymorphism) is somewhat more
interesting and complex topic.
In method overriding we overwrite the method body provided by the parent class.


At its core, static nested classes do not have a surrounding instance of the outer class, whereas non-static nested
classes do. This aﬀects both where/when one is allowed to instantiate a nested class, and what instances of those
nested classes are allowed to access.
 */