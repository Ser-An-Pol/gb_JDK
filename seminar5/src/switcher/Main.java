package switcher;

/**
 * Создайте два потока A и B.
 * Поток A меняет значение Boolean переменной switcher с задержкой 1000 миллисекунд (true в состояние false и наоборот).
 * Поток B ожидает состояния true переменной switcher и выводит на консоль обратный отсчет от 100
 * с задержкой 100 миллисекунд и приостанавливает свое действие, как только поток A переключит switcher в состояние false.
 * Условием завершения работы потоков является достижение отсчета нулевой отметки.
 * Можно воспользоваться синхронизацией для управления значения переменной или volatile
 */
public class Main {
    private static boolean switcher = true;
    private static volatile int count = 100;

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            while (count > 0) {
                switcher = !switcher;
                if (switcher) {
                    System.out.println("Wke up!");
                } else {
                    System.out.println("Sleep!");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (count > 0) {
                if (switcher) System.out.println("Count = " + count--);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
