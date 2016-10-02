package htm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Tester {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				JFrame frame = new JFrame();
				frame.setVisible(true);
				
				int gameX = 1000;
				int gameY = 1000;
				
				Board3 game = new Board3(gameX, gameY);
				CharPanel pann = new CharPanel();
				
				frame.setLayout(new BorderLayout());
				
				frame.add(game, BorderLayout.CENTER);
				frame.add(pann, BorderLayout.SOUTH);
				
				frame.setSize(gameX, gameY);
				frame.setResizable(false);
				
				frame.setTitle("Achim's Quest");
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			}
		});
	}

}
