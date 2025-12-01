package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class Blood extends GameObject{

	public Blood(Room room) {
		super(room);
	}

	public Blood(Point2D point, Room room) {
        super(point, room);
    }

	@Override
	public String getName() {
		return "blood";
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

}