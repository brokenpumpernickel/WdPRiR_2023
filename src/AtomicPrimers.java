import java.util.concurrent.atomic.AtomicInteger;

public class AtomicPrimers {
    public static class Worker extends Thread {
        private AtomicInteger atomicInteger;

        public Worker(AtomicInteger atomicInteger) {
            this.atomicInteger = atomicInteger;
        }

        @Override
        public void run() {
            int number;
            int count = 0;
            while ((number = atomicInteger.getAndIncrement()) < 1000000) {
                if(Helpers.isPrime(number)) {
                    System.out.println(number);
                }
                ++count;
            }

            System.out.println(count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
        AtomicInteger atomicInteger = new AtomicInteger();

        Worker[] workers = new Worker[12];
        for(int i = 0; i < workers.length; ++i) {
            workers[i] = new Worker(atomicInteger);
        }

        for(int i = 0; i < workers.length; ++i)
            workers[i].start();

        for(int i = 0; i < workers.length; ++i)
            workers[i].join();

        long end = System.nanoTime();
        System.out.println("Time: " + (end - start) / 1000000000.0);
    }
}
