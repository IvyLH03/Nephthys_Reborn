package process;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;

import structure.task.PrintTask;
import structure.task.Task;
import structure.task.TimedTask;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Properties;

/**
 * Main class.
 */
public class Main {
    private static Bot bot;
    private static PriorityQueue<Task> untimedQueue;
    private static PriorityQueue<TimedTask> timedQueue;


    /**
     * Initialize qq bot.
     */
    private static void botInitialize(){

        try{
            Properties properties = new Properties();
            FileInputStream configFile = new FileInputStream("config.properties");
            properties.load(configFile);
            configFile.close();

            long botQQ = Long.parseLong(properties.getProperty("botQQ"));
            String botPassword = properties.getProperty("botPassword");

            bot = BotFactory.INSTANCE.newBot(botQQ,botPassword,new BotConfiguration(){{
                setProtocol(MiraiProtocol.ANDROID_PAD);
            }});
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }

    /**
     * Initialize task queues.
     * Put routine tasks into the queues.
     */
    private static void initializeTaskQueues(){
        untimedQueue = new PriorityQueue<>();
        timedQueue = new PriorityQueue<>();

        Date date = new Date();
        TimedTask task1 = new TimedTask(new PrintTask("Print1"),
                date.getTime() + Duration.ofSeconds(1).toMillis());
        TimedTask task2 = new TimedTask(new PrintTask("Print2"),
                date.getTime() + Duration.ofSeconds(5).toMillis());
        TimedTask task3 = new TimedTask(new PrintTask("Print3"),
                date.getTime() + Duration.ofSeconds(10).toMillis());

        timedQueue.add(task1);
        timedQueue.add(task2);
        timedQueue.add(task3);
    }

    /**
     * Main =)
     */
    public static void main(String[] args) {

        System.out.println("Starting initialization...");
        initializeTaskQueues();
        botInitialize();
        System.out.println("==Initialization Complete!==");

        while(true){
            if(!timedQueue.isEmpty() && timedQueue.peek().timeToExecute()){
                TimedTask task = timedQueue.remove();
                task.execute();
            }

            if(!untimedQueue.isEmpty()){
                Task task = untimedQueue.remove();
                task.execute();
            }
        }
    }

}
