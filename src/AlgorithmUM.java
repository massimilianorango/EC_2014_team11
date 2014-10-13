import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.vu.contest.ContestEvaluation;


public class AlgorithmUM extends AbstractEA {
	
	private int lambda;
	public static Random ran = new Random(System.currentTimeMillis());
	
	public AlgorithmUM(int mu, int lambda) {
		this.lambda = lambda;
		this.initialPopulation = new InitialPopulationUM(mu);//InitialPopulationSimple(mu);
		this.population = initialPopulation.createInitialPopulation();
		this.parentSelection = new ParentSelectionUM();
		this.recombination = new RecombinationUM();
		this.mutation = new MutationUM();
		this.survivalSelection = new SurvivalSelectionMuCommaLambda(new SelectionAbsoluteFitness(mu));
	}
	
	@Override
	public void setEvaluation(ContestEvaluation evaluation, int evaluationsLimit) {
		super.setEvaluation(evaluation, evaluationsLimit);
		((MutationUM) mutation).setEvaluationsLimit(evaluationsLimit);
	}

	@Override
	public void run() {
		for (Individual ind : population.getIndividuals()) {
			evaluateInitialIndividual(ind);
		}
		while (getEvals() < evaluationsLimit && lambda != 0) {
			ArrayList<Individual> children = new ArrayList<Individual>();
			((MutationUM) mutation).setEvaluations(getEvals() + lambda);
			for (int i = 0; i < lambda; i++) {
				Individual[] couple = parentSelection.selectParents(population, 2);
				Individual child = recombination.crossover(couple, 1).get(0);
				mutation.mutate(child);
				evaluateIndividual(child, population.getGeneration()+1);
				children.add(child);
			}
			survivalSelection.selectSurvivals(population, children);
			Collections.reverse(population.getIndividuals());
			population.increaseGeneration();
			lambda = Math.min(lambda, evaluationsLimit - getEvals());
//			System.out.println("Generation " + population.getGeneration() + " Result: "
//					+ population.getBestIndividual());
		}

	}

}
