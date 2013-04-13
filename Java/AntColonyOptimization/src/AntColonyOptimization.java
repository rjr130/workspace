///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            AntColonyOptimization
// Files:            AntColonyOptimization.java, AntWorld.java
// Semester:         Fall 2011
//
// Author:           Dayna Hashemi (dhhashemi@wisc.edu)
// CS Login:         dayna
// Lecturer's Name:  James Skrentny
// Lab Section:      327
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     Junrui Ruan (jruan@wisc.edu)
// CS Login:         junrui
// Lecturer's Name:  James Skrentny
// Lab Section:      328
//
//                   
// Credits:          None
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;

/**
 * Finds the shortest route in a given map by simulating how an ant colony
 * finds the shortest route from the queens nest to a specific food location.
 * This is done by while searching for the food chamber, ants periodically lay
 * pheromones on the ground behind them for other ants to smell and follow. 
 * This creates a scent trail that helps the ant colony locate the food in the
 * shortest route possible over time.
 *
 * <p>Bugs: None known
 *
 * @author Dayna Hashemi, Junrui Ruan
 */
public class AntColonyOptimization {

	/**
	 * Finds the shortest route it can (though it might not be the absolute
	 * shortest route) from the queen ant's nest to the chamber with food based
	 * on the map of the colony in the AntWorld class, and displays this route
	 * and its length.
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		int[] shortestRoute = findShortestRoute(AntWorld.antWorld.MAP,
				AntWorld.antWorld.NEST_LOCATION,
				AntWorld.antWorld.FOOD_LOCATION, AntWorld.antWorld.N_ANTS,
				AntWorld.antWorld.N_ITERATIONS,
				AntWorld.antWorld.PHEROMONE_EVAPORATION_COEFFICIENT,
				AntWorld.antWorld.PHEROMONE_STRENGTH_COEFFICIENT,
				AntWorld.antWorld.SEED);
		System.out.print("Shortest route found: ");
		printRoute(shortestRoute);
		System.out.println("Length: " + calcLengthOfRoute(shortestRoute,
				AntWorld.antWorld.MAP));
	}
	
	/**
	 * Repeatedly finds a new set of routes from the queen's nest to the food
	 * chamber for the number of iterations given, updates the attractiveness
	 * of the tunnels based on those routes, and keeps track of the shortest
	 * route found so far (but it might not be the absolute shortest route).
	 * 
	 * @param map
	 *            distances between all pairs of chambers in which is possible
	 *            to move from one chamber to the other, if it is not possible
	 *            to move from one chamber to another then the distance for
	 *            that pair is zero
	 * @param nestLocation
	 *            the chamber of the queen's nest
	 * @param foodLocation
	 *            the chamber of the food
	 * @param nAnts
	 *            the number of ants that will find a route from the nest to
	 *            the food in each iteration of the algorithm
	 * @param nIterations
	 *            the number of times to repeat the algorithm
	 * @param pheromoneEvaporationCoefficient
	 *            the proportion of each tunnel attractiveness that is taken
	 *            away when the tunnel attractivenesses are updated
	 * @param pheromoneStrengthCoefficient
	 *            a factor used in updating the tunnel attractivenesses
	 * @param seed
	 *            a seed for the random number generator
	 * @return the sequence of chambers from the queen's nest to the food
	 *         chamber, in this case without any trailing negative ones, that
	 *         had the smallest length of all such sequences that were
	 *         considered
	 */

