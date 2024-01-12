import java.util.concurrent.locks.ReentrantLock;

public class Conditions001 {
    public static int counter = 0;
    public static ReentrantLock reentrantLock = new ReentrantLock();

    public static class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    reentrantLock.lock();
                    ++counter;
                    System.out.println("Produced: " + counter);
                } finally {
                    reentrantLock.unlock();
                }

                try {
                    sleep(5000);
                } catch (InterruptedException e) {}
            }
        }
    }

    public static class Consumer extends Thread {
        private int lastCounterValue = -1;
        @Override
        public void run() {
            while (true) {
                try {
                    reentrantLock.lock();
                    if(lastCounterValue == counter)
                        continue;
                    lastCounterValue = counter;
                    System.out.println("Consumed " + lastCounterValue + " by " + getId());
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer();
        producer.start();

        Consumer[] consumers = new Consumer[20];
        for(int i = 0; i < consumers.length; ++i) {
            consumers[i] = new Consumer();
            consumers[i].start();
        }

        producer.join();
    }
}
