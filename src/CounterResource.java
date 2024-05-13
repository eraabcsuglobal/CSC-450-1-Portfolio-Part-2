import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// class for counter integer data that is shared between threads.
// also contains logic for locking data and the count down and count up methods
public class CounterResource {

	private final Lock countUpQueueLock = new ReentrantLock();
	private final Lock countDownQueueLock = new ReentrantLock();
	
	// shared integer data
	private int counter;
	
	// increments counter until it reaches 20
    public void countUp(Object document, CountDownLatch latch) {
        final Lock countUpLock = this.countUpQueueLock;
        // locks data
        countUpLock.lock();
        try {
        	// put thread to sleep for short amount of time
            Long duration = (long) (Math.random() * 10000);
            Thread.sleep(duration);
            while (counter < 20) {
                counter++;
            }
            latch.countDown();
    	} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			countUpLock.unlock();
		}
    }

    public void countDown(Object document, CountDownLatch latch) {
        final Lock countDownLock = this.countDownQueueLock;
        try {
            latch.await(); // Wait until counting up is done
            countDownLock.lock();
            Long duration = (long) (Math.random() * 10000);
            // while counter value is above 0, count down
            while (counter > 0) {
                counter--;
            }
            // put thread to sleep for short amount of time
            Thread.sleep(duration);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			countDownLock.unlock();
		}
    }

  
	
	public int getCurrentCount()
	{
		return counter;
	}

}