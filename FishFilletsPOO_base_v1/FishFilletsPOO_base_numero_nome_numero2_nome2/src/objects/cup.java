package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Cup extends GravitationalGameObject {

	public Cup(Room room) {
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

	@Override
	public boolean isHeavy() {
		return false;
	}

	@Override
	public void fall() {
		Vector2D dir = new Vector2D(0, 1);
		Point2D currentPosition = getPosition();
		Point2D newPosition = currentPosition.plus(dir);
		for(GameObject obj : getRoom().getObjectsAt(newPosition)) {
			if(obj instanceof Water && getRoom().getObjectsAt(newPosition).size() == 1) {
				setPosition(newPosition);
			}
			if(obj instanceof holedWall && getRoom().getObjectsAt(newPosition).size() == 2) {
				setPosition(newPosition);
			}
		}
	}
	
	

}
