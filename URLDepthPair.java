import java.net.MalformedURLException;
import java.net.URL;
//класс хранит пару ссылка-и-ее глубина
public class URLDepthPair {
    private String url;
    private int depth;
    public URL urlObj;
    //также создает объект класса url
    public URLDepthPair (String url, int depth) throws MalformedURLException {
        this.url = url;
        this.depth = depth;
        this.urlObj = new URL(url);
    }
    public  String getUrl() {
        return url;
    }
    public int getDepth() {
        return depth;
    }
    public String toString() {
        return "Ссылка: " + url + ", длина: " + depth;
    }
    /** Переопределяем методы для корректного сравнения внутри LinkedList */
    @Override
    public boolean equals(Object o){
        if(o instanceof URLDepthPair p){
            return url.equals(p.url);
        }
        return false;
    }
    @Override
    public int hashCode(){
        return url.hashCode();
    }

}
