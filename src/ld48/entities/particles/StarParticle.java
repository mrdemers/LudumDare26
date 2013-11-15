package ld48.entities.particles;

import ld48.Art;
import ld48.Bitmap;
import ld48.level.Level;

public class StarParticle extends Particle {

	public StarParticle(float x, float y, Level level) {
		super(x, y, 1, 1, level);
		this.dx = (float)(Math.random()-.5)/2;
		this.dy = -(float)(Math.random());
		this.life = maxLife = 20;
	}
	
	public void update() {
		super.update();
		this.dy += .1f;
	}
	
	public void render(Bitmap screen) {
		float a = (float)life/maxLife;
		screen.blendDraw(Art.star, getX(), getY(), a);
	}

}
