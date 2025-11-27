package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import objects.Water;
import objects.Cup;
import objects.holedWall;
import objects.BigFish;
import objects.GameObject;
import objects.Interact;
import objects.SmallFish;
import objects.Wall;
import objects.SteelHorizontal;
import objects.SteelVertical;
import objects.Stone;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Room {
	
	private List<GameObject> objects;
	private String roomName;
	private GameEngine engine;
	private Point2D smallFishStartingPosition;
	private Point2D bigFishStartingPosition;
	
	public Room() {
		objects = new ArrayList<GameObject>();
	}

	private void setName(String name) {
		roomName = name;
	}
	
	public String getName() {
		return roomName;
	}
	
	private void setEngine(GameEngine engine) {
		this.engine = engine;
	}

	public void addObject(GameObject obj) {
		objects.add(obj);
		engine.updateGUI();
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
		engine.updateGUI();
	}
	
	public List<GameObject> getObjects() {
		return objects;
	}

	public void setSmallFishStartingPosition(Point2D heroStartingPosition) {
		this.smallFishStartingPosition = heroStartingPosition;
	}
	
	public Point2D getSmallFishStartingPosition() {
		return smallFishStartingPosition;
	}
	
	public void setBigFishStartingPosition(Point2D heroStartingPosition) {
		this.bigFishStartingPosition = heroStartingPosition;
	}
	
	public Point2D getBigFishStartingPosition() {
		return bigFishStartingPosition;
	}

	public boolean isHoledWall(Point2D pos) {
		for (GameObject obj : objects) {

			if (obj.getPosition().equals(pos) && obj instanceof holedWall) 
				return true;
		}
		return false;
	}

	public ArrayList<GameObject> getObjectsAt(Point2D p) {
		ArrayList<GameObject> lista = new ArrayList<>();
		for(GameObject obj : objects) {
			if(obj.getPosition().equals(p)) {
				lista.add(obj);
			}
		}
		return lista;
	}

	public GameObject getObjectAt(Point2D p, int layer) {
		ArrayList<GameObject> objetos = getObjectsAt(p);
		for(GameObject obj : objetos) {
			if(obj.getLayer() == layer);
			return obj;
		}
		return null;
	}

	public Interact getInteractObjectAt(Point2D pos) {
		for(GameObject obj : getObjectsAt(pos)) {
			if(obj instanceof Interact interact) {
				return interact;
			}
		}
		return null;
	}

	public boolean isValid(Point2D pos) {


		for (GameObject obj : objects) {

			if (obj.getPosition().equals(pos) && obj.isSolid()) 
				return false;
		}

	
		return true;
	}

	public void moveble(Point2D pos, Vector2D dir){
		for (GameObject obj : objects) {
			if (obj.getPosition().equals(pos) && obj instanceof Interact){
				Point2D currentPosition = obj.getPosition();
				Point2D newPosition = currentPosition.plus(dir);
				if(isValid(newPosition) && !isHoledWall(newPosition))
					obj.setPosition(newPosition);
			}	
		}	
		
	}


	
	public static Room readRoom(File f, GameEngine engine) {
		Room r = new Room();
		r.setEngine(engine);
		r.setName(f.getName());
		try (Scanner sc = new Scanner(f)) {
			int line = 0;
			while(sc.hasNextLine()) {
				String l = sc.nextLine();
				for(int col = 0; col < l.length(); col++) {
					char c = l.charAt(col);
					GameObject water = new Water(r);
					water.setPosition(new Point2D(col, line));
					r.addObject(water);
					if(c == 'W') {
						GameObject wall = new Wall(r);
						wall.setPosition(new Point2D(col, line));
						r.addObject(wall);
					}
					if(c == 'B') {
						GameObject bf = BigFish.getInstance();
						bf.setPosition(new Point2D(col, line));
						r.addObject(bf);
						
					}
					if(c == 'S') {
						GameObject sf = SmallFish.getInstance();
						sf.setPosition(new Point2D(col, line));
						r.addObject(sf);
						
					}

					if(c == 'H') {
						GameObject sh = new SteelHorizontal(r);
						sh.setPosition(new Point2D(col, line));
						r.addObject(sh);
						
					}
					if(c == 'c') {
						GameObject sh = new Cup(r);
						sh.setPosition(new Point2D(col, line));
						r.addObject(sh);
						
					}
					if(c == 'X') {
						GameObject sh = new holedWall(r);
						sh.setPosition(new Point2D(col, line));
						r.addObject(sh);
						
					}
					if(c == 'V') {
						GameObject sv = new SteelVertical(r);
						sv.setPosition(new Point2D(col, line));
						r.addObject(sv);
						
					}

					if(c == 'R') {
						GameObject rock = new Stone(r);
						rock.setPosition(new Point2D(col, line));
						r.addObject(rock);
					}


					if(l.length() < 10 && col == l.length() - 1) {
						while(col < 10) {
							GameObject water2 = new Water(r);
							water2.setPosition(new Point2D(col, line));
							r.addObject(water2);
							col++;
						}
						
					}	
				}
				line++;
			}


		} catch(FileNotFoundException e) {
			System.out.println("Ficheiro " + f.getName() + " não encontrado");
		}
		return r;
		
	}
	
}