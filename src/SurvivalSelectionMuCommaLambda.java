import java.util.ArrayList;

/**
 * Selects survivors discarding the parents and taking the best children according to the selectionAlgorithm.
 * 
 * @author max
 *
 */
public class SurvivalSelectionMuCommaLambda implements ISurvivalSelection {

	private ISelection selectionAlgorithm;

	public SurvivalSelectionMuCommaLambda(ISelection selectionAlgorithm) {
		this.selectionAlgorithm = selectionAlgorithm;
	}

	@Override
	public void selectSurvivals(Population population, ArrayList<Individual> children) {
		// (mu,lambda) replaces the parents with the children selected by the selectionAlgorithm
		population.setIndividuals(selectionAlgorithm.select(children));
	}

}
