import java.util.ArrayList;
import java.util.Comparator;

/**
 * Ideal Shortest Job First Scheduler
 * 
 * @version 2017
 */
public class IdealSJFScheduler extends AbstractScheduler {

  // TODO

  private ArrayList<Process> readyQueue;
 
  public IdealSJFScheduler(){
      readyQueue = new ArrayList();
  }
  
  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {

    readyQueue.add(process);

  }

  /**
   * Removes the next process to be run from the ready queue 
   * and returns it. 
   * Returns null if there is no process to run.
   */
  public Process schedule() {

    readyQueue.sort(new Comparator<Process>() {
        @Override
        public int compare(Process p1, Process p2){
            return ((Integer)p1.getNextBurst()).compareTo((Integer)p2.getNextBurst());
        }
    });
      //System.out.println(readyQueue.toString());
    if(readyQueue.size() > 0){
        System.out.println("Scheduler selects process "+readyQueue.get(0));
        return readyQueue.remove(0);
    }
    else{
        return null;
    }
  }
}
