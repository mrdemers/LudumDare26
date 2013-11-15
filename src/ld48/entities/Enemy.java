package ld48.entities;

import ld48.Art;
import ld48.Bitmap;
import ld48.level.Level;

public class Enemy extends Mob {
	public int hurtTime = 0;
	public int damage;
	public Enemy(float x, float y, int width, int height, Level level) {
		super(x, y, width, height, level);
		health = 10;
		damage = 3;
	}

	boolean stop = true;
	public void update() {
		super.update();
		if (hurtTime > 0) {
			hurtTime--;
		}
	}
	
	public void render(Bitmap screen) {
		int frame = walkTime/5%8;
		if (right) {
			screen.blendDraw(Art.enemyGuy[frame], getX(), getY());
		} else {
			screen.flipBlend(Art.enemyGuy[frame], getX(), getY());
		}
	}
	
	public void hurt(int amt) {
		super.hurt(amt);
		this.hurtTime = 20;
	}
}
