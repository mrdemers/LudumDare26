package ld48.menu;

import java.awt.event.KeyEvent;

import ld48.Art;
import ld48.Bitmap;
import ld48.Game;
import ld48.Input;
import ld48.Sound;

public class StartMenu extends Menu {
	int choice;
	public void update() {
		boolean up = Input.isKeyClicked(KeyEvent.VK_W);
		boolean down = Input.isKeyClicked(KeyEvent.VK_S);
		boolean go = Input.isKeyClicked(KeyEvent.VK_SPACE);
		
		if (up) {
			choice--;
			Sound.blip.play();
		}
		if (down) {
			choice++;
			Sound.blip.play();
		}
		
		if (go) {
			if (choice%2==0) {
				Game.toNext();
			} else {
				Game.setMenu(new AboutMenu());
			}
		}
	}
	
	public void render(Bitmap screen) {
		screen.draw(Art.startMenu, 0, 0);
		screen.blendDraw(Art.arrow, 140, 155 + choice%2*37);
	}
}
