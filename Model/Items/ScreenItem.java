package Model.Items;

import java.awt.image.BufferedImage;

// classe astratta per metodi e varibili comuni fra gli oggetti
public class ScreenItem implements Drawable {

    protected BufferedImage image;
    protected int imageWidth;
    protected int imageHeight;
    protected int position[]; // position[0] = x, position[1] = y;
    protected String path;
    
    public ScreenItem(String path, int width, int height, int[] position) {
        this.imageWidth = width;
        this.imageHeight = height;
        this.position = position;
        this.path = path;
    }
    
    public ScreenItem(int width, int height, int[] position) {
        this.imageWidth = width;
        this.imageHeight = height;
        this.position = position;
    }

    public BufferedImage getImage() {
    	return image;
    }
    
    public int[] getPosition() {
        return position;
    }
    
    public int getXPosition() {
        return position[0];
    }
    
    public int getYPosition() {
        return position[1];
    }

    public void hit() {
    }

    public void setPosition(int x, int y) {

        this.position[0] = x;
        this.position[1] = y;
    }


    public int getImageWidth() {
        return imageWidth;
    }


    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }


    public int getImageHeight() {
        return imageHeight;
    }


    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

}