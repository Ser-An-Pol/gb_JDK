package switcher;

public class Switch extends Thread {
    private Data data;

    public Switch(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        super.run();
        while (data.getCount() > 0) {
            data.setSwitcher(!data.isSwitcher());
            if (data.isSwitcher()) {
                System.out.println("Wke up!");
            } else {
                System.out.println("Sleep!");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
