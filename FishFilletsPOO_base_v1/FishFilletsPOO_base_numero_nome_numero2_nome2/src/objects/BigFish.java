package objects;

import pt.iscte.poo.game.Room;

public class BigFish extends GameCharacter {

	private static BigFish bf = new BigFish(null);
	
	private BigFish(Room room) {
		super(room);
	}

	public static BigFish getInstance() {
		return bf;
	}
	
	@Override
	public String getName() {
		if(direction == "left")
			return "bigFishLeft";
		if(direction == "right")
			return "bigFishRight";
		else{
			return "bigFishLeft";
		}
	}

	@Override
	public int getLayer() {
		return 1;
	}
	
	@Override
	public boolean isSolid() {
        return true;
    }

	public boolean isInteract(){
        return false;
    }
	
}
