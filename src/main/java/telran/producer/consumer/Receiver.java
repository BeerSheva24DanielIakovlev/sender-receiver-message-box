package telran.producer.consumer;

import java.util.concurrent.CountDownLatch;

public class Receiver extends Thread {
    private MessageBox messageBox;
    private volatile boolean running = true;

    public Receiver(MessageBox messageBox, CountDownLatch latch) {
        this.messageBox = messageBox;
    }

    @Override
    public void run() {
        try {
            while (running) {
                String message = messageBox.poll();
                if (message != null) {
                    System.out.printf("Thread: %s, message: %s\n", getName(), message);
                }
            }
        } catch (Exception e) {
            System.out.printf("Thread: %s interrupted.\n", getName());
        }
    }

    public void stopReceiver() {
        running = false;
    }
}
