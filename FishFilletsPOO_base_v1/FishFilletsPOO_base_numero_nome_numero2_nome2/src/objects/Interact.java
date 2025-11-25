package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public abstract class Interact extends GameObject{
    public Interact(Room room) {
		super(room);
	}
	
	public Interact(Point2D position, Room room) {
		super(position, room);
	}


}
