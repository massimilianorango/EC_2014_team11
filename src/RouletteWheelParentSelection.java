import java.util.ArrayList;
import java.util.Random;


public class RouletteWheelParentSelection implements IParentSelection {

    public static int NUMBER_OF_PARENTS = 2;
    private ArrayList<Double> probabilityList;
    
    @Override
    public Individual[] selectParents(Population population) {
        ArrayList<Individual> allIndividuals = population.getIndividuals();
        
        Individual[] parents = new Individual[NUMBER_OF_PARENTS];
        for( int j = 0; j< NUMBER_OF_PARENTS; j++){
            Double random = player11.rnd.nextDouble();
            for(int i = 0; i<probabilityList.size(); i++){
                if( probabilityList.get(i) > random ){
                    // found parent in range e.g if random=0.2 -> [0.1][0.15][x][0.32]..[1]
                    parents[j] = population.getIndividuals().get(i);
                    break;
                }
            }
        }
        
        return parents;
    }
    
    @Override
    public void prepareSelection(Population population) {
        probabilityList = new ArrayList<Double>();
        
        //caculate overall fitness sum
        double fitness_sum = 0;
        for(Individual individual : population.getIndividuals()){
            fitness_sum += individual.getFitness();
        }
        
        double probability_sum = 0;
        //calculate individual probability based on relation to overall fitness sum
        //create probability list like [0.1][0.15][0.21][0.32]..[1]
        for(Individual individual : population.getIndividuals()){
            double individual_probability = individual.getFitness()/fitness_sum;
            probability_sum += individual_probability;
            probabilityList.add(probability_sum);
        }

    }

}
