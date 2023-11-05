public class Primers {
    public static class Worker extends Thread {
        private Counter counter;

        public Worker(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            int number;
            int count = 0;
            while ((number = counter.getAndIncrement()) < 1000000) {
                if(Helpers.isPrime(number)) {
                    //System.out.println(number);
                }
                ++count;
            }

            System.out.println(count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
        Counter counter = new Counter();

        Worker[] workers = new Worker[12]; // To jest zle - cale wykonanie sie serializuje
        for(int i = 0; i < workers.length; ++i) {
            workers[i] = new Worker(counter);
            workers[i].start();
            workers[i].join(); // join blokuje kazda iteracje petli, az watek nie skonczy dzialac
        }

//        for(int i = 0; i < workers.length; ++i) // To wykomentowane natomiast dziala.
//            workers[i].start();
//
//        for(int i = 0; i < workers.length; ++i)
//            workers[i].join();

        long end = System.nanoTime();
        System.out.println("Time: " + (end - start) / 1000000000.0);
    }
}
