package htm;

import java.awt.EventQueue;

import javax.swing.JPanel;

public class GamePanel extends JPanel{

	SquareGUI board;
	
	public GamePanel(int x, int y) {
		
		this.board = new SquareGUI(x, y);
	}
	
	public void setVisible(boolean vis){
		board.setVisible(vis);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
			
				
				GamePanel ex = new GamePanel(400, 400);
				ex.setVisible(true);
				
			}
		});
	}
	
}
