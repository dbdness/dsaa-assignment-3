package dk.da9;

import java.util.Arrays;
import java.util.HashSet;

class Task {
    private String name;
    //Actual duration
    private int duration;
    //If the Task is on the critical path
    private int criticalDuration;
    private int earliestStart;
    private int earliestFinish;
    private int latestStart;
    private int latestFinish;
    private boolean critical;
    //The tasks this task depends on
    private HashSet<Task> dependencies = new HashSet<>();

    Task(String name, int duration, Task... dependency) {
        this.name = name;
        this.duration = duration;
        dependencies.addAll(Arrays.asList(dependency));
        this.earliestFinish = -1;
    }

    String getName() {
        return name;
    }

    int getDuration() {
        return duration;
    }

    int getCriticalDuration() {
        return criticalDuration;
    }

    void setCriticalDuration(int criticalDuration) {
        this.criticalDuration = criticalDuration;
    }

    int getEarliestStart() {
        return earliestStart;
    }

    void setEarliestStart(int earliestStart) {
        this.earliestStart = earliestStart;
    }

    int getEarliestFinish() {
        return earliestFinish;
    }

    void setEarliestFinish(int earliestFinish) {
        this.earliestFinish = earliestFinish;
    }

    int getLatestStart() {
        return latestStart;
    }

    void setLatestStart(int latestStart) {
        this.latestStart = latestStart;
    }

    void setLatestFinish(int latestFinish) {
        this.latestFinish = latestFinish;
    }

    boolean isCritical() {
        return critical;
    }

    void setCritical(boolean critical) {
        this.critical = critical;
    }

    HashSet<Task> getDependencies() {
        return dependencies;
    }

    String[] toStringArray() {
        int totalFloat = latestStart - earliestStart;
        return new String[]{name, String.valueOf(earliestStart), String.valueOf(earliestFinish),
                String.valueOf(latestStart), String.valueOf(latestFinish), String.valueOf(totalFloat),
                String.valueOf(critical)};
    }
}
