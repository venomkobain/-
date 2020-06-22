import java.io.*;

public class Socket {
    java.net.Socket socket;
    Socket(String host,int port) throws IOException {
        socket = new java.net.Socket(host,port);
        setSoTimeout(5000);
    }

    void setSoTimeout(int timeout)throws IOException {
        socket.setSoTimeout(timeout);
    }

    InputStream getInputStream()throws IOException {
        return socket.getInputStream();
    }

    OutputStream getOutputStream()throws IOException{
        return socket.getOutputStream();
    }

    void close()throws IOException{
        socket.close();
    }
}
