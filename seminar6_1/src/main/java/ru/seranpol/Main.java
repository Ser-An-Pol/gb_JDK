package ru.seranpol;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.util.ArithmeticUtils;

import java.util.Arrays;

/**
 * Создать свой Java Maven проект;
 * Добавьте зависимость commons-math3 (предназначена для решения математических задач) и изучить интерфейс библиотеки.
 * Воспользоваться пакетом org.apache.commons.math3.stat и классом DescriptiveStatistics.
 * Создать коллекцию из числовых элементов.
 * С помощью объекта DescriptiveStatistics вычислить минимальное и максимальное значение, сумму и среднее арифметическое.
 * Воспользоваться пакетом org.apache.commons.math3.util. С помощью класса ArithmeticUtils найти :
 * факториал числа N.
 * Наименьшее общее частное двух чисел
 * Наибольший общий делитель двух чисел
 * Проверить что число N это степень двойки
 */

public class Main {
    public static void main(String[] args) {
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();

        for (int i = 0; i < 100; i++) {
            descriptiveStatistics.addValue(Math.random()*100 + Math.random());
        }

        System.out.println(Arrays.toString(descriptiveStatistics.getValues()));
        System.out.println(descriptiveStatistics);
        System.out.println("min = " + descriptiveStatistics.getMin());
        System.out.println("max = " + descriptiveStatistics.getMax());
        System.out.println("sum = " + descriptiveStatistics.getSum());
        System.out.println("mean = " + descriptiveStatistics.getMean());

        System.out.println();

        System.out.println("5! = " + ArithmeticUtils.factorial(5));
        System.out.println("lcm(5, 7) = " + ArithmeticUtils.lcm(5, 7));
        System.out.println("gcd(15, 27) = " + ArithmeticUtils.gcd(15, 27));
        System.out.println("Is 1024 power of 2: " + ArithmeticUtils.isPowerOfTwo(1024));


    }
}