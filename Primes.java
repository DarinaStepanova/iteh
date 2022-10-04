public class Primes{
    //печать для диапазона
    public static void main(String[] args) {
        for (int i = 2; i < 101; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }
    //функция: является ли число простым
    public static boolean isPrime(int n) {
        for(int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}