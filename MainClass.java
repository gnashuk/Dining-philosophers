import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.geom.*;

//	Dinning Philosophers
//	(c) 2015 Oleg Hnashuk
//
//	Schema of positioning of the philosophers and chopsticks at the table
//  All philosophers and chopsticks are indexed
//	Each philosopher takes a chopstick with smaller index first
//
//	   p1		 p2	
//			c1
//      c5      c2
//	 p5            p3
//        c4   c3
//			p5
//

public class MainClass {
	JSlider slider;

	public static void main(String[] args) {
		MainClass gui = new MainClass();
		gui.go();
	}
	
	public void go() {
		// GUI creation
		JFrame frame = new JFrame("Philosophers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DrawPanel drawPanel = new DrawPanel(425, 300);
		slider = new JSlider(-200, 200, 0);
		slider.addChangeListener(new SliderListener());
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		JPanel sliderPanel = new JPanel();
		JPanel labl = new JPanel();
		labl.add(new JLabel("Speed"));
		JPanel colorPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		JPanel yellow = new JPanel();
		yellow.setBackground(Color.yellow);
		yellow.add(new JLabel("Thinking"));
		yellow.setBorder(BorderFactory.createLoweredBevelBorder());
		JPanel green = new JPanel();
		green.setBackground(Color.green);
		green.setBorder(BorderFactory.createLoweredBevelBorder());
		green.add(new JLabel("Eating"));
		colorPanel.add(yellow);
		colorPanel.add(green);
		sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
		sliderPanel.add(Box.createHorizontalGlue());
		sliderPanel.add(colorPanel);
		sliderPanel.add(labl);
		sliderPanel.add(slider);
		background.add(BorderLayout.CENTER, drawPanel);
		background.add(BorderLayout.SOUTH, sliderPanel);
		frame.getContentPane().add(background);
		frame.setSize(425, 450);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		// Creating Philosopher threads and chopsticks
		Chopstick c1 = new Chopstick(1);
		Chopstick c2 = new Chopstick(2);
		Chopstick c3 = new Chopstick(3);
		Chopstick c4 = new Chopstick(4);
		Chopstick c5 = new Chopstick(5);
		Philosopher p1 = new Philosopher(drawPanel, 1, c1, c5);
		Philosopher p2 = new Philosopher(drawPanel, 2, c2, c1);
		Philosopher p3 = new Philosopher(drawPanel, 3, c3, c2);
		Philosopher p4 = new Philosopher(drawPanel, 4, c4, c3);
		Philosopher p5 = new Philosopher(drawPanel, 5, c5, c4);
		Thread thread1 = new Thread(p1);
		Thread thread2 = new Thread(p2);
		Thread thread3 = new Thread(p3);
		Thread thread4 = new Thread(p4);
		Thread thread5 = new Thread(p5);
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();

	}
	
	public class SliderListener implements ChangeListener {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			int val = slider.getValue();
			if(val < 0) {
				Philosopher.speed = -1 + val / 100; 
			} else if(val > 0) {
				Philosopher.speed = 1 + val / 100;
			} else {
				Philosopher.speed = 1;
			}
		}
		
	}
}
