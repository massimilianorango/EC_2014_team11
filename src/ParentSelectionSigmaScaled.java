import java.util.ArrayList;
import java.util.Collections;


public class ParentSelectionSigmaScaled implements IParentSelection {

    private double[] probabilityIntervals;
    public static double C_SIGMA_VALUE = 2.0;

    @Override
    public Individual[] selectParents(Population population, int numberOfParents) {
        prepareSelection(population);
        ArrayList<Individual> currentPopulation = population.getIndividuals(); 
        Individual[] parents = new Individual[numberOfParents];
        
        for(int i = 0; i<numberOfParents; i++){
            int j = player11.getProbabilityBasedRandomIndex(probabilityIntervals);
            parents[i] = currentPopulation.get(j);
        }
    
        return parents;
    }


    public void prepareSelection(Population population) {
        probabilityIntervals = new double[population.getIndividuals().size()];
        
        Collections.sort(population.getIndividuals());
        //caculate overall fitness sum
        double fitness_sum = 0;
        for(Individual individual : population.getIndividuals()){
            fitness_sum += individual.getFitness();
        }
        
        double fitness_mean = fitness_sum/population.getIndividuals().size();
        double deviation_sum = 0.0;
        for(Individual individual : population.getIndividuals()){
            deviation_sum += (individual.getFitness()-fitness_mean)*(individual.getFitness()-fitness_mean);
        }
        double standard_deviation = Math.sqrt(deviation_sum/population.getIndividuals().size());
        
        double[] scaledFitnesses = new double[population.getIndividuals().size()];
        double scaled_fitness_sum = 0.0;
        //calculate individual probability based on relation to overall fitness sum
        //create probability list like [0.1][0.15][0.21][0.32]..[1]
        for(int i = 0; i<population.getIndividuals().size(); i++){
            scaledFitnesses[i] = Math.max(population.getIndividuals().get(i).getFitness()
                    -(fitness_mean-C_SIGMA_VALUE*standard_deviation),0.0);
            scaled_fitness_sum += scaledFitnesses[i];
        }
        
        double probability_sum = 0;
        for(int i = 0; i<population.getIndividuals().size(); i++){
            double individual_probability = scaledFitnesses[i]/scaled_fitness_sum;
            probability_sum += individual_probability;
            probabilityIntervals[i] = probability_sum;
        }
    }


}
