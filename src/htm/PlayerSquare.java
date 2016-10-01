package htm;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class PlayerSquare extends Sprite {

	private int dx, dy;
	private ArrayList missiles;
	private Image image;
	public static final int ACHIM = 0;
	public static final int MARTIN = 1;
	public static final int KASHIF = 2;
	private String missileLocation;

	private AudioStream aud;

	public PlayerSquare(int x, int y) {
		super(x, y);
		initPlayer();
	}

	private void initPlayer() {

		InputStream test;
		try {
			test = new FileInputStream(Links.ACHIM_MUS);

			aud = new AudioStream(test);
		} catch (IOException e) {
			e.printStackTrace();
		}
		AudioPlayer.player.start(aud);
		/*
		 * String bip = Links.ACHIM_MUS;
		 * 
		 * System.out.println(bip);
		 * 
		 * Media hit = new Media(bip);
		 */

		/*
		 * MediaPlayer mediaPlayer = new MediaPlayer(hit); mediaPlayer.play();
		 */

		/*
		 * mediaPlayer = new MediaPlayer(new Media(Links.ACHIM_MUS));
		 * mediaPlayer.play();
		 */

		missiles = new ArrayList();
		this.missileLocation = Links.ACHIM_S;

		loadImage(Links.ACHIM);
		getImageDimensions();

	}

	public void setPlayer(int p) {
		switch (p) {
		case ACHIM:
			loadImage(Links.ACHIM);
			this.missileLocation = Links.ACHIM_S;
			break;
		case MARTIN:
			loadImage(Links.MARTIN);
			this.missileLocation = Links.MARTIN_S;
			break;
		case KASHIF:
			loadImage(Links.KASHIF);
			this.missileLocation = Links.KASHIF_S;
			break;
		}
	}

	public void move() {
		x += dx;
		y += dy;
	}

	public ArrayList getMissiles() {
		return missiles;
	}

	private void changeMusic(String p) {
		
		InputStream test;
		try {
			AudioPlayer.player.stop(aud);
			
			test = new FileInputStream(p);

			aud = new AudioStream(test);
		} catch (IOException e) {
			e.printStackTrace();
		}
		AudioPlayer.player.start(aud);
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_1:
			setPlayer(ACHIM);
			changeMusic(Links.ACHIM_MUS);
			break;
		case KeyEvent.VK_2:
			setPlayer(MARTIN);
			changeMusic(Links.MARTIN_MUS);
			break;
		case KeyEvent.VK_3:
			setPlayer(KASHIF);
			changeMusic(Links.KASHIF_MUS);
			break;
		case KeyEvent.VK_SPACE:
			fire();
			break;
		case KeyEvent.VK_LEFT:
			dx = -1;
			break;
		case KeyEvent.VK_RIGHT:
			dx = 1;
			break;
		case KeyEvent.VK_UP:
			dy = -1;
			break;
		case KeyEvent.VK_DOWN:
			dy = 1;
		}
	}

	private void fire() {
		missiles.add(new Missile(x + width, y + height / 2, width * 20, missileLocation));
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_LEFT:
			dx = 0;
			break;
		case KeyEvent.VK_RIGHT:
			dx = 0;
			break;
		case KeyEvent.VK_UP:
			dy = 0;
			break;
		case KeyEvent.VK_DOWN:
			dy = 0;
		}
	}

}
