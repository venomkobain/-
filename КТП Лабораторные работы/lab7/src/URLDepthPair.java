import java.net.MalformedURLException;
import java.net.URL;

public class URLDepthPair {
    private String url;
    private int depth;

    URLDepthPair(String Url, int Depth){
        url=Url;
        depth=Depth;
    }

    public boolean isURL(){
        try{
            URL nUrl = new URL(url);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public String getUrl() {
        try {
            URL nUrl = new URL(url);
            return nUrl.getHost();
        }
        catch (MalformedURLException e) {
            System.err.println("MalformedURLException: " + e.getMessage());
            return null;
        }
    }

    public int getDepth() {
        return depth;
    }

    public String toString(){
        return depth + " || "+url;
    }
}
