package htm;

public abstract class Entity {

	protected int health;
	protected int att;
	protected int def;
	protected int spd;
	
	
	public Entity(int health, int att, int def, int spd) {
		this.health = health;
		this.att = att;
		this.def = def;
		this.spd = spd;
	}
	
	

}
