import java.util.ArrayList;

/**
 * Performs recombination using local discrete recombination for DNA and global intermediate recombination for sigma
 * values (p. 80, 81 book).
 * 
 * @author max
 */
public class RecombinationDiscreteAndIntermediate extends IRecombination {

	private ArrayList<Individual> population = null; // should be used

	public void setPopulation(ArrayList<Individual> population){
	       this.population = population;
	}

	private double[] dnaCrossover(Individual[] parents) {
		// local discrete recombination
		double[] child_dna = new double[player11.F_DIMENSION];
		for (int j = 0; j < child_dna.length; j++) {
			child_dna[j] = parents[player11.getRnd().nextInt(parents.length)].getDna()[j];
		}
		return child_dna;
	}

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
