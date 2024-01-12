import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Conditions002 {
    public static int counter = 0;
    public static ReentrantLock reentrantLock = new ReentrantLock();
    public static Condition condition = reentrantLock.newCondition();

    public static class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    reentrantLock.lock();
                    ++counter;
                    System.out.println("Produced: " + counter);
                    condition.signalAll();
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
                    while(lastCounterValue == counter)
                        condition.awaitUninterruptibly();
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

        Consumer[] consumers = new Consumer[5];
        for(int i = 0; i < consumers.length; ++i) {
            consumers[i] = new Consumer();
            consumers[i].start();
        }

        producer.join();
    }
}
