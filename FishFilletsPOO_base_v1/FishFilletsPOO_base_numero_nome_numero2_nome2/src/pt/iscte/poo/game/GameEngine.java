package pt.iscte.poo.game;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import objects.SmallFish;
import objects.BigFish;
import objects.GameObject;
import objects.GravitationalGameObject;
import objects.Interact;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;

public class GameEngine implements Observer {
	
	private Map<String,Room> rooms;
	private Room currentRoom;
	private int roomNumber;
	private int lastTickProcessed = 0;
	private boolean bigFishSelected = false;
	
	public GameEngine() {
		rooms = new HashMap<String,Room>();
		loadGame();
		roomNumber = 0;
		currentRoom = rooms.get("room" + roomNumber +".txt");
		updateGUI();		
		SmallFish.getInstance().setRoom(currentRoom);
		BigFish.getInstance().setRoom(currentRoom);
	}

	private void loadGame() {
		File[] files = new File("./rooms").listFiles();
		for(File f : files) {
			rooms.put(f.getName(),Room.readRoom(f,this));
		}
	}

	@Override
	public void update(Observed source) {

		if (ImageGUI.getInstance().wasKeyPressed()) {
			int k = ImageGUI.getInstance().keyPressed();
			// 11/11 -->
			switch(k) {
				case KeyEvent.VK_SPACE:
					changeSelectedFish();
					return;
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_UP:
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_RIGHT:
					break;
				default:
					return;
			}
			if(!bigFishSelected) {
				SmallFish.getInstance().move(Direction.directionFor(k).asVector());
			} else {
				BigFish.getInstance().move(Direction.directionFor(k).asVector());
			}
			// <-- 11/11
		}
		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t) {
			processTick();
			for(GameObject obj : currentRoom.getObjects()) {
				if(obj instanceof GravitationalGameObject gravObj) {
					gravObj.fall();
				}
			}
		}
		ImageGUI.getInstance().update();
	}

	private void processTick() {		
		lastTickProcessed++;
	}

	public void updateGUI() {
		if(currentRoom!=null) {
			ImageGUI.getInstance().clearImages();
			ImageGUI.getInstance().addImages(currentRoom.getObjects());
			
		}
	}

	public void changeSelectedFish() {
		this.bigFishSelected = !this.bigFishSelected;
	}


	
}
