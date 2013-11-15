package ld48.entities.block;

import ld48.Art;
import ld48.Bitmap;
import ld48.entities.Entity;
import ld48.level.Level;

public class Block extends Entity {
	int id;
	public Block(float x, float y, int width, int height, Level level) {
		super(x, y, width, height, level);
	}
	
	public void render(Bitmap screen) {
		screen.draw(Art.blocks[id], getX(), getY());
	}
}
