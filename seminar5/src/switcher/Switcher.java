package switcher;

public class Switcher {
    static volatile Data data;

    public static void main(String[] args) {
        data = new Data(false, 100);
        Switch switcher = new Switch(data);
        Count count = new Count(data);

        switcher.start();
        count.start();
    }
}
