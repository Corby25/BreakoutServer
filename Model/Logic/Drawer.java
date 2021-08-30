package Model.Logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import Model.Items.ScreenItem;

public class Drawer {
	
	Graphics graphics;
	BufferStrategy buffer;
	
	public Drawer() {
	}
	
	public void draw(ScreenItem item) {
		
		int[] position = item.getPosition();
		int imageWidth = item.getImageWidth();
		int imageHeight = item.getImageHeight();
		BufferedImage image = item.getImage();
		graphics.drawImage(image, position[0], position[1], imageWidth, imageHeight, null);
	}
	
	public void draw(String string, int x, int y) {
		
		graphics.drawString(string, x, y);
	}
	
	public void loadGraphics(Graphics graphics) {
		
		graphics.setFont(new Font("Courier", Font.BOLD, 20)); 
		graphics.setColor(Color.WHITE);
		this.graphics = graphics;
	}
}
