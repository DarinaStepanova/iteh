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
    public static String repeat(String word, int n) { // 1. ??????????????, ?????????????? ?????????????????? ???????????? ???????????? ?? ???????????? n ??????.
        System.out.print(word + " with " + n + " repetitions is ");
        String repeated = ""; //???????????? ?????? ????????????????????
        for (int i = 0; i < word.length(); i++) {
            String tempStr = ""; //?????????????????? ???????????????????? ?????? ?????????????? ??????????????
            tempStr += word.charAt(i);
            repeated += tempStr.repeat(n);
        }
        return repeated; //???????????? ?? ????????????????????????
    }
    public static int differenceMaxMin(int[] myArray) { // 2. ??????????????, ?????????????? ?????????????????? ???????????? ?? ???????????????????? ?????????????? ?????????? ???????????? ???????????????? ?? ???????????? ???????????????????? ??????????????.
        int maxNum = myArray[0]; //?????????????????????? ?????????????????? ?? ???????????????? ???????????? ???????????????? ?????????????? ???? ??????????????????
        int minNum = myArray[0];
        for (int j = 1; j < myArray.length; j++) {
            if (maxNum < myArray[j]) { //???????? ?????????????????? ?????????? ?????????????? ???????????? ?????????? ?? ????????????????????, ???? ?????????????????????? ?????? ???????????????? ????
                maxNum = myArray[j];
            }
            if (minNum > myArray[j]) { //???????? ?????????????????? ?????????? ?????????????? ???????????? ?????????? ?? ????????????????????, ???? ?????????????????????? ?????? ???????????????? ????
                minNum = myArray[j];
            }
        }
        return maxNum-minNum; //????????????????
    }

    public static boolean isAvgWhole(int[] myArray) { // 3. ??????????????, ?????????????? ?????????????????? ???????????? ?? ???????????????? ?????????????????? ?? ???????????????????? true ?????? false ?? ?????????????????????? ???? ????????, ???????????????? ???? ?????????????? ???????????????? ???????? ?????????????????? ?????????????? ?????????? ???????????? ?????? ??????.
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
        return (sum/myArray.length) % 1 == 0; //?????????????? ???? ?????????????? ???????????? ?????????? ???????????? ???????? ?????????? ????????
    }
    public static String cumulativeSum(int[] myArray) { // 4. ??????????????, ??????????????  ?????????? ???????????? ?????????? ?????????? ?? ???????????????????? ????????????, ?? ?????????????? ???????????? ?????????? ?????????? ???????????????? ???????????? ???????????? ???????? + ???????? ???????????????????? ?????????? ?? ??????????????.
        int[] sumA = new int[myArray.length];
        int sumOfPr = 0; //???????????????????? ?????? ???????????????????? ???????????????? ???????????????????????? ??????????????
        for (int i = 0; i < myArray.length; i++) {
            sumOfPr += myArray[i]; //???????????????????? ???????????????? ??????????????
            sumA[i] = sumOfPr; //???????????? ?????????? ?? ????????????
        }
        return Arrays.toString(sumA); //???????????????????? ???????????????????? ???????????? ?? ???????????????????? ?????? ?????????????? ????????
    }
    public static int getDecimalPlaces(String strNum) { // 5. ??????????????, ?????????????? ???????????????????? ?????????? ???????????????????? ????????????, ?????????????? ?????????? ?????????? (???????????????? ?? ???????? ????????????). ?????????? ???????? ?????????? ???????????????????? ?????????? ?????????????????????????? ?? ?????????????? ???????????????????? ???????????????????? ????????????.
        int indexOfPoint = strNum.indexOf('.') + strNum.indexOf(','); //???????????? ?????????? ?????? ?????????????? (???????? ?????? ????????) ?? ?????????? ?????????? ?????????? ????????????-1, ?????????? -2.
        if (indexOfPoint != -2) {
            int countOfDecimalPlaces = 0;
            for (int i = indexOfPoint + 2; i < strNum.length(); i++) { //???????????????? ?????????????? ?????????? ?????????? ??????????
                countOfDecimalPlaces += 1;
            }
            return countOfDecimalPlaces; // ???????????????????? ???????????????????? ????????????
        }
        else return 0;
    }
    public static int fibonacci(int number) { // 6. ??????????????, ?????????????? ?????? ???????????????? ?????????? ???????????????????? ?????????????????????????????? ?????????? ??????????????????
        int fib = 0; //???????????????????? ?????? ???????????????? ?????????? ??????????????????
        int[] f = new int[number + 1]; //???????????? ?????????? ??????????????????
        f[0] = 1;
        f[1] = 1;
        f[2] = 2;
        for (int i = 3; i < number + 1; ++i) {
            f[i] = f[i - 1] + f[i - 2];
            fib = f[i];
        }
        return fib; // ???????????????????? ?????????? ?????????????????? ?? ???????????????????? ?????????????? number
    }
    public static boolean isValid(String str) { // 7. ??????????????, ?????????? ????????????????????, ???????????????? ???? ???????????? ???????????????????????????? ???????????????? ????????????????.
        int digit = 1; //???? ?????????????????? true
        if (str.length() == 5) { //???????????? ???? 5 ???????????????? ?? ?????????????
            for (int i = 0; i < str.length(); i++) {
                if (digit == 1) { //???????????????????? ?????? ?????????????????????? ???????????????? ?? ?????????????????????????????? ?????????? ?????????????????? 0
                    int isNumeric = str.charAt(i); //?????????????? ASCII ??????????????
                    digit = ((isNumeric > 47) && (isNumeric < 58)) ? 1 : 0; //???????????????? ???? ???????????? ?????????????
                }
            }
        }
        else digit = 0;
        return digit == 1;
    }
    public static boolean isStrangePair(String strF, String strS) { // 8. ??????????????, ?????????????? ?????????????? ???????????????????? true, ???????? ???????? ?????????? ???????????????????????? ?????????? ???????????????? ????????, ?? false ?? ?????????????????? ????????????.
        return (strF==""&&strS=="")||strF.charAt(0)==strS.charAt(strS.length()-1)&&strS.charAt(0)==strF.charAt(strF.length()-1); // ????????????????
    }
    public static boolean isPrefix(String word, String prefix) { // 9. isPrefix ???????????? ???????????????????? true, ???????? ???? ???????????????????? ?? ?????????????????????? ??????????????????
        int isP = 0; //???????? ?????????? ???????????????????? ??????????????, ???????? ?????????? ??????????????????
        for (int i = 0; i < prefix.length() - 1; i++) { //?? ?????????????? ?????????????? ???????????? ???? ???????????? ?????????? ???????? ?? ???????????????????? ??????????????????
            for (int j = 0; j < prefix.length() - 1; j++) { //?? ?????????????? ?????????????? ?????????????????????? ?????????????????? ???? ???????????? ?????????? ???????? ?? ??????
                isP += word.charAt(i) == prefix.charAt(j) ? 1 : 0;
            }
        }
        return isP == prefix.length() - 1; //?????????? ???????????? ???????????? ???????? ?????????? ???????????????????? ???????? ?? ????????????????
    }
    public static boolean isSuffix(String word, String suffix) { // isSuffix ???????????? ???????????????????? true, ???????? ???? ?????????????????????????? ???????????????????? ????????????????.
        int isP = 0; //???????? ?????????? ???????????????????? ??????????????, ???????? ?????????? ??????????????????
        for (int i = word.length() - 1; i > word.length() - suffix.length(); i--) { //?? ???????????????????? ?????????????? ???????????? ???? ???????????? ?????????? ???????? ?? ???????????????????? ??????????????????
            for (int j = suffix.length() - 1; j > 0; j--) { //?? ???????????????????? ?????????????? ?????????????????????? ?????????????????? ???? ???????????? ?????????? ???????? ?? ??????
                isP += word.charAt(i) == suffix.charAt(j) ? 1 : 0;
            }
        }
        return isP == suffix.length() - 1; //?????????? ???????????? ???????????? ???????? ?????????? ???????????????????? ???????? ?? ????????????????
    }
    public static int boxSeq(int step) { // 10. ??????????????, ?????????????? ?????????????????? ?????????? (??????) ?? ???????????????? ?????????????????? ?? ???????????????????? ???????????????????? ?????????? ???? ???????? ???????? ????????????????????????????????????.
        int boxes = 0; //???? ?????????????? ???????? 0 ??????????
        for (int i = 1; i < step + 1; i++) { //???? ???????????? ???????? ???????????????? 1
            if (i % 2 == 0) {
                boxes -= 1;
            }
            else { //???? ???????????????? ???????? ???????????????????? 3
                boxes += 3;
            }
        }
        return boxes; //???????????????????? ??????????
    }
}
