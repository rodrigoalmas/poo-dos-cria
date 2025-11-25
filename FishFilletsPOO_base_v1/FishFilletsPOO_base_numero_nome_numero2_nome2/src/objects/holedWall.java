package objects;

import pt.iscte.poo.game.Room;

public class holedWall extends GameObject {

	public holedWall(Room room) {
		super(room);
	}

	@Override
	public String getName() {
		return "holedWall";
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
