package htm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board3 extends JPanel implements ActionListener {

	private Timer timer;
	private PlayerSquare square;
	private final int DELAY = 10;
	private final int ICRAFT_X = 40;
	private final int ICRAFT_Y = 60;
	
	public Board3() {
		initBoard();
	}
	
	private void initBoard(){
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		
		square = new PlayerSquare(ICRAFT_X, ICRAFT_Y);
		
		timer = new Timer(DELAY, this);
		timer.start();
		
	}
	
	public void setCharacter(int p){
		square.setPlayer(p);
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		doDrawing(g);
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void doDrawing(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(square.getImage(), square.getX(), square.getY(), this);
		
		ArrayList ms =  square.getMissiles();
		
		for(Object m1 : ms) {
			Missile m = (Missile) m1;
			g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
		}
		
	}
	
	private void updateMissiles(){
		ArrayList ms = square.getMissiles();
		
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
	
	private void updateCraft(){
		square.move();
	}

	@Override
	public void actionPerformed(ActionEvent e){
		updateMissiles();
		updateCraft();
		repaint();
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
