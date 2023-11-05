//import java.util.concurrent.locks.ReentrantLock;
//
//public class Counter {
//    private int count = 0;
//
//    public int getAndIncrement() {
//
//        return count++;
//    }
//}

import java.util.concurrent.locks.ReentrantLock;

//public class Counter { // Dzialajaca wersja licznika wykorzystująca zamek
//    private int count = 0;
//    private ReentrantLock lock = new ReentrantLock();
//
//    public int getAndIncrement() {
//        lock.lock(); // początek sekcji krytycznej - tu jednoczesnie bedzie tylko jeden watek
//        int tmp = count++;
//        lock.unlock(); // koniec sekcji krytycznej
//        return tmp;
//
//    }

//public class Counter { // licznik wykorzystujacy slowo kluczowe synchronized
//    private int count = 0;
//
//    synchronized public int getAndIncrement() { // cala metoda jest sekcja krytyczna
//        return count++;
//    }
//}

public class Counter { // licznik wykorzystujacy zamek, z sekcja kryryczna w sekcji try - catch
    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();

    public int getAndIncrement() {
        try {
            lock.lock();
            return count++;
        } finally { // finally sie zawsze wykona, nawet przed instrukcja return.
            lock.unlock();
        }

    }
}

