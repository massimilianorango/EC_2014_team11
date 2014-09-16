import java.util.ArrayList;


public class FitnessAndAgeBasedSurvivalSelection implements ISurvivalSelection{

    public static int MAXIMUM_POPULATION_SIZE = 100;
    public static int MAXIMUM_AGE = 2;
    
    public void selectSurvivals(Population unselectedPopulation, ArrayList<Individual> children){
        ArrayList<Individual> unselectedIndividuals = unselectedPopulation.getIndividuals();
        ArrayList<Individual> selectedIndividuals = unselectedPopulation.getIndividuals();
        Population selectedPopulation = unselectedPopulation;

        for(Individual individual : unselectedIndividuals){
            //skip all individuals older than 2 generations
            if( unselectedPopulation.getGeneration() - individual.getGeneration() > MAXIMUM_AGE){
                continue;
            }else{
                //TODO select based on fitness
                selectedPopulation.addIndividual(individual);
            }  
        }

        selectedPopulation.setIndividuals(selectedIndividuals);
    }
}
