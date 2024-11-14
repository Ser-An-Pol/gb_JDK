package runners;

import java.util.concurrent.CountDownLatch;

public class Runner extends Thread {
    private String name;
    private int velocity;
    private int distance;
    private CountDownLatch cdl;

    public Runner(String name, int distance, int velocity, CountDownLatch cdl) {
        this.name = name;
        this.distance = distance;
        this.velocity = velocity;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        super.run();
        try {
            onMark();
            cdl.countDown();
            attention();
            cdl.countDown();
            march();
            cdl.countDown();
            cdl.await();
            go();
            finish();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void onMark() throws InterruptedException {
        System.out.println(this.name + ": На старт!");
        Thread.sleep(1000);
    }
    private void attention() throws InterruptedException {
        System.out.println(this.name + ": Внимание!");
        Thread.sleep(1000);
    }
    private void march() {
        System.out.println(this.name + ": Марш!");
    }
    private void go() throws InterruptedException {
        int time = distance/velocity;
        Thread.sleep(time*1000);
    }
    private void finish(){
        System.out.println(this.name + ": Я финишировал!");
    }

}
