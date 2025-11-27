package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class SmallFish extends GameCharacter {

	private static SmallFish sf = new SmallFish(null);
	
	private SmallFish(Room room) {
		super(room);
	}

	public static SmallFish getInstance() {
		return sf;
	}
	
	@Override
	public String getName() {
		if(direction == "left")
			return "smallFishLeft";
		if(direction == "right")
			return "smallFishRight";
		else{
			return "smallFishLeft";
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

	/*
	@Override
	public void move(Vector2D dir) {
		Vector2D left = new Vector2D(-1, 0);
		Vector2D right = new Vector2D(1,0);


		Point2D currentPosition = getPosition();
		Point2D newPosition = currentPosition.plus(dir);


		getRoom().Moveble(newPosition, dir);
		if(getRoom().isValid(newPosition)){
			setPosition(newPosition);
		}


		if(dir.equals(left))
			this.direction = "left";
		if(dir.equals(right))
			this.direction = "right";
	}
	 */
}
