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

        untimedQueue.add(new PrintTask("Hello World!"));
    }

    /**
     * Main =)
     */
    public static void main(String[] args) {

        System.out.println("Starting initialization...");
        initializeTaskQueues();
        botInitialize();
        System.out.println("==Initialization Complete!==");

        Task[] tasksToAdd;

        while(true){
            if(!timedQueue.isEmpty() && timedQueue.peek().timeToExecute()){
                TimedTask task = timedQueue.remove();
                tasksToAdd = task.execute();
                for(Task t: tasksToAdd){
                    if(t instanceof TimedTask) timedQueue.add((TimedTask) t);
                    else if(t instanceof Task) untimedQueue.add(t);
                }
            }

            if(!untimedQueue.isEmpty()){
                Task task = untimedQueue.remove();
                tasksToAdd = task.execute();
                for(Task t: tasksToAdd){
                    if(t instanceof TimedTask) timedQueue.add((TimedTask) t);
                    else if(t instanceof Task) untimedQueue.add(t);
                }
            }
        }
    }

}
