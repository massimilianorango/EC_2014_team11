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
	
	

	public AlgorithmES(int mu, int lambda, ISelection selectionAlgorithm) {
		this.MU = mu;
		this.LAMBDA = lambda;
		this.initialPopulation = new InitialPopulationSimple(MU);
		this.population = initialPopulation.createInitialPopulation();
		this.parentSelection = new ParentSelectionUniformRandom();
		this.recombination = new RecombinationDiscreteAndIntermediate();
		this.mutation = new MutationUncorrelated();
		this.survivalSelection = new SurvivalSelectionMuCommaLambda(selectionAlgorithm);
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
				evaluateChild(child);
				children.add(child);
			}
			survivalSelection.selectSurvivals(population, children);
			population.increaseGeneration();
			System.out.println("Generation " + population.getGeneration() + " Result: "
					+ population.getBestIndividual());
		}
	}

}
