package test;

import java.util.Properties;

import org.vu.contest.ContestEvaluation;

public class RastriginEvaluation implements ContestEvaluation {
	// Evaluations budget
	private final static int EVALS_LIMIT_ = 45000;
	// The base performance. It is derived by doing random search on the sphere function (see function method) with the
	// same
	// amount of evaluations
	private final static double BASE_ = 11.5356;
	// The minimum of the sphere function
	private final static double ftarget_ = 0;

	// Best fitness so far
	private double best_;
	// Evaluations used so far
	private int evaluations_;

	// Properties of the evaluation
	private String multimodal_ = "true";
	private String regular_ = "true";
	private String separable_ = "false";
	private String evals_ = Integer.toString(EVALS_LIMIT_);

	public RastriginEvaluation() {
		best_ = 0;
		evaluations_ = 0;
	}

	private double function(double[] x) {
		double sum = 0.0;
		for (int i = 0; i < x.length; i++) {
			sum += (Math.pow(x[i],2) - 10*Math.cos(2*Math.PI*x[i]));
		}
		return 10*10+sum;
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
