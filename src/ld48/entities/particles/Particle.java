package ld48.entities.particles;

import ld48.entities.Entity;
import ld48.level.Level;

public class Particle extends Entity{
	protected int life, maxLife;
	public Particle(float x, float y, int width, int height, Level level) {
		super(x, y, width, height, level);
	}
	
	public void update() {
		super.update();
		life--;
		if (life <= 0) {
			isAlive = false;
		}
	}

}
