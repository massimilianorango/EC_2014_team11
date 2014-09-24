import java.util.ArrayList;
import java.util.Collections;


public class SurvivalSelectionSimple implements ISurvivalSelection {

    @Override
    public void selectSurvivals(Population population, ArrayList<Individual> children) {
        population.addIndividuals(children);
        Collections.sort(population.getIndividuals());
        //remove unfittest individuals from list until max_population_size is reached
        while( population.getIndividuals().size() >= player11.MAX_POPULATION_SIZE ){
            population.getIndividuals().remove(0);
        }
    }

    @Override
    public void setFitnessCalculation(IFitnessCalculation fitness) {
        // TODO Auto-generated method stub
        
    }

}
