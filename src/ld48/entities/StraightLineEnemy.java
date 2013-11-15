package ld48.entities;

import ld48.level.Level;

public class StraightLineEnemy extends Enemy {

	public StraightLineEnemy(float x, float y, Level level) {
		super(x, y, 15, 35, level);
		this.right = false;
	}

	public void update() {
		super.update();
		if (hurtTime == 0) {
			if (right) this.dx = 1;
			else this.dx = -1;
		}
	}
}
