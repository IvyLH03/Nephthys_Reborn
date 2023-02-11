package structure.task;

public class PrintTask extends Task{
    private String message;

    public PrintTask(String message){
        super("Send message");
        this.message = message;
    }

    @Override
    public Task[] execute() {
        System.out.println(message);
        return new Task[0];
    }
}
