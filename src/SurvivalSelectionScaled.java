
import java.util.ArrayList;
import java.util.Collections;


public class SurvivalSelectionScaled implements ISurvivalSelection {

    public static double S_INDIVIDUAL_ADVANTAGE = 2;
    
    @Override
    public void selectSurvivals(Population population, ArrayList<Individual> children) {
        population.removeAllIndividuals();
        Collections.sort(children);
        int mu_count = children.size();
        
        double[] probabilityIntervals = new double[children.size()];
        double probabilitySum = 0;
        for(int i = 0; i<children.size(); i++){
            int rank = i;
            probabilitySum += (2-S_INDIVIDUAL_ADVANTAGE)/mu_count 
                    + 2.0*rank*(S_INDIVIDUAL_ADVANTAGE-1)
                    / (mu_count*(mu_count-1));
            probabilityIntervals[i] = probabilitySum;
        }
        
        ArrayList<Integer> chosenChildren = new ArrayList<Integer>();
        for(int i = 0; i<player11.MAX_POPULATION_SIZE; i++){
            int randomIndex = player11.getProbabilityBasedRandomIndex(probabilityIntervals);
            if( chosenChildren.contains(randomIndex) ){
                continue;
            }else{
                chosenChildren.add(randomIndex);
            }
            population.addIndividual(children.get(randomIndex));
        }
        
    }

    @Override
    public void setFitnessCalculation(IFitnessCalculation fitness) {
        // TODO Auto-generated method stub
        
    }

}
