package Model.Items;

import java.awt.image.BufferedImage;

public interface Drawable {
	
	public BufferedImage getImage();
	
	public int[] getPosition();
	
	public int getImageWidth();
	
	public int getImageHeight();

}
