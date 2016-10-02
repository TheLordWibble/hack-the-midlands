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
	public static final int DAN = 3;
	public static final int VOLKER = 4;
	public static final int JOHN = 5;
	private String missileLocation;
	private int gameX;
	private int gameY;

	private AudioStream aud;
	private AudioStream quips;

	public PlayerSquare(int x, int y, int gameX, int gameY) {
		super(x, y);
		this.gameX = gameX;
		this.gameY = gameY;
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
		this.missileLocation = Links.ACHIM_W;

		loadImage(Links.ACHIM);
		getImageDimensions();

	}

	public void setPlayer(int p) {
		switch (p) {
		case ACHIM:
			loadImage(Links.ACHIM);
			this.missileLocation = Links.ACHIM_W;
			break;
		case MARTIN:
			loadImage(Links.MARTIN);
			this.missileLocation = Links.MARTIN_W;
			break;
		case KASHIF:
			loadImage(Links.KASHIF);
			this.missileLocation = Links.KASHIF_W;
			break;
		case DAN:
			loadImage(Links.DAN);
			this.missileLocation = Links.DAN_W;
			break;
		case VOLKER:
			loadImage(Links.VOLKER);
			this.missileLocation = Links.VOLKER_W;
			break;
		case JOHN:
			loadImage(Links.JOHN);
			this.missileLocation = Links.JOHN_W;
			break;

		}
	}

	public void move() {

		if (dx > 0 && x < gameX) {
			x += dx;
		} else if (dx < 0 && x > 0) {
			x += dx;
		}

		if (dy > 0 && y < gameY) {
			y += dy;
		} else if (dy < 0 && y > 0) {
			y += dy;
		}
	}

	public ArrayList getMissiles() {
		return missiles;
	}

	private void makeQuote(String p) {

		InputStream kek;
		try {
			AudioPlayer.player.stop(quips);
			kek = new FileInputStream(p);

			quips = new AudioStream(kek);
		} catch (IOException e) {
			e.printStackTrace();
		}
		AudioPlayer.player.start(quips);
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
		case KeyEvent.VK_4:
			setPlayer(DAN);
			makeQuote(Links.DAN_Q);
			changeMusic(Links.DAN_MUS);
			break;
		case KeyEvent.VK_5:
			setPlayer(VOLKER);
			changeMusic(Links.VOLKER_MUS);
			break;
		case KeyEvent.VK_6:
			setPlayer(JOHN);
			changeMusic(Links.JOHN_MUS);
			break;
		case KeyEvent.VK_SPACE:
			fire();
			break;
		case KeyEvent.VK_LEFT:
			dx = -2;
			break;
		case KeyEvent.VK_RIGHT:
			dx = 2;
			break;
		case KeyEvent.VK_UP:
			dy = -2;
			break;
		case KeyEvent.VK_DOWN:
			dy = 2;
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
