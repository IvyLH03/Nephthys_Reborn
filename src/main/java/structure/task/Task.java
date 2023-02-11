package structure.task;

import net.mamoe.mirai.Bot;
import org.jetbrains.annotations.NotNull;

/**
 * Basic class for a test that contains a series of actions to be executed
 */
public class Task implements Comparable<Task> {

    private String taskName;
    private int priority; // the priority of the task. 1 is for least urgent and 9 is for most urgent

    /**
     * Create a task.
     */
    public Task(){this.taskName = "未命名任务"; this.priority = 5;}

    /**
     * Create a task.
     * @param taskName the name of the task
     */
    public Task(String taskName){
        this.taskName = taskName;
        this.priority = 5;
    }

    /**
     * Create a task.
     * @param taskName the name of the task
     * @param priority the priority of the task.
     *                 Smaller priority indicates more urgent tasks. Default is 5.
     */
    public Task(String taskName, int priority){
        this(taskName);
        this.priority = priority;
    }


    /**
     * get the priority of the task.
     * @return an integer represents the priority of the task.
     *         Smaller priority indicates more urgent tasks. Default is 5.
     */
    public int getPriority() { return priority; }



    /**
     * Compare this task with another task.
     * @param anotherTask the object to be compared.
     * @return 1 if this.priority > anotherTask.priority
     *         0 if this.priority == anotherTask.priority
     *         -1 if this.priority < anotherTask.priority
     */
    @Override
    public int compareTo(@NotNull Task anotherTask){
        return Integer.compare(priority, anotherTask.priority);
    }

    @Override
    public String toString(){
        return "[" + taskName + "]";
    }



    /**
     * Execute the task, then return a series of tasks to add into the task queue.
     * @return an array of extra tasks that should be appended into the task queue.
     */
    public Task[] execute(){
        // TODO: add logging
        System.out.println("Wrong method was called! You need to override execute().");
        return new Task[0];
    }

}
