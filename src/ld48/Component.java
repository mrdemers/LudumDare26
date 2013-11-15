package ld48;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Component extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400, HEIGHT = 300;
	public static final int SCALE = 2;
	public static final String title = "Minimalism";
	public Thread thread;
	public boolean running;
	public Bitmap screen;
	public BufferedImage image;
	public Game game;
	
	public Component() {
		setSize(WIDTH*SCALE, HEIGHT*SCALE);
		screen = new Bitmap(WIDTH, HEIGHT);
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		screen.pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		game = new Game(WIDTH, HEIGHT);
		InputHandler i = Input.getInput();
		addKeyListener(i);
		addFocusListener(i);
		addMouseMotionListener(i);
		addMouseListener(i);
	}
	
	public void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		game.update();
		Input.update();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, WIDTH, HEIGHT);
		screen.fill(0xff000000);
		game.render(screen);
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		
		g.dispose();
		bs.show();
	}
	
	public void run() {
		long last = System.nanoTime();
		double unprocessedSeconds = 0;
		double secondsPerTick = 1 / 60.0;
		int updates = 0, frames = 0;
		while(running) {
			long now = System.nanoTime();
			long passed = now - last;
			last = now;
			if (passed < 0) passed = 0;
			if (passed > 100000000) passed = 100000000;
			unprocessedSeconds += passed / 1000000000.0;
			boolean updated = false;
			while (unprocessedSeconds > secondsPerTick) {
				update();
				unprocessedSeconds -= secondsPerTick;
				updated = true;
				updates++;
			}
			
			if (updated) {
				if (updates >= 60) {
					System.out.println("FPS: " + frames);
					updates -= 60;
					frames = 0;
				}
			}
			
			if (updated) {
			render();
			frames++;
			}
		}
	}
	
	public static void main(String[] args) {
		JPanel panel = new JPanel(new BorderLayout());
		Component game = new Component();
		panel.add(game);
		JFrame frame = new JFrame(title);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(300, 200);
		frame.pack();
		frame.setVisible(true);
		game.start();
	}
}
