package structure.task;

import org.jetbrains.annotations.NotNull;
import java.util.Date;

/**
 * Basic class for a test that contains a series of actions to be executed
 */
public class Task implements Comparable<Task> {

    private String taskName;
    private int priority; // the priority of the task. 1 is for least urgent and 9 is for most urgent
    private long time; // the time set for the task to be executed. A task will not be executed if
                      // local time < time. It will be temporarily dismissed.


    /**
     * get the priority of the task.
     * @return an integer represents the priority of the task. 1 is for the least urgent and 9 is for
     *         the most urgent.
     */
    public int getPriority() { return priority; }

    /**
     * get the time set to execute the task.
     * @return a timestamp represent the time to execute the task.
     */
    public long getTime() { return time; }

    /**
     * Compare current time with the time set to execute the task.
     * @return true if time's up, false if still need to wait
     */
    public boolean timeToExecute() {
        long currentTime = new Date().getTime();
        if(currentTime > time) return true;
        return false;
    }

    /**
     * Compare this task with another task.
     * @param anotherTask the object to be compared.
     * @return 1 if this.priority > anotherTask.priority
     *         0 if this.priority == anotherTask.priority
     *         -1 if this.priority < anotherTask.priority
     */
    @Override
    public int compareTo(@NotNull Task anotherTask){
        if(priority > anotherTask.priority) return 1;
        if(priority == anotherTask.priority) return 0;
        return -1;
    }

    @Override
    public String toString(){
        return "[" + taskName + "](" + time + ")";
    }



    /**
     * Something the task can execute by itself. Do nothing by default.
     */
    public void execute(){

    }

}
