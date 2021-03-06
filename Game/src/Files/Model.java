package Files;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.*;

public class Model implements Serializable {
	private final int FRAMEWIDTH;
    private final int FRAMEHEIGHT;
    
    static final int PREDHEIGHT = 50;
    static final int PREDWIDTH = 50;
    static final int QUIZCOUNT = 3;
    static final int END_FALCON_COUNT = 600;
    static final int DISTANCE_WIN = 700;

	static Boolean withPlayer = true;
	static Boolean withoutPlayer = false;
	static Boolean withPreds = true;
	static Boolean withoutPreds = false;
	static Boolean movePredators;
	static Boolean slideObjects = false;
	static Boolean slidePredators = false;
	static Boolean RKquiz = false;
	static Boolean CRquiz = false;
	static Boolean running = true;
	static Boolean enableTut = true;
	static Boolean recovering = false;
	public static Boolean answered = false;
	public static Boolean RKtutorial = true;
	public static Boolean showMap = false;
	public static Boolean rkWin = false;
	public static Boolean crWin = false;
	public static Boolean crLost = false;
	
    private static int xloc;
    private static int yloc;
    
    static String predStr = "Predator";
    static String gamePcString = "GamePiece";
    static String twigString = "Twig";
    static String bushString = "Bush";
    
    static Animal clapperRail;
    static Animal redKnot;
    static ArrayList<Animal> predators = new ArrayList<Animal>();
    //static ArrayList<GamePiece> gamePieces = new ArrayList<GamePiece>();
    static ArrayList<GamePiece> allObjects = new ArrayList<GamePiece>();
    static ArrayList<Cloud> clouds = new ArrayList<Cloud>();
    
    public static int collisionCount = 0;
	static int M_RANDMAX = 4;
	static int M_RANDMIN = 1;
	static int P_RANDMAX = 23;
	static int P_RANDMIN = 0;
	static int xTotal = 0;
	static int quizNum;
	static int falconCount = 0;
	static int distanceCount = 0;
   
	static int gameMode;
	//static int alpha;
	static final int MENU = 0;
	static final int CLAPPERRAIL0 = 1; 
	static final int CLAPPERRAIL1 = 2;
	static final int CLAPPERRAIL2 = 3;
	static final int CLAPPERRAIL3 = 4;
	static final int REDKNOT0 = 5;
	static final int REDKNOT = 6;
	static final int WINNER = 7;
	static final int LOSER = 8;
	
	static boolean movedTutorial = false;

	static final int CLOUD_FAST = 16;
	
	static int mapClk = 0;
	static int mapClkMax = 10000;
	static int clk1Count = 0;
	static int clk2Count = 0;
	static double clk3Count = 0;
	static int clk4Count = 0;
	static final double CLK4MAX = 200;
	static final double CLKMAX = 1000;
	static final double CLK2MAX = CLKMAX/256;
	static final int GRAVEYARD = 100000;
	static int spawnX;
	static final int LEVEL_END = 100001;
	static final int PREDATOR_SIZE = 50;
	static final int PREDATOR_SPACE = PREDATOR_SIZE*4;
	static final int DEFAULT_BUSH_ALPHA = 100;
	static final int MAX_HEALTH = 100;
	static final int DEFAULT_RK_X = 100;
	static final int PREDS_DISABLE_TUTORIAL = 50;
	
	static double variableClock = CLK2MAX;
	static int twigCount = 0;
	static int bushCount = 0;
	static int deathToll; //temp
	static int playerHealth;
	static int twigMax = 2; //bird can only hold 2 twigs at a time
	static int bushMax = 5; 
	static int bushMaxTutorial = 2;
	static int bushTrans = 50;
	static int flightTime = 0;
	static int predCount = 9;
	static final int DEAD = 5; //5 collisions = dead
	
	static int[] yPoints = {0, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600,
			650, 700, 750, 800, 850, 900, 950, 1000, 1050, 1100, 1150, 1200, 1250, 1300,
			1350, 1400, 1450, 1500, 1550, 1600, 1650, 1700};
    
    private static Direction dir = Direction.NORTH;
    private static Direction dirPred;

    public Model(int fw, int fh) {
        this.FRAMEWIDTH = fw;
        this.FRAMEHEIGHT = fh;
    }
    

