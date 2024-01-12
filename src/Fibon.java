import java.util.LinkedList;
import java.util.concurrent.*;

public class Fibon { // Zabawy z ciagiem F., pule watkow
    public static void main(String[] args) throws InterruptedException, ExecutionException {

//        long start = System.nanoTime(); // wykonanie sekwencyjne
//        for (int i = 0; i < 47; ++i)
//            System.out.println(i + " " + Helpers.fibon(i));
//        long end = System.nanoTime();
//        System.out.println("Time: " + (end - start) / 1000000000.0);

        // Pula watkow

//        ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); // Nowa pula z liczba watkow rowna liczbie logicznych rdzeni w maszynie
//        long start = System.nanoTime();
//        for (int i = 0; i < 47; ++i) {
//            int j = i;
//            ex.execute(() -> System.out.println(j + " " + Helpers.fibon(j))); // dodajemy zadanie do puli - definiujemy je przy pomocy wyrazenia lambda
//        }
//        ex.shutdown(); // wysyla polecenie do puli, zeby zakonczyla swoje zadanie. Nowe joby nie sa przyjmowane, te siedzace juz w puli zostana skonczone
//        ex.awaitTermination(1, TimeUnit.DAYS); // metoda blokuje do czasu zamknieciu puli (po wykonaniu metody shutdown)
//        long end = System.nanoTime();
//        System.out.println("Time: " + (end - start) / 1000000000.0);

        // Pula watkow zwracajaca wyniki

//        LinkedList<Future<String>> results = new LinkedList<>(); // tu beda trzymane wyniki
//        ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        long start = System.nanoTime();
//        for (int i = 0; i < 47; ++i) {
//            int j = i;
//            results.add(ex.submit(() -> j + " " + Helpers.fibon(j))); // uzywamy metody submit, a nie execute. Zwraca ona obiekt Future, ktory bedzie zawieral w przyszlosci zwrocony wynik. Funkcja lambda definiujaca joba zwraca w tym przypadku wartosc!
//        }
//        for (var future : results) { // Iterujemy sie po wszystkich wynikach
//            System.out.println(future.get()); // Metoda get blokuje do chwili, w ktorej dany wynik stanie sie dostepny (obliczony przez pule)
//        }
//
//        long end = System.nanoTime();
//        System.out.println("Time: " + (end - start) / 1000000000.0);
//
//        ex.shutdown();
//        ex.awaitTermination(1, TimeUnit.DAYS);

        // Pobieranie wynikow z puli w kolejnosci ich pojawiania sie (wykonania)

        ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletionService<String> cs = new ExecutorCompletionService<>(ex); // CompletionService bedzie zwracal wyniki z puli w kolejnosi ich wykonania

        long start = System.nanoTime();
        for (int i = 46; i >= 0; --i) { // tu dla lepszego efektu iteracja od konca
            int j = i;
            cs.submit(() -> j + " " + Helpers.fibon(j)); // dodajemy joby do puli przez CompletionService
        }

        for (int i = 0; i < 47; ++i) { // iterujemy sie po jobach pobierajac ich wyniki z CompletionService - dostajemy je w kolejnosci wykonania
            System.out.println(cs.take().get());
        }

        long end = System.nanoTime();
        System.out.println("Time: " + (end - start) / 1000000000.0);

        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.DAYS);

    }
}
