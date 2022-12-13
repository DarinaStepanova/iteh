import java.net.MalformedURLException;
import java.util.LinkedList;
public class Crawler {
    private final static String ERROR = "Usage: java Crawler <URL><depth>";
    private static void printERROR() {
        System.out.println(ERROR);
    }
    public static int parseDigit(String s) { //функция для парсинга пол
        try{
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }
    public static void main(String[] args) {
        //проверяем чтобы было два пареметрра url и port
        if (args.length != 2) {
            printERROR();
            return;
        }
        String url = args[0];
        if (!url.contains("http://")) {
            printERROR();
            return;
        }
        int depth = parseDigit(args[1]);
        if (depth == -1) {
            printERROR();
            return;
        }
        URLDepthPair firstPair;
        int currentDepth = 0;
        try {
            firstPair = new URLDepthPair(url, currentDepth);
        } catch (MalformedURLException e) {
            printERROR();
            return;
        }
        LinkedList<URLDepthPair> closed = new LinkedList<URLDepthPair>();
        LinkedList<URLDepthPair> opened = new LinkedList<URLDepthPair>();
        LinkedList<URLDepthPair> temp;
        opened.add(firstPair);
        while (currentDepth <= depth) { //цикл итерирует пока изменяется глубина или не закончатся необработанные ссылки
            temp = new LinkedList<URLDepthPair>(); //создаем пустой список, куда будем получать новые ссылки на этой итерации цикла
            for (URLDepthPair pair: opened) { //итерируем по необработанным ссылкам
                CrawlerTask task = new CrawlerTask(pair); //создаем объект для получения ссылок
                temp.addAll(task.read()); //получаем ссылки и сразу добавляем в темп
                closed.add(pair); //добавляем уже обработанную ссылку в обработанные
            }
            for (URLDepthPair pair: closed) { //итерируем по обработанным ссылкам
                opened.remove(pair); //пытаемся удалить все обработанные ссылки из списка необработанных
            }
            opened.addAll(temp); //добавляем все полученные на данной итерации ссылки в список необработанных
            if (opened.size() == 0) break; //если их нет, останавливаемся
            currentDepth = opened.get(0).getDepth(); //если есть, то получаем новую длину и ищем дальше
        }
        for (URLDepthPair pair: closed) {
            System.out.println(pair); //для вывода объекта вызывает метод toString()
        }
    }

}
