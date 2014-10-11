import java.util.ArrayList;


public class AlgorithmMM extends AbstractEA {
	
	private final int MU; // number of individuals in each generation (parent population, p.81 book)
	private final int LAMBDA; // number of offspring to be generated in each generation
	
	public AlgorithmMM(int mu, int lambda) {
		this.MU = mu;
		this.LAMBDA = lambda;
		this.initialPopulation = new InitialPopulationSimple(MU);
		this.population = initialPopulation.createInitialPopulation();
		this.parentSelection = new ParentSelectionMM();
		this.recombination = new RecombinationDiscreteAndIntermediate();
		this.mutation = new MutationUncorrelated();
		this.survivalSelection = new SurvivalSelectionMuCommaLambda(new SelectionAbsoluteFitness(MU));
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
