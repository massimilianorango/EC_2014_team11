import java.util.ArrayList;
import java.util.Collections;


public class ParentSelectionRanked implements IParentSelection {

    private double[] probabilityIntervals;
    private double S_INDIVIDUAL_ADVANTAGE;

    public ParentSelectionRanked(){
        this(2.0);
    }
    
    public ParentSelectionRanked(double S_INDIVIDUAL_ADVANTAGE){
        this.S_INDIVIDUAL_ADVANTAGE = S_INDIVIDUAL_ADVANTAGE;
    }
    
    @Override
    public Individual[] selectParents(Population population, int numberOfParents) {
        prepareSelection(population);
        ArrayList<Individual> currentPopulation = population.getIndividuals(); 
        Individual[] parents = new Individual[numberOfParents];
        
        ArrayList<Individual> selected = new ArrayList<Individual>();
        for(int i = 0; i<numberOfParents; i++){
            int j = -1;
            do{
                j = player11.getProbabilityBasedRandomIndex(probabilityIntervals);
            }while(selected.contains(currentPopulation.get(j)));
            parents[i] = currentPopulation.get(j);
            selected.add(parents[i]);
        }
    
        return parents;
    }


    public void prepareSelection(Population population) {
        probabilityIntervals = new double[population.getIndividuals().size()];
        
        Collections.sort(population.getIndividuals());
        
        double probability_sum = 0;
        double mu_count = population.getIndividuals().size();
        for(int rank = 0; rank<population.getIndividuals().size(); rank++){
            double individual_probability = (2 - S_INDIVIDUAL_ADVANTAGE) / mu_count + 2.0 * rank * (S_INDIVIDUAL_ADVANTAGE - 1)
                    / (mu_count * (mu_count - 1));
            probability_sum += individual_probability;
            probabilityIntervals[rank] = probability_sum;
        }
    }


}
