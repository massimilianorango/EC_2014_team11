import java.util.ArrayList;

public class RecombinationMM extends IRecombination {

	private final static double ALPHA = 0.5;

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

	// blend crossover
	private double[] dnaCrossover(Individual[] parents) {
		double[] child_dna = new double[player11.F_DIMENSION];
		for (int i = 0; i < child_dna.length; i++) {
			double x_i = parents[0].getDna()[i];
			double y_i = parents[1].getDna()[i];
			if (y_i < x_i) {
				double tmp = y_i;
				y_i = x_i;
				x_i = tmp;
			}
			double d_i = y_i - x_i;
			double rMax = x_i + ALPHA * d_i;
			double rMin = x_i - ALPHA * d_i;
			child_dna[i] = player11.getRnd().nextDouble() * (rMax - rMin) + rMin;
		}

		return child_dna;

	}
	
	@Override
	protected double[] sigmaCrossover(Individual[] parents) {
		double[] child_sigma = new double[player11.F_DIMENSION];
		Individual parentA = parents[0];
		Individual parentB = parents[1];
		for (int i = 0; i < child_sigma.length; i++) {
			child_sigma[i] = (parentA.getSigma_mutation_step_sizes()[i] + parentB.getSigma_mutation_step_sizes()[i]) / 2;
		}
		return child_sigma;
	}

}
