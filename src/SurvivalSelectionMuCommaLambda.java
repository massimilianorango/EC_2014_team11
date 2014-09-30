import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Selects survivals discarding the parents and taking the best NUMBER_OF_SURVIVALS survivals based on rank.
 * 
 * @author max
 *
 */
public class SurvivalSelectionMuCommaLambda implements ISurvivalSelection {

	private final int NUMBER_OF_SURVIVALS;

	public SurvivalSelectionMuCommaLambda(int numberOfSurvivals) {
		this.NUMBER_OF_SURVIVALS = numberOfSurvivals;
	}

	@Override
	// TODO: WRONG! should be rank based and not absolute fitness based
	public void selectSurvivals(Population population, ArrayList<Individual> children) {
		Collections.sort(children); // TODO: not sort the same list in population
		List<Individual> survivors = children.subList(children.size() - NUMBER_OF_SURVIVALS, children.size());
		population.setIndividuals(new ArrayList<Individual>(survivors));
	}

}
