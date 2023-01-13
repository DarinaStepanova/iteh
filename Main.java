import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println("Essay: ");
        System.out.println(printEssay(10, 7, "hello my name is Bessie and this is my essay"));
//        hello my
//        name is
//        Bessie
//        and this
//        is my
//        essay
        System.out.println(" ");

        System.out.println("Spliter ");
        System.out.println(split("()()()")); //["()", "()", "()"]
        System.out.println(split("((()))")); //["((()))"]
        System.out.println(split("((()))(())()()(()())")); //["((()))", "(())", "()", "()", "(()())"]
        System.out.println(split("((())())(()(()()))")); //["((())())", "(()(()()))"]
        System.out.println(" ");

        System.out.println("String to new format string");
        System.out.println(toCamelCase("hello_edabit")); //"helloEdabit"
        System.out.println(toSnakeCase("helloEdabit")); //"hello_edabit"
        System.out.println(toCamelCase("is_modal_open")); //"isModalOpen"
        System.out.println(toSnakeCase("getColor")); //"get_color"
        System.out.println(" ");

        System.out.println("Salary counter");
        System.out.println("{9, 17, 30, 1.5} " + overTime(new double[]{9, 17, 30, 1.5})); //"$240.00"
        System.out.println("{16, 18, 30, 1.8} " + overTime(new double[]{16, 18, 30, 1.8})); //"$84.00"
        System.out.println("{13.25, 15, 30, 1.5} " + overTime(new double[]{13.25, 15, 30, 1.5})); //"$52.50"
        System.out.println(" ");

        System.out.println("BMI");
        System.out.println(BMI("205 pounds", "73 inches")); //"27.0 Overweight"
        System.out.println(BMI("55 kilos", "1.65 meters")); //"20.2 Normal weight"
        System.out.println(BMI("154 pounds", "2 meters")); //"17.5 Underweight"
        System.out.println(" ");

        System.out.println("Bugger");
        System.out.println("39 - " + bugger(39)); //3
        System.out.println("999 - " + bugger(999)); //4
        System.out.println("4 - " + bugger(4)); //0
        System.out.println(" ");

        System.out.println("To Star Shorthand");
        System.out.println(toStarShorthand("abbccc")); //"ab*2c*3"
        System.out.println(toStarShorthand("77777geff")); //"7*5gef*2"
        System.out.println(toStarShorthand("abc")); //"abc"
        System.out.println(toStarShorthand("")); //""
        System.out.println(" ");

        System.out.println("Rhymes");
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham.")); //true
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM.")); //true
        System.out.println(doesRhyme("You are off to the races", "a splendid day.")); //false
        System.out.println(doesRhyme("and frequently do?", "you gotta move.")); //false
        System.out.println(" ");

        System.out.println("Troubler");
        System.out.println("trouble(451999277, 41177722899) " + trouble(451999277, 41177722899L)); //true
        System.out.println("trouble(1222345, 12345) " + trouble(1222345, 12345)); //false
        System.out.println("trouble(666789, 12345667) " + trouble(666789, 12345667)); //true
          System.out.println("trouble(33789, 12345337) " + trouble(33789, 12345337)); //false
        System.out.println(" ");

        System.out.println("Counter of unique books");
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A')); //4
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", '$')); //3
        System.out.println(countUniqueBooks("ZZABCDEF", 'Z')); //0
        System.out.println(" ");
    }

    public static StringBuilder printEssay(int n, int k, String essay) { // 1. Функция, возвращающая эссе из n слов, в каждой строчке которого помещается k символов, не считая пробелы
        String[] words = essay.split(" "); //массив из переданных слов без пробелов
        StringBuilder text = new StringBuilder(); //строка с получившимся текстом
        int countOfWords = 0; //переменная для подсчета символов в строке
        for (int i = 0; i < n; i++) {
            if (countOfWords + words[i].length() <= k) { //если слово помещается, то
                text.append(words[i]).append(" "); //добавляем слово и пробел
                countOfWords += words[i].length(); //увеличиваем переменную для подсчета на количество символов слова
            }
            else { //если слово не помещается, то
                countOfWords = words[i].length(); //обнуляем счетчик и увеличиваем переменную для подсчета на количество символов слова
                text.append("\n"); //переходим на новую строчку
                text.append(words[i]).append(" "); //добавляем слово и пробел
            }
        }
        return text;
    }
    public static String split(String str) { // 2. Функция, которая группирует строку в кластер скобок. Каждый кластер должен быть сбалансирован
        int counter = 0; // считаем количество не закрытых скобочек
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 40) { //если скобочка открывающая, то количество не закрытых скобочек
                counter++; //увеличивается
                temp.append(str.charAt(i)); //добавляем в строчку символ
            }
            if (str.charAt(i) == 41) { //если скобочка заккрывающая, то количество не закрытых скобочек
                counter--; //уменьшается
                temp.append(str.charAt(i)); //добавляем в строчку символ
                if (counter == 0) { //если все скобочки закрыты
                    temp.append(' '); //ставим пробел и ищем скобки дальше
                }
            }
        }
        String[] newStr = temp.toString().split(" "); //делим строчку на группы скобок по пробелам
        return Arrays.toString(newStr);
    }
    public static String toCamelCase(String str){ // 3. Две функции toCamelCase () и toSnakeCase (), каждая из которых берет  одну строку и преобразует ее либо в camelCase, либо в snake_case
        StringBuilder cameles = new StringBuilder(); //строка с результатом
        int up = 0; //проверка на необходимость повышения следующей буквы
        for(int i = 0; i < str.length(); i++){
            if (str.charAt(i) != 95 && up == 0) { //если символ не является линией и не нуждается в повышении
                cameles.append(str.charAt(i)); //просто добавляем его в результат
            }
            if (up == 1) { //если надо повысить
                char s = (char) (str.charAt(i) - 32); //повышаем букву
                cameles.append(s); //и добавляем ее, а не первоначальную
                up = 0; //сбиваем счетчик
            }
            if (str.charAt(i) == 95) { //если символ есть линия
                up = 1; //следующий символ надо повысить
            }
        }
        return cameles.toString();
    }
    public static String toSnakeCase(String str){
        StringBuilder snakes = new StringBuilder(); //строка с результатом
        for(int i = 0; i < str.length(); i++){
            if (str.charAt(i) > 95) { //если символ не является заглавной буквой или не нуждается в дополнении _
                snakes.append(str.charAt(i)); //просто добавляем его в результат
            }
            if (str.charAt(i) < 95) { //если символ есть заглавная буква
                char s = (char) (str.charAt(i) + 32); //понижаем букву
                snakes.append("_"); //добавляем _
                snakes.append(s); //и добавляем новую букву, а не первоначальную
            }
        }
        return snakes.toString();
    }
    public static String overTime(double[] job) { // 4. Функция, которая вычисляет сверхурочную работу и оплату, связанную со сверхурочной работой.
        //job[0] - start
        //job[1] - end
        //job[2] - salary
        //job[3] - overtime salary
        if (job[1] <= 17) { //если работа окончена в 17 часов или раньше, то зарплата без надбавок
            double salary = (job[1] - job[0]) * job[2];
            return "$" + String.format("%.2f",salary);
        }
        if (job[0] >= 17) { //если работа начата после 17 часов, то зарплата считается с процентной надбавкой
            double salary = (job[1] - job[0]) * job[2] * job[3];
            return "$" + String.format("%.2f",salary);
        }
        else  {
            double salary = (17 - job[0]) * job[2] + (job[1] - 17) * job[2] * job[3]; // в общем случае (в пред. исклюсили отриц. значения)
            return "$" + String.format("%.2f",salary);
        }
    }
    public static String BMI(String m, String h) { // 5. Функция, которая будет принимать вес и рост (в килограммах, фунтах, метрах или дюймах) и возвращать ИМТ и связанную с ним категорию. Округлите ИМТ до ближайшей десятой.
        String[] kilos = m.split(" "); //массив, где 1 - масса, 2 - единица измерения
        String[] meters = h.split(" "); //массив, где 1 - рост, 2 - единица измерения
        double kg = Double.parseDouble(kilos[0]); //перевод строки в число для подсчетов
        double mtr = Double.parseDouble(meters[0]);
        if (Objects.equals(kilos[1], "pounds")) { //если в единице измерения kilos (m) фунты
            kg = kg * 0.45359237; //переводим в кг
        }
        if (Objects.equals(meters[1], "inches")) { //если в единице измерения meters (h) дюймы
            mtr = mtr * 25.39954 / 1000; //переводим в м
        }
        double i = Math.round((kg / mtr / mtr) * 10.0) / 10.0; //округление результата ИМТ до десятых для правильного сравнения
        if (i < 18.5) return i + " Underweight";
        if (i <= 24.9) return i + " Normal weight";
        else return i + " Overweight";
    }
    public static int bugger(int num) { // 6. Функция, которая принимает число и возвращает его мультипликативное постоянство, которое представляет собой количество раз, которое вы должны умножать цифры в num, пока не достигнете одной цифры.
        int counter = 0; //счетчик
        while (num > 9){ //пока число не является цифрой
            int digit = 1; //первоначальное значение для последующего умножения
            while (num > 0) { //пока результат от целочисленного деления больше ноля
                digit *= (num % 10); //произведение последней цифры получающегося с каждым повторением цикла числа num
                num /= 10; //избавление num от уже помноженной цифры
            }
            num = digit; //теперь num = произведение цифр первоначального
            counter++; //счетчик + 1
        }
        return counter;
    }
    public static String toStarShorthand(String string) { // 7. Функция, которая преобразует строку в звездную стенографию. Если символ повторяется n раз, преобразуйте его в символ*n.
        StringBuilder starString = new StringBuilder();
        int n = 1;
        for (int i = 0; i < string.length(); i++) {
            if (i != string.length() - 1) { //если взятый символ не являяется последним
                starString.append(string.charAt(i)); //добавить к результату
                if (string.charAt(i) == string.charAt(i + 1)) { //если он равен следующему
                    for (int j = i; j < string.length() - 1; j++) {
                        i = j; //для того, чтоб начать главный цикл со следующего символа после повторяющихся
                        if (string.charAt(j) == string.charAt(j + 1)) { //считаем повторяющиеся
                            n++; //прибавляем степень
                        } else j = string.length(); //завершаем цикл
                    }
                    starString.append("*"); //прибавляем звезду
                    starString.append(n); //добавляем количество повторяющихся
                    n = 1; //для нового подсчета
                }
            }
            else {
                if (string.charAt(i) != string.charAt(i - 1)) { //если наш символ не равен предыдущему, но является последним
                    starString.append(string.charAt(i)); //добавим его
                }
            }
        }
        return starString.toString();
    }
    public static boolean doesRhyme(String stringF, String stringS) { // 8. Функция, которая возвращает true, если две строки рифмуются, и false в противном случае. Для целей этого упражнения две строки рифмуются, если последнее слово из каждого предложения содержит одни и те же гласные.
        String vowels = "aeiouy"; //гласные
        StringBuilder vowelsOfF = new StringBuilder(); //гласные последнего слова первой строки
        StringBuilder vowelsOfS = new StringBuilder(); //гласные второй
        String[] words = stringF.split(" "); //разделим слова первой строчки по пробелу и поместим в массив
        words[words.length - 1] = words[words.length - 1].toLowerCase(); //понизим регистр в последнем слове
        for (int i = 0; i < words[words.length - 1].length(); i++) { //поочередно проверим буквы последнего слова
            if (vowels.contains(String.valueOf(words[words.length - 1].charAt(i)))) { //если строка гласных содержит символ последнего слова
                vowelsOfF.append(words[words.length - 1].charAt(i)); //добавляем в строку гласных первой строки
            }
        }
        words = stringS.split(" "); //заменим массив на массив из слов второй строки
        words[words.length - 1] = words[words.length - 1].toLowerCase();
        for (int i = 0; i < words[words.length - 1].length(); i++) {
            if (vowels.contains(String.valueOf(words[words.length - 1].charAt(i)))) {
                vowelsOfS.append(words[words.length - 1].charAt(i));
            }
        }
        return vowelsOfF.toString().equals(vowelsOfS.toString()); //сравним полученные строки
    }
    public static boolean trouble(long num1, long num2) { // 9. Функция, которая принимает два целых числа и возвращает true, если число повторяется три раза подряд в любом месте в num1 и то же самое число повторяется два раза подряд в num2.
        String str1 = String.valueOf(num1); //преобразуем в строчки
        String str2 = String.valueOf(num2);
        for (int i = 0; i < str1.length() - 2; i++) { //в первой строке
            if(str1.charAt(i) == str1.charAt(i + 1) && str1.charAt(i) == str1.charAt(i + 2)) { //находим повторяющиеся трижды и индекс первого из них
                for (int j = 0; j < str2.length() - 1; j++) { //во второй строке
                    if (str2.charAt(j) == str2.charAt(j + 1) && str2.charAt(j) == str1.charAt(i)) { //находи повторяющиеся дважды, равные первому из главного цикла
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static int countUniqueBooks(String stringSequence, char bookEnd) { // 10. Функция, которая возвращает общее количество уникальных символов (книг, так сказать) между всеми парами концов книги.
        StringBuilder str = new StringBuilder(); //строка уникальных букв
        if (stringSequence.indexOf(bookEnd) == -1) return 0;
        String temp = stringSequence.substring(stringSequence.indexOf(bookEnd) + 1); //начинаем искать от первой границы
        if (temp.indexOf(bookEnd) == -1) return 0;
        String spliter = "\\Q" + bookEnd + "\\E"; //нужно, если граница - специальный знак
        String[] books = temp.split(spliter); //делаем массив из букв между границами
        // возможные варианты, где | - граница, |книги| - полка:
        // |книги| лишние буквы |книги|
        // лишние буквы |книги|
        // |книги| лишние буквы |книги| лишние буквы
        // лишние буквы
        // |книги|
        for (int i = 0; i < books.length; i += 2) { //нам нужны книги внутри границ, а не между полками, т.е. каждое второе значение
            for (int j = 0; j < books[i].length(); j++) {
                if (!(str.toString().contains(String.valueOf(books[i].charAt(j))))) {
                    str.append(books[i].charAt(j));
                }
            }
        }
        return str.toString().length();
//        if (stringSequence.indexOf(bookEnd) == -1) return 0;
//        for (int i = stringSequence.indexOf(bookEnd) + 1; i < stringSequence.length(); i++) {
//            if (stringSequence.charAt(i) == bookEnd) {
//                i = stringSequence.indexOf(bookEnd, i + 1);
//            }
//            int j;
//            for (j = i; j < stringSequence.indexOf(bookEnd, i); j++) {
//                if (!(str.toString().contains(String.valueOf(stringSequence.charAt(i))))) {
//                    str.append(stringSequence.charAt(i));
//                }
//            }
//            if (j == stringSequence.length() - 1) return str.toString().length();
//            i = stringSequence.indexOf(bookEnd, j + 2);
//        }
//        return str.toString().length();
//        for (int i = stringSequence.indexOf(bookEnd) + 1; i < stringSequence.length() - 1; i++) {
//            int num = 0;
////            if (stringSequence.charAt(i) != stringSequence.charAt(stringSequence.indexOf(bookEnd, i))) {
////                i = stringSequence.indexOf(bookEnd, i) + 1;
////            }
////            else
////                i = stringSequence.indexOf(bookEnd, i) + 1;
//            if (stringSequence.charAt(i) != stringSequence.charAt(i + 1)) {
//                for (int j = i; j < stringSequence.indexOf(bookEnd, i); j++) {
//                    if (stringSequence.charAt(j) != stringSequence.charAt(j + 1)) {
//                        count.append(stringSequence.charAt(j));
//                        num = j;
//                        System.out.println(count);
//                    }
//                    else {
//                        count.append(stringSequence.charAt(j));
//                        j = stringSequence.indexOf(bookEnd, i);
//                        System.out.println(count);
//                    }
//                }
//                if (num > 0) {
//                    i = stringSequence.indexOf(bookEnd, num + 1);
//                    int k = 1;
//                }
//            }
//            else {
//                while (stringSequence.charAt(i) == stringSequence.charAt(i + 1)) {
//                    i++;
//                }
//                count.append(stringSequence.charAt(i));
//            }
//        }
//        return count.toString().length();
    }
}