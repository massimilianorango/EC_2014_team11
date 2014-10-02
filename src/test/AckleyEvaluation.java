package test;

import java.util.Properties;

public class AckleyEvaluation extends TuningEvaluation {
	// Evaluations budget
	private int EVALS_LIMIT_;
	// The base performance. It is derived by doing random search on the sphere
	// function (see function method) with the same
	// amount of evaluations
	private final static double BASE_ = 11.5356;
	// The minimum of the sphere function
	private final static double ftarget_ = 0;

	// Properties of the evaluation
	private String multimodal_ = "true";
	private String regular_ = "true";
	private String separable_ = "false";
	private String evals_ = Integer.toString(EVALS_LIMIT_);

	   
    public AckleyEvaluation() {
        this(10000);
    }
    
	public AckleyEvaluation(int EVALS_LIMIT_) {
	    this.EVALS_LIMIT_ = EVALS_LIMIT_;
	    evals_ = Integer.toString(EVALS_LIMIT_);
        best_ = -100;
        evaluations_ = 0;
    }

	// The standard sphere function. It has one minimum at 0.
	private double function(double[] x) {
		double sum1 = 0.0;
		double sum2 = 0.0;
		for (int i = 0; i < x.length; i++) {
			sum1 += (x[i] * x[i]);
			sum2 += (Math.cos(2 * Math.PI * x[i]));
		}
		return (-20.0 * Math.exp(-0.2 * Math.sqrt(sum1 / ((double) x.length))) - Math.exp(sum2 / ((double) x.length))
				+ 20.0 + Math.E);
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
