package GUI;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagesLoader {
	
	static ImagesLoader instance;
	
	private ImagesLoader() {}
	
	// carica immagini nel buffer
	BufferedImage image;
	public BufferedImage uploadImage(String position) {
		try {
			image = ImageIO.read(getClass().getResource(position));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	public static synchronized ImagesLoader getInstace() {
		if(instance == null) {
			instance = new ImagesLoader();
		}
		return instance;
	}

}