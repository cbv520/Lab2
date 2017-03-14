public class TowersOfHanoi
{
    public static void main(String[] args)
    {
        towers(3, 1, 3);
    }

    public static void towers(int n, int src, int dest)
    {
        if(n == 1)
        {
            moveDisk(src, dest);
        }
        else
        {
            int tmp = 6 - src - dest;
            towers(n - 1, src, tmp);
            moveDisk(src, dest);
            towers(n - 1, tmp, dest);
        }
    }

    public static void moveDisk(int src, int dest)
    {
        System.out.println("Move disk from " + src + " to "+ dest);
    }

}
