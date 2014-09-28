import java.util.ArrayList;


public class ParentSelectionUniformRandom implements IParentSelection {

	@Override
	public Individual[] selectParents(Population population) {
		ArrayList<Individual> currentPopulation = population.getIndividuals(); 
		Individual[] parents = new Individual[2];
		parents[0] = currentPopulation.remove((player11.rnd.nextInt(currentPopulation.size())));
		parents[1] = currentPopulation.get((player11.rnd.nextInt(currentPopulation.size())));
		currentPopulation.add(parents[0]);
		return parents;
	}

	@Override
	public void prepareSelection(Population population) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFitnessCalculation(IFitnessCalculation fitness) {
		// TODO Auto-generated method stub

	}

}
