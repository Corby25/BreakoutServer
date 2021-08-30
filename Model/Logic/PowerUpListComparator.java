package Model.Logic;

import java.util.Comparator;

import Model.Items.PowerUp.PowerUp;

public class PowerUpListComparator implements Comparator<PowerUp> {

	@Override
	public int compare(PowerUp o1, PowerUp o2) {
		if(o1.whichPower().ordinal() > o2.whichPower().ordinal()) {
			return 1;
		} else if(o1.whichPower().ordinal() < o2.whichPower().ordinal()) {
			return -1;
		} else return 0;
	}
}
