import java.util.Arrays;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        System.out.println(bell(1)); //1
        System.out.println(bell(2)); //2
        System.out.println(bell(3)); //5
        System.out.println("  ");

        System.out.println(translateWord("flag")); //"agflay"
        System.out.println(translateWord("Apple")); //"Appleyay"
        System.out.println(translateWord("button")); //"uttonbay"
        System.out.println(translateWord("")); //""
        System.out.println(translateSentence("I like to eat honey waffles.")); //"Iyay ikelay otay eatyay oneyhay afflesway."
        System.out.println(translateSentence("Do you think it is going to rain today?")); //"Oday youyay inkthay ityay isyay oinggay otay ainray odaytay?"
        System.out.println(translateSentence("She said: \"I really like it!\""));
        System.out.println("  ");

        System.out.println(validColor("rgb(0,0,0)")); //true
        System.out.println(validColor("rgb(0,,0)")); //false
        System.out.println(validColor("rgb(255,256,255)")); //false
        System.out.println(validColor("rgba(0,0,0,0.123456789)")); //true
        System.out.println("  ");

        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2")); //"https://edabit.com?a=2&b=2"
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{"b"})); //"https://edabit.com?a=2"
        System.out.println(stripUrlParams("https://edabit.com", new String[]{"b"})); //"https://edabit.com"
        System.out.println("  ");

        System.out.println(getHashTags("How the Avocado Became the Fruit of the Global Trade")); //["#avocado", "#became", "#global"]
        System.out.println(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year")); //["#christmas", "#probably", "#will"]
        System.out.println(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit")); //["#surprise", "#parents", "#fruit"]
        System.out.println(getHashTags("Visualizing Science")); //["#visualizing", "#science"]
        System.out.println("  ");

        System.out.println(ulam(4)); //4
        System.out.println(ulam(9)); //16
        System.out.println(ulam(206)); //1856
        System.out.println("  ");

        System.out.println(longestNonrepeatingSubstring("abcabcbb")); //abc
        System.out.println(longestNonrepeatingSubstring("aaaaaa")); //a
        System.out.println(longestNonrepeatingSubstring("abcde")); //abcde
        System.out.println(longestNonrepeatingSubstring("abcda")); //abcd
        System.out.println("  ");

        System.out.println(convertToRoman(2)); //"II"
        System.out.println(convertToRoman(12)); //"XII"
        System.out.println(convertToRoman(16)); //"XVI"
        System.out.println("  ");

        System.out.println(formula("6 * 4 = 24")); //true
        System.out.println(formula("18 / 17 = 2")); //false
        System.out.println(formula("16 * 10 = 160 = 14 + 120")); //false
        System.out.println("  ");

        System.out.println(palindromedescendant(11211230)); //true
        System.out.println(palindromedescendant(13001120)); //true
        System.out.println(palindromedescendant(23336014)); //true
        System.out.println(palindromedescendant(11)); //true
        System.out.println("  ");
    }
    public static int bell(int n) { // 1. Функция, которая принимает числа в качестве аргументов, складывает их вместе и возвращает произведение цифр до тех пор, пока ответ не станет длиной всего в 1 цифру.
        if (n == 0 || n == 1) return 1; // 0 или 1 - > 1
        int[][] bella = new int[n + 1][n + 1]; //двумерный массив для хранения треугольника чисел белла
        bella[0][0] = 1; //треугольник начинается с 1
        // треугольник чисел Белла:
        // первый элемент новой строки равен последнему элементу предыдущей строки,
        // а следующий равен сумме двух чисел: перед и над ним:
        // (столбец i и строка j)
                 //      1
                 //    1   2
                 //   2   3  5
                 //  5  7  10 15
                 //15 20 27 37  52 и т.д.
        //для нашего массива:
        //1
        //1  2
        //2  3  5
        //5  7  10 15
        //15 20 27 37 52 и т.д.
        //номер строки = n, исключая ноль
        //последнее значение - число Белла для этой строки = первое значение следующей строки
        for (int i = 1; i <= n; i++) {
            bella[i][0] = bella[i-1][i-1]; //Белла для предыдущей строки
            for (int j = 1; j <= i; j++) { //количество столбцов в строке = номер строки
                bella[i][j] = bella[i-1][j-1] + bella[i][j-1];
            }
        }
        return bella[n][0]; //ответ - первая строчка лишней строки
    }
    public static String translateWord(String word) { // 2. Функция как переводчик с английского на свинский латинский.
        int up = (word.length() !=0 && word.charAt(0) > 64 && word.charAt(0) < 91) ? 1 : 0;
        String wordN = word.toLowerCase();
        String vowels = "aeiouy"; //гласные
        StringBuilder beforeV = new StringBuilder();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < wordN.length(); i++) { //поочередно проверим буквы
            if (vowels.contains(String.valueOf(wordN.charAt(i)))) { //если строка гласных содержит символ слова
                beforeV.append(wordN.charAt(i)); //добавляем в строку гласных слова
                break;
            }
        }
        if (wordN.indexOf(String.valueOf(beforeV)) > 0) {
            if (up == 1) {
                char m = (char)(wordN.substring(wordN.indexOf(String.valueOf(beforeV))).charAt(0) - 32);
                s.append(m);
                s.append(wordN.substring(wordN.indexOf(String.valueOf(beforeV)) + 1));
                s.append(wordN.substring(0, wordN.indexOf(String.valueOf(beforeV))));
                s.append("ay");
            }
            else {
                s.append(wordN.substring(wordN.indexOf(String.valueOf(beforeV))));
                s.append(wordN.substring(0, wordN.indexOf(String.valueOf(beforeV))));
                s.append("ay");
            }
        }
        if (wordN.indexOf(String.valueOf(beforeV)) == 0 && beforeV.length() != 0){
            s.append(word);
            s.append("yay");
        }
        if (beforeV.length() == 0) return word;
        return s.toString();
    }
    public static String translateSentence(String sentence) { // 2. Функция как переводчик с английского на свинский латинский.
        String points = ".,;?!:\"";
        String[] sen = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < sen.length; i++) {
            if (points.contains(String.valueOf(sen[i].charAt(0))) && points.contains(String.valueOf(sen[i].charAt(sen[i].length()-1)))) {
                result.append(sen[i].charAt(0));
                String wordWithoutPoints = sen[i].substring(1, sen[i].length()-1);
                if (points.contains(String.valueOf(wordWithoutPoints.charAt(wordWithoutPoints.length()-1)))) {
                    String newWordWithoutPoints = wordWithoutPoints.substring(0, wordWithoutPoints.length()-1);
                    result.append(translateWord(newWordWithoutPoints));
                    result.append(wordWithoutPoints.charAt(wordWithoutPoints.length() - 1));
                }
                else result.append(translateWord(wordWithoutPoints));
                result.append(sen[i].charAt(sen[i].length() - 1));
                result.append(" ");
            }
            else if (points.contains(String.valueOf(sen[i].charAt(sen[i].length()-1)))) {
                String wordWithoutPoints = sen[i].substring(0, sen[i].length()-1);
                if (points.contains(String.valueOf(wordWithoutPoints.charAt(wordWithoutPoints.length()-1)))) {
                    String newWordWithoutPoints = wordWithoutPoints.substring(0, wordWithoutPoints.length()-1);
                    result.append(translateWord(newWordWithoutPoints));
                    result.append(wordWithoutPoints.charAt(wordWithoutPoints.length() - 1));
                }
                else result.append(translateWord(wordWithoutPoints));
                result.append(sen[i].charAt(sen[i].length() - 1));
                result.append(" ");
            }
            else if (points.contains(String.valueOf(sen[i].charAt(0)))) {
                result.append(sen[i].charAt(0));
                String wordWithoutPoints = sen[i].substring(1);
                result.append(translateWord(wordWithoutPoints));
                result.append(" ");
            }
            else {
                result.append(translateWord(sen[i]));
                result.append(" ");
            }
        }
        return result.toString();
    }
    public static boolean validColor(String color) { // 3. Функция, которая принимает строку (например, "rgb(0, 0, 0)") и возвращает true, если ее формат правильный, в противном случае возвращает false.
        if (!color.contains("rgb") && !color.contains("rgba")) return false; //проверка: содержит ли строка "rgb" или "rgba"
        boolean a = false;
        //если содержит - проверяем и убираем скобки и имя
        if (color.contains("rgba")) {
            a = true;
            if (color.charAt(4) == '(' && color.charAt(color.length() - 1) == ')') color = color.substring(5, color.length() - 1);
            else return false;
        }
        else {
            if (color.charAt(3) == '(' && color.charAt(color.length() - 1) == ')') color = color.substring(4, color.length() - 1);
            else return false;
        }
        String[] numbers = color.split(","); //разделим числа цвета на массив
        if (!a && numbers.length != 3 || a && numbers.length != 4) return false; //количество чисел должно соответствовать формату
        for (int i = 0; i < 3; i++) {
            try {
                int intValue = Integer.parseInt(numbers[i]);
                if (intValue > 255 || intValue < 0) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }

        }
        if (numbers.length == 4) {
            try {
                double intValue = Double.parseDouble(numbers[3]);
                if (intValue > 1 || intValue < 0) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }

        }
        return true;
    }
    public static String stripUrlParams(String url, String[]... paramsToStrip) { // 4. Функция, которая принимает URL (строку), удаляет дублирующиеся параметры запроса и параметры, указанные во втором аргументе (который будет необязательным массивом).
        int indexOfQ = url.indexOf('?'); //начало параметров
        if (indexOfQ == -1) return url;
        StringBuilder newUrl = new StringBuilder();
        newUrl.append(url.substring(0, indexOfQ + 1));
        String[] params = url.substring(indexOfQ + 1).split("&"); //получаем все параметры, разбивая оставшуюся часть строки по "и" для параметров
        for (int i = 0; i < params.length; i++) {
            if (!newUrl.toString().contains(params[i].substring(0, params[i].indexOf("=") + 1)) && !Arrays.deepToString(paramsToStrip).contains(params[i].substring(0, params[i].indexOf("=")))) {
                for (int j = 0; j < params.length; j++) {
                    if (params[i].substring(0, params[i].indexOf("=")).equals(params[j].substring(0, params[j].indexOf("=")))) {
                        params[i] = params[j];
                    }
                }
                newUrl.append(params[i]);
                newUrl.append("&");
            }
        }
        String result = newUrl.toString();
        if (result.charAt(newUrl.length() - 1) == '&') result = result.substring(0, newUrl.length() - 1);
        return result;
    }
    public static String getHashTags(String post) { // 5. Функция, которая извлекает три самых длинных слова из заголовка газеты и преобразует их в хэштеги. Если несколько слов одинаковой длины, найдите слово, которое встречается первым.
        String[] words = post.toLowerCase().split("[ .,!&'\";:<>(){}]");
        int count = words.length > 3 ? 3 : words.length;
        String[] sorted = new String[count];
        for (int i = 0; i < count; i++) {
            for (int m = 0; m < words.length; m++) {
                for (int j = 0; j < words.length; j++) {
                    if (j < words.length - 1 && words[j + 1].length() > words[j].length()) {
                        String temp = words[j + 1];
                        words[j + 1] = words[j];
                        words[j] = temp;
                    }
                }
            }
            sorted[i] = "#" + words[i];
        }
        return Arrays.toString(sorted);
    }
    public static int ulam(int n) { // 6. Функция, которая принимает число n и возвращает n-е число в последовательности Улама
        //если число меньше трёх, то просто возвращаем очевидное значение
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] ulams = new int[n]; //массив для хранения чисел Улама
        ulams[0] = 1;
        ulams[1] = 2;
        int ulamSeqIdx = 2;
        int ulamNum = 3;
        //Так как сумма всегда возрастает от числа к числу, то мы перебираем последовательно все числа от 3 до n.
        //Итерируем в цикле пока номер числа улана не будет n.
        while(ulamSeqIdx < n){
            //Считаем сколькими способами можно представить СЧУ.
            int counter = 0;
            for (int i = 0; i < ulamSeqIdx; i++) {
                for (int j = i + 1; j < ulamSeqIdx; j++){
                    if (ulams[i] + ulams[j] == ulamNum) counter++;
                }
            }
            //Если способ один, то это число улама, записываем число в массив и увеличиваем индекс.
            if(counter == 1){
                ulams[ulamSeqIdx] = ulamNum;
                ulamSeqIdx++;
            }
            //Увеличиваем СЧУ.
            ulamNum++;
        }
        return ulams[n - 1];
