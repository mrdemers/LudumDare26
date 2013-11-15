package ld48.menu;

import java.awt.event.KeyEvent;

import ld48.Art;
import ld48.Bitmap;
import ld48.Game;
import ld48.Input;

public class RecapMenu extends Menu {
	Integer[] scores;
	int time;
	public RecapMenu(Integer[] levelScores) {
		scores = levelScores;
	}
	
	public void update() {
		boolean go = Input.isKeyClicked(KeyEvent.VK_SPACE);
		if (time<100) xx[0]++;
		else if (time < 200) xx[1]++;
		else if(time < 300) xx[2]++;
		else if(time < 400) xx[3]++;
		time++;
		
		if (go) {
			Game.toNext();
		}
	}
	
	int[] xx = new int[4];
	public void render(Bitmap screen) {
		screen.draw(Art.recapMenu, 0, 0);
		for (int i = 0; i < scores.length; i++) {
			screen.blendDraw(Art.levelText[i], 50, 100 + i * 20);
			String amt = "" + scores[i];
			for (int j = 0; j < amt.length(); j++) {
				int frame = Integer.parseInt("" + amt.charAt(j));
				int x = 520 - xx[i] * 4;
				screen.blendDraw(Art.numbers[frame], x + j * 10, 100 + i * 20);				
			}
		}
	}
}
