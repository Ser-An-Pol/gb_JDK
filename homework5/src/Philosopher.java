import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread {
    private final int ID;
    private final Status[] states;
    ReentrantLock locker;
    private final int limitMeals;
    private int countMeals;
    private final Random random = new Random();

    public Philosopher(int ID, int limitMeals, Status[] states, ReentrantLock locker) {
        this.ID = ID;
        this.limitMeals = limitMeals;
        this.states = states;
        countMeals = 0;
        this.locker = locker;
    }

    @Override
    public void run() {
        super.run();
        while (countMeals < limitMeals) {
            try {
                Thread.sleep(random.nextInt(3000));
                tryChangeStatus();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        states[ID] = Status.THINKING;
        System.out.println(ID + "-й пошел домой...");
    }

    private void tryChangeStatus() {
        locker.lock();
        if (states[ID] == Status.EATING) {
            states[ID] = Status.THINKING;
            System.out.println(ID + "-й думать будет...");
        }
        else {
            if (nextState() == Status.THINKING && prevState() == Status.THINKING) {
                states[ID] = Status.EATING;
                countMeals++;
                System.out.println(ID + "-й будет кушать " + countMeals + "-й раз...");
            }
            else {
                System.out.println(ID + "-й ещё подумает немного...");
            }
        }
        locker.unlock();
    }

    private Status nextState() {
        int next = (ID == states.length - 1) ? 0 : ID + 1;
        return states[next];
    }

    private Status prevState() {
        int prev = (ID == 0) ? states.length - 1 : ID - 1;
        return states[prev];
    }
}
