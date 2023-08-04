import java.net.InetSocketAddress;
import java.net.Socket;

public class ReqRunnable implements Runnable{
    private String host;
    private int port;

    public ReqRunnable(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Sending request to " + host);
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(host, port), 5000);
                Thread.sleep(100);
                socket.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

