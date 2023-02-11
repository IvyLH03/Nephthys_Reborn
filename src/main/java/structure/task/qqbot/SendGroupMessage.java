package structure.task.qqbot;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.message.data.MessageChain;
import structure.task.Task;

public class SendGroupMessage extends Task {
    long groupNumber;
    MessageChain message; // TODO: Change to messageChain
    Bot bot;

    /**
     * Create an untimed task to send a group chat message.
     * @param taskName the name of the task.
     * @param priority the priority of the task. 1 for the least urgent and 9 for the most urgent.
     * @param bot the bot to be used to send the message.
     * @param groupNumber the number of group chat to send the message in.
     * @param message the message chain containing message to send.
     */
    public SendGroupMessage(String taskName, int priority, Bot bot, long groupNumber, MessageChain message) {
        super(taskName, priority);
        this.groupNumber = groupNumber;
        this.message = message;
        this.bot = bot;
    }
}
