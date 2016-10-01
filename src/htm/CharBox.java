package htm;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CharBox extends JPanel{
	
	private Image profile;
	private int number;
	
	public CharBox(Image profile, int number) {

		this.profile = profile;
		this.number = number;
		
		
	
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(profile, 0, 0, null);
		
		
	}
	
	

}
