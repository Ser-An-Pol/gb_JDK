package deadlock;

class Neighbour {
    private String name;

    public Neighbour(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public synchronized void ask(Neighbour neighbour) {
        System.out.println(this.name + ": " + neighbour.getName() + ", how are you?");
        neighbour.answer(this);
    }

    public synchronized void answer(Neighbour neighbour) {
        System.out.println(this.name + ": I'm fine, " + neighbour.getName() + ".");
    }
}