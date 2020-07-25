package javaPart;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class DisplayingImage extends Component {
	GUIMAIN owner;
	private static final String photoPath = "tmpProject\\photo.bmp";
	private BufferedImage img;
	private int x0, y0, width, height;
	public DisplayingImage(GUIMAIN owner) {
		this.owner = owner;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x0 = e.getX();
				y0 = e.getY();
				System.out.println(x0 + " " + y0);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				width = Math.abs(e.getX() - x0);
				height = Math.abs(e.getY() - y0);
				if(owner.haveSelection) {
					owner.project.addRectangleinSelection(x0, y0, width, height);
					repaint();
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(GUIMAIN.deleteRect) {
					int x1 = e.getX(), y1 = e.getY();
					for(int i = 0; i < owner.project.getSelection().getRectagnles(); i++) {
						int n[] = owner.project.getRectangle(i);
						if(x1 >= n[0] && x1 <= n[0]+n[2] && y1>=n[1] && y1 <= n[1] + n[3]) {
							owner.project.getSelection().removerectInSelection(i);
							break;
						}
						repaint();
					}
				}
			}
			
		});
	}
	public void readImage() {
		try {
			img = ImageIO.read(new File(photoPath));
			System.out.println(img.getWidth());
			System.out.println(img.getHeight());
		} catch (Exception e) {
			System.out.println("Error during loading photo in class DiplayingImage;");
		}
	}
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
		if(GUIMAIN.haveSelection) {
		for(int i = 0; i < owner.project.getSelection().getRectagnles(); i++) {
			int n[] = owner.project.getRectangle(i);
			g.setColor(Color.GREEN);
			g.drawRect(n[0], n[1], n[2], n[3]);
		}
		}
		
	}
	public int getWidth() {
		return img.getWidth();
	}
	public int getHeight() {
		return img.getHeight();
	}
	
}
