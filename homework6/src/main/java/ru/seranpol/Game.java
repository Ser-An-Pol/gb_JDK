package ru.seranpol;

import java.util.*;

public class Game {
    private final List<Integer> nums;
    private final boolean[] doors;
    private final Random random;

    public Game() {
        random = new Random();
        nums = new ArrayList<>(Arrays.asList(0, 1, 2));
        doors = new boolean[3];
        doors[0] = doors[1] = doors[2] = false;
        switch (random.nextInt(3)) {
            case 0 -> doors[0] = true;
            case 1 -> doors[1] = true;
            case 2 -> doors[2] = true;
        }
    }

    private void play() {
        int firstChoice = random.nextInt(3);
        if (firstChoice != 0) swap(firstChoice);
        openFirstDoor();
    }

    public boolean resultWithChangeChoice() {
        play();
        nums.removeFirst();
        return doors[nums.getFirst()];
    }

    public boolean resultWithoutChangeChoice() {
        play();
        return doors[0];
    }

    private void swap(int fc) {
        boolean temp = doors[0];
        doors[0] = doors[fc];
        doors[fc] = temp;
    }

    private void openFirstDoor() {
        if (doors[0]) {
            int od = random.nextInt(2) + 1;
            nums.remove(od);
        }
        else if (doors[1]) nums.remove(2);
        else nums.remove(1);
    }

}
