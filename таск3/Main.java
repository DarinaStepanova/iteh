import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Solutions");
        System.out.println(solutions(1, 0, -1)); // 2
// x² - 1 = 0 has two solutions (x = 1 and x = -1).
        System.out.println(solutions(1, 0, 1)); // 0
// x² + 1 = 0 has no solutions.
        System.out.println(solutions(1, 0, 0)); // 1
// x² = 0 has one solution (x = 0).
        System.out.println(" ");

        System.out.println("The second index of zip: ");
        System.out.println(findZip("all zip files are zipped")); //18
        System.out.println(findZip("all zip files are compressed")); //-1
        System.out.println(" ");

        System.out.println("Is number a perfect?");
        System.out.println(checkPerfect(6)); //true
        System.out.println(checkPerfect(28)); //true
        System.out.println(checkPerfect(496)); //true
        System.out.println(checkPerfect(12)); //false
        System.out.println(checkPerfect(97)); //false
        System.out.println(" ");

        System.out.println("Permutation of the first and last characters");
        System.out.println(flipEndChars("Cat, dog, and mouse.")); //".at, dog, and mouseC"
        System.out.println(flipEndChars("ada")); //"Two's a pair."
        System.out.println(flipEndChars("Ada")); //"adA"
        System.out.println(flipEndChars("z")); //"Incompatible."
        System.out.println(" ");

        System.out.println("Permutation of the first and last characters");
        System.out.println(isValidHexCode("#CD5C5C")); //true
        System.out.println(isValidHexCode("#EAECEE")); //true
        System.out.println(isValidHexCode("#eaecee")); //true
        System.out.println(isValidHexCode("#CD5C58C")); //false
        System.out.println(isValidHexCode("#CD5C5Z")); //false
        System.out.println(isValidHexCode("#CD5C&C")); //false
        System.out.println(isValidHexCode("CD5C5C")); //false
        System.out.println(" ");

        System.out.println("Are they the same?");
        System.out.println("{1, 3, 4, 4, 4} - {2, 5, 7} " + same(new int[]{1, 3, 4, 4, 4}, new int[]{2, 5, 7})); //true
        System.out.println("{9, 8 , 7, 6} - {4, 4, 3, 1} " + same(new int[]{9, 8 , 7, 6}, new int[]{4, 4, 3, 1})); //false
        System.out.println("{2} - {3, 3, 3, 3, 3} " + same(new int[]{2}, new int[] {3, 3, 3, 3, 3})); //true
        System.out.println(" ");

        System.out.println("Is it a Kaprekar?");
        System.out.println("3 " + isKaprekar(3)); //false
        System.out.println("5 " + isKaprekar(5)); //false
        System.out.println("297 " + isKaprekar(297)); //true
        System.out.println("0 " + isKaprekar(1)); //true
        System.out.println(" ");

        System.out.println("Longest zero");
        System.out.println(longestZero("01100001011000")); //"0000"
        System.out.println(longestZero("100100100")); //"00"
        System.out.println(longestZero("11111")); //""
        System.out.println(" ");

        System.out.println("The next Prime is?");
        System.out.println("12 " + nextPrime(12)); //13
        System.out.println("24 " + nextPrime(24)); //29
        System.out.println("11 " + nextPrime(11)); //11
        System.out.println(" ");

        System.out.println("Is it a right triangle?");
        System.out.println(rightTriangle(3, 4, 5)); //true
        System.out.println(rightTriangle(145, 105, 100)); //true
        System.out.println(rightTriangle(70, 130, 110)); //false
        System.out.println(" ");
    }
    public static int solutions(double a, double b, double c) { // 1. Функция, возвращающая число решений квадратного уравнения
        System.out.print("The count of solutions for "+ a +"x^2 + " + b + "x + " + c + " is ");
        double d = b * b - 4 * a * c;
        if (d >= 0) {
            if (d > 0) {
                return 2;
            }
            else return 1;
        }
        else return 0;
    }
    public static int findZip(String string) { // 2. Функция, которая возвращает позицию второго вхождения " zip " в строку, или -1, если оно не происходит по крайней мере дважды.
        System.out.println(string);
        return string.indexOf("zip", string.indexOf("zip") + 2);
    }
    public static boolean checkPerfect(int num) { // 3. Функция, которая проверяет, является ли целое число совершенным числом или нет. Совершенное число - это число, которое можно записать как сумму его множителей, исключая само число.
        System.out.print(num + " ");
        int tr = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0 ) {
                tr += i;
            }
        }
        return tr == num;
    }
    public static String flipEndChars(String str) { // 4. Функция, которая принимает строку и возвращает новую строку с заменой ее первого и последнего символов
        System.out.println(str + " with a reverses:");
        if (str.length() > 1) {
            if (str.charAt(0) == str.charAt(str.length() - 1)) {
                return "Two's a pair.";
            }
            return str.charAt(str.length() - 1) + str.substring(1, str.length() - 1) + str.charAt(0);
        }
        else return "Incompatible.";
    }
    public static boolean isValidHexCode(String strH) { // 5. Функция, которая определяет, является ли строка допустимым шестнадцатеричным кодом.
        System.out.print(strH + " - ");
        int is = 0;
        if ((strH.length() == 7) && (strH.indexOf("#") == 0)) {
            for (int i = 1; i < 7; i++) {
                if (strH.charAt(i) > 47 && strH.charAt(i) < 58 || strH.charAt(i) > 64 && strH.charAt(i) < 71 || strH.charAt(i) > 96 && strH.charAt(i) < 103) {
                    is = 1;
                }
                else return false;
            }
        }
        return is == 1;
    }
    public static boolean same(int[] firstArray, int[] secondArray) { // 6. Функция, которая возвращает true, если два массива имеют одинаковое количество уникальных элементов, и false в противном случае.
        int countF = 0;
        int countS = 0;
        Arrays.sort(firstArray);
        Arrays.sort(secondArray);
        for (int i = 1; i < firstArray.length; i++) {
            if (firstArray[i-1] != firstArray[i]) {
                countF++;
            }
        }
        for (int j = 1; j < secondArray.length; j++) {
            if (secondArray[j-1] != secondArray[j]) {
                countS++;
            }
        }
        return countF == countS;
    }
    public static boolean isKaprekar(int n) { // 7. Функция, которая возвращает true, если это число Капрекара, и false, если это не так.
        String s = "" + n*n;
        StringBuilder sLeft = new StringBuilder();
        StringBuilder sRight = new StringBuilder();
        if (s.length() > 1) {
            for (int i = 0; i < s.length()/2; i++)
                sLeft.append(s.charAt(i));
            for (int j = s.length()/2; j < s.length(); j++) {
                sRight.append(s.charAt(j));
            }
        }
        if (s.length() == 1) {
            sLeft = new StringBuilder("0");
            sRight = new StringBuilder(s);
        }
        return Integer.parseInt(sLeft.toString())+Integer.parseInt(sRight.toString()) == n;
    }
    public static String longestZero(String bin) { // 8. Функция, которая возвращает самую длинную последовательность последовательных нулей в двоичной строке.
        System.out.print(bin + " - ");
        String[] zeroes = bin.split("1");
        Arrays.sort(zeroes);
        if (zeroes.length == 0) {
            return "";
        }
        return zeroes[zeroes.length-1];
    }
    public static int nextPrime(int number) { // 9. Функция, которая возвращает следующее простое число. Если число простое, верните само число. в двоичной строке.
        if (number % 2 == 0) {
            number++;
        }
        for (int i = 3; i < number / 2 + 1; i+=2) {
            if (number % i == 0 || number % 2 == 0) {
                number++;
                i = 1;
            }
        }
        return number;
    }
    public static boolean rightTriangle(int x, int y, int z) { // 10. Функция, которая возвращает следующее простое число. Если число простое, верните само число. в двоичной строке.
        System.out.print(x + " " + y + " " + z + " - ");
        return (z*z == x*x + y*y) || (y*y == z*z + x*x) || (x*x == y*y + z*z);
    }
}
