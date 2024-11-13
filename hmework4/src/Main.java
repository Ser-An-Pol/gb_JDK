/**
 * Создать справочник сотрудников
 * Необходимо:
 * Создать класс справочник сотрудников, который содержит внутри
 * коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
 * Табельный номер
 * Номер телефона
 * Имя
 * Стаж
 * Добавить метод, который ищет сотрудника по стажу (может быть список)
 * Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
 * Добавить метод, который ищет сотрудника по табельному номеру
 * Добавить метод добавления нового сотрудника в справочник
 */


public class Main {
    public static void main(String[] args) {
        StaffBook staffBook = new StaffBook();
        staffBook.addStaff(new Staff(1, "1243", "Александр", 5));
        staffBook.addStaff(new Staff(2, "3424", "Никита", 3));
        staffBook.addStaff(new Staff(3, "3345", "Дарья", 2));
        staffBook.addStaff(new Staff(4, "2344", "Мария", 5));
        staffBook.addStaff(new Staff(5, "3253", "Александр", 6));

        System.out.printf("Сотрудники по стажу в %d лет\n", 5);
        System.out.println(staffBook.findBySeniority(5));
        System.out.printf("Номер телефона сотрудника по имени: %s\n", "Александр");
        System.out.println(staffBook.findPhoneNumberByName("Александр"));
        System.out.printf("Сотрудник по табельному номеру %d\n", 3);
        System.out.println(staffBook.findStaffByJobNumber(3));
    }
}