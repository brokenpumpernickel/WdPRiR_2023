import java.util.concurrent.atomic.AtomicInteger;

public class AtomicOperations {
    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger();
        ai.getAndIncrement();
    }
}
