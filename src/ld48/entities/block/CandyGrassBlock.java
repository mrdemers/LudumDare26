package ld48.entities.block;

import ld48.level.Level;

public class CandyGrassBlock extends Block {
	public CandyGrassBlock(int x, int y, Level level) {
		super(x*10, y*10, 10, 1, level);
		id = 5;
	}

}
