public class Table extends Thread {
    private final int countOfPhilosopher;
    private final int limitMeals;
    private final Status[] tableStatus;
    private final Philosopher[] philosophers;

    public Table(int countOfPhilosopher, int limitMeals) {
        this.countOfPhilosopher = countOfPhilosopher;
        this.limitMeals = limitMeals;
        this.tableStatus = new Status[countOfPhilosopher];
        initStatuses();
        this.philosophers = new Philosopher[countOfPhilosopher];
        initPhilosophers();
    }


    private void initStatuses() {
        for (int i = 0; i < countOfPhilosopher; i++) {
            tableStatus[i] = Status.THINKING;
        }
    }

    private void initPhilosophers() {
        for (int i = 0; i < countOfPhilosopher; i++) {
            philosophers[i] = new Philosopher(i, limitMeals, tableStatus);
        }
    }

    private boolean isAllFinish() {
        for (int i = 0; i < countOfPhilosopher; i++) {
            if (philosophers[i].getState() != State.TERMINATED) return false;
        }
        return true;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < countOfPhilosopher; i++) {
            philosophers[i].start();
        }
        while (!isAllFinish());
        System.out.println("Все разошлись. Доброй ночи!");
    }
}
