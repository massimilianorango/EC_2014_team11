import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class RouletteWheelParentSelection implements IParentSelection {

    public static int NUMBER_OF_PARENTS = 2;
    private double[] probabilityIntervals;
    
    @Override
    public Individual[] selectParents(Population population) {
        ArrayList<Individual> allIndividuals = population.getIndividuals();
        
        Individual[] parents = new Individual[NUMBER_OF_PARENTS];
        for( int j = 0; j< NUMBER_OF_PARENTS; j++){
            int i = player11.getProbabilityBasedRandomIndex(probabilityIntervals);
            parents[j] = allIndividuals.get(i);
        }
        
        return parents;
    }
    
    @Override
    public void prepareSelection(Population population) {
        probabilityIntervals = new double[population.getIndividuals().size()];
        
        Collections.sort(population.getIndividuals());
        //caculate overall fitness sum
        double fitness_sum = 0;
        for(Individual individual : population.getIndividuals()){
            fitness_sum += individual.getFitness();
        }
        
        double probability_sum = 0;
        //calculate individual probability based on relation to overall fitness sum
        //create probability list like [0.1][0.15][0.21][0.32]..[1]
        for(int i = 0; i<population.getIndividuals().size(); i++){
            double individual_probability = population.getIndividuals().get(i).getFitness()/fitness_sum;
            probability_sum += individual_probability;
            probabilityIntervals[i] = probability_sum;
        }

    }

}
