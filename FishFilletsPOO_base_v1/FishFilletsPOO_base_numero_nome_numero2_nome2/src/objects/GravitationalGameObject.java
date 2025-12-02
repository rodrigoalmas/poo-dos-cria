package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class GravitationalGameObject extends GameObject implements Interact{
    public GravitationalGameObject(Room room) {
        super(room);
    }

    public GravitationalGameObject(Point2D point, Room room) {
        super(point, room);
    }

    public boolean fall() {
		Vector2D dir = new Vector2D(0, 1);
		Point2D currentPosition = getPosition();
		Point2D newPosition = currentPosition.plus(dir);
		if(gameCharacterBelow() instanceof SmallFish smallFish && (this.isHeavy() || objectsAbove() > 0)) {
			Point2D bloodPosition = smallFish.getPosition();
			getRoom().removeObject(smallFish);
			Blood blood = new Blood(bloodPosition, getRoom());
			getRoom().addObject(blood);
			
			//setPosition(newPosition);
			return true;
		}

		if(gameCharacterBelow() instanceof BigFish bigFish) {
			if((this.isHeavy() && objectsAbove() > 0) || (existsHeavyObjectAbove(getPosition()) && objectsAbove() > 0)) {
				Point2D bloodPosition = bigFish.getPosition();
				getRoom().removeObject(bigFish);
				Blood blood = new Blood(bloodPosition, getRoom());
				getRoom().addObject(blood);
				//setPosition(newPosition);
				return true;
			}
		}
		if(getRoom().getNonWaterObjectAt(newPosition) == null) {
			setPosition(newPosition);
		}
		return false;
	}

	public boolean existsObjectAbove(Point2D pos) {
		Point2D abovePosition = pos.plus(new Vector2D(0, -1));
		GameObject aboveObject = getRoom().getGravObjectAt(abovePosition);
		return aboveObject instanceof GravitationalGameObject;
	}

	public int objectsAbove() {
		int c = 0;
		Point2D pos = getPosition();
		while(existsObjectAbove(pos)) {
			c++;
			pos = pos.plus(new Vector2D(0, -1));
		}
		return c;
	}

	public boolean existsHeavyObjectAbove(Point2D pos) {
		while(existsObjectAbove(pos)) {
			Point2D abovePosition = pos. plus(new Vector2D(0, -1));
			GameObject objectAbove = getRoom().getNonWaterObjectAt(abovePosition);
			if(objectAbove instanceof GravitationalGameObject gravitationalGameObject && gravitationalGameObject.isHeavy()) {
				return true;
			} 
			pos = abovePosition;
		}
		return false;
	}

	

	public GameObject gameCharacterBelow() {
		GameObject fish = getRoom().getGameCharacter(getPosition().plus(new Vector2D(0, 1)));
		if(fish != null) return fish;
		return null;
	}



    
}
