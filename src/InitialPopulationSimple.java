/**
 * Create a population made of random-valued individuals in the range specified.
 * 
 * @author max
 *
 */
public class InitialPopulationSimple implements IInitialPopulation {

	private static final double INITIAL_MUTATION_VALUE = 0.8;
	private int populationSize;

	public InitialPopulationSimple(int populationSize) {
		this.populationSize = populationSize;
	}

	@Override
	public Population createInitialPopulation() {
		Population population = new Population();

		while (population.getPopulationSize() < populationSize) {
			double[] randomDna = new double[player11.F_DIMENSION];
			for (int i = 0; i < randomDna.length; i++) {
				// bounds are [-5.0,5.0[
				randomDna[i] = (player11.getRnd().nextDouble() * player11.F_DIMENSION) - player11.MAX_SEARCH_VALUE;
			}

			Individual ind = new Individual(randomDna);

			double[] sigmas = new double[player11.F_DIMENSION];
			for (int i = 0; i < sigmas.length; i++) {
				sigmas[i] = INITIAL_MUTATION_VALUE;
			}

			ind.setSigma_mutation_step_sizes(sigmas);
			population.addIndividual(ind);
		}

		return population;
	}

}