	public static int[] findShortestRoute(double[][] map, int nestLocation,
			int foodLocation, int nAnts, int nIterations,
			double pheromoneEvaporationCoefficient,
			double pheromoneStrengthCoefficient, int seed) {
		
		//Initializes Random generator to be used in chooseTunnel.
		Random random = new Random(seed);
		
		//Initialize tunnel attractiveness through 
		//initializeTunnelAttractivenesses.
		double[][] tunnelAttractivenesses = 
			AntColonyOptimization.initializeTunnelAttractivenesses(map);
		
		//Initialize current shortest route to be used in findShorterRoute.
		int[] currentShortestRoute = null;
		
		//Initialize ant routes to be used in findAntRoutes.
		int[][] antRoutes = null;
		
		//Start loop for a given number of iterations to find shortest ant 
		//route in map.
		for (int iteration = 1; iteration <= nIterations; iteration++) {
			
			//Finds a new set of routes from the queen's nest to the food 
			//chamber.
			antRoutes = 
				AntColonyOptimization.findAntRoutes(tunnelAttractivenesses,
						nestLocation, foodLocation, nAnts, random);
			
			//Update tunnelAttractivenesses accordingly.
			AntColonyOptimization.updateTunnelAttractivenesses(antRoutes,
					tunnelAttractivenesses, pheromoneEvaporationCoefficient,
					pheromoneStrengthCoefficient, map);
			
			//Checks if there is a shorter route than the current shortest 
			//route.
			currentShortestRoute = 
				AntColonyOptimization.findShorterRoute(currentShortestRoute,
						antRoutes, map);
		}
		
		//Trims currentShortestRoute so there is no trailing negative ones at
		//the end of route.
		currentShortestRoute = trimRoute(currentShortestRoute);
		return currentShortestRoute;
	}

	/**
	 * Creates the initial tunnelAttractivenesses two-dimensional double
	 * array, with a value of 1.0 for every ordered pair of indices whose
	 * value in map was positive and 0.0 for every ordered pair of indices
	 * whose value in map was zero.
	 * 
	 * @param map
	 *            distances between all pairs of chambers in which is possible
	 *            to move from one chamber to the other, if it is not possible
	 *            to move from one chamber to another then the distance for
	 *            that pair is zero
	 * @return initialTunnelAttractivenesses
	 */
	
	public static double[][] initializeTunnelAttractivenesses(double[][] map) {
		
		//Creates initial array for tunnelAttractivenesses by making a copy of
		//map array.
		double[][] tunnelAttractivenesses = 
			new double [map.length][map[0].length];
		for (int row = 0; row < map.length; row++) {
			for (int column = 0; column < map[0].length; column++) {
				tunnelAttractivenesses[row][column] = map[row][column];
			}
		}	
		
		//Initializes array for tunnelAttractivenesses.
		for (int row = 0; row<tunnelAttractivenesses.length; row++) {
			for (int column = 0; column < tunnelAttractivenesses[0].length;
			column++) {
				
				//If value on map is positive, indices on 
				//tunnelAttractivenesses equals 1.0.
				if (tunnelAttractivenesses[row][column] != 0) {
					tunnelAttractivenesses[row][column] = 1.0;
				}
				//If value on map is zero, indices on tunnelAttractivenesses 
				//equals 0.0.
				else {
					tunnelAttractivenesses[row][column] = 0.0;	
				}
			}
		}
		return tunnelAttractivenesses;
	}

	/**
	 * Probabilistically chooses the next chamber a particular ant should
	 * travel to based on the attractiveness of the tunnels from the ant's
	 * current location to the other chambers. To do this, it: 1. Finds the sum
	 * of the attractiveness of all the tunnels from the ant's location to
	 * all other chambers. 2. Calculates a threshold by multiplying a randomly
	 * generated double between 0.0 and 1.0 by the sum of the attractivenesses.
	 * 3. Starting from chamber 0, again sums the attractiveness of all the
	 * tunnels from the ant's location to all other chambers until the sum
	 * is greater than the threshold. The chamber in which the
	 * tunnel attractiveness from the ant's location to that chamber caused the
	 * sum to equal or exceed the threshold is the chamber that the ant will
	 * travel to next.
	 * 
	 * @param location
	 *            the current chamber of an ant
	 * @param tunnelAttractivenesses
	 *            the attractiveness of the tunnel between every ordered
	 *            pair of chambers
	 * @param random
	 *            a random number generator
	 * @return the chosen chamber that the ant will travel to
	 */
	
