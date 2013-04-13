import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.lang.Math;

/**
 * This class represents the car game. The car game maintains information
 *   about the highway where the game is happening as well as about the
 *   player, the player's score, and how much time is left in the game.
 *   It also defines a random number generator that is
 *   used throughout the game and keeps track of the current tick number.
 * 
 * @author Dalibor Zeleny (dalibor@cs.wisc.edu)        Junrui Ruan (jruan@wisc.edu) 
 */
public class CarGame {
   // Do not change any of these variables and constants described here.
   private Random r = new Random();
   private boolean[] keysPressed = new boolean[4];
   /**
    * The number of ticks in each second.
    */
   public static long TICKS_PER_SECOND = 40;
   /**
    * If the distance of any car is more than this constant's value from the
    *   player's car, it will get removed from the highway.
    */
   public static double DROP_CARS_DISTANCE = 1000;
   /**
    * Indicates the probability of cars appear.
    */
   private double addCarProbability;
   /**
    * Indicates the probability of coins appear.
    */
   private double addCoinProbability;   
   /**
    * A counter to count for ticks.
    */
   private long tickNumber;
   /**
    * The highway which has several lanes.
    */
   private Highway highway;
   /**
    * The driver that player controls.
    */
   private Driver player;
   /**
    * Computer drivers
    */
   private Driver driver;
   /**
    * Count before the begin of the game.
    */
   private long ticksBeforeStart;
   /**
    * To record player's score
    */
   private long score;
   /**
    * To record the player's time left on this game. 
    */
   private double timeLeft;
   /**
    * Indicates how long will a tick be.
    */
   private double tickLength;
   /**
    * To record which lane the player's car is in.
    */
   private int currentLanes;
   /**
    * An object of coins.
    */
   private Coin coins;
   /**
    * A counter for coins' ID.
    */
   private int counter = 0;
   
   /**
    * To store the initial numLanes
    */
   private int numLanes0;
   /**
    * To store the initial tickLength.
    */
   private double tickLength0;
   /**
    * To store the initial playerName.
    */
   private String playerName0;
   /**
    * To store the initial ticksBeforeStart.
    */
   private int ticksBeforeStart0;
   
