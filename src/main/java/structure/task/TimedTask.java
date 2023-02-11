package structure.task;

import java.util.Date;

/**
 * A kind of task that will be executed only after passing a certain time.
 */
public class TimedTask extends Task {
    private long executeTime; // the time set for the task to be executed. A task will not be executed if
                              // local time < time.

    /**
     * Constructor
     * @param taskName the name of the task
     * @param executeTime the timestamp set for the task to be executed after
     */
    public TimedTask(String taskName, long executeTime) {
        super(taskName);
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
        if(currentTime > executeTime) return true;
        return false;
    }

    /**
     * Return a string with information of the task.
     * @return a string in [taskName](time)
     */
    @Override
    public String toString(){
        return super.toString() + "(" + getExecuteTimeString() + ")";
    }
}
