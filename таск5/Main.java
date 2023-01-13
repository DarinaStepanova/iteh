import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(encrypt("Hello")); //[72, 29, 7, 0, 3]
        System.out.println(decrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68})); //"Hi there!"
        System.out.println(encrypt("Sunshine")); //[83, 34, -7, 5, -11, 1, 5, -9]
        System.out.println("  ");

        System.out.println(canMove("Rook", "A8", "H8")); //true
        System.out.println(canMove("Bishop", "A7", "G1")); //true
        System.out.println(canMove("Queen", "C4", "D6")); //false
        System.out.println("  ");

        System.out.println(canComplete("butl", "beautiful")); //true
        System.out.println(canComplete("butlz", "beautiful")); //false
        System.out.println(canComplete("tulb", "beautiful")); //false
        System.out.println(canComplete("bbutl", "beautiful")); //false
        System.out.println("  ");

        System.out.println(sumDigProd(16, 28)); //6
        System.out.println(sumDigProd(0)); //0
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6)); //2
        System.out.println(sumDigProd(534, 506)); //0
        System.out.println("  ");

        System.out.println(sameVowelGroup(new String[]{"toe", "ocelot", "maniac"})); //[toe, ocelot]
        System.out.println(sameVowelGroup(new String[]{"many", "carriage", "emit", "apricot", "animal"})); //[many]
        System.out.println(sameVowelGroup(new String[]{"hoops", "chuff", "bot", "bottom"})); //[hoops, bot, bottom]
        System.out.println("  ");

        System.out.println(validateCard(1234567890123456L)); //false
        System.out.println(validateCard(1234567890123452L)); //true
        System.out.println("  ");

        System.out.println(numToEng(0)); //zero
        System.out.println(numToEng(18)); //eighteen
        System.out.println(numToEng(126)); //one hundred twenty six
        System.out.println(numToEng(909)); //nine hundred nine
        System.out.println(numToRus(0)); //ноль
        System.out.println(numToRus(18)); //восемнадцать
        System.out.println(numToRus(126)); //сто двадцать шесть
        System.out.println(numToRus(909)); //девятьсот девять
        System.out.println("  ");

        System.out.println(getSha256Hash("hello world")); //true
        System.out.println(getSha256Hash("password123")); //true
        System.out.println(getSha256Hash("Fluffy@home")); //false
        System.out.println(getSha256Hash("Hey dude!")); //false
        System.out.println("  ");

        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth.")); //"Jon Snow, King in the North."
        System.out.println(correctTitle("sansa stark, lady of winterfell.")); //"Sansa Stark, Lady of Winterfell."
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN.")); //"Tyrion Lannister, Hand of the Queen."
        System.out.println("  ");

        System.out.println(hexLattice(1)); //
        System.out.println("  ");
        System.out.println(hexLattice(7)); //
        System.out.println("  ");
        System.out.println(hexLattice(19)); //
        System.out.println("  ");
        System.out.println(hexLattice(37)); //
        System.out.println("  ");
        System.out.println(hexLattice(21)); //invalid
        System.out.println("  ");
    }

    public static String encrypt(String string) { // 1. Две функции, которые принимают строку и массив и возвращают закодированное или декодированное сообщение. Первая буква строки или первый элемент массива представляет собой символьный код этой буквы. Следующие элементы-это различия между символами: например, A +3 --> C или z -1 --> y.
        int[] characters = new int[string.length()]; //массив ответа
        characters[0] = string.charAt(0); //первое значение - первая буква строки (ее код ASCII)
        for (int i = 1; i < string.length(); i++) { //для всех остальных элементов массива высчитываем разность
            //x+n=y => n=y-x
            characters[i] = (int) string.charAt(i) - (int) string.charAt(i - 1);
        }
        return Arrays.toString(characters);
    }

    public static StringBuilder decrypt(int[] characters) {
        StringBuilder string = new StringBuilder(); //строка ответа
        string.append((char) characters[0]); //первый символ - чар первого элемента числового массива
        for (int i = 1; i < characters.length; i++) { //для всех остальных находим буквы
            //y=x+n
            string.append((char) ((int) string.charAt(i - 1) + characters[i]));
        }
        return string;
    }

    public static boolean canMove(String f, String start, String end) { // 2. Функция, вычисляющая возможность шахматного хода
        int startLetter = start.charAt(0); //разделяю на буквы и цифры доски и перевожу все в ASCII для удобного подсчета шагов
        int startNumber = start.charAt(1);
        int endLetter = end.charAt(0);
        int endNumber = end.charAt(1);
        if ((Objects.equals(f, "Bishop") || Objects.equals(f, "Queen")) && Math.abs(startLetter - endLetter) == Math.abs(startNumber - endNumber))
            return true;
        if ((Objects.equals(f, "Rook") || Objects.equals(f, "Queen")) && (startLetter == endLetter || startNumber == endNumber))
            return true;
        if (Objects.equals(f, "Knight") && (Math.abs(startLetter - endLetter) == 1 && Math.abs(startNumber - endNumber) == 2 || Math.abs(startLetter - endLetter) == 2 && Math.abs(startNumber - endNumber) == 1))
            return true;
        if (Objects.equals(f, "Pawn") && ((startLetter == endLetter) && (Math.abs(startNumber - endNumber) == 1 || Math.abs(startNumber - endNumber) == 2 && (startNumber == 7 || startNumber == 2))))
            return true;
        if (Objects.equals(f, "King") && (Math.abs(startNumber - endNumber) == 1 || Math.abs(startLetter - endLetter) == 1))
            return true;
        return false;
    }

    public static boolean canComplete(String letters, String word) { // 3. Функция, которая, учитывая входную строку, определяет, может ли слово быть завершено буквами из этой строки в том же порядке без лишних.
        int index;
        int j = -1;
        for (int i = 0; i < letters.length(); i++) {
            index = word.indexOf(letters.charAt(i), j + 1);
            if (index == -1) return false;
            j = index;
        }
        return true;
    }

    public static int sumDigProd(int... numbers) { // 4. Функция, которая принимает числа в качестве аргументов, складывает их вместе и возвращает произведение цифр до тех пор, пока ответ не станет длиной всего в 1 цифру.
        int num = 0; //сюда будем складывать и потом умножать
        for (int i = 0; i < numbers.length; i++) {
            num += numbers[i]; //складываем
        }
        while (num > 9) { //умножаем, пока цифра не одна
            String[] numeric = String.valueOf(num).split(""); //делаем строковый массив по циферке
            num = 1; //для удобного умножения
            for (int i = 0; i < numeric.length; i++) {
                num *= Integer.parseInt(numeric[i]); //перемножаем все циферки массива
            }
        }
        return num;
    }

    public static String sameVowelGroup(String[] words) { // 5. Функция, которая выбирает все слова, имеющие все те же гласные (в любом порядке и / или количестве), что и первое слово, включая первое слово.
        String vowels = "aeiouy"; //гласные
        StringBuilder vowelsOfF = new StringBuilder(); //гласные первого слова
        StringBuilder wordsNew = new StringBuilder(words[0]);
        for (int i = 0; i < words[0].length(); i++) { //поочередно проверим буквы
            if (vowels.contains(String.valueOf(words[0].charAt(i))) && !(vowelsOfF.toString().contains(String.valueOf(words[0].charAt(i))))) { //если строка гласных содержит символ первого слова и в строке гласных первого слова еще нет этого символа
                vowelsOfF.append(words[0].charAt(i)); //добавляем в строку гласных первого слова
            }
        }
        words:
        //метка для следующего слова, если просчитанное не подошло
        for (int i = 1; i < words.length; i++) { //по массиву
            int j;
            for (j = 0; j < vowelsOfF.length(); j++) { //по гласным первого слова
                if (words[i].indexOf(vowelsOfF.charAt(j)) == -1) { //если в слове нет нужной гласной
                    continue words; //то ищем гласные в другом слове
                }
            }
            wordsNew.append(" "); //для будущего раздела
            wordsNew.append(words[i]); //добавляем нужное слово
        }
        return Arrays.toString(wordsNew.toString().split(" "));
    }

    public static boolean validateCard(long card) { // 6. Функция, которая принимает число в качестве аргумента и возвращает true, если это число является действительным номером кредитной карты, а в противном случае-false.
        String numeric = String.valueOf(card / 10); //удаляем последнюю цифру и делаем строчку
        if (numeric.length() > 18 || numeric.length() < 13) {
            return false; //если введено было меньше 14 или больше 19 цифр, то это не номер карты
        }
        int control = (int) (card % 10); //контрольная цифра - последняя от введенного номера
        int sum = 0;
        int[] numbers = new int[numeric.length()]; //будущий массив цифр без последней
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(String.valueOf(numeric.charAt(numeric.length() - 1 - i))); //кладем в массив цифры, начиная с конца строки номера
        }
        for (int i = 0; i < numbers.length; i += 2) { //для всех четных позиций:
            numbers[i] *= 2; //удвоить
            if (numbers[i] > 9) { //если удвоенное значение не является цифрой
                numbers[i] = numbers[i] / 10 + numbers[i] % 10; //сложить цифры значения и полученную цифру добавить в массив вместо того, что получилось до
            }
        }
        for (int i = 0; i < numbers.length; i++) { //просуммировать числа массива
            sum += numbers[i];
        }
        return control == 10 - sum % 10; //это номер карты, если контрольная цифра - разность десяти и последней цифры суммы получившегося массива
    }

    public static String numToEng(int num) { // 7. Функция, которая принимает положительное целое число от 0 до 999 включительно и возвращает строковое представление этого целого числа, написанное на английском языке.
        String[] digits = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        String hundred = "hundred";
        StringBuilder name = new StringBuilder();
        String[] numeric = String.valueOf(num).split("");
        //numeric: сотни(0), десятки(1), единицы(2),
        if (num > 99) { //для трехзначных
            name.append(digits[Integer.parseInt(numeric[0])]); //число сотен
            name.append(" ");
            name.append(hundred);
            name.append(" ");
            if (num % 100 > 19) { //если десятки - 20 - 99, то
                name.append(tens[Integer.parseInt(numeric[1]) - 2]); //число десятков, начиная от 2
                name.append(" ");
                if (num % 10 > 0) { //если единицы - не ноль
                    name.append(digits[Integer.parseInt(numeric[2])]); //число единиц
                }
            } else {
                if (num % 100 > 9) { //если десятки - 10 - 19, то
                    name.append(teens[Integer.parseInt(numeric[1])]); //число тина
                } else { //если десятки - 0
                    name.append(digits[Integer.parseInt(numeric[2])]); //число единиц
                }
            }
        } else if (num > 9) { //для двузначных
            if (num % 100 > 19) { //если не тин
                name.append(tens[Integer.parseInt(numeric[0]) - 2]); //десятки
                name.append(" ");
                if (num % 10 > 0) { //если есть единицы
                    name.append(digits[Integer.parseInt(numeric[1])]);
                }
            } else { //тин
                name.append(teens[Integer.parseInt(numeric[1])]);
            }
        }
        //однозначные - единицы
        else name.append(digits[Integer.parseInt(numeric[0])]);
        return name.toString();
    }

    public static String numToRus(int num) { //Функция, которая принимает положительное целое число от 0 до 999 включительно и возвращает строковое представление этого целого числа, написанное на русском языке.
        String[] digits = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
        String[] teens = {"десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
        String[] tens = {"двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
        String[] hundred = {"сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
        StringBuilder name = new StringBuilder();
        String[] numeric = String.valueOf(num).split("");
        //numeric: сотни(0), десятки(1), единицы(2),
        if (num > 99) { //для трехзначных
            name.append(hundred[Integer.parseInt(numeric[0]) - 1]);
            name.append(" ");
            if (num % 100 > 19) { //если десятки - 20 - 99, то
                name.append(tens[Integer.parseInt(numeric[1]) - 2]); //число десятков, начиная от 2
                name.append(" ");
                if (num % 10 > 0) { //если единицы - не ноль
                    name.append(digits[Integer.parseInt(numeric[2])]); //число единиц
                }
            } else {
                if (num % 100 > 9) { //если десятки - 10 - 19, то
                    name.append(teens[Integer.parseInt(numeric[1])]); //число тина
                } else { //если десятки - 0
                    name.append(digits[Integer.parseInt(numeric[2])]); //число единиц
                }
            }
        } else if (num > 9) { //для двузначных
            if (num % 100 > 19) { //если не тин
                name.append(tens[Integer.parseInt(numeric[0]) - 2]); //десятки
                name.append(" ");
                if (num % 10 > 0) { //если есть единицы
                    name.append(digits[Integer.parseInt(numeric[1])]);
                }
            } else { //тин
                name.append(teens[Integer.parseInt(numeric[1])]);
            }
        }
        //однозначные - единицы
        else name.append(digits[Integer.parseInt(numeric[0])]);
        return name.toString();
    }
//    public static int[] rightrotate(int[] arr) {
//        // last element will become first
//        int last = arr[arr.length-1];
//        for (int i = arr.length-1; i > 0; i--) {
//            // shift the elements to right
//            arr[i] = arr[i-1];
//        }
//        arr[0] = last;
//        return arr;
//    }

    public static String getSha256Hash(String string) throws NoSuchAlgorithmException { // 8. Функция, которая возвращает безопасный хеш SHA-256 для данной строки.
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(string.getBytes());
        byte[] digest = md.digest();
        String result = new BigInteger(1, digest).toString(16).toUpperCase();
        return result;
//        int count = string.length() * 8 + 8; //количество байт в массиве = количество символов + "10000000"
//        // + нули до того, как число не станет кратно 512
//        while (count % 512 != 0) { //пока число бит не кратно 512
//            count += 8; //добавим 8 бит = 1 байт
//        }
//        String[] bytes = new String[(count) / 8]; //массив размером с количество байт, биты которых кратны 512
//        int i;
//        for (i = 0; i < string.length(); i++) {
//            bytes[i] = Integer.toBinaryString(string.charAt(i)); //заполняем массив двоичным кодом символов
//            while (bytes[i].length() < 8) {
//                bytes[i] = "0" + bytes[i];
//            } //если значимая единица до нулевой цифры, ставим ноль
//        }
//        bytes[i] = "10000000"; //после добавления символов в массив прибавим единицу
//        for (int j = i + 1; j < bytes.length - 8; j++) {
//            bytes[j] = "00000000"; //без последних 64 битов (8 байт) заполним все нулями
//        }
//        for (int k = bytes.length - 8; k < bytes.length; k++) { //для последних 8 байт
//            bytes[k] = Integer.toBinaryString(string.length() * 8); //размер строки = количество символов * 8 бит каждый
//            while (bytes[k].length() < 8) {
//                bytes[k] = "0" + bytes[k];
//            }
//            while (k < bytes.length - 1 && bytes[k + 1] == null) { //если размер не занимает в том числе последний байт, то двигаем
//                bytes[k] = "00000000";
//                k++;
//                bytes[k] = Integer.toBinaryString(string.length() * 8);
//                if (bytes[k].length() < 8) {
//                    bytes[k] = "0" + bytes[k];
//                }
//            }
//        }
//        //Константы для вычислений.
//        //Инициализируем значения хэша (h).
//        //Теперь мы создаем 8 хэш-значений.
//        // Это жестко запрограммированные константы,
//        // которые представляют собой первые 32 бита дробных частей квадратных корней
//        // из первых восьми простых чисел: 2, 3, 5, 7, 11, 13, 17, 19.
//        String h0 = "0x6a09e667";
//        String h1 = "0xbb67ae85";
//        String h2 = "0x3c6ef372";
//        String h3 = "0xa54ff53a";
//        String h4 = "0x510e527f";
//        String h5 = "0x9b05688c";
//        String h6 = "0x1f83d9ab";
//        String h7 = "0x5be0cd19";
//        //Инициализация округленных констант (k)
//        //Каждое значение (0—63) представляет собой первые 32 бита дробных частей кубических корней первых 64 простых чисел (2—311).
//        String[] k = {"0x428a2f98", "0x71374491", "0xb5c0fbcf", "0xe9b5dba5", "0x3956c25b", "0x59f111f1", "0x923f82a4", "0xab1c5ed5",
//                "0xd807aa98", "0x12835b01", "0x243185be", "0x550c7dc3", "0x72be5d74", "0x80deb1fe", "0x9bdc06a7", "0xc19bf174",
//                "0xe49b69c1", "0xefbe4786", "0x0fc19dc6", "0x240ca1cc", "0x2de92c6f", "0x4a7484aa", "0x5cb0a9dc", "0x76f988da",
//                "0x983e5152", "0xa831c66d", "0xb00327c8", "0xbf597fc7", "0xc6e00bf3", "0xd5a79147", "0x06ca6351", "0x14292967",
//                "0x27b70a85", "0x2e1b2138", "0x4d2c6dfc", "0x53380d13", "0x650a7354", "0x766a0abb", "0x81c2c92e", "0x92722c85",
//                "0xa2bfe8a1", "0xa81a664b", "0xc24b8b70", "0xc76c51a3", "0xd192e819", "0xd6990624", "0xf40e3585", "0x106aa070",
//                "0x19a4c116", "0x1e376c08", "0x2748774c", "0x34b0bcb5", "0x391c0cb3", "0x4ed8aa4a", "0x5b9cca4f", "0x682e6ff3",
//                "0x748f82ee", "0x78a5636f", "0x84c87814", "0x8cc70208", "0x90befffa", "0xa4506ceb", "0xbef9a3f7", "0xc67178f2"};
//        for (int g = 0; g < bytes.length; g += 64) { //для каждого 512-битного фрагмента = каждых 64 байт в массиве входных данных bytes
//            int bytesLength = 512; //
////            while (bytesLength % 512 != 0) {
////                bytesLength = bytesLength - 512;
////            }
//            String[] w = new String[bytesLength / 32 + 48]; //новый массив по 32 бита в каждой ячейке из массива bytes + 48 слов
//            for (int l = bytesLength / 32; l < w.length; l++) {
//                w[l] = "00000000"; //последние 48 слов заполним все нулями
//            }
//            int m = g;
//            for (int n = 0; n < bytesLength / 32; n++) { //первые до 48 слов - слова входного, объединенные в 32-битные
//                w[n] = bytes[m] + bytes[m + 1] + bytes[m + 2] + bytes[m + 3];
//                m += 4;
//            }
//            for (i = 16; i < 64; i++) {
//                String s0 = (for (int k =0; k < 7; k++) {rightrotate(w[i..15])}) xor (w[i-15] rightrotate 18) xor (w[i-15] righthift 3);
//                String s1 = (w[i-2] rightrotate 17) xor (w[i-2] rightrotate 19) xor (w[i-2] righthift 10);
//                w [i] = w[i-16] + s0 + w[i-7] + s1;
//            }
//        }
//        //
//        System.out.println(Arrays.toString(bytes));
//        return count;

    }

    public static String correctTitle(String s) { // 9. Функция, которая принимает строку и возвращает строку с правильным регистром для заголовков символов в серии "Игра престолов". Слова and, the, of и in должны быть строчными. Все остальные слова должны иметь первый символ в верхнем регистре, а остальные-в нижнем.
        String[] words = s.toLowerCase().split(" "); //массив - пониженные слова со знаками препинания
        for (int i = 0; i < words.length; i++) {
            if (!Objects.equals(words[i], "in") && !Objects.equals(words[i], "and") && !Objects.equals(words[i], "of") && !Objects.equals(words[i], "the")) {
                words[i] = words[i].replace(words[i].charAt(0), (char) (words[i].charAt(0) - 32)); //если слово требует повышения первой буквы - заменяем ее
            }
        }
        return String.join(" ", words); //обратно переводим в строку
    }
    public static String hexLattice(int n) { // 10. Функция, которая принимает целое число n и возвращает "недопустимое", если n не является центрированным шестиугольным числом или его иллюстрацией в виде многострочной прямоугольной строки в противном случае.
        // оболочка шестиугольника: K = 3k + 3(k - 2) = 3(2k - 2) = 6(k - 1), где k - грань, натуральное число
        // n - количество точек общее
        // K / 6 + 1 = k - натуральное число, порядок шестиугольника.
        int count = 1; //переменная для сравнения и подсчета допустимых значений
        int k = 1; //порядок
        while (count < n) { //пока наша переменная меньше заданной
            k++; //увеличим порядок
            count += 6 * (k - 1); //прибавим новую оболочку
        }
        if (count != n) return "Invalid";
        StringBuilder result = new StringBuilder(); //рисуночек
        String s = "o";
        int l = k; //для кружочков
        int r = k; //для пробелов перед строчкой
        for (int m = 0; m < k; m++) { //до середины включительно
            for (int v = 1; v < r; v++) { //пробелов перед точкой меньше порядка для первой итерации на один
                result.append(" ");
            }
            r--; //уменьшаем следующее количество пробелов (на новой строчке)
            for (int h = 0; h < l; h++) { //для начала строчка = грань = k кружочков
                result.append(s);
                result.append(" ");
            }
            if (k != 1) result.append("\n");
            l++; //увеличиваем количество кружочков для следующей строки
        }
        for (int m = 1; m < k; m++) { //после середины на 1 итерацию меньше
            r++; //увеличиваем пробелы
            for (int v = 0; v < r; v++) {
                result.append(" ");
            }
            l--; //после середины на 1 кружочек меньше и дальше тоже
            for (int h = 1; h < l; h++) {
                result.append(s);
                result.append(" ");
            }
            if (m != k - 1) result.append("\n");
        }
        return result.toString();
    }
}
