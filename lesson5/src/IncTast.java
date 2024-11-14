import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class IncTast implements Runnable {
    //private int value;
    private AtomicInteger value;
    private CountDownLatch cdl;

    public IncTast(AtomicInteger value, CountDownLatch cdl) {
        this.value = value;
        this.cdl = cdl;
    }

    public AtomicInteger getValue() {
        return value;
    }

    private void inc() {
        value.incrementAndGet();
    }

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            inc();
        }
        cdl.countDown();
    }
}
