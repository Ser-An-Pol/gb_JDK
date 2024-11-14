public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("1. Hello from " + Thread.currentThread().getName());
    }
}
