import java.util.ArrayList;

/**
 * Implements a general Evolution Strategy algorithm (Chapter 4, book).
 * 
 * @author max
 *
 */
public class AlgorithmES extends AbstractEA {

	private final int MU; // number of individuals in each generation (parent population, p.81 book)
	private final int LAMBDA; // number of offspring to be generated in each generation
	
	public AlgorithmES(int mu, int lambda, ISelection selectionAlgorithm, IMutation mutation) {
		this.MU = mu;
		this.LAMBDA = lambda;
		this.initialPopulation = new InitialPopulationSimple(MU);
		this.population = initialPopulation.createInitialPopulation();
		this.parentSelection = new ParentSelectionUniformRandom();
		this.recombination = new RecombinationDiscreteAndIntermediate();
		this.mutation = mutation;
		this.survivalSelection = new SurvivalSelectionMuCommaLambda(selectionAlgorithm);
	}
	
	public AlgorithmES(int mu, int lambda, ISelection selectionAlgorithm) {
		this(mu, lambda, selectionAlgorithm, new MutationUncorrelated());
	}
	
	public AlgorithmES(int MU, int LAMBDA, ISelection selectionAlgorithm, double ALPHA, double EPSILON) {
	    this(MU,LAMBDA, selectionAlgorithm, new MutationUncorrelated(ALPHA, EPSILON));
	}
	
	public AlgorithmES(int MU, int LAMBDA, ISelection selectionAlgorithm, double ALPHA) {
        this(MU,LAMBDA, selectionAlgorithm, new MutationUncorrelated(ALPHA));
    }
	
	public AlgorithmES(int MU, ISelection selectionAlgorithm) {
        this(MU, 100, selectionAlgorithm, new MutationUncorrelated());
    }
	
	public AlgorithmES(ISelection selectionAlgorithm){
	    this(15, selectionAlgorithm);
	}

	@Override
	public void run() {
		for (Individual ind : population.getIndividuals()) {
			evaluateInitialIndividual(ind);
		}
		while (getEvals() < evaluationsLimit) {
			ArrayList<Individual> children = new ArrayList<Individual>();
			for (int i = 0; i < LAMBDA; i++) {
				Individual[] couple = parentSelection.selectParents(population, 2);
				Individual child = recombination.crossover(couple, 1).get(0);
				mutation.mutate(child);
				evaluateIndividual(child, population.getGeneration()+1);
				children.add(child);
			}
			survivalSelection.selectSurvivals(population, children);
			population.increaseGeneration();
			System.out.println("Generation " + population.getGeneration() + " Result: "
					+ population.getBestIndividual());
		}
	}

}
