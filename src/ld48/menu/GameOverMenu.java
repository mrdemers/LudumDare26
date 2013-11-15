package ld48.menu;

import java.awt.event.KeyEvent;

import ld48.Art;
import ld48.Bitmap;
import ld48.Game;
import ld48.Input;
import ld48.Sound;

public class GameOverMenu extends Menu {
	int time;
	int choice;
	public GameOverMenu() {
		
	}
	
	public void update() {
		time++;
		boolean go = Input.isKeyClicked(KeyEvent.VK_SPACE);
		boolean up = Input.isKeyClicked(KeyEvent.VK_W);
		boolean down = Input.isKeyClicked(KeyEvent.VK_S);
		if (up) {
			choice--;
			Sound.blip.play();
		}
		if (down) {
			choice++;
			Sound.blip.play();
		}
		if (go) {
			if (choice%2 == 0) {
				Game.setMenu(null);
				Game.restartLevel();
			} else {
				Game.levelId = 0;
				Game.levelScores.clear();
				Game.setMenu(new StartMenu());
			}
		}
	}
	
	public void render(Bitmap screen) {
		float a = time/100f;
		if (a > 1) a = 1;
		screen.blendDraw(Art.gameOver, 0, 0, a);
		screen.blendDraw(Art.arrow, 140, 205 + (choice%2)*28, a);
		for (int i = 0; i < Game.levelScores.size(); i++) {
			screen.blendDraw(Art.levelText[i], 117, 110+i*15, a);
			String s = "" + Game.levelScores.get(i);
			for (int j = 0; j < s.length(); j++) {
				int frame = Integer.parseInt("" + s.charAt(j));
				screen.blendDraw(Art.numbers[frame], 190+j*10, 110+i*15, a);
			}
		}
	}
}
