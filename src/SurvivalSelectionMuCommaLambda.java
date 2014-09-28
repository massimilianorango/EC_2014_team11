import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SurvivalSelectionMuCommaLambda implements ISurvivalSelection {

	@Override
	public void setFitnessCalculation(IFitnessCalculation fitness) {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectSurvivals(Population population,
			ArrayList<Individual> children) {
		Collections.sort(children);
		population.removeAllIndividuals();
		List<Individual> list = children.subList(children.size() - player11.MAX_POPULATION_SIZE, children.size());
		for(Individual ind: list) {
			population.addIndividual(ind);
		}
	}

}
