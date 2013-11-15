package ld48.entities;

import ld48.level.Level;

public class RandomMoveEnemy extends Enemy {

	public RandomMoveEnemy(float x, float y, Level level) {
		super(x, y, 15, 35, level);
	}
	
	public void update() {
		super.update();
		if (Math.random()>.99) {
			this.right = !this.right;
		}
		
		if ((Math.random() > .995 && !stop) || (Math.random() > .8 && stop)) {
			stop = !stop;
		}
		if (hurtTime == 0) {
			if (!stop) {
				if (right) dx=1;
				else dx = -1;
			} else {
				dx= 0;
			}
		}
	}
}
