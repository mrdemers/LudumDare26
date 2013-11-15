package ld48.entities.items;

import ld48.Art;
import ld48.Bitmap;
import ld48.Sound;
import ld48.entities.Player;
import ld48.level.Level;

public class Coin extends Item {
	public Coin(float x, float y, Level level) {
		super(x, y, 10, 15, level);
		stationary = true;
		grounded = true;
	}

	public void onPickup(Player p) {
		p.addScore(10);
		this.isAlive = false;
		Sound.coin.play();
	}
	
	public void render(Bitmap screen) {
		int frame = (int)(time/9.0)%4;
		screen.blendDraw(Art.coin[frame], getX(), getY());
	}
}
