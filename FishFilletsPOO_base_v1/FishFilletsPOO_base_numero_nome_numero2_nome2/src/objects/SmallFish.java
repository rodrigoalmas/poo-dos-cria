package objects;

import pt.iscte.poo.game.Room;

public class SmallFish extends GameCharacter {

	private static SmallFish sf = new SmallFish(null);
	
	private SmallFish(Room room) {
		super(room);
	}

	public static SmallFish getInstance() {
		return sf;
	}
	
	@Override
	public String getName() {
		if(direction == "left")
			return "smallFishLeft";
		if(direction == "right")
			return "smallFishRight";
		else{
			return "smallFishLeft";
		}
	}

	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public boolean isSolid() {
        return false;
    } 
	

}
