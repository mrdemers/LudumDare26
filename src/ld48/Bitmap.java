package ld48;

import java.awt.Color;
import java.util.Arrays;

public class Bitmap {
	public int[] pixels;
	public int w, h;
	
	public Bitmap(int width, int height) {
		this.w = width;
		this.h = height;
		pixels = new int[w * h];
	}
	
	public Bitmap(int width, int height, int col) {
		this(width, height);
		fill(col);
	}
	
	public void draw(Bitmap b, int xo, int yo) {
		int x0 = xo;
		int y0 = yo;
		int x1 = xo + b.w;
		int y1 = yo + b.h;
		if (x0 < 0) x0 = 0;
		if (y0 < 0) y0 = 0;
		if (x1 > w) x1 = w;
		if (y1 > h) y1 = h;
		for (int y = y0; y < y1; y++) {
			int yp = y - yo;
			if (yp < 0 || yp >= b.h) continue; 
			for (int x = x0; x < x1; x++) {
				int xp = x - xo;
				if (xp < 0 || xp >= b.w) continue;
				
				pixels[x + y * w] = b.pixels[xp + yp * b.w];
			}
		}
	}
	
	public void flipDraw(Bitmap b, int xo, int yo) {
		int x0 = xo;
		int y0 = yo;
		int x1 = xo + b.w;
		int y1 = yo + b.h;
		if (x0 < 0) x0 = 0;
		if (y0 < 0) y0 = 0;
		if (x1 > w) x1 = w;
		if (y1 > h) y1 = h;
		for (int y = y0; y < y1; y++) {
			int yp = y - yo;
			if (yp < 0 || yp >= b.h) continue; 
			for (int x = x0; x < x1; x++) {
				int xp = b.w-(x - xo);
				if (xp < 0 || xp >= b.w) continue;
				
				pixels[x + y * w] = b.pixels[xp + yp * b.w];
			}
		}
	}
	
	public void blendDraw(Bitmap b, int xo, int yo) {
		int x0 = xo;
		int y0 = yo;
		int x1 = xo + b.w;
		int y1 = yo + b.h;
		if (x0 < 0) x0 = 0;
		if (y0 < 0) y0 = 0;
		if (x1 > w) x1 = w;
		if (y1 > h) y1 = h;
		for (int y = y0; y < y1; y++) {
			int yp = y - yo;
			if (yp < 0 || yp >= b.h) continue; 
			for (int x = x0; x < x1; x++) {
				int xp = x - xo;
				if (xp < 0 || xp >= b.w) continue;
				int col1 = b.pixels[xp + yp * b.w];
				double a1 = ((col1 >> 24)&0xff)/255.0;
				if (a1 == 0) continue;
				double r1 = ((col1 >> 16)&0xff)/255.0;
				double g1 = ((col1 >> 8)&0xff)/255.0;
				double b1 = (col1&0xff)/255.0;
				int col2 = pixels[x + y * w];
				double r2 = ((col2 >> 16)&0xff)/255.0;
				double g2 = ((col2 >> 8)&0xff)/255.0;
				double b2 = (col2&0xff)/255.0;
				
				double r3 = r1 * a1 + r2 * (1-a1);
				double g3 = g1 * a1 + g2 * (1-a1);
				double b3 = b1 * a1 + b2 * (1-a1);
				int col = (255<<24|(int)(r3*255)<<16|(int)(g3*255)<<8|(int)(b3*255));
				pixels[x + y * w] = col;
			}
		}
	}
	
	public void flipBlend(Bitmap b, int xo, int yo) {
		int x0 = xo;
		int y0 = yo;
		int x1 = xo + b.w;
		int y1 = yo + b.h;
		if (x0 < 0) x0 = 0;
		if (y0 < 0) y0 = 0;
		if (x1 > w) x1 = w;
		if (y1 > h) y1 = h;
		for (int y = y0; y < y1; y++) {
			int yp = y - yo;
			if (yp < 0 || yp >= b.h) continue; 
			for (int x = x0; x < x1; x++) {
				int xp = b.w-(x - xo);
				if (xp < 0 || xp >= b.w) continue;
				int col1 = b.pixels[xp + yp * b.w];
				double a1 = ((col1 >> 24)&0xff)/255.0;
				if (a1 == 0) continue;
				double r1 = ((col1 >> 16)&0xff)/255.0;
				double g1 = ((col1 >> 8)&0xff)/255.0;
				double b1 = (col1&0xff)/255.0;
				int col2 = pixels[x + y * w];
				double r2 = ((col2 >> 16)&0xff)/255.0;
				double g2 = ((col2 >> 8)&0xff)/255.0;
				double b2 = (col2&0xff)/255.0;
				
				double r3 = r1 * a1 + r2 * (1-a1);
				double g3 = g1 * a1 + g2 * (1-a1);
				double b3 = b1 * a1 + b2 * (1-a1);
				int col = (255<<24|(int)(r3*255)<<16|(int)(g3*255)<<8|(int)(b3*255));
				pixels[x + y * w] = col;
			}
		}
	}
	
	public void blendDraw(Bitmap b, int xo, int yo, double alpha) {
		int x0 = xo;
		int y0 = yo;
		int x1 = xo + b.w;
		int y1 = yo + b.h;
		if (x0 < 0) x0 = 0;
		if (y0 < 0) y0 = 0;
		if (x1 > w) x1 = w;
		if (y1 > h) y1 = h;
		for (int y = y0; y < y1; y++) {
			int yp = y - yo;
			if (yp < 0 || yp >= b.h) continue; 
			for (int x = x0; x < x1; x++) {
				int xp = x - xo;
				if (xp < 0 || xp >= b.w) continue;
				int col1 = b.pixels[xp + yp * b.w];
				double a1 = ((col1 >> 24)&0xff)/255.0 * alpha;
				if (a1 == 0) continue;
				double r1 = ((col1 >> 16)&0xff)/255.0;
				double g1 = ((col1 >> 8)&0xff)/255.0;
				double b1 = (col1&0xff)/255.0;
				int col2 = pixels[x + y * w];
				double r2 = ((col2 >> 16)&0xff)/255.0;
				double g2 = ((col2 >> 8)&0xff)/255.0;
				double b2 = (col2&0xff)/255.0;
				
				double r3 = r1 * a1 + r2 * (1-a1);
				double g3 = g1 * a1 + g2 * (1-a1);
				double b3 = b1 * a1 + b2 * (1-a1);
				int col = (255<<24|(int)(r3*255)<<16|(int)(g3*255)<<8|(int)(b3*255));
				pixels[x + y * w] = col;
			}
		}
	}
	
	public void fill(int col) {
		Arrays.fill(pixels, col);
	}

	public void postProcess(int score, int maxScore) {
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				int col = pixels[x + y * w];
				int r = (col>>16)&0xff;
				int g = (col>>8)&0xff;
				int b = col&0xff;
				float[] hsb = Color.RGBtoHSB(r, g, b, null);
				hsb[1] = (float)(maxScore-score)/(maxScore) * hsb[1];
				if (score > maxScore) {
					hsb[1]=0;
					float val = (float)(maxScore*2-score)/(maxScore);
					if (val < 0) val = .1f;
					hsb[2] = val*hsb[2];
				}
				int newCol = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
				pixels[x + y * w] = newCol;
			}
		}
	}
}