	public static int chooseTunnel(int location,
			double[][] tunnelAttractivenesses, Random random) {
		
		//Initializes total attractiveness for all tunnels.
		double totalAttractiveness = 0;
		
		//Finds the sum of attractiveness of all tunnels.
		for (int column = 0; column < tunnelAttractivenesses.length; 
		column++) {
			totalAttractiveness += tunnelAttractivenesses[location][column];
		}
		
		//Calculates threshold by multiplying a random number 0.0 through 1.0 
		//and totalAttractiveness.
		double threshold = random.nextDouble() * totalAttractiveness;
		double totalRow = 0;
		
		//Sums the attractiveness of all the tunnels from the ant's chamber to
		//all other chambers until the sum is greater than the threshold.
		for (int column = 0; column < tunnelAttractivenesses[0].length; 
		column++) {
			totalRow += tunnelAttractivenesses[location][column];
			if (totalRow > threshold) {
				return column;
			}
		}
		return 0;
	}
	
	/**
	 * Finds a new set of routes from the queen's nest chamber to the food
	 * chamber. To do this, it: 1. Creates the antRoutes two-dimensional int
	 * array. The length of each row is the total number of chambers. 2. For
	 * every ant (i.e. row), initializes its first chamber in antRoutes to be
	 * the queen's nest chamber, and initializes all other values in antRoutes
	 * to negative one. 3. As long as is necessary, repeatedly computes for all
	 * ants (i.e., rows) in order whether or not the ant has already reached
	 * the food. If an ant has not already reached the food, a tunnel is
	 * chosen and antRoutes is updated. All ants that have not already reached 
	 * the food chamber are moved for the nth time before any ant is moved for 
	 * the (n + 1)th time.
	 * 
	 * @param tunnelAttractivenesses
	 *            the attractiveness of the tunnel between every ordered
	 *            pair of chambers
	 * @param nestLocation
	 *            the chamber of the queen's nest
	 * @param foodLocation
	 *            the chamber of the food
	 * @param nAnts
	 *            the number of ants that will find a route from the nest to
	 *            the food in each iteration of the algorithm
	 * @param random
	 *            a random number generator
	 * @return antRoutes
	 */

	public static int[][] findAntRoutes(double[][] tunnelAttractivenesses,
			int nestLocation, int foodLocation, int nAnts, Random random) {
		
		//Creates initial array for ant routes.
		int[][] antRoutes = new int [nAnts][tunnelAttractivenesses[0].length];
		
		//Initializes antRoutes array.
		for (int ant = 0; ant < antRoutes.length; ant++) {
			for (int chamber = 0; chamber < antRoutes[0].length; chamber++) {
				
				//Initializes first chamber of antRoutes to nest chamber.
				if (chamber == 0) {
					antRoutes[ant][0] = nestLocation;
				}
				
				//Initializes all other values in antRoutes to negative one.
				else {
					antRoutes[ant][chamber] = -1;
				}
			}
		}
		
		//Finds a new set of routes from the queen's nest to the food chamber
		//for each ant.
		for (int chamber = 1; chamber < antRoutes[0].length; chamber++) {
			for (int ant = 0; ant < nAnts; ant++) {	
				
				//Initializes the last location of the ant in antRoutes.
				int location = 0;
				
				//Initializes the index of the last location for ant in
				//antRoutes.
				int locationIndex = 0;
				
				//Loop for finding the last location of the ant in antRoutes.
				while (location != -1 && location != foodLocation) {
					locationIndex++;
					location = antRoutes[ant][locationIndex];
				}
				locationIndex--;

				//If the last location was not the food chamber, another a 
				//tunnel is chosen.
				if (location != foodLocation) {     
					
					//antRoutes is updated with the value of the latest 
					//location of the ant.
					location = antRoutes[ant][locationIndex];
					antRoutes[ant][chamber] = 
						AntColonyOptimization.chooseTunnel(location, 
								tunnelAttractivenesses, random);
				}
			}
		}
		return antRoutes;
	}

