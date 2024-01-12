import java.util.concurrent.*;

public class CountdownLatch001 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        long start = System.nanoTime();

        CountDownLatch countDownLatch = new CountDownLatch(47);

        for (int i = 0; i < 47; ++i) {
            int j = i;
            ex.execute(() -> {
                System.out.println(j + " " + Helpers.fibon(j));
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();

        long end = System.nanoTime();
        System.out.println("Time: " + (end - start) / 1000000000.0);

        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.DAYS);
    }
}
