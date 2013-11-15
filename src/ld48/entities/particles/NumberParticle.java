package ld48.entities.particles;

import ld48.Art;
import ld48.Bitmap;
import ld48.level.Level;

public class NumberParticle extends Particle {
	String numbers;
	public NumberParticle(float x, float y, Level level, String numbers) {
		super(x, y, 1, 1, level);
		this.numbers = numbers;
		this.dx = (float)Math.random()-.5f;
		this.dy = -(float)Math.random();
		this.life = maxLife = 100;
	}
	
	public void render(Bitmap screen) {
		double a = (double)life/maxLife;
		screen.blendDraw(Art.numbers[10], getX(), getY(), a);
		for (int i = 0; i < numbers.length(); i++) {
			int num = Integer.parseInt("" + numbers.charAt(i));	
			screen.blendDraw(Art.numbers[num], getX() + (i+1) * 10, getY(), a);
		}
	}
}
