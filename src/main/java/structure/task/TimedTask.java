package structure.task;

import org.jetbrains.annotations.NotNull;
import java.util.Date;

/**
 * A kind of task that will be executed only after passing a certain time.
 */
public class TimedTask extends Task{
    private long executeTime; // the time set for the task to be executed. A task will not be executed if
                              // local time < time.
    private Task task; // the task to execute after the execution time

    /**
     * Constructor
     * @param task the task to execute after the execution time
     * @param executeTime the timestamp set for the task to be executed after
     */
    public TimedTask(Task task, long executeTime) {
        this.task = task;
        this.executeTime = executeTime;
    }

    /**
     * get the time set to execute the task.
     * @return a timestamp represent the time to execute the task.
     */
    public long getExecuteTimeLong() { return executeTime; }

    /**
     * get a formatted string represent the time to execute.
     * @return a string in "dow mon dd hh:mm:ss zzz yyyy"
     */
    public String getExecuteTimeString(){
        Date date = new Date(executeTime);
        return date.toString();
    }

    /**
     * Compare current time with the time set to execute the task.
     * @return true if time's up, false if still need to wait
     */
    public boolean timeToExecute() {
        long currentTime = new Date().getTime();
        return currentTime > executeTime;
    }

    /**
     * Return a string with information of the task.
     * @return a string in [taskName](time)
     */
    @Override
    public String toString(){
        return "(" + getExecuteTimeString() + ")" + task.toString();
    }

    @Override
    public int compareTo(@NotNull Task anotherTask) {
        if(anotherTask instanceof TimedTask){
            return Long.compare(this.executeTime, ((TimedTask) anotherTask).executeTime);
        }
        throw new ClassCastException();
    }

    /**
     * Execute the task, then return a series of tasks to add into the task queue.
     * @return an array of extra tasks that should be appended into the task queue.
     */
    public Task[] execute(){
        return task.execute();
    }

}
