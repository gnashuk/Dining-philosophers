
public class Philosopher implements Runnable {
	private DrawPanel panel;
	private int index;
	private Chopstick firstStick, secondStick;
	boolean rh = false;
	static double speed = 1;
	
	public Philosopher(DrawPanel p, int id, Chopstick l, Chopstick r) {
		panel = p;
		index = id;
		if(r.getId() < l.getId()) {
			firstStick = r;
			secondStick = l;
			rh = true;
		} else {
			firstStick = l;
			secondStick = r;
			rh = false;
		}
	}

	@Override
	public void run() {
		while(true) {
//			
			try {
				firstStick.hold(index);
				if(rh) panel.setRightHand(index, true);
				else panel.setLeftHand(index, true);
				panel.repaint();
				secondStick.hold(index);
				if(rh) panel.setLeftHand(index, true);
				else panel.setRightHand(index, true);
				panel.setPhilosopher(index, true);
				panel.repaint();
				if(speed < 0) Thread.sleep((int)((Math.random() * 7500) * (-speed)));
				else Thread.sleep((int)((Math.random() * 7500) / speed));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			firstStick.release(index);
			secondStick.release(index);
			panel.setLeftHand(index, false);
			panel.setRightHand(index, false);
			panel.setPhilosopher(index, false);
			panel.repaint();
			try {
				if(speed < 0) Thread.sleep((int)((Math.random() * 5000) * (-speed)));
				else Thread.sleep((int)((Math.random() * 5000) / speed));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

}
