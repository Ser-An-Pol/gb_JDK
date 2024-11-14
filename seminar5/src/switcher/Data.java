package switcher;

public class Data {
    private boolean switcher;
    private int count;

    public Data(boolean switcher, int count) {
        this.switcher = switcher;
        this.count = count;
    }

    public boolean isSwitcher() {
        return switcher;
    }

    public void setSwitcher(boolean switcher) {
        this.switcher = switcher;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
