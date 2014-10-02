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

}
