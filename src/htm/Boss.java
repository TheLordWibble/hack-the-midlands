package htm;

import java.util.ArrayList;
import java.util.Random;

public class Boss extends Alien {

	private int hits;
	private int health;
	private boolean up;
	private boolean increased1;
	private boolean increased2;
	private ArrayList<Missile> attacks;
	private int z = 0;

	public Boss(int x, int y, int hits, String enemyType, int maxSpeed, ArrayList<Missile> attacks) {
		super(x, y, enemyType, maxSpeed, hits);
		this.up = true;
		this.hits = hits;
		this.health = hits;
		this.speed = maxSpeed;
		this.increased1 = false;
		this.increased2 = false;
		this.attacks = attacks;
		initBoss();

	}

	private void initBoss() {
		loadImage(enemyType);
		getImageDimensions();
	}

	private void fire() {
		Random r = new Random();
		
		Missile miss = new Missile(500, y + r.nextInt(300), 500, Links.MARK_W);
		miss.setMissileDirection(false);
		attacks.add(miss);
	}

	public String getEnemy() {
		return this.enemyType;
	}

	public void move() {

		if (up) {
			if (y > 0) {
				y -= speed;
				z++;
			} else {
				up = false;
			}
		} else if (y < 450) {
			y += speed;
			z++;
		} else {
			up = true;
		}

		Random r = new Random();
		
		
		if (z % (r.nextInt(20)+20) == 0) {
			fire();
		}
	}

	public int getHits() {
		return this.hits;
	}

	@Override
	public void gotHit() {
		health--;
		System.out.println(health);
		if ((double) health / (double) hits < 0.5 && increased1 == false) {
			speed = speed * 2;
			increased1 = true;
		} else if ((double) health / (double) hits < 0.25 && increased2 == false) {
			speed = speed * 2;
			increased2 = true;
		}

	}

	public ArrayList<Missile> getMissiles(){
		return this.attacks;
	}
	
	@Override
	public int getHealth() {
		return this.health;
	}

}
