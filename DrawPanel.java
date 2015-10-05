import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.QuadCurve2D;

import javax.swing.JPanel;

class DrawPanel extends JPanel {
	boolean[] phil1 = {false, false, false};
	boolean[] phil2 = {false, false, false};
	boolean[] phil3 = {false, false, false};
	boolean[] phil4 = {false, false, false};
	boolean[] phil5 = {false, false, false};
	boolean[] currentPhil = {false, false, false};
	float q = 1;
	
	public DrawPanel(int w, int h) {
		this.setSize(w, h);
		q = (float)w / (float)850;
	}
	
	public void paintComponent(Graphics g) {
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				q = (float)e.getComponent().getWidth() / (float)850;
			}
		});
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		currentPhil = phil1;
		drawPhilosopher(g, 1);
		currentPhil = phil2;
		drawPhilosopher(g, 2);
		currentPhil = phil3;
		drawPhilosopher(g, 3);
		currentPhil = phil4;
		drawPhilosopher(g, 4);
		currentPhil = phil5;
		drawPhilosopher(g, 5);
		
		g.setColor(Color.black);
		g.fillOval((int)(q*305),(int)(q* 235),(int)(q* 200),(int)(q* 200));
		g.setColor(Color.white);
		g.fillOval((int)(q*315),(int)(q* 245),(int)(q* 180),(int)(q* 180));
		g.setColor(new Color(51, 0, 0));
		
		g2.setStroke(new BasicStroke(q*5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
		if(!(phil1[1] || phil2[2])) g2.drawLine((int)(q*405),(int)(q* 225),(int)(q* 405),(int)(q* 305));
		if(!(phil2[1] || phil3[2])) g2.drawLine((int)(q*440),(int)(q* 325),(int)(q* 510),(int)(q* 285));
		if(!(phil3[1] || phil4[2])) g2.drawLine((int)(q*430),(int)(q* 365),(int)(q* 485),(int)(q* 415));
		if(!(phil4[1] || phil5[2])) g2.drawLine((int)(q*325),(int)(q* 415),(int)(q* 380),(int)(q* 365));
		if(!(phil5[1] || phil1[2])) g2.drawLine((int)(q*300),(int)(q* 285),(int)(q* 370),(int)(q* 325));
	}
	
	
	
	private void drawPhilosopher(Graphics g, int n) {
		int x = 0;
		int y = 0;
		
		if(n == 1) {
			x = (int)(175);
			y = (int)(75);
		} else if(n == 2) {
			x = (int)(475);
			y = (int)(75);
		} else if(n == 3) {
			x = (int)(550);
			y = (int)(300);
		} else if(n == 4) {	
			x = (int)(325);
			y = (int)(450);
		} else if(n == 5) {
			x = (int)(100);
			y = (int)(300);
		}
		
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.black);
		g.fillOval((int)(q*x), (int)(q*y), (int)(q*160), (int)(q*160));
			
		if(currentPhil[0]) g.setColor(Color.green);
		else g.setColor(Color.yellow);
		g.fillOval((int)(q*(x + 5)), (int)(q*(y + 5)), (int)(q*150), (int)(q*150));
			
		g.setColor(Color.black);
		g.fillOval((int)(q*(x + 40)), (int)(q*(y + 40)), (int)(q*20), (int)(q*30));
		g.fillOval((int)(q*(x + 100)), (int)(q*(y + 40)), (int)(q*20), (int)(q*30));
		QuadCurve2D c = new QuadCurve2D.Float();
		g2.setStroke(new BasicStroke(q*5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
//		smile
		if(currentPhil[0]) {
			c.setCurve((int)(q*(x + 50)), (int)(q*(y + 115)), (int)(q*(x + 80)), (int)(q*(y + 145)), (int)(q*(x + 110)), (int)(q*(y + 115)));
			g2.draw(c);
		} else g2.drawLine((int)(q*(x + 50)), (int)(q*(y + 120)), (int)(q*(x + 110)), (int)(q*(y + 120)));
		g2.setFont(new Font("Serif", Font.BOLD, 40));
		g2.drawString("", x + 40, y + 190);
		
//		drawing hands
		if(currentPhil[1]) {
			c.setCurve((int)(q*(x + 170)), (int)(q*(y + 170)), (int)(q*(x + 170)), (int)(q*(y + 140)), (int)(q*(x + 140)), (int)(q*(y + 130)));
			g2.draw(c);
			g2.setColor(new Color(51, 0, 0));
			g2.setStroke(new BasicStroke(q*6f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
			g2.drawLine((int)(q*(x + 180)), (int)(q*(y + 175)), (int)(q*(x + 110)), (int)(q*(y + 135)));
		}
		if(currentPhil[2]) {
			c.setCurve((int)(q*(x - 10)), (int)(q*(y + 170)), (int)(q*(x - 10)), (int)(q*(y + 140)), (int)(q*(x + 20)), (int)(q*(y + 130)));
			g2.draw(c);
			g2.setColor(new Color(51, 0, 0));
			g2.setStroke(new BasicStroke(q*6f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
			g2.drawLine((int)(q*(x - 20)), (int)(q*(y + 175)), (int)(q*(x + 50)), (int)(q*(y + 135)));
		}
	}
	
	public void setPhilosopher(int n, boolean b) {
		if(n == 1) phil1[0] = b;
		if(n == 2) phil2[0] = b;
		if(n == 3) phil3[0] = b;
		if(n == 4) phil4[0] = b;
		if(n == 5) phil5[0] = b;
	}
	
	public void setLeftHand(int n, boolean b) {
		if(n == 1) phil1[1] = b;
		if(n == 2) phil2[1] = b;
		if(n == 3) phil3[1] = b;
		if(n == 4) phil4[1] = b;
		if(n == 5) phil5[1] = b;
	}
	
	public void setRightHand(int n, boolean b) {
		if(n == 1) phil1[2] = b;
		if(n == 2) phil2[2] = b;
		if(n == 3) phil3[2] = b;
		if(n == 4) phil4[2] = b;
		if(n == 5) phil5[2] = b;
	}
}
