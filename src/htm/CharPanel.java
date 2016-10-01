package htm;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JRadioButton;



public class CharPanel extends JPanel{

	private HashMap<Integer, Image> peeps;
	
	public CharPanel(Board3 board) {
		
		this.setSize(200, 400);

		peeps.put(PlayerSquare.ACHIM, (new ImageIcon("src/images/achim-head.png")).getImage());
		peeps.put(PlayerSquare.MARTIN,(new ImageIcon("src/images/martin-head.png")).getImage()))
		
		JRadioButton achim = new JRadioButton(peeps.get(PlayerSquare.ACHIM));
		achim.addActionListener(e -> board.setCharacter(PlayerSquare.ACHIM));
		achim.setSelected(true);
		
		JRadioButton martin = new JRadioButton();
		martin.addActionListener(e -> board.setCharacter(PlayerSquare.MARTIN));
		
		ButtonGroup charas = new ButtonGroup();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(achim);
		add(martin);
		
		
	}

}
