
import java.util.ArrayList;



public interface ISurvivalSelection {

    public void setFitnessCalculation(IFitnessCalculation fitness);
    
    /**
     * 
     * @param population
     * @param children
     */
    public void selectSurvivals(Population population, ArrayList<Individual> children);
    
}
