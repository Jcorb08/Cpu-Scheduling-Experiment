import java.util.ArrayList;
import java.util.Properties;
import java.util.Comparator;

/**
 * Feedback Round Robin Scheduler
 * 
 * @version 2017
 */
public class FeedbackRRScheduler extends AbstractScheduler {

  // TODO
  // priority queue using Process.getPriority();
  private ArrayList<Process> readyQueue;
  private int timeQuantum;
  
  public FeedbackRRScheduler(){
      readyQueue = new ArrayList();
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
    if(usedFullTimeQuantum){
        //process.id;
        System.out.println(usedFullTimeQuantum);
        process.setPriority(process.getPriority()+1);
        readyQueue.add(process);
    }
    else{
        readyQueue.add(process);
    }

  }
  
  @Override
  public boolean isPreemptive(){
      return true;
  }
  /**
   * Removes the next process to be run from the ready queue 
   * and returns it. 
   * Returns null if there is no process to run.
   */
  public Process schedule() {

    // TODO
    readyQueue.sort(new Comparator<Process>() {
        @Override
        public int compare(Process p1, Process p2){
            return ((Integer)p1.getPriority()).compareTo((Integer)p2.getPriority());
        }
    });
    System.out.println(readyQueue.toString());
    if(readyQueue.size() > 0){
        System.out.println("Scheduler selects process "+readyQueue.get(0));
        return readyQueue.remove(0);
    }
    else{
        return null;
    }
  }
}
