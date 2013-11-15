package ld48.entities.block;

import ld48.Art;
import ld48.Bitmap;
import ld48.level.Level;

public class HouseBlock extends Block {

	public HouseBlock(float x, float y, Level level) {
		super(x*10, y*10, 10, 10, level);
	}

	public void render(Bitmap screen) {
		screen.blendDraw(Art.house, getX()-25, getY()-38);
	}
}