    /**
	 * Changes the game and resets the scene. 0 = Main menu, 1 = Clapper rail, 2 = Red knot
	 *
	 * @author Amjed Hallak, Paul Jureidini, Amelia Abobo, Adheena Chacko
	 * @param Game mode to change to. Either 0, 1, 2, 3, 4, 5, 6, 7, or 8
	 * 
	 * */
    public static void changeGameMode(int gameMode) {

    	switch(gameMode) {
    	case(MENU):
    		gameMode = MENU;
    		rkWin = false;
    		crWin = false;
    		crLost = false;
    		RKquiz = false;
    		running = true;
    		removeAllObjects();
    		startMenu();
    		clapperRail = null;
    		redKnot = null;
    		View.gameMode = MENU;
    		movedTutorial = false;
    		answered = false;
    		break;
    	case(CLAPPERRAIL0):
    		resetBushTrans();
			twigCount = 0;
			bushCount = 0;
			deathToll = 0;
			playerHealth = MAX_HEALTH;
			setUpClapperRailGameLevel(0);
			View.gameMode = CLAPPERRAIL0;
			break;
    	case(CLAPPERRAIL1):
    		resetBushTrans();
    		twigCount = 0;
    		bushCount = 0;
    		deathToll = 0;
    		playerHealth = MAX_HEALTH;
    		setUpClapperRailGameLevel(1);
    		View.gameMode = CLAPPERRAIL1;
    		break;
    	case(CLAPPERRAIL2):
    		resetBushTrans();
    		removeAllObjects();
    		bushTrans = DEFAULT_BUSH_ALPHA; //reset bushTrans
    		twigCount = 0;
    		bushCount = 0;
    		deathToll = 0;
    		playerHealth = MAX_HEALTH;
    		setUpClapperRailGameLevel(2);
    		View.gameMode = CLAPPERRAIL2;
    		//View.resetAlpha();
    		break;
    	case(CLAPPERRAIL3):
    		resetBushTrans();
    		removeAllObjects();
    		twigCount = 0;
    		bushCount = 0;
    		deathToll = 0;
    		playerHealth = MAX_HEALTH;
    		setUpClapperRailGameLevel(3);
    		View.gameMode = CLAPPERRAIL3;
    		break;
    	case(REDKNOT):
    		RKtutorial = true;
    		enableTut = true;
    		falconCount = 0;
    		distanceCount = 0;
    		spawnX = View.frameWidth + 50;
    		removeAllObjects();
    		View.gameMode = REDKNOT;
    		playerHealth = MAX_HEALTH;
    		deathToll = 0;
    		variableClock = CLK2MAX;
    		setUpRedKnotGame();
    		break;
    	case(WINNER):
    		View.gameMode = WINNER;
    		break;
    	case(LOSER):
    		View.gameMode = LOSER;
    		break;
    	}
    }
    
    /**
	 * Switching game levels in Clapper Rail game
	 *
	 * @author Paul Jureidini, Amjed Hallak, Amelia Abobo
	 * 
	 * */
    public static void gameLevelClapperRail() {
    	if(bushCount < bushMaxTutorial && gameMode == CLAPPERRAIL0) {
    		gameMode = CLAPPERRAIL0;
    	}
    	else if(bushCount >= bushMaxTutorial && gameMode == CLAPPERRAIL0) {
    		changeGameMode(CLAPPERRAIL1);
    	}
    	else if (bushCount >= bushMax && gameMode == CLAPPERRAIL1) {
    		changeGameMode(CLAPPERRAIL2);
    	}
    	else if(bushCount >= bushMax && gameMode == CLAPPERRAIL2) {
    		changeGameMode(CLAPPERRAIL3);
    	}
    	if(bushCount >= bushMax && gameMode == CLAPPERRAIL3) {
    		running = false;
    		crWin = true;
    	}
    }
    
    
    /**
   	 * Sets up moving visual objects for the main menu
   	 *
   	 * @author Amjed Hallak
   	 * 
   	 * */
    public static void startMenu() {
    	clouds.add(new Cloud(500, 40, 1, 1)); //Spawn cloud at 500(x) 40(y), cloud type 1
    	clouds.add(new Cloud(400, 75, 2, 2)); //Spawn cloud at 400(x) 75(y), cloud type 2
    }
    
