import java.util.ArrayList;

public abstract class IRecombination {

	public abstract ArrayList<Individual> crossover(Individual[] parents, int number_of_children);
	
	// TODO: should be global intermediate recombination and use this.population (now it's local)
    protected double[] sigmaCrossover(Individual[] parents) {
        double[] child_sigma = new double[player11.F_DIMENSION];
        Individual parentA = parents[0];// population.remove(player11.getRnd().nextInt(population.size()));
        Individual parentB = parents[1];// population.remove(player11.getRnd().nextInt(population.size()));
        for (int i = 0; i < child_sigma.length; i++) {
            // average of 2 random parents
            child_sigma[i] = (parentA.getSigma_mutation_step_sizes()[i] + parentB.getSigma_mutation_step_sizes()[i]) / 2;
        }
        // population.add(parentA);
        // population.add(parentB);
        return child_sigma;
    }
}
