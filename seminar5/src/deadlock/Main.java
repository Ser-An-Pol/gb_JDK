package deadlock;

/**
 * Создать два класс ObjectA, ObjectB
 * Реализовать класс в котором два потока при запуске провоцируют DeadLock для объектов ObjectA, ObjectB
 */
public class Main {

    public static void main(String[] args) {
        Neighbour neighbourA = new Neighbour("Alex");
        Neighbour neighbourB = new Neighbour("Boris");


        Thread threadA = new Thread(() -> neighbourA.ask(neighbourB));
        Thread threadB = new Thread(() -> neighbourB.ask(neighbourA));
        threadA.start();
        threadB.start();

        System.out.println("Fin!");
    }
}