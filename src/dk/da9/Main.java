package dk.da9;

import java.util.Arrays;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        CriticalPath criticalPath = new CriticalPath();
        Task e = new Task("E", 20);
        Task g = new Task("G", 5, e);
        Task d = new Task("D", 10, e);
        Task h = new Task("H", 15, e);
        Task c = new Task("C", 5, d, g);
        Task f = new Task("F", 15, g);
        Task b = new Task("B", 20, c);
        Task a = new Task("A", 10, b, f, h);

        HashSet<Task> tasks = new HashSet<>(Arrays.asList(e, g, d, h, c, f, b, a));

        Task[] critical = criticalPath.getCriticalPath(tasks);
        printCriticalPath(critical);
        print(critical);
    }

    private static void print(Task[] tasks) {
        String printFormat = "%1$-10s %2$-5s %3$-5s %4$-5s %5$-5s %6$-5s %7$-10s\n";
        System.out.format(printFormat, "Task", "ES", "EF", "LS", "LF", "TF", "Critical?");
        for (Task t : tasks)
            System.out.format(printFormat, (Object[]) t.toStringArray());
    }

    private static void printCriticalPath(Task[] tasks) {
        System.out.println("Critical path:");
        for (Task task : tasks) {
            if (task.isCritical()) {
                System.out.printf("[ %s ]->", task.getName());
            }
        }
        System.out.println("\n");
    }
}

