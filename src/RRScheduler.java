import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

/**
 * Round Robin Scheduler
 * 
 * @version 2017
 */
public class RRScheduler extends AbstractScheduler {

  // TODO
  private Queue<Process> readyQueue;
  private int timeQuantum;
 
  public RRScheduler(){
    readyQueue = new LinkedList<Process>();
    
  }
  
  @Override
  public void initialize(Properties parameters){
      
    try{
        timeQuantum = 
                Integer.parseInt(parameters.getProperty("timeQuantum"));
    }  catch(Exception e){
        System.out.println(e);
        System.out.println("No time Quantum!");  
    }
      
  }
  
  @Override
  public int getTimeQuantum(){
      return timeQuantum;
  }

  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {
    // TODO
    // needs a context switch, if its the end of the time quantum, switch to next in queue
    // works need to look into it - to make sure its correct (blocking??)
    if(usedFullTimeQuantum){
        //process.id;
        System.out.println(usedFullTimeQuantum);
        readyQueue.offer(process);
    }
    else{
        readyQueue.offer(process);
    }
    for(Process p : readyQueue){
        System.out.println(p.id);
    }    

      
  }

  /**
   * Removes the next process to be run from the ready queue 
   * and returns it. 
   * Returns null if there is no process to run.
   */
  public Process schedule() {
    // TODO
    System.out.println("Scheduler selects process "+readyQueue.peek());
    return readyQueue.poll();
  }
}
