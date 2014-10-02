import org.vu.contest.ContestEvaluation;

/**
 * Contains fields and methods general for all the EA algorithms implemented
 */
public abstract class AbstractEA {

	private ContestEvaluation evaluation;
	protected int evaluationsLimit;
	private int evals;

	protected IInitialPopulation initialPopulation; // how to initialize the population
	protected IParentSelection parentSelection; // how to select parents
	protected IRecombination recombination; // how to recombine parents
	protected IMutation mutation; // how to mutate offspring
	protected ISurvivalSelection survivalSelection; // how to select survivals

	public void setEvaluation(ContestEvaluation evaluation, int evaluationsLimit) {
		this.evaluation = evaluation;
		this.evaluationsLimit = evaluationsLimit;
	}

	public int getEvals() {
		return evals;
	}

	public void evaluateInitialIndividual(Individual ind) {
		evaluateIndividual(ind, 1);
	}

	public void evaluateIndividual(Individual ind, int generation) throws RuntimeException {
		Double fitness = (Double) evaluation.evaluate(ind.getDna());
		if (fitness == null) {
			throw new RuntimeException("Maximum evaluations were reached.");
		}
		evals++;
		ind.setFitness(fitness);
		ind.setGeneration(generation);
	}
	
	public void resetEvals(){
	    evals = 0;
	}

	public abstract void run();

}
