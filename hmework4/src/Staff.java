public class Staff {
    private int jobNumber;
    private String phoneNumber;
    private String name;
    private int seniority;

    public Staff(int jobNumber, String phoneNumber, String name, int seniority) {
        this.jobNumber = jobNumber;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.seniority = seniority;
    }

    public int getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "jobNumber=" + jobNumber +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", seniority=" + seniority +
                "}\n";
    }
}