	/**
	 * Calculates the length of a route according to the distances in the map.
	 * 
	 * @param route
	 *            a sequence of chambers from the queen's chamber to the food
	 *            chamber, followed by some number of negative ones
	 * @param map
	 *            distances between all pairs of chambers in which is possible
	 *            to move from one chamber to the other, if it is not possible
	 *            to move from one chamber to another then the distance for
	 *            that pair is zero
	 * @return the length of the route
	 */
	
	public static double calcLengthOfRoute(int[] route, double[][] map) {
		
		//Initializes length for each ant route.
		double length = 0;
		
		//Calculates the length of a route according to the distances in the 
		//map while route is valid.
		for (int chamber = 0; chamber<route.length - 1; chamber++) {
			if (route[chamber + 1] != -1) {
				length += map[route[chamber]][route[chamber + 1]];
			}
		}
		return length;
	}

	/**
	 * Changes the attractiveness of tunnels based on the length of any routes
	 * they were used in, and then proportionately decreases the attractiveness
	 * of all of the tunnels. To do this, it: 1. For every tunnel taken in
	 * every route in antRoutes, the pheromoneStrengthCoefficient / the length
	 * of the route is added to the attractiveness of that tunnel. 2. The
	 * attractiveness of all tunnels is multiplied by 1.0 - the
	 * pheromoneEvaporationCoefficient.
	 * 
	 * @param antRoutes
	 *            a set of routes from the nest to the food for a number of
	 *            ants. The first dimension corresponds to ants and the second
	 *            dimension corresponds to time. For each ant, the values over
	 *            time correspond to the chambers the ant visited. The length
	 *            of the second dimension is the total number of chambers in
	 *            the world, and the values over time after an ant has reached
	 *            the food are -1.
	 * @param tunnelAttractivenesses
	 *            the attractiveness of the tunnel between every ordered
	 *            pair of chambers
	 * @param pheromoneEvaporationCoefficient
	 *            the proportion of each tunnel attractiveness that is taken
	 *            away when the tunnel attractivenesses are updated
	 * @param pheromoneStrengthCoefficient
	 *            a factor used in updating the tunnel attractivenesses
	 * @param map
	 *            distances between all pairs of chambers in which is possible
	 *            to move from one chamber to the other, if it is not possible
	 *            to move from one chamber to another then the distance for
	 *            that pair is zero
	 */
	
	public static void updateTunnelAttractivenesses(int[][] antRoutes,
			double[][] tunnelAttractivenesses,
			double pheromoneEvaporationCoefficient,
			double pheromoneStrengthCoefficient, double[][] map) {

		//Updates attractiveness of tunnels based on the length of routes they
		//were used in and then proportionately decreases the attractiveness of
		//all of the tunnels.
		for (int ant = 0; ant < antRoutes.length; ant++) {
			for (int chamber = 0; chamber < antRoutes[0].length - 1; 
			chamber++) {
				
				//Initializes the first chamber in the tunnel.
				int row = antRoutes[ant][chamber];
				
				//Initializes the second chamber in the tunnel.
				int column = antRoutes[ant][chamber + 1];
				
				//If the route is valid, it updates tunnelAttractivenesses
				//accordingly.
				if (column != -1){
					double length = map[row][column];
					tunnelAttractivenesses[row][column] += 
						pheromoneStrengthCoefficient / length;			
					tunnelAttractivenesses[row][column] = 
						tunnelAttractivenesses[row][column] - 
						pheromoneEvaporationCoefficient;
				}
			}
		}
	}

