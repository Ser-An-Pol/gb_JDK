package ru.seranpol;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;

/**
 * Создать свой Java Gradle проект;
 * Добавить зависимость Guava (популярная библиотека от Google, содержащая набор утилитарных механизмов).
 * Воспользоваться утилитарным классом Lists:
 * Развернуть список элементов
 * Получить лист Character из строки
 * Разделить лист на группы по 2 элемента
 * Воспользоваться утилитарным классом Sets
 * Получить итоговый Set из двух коллекций
 * Получить итоговый Set из общих элементов двух коллекций
 * Получить итоговый Set из непересекающихся элементов двух коллекций
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2 ,3, 4, 5, 6, 7));

        System.out.println(list);
        System.out.println(Lists.reverse(list));

        String string = "Hello, world!";
        System.out.println(Lists.charactersOf(string));

        Lists.partition(list, 2).forEach(System.out::println);

        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2 ,3, 4, 5, 6, 7));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(5, 6, 7, 8, 9, 10, 11));
        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2);
        System.out.println(Sets.union(set1, set2));
        System.out.println(Sets.intersection(set1, set2));
        System.out.println(Sets.symmetricDifference(set1, set2));

    }
}