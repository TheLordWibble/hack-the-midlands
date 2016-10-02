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

public class Board3 extends JPanel implements ActionListener {

	private Timer timer;
	private Player square;
	private ArrayList<Alien> aliens;
	private boolean ingame;
	private final int DELAY = 10;
	private final int ICRAFT_X = 40;
	private final int ICRAFT_Y = 60;
	private final int B_WIDTH = 940;
	private final int B_HEIGHT = 815;
	private final int NUM = 20;
	private int[][] allenz;
	private boolean newG;
	private boolean won;
	private Level currLevel;
	private Level level1;
	private Level level2;
	private Level level3;
	private LinkedList<Level> levels;
	private int player;
	private int deaths;
	private int deathsThisLevel;
	private int maxLevel = 3;

	public Board3(int gameX, int gameY, int player) {

		deaths = 0;
		levels = new LinkedList<>();
		
		level1 = new Level(50, 3, Links.SABS, 1);
		level2 = new Level(60, 4, Links.GOODMAN, 2);
		level3  = new Level(70, 5, Links.ALEX, 3);
		
		levels.add(level2);
		levels.add(level3);
		levels.add(level1);

		this.player = player;

		newG = true;
		currLevel = level1;
		initBoard(940, 815, currLevel);

	}

	public void initAliens(Level level) {

		aliens = new ArrayList<>();
		allenz = new int[level.getEnemies()][2];
		Random r = new Random();

		for (int i = 0; i < level.getEnemies(); i++) {
			allenz[i][0] = r.nextInt(2000) + 500;
			allenz[i][1] = r.nextInt(800);
		}

		for (int[] p : allenz) {
			aliens.add(new Alien(p[0], p[1], level.getEnemyType(), level.getMaxSpeed()));
		}
	}

	private void initBoard(int gameX, int gameY, Level level) {

		currLevel = level;
		
		won = false;
		initAliens(level);
		ingame = true;

		if (newG) {
			addKeyListener(new TAdapter());
			setFocusable(true);
			setBackground(Color.BLACK);

			square = new Player(ICRAFT_X, ICRAFT_Y, gameX, gameY);

			timer = new Timer(DELAY, this);
			timer.start();
			newG = false;
		}

		square.setVisible(true);
		square.setPlayer(player);
		repaint();

	}

	public void setCharacter(int p) {
		square.setPlayer(p);

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

	private void drawObjects(Graphics g) {

		if (square.isVisible()) {
			g.drawImage(square.getImage(), square.getX(), square.getY(), this);
		}

		ArrayList<Missile> ms = square.getMissiles();

		for (Missile m : ms) {
			if (m.isVisible()) {
				g.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}
		}

		for (Alien a : aliens) {
			if (a.isVisible()) {
				g.drawImage(a.getImage(), a.getX(), a.getY(), this);
			}
		}

		g.setColor(Color.WHITE);
		g.drawString("Lads left: " + aliens.size() + " ~ Press ENTER to restart ~ Press ESC to quit ~ Level " + currLevel.getNo() + " ~ Deaths: " + deaths, 5, 15);

		/*
		 * Graphics2D g2d = (Graphics2D) g; g2d.drawImage(square.getImage(),
		 * square.getX(), square.getY(), this);
		 * 
		 * ArrayList ms = square.getMissiles();
		 * 
		 * for(Object m1 : ms) { Missile m = (Missile) m1;
		 * g2d.drawImage(m.getImage(), m.getX(), m.getY(), this); }
		 */

	}

	private void drawGameOver(Graphics g) {

		String msg = "";

		if (!won) {
			msg = "Game Over ~ Press ENTER to restart";
		} else if (currLevel.getNo() == maxLevel) {
			msg = "Ultimate Victory! ~ You died on this level " + deaths + " times ~ You died " + deaths + " times in the whole game! ~ Press ENTER to restart or ESC to quit";
		} else {
			msg = "Victory! ~ You died on this level " + deaths + " times ~ Press ENTER to continue";
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
			won = true;
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
			}
		}

		ArrayList<Missile> ms = square.getMissiles();

		for (Missile m : ms) {

			Rectangle r1 = m.getBounds();

			for (Alien alien : aliens) {

				Rectangle r2 = alien.getBounds();

				if (r1.intersects(r2)) {
					m.setVisible(false);
					alien.setVisible(false);
				}
			}
		}
	}

	private void updateCraft() {
		if (square.isVisible()) {
			square.move();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		updateMissiles();
		updateCraft();
		updateAliens();

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
		System.out.println(temp);
		levels.add(temp);
		return temp;
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

				if (won) {
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
