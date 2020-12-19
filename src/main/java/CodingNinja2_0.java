import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class CodingNinja2_0 {
    public static void main(String[] args) {
        Scanner inp = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n=inp.nextInt();
        int pair=inp.nextInt();

        ArrayList<int[]> groups=new ArrayList<>();
        for (int i = 0; i < pair; i++) {
            int [] temp=new int[2];
            temp[0]=inp.nextInt();
            temp[1]=inp.nextInt();
            groups.add(temp);
        }

        ArrayList<HashSet<Integer>> op=new ArrayList<>();
        int g=0;
        while (groups.size()>0){
            System.out.println("Size: "+groups.size());
            op.add(new HashSet<>());
            op.get(g).add(groups.get(0)[0]);
            op.get(g).add(groups.get(0)[1]);
            groups.remove(0);
            System.out.println(op);
            for (int i = 0; i < groups.size(); i++) {
                System.out.println("Inner: "+i);

                if(op.get(g).contains(groups.get(i)[0])) {
                    op.get(g).add(groups.get(i)[0]);
                    op.get(g).add(groups.get(i)[1]);
                    System.out.println("remove 1");
                    groups.remove(i);
                    continue;
                }
                if(op.get(g).contains(groups.get(i)[1])) {
                    System.out.println("remove 2");
                    op.get(g).add(groups.get(i)[0]);
                    op.get(g).add(groups.get(i)[1]);
                    groups.remove(i);
                }
            }
//            groups.remove(g);
            g++;
        }
        System.out.println("Done");
        System.out.println(op);

//        System.out.println(op.get(0).contains(3));

    }
}
