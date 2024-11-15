package withForks;

public class Table extends Thread {
    private final int countOfPhilosopher;
    private final int countOfMeal;
    private final Fork[] forks;
    private final Philosopher[] philosophers;
    private int countOfFinish;

    public Table(int countOfPhilosopher, int countOfMeal) {
        this.countOfPhilosopher = countOfPhilosopher;
        this.countOfMeal = countOfMeal;
        forks = new Fork[countOfPhilosopher];
        philosophers = new Philosopher[countOfPhilosopher];
        initForks();
        initPhilosophers();
        countOfFinish = 0;
    }

    private void initForks() {
        for (int i = 0; i < countOfPhilosopher; i++) {
            forks[i] = new Fork();
        }
    }

    private void initPhilosophers() {
        for (int i = 0; i < countOfPhilosopher; i++) {
            philosophers[i] = new Philosopher(i, this, countOfMeal);
        }
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < countOfPhilosopher; i++) {
            philosophers[i].start();
        }
    }

    public synchronized void finish() {
        countOfFinish++;
        if (countOfFinish == countOfPhilosopher) {
            System.out.println("Все разошлись. Доброй ночи!");
        }
    }

    public synchronized boolean takeForks(int id) {
        int left = id;
        int right = (left == countOfPhilosopher - 1) ? 0: left + 1;
        if (!forks[left].isUsing() && !forks[right].isUsing()) {
            forks[left].setUsing(true);
            forks[right].setUsing(true);
            return true;
        }
        else {
            return false;
        }
    }

    public void putForks(int id) {
        int left = id;
        int right = (left == countOfPhilosopher - 1) ? 0: left + 1;

        forks[left].setUsing(false);
        forks[right].setUsing(false);
    }
}
