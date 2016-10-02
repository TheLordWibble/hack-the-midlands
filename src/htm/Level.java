package htm;

public class Level {

	private int enemies;
	private int maxSpeed;
	private int no;
	private String enemyType;
	private boolean boss;
	private int hits;
	
	public Level(int enemies, int maxSpeed, String enemyType, int no, boolean boss, int hits) {
		this.enemies = enemies;
		this.maxSpeed = maxSpeed;
		this.enemyType = enemyType;
		this.no = no;
		this.boss = boss;
		this.hits = hits;
	}

	public int getEnemies() {
		return enemies;
	}

	public void setEnemies(int enemies) {
		this.enemies = enemies;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getEnemyType() {
		return enemyType;
	}

	public void setEnemyType(String enemyType) {
		this.enemyType = enemyType;
	}

	public boolean equals(int n){
		return this.no == n;
	}
	
	public int getNo(){
		return no;
	}

	public String toString(){
		return "Level " + no;
	}
	
	public boolean isBoss(){
		return boss;
	}
	
	public int getHits(){
		return this.hits;
	}
	
}

