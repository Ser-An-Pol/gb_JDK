package withForks;

public class Fork {
    private boolean using;

    public Fork() {
        using = false;
    }

    public boolean isUsing() {
        return using;
    }

    public void setUsing(boolean using) {
        this.using = using;
    }
}
