
public class AntWorld {
	
	public final double[][] MAP;
	public final int NEST_LOCATION;
	public final int FOOD_LOCATION;
	public final int N_ANTS;
	public final int N_ITERATIONS;
	public final double PHEROMONE_EVAPORATION_COEFFICIENT;
	public final double PHEROMONE_STRENGTH_COEFFICIENT;
	public final int SEED;
	
	
	public AntWorld(double[][] map, int nestLocation, int foodLocation, int nAnts, int nIterations, double pheromoneEvaporationCoefficient, double pheromoneStrengthCoefficient, int seed){
		MAP = map;
		NEST_LOCATION = nestLocation;
		FOOD_LOCATION = foodLocation;
		N_ANTS = nAnts;
		N_ITERATIONS = nIterations;
		PHEROMONE_EVAPORATION_COEFFICIENT = pheromoneEvaporationCoefficient;
		PHEROMONE_STRENGTH_COEFFICIENT = pheromoneStrengthCoefficient;
		SEED = seed;
	}
	
	
	public static double[][] map1 =
	{
		{0,2,2,1,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,2,2,1,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,2,2,1,2,2,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,1,2,2,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,1,2,2,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	};
	public static AntWorld antWorld1 = new AntWorld(map1, 0, 26, 10, 50, .1, 5, 0);
	
	
	public static double[][] map2 = 
	{
		{0,1,2,0},
		{0,0,0,5},
		{0,0,0,3},
		{0,0,0,0}
	};
	public static AntWorld antWorld2 = new AntWorld(map2, 0, 3, 10, 50, .01, 1, 0);
	
	
	public static double[][] map3 =
	{
		{0,1,0,0},
		{0,0,1,0},
		{0,0,0,1},
		{0,0,0,0},
	};
	public static AntWorld antWorld3 = new AntWorld(map3, 0, 3, 2, 50, .01, 1, 0);
	
	
	//TODO: Change the line below to use other ant worlds
	//We suggest initially using antWorld2 to develop your program by
	//commenting out the line below and uncommenting the second line below

	public static AntWorld antWorld = antWorld1;
	//public static AntWorld antWorld = antWorld2;
	
	
}
