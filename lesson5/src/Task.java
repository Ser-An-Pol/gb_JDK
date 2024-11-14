public class Task implements Runnable {
    private final int left, right;

    public Task(int left, int right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        System.out.println(left+right);
    }

    @Override
    public String toString() {
        return "(" + left + " + " + right + ")\n";
    }
}
