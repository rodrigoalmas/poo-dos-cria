package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class Interact extends GameObject{
    public Interact(Room room) {
		super(room);
	}
	
	public Interact(Point2D position, Room room) {
		super(position, room);
	}

	/*
	public void fall() {
		Vector2D dir = new Vector2D(0, 1);
		Point2D currentPosition = getPosition();
		Point2D newPosition = currentPosition.plus(dir);
		for(GameObject obj : getRoom().getObjectAt(newPosition)) {
			if(obj instanceof Water && getRoom().getObjectAt(newPosition).size() == 1) {
				setPosition(newPosition);
			}
		}
	}
	 */	
	

	public abstract boolean isHeavy();


}
