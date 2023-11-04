import java.util.LinkedList;
import java.util.concurrent.*;

public class Fibon {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

//        long start = System.nanoTime();
//        for (int i = 0; i < 47; ++i)
//            System.out.println(i + " " + Helpers.fibon(i));
//        long end = System.nanoTime();
//        System.out.println("Time: " + (end - start) / 1000000000.0);

//        ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        long start = System.nanoTime();
//        for (int i = 0; i < 47; ++i) {
//            int j = i;
//            ex.execute(() -> System.out.println(j + " " + Helpers.fibon(j)));
//        }
//        ex.shutdown();
//        ex.awaitTermination(1, TimeUnit.DAYS);
//        long end = System.nanoTime();
//        System.out.println("Time: " + (end - start) / 1000000000.0);

//        LinkedList<Future<String>> results = new LinkedList<>();
//        ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        long start = System.nanoTime();
//        for (int i = 0; i < 47; ++i) {
//            int j = i;
//            results.add(ex.submit(() -> j + " " + Helpers.fibon(j)));
//        }
//        for (var future : results) {
//            System.out.println(future.get());
//        }
//
//        long end = System.nanoTime();
//        System.out.println("Time: " + (end - start) / 1000000000.0);
//
//        ex.shutdown();
//        ex.awaitTermination(1, TimeUnit.DAYS);

        ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletionService<String> cs = new ExecutorCompletionService<>(ex);

        long start = System.nanoTime();
        for (int i = 46; i >= 0; --i) {
            int j = i;
            cs.submit(() -> j + " " + Helpers.fibon(j));
        }

        for (int i = 0; i < 47; ++i) {
            System.out.println(cs.take().get());
        }

        long end = System.nanoTime();
        System.out.println("Time: " + (end - start) / 1000000000.0);

        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.DAYS);

    }
}
