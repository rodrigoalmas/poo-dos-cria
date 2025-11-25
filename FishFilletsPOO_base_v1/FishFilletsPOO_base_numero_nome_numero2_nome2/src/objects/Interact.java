package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Interact extends GameObject{
    public Interact(Room room) {
		super(room);
	}
	
	public Interact(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "Interact";
	}

	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public boolean isSolid() {
        return true;
    }

    public boolean isMovable(Point2D pos){
		/*
		for (GameObject obj : getRoom().getObjects()) {
			if (obj.getPosition().equals(pos) && obj instanceof Interact){
				Point2D currentPosition = obj.getPosition();
				Point2D newPosition = currentPosition.plus(dir);
				obj.setPosition(newPosition);
			}	
		}
		*/
		return getRoom().getObjectAt(pos).contains(this); 
		
	}

	public boolean canMove(Point2D newPos) {
		if(getPosition().distanceTo(newPos) == 1) {
			for(GameObject obj : getRoom().getObjectAt(newPos)){ 
				if(obj.isSolid()) {
					return false;
				}	
			}
			return true; 
		}
		return false;
 	}

	public void move(Vector2D dir) {
		Point2D currentPosition = getPosition();
		Point2D newPosition = currentPosition.plus(dir);
		setPosition(newPosition);
	}

}
