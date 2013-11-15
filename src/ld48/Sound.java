package ld48;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	public static Sound hit = loadSound("/snd/hit.wav");
	public static Sound coin = loadSound("/snd/coin.wav");
	public static Sound jump = loadSound("/snd/jump.wav");
	public static Sound walk = loadSound("/snd/walk.wav");
	public static Sound win = loadSound("/snd/win.wav");
	public static Sound flag = loadSound("/snd/flag.wav");
	public static Sound land = loadSound("/snd/land.wav");
	public static Sound lose = loadSound("/snd/die.wav");
	public static Sound blip = loadSound("/snd/blip.wav");
	
	public static Sound loadSound(String fileName) {
		Sound s = new Sound();
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(Sound.class.getResource(fileName));
			s.clip = AudioSystem.getClip();
			s.clip.open(stream);
		} catch (Exception e) {
			System.out.println("Couldnt read "+ fileName);
		}
		return s;
	}
	
	private Clip clip;
	
	public void play() {
		try {
			if (clip != null) {
				new Thread(new Runnable() {
					public void run() {
						synchronized(clip) {
							clip.stop();
							clip.flush();
							clip.setFramePosition(0);
							clip.start();
						}
					}
				}).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
