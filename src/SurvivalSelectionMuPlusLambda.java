import java.util.ArrayList;


public class SurvivalSelectionMuPlusLambda implements ISurvivalSelection {

    ISelection selectionAlgorithm;
    
    public SurvivalSelectionMuPlusLambda(ISelection selectionAlgorithm) {
        this.selectionAlgorithm = selectionAlgorithm;
    }
    
    @Override
    public void selectSurvivals(Population population, ArrayList<Individual> children) {
        children.addAll(population.getIndividuals());
        population.setIndividuals(selectionAlgorithm.select(children));

    }

}
