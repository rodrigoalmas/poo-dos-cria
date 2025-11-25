package objects;

import pt.iscte.poo.game.Room;

public class trap extends GameObject {

	public trap(Room room) {
		super(room);
	}

	@Override
	public String getName() {
		return "cup";
	}

	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public boolean isSolid() {
        return true;
    }
	
	

}
