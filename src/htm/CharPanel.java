package htm;

import java.awt.Font;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CharPanel extends JPanel {

	private HashMap<Integer, Image> peeps;

	public CharPanel() {

		Board board1 = new Board(Links.ACHIM);
		Board board2 = new Board(Links.MARTIN);
		Board board3 = new Board(Links.KASHIF);
		Board board4 = new Board(Links.DAN);
		Board board5 = new Board(Links.VOLKER);
		Board board6 = new Board(Links.JOHN);
		
		JLabel label1 = new JLabel("1");
		JLabel label2 = new JLabel("2");
		JLabel label3 = new JLabel("3");
		JLabel label4 = new JLabel("4");
		JLabel label5 = new JLabel("5");
		JLabel label6 = new JLabel("6");
		label1.setFont(new Font("SansSerif", Font.BOLD, 40));
		label1.setBorder(new EmptyBorder(0, 10, 0, 10));
		label2.setFont(new Font("SansSerif", Font.BOLD, 40));
		label2.setBorder(new EmptyBorder(0, 10, 0, 10));
		label3.setFont(new Font("SansSerif", Font.BOLD, 40));
		label3.setBorder(new EmptyBorder(0, 10, 0, 10));
		label4.setFont(new Font("SansSerif", Font.BOLD, 40));
		label4.setBorder(new EmptyBorder(0, 10, 0, 10));
		label5.setFont(new Font("SansSerif", Font.BOLD, 40));
		label5.setBorder(new EmptyBorder(0, 10, 0, 10));
		label6.setFont(new Font("SansSerif", Font.BOLD, 40));
		label6.setBorder(new EmptyBorder(0, 10, 0, 10));
		
		
		// setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(board1);
		add(label1);
		add(board2);
		add(label2);
		add(board3);
		add(label3);
		add(board4);
		add(label4);
		add(board5);
		add(label5);
		add(board6);
		add(label6);
		
		/*
		 * setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		 * 
		 * ImageIcon ii = new ImageIcon("src/images/achim-head.jpg"); Image
		 * image = ii.getImage(); add(new CharBox(image, 1)); add(new
		 * JLabel("1"));
		 */

	}

}
