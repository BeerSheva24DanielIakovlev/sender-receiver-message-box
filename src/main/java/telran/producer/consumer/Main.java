// package telran.producer.consumer;

// public class Main {
//     private static final int N_MESSAGES = 20;
//     static final int N_RECEIVERS = 10;

//     public static void main(String[] args) throws InterruptedException {
//         MessageBox messageBox = new SimpleMessageBox();
//         Sender sender = new Sender(N_MESSAGES, messageBox);
//         for (int i = 0; i < N_RECEIVERS; i++) {
//             new Receiver(messageBox).start();
//         }
//         sender.start();
//         sender.join();
//         //FIXME line 16 should be taken out
//         Thread.sleep(100);
//         //TODO stoping all receivers
//     }
// }

package telran.producer.consumer;

import java.util.concurrent.CountDownLatch;

public class Main {
    private static final int N_MESSAGES = 20;
    static final int N_RECEIVERS = 10;

    public static void main(String[] args) throws InterruptedException {
        MessageBox messageBox = new SimpleMessageBox();
        CountDownLatch latch = new CountDownLatch(N_RECEIVERS);
        Sender sender = new Sender(N_MESSAGES, messageBox);

        for (int i = 0; i < N_RECEIVERS; i++) {
            new Receiver(messageBox, latch).start();
        }

        sender.start();
        sender.join();

         System.out.println("Sender has finished.");
    }
}

