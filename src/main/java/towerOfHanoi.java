public class towerOfHanoi
{
    public int moves;
    public void toh(int N, int from, int to, int aux)
    {   moves= (int) Math.pow(2,N-1);
        if(N==1)
            {System.out.println("Move disk 1 from rod " +  from + " to rod " + to);
            return;}
        toh(N-1,from,aux,to);
        System.out.println("Move disk " + N + " from rod " +  from + " to rod " + to);
        toh(N-1, aux, to, from);
    }
    /*
    * if (N >= 1) {
            // recursive call to move top disk from "from" to aux in current call
            toh(N - 1, from, aux, to);
            System.out.println("move disk " + N + " from rod " + from +
                               " to rod " + to);

            // increment moves
            this.moves++;

            // recursive call to move top disk from aux to "to" in current call
            toh(N - 1, aux, to, from);
        }
     */
    public static void main(String ...ee)
    {

    }
}
