import java.util.ArrayList;

/**
 * Performs recombination using local discrete recombination for DNA and local intermediate recombination for sigma
 * values (p. 80, 81 book).
 * 
 * @author max
 */
public class RecombinationDiscreteAndIntermediate implements IRecombination {

	public RecombinationDiscreteAndIntermediate() {
	}

	private double[] dnaCrossover(Individual[] parents) {
		// local discrete recombination
		double[] child_dna = new double[player11.F_DIMENSION];
		for (int j = 0; j < child_dna.length; j++) {
			child_dna[j] = parents[player11.getRnd().nextInt(parents.length)].getDna()[j];
		}
		return child_dna;
	}

	// TODO: should be global intermediate recombination (now it's local)
	private double[] sigmaCrossover(Individual[] parents) {
		double[] child_sigma = new double[player11.F_DIMENSION];
		Individual parentA = parents[0];
		Individual parentB = parents[1];
		for (int i = 0; i < child_sigma.length; i++) {
			// average of 2 random parents
			child_sigma[i] = (parentA.getSigma_mutation_step_sizes()[i] + parentB.getSigma_mutation_step_sizes()[i]) / 2;
		}
		return child_sigma;
	}

	// private double[] sigmaCrossover(Individual[] parents) {
	// double[] child_sigma = new double[player11.F_DIMENSION];
	// int firstRandom = player11.getRnd().nextInt(population.size());
	// int secondRandom = -1;
	// while ((secondRandom = player11.getRnd().nextInt(population.size())) == firstRandom)
	// ;
	// Individual parentA = population.get(firstRandom);
	// Individual parentB = population.get(secondRandom);
	// for (int i = 0; i < child_sigma.length; i++) {
	// // average of 2 random parents
	// child_sigma[i] = (parentA.getSigma_mutation_step_sizes()[i] + parentB.getSigma_mutation_step_sizes()[i]) / 2;
	// }
	// // population.add(parentA);
	// // population.add(parentB);
	// return child_sigma;
	// }

	@Override
	public ArrayList<Individual> crossover(Individual[] parents, int number_of_children) {
		ArrayList<Individual> individuals = new ArrayList<Individual>();
		for (int i = 0; i < number_of_children; i++) {
			Individual child = new Individual(dnaCrossover(parents));
			child.setSigma_mutation_step_sizes(sigmaCrossover(parents));
			individuals.add(child);
		}

		return individuals;
	}

}
