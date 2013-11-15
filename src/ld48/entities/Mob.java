package ld48.entities;

import ld48.entities.block.Block;
import ld48.entities.items.Cherry;
import ld48.level.Level;

public class Mob extends Entity{
	public boolean grounded = false;
	public int walkTime = 0;
	public boolean right = true;
	public int health;
	
	public Mob(float x, float y, int width, int height, Level level) {
		super(x, y, width, height, level);
	}
	
	public void update() {
		super.update();
		if (!grounded) {
			dy += .4f;
		}
		grounded = checkGrounded();
	}
	
	public boolean checkGrounded() {
		y += 1;
		for (Block b : level.blocks) {
			if (intersects(b)) {
				if ((b.height == 1 && y + height - 1 <= b.y) || (b.height != 1 && b.height != 0)) {
					y -= 1;
					return true;
				}
			}
		}
		y -= 1;
		return false;
	}
	
	public void jump() {
		this.dy = - 8f;
		grounded = false;
	}
	
	public void move(float dx, float dy) {
		super.move(dx, dy);
		if (dx > .1 || dx < -.1) walkTime++;
		for (Block b : level.blocks) {
			if (intersects(b)) {
				if (dy > 0) {
					if ((b.height == 1 && (y - dy + height-1) <= b.y) || (b.height != 1 && b.height != 0)) {
						grounded = true;
						y = b.y - height;
						this.dy = 0;
					} 
				} else if (dy < 0) {
					if (b.height == 1 || b.height == 0) continue;
					y -= dy;
					this.dy = 0;
				} else if (dx > 0){
					if (b.height == 1 || b.height == 0) continue;
					x = b.x - width;
					this.dx = 0;
				} else if (dx < 0) {
					if (b.height == 1 || b.height == 0) continue;
					x = b.x + b.width;
					this.dx = 0;
				}
			}
		}
	}
	
	public void hurt(int amt) {
		health -= amt;
		if (health <= 0) {
			this.isAlive = false;
			if (Math.random() > .7) {
				level.addEntity(new Cherry(x, y, level));
			}
		}
	}
}
