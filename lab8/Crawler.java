import java.net.MalformedURLException;
import java.util.LinkedList;
public class Crawler {
    private final static String ERROR = "Usage: java Crawler <URL> <depth> <num_threads>";
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
        if (args.length != 3) {
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
        int threadsnum = parseDigit(args[2]);
        if (threadsnum == -1) {
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
        URLPool pool = new URLPool(); //создаем пул
        pool.push(firstPair); //добавлем первую необработ ссылку
        Thread[] threads = new Thread[threadsnum];
        for (int i = 0; i < threadsnum; i++) { //создаем потоки на основе класса CrawlerTask
            CrawlerTask task = new CrawlerTask(pool, depth);
            threads[i] = new Thread(task);
            threads[i].start();
            System.out.println("Запущен поток " + threads[i].getName());
        }
        while (pool.getWaiters() != threadsnum) { //основной поток ждет окончания работы других потоков
            try {
                Thread.sleep(500); //основной поток спит полсекунды
            } catch (InterruptedException e) {
            }
        }
        for (int i = 0; i < threadsnum; i++) { //останавливаем все потоки
            threads[i].stop();
        }
        for (URLDepthPair pair: pool.getClosed()) {
            System.out.println(pair); //для вывода объекта вызывает метод toString()
        }
    }

}
