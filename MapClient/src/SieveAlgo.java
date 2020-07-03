import java.util.ArrayList;

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
        int primeDivs = (int) Math.sqrt(2*num);
        boolean[] Sieve = EratospheneSieve(primeDivs);
        ArrayList<Integer> primes= Sieve2Primes(Sieve);

        for(int t = num; t < 2*num; t++)
        {
            for (int i = k; i <= primes.size(); i++)
                if(t % i == 0) { return false; }
        }
        return true;
//        for(int i = 2; i <= Math.sqrt(num); i ++)
//        {
//            if(num % i == 0) { return false; }
//        }
//        return true;

    }

    private static  boolean[] EratospheneSieve(int num) {
        boolean[] sieve = new boolean[num];
        int k = 2;
        while(k < num) {
            while (sieve[k]) k++;

            int i=k+k;
            if (i>num) break;
            while(i<num) {
                sieve[i] = true;
                i += k;
            }
            k++;
        }
        return sieve;
    }
    private static ArrayList<Integer> Sieve2Primes(boolean[] sieve)
    {
        ArrayList<Integer> primes= new ArrayList<>();
        for(int i=2;i<sieve.length;i++)
            if(!sieve[i]) primes.add(i);
        return primes;
    }
}
