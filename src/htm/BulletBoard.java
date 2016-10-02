package htm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A class to create the area in which the game takes place,
 */

public class BulletBoard extends JPanel implements ActionListener {

	private Timer timer;
	private Player square;
	private ArrayList<Alien> aliens;
	private ArrayList<Missile> markMissiles;
	private boolean ingame;
	private final int DELAY = 10;
	private final int ICRAFT_X = 40;
	private final int ICRAFT_Y = 60;
	private final int B_WIDTH = 940;
	private final int B_HEIGHT = 815;
	private final int NUM = 20;
	private int[][] allenz;
	private boolean newG;
	private boolean died;
	private Level currLevel;
	private Level level1;
	private Level level2;
	private Level level3;
	private Level level4;
	private Level level5;
	private LinkedList<Level> levels;
	private int player;
	private int deaths;
	private int deathsThisLevel;
	private int maxLevel = 5;
	private boolean won;
	private Boss boss;

	/**
	 * Create a new bullet board
	 * 
	 * @param gameX
	 *            the width of the board (unused as of 0804)
	 * @param gameY
	 *            the height of the board (unused as of 0804)
	 * @param player
	 *            the initial player character of the game (see Player.java for
	 *            list)
	 */

	public BulletBoard(int gameX, int gameY, int player) {

		// create the different levels

		level1 = new Level(20, 3, Links.SABS, 1, false, 1);
		level2 = new Level(30, 4, Links.GOODMAN, 2, false, 1);
		level3 = new Level(40, 5, Links.ALEX, 3, false, 1);
		level4 = new Level(50, 6, Links.MEEK, 4, false, 1);
		level5 = new Level(1, 3, Links.MARK, 5, true, 100);

		// add the levels to the levels list

		levels = new LinkedList<>();
		levels.add(level2);
		levels.add(level3);
		levels.add(level4);
		levels.add(level5);
		levels.add(level1);

		// sets up various initial parameters

		markMissiles = new ArrayList<>();
		deaths = 0;
		this.player = player;
		newG = true;
		currLevel = level5;
		initBoard(940, 815, currLevel);

	}

	/**
	 * Initialise the board
	 * 
	 * @param gameX
	 *            the width of the game board
	 * @param gameY
	 *            the height of the game board
	 * @param level
	 *            the initial level
	 */

	private void initBoard(int gameX, int gameY, Level level) {

		// setting the initial level as the current level

		currLevel = level;
		boss = null;

		// initialise the aliens

		if (!currLevel.isBoss()) {
			initAliens(level);
		} else {
			initBoss(level);
		}

		// set up parameters

		ingame = true;
		died = false;
		won = false;

		// if it's the first time playing some other things must be set up
		// (It's important this is only done once otherwise it lags and does all
		// sorts of werid stuff)

		if (newG) {
			addKeyListener(new TAdapter());
			setFocusable(true);
			setBackground(Color.BLACK);

			square = new Player(ICRAFT_X, ICRAFT_Y, gameX, gameY, player);

			timer = new Timer(DELAY, this);
			timer.start();
			newG = false;
		}

		// complete the initialisation of the board

		square.setBoss(false);

		if (currLevel.isBoss()) {
			square.changeMusic(Links.MARK_MUS);
			square.setBoss(true);
		}

		square.setVisible(true);
		square.setPlayer(player);
		repaint();

	}

	/**
	 * Initialise all the aliens on the board
	 * 
	 * @param level
	 *            the level (used to determine amount of aliens)
	 */

	public void initAliens(Level level) {

		// create various data structures

		aliens = new ArrayList<>();
		allenz = new int[level.getEnemies()][2];

		// randomly place the monsters

		Random r = new Random();

		for (int i = 0; i < level.getEnemies(); i++) {
			allenz[i][0] = r.nextInt(2000) + 500;
			allenz[i][1] = r.nextInt(800);
		}

		// add the monsters to the data structure

		for (int[] p : allenz) {
			aliens.add(new Alien(p[0], p[1], level.getEnemyType(), level.getMaxSpeed(), level.getHits()));
		}
	}

	public void initBoss(Level level) {
		aliens = new ArrayList<>();
		boss = new Boss(500, 250, level.getHits(), level.getEnemyType(), level.getMaxSpeed(), markMissiles);
		aliens.add(boss);
	}

	/**
	 * Change between characters
	 * 
	 * @param p
	 *            the character
	 */

	public void setCharacter(int p) {
		square.setPlayer(p);

	}

	/**
	 * Draw objects on the board
	 * 
	 * @param g
	 *            the graphics object
	 */

