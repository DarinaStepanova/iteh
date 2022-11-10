import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        System.out.println("Repeater:");
        System.out.println("\"mice\"" + " " + repeat("mice", 5)); //mmmmmiiiiiccccceeeee
        System.out.println("\"hello\"" + " " + repeat("hello", 3)); //hhheeellllllooo
        System.out.println("\"stop\"" + " " + repeat("stop", 1)); //stop
        System.out.println(" ");

        System.out.println("Difference between Max and Min:");
        System.out.println("10, 4, 1, 4, -10, -50, 32, 21:  " + differenceMaxMin(new int[]{10, 4, 1, 4, -10, -50, 32, 21})); //82
        System.out.println("44, 32, 86, 19:  " + differenceMaxMin(new int[]{44, 32, 86, 19})); //67
        System.out.println(" ");

        System.out.println("Is Avg Whole?");
        System.out.println( "1, 3 - "+ isAvgWhole(new int[]{1, 3})); //true
        System.out.println("1, 2, 3, 4 - " + isAvgWhole(new int[]{1, 2, 3, 4})); //false
        System.out.println("1, 5, 6 - " + isAvgWhole(new int[]{1, 5, 6}) ); //true
        System.out.println("1, 1, 1 - " + isAvgWhole(new int[]{1, 1, 1})); //true
        System.out.println("9, 2, 2, 5 - " + isAvgWhole(new int[]{9, 2, 2, 5})); //false
        System.out.println("5 - " + isAvgWhole(new int[]{5})); //true
        System.out.println(" ");

        System.out.println("Sums of previous numbers:");
        System.out.println("1, 2, 3 - " + cumulativeSum(new int[]{1, 2, 3})); //[1, 3, 6]
        System.out.println("1, -2, 3 - " + cumulativeSum(new int[]{1, -2, 3})); //[1, -1, 2]
        System.out.println("3, 3, -2, 408, 3, 3 - " + cumulativeSum(new int[]{3, 3, -2, 408, 3, 3}) ); //[3, 6, 4, 412, 415, 418]
        System.out.println(" ");

        System.out.println("How many decimal places?");
        System.out.println("\"43.20\" - " + getDecimalPlaces("43.20")); //2
        System.out.println("\"400\" - " + getDecimalPlaces("400")); //0
        System.out.println("\"3.1\" - " + getDecimalPlaces("3.1")); //1
        System.out.println(" ");

        System.out.println("Fibonacci:");
        System.out.println("3 - " + fibonacci(3)); //3
        System.out.println("7 - " + fibonacci(7)); //21
        System.out.println("12 - " + fibonacci(12)); //233
        System.out.println(" ");

        System.out.println("Is it a postal code?");
        System.out.println("\"59001\" - " + isValid("59001")); //true
        System.out.println("\"853a7\" - " + isValid("853a7")); //false
        System.out.println("\"732 32\" - " + isValid("732 32")); //false
        System.out.println("\"393939\" - " + isValid("393939")); //false
        System.out.println(" ");

        System.out.println("Is it a strange pair?");
        System.out.println("\"ratio\", \"orator\" " + isStrangePair("ratio", "orator")); //true
        System.out.println("\"sparkling\", \"groups\" " + isStrangePair("sparkling", "groups")); //true
        System.out.println("\"bush\", \"hubris\" " + isStrangePair("bush", "hubris")); //false
        System.out.println("\"\", \"\" " + isStrangePair("", "")); //true
        System.out.println(" ");

        System.out.println("Is it a suffix or prefix?");
        System.out.println("\"automation\", \"auto-\" " + isPrefix("automation", "auto-")); //true
        System.out.println("\"arachnophobia\", \"-phobia\" " + isSuffix("arachnophobia", "-phobia")); //true
        System.out.println("\"retrospect\", \"sub-\" " + isPrefix("retrospect", "sub-")); //false
        System.out.println("\"vocation\", \"-logy\" " + isSuffix("vocation", "-logy")); //false
        System.out.println(" ");

        System.out.println("Count of boxes:");
        System.out.println(boxSeq(0)); //0
        System.out.println(boxSeq(1)); //3
        System.out.println(boxSeq(2)); //2
        System.out.println(boxSeq(5)); //7
        System.out.println(" ");
    }
    public static String repeat(String word, int n) { // 1. Функция, которая повторяет каждый символ в строке n раз.
        System.out.print(word + " with " + n + " repetitions is ");
        String repeated = ""; //строка для повторений
        for (int i = 0; i < word.length(); i++) {
            String tempStr = ""; //временная переменная для каждого символа
            tempStr += word.charAt(i);
            repeated += tempStr.repeat(n);
        }
        return repeated; //строка с повторениями
    }
    public static int differenceMaxMin(int[] myArray) { // 2. Функция, которая принимает массив и возвращает разницу между самыми большими и самыми маленькими числами.
        int maxNum = myArray[0]; //присваиваем максимуму и минимуму первое значение массива по умолчанию
        int minNum = myArray[0];
        for (int j = 1; j < myArray.length; j++) {
            if (maxNum < myArray[j]) { //если следующее число массива больше числа в переменной, то присваиваем это значение ей
                maxNum = myArray[j];
            }
            if (minNum > myArray[j]) { //если следующее число массива меньше числа в переменной, то присваиваем это значение ей
                minNum = myArray[j];
            }
        }
        return maxNum-minNum; //разность
    }

    public static boolean isAvgWhole(int[] myArray) { // 3. Функция, которая принимает массив в качестве аргумента и возвращает true или false в зависимости от того, является ли среднее значение всех элементов массива целым числом или нет.
        double sum = 0;
        for (int i = 0; i < myArray.length; i++) {
            sum += myArray[i];
        }
//        double div = sum/myArray.length;
//        int a=0;
//        for (int j = 0; j < sum+1; j++) {
//            if (div == j) {
//                a=1;
//            }
//        }
        return (sum/myArray.length) % 1 == 0; //остаток от деления целого числа должен быть равен нулю
    }
    public static String cumulativeSum(int[] myArray) { // 4. Функция, которая  берет массив целых чисел и возвращает массив, в котором каждое целое число является суммой самого себя + всех предыдущих чисел в массиве.
        int[] sumA = new int[myArray.length];
        int sumOfPr = 0; //переменная для добавления значений изначального массива
        for (int i = 0; i < myArray.length; i++) {
            sumOfPr += myArray[i]; //прибавляем значение массива
            sumA[i] = sumOfPr; //вносим сумму в массив
        }
        return Arrays.toString(sumA); //возвращает полученный массив в адекватном для консоли виде
    }
    public static int getDecimalPlaces(String strNum) { // 5. Функция, которая возвращает число десятичных знаков, которое имеет число (заданное в виде строки). Любые нули после десятичной точки отсчитываются в сторону количества десятичных знаков.
        int indexOfPoint = strNum.indexOf('.') + strNum.indexOf(','); //индекс точки или запятой (если они есть) в итоге будет равен индекс-1, иначе -2.
        if (indexOfPoint != -2) {
            int countOfDecimalPlaces = 0;
            for (int i = indexOfPoint + 2; i < strNum.length(); i++) { //начинаем считать числа после знака
                countOfDecimalPlaces += 1;
            }
            return countOfDecimalPlaces; // количество десятичных знаков
        }
        else return 0;
    }
    public static int fibonacci(int number) { // 6. Функция, которая при заданном числе возвращает соответствующее число Фибоначчи
        int fib = 0; //переменная для хранения числа Фиббоначи
        int[] f = new int[number + 1]; //массив чисел Фиббоначи
        f[0] = 1;
        f[1] = 1;
        f[2] = 2;
        for (int i = 3; i < number + 1; ++i) {
            f[i] = f[i - 1] + f[i - 2];
            fib = f[i];
        }
        return fib; // возвращает число Фиббоначи с порядковым номером number
    }
    public static boolean isValid(String str) { // 7. Функция, чтобы определить, является ли строка действительным почтовым индексом.
        int digit = 1; //по умолчанию true
        if (str.length() == 5) { //меньше ли 5 символов в строке?
            for (int i = 0; i < str.length(); i++) {
                if (digit == 1) { //необходимо для прекращения проверки и переопределения после получения 0
                    int isNumeric = str.charAt(i); //находим ASCII символа
                    digit = ((isNumeric > 47) && (isNumeric < 58)) ? 1 : 0; //является ли символ числом?
                }
            }
        }
        else digit = 0;
        return digit == 1;
    }
    public static boolean isStrangePair(String strF, String strS) { // 8. Функция, которая которая возвращает true, если пара строк представляет собой странную пару, и false в противном случае.
        return (strF==""&&strS=="")||strF.charAt(0)==strS.charAt(strS.length()-1)&&strS.charAt(0)==strF.charAt(strF.length()-1); // проверка
    }
    public static boolean isPrefix(String word, String prefix) { // 9. isPrefix должен возвращать true, если он начинается с префиксного аргумента
        int isP = 0; //сюда будем прибавлять единицу, если буквы одинаковы
        for (int i = 0; i < prefix.length() - 1; i++) { //с первого символа строки по символ перед тире в префиксном аргументе
            for (int j = 0; j < prefix.length() - 1; j++) { //с первого символа префиксного аргумента по символ перед тире в нем
                isP += word.charAt(i) == prefix.charAt(j) ? 1 : 0;
            }
        }
        return isP == prefix.length() - 1; //сумма единиц должна быть равна количеству букв в префиксе
    }
    public static boolean isSuffix(String word, String suffix) { // isSuffix должен возвращать true, если он заканчивается аргументом суффикса.
        int isP = 0; //сюда будем прибавлять единицу, если буквы одинаковы
        for (int i = word.length() - 1; i > word.length() - suffix.length(); i--) { //с последнего символа строки по символ после тире в префиксном аргументе
            for (int j = suffix.length() - 1; j > 0; j--) { //с последнего символа префиксного аргумента по символ после тире в нем
                isP += word.charAt(i) == suffix.charAt(j) ? 1 : 0;
            }
        }
        return isP == suffix.length() - 1; //сумма единиц должна быть равна количеству букв в суффиксе
    }
    public static int boxSeq(int step) { // 10. Функция, которая принимает число (шаг) в качестве аргумента и возвращает количество полей на этом шаге последовательности.
        int boxes = 0; //на нулевом шаге 0 полей
        for (int i = 1; i < step + 1; i++) { //на четном шаге вычитаем 1
            if (i % 2 == 0) {
                boxes -= 1;
            }
            else { //на нечетном шаге прибавляем 3
                boxes += 3;
            }
        }
        return boxes; //количество полей
    }
}
