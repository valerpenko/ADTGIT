public class SieveAlgo
{
    public static void main(String[] args)
    {
//        int[]array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//
//        for(int number:array)
//            System.out.println(findPrime(number));

        findPrimes(10);
    }

    public static boolean findPrimes(int num)
    {
        int k = 2;
        boolean[] Sieve = new boolean[num];

        while(k < num)
            while(!Sieve[k]) k++;
            for(int i = k; i < num; i += k)
                Sieve[i] = false;
            k++;

        double primeDivs = Math.sqrt(num);
        for(int t = num; t < 2*num; t++)
        {
            for (int i = k; i <= primeDivs; i++)
                if(num % i == 0) { return false; }
        }
        return true;
//        for(int i = 2; i <= Math.sqrt(num); i ++)
//        {
//            if(num % i == 0) { return false; }
//        }
//        return true;

    }
}
