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
    
   
    /*
    //i metodi setxdir e setydir vengono chiamati quando la pallina colpisce il paddle o il mattone
    public void setXdir(int xdir) {
        this.xdir = xdir;
        if (this.xdir*xdir1<0) xdir1*=-1;
    }

    public void setYdir(int ydir) {
        this.ydir = ydir;
        if (this.ydir*ydir1<0) ydir1*=-1;
    }
    
    public int getSpeed() {
    	int vel = xdir1;
    	if (xdir1<0) vel*=-1;
    	return vel;
    }
    
    public void incrSpeed() {
    	if (xdir1==-1) xdir1 -= 1;
    	else if(xdir1==1) xdir1 += 1;
    	if (ydir1==-1) ydir1 -= 1;
    	else if (ydir1==1) ydir1 += 1;   		
    }
    */
}