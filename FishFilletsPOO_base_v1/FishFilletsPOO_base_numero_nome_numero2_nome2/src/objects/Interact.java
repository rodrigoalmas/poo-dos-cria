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

    public void Moveble(Point2D pos, Vector2D dir){
		for (GameObject obj : getRoom().getObjects()) {
			if (obj.getPosition().equals(pos) && obj instanceof Interact){
				Point2D currentPosition = obj.getPosition();
				Point2D newPosition = currentPosition.plus(dir);
				obj.setPosition(newPosition);
			}	
		}	
		
	}


}
