import java.util.ArrayList;

/**
 * Implements a general Evolution Strategy algorithm (Chapter 4, book).
 * 
 * @author max
 *
 */
public class AlgorithmES extends AbstractEA {

	private final static int MU = 15; // number of individuals in each generation (parent population, p.81 book)
	private final static int LAMBDA = 100; // number of offspring to be generated in each generation

	public AlgorithmES() {
		initialPopulation = new InitialPopulationSimple(MU);
		population = initialPopulation.createInitialPopulation();
		parentSelection = new ParentSelectionUniformRandom();
		recombination = new RecombinationDiscreteAndIntermediate(population.getIndividuals());
		mutation = new MutationUncorrelated();
		survivalSelection = new SurvivalSelectionMuCommaLambda(MU);
	}

	@Override
	public void run() {
		for (Individual ind : population.getIndividuals()) {
			evaluateInitialIndividual(ind);
		}
		while (getEvals() < evaluationsLimit) {
			ArrayList<Individual> children = new ArrayList<Individual>();
			for (int i = 0; i < LAMBDA * 2; i++) { // TODO: fix: should be only LAMBDA
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
