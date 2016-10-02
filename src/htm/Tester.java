package htm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Tester {

	private static void remind(){
		System.out.println("<1> Achim");
		System.out.println("<2> Martin");
		System.out.println("<3> Kashif");
		System.out.println("<4> Dan");
		System.out.println("<5> Volker");
		System.out.println("<6> John");
	}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				int player = Player.ACHIM;
				
				try{
					player = Integer.parseInt(args[0]) - 1;
					
					if (player < 1 || player > 7){
						throw new NumberFormatException();
					}
					
				}catch(NumberFormatException e){
					
					remind();
					System.exit(1);
				}
				
				JFrame frame = new JFrame();
				frame.setVisible(true);
				
				int gameX = 1000;
				int gameY = 1000;
				
				Board3 game = new Board3(gameX, gameY, player);
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
