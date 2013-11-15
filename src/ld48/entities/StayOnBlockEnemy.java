package ld48.entities;

import ld48.entities.block.Block;
import ld48.level.Level;

public class StayOnBlockEnemy extends RandomMoveEnemy {
	Block on;
	public StayOnBlockEnemy(float x, float y, Level level) {
		super(x, y, level);
	}
	
	public void update() {
		super.update();
		if (hurtTime != 0) {
			on = null;
		}
		if (on == null) {
			y+=1;
			for (Block b : level.blocks) {
				if (b.height == 0) continue;
				if (intersects(b)) {
					on = b;
				}
			}
			y--;
		}
		
		if (on != null) {
			y++;
			boolean stillOn = false;
			for (Block b: level.blocks) {
				if (b.height == 0) continue;
				if (intersects(b)) {
					stillOn = true;
					break;
				}
			}
			y--;
			if (!stillOn) {
				right = !right;
				x -= dx*5;
			}
		}
	}
}
