
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;


public class ParentSelectionSimple implements IParentSelection{

    public static int NUMBER_OF_ELITE = 4;
    private CopyOnWriteArrayList<Individual> elite = null;
    
    @Override
    public Individual[] selectParents(Population population) {
        Individual[] parents = new Individual[2];
        parents[0] = elite.remove(player11.rnd.nextInt(elite.size()));
        parents[1] = elite.remove(player11.rnd.nextInt(elite.size()));
        
        return parents;
    }

    @Override
    public void prepareSelection(Population population) {
        //sort full population
        Collections.sort(population.getIndividuals());
        //select best (last) NUMBER_OF_ELITE elements
        elite = new CopyOnWriteArrayList<Individual>(population.getIndividuals().subList((population.getIndividuals().size()-NUMBER_OF_ELITE) < 0 ? 0 : (population.getIndividuals().size()-NUMBER_OF_ELITE), population.getIndividuals().size()));
    }

    @Override
    public void setFitnessCalculation(IFitnessCalculation fitness) {
        // TODO Auto-generated method stub
        
    }

}
