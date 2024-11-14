package runners;

import java.util.concurrent.CountDownLatch;

/**
 * В рамках выполнения задачи необходимо:
 * 3 бегуна должны прийти к старту гонки
 * Программа должна гарантировать, что гонка начнется только когда все три участника будут на старте
 * Программа должна отсчитать “На старт”, “Внимание”, “Марш”
 * Программа должна завершиться когда все участники закончат гонку.
 * Подумайте об использовании примитива синхронизации
 */
public class Main {
    public static void main(String[] args) {
        int countOfRunners = 3;
        int distance = 100;
        CountDownLatch cdl = new CountDownLatch(3*countOfRunners);

        for (int i = 0; i < countOfRunners; i++) {
            new Runner("Number_" + i, distance, (int)((Math.random()+1)*10), cdl).start();
        }
    }
}
