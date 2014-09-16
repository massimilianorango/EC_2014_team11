import java.util.Random;

import org.vu.contest.ContestEvaluation;


public class DefaultInitialPopulation implements IInitialPopulation {

    public static int INITIAL_POPULATION_SIZE = 10;
    
    @Override
    public Population createInitialPopulation() {
        Population population = new Population();
        
        while(population.getIndividuals().size() < INITIAL_POPULATION_SIZE){
            double[] randomDna = new double[10];
            for(int i = 0; i<10; i++){
                //bounds are [-5.0,5.0[
                randomDna[i] = player11.rnd.nextDouble()*10.0 - 5.0;
            }
            
            population.addIndividual(player11.createIndividual(randomDna));
        }
        
        return population;
    }

}
