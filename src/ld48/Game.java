package ld48;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ld48.level.Level;
import ld48.menu.GameOverMenu;
import ld48.menu.Menu;
import ld48.menu.RecapMenu;
import ld48.menu.StartMenu;
import ld48.menu.WinMenu;

public class Game {
	public static int WIDTH, HEIGHT;
	public static int xScroll, yScroll;
	public static Level currentLevel;
	public static char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	public static Menu currentMenu;
	public static ArrayList<Integer> levelScores = new ArrayList<Integer>();
	public static int levelId = 0;
	
	public Game(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		currentMenu = new StartMenu();
	}
	
	public void update() {
		if (Input.isKeyClicked(KeyEvent.VK_ESCAPE)) {
			levelScores.add(currentLevel.score);
			restartLevel();
			return;
		}
		if (currentMenu == null) {			
			currentLevel.update();
			xScroll = currentLevel.xScroll;
			yScroll = currentLevel.yScroll;
		} else {
			currentMenu.update();
		}
	}
	
	public void render(Bitmap screen) {
		if (currentMenu == null) {
			currentLevel.render(screen);
		} else {
			currentMenu.render(screen);
		}
	}
	
	public static void setMenu(Menu m) {
		if (m instanceof GameOverMenu) {
			levelScores.add(currentLevel.score);
		}
		currentMenu = m;
	}
	
	public static void restartLevel() {
		levelScores.remove(currentLevel.id);
		currentLevel = currentLevel.reset();
	}
	
	public static void nextLevel() {
		levelScores.add(currentLevel.score);
		if (currentLevel.id == 3) {
			currentMenu = new WinMenu();
		} else {
			Integer[] a = new Integer[levelScores.size()];
			currentMenu = new RecapMenu((Integer[])levelScores.toArray(a));
		}
	}
	
	public static void toNext() {
		currentLevel = new Level(levelId);
		levelId++;
		currentMenu = null;
	}
}
