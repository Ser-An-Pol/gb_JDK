package ru.seranpol;

import java.util.HashMap;

/**
 * В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла
 * (Парадокс Монти Холла — Википедия) и наглядно убедиться в верности парадокса
 * (запустить игру в цикле на 1000 и вывести итоговый счет).
 * ******************************************************************
 * Участнику игры заранее известны следующие правила:
 *  -автомобиль равновероятно размещён за любой из трёх дверей;
 *  -ведущий знает, где находится автомобиль;
 *  -ведущий в любом случае обязан открыть дверь с козой (но не ту, которую выбрал игрок)
 *      и предложить игроку изменить выбор;
 *  -если у ведущего есть выбор, какую из двух дверей открыть
 *      (то есть, игрок указал на верную дверь, и за обеими оставшимися дверями — козы),
 *      он выбирает любую из них с одинаковой вероятностью.
 * ******************************************************************
 * Необходимо:
 * Создать свой Java Maven или Gradle проект;
 * Самостоятельно реализовать прикладную задачу;
 * Сохранить результат в HashMap<шаг теста, результат>
 * Вывести на экран статистику по победам и поражениям
 */
public class Main {

    public static void main(String[] args) {
        int countOfIteration = 1000;
        int countWins;
        HashMap<Integer, Boolean> resultWithChangeChoice = new HashMap<>(countOfIteration);
        HashMap<Integer, Boolean> resultWithoutChangeChoice = new HashMap<>(countOfIteration);

        System.out.println("Игра со сменой выбора:");
        countWins = 0;
        for (int i = 0; i < countOfIteration; i++) {
            resultWithChangeChoice.put(i,
                    new Game().resultWithChangeChoice());
            System.out.printf("Шаш %d: Результат - %s\n", i,
                    (resultWithChangeChoice.get(i)) ? "выиграл" : "проиграл");
            if (resultWithChangeChoice.get(i)) countWins++;
        }
        System.out.println("Всего выигрышей: " + countWins);

        System.out.println();
        System.out.println("***********************************************");
        System.out.println();

        System.out.println("Игра без смены выбора:");
        countWins = 0;
        for (int i = 0; i < countOfIteration; i++) {
            resultWithoutChangeChoice.put(i,
                    new Game().resultWithoutChangeChoice());
            System.out.printf("Шаш %d: Результат - %s\n", i,
                    (resultWithoutChangeChoice.get(i)) ? "выиграл" : "проиграл");
            if (resultWithoutChangeChoice.get(i)) countWins++;
        }
        System.out.println("Всего выигрышей: " + countWins);
    }
}