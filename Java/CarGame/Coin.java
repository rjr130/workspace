import java.awt.Color;
import java.awt.Graphics;

/**
 * This class represents a coin the player can pick up in the car game.
 * It increases the player's score and time. The lifetime of the coin is
 * measured in ticks. The time and score bonus decrease by specific amounts
 * periodically after a specified number of ticks.
 * 
 * @author Dalibor Zeleny (dalibor@cs.wisc.edu)
 */
public class Coin {
   
   /**
    * A unique ID of this coin.
    */
   public int ID;
   
   /**
    * The length of every coin will be the same.
    */
   public static double LENGTH = 5.0;
	

	/**
	 * The color a coin should have when displayed if its score bonus
	 *   is above <tt>MAX_SCORE</tt> 
	 */
	public static final java.awt.Color MAX_SCORE_COLOR = java.awt.Color.YELLOW;
	/**
	 * The color a coin should have when displayed if its score bonus is
	 *   below <tt>MIN_SCORE</tt>
	 */
	public static final java.awt.Color MIN_SCORE_COLOR = java.awt.Color.GREEN;
	/**
	 * A minimum score threshold for the purpose of determining display color
	 *   for a coin.
	 */
	public static final int MIN_SCORE = 100;
   /**
    * A maximum score threshold for the purpose of determining display color
    *   for a coin.
    */
	public static final int MAX_SCORE = 1000;
	/**
	 * Transparency (alpha value) of this coin when displayed right after
	 *   its creation
	 */
	public static final int MAX_ALPHA = 255;
	/**
	 * Transparency (alpha value) of this coin when displayed right before
	 *   its expiration.
	 */
	public static final int MIN_ALPHA = 32;
	/**
	 * When you met the coin you will get the time bonus
	 */
	private double timeBonus;
	/**
	 * The time bonus is decreased periodically by this
     *   amount after <tt>decreasePeriod</tt> many ticks.
	 */
	private double timeDecrease;
	/**
	 * The initial score bonus this coin grants to the player
	 */
	private int scoreBonus;
	/**
	 * scoreDecrease The score bonus is decreased periodically by this
     *   amount after <tt>decreasePeriod</tt> many ticks.
	 */
	private int scoreDecrease;
	/**
	 * The number of ticks for which this coin will exist.
	 */
	private long lifetimeInTicks;
	/**
	 * The starting position of the front of this coin on the highway.
	 */
	private double positionOfFront;
	/**
	 * The velocity at which this coin is moving.
	 */
	private double velocity;
	/**
	 * Number of ticks after which bonuses periodically decrease.
	 */
	private double decreasePeriod;
	
	/**
	 * The number of ticks for which this coin has been alive.
	 */
	private long ticksAlive;
	
	   
	/**
    * Creates a new coin with specified properties.
    * @param timeBonus The initial time bonus this coin grants to the player
    * @param timeDecrease The time bonus is decreased periodically by this
    *   amount after <tt>decreasePeriod</tt> many ticks.
    * @param scoreBonus The initial score bonus this coin grants to the player
    * @param scoreDecrease The score bonus is decreased periodically by this
    *   amount after <tt>decreasePeriod</tt> many ticks.
    * @param lifetimeInTicks The number of ticks for which this coin will
    *   exist.
    * @param positionOfFront The starting position of the front of 
    *   this coin on the highway.
    * @param velocity The velocity at which this coin is moving.
    * @param decreasePeriod Number of ticks after which bonuses periodically decrease.
    */
	public Coin(double timeBonus, double timeDecrease,
			int scoreBonus, int scoreDecrease, long lifetimeInTicks,
			double positionOfFront, double velocity, long decreasePeriod) {
		this.timeBonus = timeBonus;
		this.timeDecrease = timeDecrease;
		this.scoreBonus = scoreBonus;
		this.scoreDecrease = scoreDecrease;
		this.lifetimeInTicks = lifetimeInTicks;
		this.positionOfFront = positionOfFront;
		this.velocity = velocity;
		this.decreasePeriod = decreasePeriod;
	}
	
	/**
	 * Returns the time bonus this coin grants if picked up at the time
	 *   this method is called.
	 * @return the time bonus this coin grants if picked up at the time
    *   this method is called.
	 */
	public double getTimeBonus() {
		return timeBonus;
	}
	
	/**
    * Returns the score bonus this coin grants if picked up at the time
    *   this method is called.
    * @return the score bonus this coin grants if picked up at the time
    *   this method is called.
	 */
	public double getScoreBonus() {
		return scoreBonus;
	}
	
	/**
	 * Returns how many ticks this coin has been alive.
	 * @return how many ticks this coin has been alive.
	 */
	public long getTimeAlive() {
		return ticksAlive;
	}
	
	/**
    * Returns how far the coin is along the highway. The returned value tells us the
    *   position of the FRONT of the coin. The position of the rear of the coin
    *   is given by the return value of this method minus the value of the
    *   LENGTH constant.
    * @return how far along the highway the coin is.
	 */
	public double getPositionOfFront() {
		return positionOfFront;
	}
	
	/**
    * Returns how fast the coin is currently moving.
    * @return the speed at which the coin is moving
    */
   public double getVelocity() {
		return velocity;
	}
	
