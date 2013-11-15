package ld48.entities.items;

import ld48.entities.Entity;
import ld48.entities.Mob;
import ld48.entities.Player;
import ld48.level.Level;

public class Item extends Mob {
	public float yy;
	public int time;
	boolean stationary;
	public Item(float x, float y, int width, int height, Level level) {
		super(x, y, width, height, level);
		this.dx = (float)(Math.random()*2);
		this.dy = -(float)(Math.random()*3);
		yy = y;
	}
	
	public void update() {
		time++;
		if (!stationary) {
			super.update();
		}
		if (grounded) {
			dx *= .7f;
			this.yy = y + (float)(Math.sin(time/15)*4);
		} else {
			yy = y;
		}
		
		for (Entity e : level.entities) {
			if (e instanceof Player && intersects(e)) {
				onPickup((Player)e);
			}
		}
	}
	
	public void onPickup(Player p) {
		
	}
}
