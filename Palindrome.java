public class Palindrome {
    //Точка входа в программу. Перебор массива
    public static void main (String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            System.out.print(s + " ");
            System.out.println(isPalindrome(s));
        }
    }

    //Реверс строки
    public static String reverseString(String strings) {
        String reverse = "";
        for (int i = 0; i < strings.length(); i++) {
            reverse += strings.charAt(strings.length()-i -1);
        }
        return reverse;
    }

    //Палиндром?
    public static boolean isPalindrome(String s) {
        return (s.equals(reverseString(s)));
    }
}