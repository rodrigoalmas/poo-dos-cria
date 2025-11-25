package objects;

import pt.iscte.poo.game.Room;

public class stone extends Interact {

	public stone(Room room) {
		super(room);
	}

	@Override
	public String getName() {
		return "stone";
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