	/**
	 * Finds if there is a shorter route than currentShortestRoute of the
	 * routes in antRoutes using to the distances in the map to determine their
	 * length. If there is more than one route tieing for shortest, it will
	 * return the first one, starting from currentShortestRoute and
	 * continuing in increasing order through the indexes of antRoutes.
	 * 
	 * @param currentShortestRoute
	 *            the sequence of chambers from the queen's nest to the food
	 *            chamber, followed by some number of negative ones, that has
	 *            the smallest length of any such sequence considered so far.
	 *            This parameter may be passed null, in which case the
	 *            currentShortestRoute is not to be considered.
	 * @param antRoutes
	 *            a set of routes from the nest to the food for a number of
	 *            ants. The first dimension corresponds to ants and the second
	 *            dimension corresponds to time. For each ant, the values over
	 *            time correspond to the chambers the ant visited. The length
	 *            of the second dimension is the total number of chambers in
	 *            the world, and the values over time after an ant has reached
	 *            the food are -1.
	 * @param map
	 *            distances between all pairs of chambers in which is possible
	 *            to move from one chamber to the other, if it is not possible
	 *            to move from one chamber to another then the distance for
	 *            that pair is zero
	 * @return the sequence of chambers from the queen's nest to the food
	 *         chamber, followed by some number of negative ones, that has the
	 *         smallest length of such sequences amongst the
	 *         currentShortestRoute and the routes in antRoutes
	 */
	
	public static int[] findShorterRoute(int[] currentShortestRoute,
			int[][] antRoutes, double[][] map) { 
		
		//Initializes length of shortest route.
		double shortestLength = 0;
		
		//Sets the shortest length to be the length of the
		//currentShortestRoute.
		if (currentShortestRoute != null) {
			shortestLength = calcLengthOfRoute(currentShortestRoute,map);
		}
		
		//If currentShortestRoute is null, the shortestLength equals the length
		//of the first route in antRoutes.
		else {
			currentShortestRoute = antRoutes[0];
			shortestLength = calcLengthOfRoute(currentShortestRoute,map);
		}
		
		//Finds if there is a shorter route than currentShortestRoute in 
		//antRoutes.
		for (int ant = 0; ant < antRoutes.length; ant++) {
			double length = calcLengthOfRoute(antRoutes[ant],map);
			
			//If the new length is smaller than the shortestLength, 
			//shortestLength and currentShortestRoute are updated with the new
			//values.
			if (length < shortestLength) {
				shortestLength = length;
				currentShortestRoute = antRoutes[ant];
			}

		}
		return currentShortestRoute;
	}

	/**
	 * Makes a new array representing the same sequence of chambers as in route
	 * but without any trailing negative ones.
	 * 
	 * @param route
	 *            a sequence of chambers from the queen's nest to the food
	 *            chamber, followed by some number of negative ones
	 * @return a sequence of chambers from the queen's nest to the food
	 *         chamber
	 */
	public static int[] trimRoute(int[] route) {

		//Initializes length of route.
		int length = 0;
		
		//If chamber is a valid chamber, length gets incremented.
		for (int chamber = 0; chamber < route.length; chamber++) {
			if (route[chamber] != -1) {
				length++;
			}
		}
		
		//The finalRoute is created with the value of length.
		int[] finalRoute = new int [length];
		
		//finalRoute is initialized with the values of route that are not -1.
		for (int chamber = 0; chamber < finalRoute.length; chamber++) {
			finalRoute[chamber] = route[chamber];
		}
		return finalRoute;

	}

	/**
	 * Displays the route horizontally on the screen separated by spaces.
	 * 
	 * @param route
	 *            a sequence of chambers from the queen's nest to the food
	 *            chamber
	 */
	
	public static void printRoute(int[] route) {
		
		//Displays the route on the screen.
		for (int chamber = 0; chamber < route.length; chamber++) {
			System.out.print(route[chamber] + " ");
		}
		System.out.println();
	}
}
