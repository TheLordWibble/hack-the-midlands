package htm;

import java.util.Random;

public class Alien extends Sprite {

	private final int INITIAL_X = 1000;
	private int speed;
	
	public Alien(int x, int y, String enemyType, int maxSpeed) {
		super(x, y);

		Random r = new Random();
		speed = r.nextInt(maxSpeed) + 1;
		
		initAlien(enemyType);
	}
	
	private void initAlien(String enemyType){
		loadImage(enemyType);
		getImageDimensions();
	}

	public void move() {

        if (x < 0) {
            x = INITIAL_X;
        }

        x -= speed;
        
    }
	
}
