package htm;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PlayerSquare extends Sprite {

	private int dx, dy;
	private ArrayList missiles;
	private Image image;
	public static final int ACHIM = 0;
	public static final int MARTIN = 1;

	public PlayerSquare(int x, int y) {
		super(x, y);
		initPlayer();
	}

	private void initPlayer() {
		missiles = new ArrayList();
		loadImage("src/images/achim-head.png");
		getImageDimensions();

	}
	
	public void setPlayer(int p){
		switch(p){
		case ACHIM:
			loadImage("src/images/achim-head.png");
			break;
		case MARTIN:
			loadImage("src/images/blue-square.png");
			
		}
	}

	public void move() {
		x += dx;
		y += dy;
	}

	public ArrayList getMissiles() {
		return missiles;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_SPACE:
			fire();
			break;
		case KeyEvent.VK_LEFT:
			dx = -1;
			break;
		case KeyEvent.VK_RIGHT:
			dx = 1;
			break;
		case KeyEvent.VK_UP:
			dy = -1;
			break;
		case KeyEvent.VK_DOWN:
			dy = 1;
		}
	}

	private void fire() {
		missiles.add(new Missile(x + width, y + height / 2));
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_LEFT:
			dx = 0;
			break;
		case KeyEvent.VK_RIGHT:
			dx = 0;
			break;
		case KeyEvent.VK_UP:
			dy = 0;
			break;
		case KeyEvent.VK_DOWN:
			dy = 0;
		}
	}

}
