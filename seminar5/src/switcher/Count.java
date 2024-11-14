package switcher;

public class Count extends Thread {
    private Data data;

    public Count(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        super.run();
        while (data.getCount() > 0) {
            if (data.isSwitcher()) {
                System.out.println("Count = " + data.getCount());
                data.setCount(data.getCount() - 1);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
