import java.util.InputMismatchException;
import java.util.Scanner;
public class exception_t
{
    public static int quotient(int n1,int n2)
    {
        if(n2==0)
            throw new ArithmeticException("Divisor cannot be 0"); //THrowing exception
        return n1/n2;
    }

    public static void main(String ...s)
    {
        Scanner inp=new Scanner(System.in);
        int n1=inp.nextInt();
        int n2=inp.nextInt();
        try
        {
            int result=quotient(n1,n2);
            System.out.println(n1+" / "+n2+" = "+result);
        }
        catch (ArithmeticException ex)
        {
            System.out.println("Exception: Integer cannot be divided by zero!");
            System.out.println("Stack Trace :"+ex.getStackTrace());
        }
        System.out.println("Execution continues!");
        //-------------------------------------------------------------
        boolean run=true;
        do
        {
            try
            {
                System.out.println("Enter a integer: ");
                int num=inp.nextInt();
                System.out.println("the number is: "+num);
                run=false;
            }
            catch (InputMismatchException ex)
            {
                System.out.println("Try again: Incorrect number value:");
                inp.nextLine(); //Discard input
            }
        }
        while (run);
    }
}
/*
--Exception handling enables a program to deal with exceptional situations and continue
its normal execution.
--Our main motive is to avoid errors , and if there is error resolve it in compile time,
'but dont let error happen in runtime.
**  ArrayIndexOutOfBoundException
**  InputMismatchException
--Exceptions are thrown from a method. the caller of the method can catch and handel
the exception.
-- Exception is an object created form an exception class.
--When an exception is thrown, the normal execution is interrupted .
"to throw a exception " is to pass the exception form one palce to another .
The statement for executing the method is contained in a try block and a catch block,
--The try block contains the code that is executed in normal execution of the code.
--The exception is caught by the catch block,
The code in the catch block is executed to handle the exception.
--The advantage of using exception handling is: It enables a method to throw an exception
to its caller, enabling the caller to handle the exception.
--Exceptions are ojects and objects are defined using classes .The root class for exceptions
is java.lang.Throwable
** THe throws keyword indicate that any method might throw an IOException. If the method
might throw multiple exceptions , add a list of the exception , seperated by commas ,
after throws.
public myMethod() throws excp1, excp2,...excpN
{  //code}

THe keyword to declare a exception is throws
and keyword to throw a exception is throw

--CATCHING EXCEPTION
try-catch
--If one of the statement inside the try block throws an exception , Java skips the remaining
statement of the try block and starts the process of finding the code to handle the exception.
THe code that handle the exception is called the exception handler;
it is found by propogating the exception backward through a chain of method calls;
Each catch block is examined in turn, from first to last; to see whether the type of the
exception object is an instance of the exeption classs in the catch block.
If so the exception object is assgned to the variable declared , and the code in the
catch block is exceuted.
-- Java force to deal with checked exceptions(i.e. .an exception other than Error or
RunTimeException ), we must invoke it in a try-catch block or declare the exception in calling method.
-------------MULTI CATCH------------
catch (Exception 1 | excp2  | excp3 |  .... | excpN ex)
{//code }
**          The finally keyword
The finally clause is always executed regardless wheteher an exception is occured or not
--Finally block executes even of there is a return statement prior to reaching the finally block
--a catch block may  be omitted when there is finally used,
A common use of the finally clause i in I/O programming , To ensure that a file is closed
under all circumstance.
we may place a file closing statement in the finally block.
--A method should throw an exception if the error needs to be handled by its caller.
--we should use exception in a situation which is exceptional and which is expected
is sometime difficult to decide.
--THe point is not to abuse exceptional handling as a way to deal with simple logic test,
because exceptional handling usually require more time and resources, it requires instanciating
a new exception object, rolling back the call stack,and propogating the exception through the chain
of method invoked to search for the hander.
* */

