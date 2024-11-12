package Pair;

/**
 *Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
 * Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из составляющих пары,
 * а также переопределение метода toString(), возвращающее строковое представление пары.
 */

public class Pair <S, U> {
    private S firstVal;
    private U secondVal;

    public Pair(S firstVal, U secondVal) {
        this.firstVal = firstVal;
        this.secondVal = secondVal;
    }

    public S getFirst() {
        return firstVal;
    }

    public U getSecond() {
        return secondVal;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "firstVal=" + firstVal +
                ", secondVal=" + secondVal +
                '}';
    }

    public static void main(String[] args) {
        Pair<Integer, String> pair = new Pair<>(5, "Hi");

        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
        System.out.println(pair);
    }
}
