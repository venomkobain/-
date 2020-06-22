import java.io.*;
import java.util.LinkedList;
import java.util.regex.*;

public class Crawler {
    LinkedList<URLDepthPair> siteList;
    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    Pattern regHTTP;

    Crawler (URLDepthPair urlDepthPair) throws IOException{
        try {
            socket = new Socket(urlDepthPair.getUrl(),80);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            siteList = new LinkedList<URLDepthPair>();
            regHTTP = Pattern.compile("(http:\\/\\/[\\w\\-\\.!~?&=+\\*'(),\\/\\#\\:]+)((?!\\<\\/\\w\\>))*?");
            siteList = new LinkedList<URLDepthPair>();
        }
        catch (Exception exc){
            System.out.println(exc);
            return;
        }

        printWriter.println("GET / HTTP/1.1");
        printWriter.println("Host: "+urlDepthPair.getUrl()+":80");
        printWriter.println("Connection: Close");
        printWriter.println();

        try{
            String line;
            while ((line=bufferedReader.readLine())!=null) {
                while(line.contains("<a")){
                    while (line.indexOf(">", line.indexOf("<a"))==-1) line+=bufferedReader.readLine();

                    String http = line.substring(line.indexOf("<a"),line.indexOf(">", line.indexOf("<a")));
                    if (http.contains("http://")){
                        Matcher matcher = regHTTP.matcher(http);
                        String url="";
                        if (matcher.find()) {
                            url = matcher.group();
                        }
                        else
                            return;
                        siteList.add(new URLDepthPair(url,urlDepthPair.getDepth()+1));
                    }
                    line=line.replace(http,"");
                }
            }
        }
        catch (IOException except){
            System.out.println(except);
        }
        socket.close();
    }

    LinkedList<URLDepthPair> getSites(){
        return siteList;
    }

    public static void main(String[] args) throws IOException{
        int endDepth=Integer.parseInt(args[1]);
        LinkedList<URLDepthPair> site = new LinkedList<>();
        LinkedList<URLDepthPair> newSite = new LinkedList<>();

        newSite.add(new URLDepthPair(args[0],0));

        while(newSite.size()!=0){
            Crawler crawler = new Crawler(newSite.getFirst());
            if (newSite.getFirst().getDepth()<endDepth)
                newSite.addAll(crawler.getSites());

            else
                site.addAll(crawler.getSites());
            site.add(newSite.removeFirst());
        }

        for(URLDepthPair url: site){
            System.out.println(url);
        }
    }
}
