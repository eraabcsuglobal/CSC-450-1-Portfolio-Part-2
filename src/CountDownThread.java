// thread for when counter reaches 20, will begin to count down to 0
import java.util.concurrent.CountDownLatch;
public class CountDownThread extends Thread {
	
	private CounterResource counterResource;
	private CountDownLatch threadLatch;
	
	public CountDownThread(CounterResource cr, CountDownLatch latch) {
		counterResource = cr;
		threadLatch = latch;
	}
	
	public void run() {
		counterResource.countDown(new Object(), threadLatch);
	}


}