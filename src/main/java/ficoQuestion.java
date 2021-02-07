import java.util.*;

public class ficoQuestion {
    public static int lineUp(String commands){
        int res=0;
        int correct=0;
        int wrong=0;
        int count=0;
        while (count<commands.length()){
            char turn=commands.charAt(count);
//            System.out.println("Corr: "+correct+"  wrong: "+wrong +" turn: "+turn);
            switch (turn){
                case 'L':
                    wrong=((wrong+1)+4)%4;
                    correct=((correct-1)+4)%4;
                    break;
                case 'R':
                    wrong=((wrong-1)+4)%4;
                    correct=((correct+1)+4)%4;
                    break;
                case 'A':
                    wrong=((wrong+2)+4)%4;
                    correct=((correct+2)+4)%4;
                    break;
            }
            if (correct==wrong)
                res++;
            count++;
        }
        return res;
    }
    public static void scoreBoard(String[] matches){
        Map<String,Integer> fin=new TreeMap<>();
        for (String match : matches) {
            String[] temp=match.split(" ");
            String team1=temp[0];
            String team2=temp[2];
            String[]arr=temp[1].split(":");
            int valT1=Integer.parseInt(arr[0]);
            int valT2=Integer.parseInt(arr[1]);

            if (valT1==valT2){
                valT1=1;
                valT2=1;
            }
            else if (valT1<valT2){
                valT1=0;
                valT2=3;
            }
            else {
                valT1=3;
                valT2=0;
            }
            if (fin.containsKey(team1)){
                int prev=fin.get(team1);
                fin.replace(team1,prev+valT1);
            }
            else {
                fin.put(team1,valT1);
            }
            if (fin.containsKey(team2)){
                int prev=fin.get(team2);
                fin.replace(team2,prev+valT2);
            }
            else {
                fin.put(team2,valT2);
            }
        }
        System.out.println(fin);
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(fin.entrySet());
        System.out.println(list);
        // Sort the list
//        Collections.sort(list, (o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));

//        Collections.sort(list, (o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

//        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
//            public int compare(Map.Entry<String, Integer> o1,
//                               Map.Entry<String, Integer> o2)
//            {
//                return (o1.getValue()).compareTo(o2.getValue());
//            }
//        });

        // put data from sorted list to hashmap
//        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
//        for (Map.Entry<String, Integer> aa : list) {
//            temp.put(aa.getKey(), aa.getValue());
//        }
//        System.out.println(temp);
//        Collections.sort(fin,new Comparator<HashMap<String,Integer>>(){
//
//            @Override
//            public int compare(HashMap<String, Integer> o1, HashMap<String, Integer> o2) {
//                return (o1.getValue()).compareTo(o2.getValue());;
//            }
//        });

//        Collections.sort(fin, (Comparator<HashMap<String, Integer>>) (o1, o2) -> {
//            return (o1.getValue(o1)).compareTo(o2.getValue());;
//        });
    }
    // Win: 3 pts
    // draw: 1 each point


    public static void chunk(String str,int limit){
        String[] splitted=str.split(" ");
        int[] lengths=new int[splitted.length];
        for (int i = 0; i < splitted.length; i++) {
            lengths[i]=splitted[i].length();
        }

        int maxChunk=0;
        int counter=0;
        //      [1/2]: apple is red
        int next=10;
        int maxChkVal=1;
        for (int i = 0; i < splitted.length && counter<splitted.length; i++) {
            if (i==next){
                maxChkVal++;
                next=next*10;
                i=-1;
                counter=0;
                continue;
            }
            int extra=numLen(i)+maxChkVal+5;
            int curlen=extra;
            while (curlen<=limit){
                curlen+=lengths[counter]+1;
                if (curlen-1>limit){
                    break;
                }
                counter++;
                if (counter>=splitted.length)
                    break;
            }
            maxChunk=i;
        }

        counter=0;

        System.out.println(Arrays.toString(splitted));

        for (int i = 1; i <=maxChunk && counter<splitted.length; i++) {
            String out="["+(i)+"/"+maxChunk+"]: ";
            int extra=numLen(i)+numLen(maxChkVal)+5;
            int curlen=extra;
            while (curlen<=limit){
                curlen+=lengths[counter]+1;
                if (curlen-1>limit){
                    break;
                }
                out+=splitted[counter]+" ";
                counter++;
                if (counter>=splitted.length)
                    break;
            }
            System.out.println(out.trim());
        }
    }
    public static int numLen(int n){
        if (n>999)
            return 4;
        else if (n>99)
            return 3;
        else if (n>9)
            return 2;
        else
            return 1;
    }

    public static void main(String[] args) {
        chunk("Dear Student, if you have attended the complete Informatica Webinar starting at 2:45 pm Deadline to fill this form: before 11:00 pm",20);
        String[] matches={"Liverpool 3:2 Psg","Redstar 0:0 Napoli","Psg 6:1 Redstar","Napoli 1:0 Liverpool"};
//        scoreBoard(matches);
//        System.out.println(lineUp("LLARL"));
    }
}
