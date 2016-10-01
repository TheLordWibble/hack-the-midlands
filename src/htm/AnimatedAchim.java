package htm;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class AnimatedAchim extends JFrame {

	public AnimatedAchim() {
		initUI();
	}

	private void initUI(){
		add(new Board2());
		setResizable(false);
		pack();
		
		setTitle("ACHIM!!");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame ex = new AnimatedAchim();
                ex.setVisible(true);   
				
			}
		});
	}
	
}


