package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class GameCharacter extends GameObject {
	
	public GameCharacter(Room room) {
		super(room);
	}
	
	public void move(Vector2D dir) {
		Vector2D left = new Vector2D(-1, 0);
		Vector2D right = new Vector2D(1,0);


		Point2D currentPosition = getPosition();
		Point2D newPosition = currentPosition.plus(dir);
		if(!(this instanceof BigFish && getRoom().getObjectByLayer(newPosition, 7) != null )) {
			getRoom().moveble(newPosition, dir);
			if(getRoom().isValid(newPosition)){
				setPosition(newPosition);
			}
		}
		if(getRoom().bigFishOut() && this instanceof BigFish) BigFish.getInstance().die();
		if(getRoom().smallFishOut() && this instanceof SmallFish) SmallFish.getInstance().die();


		if(dir.equals(left))
			this.direction = "left";
		if(dir.equals(right))
			this.direction = "right";
	}

	@Override
	public int getLayer() {
		return 2;
	}

	public abstract void die();

	
	
}