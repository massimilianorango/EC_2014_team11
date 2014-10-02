import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SurvivalSelectionMuPlusLambda implements ISurvivalSelection {

    private final int NUMBER_OF_SURVIVALS;

    public SurvivalSelectionMuPlusLambda(int numberOfSurvivals) {
        this.NUMBER_OF_SURVIVALS = numberOfSurvivals;
    }
    
    @Override
    public void selectSurvivals(Population population, ArrayList<Individual> children) {
        population.getIndividuals().addAll(children);
        Collections.sort(population.getIndividuals());
        List<Individual> survivors = population.getIndividuals().subList(population.getIndividuals().size() - NUMBER_OF_SURVIVALS,population.getIndividuals().size());
        population.setIndividuals(new ArrayList<Individual>(survivors));
    }

}
