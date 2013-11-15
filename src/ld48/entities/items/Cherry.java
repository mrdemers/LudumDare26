package ld48.entities.items;

import ld48.Art;
import ld48.Bitmap;
import ld48.Sound;
import ld48.entities.Player;
import ld48.level.Level;

public class Cherry extends Item {
	public Cherry(float x, float y, Level level) {
		super(x, y, 10, 10, level);
	}
	
	public void onPickup(Player p) {
		p.addScore(20);
		this.isAlive = false;
		Sound.coin.play();
	}
	
	public void render(Bitmap screen) {
		screen.blendDraw(Art.cherry, getX(), getY());
	}

}
