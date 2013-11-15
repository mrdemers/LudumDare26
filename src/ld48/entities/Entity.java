package ld48.entities;

import ld48.Bitmap;
import ld48.Game;
import ld48.level.Level;

public class Entity {
	public float x, y;
	public float dx, dy;
	public int width, height;
	public boolean isAlive;
	public Level level;
	
	public Entity(float x, float y, int width, int height, Level level) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		isAlive = true;
		this.level = level;
	}
	
	public void update() {
		move(dx, 0);
		move(0, dy);
	}
	
	/**
	 * Method meant to be overriden if collision detection required
	 * @param dx
	 * @param dy
	 */
	public void move(float dx, float dy) {
		x += dx;
		y += dy;
	}
	
	public void render(Bitmap screen) {
		
	}
	
	public int getX() { return (int)x - Game.xScroll; }
	public int getY() { return (int)y - Game.yScroll; }
	
	public boolean intersects(Entity e) {
		return !(x >= e.x + e.width || x + width <= e.x || y >= e.y + e.height || y + height <= e.y);
	}
}
