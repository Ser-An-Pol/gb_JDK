import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        System.out.println("Hello, world!");
//        System.out.println(Thread.currentThread().getName());

//        for (int i = 0; i < 3; i++) {
//            new MyThread().start();
//        }
//        for (int i = 0; i < 5; i++) {
//            new Thread(new MyRunnable()).start();
//        }
//        for (int i = 0; i < 3; i++) {
//            new Thread(() -> {
//                System.out.println("3. Hello from " + Thread.currentThread());
//            }).start();
//        }

        Thread tic = new Thread(new TicTac("{"));
        Thread tac = new Thread(new TicTac("}"));
        //tic.setDaemon(true); //Делаем поток сервисным (тем, который завершается при завершении основной программы)
        tic.start();
        tac.start();


//        long start = System.currentTimeMillis();
//        CountDownLatch cdl = new CountDownLatch(3);
//        IncTast task = new IncTast(new AtomicInteger(0), cdl);
//        for (int i = 0; i < 3; i++) {
//            Thread thread = new Thread(task);
//            thread.start();
//            //thread.join();
//        }
//        cdl.await();
//        long delta = System.currentTimeMillis() - start;
//        System.out.println(task.getValue() + ".\nIt takes " + delta + "ms.");
    }
}