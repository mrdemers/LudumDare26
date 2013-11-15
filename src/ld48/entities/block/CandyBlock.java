package ld48.entities.block;

import ld48.level.Level;

public class CandyBlock extends Block {

	public CandyBlock(float x, float y, Level level, boolean solid) {
		super(x*10, y*10, 10, solid?10:0, level);
		id = 4;
	}

}
