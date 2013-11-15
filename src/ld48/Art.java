package ld48;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Art {
	public static Bitmap hills = loadImage("/hills.png");
	public static Bitmap sky = loadImage("/sky.png");
	public static Bitmap guy[] = loadImages("/guy/guy.png", 8);
	public static Bitmap guyAttack[] = loadImages("/guy/guyAttack.png", 10);
	public static Bitmap guyJump = loadImage("/guy/jump.png");
	public static Bitmap enemyGuy[] = loadImages("/guy/enemyGuy.png", 8);
	public static Bitmap[] coin = loadAnimation("/coin.png", 10);
	public static Bitmap[] numbers = loadAnimation("/numbers.png", 10);
	public static Bitmap score = loadImage("/score.png");
	public static Bitmap star = loadImage("/star.png");
	public static Bitmap[] levels = {loadImage("/level1.png"), loadImage("/level2.png"), loadImage("/level3.png"), loadImage("/level4.png")};
	public static Bitmap gameOver = loadImage("/gameOverMenu.png");
	public static Bitmap cherry = loadImage("/cherry.png");
	public static Bitmap[] blocks = {loadImage("/grassBlock.png"), loadImage("/dirtBlock.png"), loadImage("/flag.png"), loadImage("/rockBlock.png"), loadImage("/candyBlock.png"), loadImage("/candyGrassBlock.png")};
	public static Bitmap arrow = loadImage("/arrow.png");
	public static Bitmap house = loadImage("/house.png");
	public static Bitmap[] levelText = loadImages("/lvl.png", 4);
	public static Bitmap startMenu = loadImage("/startMenu.png");
	public static Bitmap rockWall = loadImage("/rockWall.png");
	public static Bitmap recapMenu = loadImage("/recapMenu.png");
	public static Bitmap candyHills = loadImage("/candyHills.png");
	public static Bitmap candySky = loadImage("/candySky.png");
	public static Bitmap aboutMenu = loadImage("/aboutMenu.png");
	public static Bitmap winMenu = loadImage("/winMenu.png");
	
	public static Bitmap loadImage(String fileName) {
		try {
			BufferedImage image = ImageIO.read(Art.class.getResource(fileName));
			int w = image.getWidth();
			int h = image.getHeight();
			Bitmap b = new Bitmap(w, h);
			
			for (int y = 0; y < h; y++) {
				for (int x = 0; x < w; x++) {
					b.pixels[x + y * w] = image.getRGB(x, y);
				}
			}
			return b;
		} catch (Exception e) {
			System.out.println("Couldn't read " + fileName);
			e.printStackTrace();
		}
		return null;
	}
	
	public static Bitmap[] loadAnimation(String fileName, int xc) {
		try {
			BufferedImage image = ImageIO.read(Art.class.getResource(fileName));
			int w = image.getWidth();
			int h = image.getHeight();
			Bitmap b[] = new Bitmap[w/xc];
			
			for (int i = 0; i < b.length; i++) {
				b[i] = new Bitmap(xc, h);
				for (int y = 0; y < h; y++) {
					for (int x = 0; x < xc; x++) {
						b[i].pixels[x + y * xc] = image.getRGB(x+i*xc, y);
					}
				}
			}
			return b;
		} catch (Exception e) {
			System.out.println("Couldn't read " + fileName);
			e.printStackTrace();
		}
		return null;
	}
	
	public static Bitmap[] loadImages(String fileName, int number) {
		try {
			Bitmap b[] = new Bitmap[number];
			for (int i = 1; i <= number; i++) {
				String name = fileName.substring(0, fileName.length()-4);
				if (i < 10)
					name += "000" + i + ".png";
				else if (i < 100) 
					name += "00" + i + ".png";					
				BufferedImage image = ImageIO.read(Art.class.getResource(name));
				int w = image.getWidth();
				int h = image.getHeight();
				b[i-1] = new Bitmap(w, h); 
				
				for (int y = 0; y < h; y++) {
					for (int x = 0; x < w; x++) {
						b[i-1].pixels[x + y * w] = image.getRGB(x, y);
					}
				}
			}
		
			return b;
		} catch (Exception e) {
			System.out.println("Couldn't read " + fileName);
			e.printStackTrace();
		}
		return null;
	}
}
