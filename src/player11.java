import java.util.Properties;
import java.util.Random;

import org.vu.contest.ContestEvaluation;
import org.vu.contest.ContestSubmission;

/**
 * Sets the run environment and chooses the right IAlgorithm to be run. Contains utility methods shared between many
 * classes.
 */
public class player11 implements ContestSubmission {

	public static final int F_DIMENSION = 10; // dimension of each function
	public static final int MIN_SEARCH_VALUE = -5; // min x value
	public static final int MAX_SEARCH_VALUE = 5; // max x value

	private static Random rnd;
	private AbstractEA algorithm;

	public player11() {
		rnd = new Random();
	}

	@Override
	public void setSeed(long seed) {
		rnd.setSeed(seed);
	}

	@Override
	public void setEvaluation(ContestEvaluation evaluation) {

		// Get evaluation properties
		Properties props = evaluation.getProperties();
		int evaluationsLimit = Integer.parseInt(props.getProperty("Evaluations"));
		boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
		boolean isRegular = Boolean.parseBoolean(props.getProperty("Regular"));
		boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));
		
		if(!isMultimodal) {
			int mu = 4, lambda = mu*7;
			algorithm = new AlgorithmUM(mu, lambda);
		} else {
			if(!isRegular) {
//				int mu = 50, lambda = mu*7;
//				algorithm = new AlgorithmMM(mu, lambda);
				
				int mu = 30, lambda = mu * 7, k = 20;
				algorithm = new AlgorithmES(mu, lambda, new SelectionTournament(k, mu));
			} else {
				int mu = 15, lambda = 200;
				algorithm = new AlgorithmES(mu, lambda, new SelectionAbsoluteFitness(mu));
			}
		}

		/*if (!isMultimodal && isSeparable && !isRegular) {
			
			//NEW VERSION: like f3
			int mu = 15, lambda = mu * 7;
			algorithm = new AlgorithmES(mu, lambda, new SelectionAbsoluteFitness(mu));
			
			//OLD VERSION:
			//int mu = 30, lambda = mu * 7, k = 20;
			//algorithm = new AlgorithmES(mu, lambda, new SelectionTournament(k, mu));
			// something simpler should be better as it is not multimodal -> AlgorithmSimple()

		} else if (isMultimodal && !isSeparable && !isRegular) {

			int mu = 30, lambda = mu * 7, k = 20;
			algorithm = new AlgorithmES(mu, lambda, new SelectionTournament(k, mu));
			// probably a function with randomly distributed (and high) local optima

		} else { // if(isMultimodal && !isSeparable && isRegular){

			int mu = 15, lambda = 200;
			algorithm = new AlgorithmES(mu, lambda, new SelectionAbsoluteFitness(mu));
			// something like 'Ackley's function' -> ES should be ok but needs improvement

		}*/

		algorithm.setEvaluation(evaluation, evaluationsLimit);

	}
	
	public void setTuningAlgorithm(AbstractEA algorithm){
	    this.algorithm = algorithm;
	}

	@Override
	public void run() {
		try {
			algorithm.run();
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public static Random getRnd() {
		return rnd;
	}

	/**
	 * @param intervals
	 *            probability list like [0.1][0.15][0.21][0.32]..[1]
	 * @return returns list index of the first value which is higher than the given one
	 */
	public static int getProbabilityBasedRandomIndex(double[] intervals) {
		Double random = rnd.nextDouble();
		for (int i = 0; i < intervals.length; i++) {
			if (intervals[i] > random) {
				// found parent in range e.g if random = 0.2 -> [0.1][0.15][x][0.32]..[1]
				return i;
			}
		}
		throw new RuntimeException("Random number i=[0-1[ was not inside the given interval.");
	}

	public static int[] shuffleArray(int size) {
		int[] ar = new int[size];
		// initialize array {0...size-1}
		for (int i = 0; i < ar.length; i++) {
			ar[i] = i;
		}
		// shuffle
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
		return ar;
	}

	public static void main(String[] args) {
	}

}
