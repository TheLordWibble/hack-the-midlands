package htm;

import java.util.Random;

public class Alien extends Sprite {

	private final int INITIAL_X = 1000;
	protected int speed;
	protected String enemyType;
	protected int health;
	
	public Alien(int x, int y, String enemyType, int maxSpeed, int hits) {
		super(x, y);

		this.enemyType = enemyType;
		Random r = new Random();
		this.health = hits;
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
	
	public void gotHit(){
		 health--;
		System.out.println(health);
	}
	
	public int getHealth(){
		return this.health;
	}
	
}
