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
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board3 extends JPanel implements ActionListener {

	private Timer timer;
	private PlayerSquare square;
	private ArrayList<Alien> aliens;
	private boolean ingame;
	private final int DELAY = 10;
	private final int ICRAFT_X = 40;
	private final int ICRAFT_Y = 60;
	private final int B_WIDTH = 940;
	private final int B_HEIGHT = 815;
	private final int NUM = 100;
	private int[][] allenz;
	
	public Board3(int gameX, int gameY) {
		
		
		
		initBoard(940, 815);
	
	}
	
	
	public void initAliens(){
		aliens = new ArrayList<>();
		
		allenz = new int[NUM][2];
		Random r = new Random();
		
		for(int i = 0; i < NUM; i++){
			allenz[i][0] = r.nextInt(2000) + 500;
			allenz[i][1] = r.nextInt(800);
		}
		
		
		for(int[] p : allenz){
			aliens.add(new Alien(p[0], p[1]));
		}
	}
	
	private void initBoard(int gameX, int gameY){
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		ingame = true;
		
		square = new PlayerSquare(ICRAFT_X, ICRAFT_Y, gameX, gameY);
		
		initAliens();
		
		timer = new Timer(DELAY, this);
		timer.start();
		
	}
	
	public void setCharacter(int p){
		square.setPlayer(p);
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(ingame){
			drawObjects(g);
		}
		else
		{
			drawGameOver(g);
		}
		
		//doDrawing(g);
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void drawObjects(Graphics g){
		
		if (square.isVisible()) {
            g.drawImage(square.getImage(), square.getX(), square.getY(),
                    this);
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
        g.drawString("Aliens left: " + aliens.size(), 5, 15);
		
		
		/*Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(square.getImage(), square.getX(), square.getY(), this);
		
		ArrayList ms =  square.getMissiles();
		
		for(Object m1 : ms) {
			Missile m = (Missile) m1;
			g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
		}*/
		
	}
	
	private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
    }
	
	private void updateMissiles(){
		ArrayList<Missile> ms = square.getMissiles();
		
		for(int i = 0; i < ms.size(); i++){
			Missile m = (Missile) ms.get(i);
			
			if(m.isVisible()){
				m.move();
			}
			else
			{
				ms.remove(i);
			}
		}
	}
	
	private void updateAliens() {
		if(aliens.isEmpty()){
			ingame = false;
			return;
		}
		
		for (int i = 0; i < aliens.size(); i++){
			Alien a = aliens.get(i);
			if(a.isVisible()){
				a.move();
			}else{
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
	                }}}
	        }
	
	private void updateCraft(){
		if(square.isVisible()){
			square.move();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		updateMissiles();
		updateCraft();
		updateAliens();
		
		checkCollisions();
		
		repaint();
	}
	
	private void inGame(){
		if(!ingame){
			timer.stop();
		}
	}
	
	private class TAdapter extends KeyAdapter{
		
		@Override
		public void keyReleased(KeyEvent e){
			square.keyReleased(e);
		}
		
		@Override
		public void keyPressed(KeyEvent e){
			square.keyPressed(e);
		}
	}
	
	
}
