import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Calculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Queue<Task> queue = new ArrayDeque<>();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Thread taskService  = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    executor.submit(() -> {
                        Task task = queue.poll();
                        if (task != null) {
                            System.out.println("Task " + task + " is started!");
                            task.run();
                        }
                    });

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        taskService.start();
        while (in.hasNextLine()) {
            String[] vals = in.nextLine().split("\\+");
            int left = Integer.parseInt(vals[0]);
            int right = Integer.parseInt(vals[1]);
            Task task = new Task(left, right);
            queue.add(task);
        }
    }
}
