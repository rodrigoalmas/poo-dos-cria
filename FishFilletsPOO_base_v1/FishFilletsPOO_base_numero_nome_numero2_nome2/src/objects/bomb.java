package objects;

import pt.iscte.poo.game.Room;

public class Bomb extends GravitationalGameObject {

	public Bomb(Room room) {
		super(room);
	}

	@Override
	public String getName() {
		return "bomb";
	}

	@Override
	public int getLayer() {
		return 6;
	}

	@Override
	public boolean isSolid() {
        return true;
    }

	@Override
	public boolean isHeavy(){
		return false;
	} 

	@Override
	public void fall() {
		
	}
	


}
