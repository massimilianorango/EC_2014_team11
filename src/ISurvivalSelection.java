import java.util.ArrayList;



public interface ISurvivalSelection {

    /**
     * 
     * @param population
     * @param children
     */
    public void selectSurvivals(Population population, ArrayList<Individual> children);
    
}
