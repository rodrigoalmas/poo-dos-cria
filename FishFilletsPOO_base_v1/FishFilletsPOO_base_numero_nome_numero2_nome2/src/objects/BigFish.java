package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class BigFish extends GameCharacter {

	private static BigFish bf = new BigFish(null);
	
	private BigFish(Room room) {
		super(room);
	}

	public static BigFish getInstance() {
		return bf;
	}
	
	@Override
	public String getName() {
		if(direction == "left")
			return "bigFishLeft";
		if(direction == "right")
			return "bigFishRight";
		else{
			return "bigFishLeft";
		}
	}

	@Override
	public int getLayer() {
		return 2;
	}
	
	@Override
	public boolean isSolid() {
        return true;
    }

	public boolean isInteract(){
        return false;
    }

	@Override
	public void move(Vector2D dir) {
		Vector2D left = new Vector2D(-1, 0);
		Vector2D right = new Vector2D(1,0);


		Point2D currentPosition = getPosition();
		Point2D newPosition = currentPosition.plus(dir);
		if(getRoom().getObjectByLayer(newPosition, 7) == null  && getRoom().getObjectByLayer(newPosition, 1) == null) {
			getRoom().moveble(newPosition, dir);
			if(getRoom().isValid(newPosition)){
				setPosition(newPosition);
			}
		}
		if(dir.equals(left))
			this.direction = "left";
		if(dir.equals(right))
			this.direction = "right";
	}
	
}
