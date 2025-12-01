package objects;

import pt.iscte.poo.game.Room;

public class Stone extends GravitationalGameObject {

	public Stone(Room room) {
		super(room);
	}

	@Override
	public String getName() {
		return "stone";
	}

	@Override
	public int getLayer() {
		return 5;
	}

	@Override
	public boolean isSolid() {
        return true;
    }

    @Override
	public boolean isHeavy() {
		return true;
	}
}