   /**
    * Returns a reference to the random number generator used by this game.
    * @return a reference to the random number generator used by this game.
    */
   public Random getRNG() {
      return r;
   }
   
   
   /**
    * Updates which keys were pressed before this tick. The keystrokes will
    *   be acknowledged by the game in its next tick. The array indices
    *   correspond to the constants <tt>KEY_UP</tt>, <TT>KEY_RIGHT</tt>,
    *   <tt>KEY_DOWN</tt> and <tt>KEY_LEFT</tt> in <tt>CarGameController</tt>.
    * You should not call this method from anywhere in your code.
    *
    * @param keystrokes An array indicating which keys were pressed. An array
    *   element is true if and only if its corresponding key was pressed.
    */
   public void updateKeystrokes(boolean[] keystrokes) {
      for (int i = 0; i < this.keysPressed.length; i++) {
         this.keysPressed[i] = keystrokes[i];
      }
   }
   
   
   
   
   /**
    * Creates a new <tt>CarGame</tt> object with the specified properties
    * 
    * @param numLanes Number of lanes in the highway
    * @param tickLength Length of a tick. Recommended: 0.01
    * @param playerName Name of the player
    * @param ticksBeforeStart Number of ticks before the start of
    *    the game. This allows other parts of the program to do some setup 
    *    before the game actually starts and also to display a nice countdown.
    */
   public CarGame(int numLanes, double tickLength, String playerName,
         int ticksBeforeStart) {
      // Here are some suggestions and remarks:
      // - It doesn't matter what you set the human driver's preferred
      //     velocity to. It won't be used by a human driver.
      // - Set the player's car color to blue using Color.BLUE as
      //     one of the arguments when calling the constructor that makes
      //     a new car for the player.
      // - Put the player's car in lane 0.
      // - The tick number should be the negative of ticksBeforeStart
	  //Save the State
	  this.numLanes0 = numLanes;
	  this.tickLength0 = tickLength;
	  this.playerName0 = playerName;
	  this.ticksBeforeStart0 = ticksBeforeStart;
	   
	  //TODO JIM: make a Highway with numLanes
	  this.highway = new Highway(numLanes);
	  //TODO JIM: make a Driver with playerName
	  this.player = new Driver(playerName, 50, true);
	  //TODO JIM: make a car that is Blue and length 5
	  Car c = new Car(java.awt.Color.BLUE, 5);
	  //TODO JIM: have the player enter the new car
	  this.player.enterCar(c);
	  this.player.getDrivenCar().setDriver(this.player);
	  this.player.getDrivenCar().setVelocity(50);              //value doesn't really matter
	  //TODO JIM: set the player's lane number to 0
	  this.currentLanes = 0;
	  //TODO JIM: add the player's driven car to Lane 0 of the Highway
	  this.getHighway().lanes.get(this.currentLanes).addCar(c);
	  //TODO JIM: initialize the tickLength with tickLength
	  this.tickLength = tickLength;
	  //TODO JIM: initialize the tickBeforeStart with ticksBeforeStart
	  this.tickNumber = - this.ticksBeforeStart - 120;
	  
	  //TODO JIM: set time left to 60;
	  this.timeLeft = 60;
	  //TODO JIM: set score to 0;
	  this.score = 0;
   
   }
   
   
   /** 
    * Returns a reference to an object that represents the player.
    * @return a reference to an object that represents the player.
    */
   public Driver getPlayer() {
      return this.player;
   }
   
   
   /**
    * Returns the player's score.
    * @return the player's score.
    */
   public long getScore() {
      return this.score;
   }
   
   
   /**
    * Returns how many seconds are remaining in the game.
    * @return how many seconds are remaining in the game.
    */
   public double getTimeLeft() {
      return this.timeLeft;
   }
   
   
   /**
    * Returns the current tick number.
    * @return the current tick number.
    */
   public long getTickNumber() {
      return this.tickNumber;
   }
   
   
   /**
    * Updates the probability with which another car should be added in each
    *   tick using the parameter as the new value.
    * @param addCarProbability A positive double value that is less than 1. 
    */
   public void setAddCarProbability(double addCarProbability) {
	   if (addCarProbability < 1 && addCarProbability > 0) {
		   this.addCarProbability = addCarProbability;
	   }
   }
   
   
   /**
    * Updates the probability with which another object should be added in each
    *   tick using the parameter as the new value.
    * @param addObjectProbability A positive double value that is less than 1.
    */
   public void setAddCoinProbability(double addObjectProbability) {
        if (addObjectProbability < 1 && addObjectProbability > 0) {
        	this.addCoinProbability = addObjectProbability;
        }
   }

   
   /**
    * Runs one tick of the game. You should not modify this method's code.
    */
   public void tick() {      
      // Add a random cars with some probability.
      // We allow multiple cars and/or objects to be added.
      while (this.getRNG().nextDouble() < addCarProbability) {
         this.addRandomCar();
      }
      
      // A negative tick number means the game is not ready to start yet.
      // This is used mostly so that we have some time to set up
      //   the game world and also to display a countdown before game starts.
      if (this.tickNumber < 0) {
         this.tickNumber++;
         return;
      }
      
      // We add random objects only after the game starts.
      while (this.getRNG().nextDouble() < addCoinProbability) {
         this.addRandomCoin();
      }
      
      // Drivers should now all be able to act
      this.highway.rewind();
      for (int i = 0; i < this.highway.getNumLanes(); i++) {
         while (this.highway.hasNextCar(i)) {
            this.highway.getNextCar(i).getDriver().setCanAct(true);
         }
      }
      
      // Unlike computers that move cars around by using the makeDecision() method,
      //   human drivers change depending on keyboard input. Now is the time
      //   to process those key presses. 
      this.processKeysPressed();
      
      // We must check for crashes now because the player has switched lanes.
      // If the player did crash, that's it for this tick and the game is over.
      this.checkForCrashes();
      if (this.player.getDrivenCar().isCrashed()) {
         return;
      }
      
      // Looks like the driver has moved and hasn't run into anything,
      //   so let's see if he caught something.
      this.pickUpCoins();
      
      // Let all the other drivers adjust their cars' speeds and change lanes. 
      this.notifyDrivers();
      
      // Move everybody now
      this.moveEverything();
      
      // Check for crashes again in case our drivers did something silly.
      this.checkForCrashes();
      if (this.player.getDrivenCar().isCrashed()) {
         return;
      }
      
      // Maybe the driver caught some additional objects after everything has
      //   moved
      this.pickUpCoins();
      
      // Remove all expired coins.
      this.removeExpiredCoins();
      
      // Dump cars that are too far (on either side of the player's car).
      this.removeDistantCars();
      
      // Some bookkeeping at the end.
      this.tickNumber++;
      this.timeLeft -= 1.0 / CarGame.TICKS_PER_SECOND;
   }
   
   
   /**
    * Returns a reference to the highway where the game is happening.
    * @return a reference to the highway where the game is happening.
    */
   public Highway getHighway() {
      return this.highway;
   }
      
   
   /**
    * Indicates whether the game is over. The game is over when the player
    *   crashed his car or when time runs out.
    * @return whether the game is over.
    */
   public boolean isGameOver() {
	   //step 1
	   //return false;
	   //step 3: end if no time left or players driven car is crashed
      if (this.getTimeLeft() <= 0 || this.player.getDrivenCar().isCrashed()) {
    	  return true;
      }
      else {
    	  return false;
      }
   }

   
   /**
    * Restarts the game. This should restore the highway to the state it was
    *   in right after the constructor finished.
    */
   public void reset() {
      this.highway = new Highway(this.numLanes0);
	  this.player = new Driver(this.playerName0, 50, true);
	  Car c = new Car(java.awt.Color.BLUE, 5);
	  this.player.enterCar(c);
	  this.player.getDrivenCar().setDriver(this.player);
	  this.player.getDrivenCar().setVelocity(50);
	  this.currentLanes = 0;
	  this.getHighway().lanes.get(this.currentLanes).addCar(c);
	  this.tickLength = this.tickLength0;
	  this.tickNumber = - this.ticksBeforeStart0;
	  this.timeLeft = 60;
	  this.score = 0;
   }
   
   
   /**
    * Returns a string description of the game's state. Here is an example of
    *   what the output could look like:
    * <xmp>Tick 962, score: 2172, time left: 53
Lane 0: 5 car(s), 1 coin(s)
CAR 0: ID: 41, distance: 1098.5299999999847, velocity: 33.0, length: 4.0; driven by Bob who prefers 33.0
CAR 1: ID: 35, distance: 1042.9299999999862, velocity: 43.0, length: 3.75; driven by Bob who prefers 43.0
CAR 2: ID: 31, distance: 1008.4099999999748, velocity: 56.0, length: 3.75; driven by Bob who prefers 56.0
CAR 3: ID: 4, distance: 845.9199999999934, velocity: 66.0, length: 3.5; driven by Bob who prefers 66.0
CAR 4: ID: 2, distance: 563.2399999999914, velocity: 52.0, length: 5.75; driven by Bob who prefers 52.0
COIN 0: ID: 3, at distance: 955.850000000004 with score bonus 796(-12 decrease), time bonus 6.300000000000006(-0.1 decrease) and decrease period 5. It has lived 88 out of 400 ticks

Lane 1: 9 car(s), 0 coin(s)
CAR 0: ID: 40, distance: 1277.4299999999923, velocity: 78.0, length: 4.5; driven by Bob who prefers 78.0
CAR 1: ID: 42, distance: 1184.5500000000104, velocity: 70.0, length: 4.0; driven by Bob who prefers 70.0
CAR 2: ID: 36, distance: 1126.8599999999908, velocity: 53.0, length: 4.5; driven by Bob who prefers 53.0
CAR 3: ID: 34, distance: 1119.609999999992, velocity: 52.0, length: 3.25; driven by Bob who prefers 52.0
CAR 4: ID: 39, distance: 993.3899999999787, velocity: 24.0, length: 4.5; driven by Bob who prefers 24.0
CAR 5: ID: 1, distance: 926.25, velocity: 100.0, length: 5.0; driven by Dalibor who prefers 10.0
CAR 6: ID: 15, distance: 906.5300000000148, velocity: 48.0, length: 4.75; driven by Bob who prefers 48.0
CAR 7: ID: 11, distance: 787.7300000000116, velocity: 36.0, length: 4.75; driven by Bob who prefers 36.0
CAR 8: ID: 3, distance: 768.1599999999852, velocity: 68.0, length: 3.0; driven by Bob who prefers 68.0

Lane 2: 9 car(s), 0 coin(s)
CAR 0: ID: 49, distance: 1351.3499999999992, velocity: 30.0, length: 4.25; driven by Bob who prefers 30.0
CAR 1: ID: 46, distance: 1246.3000000000086, velocity: 43.0, length: 4.5; driven by Bob who prefers 43.0
CAR 2: ID: 32, distance: 1102.5899999999992, velocity: 66.0, length: 5.25; driven by Bob who prefers 66.0
CAR 3: ID: 12, distance: 1082.0099999999923, velocity: 76.0, length: 3.75; driven by Bob who prefers 76.0
CAR 4: ID: 24, distance: 1017.5600000000327, velocity: 69.0, length: 5.5; driven by Bob who prefers 69.0
CAR 5: ID: 8, distance: 999.5899999999751, velocity: 78.0, length: 5.0; driven by Bob who prefers 78.0
CAR 6: ID: 7, distance: 720.7999999999781, velocity: 40.0, length: 5.0; driven by Bob who prefers 40.0
CAR 7: ID: 13, distance: 683.0499999999809, velocity: 40.0, length: 5.5; driven by Bob who prefers 40.0
CAR 8: ID: 5, distance: 618.3800000000087, velocity: 49.0, length: 4.25; driven by Bob who prefers 49.0

Lane 3: 10 car(s), 0 coin(s)
CAR 0: ID: 47, distance: 1275.75, velocity: 75.0, length: 3.25; driven by Bob who prefers 75.0
CAR 1: ID: 28, distance: 1205.6399999999908, velocity: 77.0, length: 3.5; driven by Bob who prefers 77.0
CAR 2: ID: 26, distance: 1139.090000000015, velocity: 72.0, length: 5.0; driven by Bob who prefers 72.0
CAR 3: ID: 9, distance: 1050.9299999999841, velocity: 65.0, length: 3.5; driven by Bob who prefers 65.0
CAR 4: ID: 25, distance: 1037.4000000000206, velocity: 71.0, length: 4.75; driven by Bob who prefers 71.0
CAR 5: ID: 23, distance: 977.1699999999805, velocity: 66.0, length: 4.0; driven by Bob who prefers 66.0
CAR 6: ID: 21, distance: 954.5500000000054, velocity: 59.0, length: 4.75; driven by Bob who prefers 59.0
CAR 7: ID: 14, distance: 930.2699999999837, velocity: 78.0, length: 3.75; driven by Bob who prefers 78.0
CAR 8: ID: 20, distance: 890.2200000000081, velocity: 47.0, length: 4.25; driven by Bob who prefers 47.0
CAR 9: ID: 17, distance: 800.0099999999715, velocity: 56.0, length: 5.0; driven by Bob who prefers 56.0

Lane 4: 17 car(s), 0 coin(s)
CAR 0: ID: 50, distance: 1344.25, velocity: 25.0, length: 4.0; driven by Bob who prefers 25.0
CAR 1: ID: 48, distance: 1270.1099999999974, velocity: 36.0, length: 4.5; driven by Bob who prefers 36.0
CAR 2: ID: 44, distance: 1221.8199999999824, velocity: 37.0, length: 5.75; driven by Bob who prefers 37.0
CAR 3: ID: 45, distance: 1188.4500000000062, velocity: 20.0, length: 4.0; driven by Bob who prefers 20.0
CAR 4: ID: 43, distance: 1178.0500000000147, velocity: 41.0, length: 3.75; driven by Bob who prefers 41.0
CAR 5: ID: 37, distance: 1090.7500000000048, velocity: 42.0, length: 3.25; driven by Bob who prefers 42.0
CAR 6: ID: 38, distance: 1000.0899999999892, velocity: 29.0, length: 4.5; driven by Bob who prefers 29.0
CAR 7: ID: 30, distance: 980.469999999988, velocity: 22.0, length: 3.5; driven by Bob who prefers 22.0
CAR 8: ID: 22, distance: 974.7300000000337, velocity: 44.0, length: 4.5; driven by Bob who prefers 44.0
CAR 9: ID: 27, distance: 941.9799999999896, velocity: 27.0, length: 5.5; driven by Bob who prefers 27.0
CAR 10: ID: 18, distance: 929.839999999977, velocity: 67.0, length: 3.0; driven by Bob who prefers 67.0
CAR 11: ID: 33, distance: 894.4700000000081, velocity: 22.0, length: 4.5; driven by Bob who prefers 22.0
CAR 12: ID: 29, distance: 847.9400000000202, velocity: 33.0, length: 4.0; driven by Bob who prefers 33.0
CAR 13: ID: 19, distance: 824.6199999999726, velocity: 31.0, length: 4.0; driven by Bob who prefers 31.0
CAR 14: ID: 16, distance: 682.4900000000073, velocity: 24.0, length: 3.75; driven by Bob who prefers 24.0
CAR 15: ID: 10, distance: 611.5900000000147, velocity: 22.0, length: 3.0; driven by Bob who prefers 22.0
CAR 16: ID: 6, distance: 562.6199999999928, velocity: 51.0, length: 5.75; driven by Bob who prefers 51.0
    * </xmp>
    */
   public String toString() {
      //TODO
	  return "tick: " + this.tickNumber + ", score: " + this.score + ", time left: " + this.timeLeft;
   }

   
   /**
    * For each car on the highway, tell its driver about nearby cars by calling
    *   the <tt>makeDecision()</tt> method. Once the <tt>makeDecision()</tt>
    *   method returns, put the car in the correct lane if the driver chose to 
    *   switch lanes. The <tt>makeDecision()</tt> method should not get 
    *   called twice for any driver, which you can guarantee by properly using
    *   the <tt>canAct()</tt> and <tt>setCanAct()</tt> methods.
    */
   private void notifyDrivers() {
	   
	   double velocityOfCarAhead = -1;
	   double distanceFromCarAhead = -1;
	   double velocityOfCarBehind = -1;
	   double distanceFromCarBehind = -1;
	   double velocityOfCarAheadLeft = -1;
	   double distanceFromCarAheadLeft = -1;
	   double velocityOfCarBehindLeft = -1;
	   double distanceFromCarBehindLeft = -1;
	   double velocityOfCarAheadRight = -1;
	   double distanceFromCarAheadRight = -1;
	   double velocityOfCarBehindRight = -1;
	   double distanceFromCarBehindRight = -1;
	   
	   int i = 0;
	   
	   for (int j = 0; j < this.getHighway().lanes.size(); j++) {
		   this.getHighway().rewind();
		   
		   while (this.getHighway().lanes.get(j).hasNextCar()) {
			   Car c0 = this.highway.lanes.get(j).getNextCar();
			   if (this.getHighway().lanes.get(j).hasNextCar() && !this.getHighway().lanes.get(j).lookAtNextCar().getDriver().isHuman() ) {
				   
				   Car c1 = this.highway.lanes.get(j).getNextCar();
				   if (c1.getDriver().canAct()) {
					   if (j == 4 && this.getHighway().lanes.get(j - 1).hasNextCar()) {
						   while (this.getHighway().lanes.get(j - 1).hasNextCar()) {
							   Car cLeft = this.getHighway().lanes.get(j - 1).getNextCar();
							   if (cLeft.getPositionOfFront() < c1.getPositionOfFront()) {
								   distanceFromCarBehindLeft = c1.getPositionOfFront() - cLeft.getPositionOfFront();
								   velocityOfCarBehindLeft = cLeft.getVelocity();
							   }
							   else if (cLeft.getPositionOfFront() > c1.getPositionOfFront()) {
								   distanceFromCarAheadLeft = cLeft.getPositionOfFront() - c1.getPositionOfFront();
								   velocityOfCarAheadLeft = cLeft.getVelocity();
								   break;
							   }
						   }						   
					   }
					   else if (j == 0 && this.getHighway().lanes.get(j + 1).hasNextCar()) {
						   while (this.getHighway().lanes.get(j + 1).hasNextCar()) {
							   Car cRight = this.getHighway().lanes.get(j + 1).getNextCar();
							   if (cRight.getPositionOfFront() < c1.getPositionOfFront()) {
								   distanceFromCarBehindRight = c1.getPositionOfFront() - cRight.getPositionOfFront();
								   velocityOfCarBehindRight = cRight.getVelocity();
							   }
							   else if (cRight.getPositionOfFront() > c1.getPositionOfFront()) {
								   distanceFromCarAheadRight = cRight.getPositionOfFront() - c1.getPositionOfFront();
								   velocityOfCarAheadRight = cRight.getVelocity();
								   break;
							   }
						   }
					   }
					   
					   else if (j > 0 && j < this.getHighway().lanes.size() - 1 && 
							   this.getHighway().lanes.get(j - 1).hasNextCar() &&
							   this.getHighway().lanes.get(j + 1).hasNextCar()) {
							   while (this.getHighway().lanes.get(j - 1).hasNextCar()) {
								   Car cLeft = this.getHighway().lanes.get(j - 1).getNextCar();
								   if (cLeft.getPositionOfFront() < c1.getPositionOfFront()) {
									   distanceFromCarBehindLeft = c1.getPositionOfFront() - cLeft.getPositionOfFront();
									   velocityOfCarBehindLeft = cLeft.getVelocity();
								   }
								   else if (cLeft.getPositionOfFront() > c1.getPositionOfFront()) {
									   distanceFromCarAheadLeft = cLeft.getPositionOfFront() - c1.getPositionOfFront();
									   velocityOfCarAheadLeft = cLeft.getVelocity();
									   break;
								   }
							   }
							   while (this.getHighway().lanes.get(j + 1).hasNextCar()) {
								   Car cRight = this.getHighway().lanes.get(j + 1).getNextCar();
								   if (cRight.getPositionOfFront() < c1.getPositionOfFront()) {
									   distanceFromCarBehindRight = c1.getPositionOfFront() - cRight.getPositionOfFront();
									   velocityOfCarBehindRight = cRight.getVelocity();
								   }
								   else if (cRight.getPositionOfFront() > c1.getPositionOfFront()) {
									   distanceFromCarAheadRight = cRight.getPositionOfFront() - c1.getPositionOfFront();
									   velocityOfCarAheadRight = cRight.getVelocity();
									   break;
								   }
							   }
					   }
					   if (this.getHighway().lanes.get(j).hasNextCar()) {
						   Car c2 = this.highway.lanes.get(j).getNextCar();
						   velocityOfCarBehind = c0.getVelocity();
						   velocityOfCarAhead = c2.getVelocity();
						   distanceFromCarBehind = Math.abs(c1.getPositionOfFront() - c0.getPositionOfFront());
						   distanceFromCarAhead = Math.abs(c1.getPositionOfFront() - c2.getPositionOfFront());
						   i = c1.getDriver().makeDecision(velocityOfCarAhead, distanceFromCarAhead, velocityOfCarBehind, distanceFromCarBehind, velocityOfCarAheadLeft,
				    				distanceFromCarAheadLeft, velocityOfCarBehindLeft, distanceFromCarBehindLeft, velocityOfCarAheadRight, distanceFromCarAheadRight, 
				    				velocityOfCarBehindRight, distanceFromCarBehindRight, tickLength);
						   if (i == 1) {
							   this.highway.lanes.get(currentLanes).removeCar(c1);
							   this.highway.lanes.get(currentLanes + 1).addCar(c1);
						   }
						   else if (i == -1) {
							   this.highway.lanes.get(currentLanes).removeCar(c1);
							   this.highway.lanes.get(currentLanes -1).addCar(c1);
						   }
					   }
					   c1.getDriver().setCanAct(false);
					   c0 = c1;
				   }
			   }
		   }		   		   		   
	   	}
   	}
      