	private void drawObjects(Graphics g) {

		// draw the player if they are visible

		if (square.isVisible()) {
			g.drawImage(square.getImage(), square.getX(), square.getY(), this);
		}

		ArrayList<Missile> ms = square.getMissiles();

		for (Missile m : ms) {
			if (m.isVisible()) {
				g.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}
		}
		
		if(currLevel.isBoss()){
			ArrayList<Missile> mes = boss.getMissiles();
			for (Missile m : mes) {
				if (m.isVisible()) {
					g.drawImage(m.getImage(), m.getX(), m.getY(), this);
				}
			}
		}

		for (Alien a : aliens) {
			if (a.isVisible()) {
				g.drawImage(a.getImage(), a.getX(), a.getY(), this);
			}
		}

		g.setColor(Color.WHITE);
		g.drawString("Lads left: " + aliens.size() + " ~ Press ENTER to restart ~ Press ESC to quit ~ Level "
				+ currLevel.getNo() + " ~ Deaths: " + deaths, 5, 15);

	}

	private void drawGameOver(Graphics g) {

		String msg = "";

		if (died) {
			msg = "Game Over ~ Press ENTER to restart";

		} else if (currLevel.getNo() == maxLevel) {
			msg = "Ultimate Victory! ~ You died on this level " + deaths + " times ~ You died " + deaths
					+ " times in the whole game! ~ Press ENTER to restart or ESC to quit";
			won = true;
		} else {
			msg = "Victory! ~ You died on this level " + deaths + " times ~ Press ENTER to continue";
			won = true;
		}

		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fm = getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);

	}

	private void updateMissiles() {
		ArrayList<Missile> ms = square.getMissiles();

		for (int i = 0; i < ms.size(); i++) {
			Missile m = (Missile) ms.get(i);

			if (m.isVisible()) {
				m.move();
			} else {
				ms.remove(i);
			}
		}
	}

	private void updateAliens() {
		if (aliens.isEmpty()) {
			ingame = false;
		}

		for (int i = 0; i < aliens.size(); i++) {
			Alien a = aliens.get(i);
			if (a.isVisible()) {
				a.move();
			} else {
				aliens.remove(i);
			}
		}
	}

	public void checkCollisions() {

		Rectangle r3 = square.getBounds();

		for (Alien alien : aliens) {
			Rectangle r2 = alien.getBounds();

			if (r3.intersects(r2)) {
				square.setVisible(false);
				alien.setVisible(false);
				ingame = false;
				died = true;
			}
		}

		if (currLevel.isBoss()) {

			ArrayList<Missile> markMissiles = boss.getMissiles();

			for (Missile marky : markMissiles) {

				Rectangle r7 = marky.getBounds();

				if (r7.intersects(r3)) {
					square.setVisible(false);
					marky.setVisible(false);
					ingame = false;
					died = true;

				}

			}
		}

		ArrayList<Missile> ms = square.getMissiles();

		for (Missile m : ms) {

			Rectangle r1 = m.getBounds();

			for (Alien alien : aliens) {

				Rectangle r2 = alien.getBounds();

				if (r1.intersects(r2)) {
					alien.gotHit();
					if (alien.getHealth() < 0) {
						alien.setVisible(false);
					}
					m.setVisible(false);
				}
			}
		}
	}

	private void updateCraft() {
		if (square.isVisible()) {
			square.move();
		}
	}

	private void updateMarkMissiles() {
		ArrayList<Missile> ms = boss.getMissiles();
		
		for (int i = 0; i < ms.size(); i++) {
			Missile m = (Missile) ms.get(i);
			
			if (m.isVisible()) {
				m.move();
			} else {
				ms.remove(i);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		updateMissiles();
		updateCraft();
		updateAliens();

		if (currLevel.isBoss()) {
			updateMarkMissiles();
		}

		checkCollisions();

		repaint();
	}

	private void inGame() {
		if (!ingame) {
			timer.stop();
		}
	}

	private Level nextLevel() {
		Level temp = levels.pop();
		levels.add(temp);
		return temp;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (ingame) {
			drawObjects(g);
		} else {
			drawGameOver(g);
		}

		// doDrawing(g);

		Toolkit.getDefaultToolkit().sync();
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {

			square.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			if (key == KeyEvent.VK_ENTER) {
				square.end();
				aliens.clear();

				if (!died && won) {
					deathsThisLevel = 0;
					initBoard(940, 815, nextLevel());
				} else {
					deaths++;
					deathsThisLevel++;
					initBoard(940, 815, currLevel);
				}

			} else if (key == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			} else if (!ingame) {

			} else {

				switch (key) {
				case KeyEvent.VK_1:
					player = Player.ACHIM;
					break;
				case KeyEvent.VK_2:
					player = Player.MARTIN;
					break;
				case KeyEvent.VK_3:
					player = Player.KASHIF;
					break;
				case KeyEvent.VK_4:
					player = Player.DAN;
					break;
				case KeyEvent.VK_5:
					player = Player.VOLKER;
					break;
				case KeyEvent.VK_6:
					player = Player.JOHN;
					break;
				}

				square.keyPressed(e);
			}
		}
	}

}
