import java.util.ArrayList;


public class AlgorithmMM extends AbstractEA {
	
	private final int MU; // number of individuals in each generation (parent population, p.81 book)
	private final int LAMBDA; // number of offspring to be generated in each generation
	private final static double ALPHA = 3;
	private final static double SIGMA_SHARE = 12;
	private double[][] distanceMatrix;
	
	public AlgorithmMM(int mu, int lambda) {
		this.MU = mu;
		this.LAMBDA = lambda;
		this.initialPopulation = new InitialPopulationSimple(MU);
		this.population = initialPopulation.createInitialPopulation();
		this.parentSelection = new ParentSelectionMM();
		this.recombination = new RecombinationDiscreteAndIntermediate();
		this.mutation = new MutationUncorrelatedMM();
		this.survivalSelection = new SurvivalSelectionMuCommaLambda(new SelectionAbsoluteFitness(MU));
		//TODO: try with FPS even for survivals
	}

	@Override
	public void run() {
		for (Individual ind : population.getIndividuals()) {
			evaluateInitialIndividual(ind);
		}
		while (getEvals() < evaluationsLimit) {
			ArrayList<Individual> children = new ArrayList<Individual>();
			calculateDistances(population.getIndividuals());
			adjustFitness(population.getIndividuals());
			for (int i = 0; i < LAMBDA; i++) {
				Individual[] couple = parentSelection.selectParents(population, 2);
				Individual child = recombination.crossover(couple, 1).get(0);
				mutation.mutate(child);
				evaluateIndividual(child, population.getGeneration()+1);
				children.add(child);
			}
			calculateDistances(children);
			adjustFitness(children);
			survivalSelection.selectSurvivals(population, children);
			population.increaseGeneration();
//			System.out.println("Generation " + population.getGeneration() + " Result: "
//					+ population.getBestIndividual());
		}
	}
	
	private void adjustFitness(ArrayList<Individual> individuals) {
		for(int i = 0; i < individuals.size(); i++) {
			Individual ind_i = individuals.get(i);
			ind_i.setFitness(ind_i.getFitness()/sumSharingFunction(individuals, i));
		}
	}

	private double sumSharingFunction(ArrayList<Individual> individuals, int i) {
		double sum = 0;
		for(int j = 0; j < individuals.size(); j++) {
			double d = distanceMatrix[i][j]; 
			if(d <= SIGMA_SHARE)
				sum += 1 - Math.pow(d / SIGMA_SHARE, ALPHA);
		}
		return sum;
	}

	private void calculateDistances(ArrayList<Individual> individuals) {
		int size = individuals.size();
		distanceMatrix = new double[size][size];
		for(int i = 0; i < size; i++) {
			double[] x_a = individuals.get(i).getDna(); 
			for(int j = i; j < size; j++) {
				if(i != j) {
					double[] x_b = individuals.get(j).getDna();
					double dist_ij = euclidianDistance(x_a, x_b);
					distanceMatrix[i][j] = dist_ij;
					distanceMatrix[j][i] = dist_ij;					
				} else {
					distanceMatrix[i][j] = 0;
				}
			}
		}
		
	}
	
	private double euclidianDistance(double[] x_a, double[] x_b) {
		double sum = 0.0;
		for(int i = 0; i < x_a.length; i++) {
			sum += Math.pow(x_a[i] - x_b[i], 2);
		}
		return Math.sqrt(sum);
	}

}