   /**
    * For each coin on the highway, check if it overlaps
    * with the player's car. If it does, update the player's score and
    * time left and remove that coin from the highway. 
    */
   private void pickUpCoins() {	   
	   while (this.getHighway().lanes.get(this.currentLanes).hasNextCoin()) {
		   Coin coin1 = this.getHighway().lanes.get(this.currentLanes).getNextCoin();
		   if (Math.abs(this.player.getDrivenCar().getPositionOfFront() - coin1.getPositionOfFront()) < coins.LENGTH) {
			   this.score += coin1.getScoreBonus();
			   this.timeLeft += coin1.getTimeBonus();
			   this.highway.lanes.get(this.currentLanes).removeCoin(coin1);
			   break;
			   }
		   }
	   this.highway.rewind();
   }
	   

   
   /**
    * This method processes key presses made before this tick. They key
    * presses are located in the instance variable <tt>keysPressed</tt> which we
    * provided in the code skeleton. Make use of that array to change the
    * velocity of the player's car and the lane that car is in. Refer to
    * constants defined in <tt>CarGameController</tt> to find out what
    * the individual array elements mean. For example,
    * <tt>this.keysPressed[CarGameController.KEY_UP]</tt> tells us whether
    * the up key was pressed.
    * <br /> 
    * <tt>The following should happen:
    * <ul>
    *   <li>If the up key is pressed, the player's car should move one
    *     lane to the left, even if this causes an accident. Nothing should
    *     happen if the player's car is in the leftmost lane already.</li>
    *   <li>If the right key is pressed, the player's car velocity should
    *     increase by 1.</li>
    *   <li>If the down key is pressed, the player's car should move one
    *     lane to the right, even if this causes an accident. Nothing should
    *     happen if the player's car is in the rightmost lane already.</li>
    *   <li>If the left key is pressed, the player's car velocity should
    *     decrease by 1.</li>
    * </ul>
    * Once this method returns, the <tt>keysPressed</tt> array should indicate
    * that no keys were pressed.
    */
   private void processKeysPressed() {
	   Car drivenCar = this.player.getDrivenCar();
	   if (this.keysPressed[1]) {
		   double velocity = this.player.getDrivenCar().getVelocity() + 1;
		   this.player.getDrivenCar().setVelocity(velocity);
	   }
	   if (this.keysPressed[3]) {
		   double velocity = this.player.getDrivenCar().getVelocity() - 1;
		   this.player.getDrivenCar().setVelocity(velocity);
	   }
	   if (this.keysPressed[0]) {
		   if (this.currentLanes > 0) {
			   this.getHighway().moveCar(this.player.getDrivenCar(), this.currentLanes, this.currentLanes - 1);
			   this.currentLanes--;
		   }
	   }
	   if (this.keysPressed[2]) {
		   if (this.currentLanes < this.getHighway().lanes.size() - 1) {
			   this.getHighway().moveCar(this.player.getDrivenCar(), this.currentLanes, this.currentLanes + 1);
			   this.currentLanes++;
		   }
	   }
	   for (int i = 0; i < this.keysPressed.length; i++) {
		   this.keysPressed[i] = false;
	   }
   }
   
   
   /**
    * Every car and every coin should now move (tick) for
    * the amount of time specified by the game's tick length.
    */
   private void moveEverything() {
	   this.highway.rewind();
	   for (int i = 0; i < this.highway.lanes.size(); i++) {
		    while (this.highway.lanes.get(i).hasNextCar()) {
		    	this.highway.lanes.get(i).getNextCar().tick(tickLength);
		    }
	   }
	   this.highway.rewind();
	   for (int i = 0; i < this.highway.lanes.size(); i++) {
		    while (this.highway.lanes.get(i).hasNextCoin()) {
		    	this.highway.lanes.get(i).getNextCoin().tick(tickLength);
		    }
	   }
   }
   
   
   /**
    * Remove all expired coins from the highway so that
    * the player cannot pick the up and get points/time for them. 
    */
   private void removeExpiredCoins() {
	   this.getHighway().rewind();
	   for (int i = 0; i < this.getHighway().lanes.size(); i++) {
		    if (this.getHighway().lanes.get(i).hasNextCoin()) {
		    	Coin coin1 = this.getHighway().lanes.get(i).getNextCoin();
		    	while (this.getHighway().lanes.get(i).hasNextCoin()) {
		    		Coin coin2 = this.getHighway().lanes.get(i).getNextCoin();
		    		if (coin1.getTimeAlive() >= 1000 / this.TICKS_PER_SECOND) {
		    			coin1.isExpired();
		    		}
		    		if (coin1.isExpired()) {
		    			this.getHighway().lanes.get(i).removeCoin(coin1);
		    		}
		    		coin1 = coin2;
		    	}
		    }
		    this.getHighway().rewind();
	   }
   }
   
   
   /**
    * To speed up the game, all cars whose distance from the player's car is
    *   more than <tt>DROP_CARS_DISTANCE</tt> should be removed from the highway.
    */
   private void removeDistantCars() {
	   for (int i = 0; i < this.getHighway().lanes.size(); i++) {
		    if (this.getHighway().lanes.get(i).hasNextCar()) {
		    	Car car1 = this.getHighway().lanes.get(i).getNextCar();
		    	while (this.getHighway().lanes.get(i).hasNextCar()) {
		    		Car car2 = this.getHighway().lanes.get(i).getNextCar();
		    		if (Math.abs(this.player.getDrivenCar().getPositionOfFront() - car1.getPositionOfFront()) > this.DROP_CARS_DISTANCE) {
		    			this.getHighway().lanes.get(i).removeCar(car1);
		    		}
		    		car1 = car2;
		    	}
		    }
		    this.getHighway().rewind();
	   }
   }
   
   
   /**
    * Add one random car to the highway. Place it in a random lane at a random
    *   distance away from the player's car and give it a new driver
    *   who should have a random preferred velocity.
    *   Placing this car on the highway should not cause an
    *   accident, so make sure to check your random location and change it if
    *   necessary.
    */
   private void addRandomCar() {
	  Color color = new Color(this.r.nextInt(256), this.r.nextInt(256), 0);
	  Car car = new Car(color, (this.r.nextDouble() * 4) + 3);
	  if (this.highway.lanes.get(currentLanes).canFitCar(car)) {
		  car.setDistance(10000 * this.r.nextDouble());
		  this.driver = new Driver("ComputerDriver", this.r.nextDouble() * 100, false);
		  this.driver.enterCar(car);
		  this.driver.getDrivenCar().setDriver(this.driver);
		  this.getHighway().lanes.get(r.nextInt(this.getHighway().lanes.size())).addCar(this.driver.getDrivenCar());
		  this.driver.getDrivenCar().setVelocity(r.nextDouble() * 100);
		  this.driver.setCanAct(true);
	  }
   }
   
   
   /**
    * Add one random coin to the highway. Place it in a random
    *   lane and pick some interesting properties for it. Again, we leave
    *   the details up to your creativity. 
    */
   private void addRandomCoin() {
	   int tmpLane = r.nextInt(this.getHighway().lanes.size());
	   Coin c = new Coin(30, 0.1, 1000, 10, 1000 , 
				   this.r.nextDouble() * 5000, this.r.nextDouble() * 20, 10);
		   this.highway.lanes.get(tmpLane).addCoin(c);
		   c.ID = counter;
		   counter++;
   }
   
   
   /**
    * 
    * 
    * If there are any cars that crashed into each other (that is, they overlap),
    * this method should remove them from the highway.
    */
   private void checkForCrashes() {
	   this.getHighway().rewind();
	   for (int i = 0; i < this.getHighway().lanes.size(); i++) {
		    if (this.getHighway().lanes.get(i).hasNextCar()) {
		    	Car car1 = this.getHighway().lanes.get(i).getNextCar();
		    	while (this.getHighway().lanes.get(i).hasNextCar()) {
		    		Car car2 = this.getHighway().lanes.get(i).getNextCar();
		    		if (car1.getPositionOfFront() + car2.LENGTH < car2.getPositionOfFront() 
		    				|| car1.getPositionOfFront() - car1.LENGTH < car2.getPositionOfFront()) {
		    			car1.setCrashed(true);
		    			car2.setCrashed(true);
		    		}
		    		if (car1.isCrashed()) {
		    			this.getHighway().lanes.get(i).removeCar(car1);
		    		}
		    		if (car2.isCrashed()) {
		    			this.getHighway().lanes.get(i).removeCar(car2);
		    		}
		    		car1 = car2;
		    	}
		    }
		    this.getHighway().rewind();
	   }
   }   
}
