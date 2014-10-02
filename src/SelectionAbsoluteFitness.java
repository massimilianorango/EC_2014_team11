import java.util.ArrayList;
import java.util.Collections;


public class SelectionAbsoluteFitness implements ISelection {
	
	private final int NUM_OF_SELECTED;
	
	public SelectionAbsoluteFitness(int numIndividualToSelect) {
		NUM_OF_SELECTED = numIndividualToSelect;
	}

	@Override
	public ArrayList<Individual> select(ArrayList<Individual> from) {
		Collections.sort(from);
		return new ArrayList<Individual>(from.subList(from.size() - NUM_OF_SELECTED, from.size()));
	}

}
