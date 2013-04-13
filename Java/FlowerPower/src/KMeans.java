/**
 * A k-means clustering algorithm implementation.
 * 
 */

public class KMeans {
	// Attributes here
	private int centroidsCol = 0;
	private int centroidsRow = 0;
	private int instancesCol = 0;
	private int instancesRow = 0;
	private double d = 0;
	private int[] clusterAssignment; 
	private double[] distortionIterations = new double[100000];
	private int distortionCount = 0;
	private double[][] centroids;
	private double[][] instances;
	private double stop = 0;
	
	public KMeansResult cluster(
			double[][] centroids, 
			double[][] instances, 
			double stoppingPoint) {
		/* ... YOUR CODE GOES HERE ... */
		this.centroidsCol = centroids.length;
		this.centroidsRow = centroids[0].length;
		this.instancesCol = instances.length;
		this.instancesRow = instances[0].length;
		this.clusterAssignment = new int[instancesCol];
		
		this.centroids = new double[this.centroidsCol][this.centroidsRow];
		this.instances = new double[this.instancesCol][this.instancesRow];
		for (int i = 0; i < this.centroidsCol; i++) {
			for (int j = 0; j < this.centroidsRow; j++) {
				this.centroids[i][j] = centroids[i][j];
			}
		}
		
		for (int i = 0; i < this.instancesCol; i++) {
			for (int j = 0; j < this.instancesRow; j++) {
				this.instances[i][j] = instances[i][j];
			}
		}
		
		if (centroidsRow != instancesRow) {
			System.out.println("Instances should have the same dimensions" +
					"as the centroids!");
			System.exit(1);
		}
		
		// Assign instances to centroids
		assignCentroids(this.centroids, this.instances);
		
		
		this.stop = stoppingPoint + 1;
		
		// Distortion
		while (this.stop > stoppingPoint) {
			this.stop = distortion(this.clusterAssignment, this.centroids, 
					this.instances);
			assignCentroids(this.centroids, this.instances);
		}
		
		int count = 0;
		
		for (int i = 0; i < this.distortionIterations.length; i++) {
			if (this.distortionIterations[i] != 0) {
				count++;
			}
		}
		
		double[] tmp = new double[count];
		
		for (int i = 0; i < count; i++) {
			tmp[i] = this.distortionIterations[i];
		}
		
		this.distortionIterations = tmp;
		
		// Return the value 
		KMeansResult result = new KMeansResult();
		result.centroids = this.centroids;
		result.clusterAssignment = this.clusterAssignment;
		result.distortionIterations = this.distortionIterations;
		return result;
	}	
	
	/**
	 * Assign each the instance to its centroid
	 * @param centroids
	 * @param instances
	 */
	private void assignCentroids (double [][] centroids, double[][] instances) {
		double minimum = 0;
		int min = 0;
		for (int i = 0; i < this.instancesCol; i++) {
			for (int j = 0; j < this.centroidsCol; j++) {
				this.d = distance (centroids[j], instances[i]);
				if (j == 0) {
					minimum = this.d;
					min = 0;
				}
				if (this.d < minimum) {
					minimum = this.d;
					min = j;
				}
			}
			this.clusterAssignment[i] = min;
		}
	}
	
	/**
	 * Calculate the distance of a instance and a specific centroid
	 * @param centroid
	 * @param instance
	 * @return the distance between the instance and the centroid
	 */
	private double distance (double[] centroid, double[] instance) {
		double distance = 0;
		for (int i = 0; i < centroid.length; i++) {
			distance += Math.pow((centroid[i] - instance[i]), 2);
		}
		return distance;
	}
	
	/**
	 * Distortion
	 * @param clusterAssignment
	 * @param centroids
	 * @param instances
	 */
	private double distortion (int[] clusterAssignment, double[][] centroids, 
			double[][] instances) {
		centroidsAdjustment(this.clusterAssignment, 
				this.centroids, this.instances);
		double[] clusterDistance = new double[instancesCol];
		double tmpSum = 0;
		for (int i = 0; i < this.instancesCol; i++) {
			clusterDistance[i] = this.distance(
				this.centroids[this.clusterAssignment[i]], this.instances[i]);
			tmpSum += clusterDistance[i];
		}
		this.distortionIterations[this.distortionCount] = tmpSum;
		this.distortionCount++;
		if (this.distortionCount == 1) {
			return this.distortionIterations[0];
		}
		return (this.distortionIterations[this.distortionCount - 2] - 
				this.distortionIterations[this.distortionCount - 1]);
	}
	
	/**
	 * Adjust the centroids' locations
	 * @param clusterAssignment
	 * @param centroids
	 * @param instances
	 */
	private void centroidsAdjustment (int[] clusterAssignment, 
			double[][] centroids, double[][] instances) {
		// Move the centroid which has no instances to the instance which
		// is the farthest from it. Assign the instance to the centroid
		int[] flag = new int[centroids.length];
		int tmp = -1;
		for (int i = 0; i < centroids.length; i++) {
			flag[i] = 0;
		}
		for (int i = 0; i < clusterAssignment.length; i++) {
			flag[this.clusterAssignment[i]] = 1;
		}
		for (int i = 0; i < flag.length; i++) {
			if (flag[i] == 0) {
				tmp = i;
				break;
			}
		}
		if (tmp != -1) {
			double dis = 0;
			double maximum = 0;
			int max = 0;
			for (int i = 0; i < instances.length; i++) {
				dis = this.distance(centroids[this.clusterAssignment[i]], 
						instances[i]);
				if (dis > maximum) {
					maximum = dis;
					max = i;
				}
			}
			for (int i = 0; i < this.instancesRow; i++) {
				centroids[tmp][i] = instances[max][i];
			}
			this.clusterAssignment[max] = tmp;
		}
		
		// Update centroids' locations
		for (int i = 0; i < centroids.length; i++) {
			double tmpCount = 0;
			double[] tmpSum = new double[this.instancesRow];
			for (int j = 0; j < instances.length; j++) {
				if (this.clusterAssignment[j] == i) {
					tmpCount++;
					for (int k = 0; k < this.instancesRow; k++) {
						tmpSum[k] += this.instances[j][k];
					}
				}
			}
			for (int l = 0; l < this.instancesRow; l++) {
				tmpSum[l] = tmpSum[l] / tmpCount;
				this.centroids[i][l] = tmpSum[l];
			}
			//Assign the new location for centroids
		}
	}
}