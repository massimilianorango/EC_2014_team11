
public class MutationUM implements IMutation {
	
	private static final int ALPHA = 5;
	private static final double MUTATION_RATIO = 0.1;
	private int evaluationsLimit;
	private int evaluations;
	
	public void setEvaluationsLimit(int evaluationsLimit) {
		this.evaluationsLimit = evaluationsLimit;
	}
	
	public void setEvaluations(int evaluations) {
		this.evaluations = evaluations;
	}

	@Override
	public void mutate(Individual individual) {
		double[] dna = individual.getDna();
		double[] sigmas = individual.getSigma_mutation_step_sizes();

		for (int i = 0; i < dna.length; i++) {
//			if(player11.getRnd().nextDouble() <= MUTATION_RATIO) {
			if(AlgorithmUM.ran.nextDouble() <= MUTATION_RATIO) {
				
				sigmas[i] = Math.pow(1.0 - ((double) evaluations / (double) evaluationsLimit), ALPHA);
				if(sigmas[i] <= 0.000000001) {
					sigmas[i] = 0.000000001;
				}
				
//				double gaussian_i = player11.getRnd().nextGaussian();
				double gaussian_i = individual.getN_01()[i];//AlgorithmUM.ran.nextGaussian();
				dna[i] = dna[i] + sigmas[i] * gaussian_i;
				
				// Curtailing values to [-5, 5[ if out of interval
				double maxValue = player11.MAX_SEARCH_VALUE;
				double minValue = player11.MIN_SEARCH_VALUE;
				if (dna[i] > player11.MAX_SEARCH_VALUE) {
					dna[i] = (dna[i] - minValue) % (maxValue - minValue) + minValue;//player11.MAX_SEARCH_VALUE;
				} else if (dna[i] < player11.MIN_SEARCH_VALUE) {
					dna[i] = (dna[i] - minValue) % (maxValue - minValue) + maxValue;//player11.MIN_SEARCH_VALUE;
				}				
			}
				
		}

		individual.setDna(dna);
		individual.setSigma_mutation_step_sizes(sigmas);

	}

}
