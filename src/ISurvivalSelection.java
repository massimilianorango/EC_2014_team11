import java.util.ArrayList;



public interface ISurvivalSelection {

    /**
     * select from unselected population after children were added 
     * 
     * @param unselectedPopulation
     */
    public void selectSurvivals(Population unselectedPopulation, ArrayList<Individual> children);
    
}
