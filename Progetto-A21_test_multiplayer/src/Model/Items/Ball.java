package Model.Items;

import java.awt.image.BufferedImage;

public class Ball extends ScreenItem{

    private boolean active;
    private int xDirection;
    private int yDirection;
    private int speed;
    public Ball(BufferedImage image, int width, int height, int[] position) {
        super(image, width, height, position);
        this.active = true;
        xDirection = 1;
        yDirection = -1;
        speed = (int) Utilities.DEFAULT_SPEED;    // costante di incremento velocit�
    }
    

    public void move() {
        position[0] += xDirection + xDirection * speed;
        position[1] += yDirection + yDirection * speed;
    }
    
    

    public void setXdir(int xdir) {
		this.xDirection = xdir;
	}

	public void setYdir(int ydir) {
		this.yDirection = ydir;
	}

	public int getXdir() {
        return xDirection;
    }
    
    public int getYdir() {
        return yDirection;
    }
    
    public void incrSpeed() {
    	speed++;
    }
    
    public int getSpeed() {
    	return speed;
    }
    
    public void setSpeed(double defaultBallSpeed) {
    	this.speed = (int) defaultBallSpeed ;
    }
    
    public void refresh() {
    	position[0] = Utilities.INITIAL_POSITION_PADDLE_X;
    	position[1] = Utilities.INITIAL_POSITION_BALL_Y;
    	xDirection = Utilities.INITIAL_DIRECTION_BALL_X;
    	yDirection = Utilities.INITIAL_DIRECTION_BALL_Y;
    	speed = (int) Utilities.DEFAULT_SPEED;
    }
}