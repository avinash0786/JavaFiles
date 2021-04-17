import java.util.Arrays;

public class newtonTest {
    public static void main(String[] args) {
            /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
            int n=4;
            int min=4;

            int[] allen=new int[]{1,2,3,4};
            int[] alnCpy=new int[]{1,2,3,4};
            int allenTotal=0;

            for(int i=0;i<n;i++){
                allenTotal+=allen[i];
            }
            int[] bob=new int[]{9,1,10,6};

            //solution starts
            int alnIndx=0;
            int totalCpy=allenTotal;
        System.out.println("Total stren: "+totalCpy);
        System.out.println(Arrays.toString(allen));
        System.out.println(Arrays.toString(bob));
        for(int i=0;i<min;i++){
                System.out.println("min: "+i+" indx: "+alnIndx+" attack: "+bob[i]);
                int cur=bob[i];
                int died=0;
                while(cur>0 && alnIndx<n){
                    System.out.println("die: "+allen[alnIndx]+"   "+cur+" alnIndxL "+alnIndx);
                    if(cur>=allen[alnIndx]){
                        System.out.println("normal inc");
                        cur=cur-allen[alnIndx];
                        died+=allen[alnIndx];
                        alnIndx++;
                    }
                    else{
                        System.out.println("Str dec: "+allen[alnIndx]+" cur: "+cur);
                        int t=allen[alnIndx];
                        allen[alnIndx]=allen[alnIndx]-cur;
                        cur=cur-t;
                        died+=allen[alnIndx];
                    }
                }
                allenTotal-=died;
                if(allenTotal<=0 || alnIndx==n){  //survive all after they all die
                    System.out.println("All died reset ind: "+alnIndx);
                    alnIndx=0;
                    allenTotal=totalCpy;
                    for(int j=0;j<n;j++){
                        allen[j]=alnCpy[j];
                    }
                }
            System.out.println("ending min: "+i+" alnIndx: "+alnIndx);
                System.out.println(n-alnIndx);
            System.out.println("---------------------");
            }
        }
//        Scanner inp=new Scanner(System.in);
//        int n=inp.nextInt();
//        String x=inp.next();
//        stringLev("");
//        int[] arr=new int[n];
//        for (int i = 0; i < n; i++) {
//            arr[i]=inp.nextInt();
//        }
//        System.out.println(initPosition(arr,x));
    public static void stringLev(String str){
        char[] arr=str.toCharArray();
        boolean chance=true;
        //  true->player01  false->player02
        int n=arr.length-1;
        if (arr.length<=1){
            System.out.println("Player1");return;
        }
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i <=n; i++) {
            System.out.println(Arrays.toString(arr));
            if (arr[i]=='A'){
                System.out.println("i: "+i);
                if (i==0){

                        if (arr[1]=='B')
                            arr[0]='C';
                        else if (arr[1]=='C')
                            arr[0]='B';
                        else
                            arr[0]='B';
                        chance=!chance;
                        continue;
                }
                else if (i==arr.length-1){
                    if (arr[n-1]=='B')
                        arr[n]='C';
                    else
                        arr[n]='B';
                    chance=!chance;
                    continue;
                }
                else if (arr[i - 1] == arr[i + 1]){
                    if (arr[i-1]=='B')
                        arr[i]='C';
                    else
                        arr[i]='B';
                    chance=!chance;
                }
                else if (arr[i] == arr[i + 1]){
                    if (arr[i-1]=='B')
                        arr[i]='C';
                    else
                        arr[i]='B';
                    chance=!chance;
                }
                System.out.println(chance?"Player1":"Player2");
            }

        }
        System.out.println(Arrays.toString(arr));
        System.out.println(!chance?"Player1":"Player2");
    }
    public static int initPosition(int[] arr,int finalpos){
        int prevPos=finalpos;
        for (int i = arr.length-1; i >=0; i--) {
            prevPos=prevPos/arr[i];
        }
        return prevPos;
    }
    public static void  harryProf(int[] arr){
        int harry=0;
        int prof=0;
        for (int i = 0; i < arr.length; i++) {
            if (i%2==0)
                prof+=arr[i];
            else
                harry+=arr[i];
        }
        System.out.println((harry>prof)?"Harry":"Professor");
    }
}
