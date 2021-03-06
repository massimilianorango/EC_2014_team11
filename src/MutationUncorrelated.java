/**
 * Implements the Uncorrelated n-step size mutation (p. 76 book).
 * 
 * @author max
 *
 */
public class MutationUncorrelated implements IMutation {

//	private double ALPHA;
	private double TAU_1;
	private double TAU;
	private double EPSILON;

	public MutationUncorrelated(double ALPHA, double EPSILON) {
//		this.ALPHA = ALPHA;
		TAU_1 = ALPHA / Math.sqrt(2 * 10);
		TAU = ALPHA / Math.sqrt(2 * Math.sqrt(10));
		this.EPSILON = EPSILON;
	}

	public MutationUncorrelated(double ALPHA) {
		this(ALPHA, 0.000001);
	}

	public MutationUncorrelated() {
		this(1.0);
	}

	@Override
	public void mutate(Individual individual) {

		double[] dna = individual.getDna();
		double[] sigmas = individual.getSigma_mutation_step_sizes();
		
		double gaussian = player11.getRnd().nextGaussian();

		for (int i = 0; i < dna.length; i++) {
			
			double gaussian_i = player11.getRnd().nextGaussian();

			sigmas[i] = sigmas[i] * Math.exp(TAU_1 * gaussian + TAU * gaussian_i);
			// Reset sigma[i] if too small
			if (sigmas[i] < EPSILON) {
				sigmas[i] = EPSILON;
			}

			dna[i] = dna[i] + sigmas[i] * gaussian_i;
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
