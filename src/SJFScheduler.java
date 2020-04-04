import java.util.ArrayList;
import java.util.Comparator;
import java.util.Properties;

/**
 * Shortest Job First Scheduler
 * 
 * @version 2017
 */
public class SJFScheduler extends AbstractScheduler {

  // TODO

  private ArrayList<Process> readyQueue; 
  // the t[n-1] bursts
  private ArrayList<Integer> bursts;
  private int alphaBurst;
    
  public SJFScheduler(){
      readyQueue = new ArrayList();
      bursts = new ArrayList();
  }
  
  @Override
  public void initialize(Properties parameters){
      try{
          bursts.add(0,
                  Integer.parseInt(parameters.getProperty("initialBurstEstimate")));
          alphaBurst = (int)
                  Double.parseDouble(parameters.getProperty("alphaBurstEstimate"));
          
      } catch(Exception e){
          System.out.println(e);
          System.out.println("Wrong parameters");
      }
  }
  
  // returns the predicted next burst 
  // T[n+1] = a * t[n] + (1-a) * t[n-1]
  public int getBurst(Process p){
     
    if(p.getRecentBurst() == -1){
      // return 1st case
      return bursts.get(0);
    }
    else if (bursts.get(p.getId()) == 0){
      // 2nd case
      bursts.add(p.getId(), p.getRecentBurst());
      return alphaBurst * p.getRecentBurst() + (1-alphaBurst) * bursts.get(0);  
    }
    else{
      // other case
      bursts.add(p.getId(), p.getRecentBurst());
      return alphaBurst * p.getRecentBurst() + (1-alphaBurst) * bursts.get(p.getId());
    }


  } 
  
  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {

    // TODO
    // inital add to bursts 
    bursts.add(process.getId(), 0);
    readyQueue.add(process);

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
            return ((Integer)getBurst(p1)).compareTo((Integer)getBurst(p2));
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