    /**
	 * Sets up general objects for the Clapper Rail game 
	 * 
	 * @author Amjed Hallak, Amelia Abobo, Paul Jureidini
	 * @param Level number
	 * 
	 * */
    public static void setUpClapperRailGameLevel(int level) {
    	switch(level) {
    	case(0):
    		//System.out.println("Clapper Rail Tutorial");
			clapperRail = new ClapperRail(150, 200);
			gameMode = CLAPPERRAIL0;
			spawnObject(twigString, 500, 50, 0); //Spawn twig at 200(x) 400(y)
			spawnObject(twigString, 300, 100, 0); //Spawn twig at 300(x) 100(y)
			spawnObject(bushString, 500, 200, 0);//Spawn bush at 500(x) 200(y)
			spawnObject(predStr, 500, 150, 0);//Spawn predator at 500(x) 300(y)
			break;
    	case(1):
    		//System.out.println("Clapper Rail Level 1");
			clapperRail = new ClapperRail(100, 100);
			gameMode = CLAPPERRAIL1;
	    	spawnObject(twigString, 150,400,20); //Spawn twig at 300(x) 100(y)
	    	spawnObject(twigString, 200,500,40);
	    	spawnObject(twigString, 300,250,60);
	    	spawnObject(twigString, 350,300,80);
	    	spawnObject(twigString, 500,150,100);
	    	spawnObject(bushString, 500,200, 0);//Spawn bush at 500(x) 200(y)
	    	spawnObject(predStr, 500, 300, 0);//Spawn predator at 500(x) 300(y)
	    	spawnObject(predStr, 400, 100, 0);
    		break;
    	case(2):
    		//System.out.println("Clapper Rail Level 2");
			clapperRail = new ClapperRail(100, 100);
			gameMode = CLAPPERRAIL2;
			spawnObject(twigString, 300,150,20); //Spawn twig at 300(x) 100(y)
	    	spawnObject(twigString, 200,250,40);
	    	spawnObject(twigString, 100,350,60);
	    	spawnObject(twigString, 400,350,80);
	    	spawnObject(twigString, 500,500,100);
	    	spawnObject(bushString, 500,200, 0);//Spawn bush at 500(x) 200(y)
	    	spawnObject(predStr, 500, 300, 0);
	    	spawnObject(predStr, 300, 400, 0);
	    	spawnObject(predStr, 600, 500, 0);
	    	spawnObject(predStr, 400, 250, 0);
    		break;
    	case(3):
    		//System.out.println("Clapper Rail Level 3");
			clapperRail = new ClapperRail(100, 100);
			gameMode = CLAPPERRAIL3;
			spawnObject(twigString, 450,450,20); //Spawn twig at 300(x) 100(y)
	    	spawnObject(twigString, 300,400,40);
	    	spawnObject(twigString, 350,300,60);
	    	spawnObject(twigString, 400,150,80);
	    	spawnObject(twigString, 550,100,100);
	    	spawnObject(bushString, 500,200, 0);//Spawn bush at 500(x) 200(y)
	    	spawnObject(predStr, 500, 300, 0);
	    	spawnObject(predStr, 300, 200, 0);
	    	spawnObject(predStr, 600, 500, 0);
	    	spawnObject(predStr, 350, 250, 0);
	    	spawnObject(predStr, 600, 100, 0);
	    	spawnObject(predStr, 400, 300, 0);
    		break;
    	}
    }
    
    /**
	 * Sets up general testbed for the Red Knot game. Currently spawns objects
	 *
	 * @author Amjed Hallak, Adheena Chacko
	 * 
	 * */
    public static void setUpRedKnotGame() {
    	redKnot = new RedKnot();
    	redKnot.setX(DEFAULT_RK_X);
    	clouds.add(new Cloud(spawnX, 40, 1, 1)); //Spawn cloud at 500(x) 40(y), cloud speed 1
    	clouds.add(new Cloud(spawnX, 100, 1.5, 2));
    	clouds.add(new Cloud(spawnX, 240, 1.2, 2));
    	clouds.add(new Cloud(spawnX, 400, 1.4, 1));
		gameMode = REDKNOT;
		showMap = true;
		running = false;
    }
 
    /**
	 * Function to return a list of all the present objects on the screen
	 *
	 * @author Amjed Hallak
	 * @return ArrayList of all the birds, predators, and other objects on screen
	 * 
	 * */
    public static ArrayList<GamePiece> getAllObjects(Boolean inclPlayer, Boolean inclPreds){
    	if(inclPlayer)
    		allObjects.add(clapperRail);
    	return allObjects;
    }
    
