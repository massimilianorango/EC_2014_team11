
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class SurvivalSelectionRouletteWheelFitnessAndAgeBased implements ISurvivalSelection{

    public static int MAXIMUM_AGE = 30;
    private IFitnessCalculation fitness;
    
    public void setFitnessCalculation(IFitnessCalculation fitness){
        this.fitness = fitness;
    }
    
    public void selectSurvivals(Population population, ArrayList<Individual> children){
        Iterator<Individual> iter = population.getIndividuals().iterator();
        ArrayList<Individual> generationSurvivors = new ArrayList<Individual>();
        //delete individuals which are MAXIMUM_AGE years old
        while(iter.hasNext()){
            Individual individual = iter.next();
            if( population.getGeneration() - individual.getGeneration() < MAXIMUM_AGE ){
                generationSurvivors.add(individual);
            }
        }
        
        //add children to population
        for(Individual individual : children){
            generationSurvivors.add(individual);
        }
        
        //sort by fitness levels
        Collections.sort(generationSurvivors);
        System.out.println(generationSurvivors.size());
        
        //if population size is still smaller then maximum population size, then we can finish here
        if( generationSurvivors.size() <= player11.MAX_POPULATION_SIZE ){
            population.setIndividuals(generationSurvivors);
            return;
        }

        //collect sum of all fitness levels
        double fitnessSum = 0;
        double[] fitnessValues = new double[generationSurvivors.size()];
        for(int i = 0; i < generationSurvivors.size(); i++){
        	fitnessValues[i] = fitness.recalculateFitness(generationSurvivors.get(i).getFitness()); 
            fitnessSum += fitnessValues[i];
        }
        
        //setup probability intervals
        double[] probabilityIntervals = new double[generationSurvivors.size()];
        double probabilitySum = 0.0;
        for(int i = 0; i < generationSurvivors.size(); i++){
            probabilitySum += fitnessValues[i]/fitnessSum;
            probabilityIntervals[i] = probabilitySum;
        }
        
        ArrayList<Individual> fitnessSurvivors = new ArrayList<Individual>();
        //choose survivors
        while(fitnessSurvivors.size() < player11.MAX_POPULATION_SIZE){
            int index = player11.getProbabilityBasedRandomIndex(probabilityIntervals);
            if( fitnessSurvivors.contains(generationSurvivors.get(index)) ){
                continue;
            }
            fitnessSurvivors.add(generationSurvivors.get(index));
        }
        
        population.setIndividuals(fitnessSurvivors);
    }
}
