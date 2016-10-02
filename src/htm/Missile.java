package htm;

import java.awt.Image;

public class Missile extends Sprite {

	private int width = 600;
	private final int MISSILE_SPEED = 4;
	
	public Missile(int x, int y, int w, String image) {
		super(x, y);
		this.width = w;
		initMissile(image);
	}

	
	private void initMissile(String image){
		loadImage(image);
		getImageDimensions();
	}
	
	public void setMissile(int i){
		switch(i){
		case PlayerSquare.ACHIM:
			loadImage(Links.ACHIM_W);
			break;
		case PlayerSquare.MARTIN:
			loadImage(Links.MARTIN_W);
			break;
		}
	}
	
	public void move(){
		x += MISSILE_SPEED;
		
		if (x > width){
			vis = false;
		}
	}
}
