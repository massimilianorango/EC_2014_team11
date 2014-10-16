/**
 * Implements the Uncorrelated n-step size mutation (p. 76 book).
 * 
 * @author max
 *
 */
public class MutationES2 implements IMutation {

//	private double ALPHA;
	private double TAU;
	private double EPSILON;

	public MutationES2(double ALPHA, double EPSILON) {
//		this.ALPHA = ALPHA;
		TAU = ALPHA / Math.sqrt(10);
		this.EPSILON = EPSILON;
	}

	public MutationES2(double ALPHA) {
		this(ALPHA, 0.000001);
	}

	public MutationES2() {
		this(0.8);
	}

	@Override
	public void mutate(Individual individual) {

		double[] dna = individual.getDna();
		double[] sigmas = individual.getSigma_mutation_step_sizes();
		
		double gaussian = player11.getRnd().nextGaussian();
		
		sigmas[0] = sigmas[0] * Math.exp(TAU * gaussian);
		// Reset sigma[i] if too small
		if (sigmas[0] < EPSILON) {
			sigmas[0] = EPSILON;
		}

		for (int i = 0; i < dna.length; i++) {
			
			double gaussian_i = player11.getRnd().nextGaussian();

			dna[i] = dna[i] + sigmas[0] * gaussian_i;
			// Curtailing values to [-5, 5[ if out of interval
			if (dna[i] > player11.MAX_SEARCH_VALUE) {
				dna[i] = player11.MAX_SEARCH_VALUE;
			} else if (dna[i] < player11.MIN_SEARCH_VALUE) {
				dna[i] = player11.MIN_SEARCH_VALUE;
			}
		}

		individual.setDna(dna);
		individual.setSigma_mutation_step_sizes(sigmas);
	}

}
