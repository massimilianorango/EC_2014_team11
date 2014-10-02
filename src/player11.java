import java.util.Properties;
import java.util.Random;

import org.vu.contest.ContestEvaluation;
import org.vu.contest.ContestSubmission;

/**
 * Sets the run environment and chooses the right IAlgorithm to be run. Contains utility methods shared between many classes.
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

	public static Random getRnd() {
		return rnd;
	}

	@Override
	public void setEvaluation(ContestEvaluation evaluation) {

		// Get evaluation properties
		Properties props = evaluation.getProperties();
		int evaluationsLimit = Integer.parseInt(props.getProperty("Evaluations"));
		boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
		boolean isRegular = Boolean.parseBoolean(props.getProperty("Regular"));
		boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

		if (!isMultimodal && isSeparable && !isRegular) {
			algorithm = new AlgorithmES(15,100,1); // something simpler should be better as it is not multimodal -> AlgorithmSimple()
		} else if (isMultimodal && !isSeparable && !isRegular) {
			algorithm = new AlgorithmESAdvanced(15,200,1); // probably a function with randomly distributed (and high) local optima
		} else { // if(isMultimodal && !isSeparable && isRegular){
			algorithm = new AlgorithmESAdvanced(15,100,1); // something like 'Ackley's function' -> ES should be ok but needs improvement
		}
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
		    if(e.getMessage()!="Maximum evaluations were reached."){
	            e.printStackTrace();
	            System.out.println(e.getMessage());
		    }
		}
	}

	public static int getValueBasedRandomIndex(double[] values){
	    double sum = 0.0;
	    for(int i = 0; i< values.length; i++){
	        sum += values[i];
	    }
	    
	    for(int i = 0; i< values.length; i++){
	        values[i] = values[i]/sum;
	    }
	    
	    double[] probability_array = new double[values.length];
	    double probability_sum = 0.0;
	    for(int i = 0; i<values.length; i++){
	        probability_sum += values[i];
	        probability_array[i] = probability_sum;
	    }
	    
	    return getProbabilityBasedRandomIndex(probability_array);
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

	public static void main(String[] args) {
	}

}
