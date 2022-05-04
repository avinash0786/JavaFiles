import java.util.*;

public class arunAssignment {
    //to store student name and matks in 5 subjects
    static HashMap<String,int[]> stuData=new HashMap<>();
    static TreeMap<String,Character> stuGrades=new TreeMap<>();
    public static void main(String[] args) {
        System.out.println("Enter the number of elements");
        Scanner inp=new Scanner(System.in);
        int n=inp.nextInt();
        if (n<1 && n>10)
            System.out.println(n+" is invalid array size");
        else {
            int[] arr1=new int[n];
            int[] arr2=new int[n];
            System.out.println("Enter elements of the first array");
            takeInput(arr1);
            System.out.println("Enter elements of the second array");
            takeInput(arr2);
            //generate valid pairs
            getPair(arr1,arr2);
            for (int[] pair : pairs) {
                System.out.println(pair[0]+","+pair[1]);
            }
        }

//        int n=inp.nextInt();
//
//        fetchData(n);   //function to get and generate grade
//
//        for (Map.Entry<String, Character> student : stuGrades.entrySet()) {
//            System.out.println(student.getKey()+":"+student.getValue());
//        }
    }
    public static void takeInput(int[] arr){
        Scanner inp=new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            arr[i]=inp.nextInt();
            if (arr[i]<10 && arr[i]>999){
                System.out.println("Invalid input");
                return;
            }
        }
    }
    static ArrayList<int[]> pairs=new ArrayList<>();
    public static void getPair(int[] arr1,int[] arr2){
        for (int i = 0; i <arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                int a=arr1[i];
                int b=arr2[j];
                if (getProduct(a)==getSum(b)) {
                    pairs.add(new int[]{a, b});
                }
            }
        }
    }
    public static int getSum(int n){
        int sum=0;
        while (n>0){
            sum+=n%10;
            n=n/10;
        }
        return sum;
    }
    public static int getProduct(int n){
        int prod=1;
        while (n>0){
            prod*=n%10;
            n=n/10;
        }
        return prod;
    }


    public static void fetchData(int n){
        Scanner inp=new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            String str=inp.nextLine();
            String[] data=str.split(":");
            int[] marks=new int[5];
            int sum=0;
            for (int j = 1; j <= 5; j++) {
                marks[j-1]=Integer.parseInt(data[j]);
                sum+=marks[j-1];
            }
            stuData.put(data[0],marks);
            stuGrades.put(data[0],getGrade(sum));
        }
    }
    public static char getGrade(int totalMarks){
        if (totalMarks>=400)
            return 'O';
        else if (totalMarks>=300)
            return 'A';
        else if (totalMarks>=250)
            return 'B';
        else if (totalMarks>=200)
            return 'C';
        else
            return 'E';
    }
}
/*  TEST CASE
jasja:48:64:25:76:39
chrs:85:41:62:55:70
nuu:80:66:87:75:96
za sas:45:36:27:49:36

12
35
56
34

261
195
112
813
 */