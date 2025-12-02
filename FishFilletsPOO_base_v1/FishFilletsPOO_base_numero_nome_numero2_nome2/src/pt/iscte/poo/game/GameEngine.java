package pt.iscte.poo.game;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import objects.SmallFish;
import objects.BigFish;
import objects.GameObject;
import objects.GravitationalGameObject;
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
	private boolean buttonClicked ;
	
	public GameEngine() {
		rooms = new HashMap<String,Room>();
		loadGame();
		roomNumber = 0;
		currentRoom = rooms.get("room" + roomNumber +".txt");
		buttonClicked = false;
		updateGUI();
		SmallFish.getInstance().setRoom(currentRoom);
		SmallFish.getInstance().setPosition(currentRoom.getSmallFishStartingPosition());
		BigFish.getInstance().setRoom(currentRoom);
		BigFish.getInstance().setPosition(currentRoom.getBigFishStartingPosition());
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
			buttonClicked = true;
			int k = ImageGUI.getInstance().keyPressed();
			switch(k) {
				case KeyEvent.VK_R:
					restart();
					return;
				case KeyEvent.VK_SPACE:
					changeSelectedFish();
					return;
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_UP:
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_RIGHT:
					if((currentRoom.bigFishOut() && bigFishSelected) || (currentRoom.smallFishOut() && !bigFishSelected)) changeSelectedFish();
					break;
				default:
					return;
			}
			
			if(!bigFishSelected) {
				SmallFish.getInstance().move(Direction.directionFor(k).asVector());
			} else {
				BigFish.getInstance().move(Direction.directionFor(k).asVector());
			}
		}
		if(currentRoom.bigFishOut() && currentRoom.smallFishOut()) nextLevel();
		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t) {
			processTick();
			if(buttonClicked) {
				boolean someoneDied = currentRoom.applyGravity();
				if(someoneDied) {
					ImageGUI.getInstance().showMessage("Message", "GameCharacter died");
					restart();
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

	public void restart() {
		loadGame();
		currentRoom = rooms.get("room" + roomNumber +".txt");
		updateGUI();
		SmallFish.getInstance().setRoom(currentRoom);
		SmallFish.getInstance().setPosition(currentRoom.getSmallFishStartingPosition());
		BigFish.getInstance().setRoom(currentRoom);
		BigFish.getInstance().setPosition(currentRoom.getBigFishStartingPosition());
	}

	public void nextLevel() {
		roomNumber++;
		/* Falta implementar (verificação para caso seja o ultimo nivel)
		if(roomNumber == rooms.size() - 1) {
			finishGame();
		}
		 */
		loadGame();
		currentRoom = rooms.get("room" + roomNumber +".txt");
		updateGUI();
		SmallFish.getInstance().setRoom(currentRoom);
		SmallFish.getInstance().setPosition(currentRoom.getSmallFishStartingPosition());
		BigFish.getInstance().setRoom(currentRoom);
		BigFish.getInstance().setPosition(currentRoom.getBigFishStartingPosition());
	}

	/* falta implementar
	public void finishGame() {

	}
	 */
}
