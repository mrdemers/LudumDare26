package ld48.menu;

import java.awt.event.KeyEvent;

import ld48.Art;
import ld48.Bitmap;
import ld48.Game;
import ld48.Input;

public class WinMenu extends Menu {
	public void update() {
		if (Input.isKeyClicked(KeyEvent.VK_SPACE)) {
			Game.levelId = 0;
			Game.levelScores.clear();
			Game.setMenu(new StartMenu());
		}
	}
	
	public void render(Bitmap screen) {
		screen.draw(Art.winMenu, 0, 0);
		for (int i = 0; i < Game.levelScores.size(); i++) {
			screen.blendDraw(Art.levelText[i], 140, 130+i*20);
			String s = "" + Game.levelScores.get(i);
			for (int j = 0; j < s.length(); j++) {
				int frame = Integer.parseInt("" + s.charAt(j));
				screen.blendDraw(Art.numbers[frame], 210 + j * 10, 130 + i * 20);
			}
		}
	}
}
