package objects;

import pt.iscte.poo.game.Room;

public class anchor extends Interact {

	public anchor(Room room) {
		super(room);
	}

	@Override
	public String getName() {
		return "anchor";
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
