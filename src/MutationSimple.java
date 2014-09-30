public class MutationSimple implements IMutation {

	public static double MUTATION_PROBABILITY = 0.7;
	public static double MUTATION_RATE = 0.2;

	@Override
	public void mutate(Individual individual) {
		for (int j = 0; j < 10; j++) {
			// apply mutation to gene
			if (player11.getRnd().nextDouble() < MUTATION_PROBABILITY) {
				// apply random mutation, the mutation rate defines how much the gene is allowed to change
				individual.getDna()[j] = individual.getDna()[j] + (player11.getRnd().nextDouble() * 2.0 - 1.0)
						* (MUTATION_RATE * individual.getDna()[j]);
				if (individual.getDna()[j] < -5) {
					individual.getDna()[j] = -5;
				}
				if (individual.getDna()[j] > 5) {
					individual.getDna()[j] = 5;
				}
			}
		}
	}

}