    /**
	 * Spawns a new object at a specified x and y position
	 *
	 * @author Amjed Hallak, Paul Jureidini
	 * @param Type of object, whether it's a predator or a gamepiece
	 * @param x and y coordinates of the object being spawned
	 * 
	 * */
    static public void spawnObject(String type, int x, int y, int angle) {
    	switch(type) { 
    	case("Predator"):
    		predators.add(new Animal(x,y));
    		break;
    	case("Twig"):
    		allObjects.add(new Twig(x,y, angle));
    		break;
	    case("Bush"):
			allObjects.add(new Bush(x,y));
			break;
		}
    }
    
    /**
	 * Checks for collisions between an animal object, and all game objects onto he screen
	 *
	 * @author Amjed Hallak, Paul Jureidini
	 * @param Animal being compared to for a grid collision
	 * 
	 * */
    public static void chkCollision(Animal b) {
    	try {
	    	for(GamePiece o: allObjects) {
	    		
		    	if (b.getX() == o.getX() && b.getY() == o.getY()) {
		    		
		    		if(o instanceof Twig) {
		    			if(twigCount < twigMax) { //makes sure that there are not more than 2 twigs collected
		    				o.x = GRAVEYARD;
		    				twigCount++;
		    			}
		    		}//end "Twig"
		    		if(o instanceof Animal) {
		    			if(!recovering) {
			    			playerHealth--;
			    			deathToll++;
			    			//System.out.println("1 - RECOVERY BEGIN");
			    			recovering = true;
			    			System.out.println("hi");
		    			}
		    		}
		    		if(o instanceof Bush) {
		    			if(twigCount>0 && bushCount<bushMax) {
		    				bushCount += twigCount;
		    				//System.out.println(bushCount);
		    				//bushTrans += DEFAULT_BUSH_ALPHA*twigCount; //increment bush transparency 
		    				//System.out.println(bus fhTrans);
		    				twigCount = 0;
		    			}else if(twigCount>0 && bushCount == bushMax) {
		    				//System.out.println("checkpoint");
		    			}
		    		}
		    	}
	    	}
	    	gameLevelClapperRail(); //gets called on collision to change levels
	    	for(Animal a: predators) {
		    	if (b.getX() == a.getX() && b.getY() == a.getY()) {
		    		if(a instanceof Animal) {
		    			if(!recovering) {
			    			playerHealth--;
			    			deathToll+=2;
			    			//System.out.println("2 - RECOVERY BEGIN");
			    			recovering = true;
		    			}
		    			if (deathToll >= DEAD) { 
		    				switch(gameMode) {
		    				case(REDKNOT):
		    					quizNum = (int)(Math.random() * QUIZCOUNT + 1);
		    					RKquiz = true;
		    					answered = false;
		    					running = false;
		    					break;
			    			case(CLAPPERRAIL0):
			    			case(CLAPPERRAIL1): //Cycle through all levels
			    			case(CLAPPERRAIL2):
			    			case(CLAPPERRAIL3):
			    				running = false;
			    				crLost = true;
			    				break;
		    				}
		    			}
		    		}
		    	}
	    	}
    	} catch(ConcurrentModificationException e) {
    		System.out.println("CME CAUGHT");
    	}
    }
    
    /**
	 * Moves the animals position based on the input direction
	 *
	 * @author Amjed Hallak
	 * @param Enumerated direction
	 * 
	 * */
    public static void move(Direction dir) {
    	if(clapperRail != null) { //if game is active
	    	clapperRail.move(dir);
			chkCollision(clapperRail);
    	} else if (redKnot != null) {
			redKnot.move(dir);
			chkCollision(redKnot);
    	}
    }
    
    /**
	 * Method to increment all objects toward the left side of the screen
	 * in the Red Knot game. Also generates new objects
	 *
	 * @author Amjed Hallak
	 * 
	 * */
    public static void slideObjectsLeft() {
    	if(slidePredators) {
    		for(Animal p: predators) {
    			p.x -= 1;
    		}
    		for(Cloud c: clouds) {
    			c.move(CLOUD_FAST);
    		}
			xTotal++;
			//System.out.println(xTotal);
    		chkCollision(redKnot);
    	}
    	//System.out.println(flightTime); 


		
    	if((flightTime < LEVEL_END) && slideObjects) {
    		if((flightTime % PREDATOR_SPACE) == 0) {
	    		if(falconCount < END_FALCON_COUNT) {
	    			int[] yCoords = getRandYlist();
	    			int i = 0;
	    			while(i < predCount) {
	    				spawnObject(predStr, spawnX, yCoords[i], 0);
	    				i++;
	    				falconCount++;
	    			}
	    		}
	    		distanceCount += predCount;
    		}
    	}
    }

