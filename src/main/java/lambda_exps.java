public class lambda_exps
{
    interface Parent{
        public String parentMethod(String s);
    }
    interface child extends Parent{
        public string childMethod();
    }
    public static void passMeLambda(Parent p,String s){
        p.parentMethod(s);
    }
    public static void main(String[] args) {
        Parent pi=new Parent() {
            @Override
            public String parentMethod(String s) {
                return null;
            }
        };
        child c1=new child() {
            @Override
            public String parentMethod(String s) {
                return null;
            }

            @Override
            public string childMethod() {
                return null;
            }
        };

        Parent p2=(s)-> s+" Returned !";        //Lambda Expression to define functional interface
        /*
        p2 holds a singleton instance of a class, similar to an anonymous class, which imlements
        Parent and where the one method definition is provided by lambda expression
         */
        Parent p3=(S)->{
            return "Added start: "+S;
        };
        System.out.println(p2.parentMethod("Send message"));
        //PAssing lambda as a function args
        passMeLambda((a)-> {
            System.out.println("Lambda Called: " + a);
            return "Lambda Called: "+a;
        },"My string");

    }
}
/*
Lambda expressions provide a clear and consice way of implementing a single method
interface using an expression. They allow us to reduce the amount of code we have to
create and mainteain.
While similar to anonymous classes, they have no type information by themselves,

Functional interface: INterface with just one abstract methods.
 */
