package ld48.entities.block;

import ld48.Art;
import ld48.Bitmap;
import ld48.level.Level;

public class FlagBlock extends Block {
	public FlagBlock(float x, float y, Level level) {
		super(x*10, y*10, 10, 80, level);
		id = 2;
	}
	
	public void render(Bitmap screen) {
		screen.blendDraw(Art.blocks[id], getX(), getY());
	}
}
