
// thread that will count up until 20
import java.util.concurrent.CountDownLatch;


public class CountUpThread extends Thread {
	
	//
	private CounterResource counterResource;
	private CountDownLatch threadLatch;
	
	public CountUpThread(CounterResource cr, CountDownLatch latch) {
		
		counterResource = cr;
		threadLatch = latch;
	}
	
	public void run() {
		counterResource.countUp(new Object(), threadLatch);
	}

}