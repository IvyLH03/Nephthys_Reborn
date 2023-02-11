package process;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import structure.task.PrintTask;
import structure.task.Task;
import structure.task.TimedTask;
import java.util.PriorityQueue;

/**
 * Main class.
 */
public class Main {
    Bot bot = BotFactory.INSTANCE.newBot(1l,"",new BotConfiguration(){{
        setProtocol(MiraiProtocol.ANDROID_PAD);
    }});

    private static PriorityQueue<Task> untimedQueue = new PriorityQueue<>();
    // private static PriorityQueue<TimedTask> timedQueue = new PriorityQueue<>();

    private static void initializeTaskQueues(){


        untimedQueue.add(new PrintTask("Hello world!"));
    }

    public static void main(String[] args) {
        initializeTaskQueues();
        while(true){
            if(!untimedQueue.isEmpty()){
                Task task = untimedQueue.remove();
                task.execute();
            }
        }
    }

}
