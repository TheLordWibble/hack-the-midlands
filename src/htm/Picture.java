package htm;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Picture extends JFrame {

	public Picture() {
		initUI();
	}

	private void initUI() {
		add(new Board());

		pack();

		setTitle("SURPRISE!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Picture ex = new Picture();
				ex.setVisible(true);
			}
		});
	}

}
