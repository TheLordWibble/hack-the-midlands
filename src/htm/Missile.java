package htm;

import java.awt.Image;

public class Missile extends Sprite {

	private int width =1000;
	private int MISSILE_SPEED = 4;
	private boolean right;

	public Missile(int x, int y, int w, String image) {
		super(x, y);
		this.width = w;
		initMissile(image);
		right = true;
	}

	private void initMissile(String image) {
		loadImage(image);
		getImageDimensions();
	}

	public void setMissile(int i) {
		switch (i) {
		case Player.ACHIM:
			loadImage(Links.ACHIM_W);
			break;
		case Player.MARTIN:
			loadImage(Links.MARTIN_W);
			break;
		}
	}

	public void setMissileSpeed(int n) {
		MISSILE_SPEED = n;
	}

	public void setMissileDirection(boolean right) {
		this.right = right;
	}

	public void move() {

		if (right) {
			x += MISSILE_SPEED;

			if (x > width) {
				vis = false;
			}
		} else {
			x -= MISSILE_SPEED;

			if (x < 0) {
				vis = false;
			}
		}
	}
}
