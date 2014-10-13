import java.util.ArrayList;


public class RecombinationUM extends IRecombination {

	@Override
	public ArrayList<Individual> crossover(Individual[] parents, int number_of_children) {
		ArrayList<Individual> individuals = new ArrayList<Individual>();
		for (int i = 0; i < number_of_children; i++) {
			double[] child_dna = new double[player11.F_DIMENSION];
			double[] child_sigma = new double[player11.F_DIMENSION];
			for(int j = 0; j < child_dna.length; j++) {
//				if(player11.getRnd().nextBoolean()) {
				if(AlgorithmUM.ran.nextBoolean()) {
					child_dna[j] = parents[0].getDna()[j];
					child_sigma[j] = parents[0].getSigma_mutation_step_sizes()[j];
				} else {
					child_dna[j] = parents[1].getDna()[j];
					child_sigma[j] = parents[1].getSigma_mutation_step_sizes()[j];
				}
			}
			Individual child = new Individual(child_dna);
			child.setSigma_mutation_step_sizes(child_sigma);
			individuals.add(child);
		}

		return individuals;
		
	}

}
