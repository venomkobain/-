import java.io.IOException;

public class CrawlerTask implements Runnable {

    private URLPool urlPool;
    private URLDepthPair urlDepthPair;

    public CrawlerTask(URLPool pool) {
        urlPool = pool;
    }

    public void run() {
        while (true) {
            urlDepthPair = urlPool.getURL();
            try {
                Crawler clawler = new Crawler(urlDepthPair);
                urlPool.addListURL(clawler.getSites());
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
