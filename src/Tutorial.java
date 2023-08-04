import java.util.ArrayList;
import java.util.List;

public class Tutorial {
    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<Thread>();
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        int numberOfThreads = Integer.parseInt(args[2]);

        for (int i = 0; i < numberOfThreads; i++) {
            threads.add(new Thread(new ReqRunnable(host, port)));
        }
        for (Thread thread: threads) {
            thread.start();
        }
    }
}
