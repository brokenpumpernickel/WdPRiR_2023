public class Helpers {
    public static boolean isPrime(int i) {
        for(int j = 2; j < i; ++j)
            if(i % j == 0)
                return false;
        return true;
    }
}
