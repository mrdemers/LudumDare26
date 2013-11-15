package ld48.entities;

import java.awt.event.KeyEvent;

import ld48.Art;
import ld48.Bitmap;
import ld48.Game;
import ld48.Input;
import ld48.Sound;
import ld48.entities.block.Block;
import ld48.entities.block.FlagBlock;
import ld48.entities.block.HouseBlock;
import ld48.entities.particles.NumberParticle;
import ld48.entities.particles.StarParticle;
import ld48.level.Level;

public class Player extends Mob {
	public int score;
	public int attackTime = 0;
	public int hurtTime = 0;
	boolean hitFlag = false;
	int timer = 0;
	Block flag;
	
	public Player(float x, float y, Level level) {
		super(x, y, 15, 35, level);
		health = 100;
	}
	
	boolean wasGrounded;
	public void update() {
		wasGrounded = grounded;
		super.update();
		if (grounded && !wasGrounded) {
			Sound.land.play();
		}
		if (hitFlag) {
			dy = 0;
			dx = 0;
		}
		boolean up = Input.isKeyClicked(KeyEvent.VK_W);
		boolean left = Input.isKeyDown(KeyEvent.VK_A);
		boolean right = Input.isKeyDown(KeyEvent.VK_D);
		boolean attack = Input.isKeyClicked(KeyEvent.VK_SPACE);
		
		if (hurtTime > 0) {
			hurtTime--;
		}
		
		if (!hitFlag) {
			if (score > level.maxScore*3/2) {
				dx = dy = 0;
				return;
			}
			x+=dx; y+=dy;
			for (Block b : level.blocks) {
				if (b instanceof FlagBlock) {
					if (intersects(b)) {
						Sound.flag.play();
						addScore(200);
						hitFlag = true;
						dy = 0;
						dx = 0;
						this.x = b.x+b.width+1;
						flag = b;
						grounded = true;
					}
				} else if (b instanceof HouseBlock) {
					if (intersects(b)) {
						nextLevel();
					}
				}
			}
			x-=dx; y-=dy;
		}
		
		if (hitFlag) {
			grounded = true;
			timer++;
			up = left = right = false;
			if (y + height< flag.y + flag.height) {
				y+=.6;
			} else {
				if (timer > 60*4) {
					nextLevel();
				}
				dx = 1;
				x++;
				for (Block b : level.blocks) {
					if (b instanceof HouseBlock) {
						if (intersects(b)) {
							nextLevel();
						}
					}
				}
				x--;
			}
		}
		
		if (y + height >= level.height) {
			addScore(level.maxScore*2);
			Sound.lose.play();
		}
		
		for (Entity e : level.entities) {
			if (e instanceof Enemy) {
				if (intersects(e) && hurtTime == 0) {
					this.hurt(((Enemy)e).damage);
					this.dx = Math.signum(x-e.x) * 3 + e.dx;
					this.dy = -2;
					Sound.hit.play();
				}
			}
		}
		
		if (attack || attackTime > 0) {
			attackTime+=2;
			int range = 30;
			if (attackTime == 30) {
				for (Entity e : level.entities) {
					if (e instanceof Enemy) {
						if (this.right) {
							if (e.x > x + 5 && e.x < x + width + range && Math.abs(e.y-y) < 11) {
								((Enemy) e).hurt(4);
								e.dx += 3;
								e.dy = -2;
								addScore(3);
								for (int i = 0; i < 5; i++) {
									level.addEntity(new StarParticle(e.x+(float)(Math.random()*e.width), e.y, level));
								}
								Sound.hit.play();
								if (!e.isAlive) {
									addScore(30);
								}
							}
						} else {
							if (e.x + 5 < x && e.x > x - 5 - range && Math.abs(e.y-y) < 11) {
								((Enemy) e).hurt(4);
								e.dx -= 3;
								e.dy = -2;
								addScore(3);
								for (int i = 0; i < 5; i++) {
									level.addEntity(new StarParticle(e.x+(float)(Math.random()*e.width), e.y, level));
								}
								Sound.hit.play();
								if (!e.isAlive) {
									addScore(30);
								}
							}
						}
					}
				}
				attackTime = 0;
			}
		}
		
		
		
		float speed = 4.5f;
		if (left && hurtTime == 0) {
			this.right = false;
			dx -= .7f;
			if (walkTime%20==0 && grounded) Sound.walk.play();
			if (dx < -speed)
				dx = -speed;
		} else if (right && hurtTime == 0) {
			this.right = true;
			dx += .7f;
			if (walkTime%20==0 && grounded) Sound.walk.play();
			if (dx > speed) 
				dx = speed;
		} else {
			dx *= 0.7f;
			if (dx < .001 && dx > -.001) dx = 0;
		}
		if (up && grounded) {
			jump();
			Sound.jump.play();
		}
		
		if (getX() > Game.WIDTH/2 + 20) {
			level.setScroll(level.xScroll+(getX()-Game.WIDTH/2-20), level.yScroll);
		}
		
		if (getX() < Game.WIDTH/2 - 20) {
			level.setScroll(level.xScroll+(getX()-Game.WIDTH/2+20), level.yScroll);
		}
		
		if (getY() > Game.HEIGHT/2 + 20) {
			level.setScroll(level.xScroll, level.yScroll+(getY()-Game.HEIGHT/2-20));
		}
		if (getY() < Game.HEIGHT/2 - 20) {
			level.setScroll(level.xScroll, level.yScroll+(getY()-Game.HEIGHT/2+20));
		}
	}
	
	public void nextLevel() {
		Sound.win.play();
		Game.nextLevel();
	}
	
	public void addScore(int amt) {
		score += amt;
		level.addEntity(new NumberParticle(x, y, level, "" + amt));
	}
	
	public void hurt(int amt) {
		super.hurt(amt);
		hurtTime = 20;
		addScore(amt);
	}
	
	public void render(Bitmap screen) {
		int frame = walkTime/5%8;
		if (right) {
			if (!(attackTime>0)) {
				if (!grounded) {
					screen.blendDraw(Art.guyJump, getX(), getY());
				} else
					screen.blendDraw(Art.guy[frame], getX(), getY());
			} else {
				
				frame = attackTime/3%10;
				screen.blendDraw(Art.guyAttack[frame], getX(), getY());
			}
		}
		else {
			if (!(attackTime>0)) {
				if (!grounded) {
					screen.flipBlend(Art.guyJump, getX(), getY());
				} else
					screen.flipBlend(Art.guy[frame], getX(), getY());
			} else {
				frame = attackTime/3%10;
				screen.flipBlend(Art.guyAttack[frame], getX(), getY());
			}
		}
	}

}
