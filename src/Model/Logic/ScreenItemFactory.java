package Model.Logic;

import Model.Items.Ball;
import Model.Items.Item;
import Model.Items.ScreenItem;
import Utility.Utilities;

public class ScreenItemFactory {
	
	private static ScreenItemFactory instance;
	
	private ScreenItemFactory() {}
	
	public static synchronized ScreenItemFactory getInstance() {
        if (instance == null) {
            instance = new ScreenItemFactory();
        }
        return instance;
    }
	
	public ScreenItem getScreenItem(Item item) {
		int[] position = new int[2];
		String path;
		ScreenItem obj;
		
		switch(item) {
		
		case SFONDO:
			position[0] = 0;
			position[1] = 0;
			path = "/Images/sfondo.jpeg";
			obj = new ScreenItem(path, Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT, position);
			break;
		case BOX:
			position[0] = 495;
			position[1] = 0;
			path = "/Images/box.png";
			obj = new ScreenItem(path, Utilities.BOX_WIDTH, Utilities.BOX_HEIGHT, position);
			break;
		case WIN:
			position[0] = 495/2-150;
			position[1] = Utilities.SCREEN_HEIGHT/2 - 100;
			path = "/Images/youWin.png";
			obj = new ScreenItem(path, 300, 70, position);
			break;
		case LOSE:
			position[0] = 495/2-250;
			position[1] = Utilities.SCREEN_HEIGHT/2;
			path = "/Images/lose.png";
			obj = new ScreenItem(path, 500, 500, position);
			break;
		case BALL:
			position[0] = 250;
			position[1] = 500;
			obj = new Ball(Utilities.BALL_WIDTH, Utilities.BALL_HEIGHT, position);
			break;
		case HIT:
			position[0] = 508;
			position[1] = 20;
			path = "/Images/hit.png";
			obj = new ScreenItem(path, Utilities.HIT_WIDTH, Utilities.HIT_HEIGHT, position);
			break;
		case SWITCH:
			position[0] = 508;
			position[1] = Utilities.POSITION_Y_POWER_UP;
			path = "/Images/flipLogo.png";
			obj = new ScreenItem(path, Utilities.LOGO_POWER_UP_WIDTH, Utilities.LOGO_POWER_UP_HEIGHT, position);
			break;
		case SPEED_UP:
			position[0] = 508;
			position[1] = Utilities.POSITION_Y_POWER_UP + 50;
			path = "/Images/fastLogo.png";
			obj = new ScreenItem(path, Utilities.LOGO_POWER_UP_WIDTH, Utilities.LOGO_POWER_UP_HEIGHT, position);
			break;
		case LONG_UP:
			position[0] = 508;
			position[1] = Utilities.POSITION_Y_POWER_UP + 100;
			path = "/Images/big.png";
			obj = new ScreenItem(path, Utilities.LOGO_POWER_UP_WIDTH, Utilities.LOGO_POWER_UP_HEIGHT, position);
			break;
		case SHORT_UP:
			position[0] = 508;
			position[1] = Utilities.POSITION_Y_POWER_UP + 150;
			path = "/Images/little.png";
			obj = new ScreenItem(path, Utilities.LOGO_POWER_UP_WIDTH, Utilities.LOGO_POWER_UP_HEIGHT, position);
			break;
		default:
			obj = null;
		}	
		return obj;
	}
	
	/*
	 * Se aggiungo un terzo power Up position[1] = Utilities.POSITION_Y_POWER_UP + (3*parse)
	 * On/off sono gia a posto.
	 */
	public ScreenItem[] getScreenItem(Item item, int numberItem) {
		ScreenItem[] obj = new ScreenItem[numberItem];
		String path;
		int n = numberItem;
		int parse = 25;
		
		while(n != 0) {
			int[] position = new int[2];
			
			switch(item) {

			case BALL:
				position[0] = 250;
				position[1] = 500;
				obj[numberItem - n] = new Ball(Utilities.BALL_WIDTH, Utilities.BALL_HEIGHT, position);
				break;
			case SWITCH:
				position[0] = 508;
				position[1] = Utilities.POSITION_Y_POWER_UP;
				path = "/Images/flipLogo.png";
				obj[numberItem - n] = new ScreenItem(path, Utilities.LOGO_POWER_UP_WIDTH, Utilities.LOGO_POWER_UP_HEIGHT, position);
				break;
			case SPEED_UP:
				position[0] = 508;
				position[1] = Utilities.POSITION_Y_POWER_UP + (2*parse);
				path = "/Images/fastLogo.png";
				obj[numberItem - n] = new ScreenItem(path, Utilities.LOGO_POWER_UP_WIDTH, Utilities.LOGO_POWER_UP_HEIGHT, position);
				break;
			case ON:
				position[0] = 508;
				position[1] = Utilities.POSITION_Y_POWER_UP + parse + (2*parse*(numberItem-n));
				path = "/Images/on.png";
				obj[numberItem - n] = new ScreenItem(path, Utilities.LOGO_POWER_UP_WIDTH, Utilities.LOGO_POWER_UP_HEIGHT, position);
				break;
			case OFF:
				position[0] = 508;
				position[1] = Utilities.POSITION_Y_POWER_UP + parse + (2*parse*(numberItem-n));
				path = "/Images/off.png";
				obj[numberItem - n] = new ScreenItem(path, Utilities.LOGO_POWER_UP_WIDTH, Utilities.LOGO_POWER_UP_HEIGHT, position);
				break;
			case LIFE:
				position[0] = 505;
				position[1] = 78 + (10*(numberItem-n));
				path = "/Images/life.png";
				obj[numberItem - n] = new ScreenItem(path, 20, 20, position);
				break;
			case PADDLE:
				//position[0] = 200
				//position[1] = 
			default:
				obj[numberItem - n] = null;
			}
			n--;
		}	
		return obj;
	}


}
