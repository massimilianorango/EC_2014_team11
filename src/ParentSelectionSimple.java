import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParentSelectionSimple implements IParentSelection {

	public static int NUMBER_OF_ELITE = 4; // TODO: should be >= the number of total parents to choose
	private CopyOnWriteArrayList<Individual> elite;

	@Override
	public Individual[] selectParents(Population population, int numberOfParents) {
		Individual[] parents = new Individual[2];
		parents[0] = elite.remove(player11.getRnd().nextInt(elite.size()));
		parents[1] = elite.remove(player11.getRnd().nextInt(elite.size()));

		return parents;
	}

	public void prepareSelection(Population population) {
		Collections.sort(population.getIndividuals()); // sort full population
		// select best (last) NUMBER_OF_ELITE elements
		elite = new CopyOnWriteArrayList<Individual>(population.getIndividuals().subList(
				(population.getIndividuals().size() - NUMBER_OF_ELITE) < 0 ? 0
						: (population.getIndividuals().size() - NUMBER_OF_ELITE), population.getIndividuals().size()));
	}

}
