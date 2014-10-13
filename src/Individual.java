import java.util.Arrays;

public class Individual implements Comparable<Individual> {

	private double[] dna;
	private double[] sigma_mutation_step_sizes;
	private double[] recombination_operators;
	private double[] N_01;
	private Double fitness;
	private int generation;

	public Individual(double[] dna) {
		super();
		this.dna = dna;
		sigma_mutation_step_sizes = new double[dna.length];
		recombination_operators = new double[2];
		N_01 = new double[dna.length];
		for (int i = 0; i < N_01.length; i++) {
			N_01[i] = AlgorithmUM.ran.nextGaussian();
		}
	}

	@Override
	public String toString() {
		return "Individual [dna=" + Arrays.toString(dna) + ", fitness=" + fitness + "]";
	}

	public double[] getDna() {
		return dna;
	}

	public void setDna(double[] dna) {
		this.dna = dna;
	}

	public Double getFitness() {
		return fitness;
	}

	public void setFitness(Double fitness) {
		this.fitness = fitness;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public double[] getSigma_mutation_step_sizes() {
		return sigma_mutation_step_sizes;
	}

	public void setSigma_mutation_step_sizes(double[] sigma_mutation_step_sizes) {
		this.sigma_mutation_step_sizes = sigma_mutation_step_sizes;
	}

	public double[] getRecombination_parameters() {
		return recombination_operators;
	}

	public void setRecombination_parameters(double[] recombination_operators) {
		this.recombination_operators = recombination_operators;
	}

	@Override
	public int compareTo(Individual o) {
		if (o instanceof Individual) {
			Individual comp = (Individual) o;
			return this.getFitness().compareTo(comp.fitness);
		}
		return 0;
	}

	public double[] getN_01() {
		return N_01;
	}

}