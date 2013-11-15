package ld48.entities.block;

import ld48.level.Level;

public class DirtBlock extends Block {
	public DirtBlock(int x, int y, Level level, boolean solid) {
		super(x*10, y*10, 10, solid?10:0, level);
		id = 1;
	}
}
