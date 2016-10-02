package htm;

import java.util.Random;

public class Alien extends Sprite {

	private final int INITIAL_X = 1000;
	private int speed;
	
	public Alien(int x, int y) {
		super(x, y);

		Random r = new Random();
		speed = r.nextInt(5) + 1;
		
		initAlien();
	}
	
	private void initAlien(){
		loadImage(Links.SABS);
		getImageDimensions();
	}

	public void move() {

        if (x < 0) {
            x = INITIAL_X;
        }

        x -= speed;
    }
	
}