//        int counter = 0;
////        while (index + 1 < n) {
//            for (int i = 0; i < n; i++) {
//                for (int j = i + 1; j < index + 1; j++) {
//                    if (ulams[i] + ulams[j] == j + 2) {
//                        counter++;
//                        index = j + 1;
//                    }
//                }
//            }
////            if (counter == 1) {
////                ulams[index] = index;
////            }
////        }
    }
    public static String longestNonrepeatingSubstring(String string) { // 7. Функция, которая возвращает самую длинную неповторяющуюся подстроку для строкового ввода.
        StringBuilder s = new StringBuilder(); //временная строка для добавления символов до повторяющегося
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length(); i++) { //для добавления символов до повторяющегося
            if (!s.toString().contains(String.valueOf(string.charAt(i)))) {
                s.append(string.charAt(i));
            }
            else { //после повтора
                result.append(s); //записать в строку всех индив
                s = new StringBuilder(String.valueOf(string.charAt(i))); //во временную - новый(старый) символ
                result.append("\n");//раздел
            }
            if (i == string.length() - 1) result.append(s);
        }
        String[] words = result.toString().split("\n"); //массив слов
        String[] sorted = new String[words.length]; //сортировка по длине
        for (int i = 0; i < 1; i++) {
            for (int m = 0; m < words.length; m++) {
                for (int j = 0; j < words.length; j++) {
                    if (j < words.length - 1 && words[j + 1].length() > words[j].length()) {
                        String temp = words[j + 1];
                        words[j + 1] = words[j];
                        words[j] = temp;
                    }
                }
            }
            sorted[i] = words[i];
        }
        return sorted[0];
    }
    public static String convertToRoman(int num) { // 8. Функция, которая принимает арабское число и преобразует его в римское
        StringBuilder result = new StringBuilder();
        String[] ones = {"","I","II","III","IV","V","VI","VII","VIII","IX"};
        String[] tens = {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
        String[] hunds = {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
        String[] thous = {"","M","MM","MMM"};
        if (num >= 1000) {
            result.append(thous[num / 1000]);
            num = num % 1000;
        }
        if (num >= 100) {
            result.append(hunds[num / 100]);
            num = num % 100;
        }
        if (num >= 10) {
            result.append(tens[num / 10]);
            num = num % 10;
        }
        if (num >= 0) {
            result.append(ones[num]);
        }
        return result.toString();
    }
    //Реверс строки
    public static String reverseString(String strings) {
        StringBuilder reverse = new StringBuilder();
        for (int i = strings.length()-1; i >= 0; i--) {
            reverse.append(strings.charAt(i));
        }
        return reverse.toString();
    }
    //Палиндром?
    public static boolean isPalindrome(String s) {
        return (s.equals(reverseString(s)));
    }

    public static double actions(int n1, int n2, char character) {
        if (character == '+') return n1+n2;
        if (character == '-') return n1-n2;
        if (character == '*') return n1*n2;
        if (character == '/') return n1/n2;
        return character;
    }
    private static int counter(String str, char ch) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == ch) {
                counter++;
            }
        }
        return counter;
    }
    public static boolean formula(String s) {
        int l = 0;
        String[] actToLeft = s.split(" = ");
        double[] result = new double[counter(s, '=') + 1];
        for (int n = 0; n < actToLeft.length; n++) {
            String[] elem = actToLeft[n].split(" ");
            if (elem.length == 1) result[n] = Double.parseDouble(elem[0]);
            else {
                for (int i = 0; i < 3; i+=3) {
                    result[n] = actions(Integer.parseInt(elem[i]), Integer.parseInt(elem[i + 2]), elem[i + 1].charAt(0));
                }
            }
            if (n > 0) {
                if (result[n] == result[n - 1]) l = 1;
                else return false;
            }
        }
        return l == 1;
    }
    public static boolean palindromedescendant(int num) {
        String numeric = String.valueOf(num);
        while (numeric.length() > 1) {
            StringBuilder temp = new StringBuilder();
            //проверка на палиндром сразу же
            if (isPalindrome(numeric)) return true;
            for(int i = 0; i < numeric.length() / 2; i += 1) {
                int left = Character.getNumericValue(numeric.charAt(i * 2));
                int right = Character.getNumericValue(numeric.charAt(i * 2 + 1));
                int c = left + right;
                temp.append(c);
            }
            numeric = temp.toString(); //переносим получившуюся строку в оаюочее
            temp = new StringBuilder(); //очистка
        }
        return false;
    }
}
