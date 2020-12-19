public class fib_series<stop>
{
    public static void main(String args[])
    {

        int i=0;
        int stop=10;
        while(i<=stop)
            {System.out.print(fib(i)+" - ");
            i+=1;}
    }
    static public int fib(int n)
    {
        if(n==0)
            return 0;
        else if(n==1)
            return 1;
        else return fib(n-1)+fib(n-2);
    }

}















/* USING RECURSION::::::::::
* f(count>0){
         n3 = n1 + n2;
         n1 = n2;
         n2 = n3;
         System.out.print(" "+n3);
         printFibonacci(count-1);              :::::::RECURSIVE CALL TO FUNCTION
     }
*/
