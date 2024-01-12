import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {
    public static class Producer extends Thread {
        private BlockingQueue<String> blockingQueue;

        public Producer(BlockingQueue<String> blockingQueue) { this.blockingQueue = blockingQueue; }

        @Override
        public void run() {
            Random random = new Random();
            for(int count = 0; true; ++count) {
                try {
                    sleep(random.nextInt(2000));
                    System.out.println("Produced: " + getId() + " " + count);
                    blockingQueue.put(getId() + " " + count);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static class Consumer extends Thread {
        private BlockingQueue<String> blockingQueue;

        public Consumer(BlockingQueue<String> blockingQueue) { this.blockingQueue = blockingQueue; }

        @Override
        public void run() {
            Random random = new Random();
            while(true) {
                try {
                    String result = blockingQueue.take();

                    sleep(random.nextInt(2000));
                    System.out.println("Consumed: " + result + "; by " + getId());

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();

        Producer[] producers = new Producer[6];
        for(int i = 0; i < producers.length; ++i)
            producers[i] = new Producer(blockingQueue);

        Consumer[] consumers = new Consumer[6];
        for(int i = 0; i < consumers.length; ++i)
            consumers[i] = new Consumer(blockingQueue);

        for(int i = 0; i < consumers.length; ++i) {
            producers[i].start();
            consumers[i].start();
        }
    }
}
