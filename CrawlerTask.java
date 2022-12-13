import java.net.*;
import java.io.*;
import java.util.LinkedList;
public class CrawlerTask {
    private URLDepthPair url;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    public static final String URL_PREFIX = "http://";
    public static final String URL_HREF = "<a href=";

    public CrawlerTask (URLDepthPair url) {
        this.url = url;
    }
    private boolean connect() { //функция для создания объекта сокета
        try {
            socket = new Socket(url.urlObj.getHost(), 80); //80 порт ответственн за http
        } catch (UnknownHostException e) {
            System.out.println("Неизвестный хост: " + url.getUrl());
            return false;
        } catch (IOException e) {
            System.out.println("Ошибка ввода или вывода при создании сокета: " + url.getUrl());
            return false;
        }
        return true;
    }
    private boolean setTimeOut() { //функция для установки тайм-аута для обращения потоков
        try {
            socket.setSoTimeout(1000);
        } catch (SocketException e) {
            System.out.println("Ошибка при установке тайм-аута: " + url.getUrl());
            return false;
        }
        return true;
    }
    private boolean openStreams() { //функция для создания входного и выходного потоков
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Ошибка при создании потоков: " + url.getUrl());
            return false;
        }
        return true;
    }
    private LinkedList<String> readHTML() { //функция для считывания HTML-документа и сохранения его построчно
        LinkedList<String> list = new LinkedList<String>(); //запросили http-код
        out.println("GET " + url.urlObj.getPath() + " HTTP/1.1"); //начало обращения для получения страницы
        out.println("Host: " + url.urlObj.getHost());
        out.println("Connection: close");
        out.println(); //конец обращения
        String temp;
        try {
            while ((temp = in.readLine()) != null) { //считывание документа построчно
                list.add(temp);
            }
        } catch (IOException e) {
            System.out.println("Ошибка во время считывания");
        }
        return list;
    }
    private boolean closeConnection() { //функция для закрытия сокета
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Ошибка закрытия сокета");
            return false;
        }
        return true;
    }
    public LinkedList<URLDepthPair> read() { //функция для получения спарсенных ссылок
        if (!connect()) { //начало сетевой части
            return null;
        }
        if (!setTimeOut()) {
            return null;
        }
        if (!openStreams()) {
            return null;
        }
        LinkedList<String> listRead = readHTML();
        while (!closeConnection()) {} //конец сетевой части
        LinkedList<URLDepthPair> pairs = new LinkedList<URLDepthPair>();
        for (String line: listRead) { //поиск ссылок в документе
            int index = line.indexOf(URL_PREFIX);
            int indexX = line.indexOf("\"", index);
            if (!line.contains(URL_HREF) || (index == -1)) continue;
            URLDepthPair url;
            try {
                url = new URLDepthPair(line.substring(index, indexX), this.url.getDepth() + 1);
            }
            catch (MalformedURLException e) {
                continue;
            }
            pairs.add(url);
        }
        return pairs;
    }
}