    /**
	 * Generates a random y-coordinate for the Red knot game
	 *
	 * @author Amjed Hallak
	 * 
	 * */
    public static int[] getRandYlist() {
    	int i = 0;
    	int[] randPts = new int[P_RANDMAX];
    	while(i < predCount) {
    		int random = (int)(Math.random() * P_RANDMAX + P_RANDMIN);
			randPts[i] = yPoints[random];
    		i++;
    	}
    	return randPts;
    }
    
    /**
	 * Method to increment a clock. Effectivley timer method for different
	 * game functions.
	 *
	 * @author Amjed Hallak
	 * 
	 * */
    public static void updateClock() {
    	clk1Count++;
    	clk2Count++;
    	clk3Count++;
    	clk4Count++;
    	if(clk1Count > CLKMAX) {
    		movePredators = true;
    		clk1Count = 0;
    	} else {
    		movePredators = false;
    	}
    	if(clk2Count > CLK2MAX) {
    		slideObjects = true;
    		clk2Count = 0;
    		flightTime++;
    	} else {
    		slideObjects = false;
    	}
    	if(clk3Count > variableClock) {
    		slidePredators = true;
    		clk3Count = 0;
    		//variableClock -= 0.01;
    		//System.out.println(variableClock);
    	} else {
    		slidePredators = false;
    	}
    	if(clk4Count > CLK4MAX) {
    		recovering = false;
    		//System.out.println("RECOVERY END");
    		clk4Count = 0;
    	}
    }
    
    /**
	 * Controls scanning for showing map prior to Red Knot game
	 *
	 * @author Amjed Hallak
	 * 
	 * */
    public void updateMapChk() {
    	if(!enableTut) {
    		showMap = false;
    		running = true;
    		enableTut = false;
    	}
    }
    
    /**
	 * Method for View class to call to check player x and y direction
	 *
	 * @author Amjed Hallak
	 * 
	 * */
	void updateLocationAndDirection() {
		if(falconCount == 0) {
			updateMapChk();
		}
		//System.out.println(distanceCount);
		if(distanceCount > DISTANCE_WIN) {
			//System.out.println(distanceCount);
			running = false;
			rkWin = true;
			distanceCount = 0;
		}
		if(falconCount > PREDS_DISABLE_TUTORIAL) {
			RKtutorial = false;
		}
		if(running) {
			if(clapperRail != null) { //If game is active
				xloc = clapperRail.getX();
				yloc = clapperRail.getY();
				updateClock();
				movePredators();
			} else if (redKnot != null) {
				xloc = redKnot.getX();
				yloc = redKnot.getY();
				updateClock();
				slideObjectsLeft();
				movePredators();
				rotateTwigs();
			} else {
				if(slideObjects) {
					for(Cloud c: clouds) {
			    		c.move(1);
			    	}
				}
				updateClock();
			}
		}
	}
	
    /**
	 * Implements logic for the visual of twig rotation
	 *
	 * @author Amjed Hallak
	 * 
	 * */
	public void rotateTwigs() {
		for(GamePiece p: allObjects) {
			if (p instanceof Twig) {
				((Twig) p).rotate();
			}
		}
	}
	
    /**
	 * Cleans out all objects that are no longer necessary.
	 * Effectively backup/manual garbage collection.
	 *
	 * @author Amjed Hallak
	 * 
	 * */
	public void clean() {
		Iterator<Animal> iter = predators.iterator();
    	while(iter.hasNext()) {
    		if(iter.next().getX() < (-1*PREDWIDTH)) {
    			iter.remove();
    		}
    	}
	}
	
