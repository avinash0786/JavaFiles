public class latinSquare {
    static void printLatin(int n) {

        // A variable to control the
        // rotation point.
        int k = n + 1;

        // Loop to print rows
        for (int i = 1; i <= n; i++) {

            // This loops runs only after
            // first iteration of outer
            // loop. It prints
            // numbers from n to k
            int temp = k;

            while (temp <= n) {
                System.out.print(temp + " ");
                temp++;
            }

            // This loop prints numbers from
            // 1 to k-1.
            for (int j = 1; j < k; j++)
                System.out.print(j + " ");

            k--;
            System.out.println();
        }
    }

    public static void main (String[] args)
    {

        // Invoking printLatin function
        printLatin(4);
    }
}