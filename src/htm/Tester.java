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
				frame.setSize(600, 400);
				frame.setVisible(true);
				
				Board3 game = new Board3();
				CharPanel chara = new CharPanel(game);
				
				frame.setLayout(new BorderLayout());
				
				frame.add(game, BorderLayout.CENTER);
				frame.add(chara, BorderLayout.EAST);
				
				frame.setSize(600, 400);
				frame.setResizable(false);
				
				frame.setTitle("Achim's Quest");
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			}
		});
	}

}
