public class Chopstick {
	private int id;
	private boolean isLocked = false;
	
	public Chopstick(int i) {
		id = i;
	}
	
	public int getId() {
		return id;
	}
	
	public synchronized void hold(int i) throws InterruptedException {
		while(isLocked) {
			wait();
//			System.out.println(id + "Locked by " + i);
		}
		isLocked = true;
//		System.out.println(id + "Lock was taken by " + i);
	}
	
	public synchronized void release(int i) {
		isLocked = false;
//		System.out.println(id + "Lock was released" + " by " + i);
		notify();
	}
}
