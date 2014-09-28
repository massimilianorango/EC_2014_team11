import java.util.ArrayList;


public class RecombinationES implements IRecombination {

	private IMutation mutation;

	@Override
	public ArrayList<Individual> crossover(Individual[] parents, int number_of_childs) {
		ArrayList<Individual> individuals = new ArrayList<Individual>();
        for(int i = 0; i<number_of_childs; i++){
            Individual child = new Individual(crossOperator(parents[0].getDna(), parents[1].getDna()));
            mutation.crossoverMutationValues(child, parents[0], parents[1]);
            individuals.add(child);
        }
        
        return individuals;
	}

	@Override
	public double[] crossOperator(double[] a, double[] b) {
		double[] child_dna = new double[10];
        for(int j = 0; j<10; j++){
        	if(player11.rnd.nextInt(2) == 0) {
        		child_dna[j] = a[j];  
        	} else {
        		child_dna[j] = b[j];
        	}
        }
        
        return child_dna;
	}

	@Override
	public void setMutation(IMutation mutation) {
		this.mutation = mutation;
	}

}
