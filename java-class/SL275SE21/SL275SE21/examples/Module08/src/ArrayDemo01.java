public class ArrayDemo01 {
    public static void main(String[] args) {
        int[] primes;
        // int primes[];
        primes = new int[4];
        primes[1] = 3;
        primes[2] = 7;
        primes[0] = primes[1] - 1;
        primes[4] = 2;

        int[] primes02;
        primes02 = new int[]{2,3,5};

        int[] primes03 = {2,3,5};
    }
}
