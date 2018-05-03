package dk.da9;

import java.util.*;

class CriticalPath {
    Task[] getCriticalPath(Set<Task> tasks) {
        //Critical duration is has been calculated on these tasks.
        HashSet<Task> completed = new HashSet<>();
        //These tasks still need to have their critical duration calculated.
        HashSet<Task> remaining = new HashSet<>(tasks);

        while (!remaining.isEmpty()) {
            Iterator<Task> iterator = remaining.iterator();

            while (iterator.hasNext()) {
                Task task = iterator.next();
                HashSet<Task> dependencies = task.getDependencies();
                if (completed.containsAll(dependencies)) {
                    int critical = 0;
                    for (Task t : dependencies) {
                        int criticalDur = t.getCriticalDuration();
                        if (criticalDur > critical) {
                            critical = criticalDur;
                        }
                    }
                    task.setCriticalDuration(critical + task.getDuration());
                    //Add task as completed.
                    completed.add(task);
                    iterator.remove();
                }
            }
        }

        //Latest start and finish
        int maxDur = getMaxDuration(tasks);
        for (Task task : tasks) {
            task.setLatestStart((maxDur - task.getCriticalDuration()) + 1);
            task.setLatestFinish((task.getLatestStart() + task.getDuration()-1));
        }

        //Get the very first task - in this case: A
        HashSet<Task> initialTasks = getInitialTasks(tasks);
        //Set earliest start and finish on all tasks
        for(Task task: initialTasks){
            task.setEarliestStart(1);
            task.setEarliestFinish(task.getDuration());
            setEarliestStartAndFinish(task);
        }

        //Set critical status
        for(Task task: completed){
            task.setCritical(task.getEarliestStart() == task.getLatestStart());
        }

        Task[] criticalPath = completed.toArray(new Task[0]);
        Arrays.sort(criticalPath, Comparator.comparing(Task::getName));

        return criticalPath;
    }

    private int getMaxDuration(Set<Task> tasks) {
        int max = -1;
        for (Task task : tasks) {
            int criticalDur = task.getCriticalDuration();
            if (criticalDur > max) {
                max = criticalDur;
            }
        }
        return max;
    }

    private HashSet<Task> getInitialTasks(Set<Task> tasks){
        HashSet<Task> remaining = new HashSet<>(tasks);
        for(Task task: tasks){
            for(Task dep: task.getDependencies()){
                remaining.remove(dep);
            }
        }
        return remaining;
    }

    private void setEarliestStartAndFinish(Task task) {
        int earlyFinish = task.getEarliestFinish();

        for (Task t : task.getDependencies()) {
            if (earlyFinish >= t.getEarliestStart()) {
                t.setEarliestStart(earlyFinish + 1);
                t.setEarliestFinish(earlyFinish + t.getDuration());
            }
            setEarliestStartAndFinish(t);
        }
    }
}
