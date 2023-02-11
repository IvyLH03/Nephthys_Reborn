package structure.task.qqbot;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.Message;
import structure.task.Task;

public class SendGroupMessage extends Task {
    private Group group;
    private Message message;
    private Bot bot;



    /**
     * Create an untimed task to send a group chat message.
     * @param bot the bot that is used to send the message
     * @param group the group chat to send the message in.
     * @param message the message chain containing message to send.
     */
    public SendGroupMessage(Bot bot, Group group, Message message) {
        super("发送群聊消息", 5);
        this.group = group;
        this.message = message;
        this.bot = bot;
    }

    /**
     * Create an untimed task to send a group chat message.
     * @param bot the bot that is used to send the message
     * @param group the group chat to send the message in.
     * @param message the message chain containing message to send.
     * @param priority the priority of the task.
     *                 Smaller priority indicates more urgent tasks. Default is 5.
     */
    public SendGroupMessage(Bot bot, Group group, Message message, int priority) {
        super("发送群聊消息", priority);
        this.group = group;
        this.message = message;
        this.bot = bot;
    }

    /**
     * Create an untimed task to send a group chat message.
     * @param taskName the name of the task.
     * @param bot the bot that is used to send the message
     * @param group the group chat to send the message in.
     * @param message the message chain containing message to send.
     * @param priority the priority of the task.
     *                 Smaller priority indicates more urgent tasks. Default is 5.
     */
    public SendGroupMessage(String taskName, Bot bot, Group group, Message message, int priority) {
        super(taskName, priority);
        this.group = group;
        this.message = message;
        this.bot = bot;
    }


    @Override
    public Task[] execute() {
        group.sendMessage(message);
        return new Task[0];
    }
}
