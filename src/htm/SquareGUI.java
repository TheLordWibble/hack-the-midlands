package htm;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class SquareGUI extends JFrame{

	public SquareGUI(int x, int y) {
		initUI(x, y);
	}
	
	private void initUI(int x, int y){
		add(new Board3());
		
		setSize(x, y);
		setResizable(false);
		
		setTitle("Achim's Quest");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	

}
