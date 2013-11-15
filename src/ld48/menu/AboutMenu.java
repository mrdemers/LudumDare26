package ld48.menu;

import java.awt.event.KeyEvent;

import ld48.Art;
import ld48.Bitmap;
import ld48.Game;
import ld48.Input;

public class AboutMenu extends Menu {
	
	public void update() {
		if (Input.isKeyClicked(KeyEvent.VK_SPACE)) {
			Game.setMenu(new StartMenu());
		}
	}
	
	public void render(Bitmap screen) {
		screen.draw(Art.aboutMenu, 0, 0);
	}
}
