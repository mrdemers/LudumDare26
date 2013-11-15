package ld48.entities.block;

import ld48.level.Level;

public class GrassBlock extends Block{
	public GrassBlock(int x, int y, Level level, boolean solid) {
		super(x*10, y*10, 10, solid?10:1, level);
		id = 0;
	}
}
