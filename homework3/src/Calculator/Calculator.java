package Calculator;
/**
 Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы:
 sum(), multiply(), divide(), subtract().
 Параметры этих методов – два числа разного типа, над которыми должна быть произведена операция.
 Методы должны возвращать результат своей работы.

 */
public class Calculator {
    public static <S extends Number, U extends Number> Number sum(S val1, U val2) {
        return val1.doubleValue() + val2.doubleValue();
    }
    public static <S extends Number, U extends Number> Number multiply(S val1, U val2) {
        return val1.doubleValue() * val2.doubleValue();
    }
    public static <S extends Number, U extends Number> Number divide(S val1, U val2) {
        return val1.doubleValue() / val2.doubleValue();
    }
    public static <S extends Number, U extends Number> Number subtract(S val1, U val2) {
        return val1.doubleValue() - val2.doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(sum(5f, 7));
        System.out.println(multiply(6.4, 5));
        System.out.println(divide(7, 0));
        System.out.println(subtract(1.111, 0.1));
    }
}
