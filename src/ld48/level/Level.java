package ld48.level;

import java.util.ArrayList;

import ld48.Art;
import ld48.Bitmap;
import ld48.Game;
import ld48.entities.Entity;
import ld48.entities.Player;
import ld48.entities.RandomMoveEnemy;
import ld48.entities.StayOnBlockEnemy;
import ld48.entities.StraightLineEnemy;
import ld48.entities.block.Block;
import ld48.entities.block.CandyBlock;
import ld48.entities.block.CandyGrassBlock;
import ld48.entities.block.DirtBlock;
import ld48.entities.block.FlagBlock;
import ld48.entities.block.GrassBlock;
import ld48.entities.block.HouseBlock;
import ld48.entities.block.RockBlock;
import ld48.entities.items.Coin;
import ld48.menu.GameOverMenu;

public class Level {
	public ArrayList<Entity> entities;
	public ArrayList<Entity> newEntities;
	public ArrayList<Block> blocks;
	public ArrayList<Block> newBlocks;
	public int xScroll, yScroll;
	public int width, height;
	public int score, maxScore;
	Player p;
	int loseTime;
	public int id;
	
	public Level(int id) {
		this.id = id;
		this.width = Art.levels[id].w*10;
		this.height = Art.levels[id].h*10;
		entities = new ArrayList<Entity>();
		newEntities = new ArrayList<Entity>();
		blocks = new ArrayList<Block>();
		newBlocks = new ArrayList<Block>();
		addBlock(new Block(-2, 0, 2, height, this));
		addBlock(new Block(width+1, 0, 1, height, this));
		for (int y = 0; y < Art.levels[id].h; y++) {
			for (int x = 0; x < Art.levels[id].w; x++) {
				int col = Art.levels[id].pixels[x + y * Art.levels[id].w];
				if (col == 0xffffff00) {
					addEntity(new Coin(x*10, y*10, this));
					maxScore += 2;
				} else if (col == 0xffff0000) {
					addEntity(new RandomMoveEnemy(x*10, y*10, this));
					maxScore += 5;
				} else if (col == 0xffff0001) {
					addEntity(new StraightLineEnemy(x*10, y*10, this));
					maxScore += 5;
				} else if (col == 0xffff0002) {
					addEntity(new StayOnBlockEnemy(x*10, y*10, this));
					maxScore += 5;
				} else if (col == 0xff000000) {
					addBlock(new DirtBlock(x, y, this, true));
				} else if (col == 0xff000001) {
					addBlock(new DirtBlock(x, y, this, false));
				} else if (col == 0xff00ff00) {
					addBlock(new GrassBlock(x, y, this, false));
				} else if (col == 0xff0000ff) {
					addBlock(new FlagBlock(x,y,this));
					maxScore += 100;
				} else if (col == 0xff111111) {
					addBlock(new HouseBlock(x,y,this));
				} else if (col == 0xffff00ff) {
					p = new Player(x*10, y*10, this);
					addEntity(p);
				} else if (col == 0xff000002) {
					addBlock(new RockBlock(x,y,this,true));
				} else if (col == 0xff000003) {
					addBlock(new CandyBlock(x,y,this,false));
				} else if (col == 0xff00ff01) {
					addBlock(new CandyGrassBlock(x,y,this));
				}
			}
		}
	}
	
	public void update() {
		this.score = p.score;
		if (!newEntities.isEmpty()) {
			entities.addAll(newEntities);
			newEntities.clear();
		}
		if (!newBlocks.isEmpty()) {
			blocks.addAll(newBlocks);
			newBlocks.clear();
		}
		
		for (int i = 0; i < entities.size(); ) {
			Entity e = entities.get(i);
			if (e.isAlive) {
				e.update();
				i++;
			} else {
				entities.remove(i);
			}
		}
		
		if (score > maxScore * 3 / 2) {
			loseTime++;
		}
		
		if (loseTime > 100) {
			Game.setMenu(new GameOverMenu());
		}
	}
	
	public void render(Bitmap screen) {
		if (id == 2) {
			screen.draw(Art.rockWall, -xScroll, -yScroll);
		} else if (id == 3) {
			screen.draw(Art.candySky, -xScroll/3, -yScroll);
			screen.blendDraw(Art.candyHills, -xScroll/2, height-Art.candyHills.h-yScroll);
		} else {
			screen.blendDraw(Art.sky, -xScroll/3, -yScroll);
			screen.blendDraw(Art.hills, -xScroll/2, height-Art.hills.h-yScroll);
		}
		for (Block b : blocks) {
			b.render(screen);
		}
		for (Entity e : entities) {
			e.render(screen);
		}
		screen.postProcess(score, maxScore);
		
		screen.blendDraw(Art.score, Game.WIDTH/2-40, 10);
		String s = "" + score;
		for (int i = 0; i < s.length(); i++) {
			int num = Integer.parseInt("" + s.charAt(i));
			screen.blendDraw(Art.numbers[num], Game.WIDTH/2 + i*10+10, 11);
		}
	}
	
	public void addEntity(Entity e) {
		newEntities.add(e);
	}
	
	public void addBlock(Block b) {
		newBlocks.add(b);
	}
	
	public void setScroll(int xs, int ys) {
		this.xScroll = xs;
		this.yScroll = ys;
		if (xScroll < 0) xScroll = 0;
		if (xScroll > width - Game.WIDTH) xScroll = width - Game.WIDTH;
		if (yScroll < 0) yScroll = 0;
		if (yScroll > height - Game.HEIGHT) yScroll = height - Game.HEIGHT;
	}

	public Level reset() {
		return new Level(id);
	}
}
