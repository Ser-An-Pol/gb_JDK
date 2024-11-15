package withForks;

import java.util.Random;

public class Philosopher extends Thread {
    private final int ID;
    private Status status;
    private final Table table;
    private final int limitMeals;
    private int countMeals;
    private final Random random;

    public Philosopher(int ID, Table table, int limitMeals) {
        this.ID = ID;
        this.table = table;
        this.limitMeals = limitMeals;
        this.random = new Random();
        countMeals = 0;
    }

    @Override
    public void run() {
        super.run();
        begin();
        while (countMeals < limitMeals) {
            try {
                Thread.sleep(random.nextInt(3000));
                tryChangeStatus();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        end();
    }

    private void begin() {
        status = Status.THINKING;
        System.out.println(ID + "-й молча приветствует коллег и начинает думать...");
    }

    private void end() {
        table.putForks(ID);
        status = Status.THINKING;
        System.out.println(ID + "-й сыт, доволен и по-английски уходит домой!");
        table.finish();
    }

    private void tryChangeStatus() {
        if (status == Status.THINKING) {
            if (table.takeForks(ID)) {
                status = Status.EATING;
                countMeals++;
                System.out.println(ID + "-й будет кушать " + countMeals + "-й раз...");
            } else {
                System.out.println(ID + "-й ещё подумает немного...");
            }
        }
        else {
            table.putForks(ID);
            status = Status.THINKING;
            System.out.println(ID + "-й сыт и теперь думать будет...");
        }
    }
}
