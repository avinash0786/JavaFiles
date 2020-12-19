public class convmethod
{
    public static void main(String ...a)
    {
        double[] art=new double[]{1.0,2.0,3.5,5.6,9.5,4.5,66.5};

        //System.out.println(returna(art));
        for(int aa:returna(art))
            System.out.println(aa);
        System.out.println(art);
    }
    public static int[] returna(double []arr)
    {
        int[] ar2=new int[arr.length];
        for (int i=0;i<arr.length;i++)
            {
                ar2[i]=(int)(arr[i]);
            }
        return ar2;
    }
}
