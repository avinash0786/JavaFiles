public class fact
{
    public static void main(String args[])
    {
        int i, fact=1;
        int n=5;
        for (i=1;i<=n;i++)
        {
            fact*=i;
        }
        System.out.println(fact);
    }
}
/**::::::::::using recurion
 * static int factorial(int n){
 *   if (n == 0)
 *     return 1;
 *   else
 *     return(n * factorial(n-1));
 *  }
 */
