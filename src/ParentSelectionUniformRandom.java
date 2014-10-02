import java.util.ArrayList;

/**
 * Chooses parents randomly from the given population.
 * 
 * @author max
 *
 */
public class ParentSelectionUniformRandom implements IParentSelection {

	@Override
	public Individual[] selectParents(Population population, int numberOfParents) {
		int[] ar = player11.shuffleArray(population.getPopulationSize());
		ArrayList<Individual> currentPopulation = population.getIndividuals();
		Individual[] parents = new Individual[numberOfParents];
		// select first numberOfParents
		for (int i = 0; i < numberOfParents; i++) {
			parents[i] = currentPopulation.get(ar[i]);
		}

		return parents;
	}
	
	//diff with FUNC 3: 7
//	public Individual[] selectParents(Population population, int numberOfParents) {
//		//TODO: should use numberOfParents and not change individuals position in population
//		ArrayList<Individual> currentPopulation = population.getIndividuals();
//		Individual[] parents = new Individual[2];
//		parents[0] = currentPopulation.remove((player11.getRnd().nextInt(currentPopulation.size())));
//		parents[1] = currentPopulation.get((player11.getRnd().nextInt(currentPopulation.size())));
//		currentPopulation.add(parents[0]);
//		return parents;
//	}

}
