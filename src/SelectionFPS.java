import java.util.ArrayList;


public class SelectionFPS implements ISelection {
	
	private final int NUM_OF_SELECTED;
	
	public SelectionFPS(int numIndividualToSelect) {
		NUM_OF_SELECTED = numIndividualToSelect;
	}

	@Override
	public ArrayList<Individual> select(ArrayList<Individual> from) {
		ArrayList<Individual> selected = new ArrayList<Individual>(NUM_OF_SELECTED);
		double minFitness = from.get(0).getFitness();
		for(int i = 1; i < from.size(); i++) {
			double currentFitness = from.get(i).getFitness(); 
			if(currentFitness < minFitness) {
				minFitness = currentFitness;
			}
		}
		
		//minFitness is to normalize if there is at least a negative fitness value
		if(minFitness >= 0) { 
			minFitness = 0; 
		}
		
		double fitnessSum = 0;
		for(int i = 0; i < from.size(); i++) {
			fitnessSum += from.get(i).getFitness() + (-1 * minFitness);
		}
		
		double[] a = new double[from.size()];
		double sum_prob = 0;
		for(int i = 0; i < a.length; i++) {
			sum_prob += (from.get(i).getFitness() + (-1 * minFitness)) / fitnessSum;
			a[i] = sum_prob;
		}
		
		for(int i = 0; i < NUM_OF_SELECTED; ) {
			double r = player11.getRnd().nextDouble();
			int j = 0;
			while(j < a.length && a[j] < r) {
				j++;
			}
			Individual chosenInd = from.get(i); 
			if(!selected.contains(chosenInd)) { 
				selected.add(chosenInd);
				i++;
			}
		} 
		
		return selected;
	}

}
