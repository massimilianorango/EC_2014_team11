package test;

import java.util.Properties;

// This is an example evalation. It is based on the standard sphere function. It is a maximization problem with a maximum of 10 for 
//  	vector x=0.
// The sphere function itself is for minimization with minimum at 0, thus fitness is calculated as Fitness = 10 - 10*(f-fbest), 
//  	where f is the result of the sphere function
// Base performance is calculated as the distance of the expected fitness of a random search (with the same amount of available
//	evaluations) on the sphere function to the function minimum, thus Base = E[f_best_random] - ftarget. Fitness is scaled
//	according to this base, thus Fitness = 10 - 10*(f-fbest)/Base
public class SphereEvaluation extends TuningEvaluation {
	// Evaluations budget
	private int EVALS_LIMIT_;
	// The base performance. It is derived by doing random search on the sphere function (see function method) with the same
	// amount of evaluations
	private final static double BASE_ = 11.5356;
	// The minimum of the sphere function
	private final static double ftarget_ = 0;

	// Properties of the evaluation
	private String multimodal_ = "false";
	private String separable_ = "true";
	private String regular_ = "false";
	private String evals_ = Integer.toString(EVALS_LIMIT_);

	public SphereEvaluation(){
	    this(10000);
	}
	public SphereEvaluation(int EVALS_LIMIT_) {
	    this.EVALS_LIMIT_ = EVALS_LIMIT_;
	    evals_ = Integer.toString(EVALS_LIMIT_);
		best_ = 0;
		evaluations_ = 0;
	}

	// The standard sphere function. It has one minimum at 0.
	private double function(double[] x) {
		double sum = 0;
		for (int i = 0; i < 10; i++) {
			sum += Math.pow(x[i], 2); // Sphere function
			// sum += 100*Math.pow((x[i+1]-Math.pow(x[i], 2)), 2) + Math.pow(1-x[i], 2); //De Jong's function 2, change
			// for i = 0 to 9
			// sum+=Math.pow(Math.abs(x[i]), i+1); //sum of different power function 9
		}
		return sum;
	}

	@Override
	public Object evaluate(Object result) {
		// Check argument
		if (!(result instanceof double[]))
			throw new IllegalArgumentException();
		double ind[] = (double[]) result;
		if (ind.length != 10)
			throw new IllegalArgumentException();

		if (evaluations_ > EVALS_LIMIT_)
			return null;

		// Transform function value (sphere is minimization).
		// Normalize using the base performance
		double f = 10 - 10 * ((function(ind) - ftarget_) / BASE_);
		if (f > best_)
			best_ = f;
		evaluations_++;

		return new Double(f);
	}

	@Override
	public Object getData(Object arg0) {
		return null;
	}

	@Override
	public double getFinalResult() {
		return best_;
	}

	@Override
	public Properties getProperties() {
		Properties props = new Properties();
		props.put("Multimodal", multimodal_);
		props.put("Regular", regular_);
		props.put("Separable", separable_);
		props.put("Evaluations", evals_);
		return props;
	}
}
