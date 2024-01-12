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

//public class Counter {
//    private int count = 0;
//    private ReentrantLock lock = new ReentrantLock();
//
//    public int getAndIncrement() {
//        lock.lock();
//        int tmp = count++;
//        lock.unlock();
//        return tmp;
//
//    }

//public class Counter {
//    private int count = 0;
//
//    synchronized public int getAndIncrement() {
//        return count++;
//    }
//}

//public class Counter {
//    private int count = 0;
//    private ReentrantLock lock = new ReentrantLock();
//
//    public int getAndIncrement() {
//        try {
//            lock.lock();
//            return count++;
//        } finally {
//            lock.unlock();
//        }
//
//    }
//}

public class Counter {
    private int count = 0;
    private TestAndSetLock lock = new TestAndSetLock();

    public int getAndIncrement() {
        try {
            lock.lock();
            return count++;
        } finally {
            lock.unlock();
        }

    }

    public int getCount() {
        return count;
    }
}
