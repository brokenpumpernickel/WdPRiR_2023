import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class TestAndSetLock implements PoorLock {
    private AtomicBoolean atomicBoolean = new AtomicBoolean();
    //private boolean flag = false;

    @Override
    public void lock() {
        while(atomicBoolean.getAndSet(true));
    }

    @Override
    public void unlock() {
        atomicBoolean.set(false);
    }

//    @Override
//    public void lock() {
//        while(flag);
//        flag = true;
//    }
//
//    @Override
//    public void unlock() {
//        flag = false;
//    }
}
