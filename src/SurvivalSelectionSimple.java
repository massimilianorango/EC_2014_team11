import java.util.ArrayList;
import java.util.Collections;

public class SurvivalSelectionSimple implements ISurvivalSelection {

	private final int MAX_POPULATION_SIZE;

	public SurvivalSelectionSimple(int maxPopulationSize) {
		this.MAX_POPULATION_SIZE = maxPopulationSize;
	}

	@Override
	public void selectSurvivals(Population population, ArrayList<Individual> children) {
		population.addIndividuals(children);
		Collections.sort(population.getIndividuals());
		// remove unfittest individuals from list until max_population_size is reached
		while (population.getIndividuals().size() >= MAX_POPULATION_SIZE) {
			population.getIndividuals().remove(0);
		}
	}
}
