import java.util.ArrayList;
import java.util.List;

public class StaffBook {
    private final List<Staff> staffList;

    public StaffBook() {
        this.staffList = new ArrayList<>();
    }

    /**
     * метод добавления нового сотрудника в справочник
     */
    public void addStaff(Staff staff) {
        if (findStaffByJobNumber(staff.getJobNumber()) != null) {
            System.out.println("Сотрудник с таким табельным номером уже существует");
            return;
        }
        staffList.add(staff);
    }

    /**
     * метод, который ищет сотрудника по стажу (может быть список)
     */
    public List<Staff> findBySeniority(int seniority) {
        return staffList.stream().filter(staff -> staff.getSeniority() == seniority).toList();
    }

    /**
     * метод, который возвращает номер телефона сотрудника по имени (может быть список)
     */
    public List<String> findPhoneNumberByName(String name) {
        return staffList.stream()
                .filter(staff -> staff.getName().equalsIgnoreCase(name))
                .map(Staff::getPhoneNumber)
                .toList();
    }

    /**
     * метод, который ищет сотрудника по табельному номеру
     */
    public Staff findStaffByJobNumber(int jobNumber) {
        return staffList.stream().filter(staff -> staff.getJobNumber() == jobNumber)
                .findFirst()
                .orElse(null);
    }

}