   /**
    * Two coins are equal to each other if and only if their
    * IDs are the same.
    */
   public boolean equals(Object o) {       
	   if (o != null && o instanceof Coin) {
	         Coin c = (Coin)o;
	         return c.ID == this.ID;
	   } 
	   else {
	         return false;
	   }
   }
   
   /**
    * Updates the state of the coin. This should increase the number of
    *   ticks this coin has been alive and update its distance based
    *   on its velocity and the tick length. Time and score bonuses
    *   also get decreased if the right number of ticks since the last time they 
    *   were decreased has passed.
    * @param time The tick length of the car game
    */
	public void tick(double time) {
		if(ticksAlive <= lifetimeInTicks){
			ticksAlive++;
			positionOfFront += velocity * time;
			if(timeBonus >= 0 && scoreBonus >= 0){
				if(ticksAlive % decreasePeriod == 0 && ticksAlive != 0){
					timeBonus -= timeDecrease;
					scoreBonus -= scoreDecrease;
				}
			}
		}
	}
	
	/**
	 * Indicates whether this coin's lifetime is over.
	 * @return whether this coin's lifetime is over.
	 */
	public boolean isExpired() {
	   if(ticksAlive >= lifetimeInTicks){
		   return true;
	   }
	   else{
		   return false;
	   }
	}
	
	
	/**
    * Returns a one-line string with some meaningful easy-to-read description
    * of this coin's properties. Here is an example.
    * <xmp>ID: 3, at distance: 955.850000000004 with score bonus 796(-12 decrease), time bonus 6.300000000000006(-0.1 decrease) and decrease period 5. It has lived 88 out of 400 ticks</xmp>
    */
   public String toString() {
      return "ID: " + this.ID + ", at distance: " + this.positionOfFront + "with score bonus " + this.scoreBonus + 
    		  "(-" + this.scoreDecrease + "), time bonus " + this.timeBonus + "(-" +this.timeDecrease + ") and decrease period "
    		  + this.decreasePeriod + ". It has lived " + this.ticksAlive + "out of " + this.lifetimeInTicks + "ticks";
   }
	
   
   
   /**
    * This method paints the coin using the provided <tt>Graphics</tt> coin <tt>g</tt>.
    * The color of the coin is governed by the score bonus and the
    * transparency by how long it's been alive. The color is
    * <tt>MAX_SCORE_COLOR</tt> if the score bonus is above <tt>MAX_SCORE</tt>,
    * <tt>MIN_SCORE_COLOR</tt> if the score bonus is below <tt>MIN_SCORE</tt>,
    * and it's a linear interpolation between those two colors when the score
    * bonus is between <tt>MAX_SCORE</tt> and <tt>MIN_SCORE</tt>. The
    * transparency (alpha value of the <tt>Color</tt> coin) is a linear
    * interpolation between <tt>MAX_ALPHA</tt> and <tt>MIN_ALPHA</tt> depending
    * on the ratio of the amount of ticks alive and the total lifetime (in ticks).
    * 
    * @param g The graphics coin where this coin should be painted.
    * @param x The x coordinate where the REAR of the coin should be painted. 
    * @param y The y coordinate where the TOP of the coin should be painted.
    */
	public void draw(Graphics g, int x, int y) {
	   // Draw a circle indicating how much time is left and what the score
	   //   bonus is. Color depends on the point value, transparency on time 
	   //   left.
	   Color c = this.getDisplayColor();
	   g.setColor(c);
	   g.fillOval(x, y, 20, 20);
	   g.setColor(c.darker());
	   g.drawOval(x, y, 20, 20);
	}
	
	private Color getDisplayColor() {
	   int red;
	   int blue;
	   int green;
	   int alpha;
	   
	   // Color is a convex combination of min and max color depending on
	   //   the score
	   if (this.scoreBonus >= MAX_SCORE) {
         red = MAX_SCORE_COLOR.getRed();
         green = MAX_SCORE_COLOR.getGreen();
         blue = MAX_SCORE_COLOR.getBlue();
	   } 
	   else if (this.scoreBonus <= MIN_SCORE) {
         red = MIN_SCORE_COLOR.getRed();
         green = MIN_SCORE_COLOR.getGreen();
         blue = MIN_SCORE_COLOR.getBlue();
       } 
	   else { 
         // NOTE: MUST TYPECAST
         double scoreFraction = ((double)this.scoreBonus - MIN_SCORE) / (MAX_SCORE - MIN_SCORE);
         red = (int)(MIN_SCORE_COLOR.getRed() * (1 - scoreFraction) + 
               MAX_SCORE_COLOR.getRed() * scoreFraction);
         green = (int)(MIN_SCORE_COLOR.getGreen() * (1 - scoreFraction) + 
               MAX_SCORE_COLOR.getGreen() * scoreFraction);
         blue = (int)(MIN_SCORE_COLOR.getBlue() * (1 - scoreFraction) + 
               MAX_SCORE_COLOR.getBlue() * scoreFraction);
       }
	   
	   // Transparency depends on the fraction of time left in this guy's lifetime.
	   double timeFraction = (double)this.ticksAlive / this.lifetimeInTicks;
	   alpha = (int)(timeFraction * MIN_ALPHA + (1 - timeFraction) * MAX_ALPHA);
	   //System.out.println(alpha + " Test");
	   
	   // And now we have all the data we need to make the color and return it.
	   return new Color(red, green, blue, alpha);
	}
}