    /**
	 * Removes all objects for game reset
	 *
	 * @author Amjed Hallak
	 * 
	 * */
    public static void removeAllObjects() {
    	Iterator preds = predators.iterator();
    	Iterator ao = allObjects.iterator();
    	Iterator co = clouds.iterator();
    	while(preds.hasNext()) {
    		preds.next();
	    	preds.remove();
    	}
    	while(ao.hasNext()) {
    		ao.next();
    		ao.remove();
    	}
    	while(co.hasNext()) {
    		co.next();
    		co.remove();
    	}
    }
    
    /**
	 * Method to create random movement of the predators in the games
	 *
	 * @author Amjed Hallak
	 * 
	 * */
	void movePredators() {
		if (clapperRail != null) {
			if(movePredators) {
				for(Animal p: predators) {
					int random = (int)(Math.random() * M_RANDMAX + M_RANDMIN);
						//Generate random # between 1 and 4 to determine
						//random direction of movement 
						switch(random) {
						case(1):
							if(p.getX() < (View.frameWidth - Animal.INCR)) {
								dirPred = Direction.EAST;
								p.move(Direction.EAST);
							} else {
								dirPred = Direction.WEST;
								p.move(Direction.WEST);
							}
							break;
						case(2):
							if(p.getX() > 0) {
								dirPred = Direction.WEST;
								p.move(Direction.WEST);
							} else {
								dirPred = Direction.EAST;
								p.move(Direction.EAST);
							}
							break;
						case(3):
							if(p.getY() < (View.frameHeight - Animal.INCR)) {
								dirPred = Direction.SOUTH;
								p.move(Direction.SOUTH);
							} else {
								dirPred = Direction.NORTH;
								p.move(Direction.NORTH);
							}
							break;
						case(4):
							if(p.getY() > 0) {
								dirPred = Direction.NORTH;
								p.move(Direction.NORTH);
							} else {
								dirPred = Direction.SOUTH;
								p.move(Direction.SOUTH);
							}
							break;
						}
				}
				chkCollision(clapperRail);

				clean();
			}
		} else if (gameMode == REDKNOT) {
			clean();
		}
	}
	
	/**
	 * Main method
	 *
	 * @author Team 11-15
	 * @param Array of string arguments. Parameter not currently used
	 * 
	 * */
	
	public static void main(String[] args) {
		//SERIALIZABLE
		//JAVADOC
		//MAGIC NUMBERS
		
		Controller ctrl = new Controller();
		ctrl.start();
	}
    /**
	 * Used to reset bushTrans when the game levels change
	 *
	 * @author Paul Jureidini
	 * 
	 * */
	public static void resetBushTrans() {
		bushTrans = DEFAULT_BUSH_ALPHA;
	}
    
    /**
	 * Modified getter for View class to iterate predators. 
	 * This method of returning prevents comodification exceptions.
	 *
	 * @author Amjed Hallak
	 * @return Arraylist of animals containing all the current predators
	 * at the time of calling
	 * 
	 * */
	public static ArrayList<Animal> getPredators() { 
		ArrayList<Animal> cpPredators = new ArrayList<Animal>();
		for(Animal p: predators)
			cpPredators.add(p);
		return cpPredators;
	}
    /**
	 * Modified getter for View class to iterate clouds. 
	 * This method of returning prevents comodification exceptions.
	 *
	 * @author Amjed Hallak
	 * @return Arraylist of clouds containing all the current clouds
	 * at the time of calling
	 * 
	 * */
	public static ArrayList<Cloud> getClouds() { 
		ArrayList<Cloud> cpclouds = new ArrayList<Cloud>();
		for(Cloud c: clouds)
			cpclouds.add(c);
		return cpclouds;
	}
	public static boolean getMovedTutorial() { return movedTutorial;}
	public static void setMovedTutorial(Boolean bool) { movedTutorial=bool; }
	
	public static int getX() { return xloc; }
	public static int getY() { return yloc; }
	public static int getDeathToll() { return deathToll; }
	public static int getFalconCount() { return falconCount; }
    public static int getBushCount() { return bushCount; }
    public static int getBushMax() { return bushMax; }
    public static int getTwigCount() { return twigCount; }
    public static int getBushTrans() { return bushTrans; }
    

    //setters and getters for rotating images
	public static Direction getDir() {
		return dir;
	}
	public static void setDir(Direction dir) {
		Model.dir = dir;
	}
	
	public static Direction getDirPred() {
		return dirPred;
	}
	public static void setDirPred(Direction dirPred) {
		Model.dirPred = dirPred;
	}
    
}