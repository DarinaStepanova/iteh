import java.util.LinkedList;
public class URLPool {
    private final LinkedList<URLDepthPair> opened = new LinkedList<URLDepthPair>();
    private final LinkedList<URLDepthPair> closed = new LinkedList<URLDepthPair>();
    private int numWaiters = 0; //кол-во потоков, ожидающих ссылку
    public synchronized URLDepthPair get() { //синхронизированный метод для получения ссылки
        while (opened.isEmpty()) {
            numWaiters++;
            try {
                wait();
            } catch (InterruptedException e) {
            }
            numWaiters--;
        }
        return opened.removeFirst();
    }
    public synchronized boolean push(URLDepthPair pair) { //синх метод для добавления ссылки в необраб
        if (!opened.contains(pair) && !closed.contains(pair)) {
            opened.add(pair);
            notify(); //выводит остальные потоки из ждущего режима
            return true;
        }
        return false;
    }
    public synchronized void migrate(URLDepthPair pair) { // синх метод для доб ссылки в обработанные
        if (!closed.contains(pair)) {
            closed.add(pair);
        }
    }
    public LinkedList<URLDepthPair> getClosed() {
        return closed;
    }
    public int getWaiters() {
        return numWaiters;
    }

}
