package CompareArrays;

/**
 * Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true,
 * если они одинаковые, и false в противном случае. Массивы могут быть любого типа данных,
 * но должны иметь одинаковую длину и содержать элементы одного типа по парно по индексам.
 */
public class Task2 {
    public static <S, U> boolean compareArrays(S[] arr1, U[] arr2) {
        if (arr1.length != arr2.length) return false;
        int len = arr1.length;
        for (int i = 0; i < len; i++) {
            if (!arr1[i].getClass().equals(arr2[i].getClass())) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Number[] numbers = new Number[5];
        Object[] objects = new Object[5];

        numbers[0] = 5.0;
        numbers[1] = 5f;
        numbers[2] = (byte) 1;
        numbers[3] = (short) 3;
        numbers[4] = 7;

        objects[0] = 5.0;
        objects[1] = 5f;
        objects[2] = 1;           //difference
        objects[3] = (short) 3;
        objects[4] = 7;

        System.out.println(compareArrays(numbers, objects));
    }
}
