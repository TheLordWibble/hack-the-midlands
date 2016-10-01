package htm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel {
	
	private Image image;

	public Board() {
		
		initBoard();

	}

	private void initBoard(){
		
		loadImage();
		
		int w = image.getWidth(this);
		int h = image.getHeight(this);
		setPreferredSize(new Dimension(w, h));
	}
	
	private void loadImage(){
		ImageIcon ii = new ImageIcon("src/images/achim.jpg");
		image = ii.getImage();
	}
	
	@Override
	public void paintComponent(Graphics g) {

		
		
		g.drawImage(image, 0, 0, null);

		//drawDoughnut(g);
		
		
	}

	private void drawDoughnut(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);

		Dimension size = getSize();
		double w = size.getWidth();
		double h = size.getHeight();

		Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.gray);
		
		for(double deg = 0; deg < 360; deg += 5){
			AffineTransform at = AffineTransform.getTranslateInstance(w/2, h/2);
			at.rotate(Math.toRadians(deg));
			g2d.draw(at.createTransformedShape(e));
		}
	}

}
