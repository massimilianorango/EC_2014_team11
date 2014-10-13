import java.util.ArrayList;


public class ParentSelectionUM implements IParentSelection {
	
	private final int SIZE_TOURNAMENT = 3;

	@Override
	public Individual[] selectParents(Population population, int numberOfParents) {
		ArrayList<Individual> currentPopulation = population.getIndividuals();
		Individual[] parents = new Individual[numberOfParents];
		parents[0] = tournament(currentPopulation);
		do {
			parents[1] = tournament(currentPopulation);
		} while(parents[0] == parents[1]);
		return parents;
	}

	private Individual tournament(ArrayList<Individual> population) {
		ArrayList<Individual> selected = new ArrayList<Individual>();
		Individual fittest = null, current = null;
		double bestFitness = (double)Integer.MIN_VALUE, currentFitness = 0;
		int i = 0;
		while(i < SIZE_TOURNAMENT) {
//			current = population.get(player11.getRnd().nextInt(population.size()));
			current = population.get(AlgorithmUM.ran.nextInt(population.size()));
			if(!selected.contains(current)) {
				selected.add(current);
				currentFitness = current.getFitness();
				if(currentFitness > bestFitness) {
					fittest = current;
					bestFitness = currentFitness;
				}
				i++;
			}
		}
		return fittest;
	}

}
