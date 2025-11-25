package objects;

import pt.iscte.poo.game.Room;

public class Water extends GameObject{

	public Water(Room room) {
		super(room);
	}

	@Override
	public String getName() {
		return "water";
	}

	@Override
	public int getLayer() {
		return 0;
	}
	
	@Override
	public boolean isSolid() {
        return false;
    }
	
	public boolean isInteract(){
        return false;
    }

	/*
	@Override
	public boolean isGravitacional() {
		return false;
	}
	 */

}
