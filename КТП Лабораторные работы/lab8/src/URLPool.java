import java.io.IOException;
import java.util.*;
public class URLPool {
    private LinkedList<URLDepthPair> site = new LinkedList<>();
    private LinkedList<URLDepthPair> newSite = new LinkedList<>();

    private int finalDepth;

    private int waitThreads = 0;

    URLPool(URLDepthPair url,int depth){
        finalDepth=depth;
        newSite = new LinkedList<URLDepthPair>();
        site = new LinkedList<URLDepthPair>();
        newSite.add(url);
    }

    public synchronized int getWaitThreads() {
        return waitThreads;
    }

    public synchronized URLDepthPair getURL(){
        if (newSite.size() == 0) {
            try {
                waitThreads++;
                this.wait();
            }
            catch (InterruptedException e) {
                System.err.println("MalformedURLException: " + e.getMessage());
                return null;
            }
        }
        site.add(newSite.getFirst());
        return newSite.removeFirst();
    }

    public synchronized void addListURL(LinkedList<URLDepthPair> URLs){

        if (URLs.size()!=0){
            if (URLs.getFirst().getDepth()>=finalDepth){
                site.addAll(URLs);
            }
            else{
                newSite.addAll(URLs);
                for (int countSite=URLs.size(); countSite!=0 && waitThreads!=0;countSite-- , waitThreads--){
                    this.notify();
                }
            }
        }
    }

    public LinkedList<URLDepthPair> getSite(){
        return site;
    }

    public static void main(String[] args) throws IOException {
        String url=args[0];
        int endDepth=Integer.parseInt(args[1]);
        int countT=Integer.parseInt(args[2]);

        URLPool pool = new URLPool(new URLDepthPair(url,0),endDepth);
        LinkedList<Thread> threadList = new LinkedList<>();

        for (int i=0;i<countT;i++){
            CrawlerTask crawlerTask = new CrawlerTask(pool);
            threadList.add(new Thread(crawlerTask));
            threadList.getLast().start();
        }

        while (pool.getWaitThreads()!=countT){}

        LinkedList<URLDepthPair> site=pool.getSite();

        for(URLDepthPair iurl: site){
            System.out.println(iurl);
        }

        for(Thread thread: threadList){
            thread.stop();
        }
    }
}
