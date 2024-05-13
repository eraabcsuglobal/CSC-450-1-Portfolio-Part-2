/* Name: Evan Raab
 * Project Name: CSC450-Portfolio Project: Part 2
 * Project Purpose: Use concurrency to update counter integer
 * Algorithm Used: Within two threads, have one method increment integer using while method and the other thread decrement. Use locks, latches, and thread sleep to ensure that counter is only changed by one thread at a single time.
 * Program Inputs: None
 * Program Outputs: Integer Counter
 * Program Errors: InterruptedException if thread is interrupted
*/

import java.util.concurrent.CountDownLatch;


public class CountingThreads {
    
	public static void main(String args[]) {
    	
    	// create counter resource that will contain the counter shared data as well as count up and count down methods
        CounterResource counter = new CounterResource();
        
        // create latch and threads
        CountDownLatch latch = new CountDownLatch(1); // Create a latch to coordinate threads

        CountUpThread thread1 = new CountUpThread(counter, latch);
        CountDownThread thread2 = new CountDownThread(counter, latch);

        System.out.println("Current count before threads start: " + counter.getCurrentCount());

        // begin count up thread
        thread1.start();
        try {
            thread1.join();
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        System.out.println("Current count after thread 1 ends: " + counter.getCurrentCount());
        
        // when count down thread is complete, begin thread 2
        thread2.start();
        try {
            thread2.join();
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        System.out.println("Current count after thread 2 ends: " + counter.getCurrentCount());
    }
}
