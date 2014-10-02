import java.util.ArrayList;

/**
 * Implements a general Evolution Strategy algorithm (Chapter 4, book).
 * 
 * @author max
 *
 */
public class AlgorithmES extends AbstractEA {
    protected Population population;

	private int MU; // number of individuals in each generation (parent population, p.81 book)
	private int LAMBDA; // number of offspring to be generated in each generation
	protected RecombinationDiscreteAndIntermediate recombination; // how to recombine parents

	public AlgorithmES(){
	    this(15);
	}
	
	public AlgorithmES(int MU) {
        this(MU,100, new MutationUncorrelated());
    }
	
	public AlgorithmES(int MU, int LAMBDA) {
        this(MU,LAMBDA, new MutationUncorrelated());
    }
	
	public AlgorithmES(int MU, int LAMBDA, double ALPHA) {
        this(MU,LAMBDA, new MutationUncorrelated(ALPHA));
    }
	
	public AlgorithmES(int MU, int LAMBDA, double ALPHA, double EPSILON) {
	    this(MU,LAMBDA, new MutationUncorrelated(ALPHA, EPSILON));
	}
	
	public AlgorithmES(int MU, int LAMBDA, IMutation mutation){
	    this.MU = MU;
	    this.LAMBDA = LAMBDA;
        initialPopulation = new InitialPopulationSimple(MU);
        parentSelection = new ParentSelectionUniformRandom();
        recombination = new RecombinationDiscreteAndIntermediate();
        this.mutation = mutation;
        survivalSelection = new SurvivalSelectionMuCommaLambda(MU);
	}

	@Override
	public void run() {
        population = initialPopulation.createInitialPopulation();
        recombination.setPopulation(population.getIndividuals());
        
		for (Individual ind : population.getIndividuals()) {
			evaluateInitialIndividual(ind);
		}
		while (getEvals() < evaluationsLimit) {
			ArrayList<Individual> children = new ArrayList<Individual>();
			for (int i = 0; i < LAMBDA; i++) { // TODO: fix: should be only LAMBDA
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
